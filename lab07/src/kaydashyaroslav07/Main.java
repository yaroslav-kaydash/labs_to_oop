package kaydashyaroslav07;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    //class for displaying time
    static class WorkTime {
        //schedule data
        String workTime;

        // check for correct time input
        boolean isVariableTime(String time) {
            Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            Matcher m = p.matcher(time);
            return m.matches();
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

        //return the robotic time
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

    static class TradingPoint {

    String name;
    String address;

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

    //raise the name
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

static class ProgressiveTradingPoint extends TradingPoint {

    List<BigInteger>phones;
    String specialization;
    WorkTime workTime;

    //check for correct phone numbers
    private boolean isValidPhoneNumber(List<BigInteger> t) {
        for (BigInteger i : t) {
            if (i.toString().length() != 9) {
                return false;
            }
        }
        return true;
    }

    //constructor
    public ProgressiveTradingPoint(String name, String address, List<BigInteger> phones, String specialization, WorkTime workTime) {
        //visit the perent constructor(TradingPoint)
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
                "phones=" + phones +
                ", specialization='" + specialization + '\'' +
                ", workTime=" + workTime +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public List<BigInteger> getTelephones() {
        return phones;
    }

    public void setTelephones(List<BigInteger> telephones) {
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
        //create some variables for demonstration
         TradingPoint ptp = new ProgressiveTradingPoint("Le_paste",
                 "вул Ньютона 129Б",
                 Stream.of(new BigInteger("111111111"), new BigInteger("222222222")).collect(Collectors.toList()),
                 "щось",
                 new WorkTime("Вівторок", "Середа", "8:00", "19:00"));

                TradingPoint tp = new TradingPoint("Item", "address");

                TradingPoint ptp1 = new ProgressiveTradingPoint("sir",
                        "address",
                        Stream.of(new BigInteger("579911516")).collect(Collectors.toList()),
                        "stalker",
                        new WorkTime("Wednesday", "Thursday", "8:00", "23:00"));

                //create an array from the provided variables
                TradingPoint[] tradingPoints = new TradingPoint[]{ptp, tp, ptp1};

                // print all variables from the array
                Arrays.stream(tradingPoints).forEach(System.out::println);

                //demonstrate the getter
                System.out.println(ptp.getAddress());
                //demonstrate the setter
                ptp.setAddress("new address");
                //show the new value
                System.out.println(ptp.getAddress());
            }
        }