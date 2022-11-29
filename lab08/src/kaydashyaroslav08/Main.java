package kaydashyaroslav08;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    //class for displaying time
    public static class WorkTime {
        //schedule data
        private String workTime;

        // check for correct time input
        boolean isVariableTime(String time) {
            Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            Matcher m = p.matcher(time);
            return m.matches();
        }

        //constructor for java bean
        public WorkTime(){

        }

        //constructor
        public WorkTime(String startDay, String endDay, String startTime, String endTime) {
            // check for correct time input
            if (!isVariableTime(startTime) || !isVariableTime(endTime)) {
                throw new IllegalArgumentException("Enter current time!!!");
            }
            //collect data into 1 variable
            this.workTime = String.format("Work day: %s - %s; Time: %s - %s", startDay, endDay, startTime, endTime);
        }

        //return work time
        public String getWorkTime() {
            return workTime;
        }

        //assign new value
        public void setWorkTime(String startDay, String endDay, String startTime, String endTime) {
            if (isVariableTime(startTime) || isVariableTime(endTime)) {
                throw new IllegalArgumentException("Enter current time!!!");
            }
            this.workTime = String.format("Work day: %s - %s; Time: %s - %s", startDay, endDay, startTime, endTime);
        }

        //override the toString method for correct output
        @Override
        public String toString() {
            return workTime;
        }

    }

    public static class TradingPoint {

        private String name;
        private String address;

        //constructor for java bean
        public TradingPoint(){

        }

        //constructor
        public TradingPoint(String name, String address) {
            this.name = name;
            this.address = address;
        }

        //override the toString method for correct output
        @Override
        public String toString() {
            return "TradingPoint{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        //return the name
        public String getName() {
            return name;
        }

        //reassign the new value to the variable
        public void setName(String name) {
            this.name = name;
        }

        //return the address
        public String getAddress() {
            return address;
        }

        //reassign the new value to the variable
        public void setAddress(String address) {
            this.address = address;
        }

    }

    public static class ProgressiveTradingPoint extends TradingPoint {

        private List<String> phones;
        private String specialization;
        private WorkTime workTime;

        //check for correct phone numbers
        private boolean isValidPhoneNumber(List<String> t) {
            for (String i : t) {
                if (i.length() != 9) {
                    return false;
                }
            }
            return true;
        }

        //constructor for java bean
        public ProgressiveTradingPoint(){

        }

        //constructor
        public ProgressiveTradingPoint(String name, String address, List<String> phones, String specialization, WorkTime workTime) {
            //visit the parent constructor(TradingPoint)
            super(name, address);
            // checking phones
            if (!isValidPhoneNumber(phones)) {
                throw new IllegalArgumentException("Enter correct phone numbers!!!");
            }
            this.phones = phones;
            this.specialization = specialization;
            this.workTime = workTime;
        }

        //override the toString method for correct output
        @Override
        public String toString() {
            return "ProgressiveTradingPoint{" +
                    "telephones=" + phones +
                    ", specialization='" + specialization + '\'' +
                    ", workTime=" + workTime +
                    ", name='" + super.getName() + '\'' +
                    ", address='" + super.getAddress() + '\'' +
                    '}';
        }

        public List<String> getTelephones() {
            return phones;
        }

        public void setTelephones(List<String> telephones) {
            this.phones = telephones;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public WorkTime getWorkTime() {
            return workTime;
        }

        public void setWorkTime(WorkTime workTime) {
            this.workTime = workTime;
        }

    }


    public static void main(String[] args) {

        //Создайте несколько переменних для демонстрации

        List<String> phones = Stream.of("111111111", "222222222").collect(Collectors.toList());
        WorkTime workTime = new WorkTime("Вівторок", "Середа", "8:00", "19:00");
        List<String> phones1 = Stream.of("579911516").collect(Collectors.toList());
        WorkTime workTime1 = new WorkTime("Wednesday", "Thursday", "8:00", "23:00");

        TradingPoint ptp = new ProgressiveTradingPoint("Le_paste",
                "вул Ньютона 129Б",
                phones,
                "щось",
                workTime);

        TradingPoint tp = new TradingPoint("Item", "address");

        TradingPoint ptp1 = new ProgressiveTradingPoint("sir",
                "address",
                phones1,
                "stalker",
                workTime1);

        //create a few variables to demonstrate
        TradingPoint[] tradingPoints = new TradingPoint[]{ptp, tp, ptp1};

        System.out.println("Enter a number to select an action");

        //text menus:
        System.out.println("1 - print arr");
        System.out.println("2 - print arr elem");
        System.out.println("3 - write arr");
        System.out.println("4 - read arr");
        System.out.println("5 - end");


        //first input
        Scanner myInput1 = new Scanner(System.in);

        // end flag
        boolean isEnd = true;

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            ///switch use first input as parameter
            //use first input parameter
            switch (numberImp) {
                case ("1"):
                    //print array
                    Arrays.stream(tradingPoints).forEach(System.out::println);
                    break;
                case ("2"):
                    //print arr elem
                    System.out.println("Enter index of element\n");
                    Scanner myInput2 = new Scanner(System.in);
                    String numberImp1 = myInput2.nextLine();
                    int num = Integer.parseInt(numberImp1);
                    //check for a number if it is greater than the length of the array, go back
                    if (num > tradingPoints.length){
                        System.out.println("Enter correct value!!!");
                        break;
                    }
                    System.out.println(tradingPoints[num]);
                    break;
                case ("3"):
                    //try catch block is needed in order to catch errors without it, the program will not work
                    try {
                        //create several objects for correct writing to the file
                        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("safe.xml"));
                        XMLEncoder encoder = new XMLEncoder(fos);
                        //writeObject - a method that writes an object to an XML file
                        encoder.writeObject(tradingPoints);
                        encoder.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("4"):
                    //try catch block is needed in order to catch errors without it, the program will not work
                    try {
                        //create several objects for the correct taking from the file
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("safe.xml"));
                        XMLDecoder decoder = new XMLDecoder(bis);
                        //drag the object from the file
                        TradingPoint[] tradingPoint2 = (TradingPoint[]) decoder.readObject();
                        decoder.close();
                        bis.close();
                        //print object
                        Arrays.stream(tradingPoint2).forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("5"):
                    //end the loop
                    isEnd = false;
                    System.out.println("end");
                    break;
                //if user uses invalid input in console
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
            }
        }
    }
}