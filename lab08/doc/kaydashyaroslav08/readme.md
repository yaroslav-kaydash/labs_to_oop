Лабораторна робота № 8.Основи введення/виведення Java SE.

        
Мета: Оволодіння навичками управління введенням/виведенням даних з використанням класів платформи Java SE.

     
1 ВИМОГИ :
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

 1.2 Загальне завдання :

	Забезпечити можливість збереження і відновлення масива об'єктів рішення завдання лабораторної роботи номер 7.
	Забороняється використання стандартного протокола серіалізації.
	Продемонструвати використання моделі Long Term Persistence.
	Забезпечити діалог з користувачем у вигляді простого текстового меню.
	При збереженні та відновленні даних забезпечити діалоговий режим вибору директорії з відображенням вмісту і можливістю переміщення по підкаталогах.


1.3 Індивідуальне завдання 
	5.Довідник покупця
	Торгівельна точка: назва; адреса; телефони (кількість не обмежена); спеціалізація; час роботи (з зазначенням днів тижня).

2. ОПИС ПРОГРАМИ :
	Моя программа створює ієрархію класів, в кожному з них є гетері та сетері, ці класи записуємо у масив та пичатаємо й демонструємо гетр та сетр.

2.1 Засоби ООП:

Java code convention;
JDK;
java.util.Objects;
java.util.Scanner;
java.util.*;
java.io.*;
java.math.BigInteger;
java.util.Arrays;
java.util.List;
java.util.regex.Matcher;
java.util.regex.Pattern;
java.util.stream.Collectors;
java.util.stream.Stream;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder.


2.2 Ієрархія та структура класів : 
	Має тількі один класс.
   
2.3 Важливі фрагменти програми:

public class Main {
    
    public static class WorkTime {
       
        private String workTime;

        
        boolean isVariableTime(String time) {
            Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            Matcher m = p.matcher(time);
            return m.matches();
        }

        
        public WorkTime(){

        }

        
        public WorkTime(String startDay, String endDay, String startTime, String endTime) {
            
            if (!isVariableTime(startTime) || !isVariableTime(endTime)) {
                throw new IllegalArgumentException("Enter current time!!!");
            }
           
            this.workTime = String.format("Work day: %s - %s; Time: %s - %s", startDay, endDay, startTime, endTime);
        }

        
        public String getWorkTime() {
            return workTime;
        }

        
        public void setWorkTime(String startDay, String endDay, String startTime, String endTime) {
            if (isVariableTime(startTime) || isVariableTime(endTime)) {
                throw new IllegalArgumentException("Enter current time!!!");
            }
            this.workTime = String.format("Work day: %s - %s; Time: %s - %s", startDay, endDay, startTime, endTime);
        }

        
        @Override
        public String toString() {
            return workTime;
        }

    }

    public static class TradingPoint {

        private String name;
        private String address;

        
        public TradingPoint(){

        }

        public TradingPoint(String name, String address) {
            this.name = name;
            this.address = address;
        }

        @Override
        public String toString() {
            return "TradingPoint{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

    public static class ProgressiveTradingPoint extends TradingPoint {

        private List<String> phones;
        private String specialization;
        private WorkTime workTime;

        private boolean isValidPhoneNumber(List<String> t) {
            for (String i : t) {
                if (i.length() != 9) {
                    return false;
                }
            }
            return true;
        }

        public ProgressiveTradingPoint(){

        }

        public ProgressiveTradingPoint(String name, String address, List<String> phones, String specialization, WorkTime workTime) {
            constructor(TradingPoint)
            super(name, address);
           
            if (!isValidPhoneNumber(phones)) {
                throw new IllegalArgumentException("Enter correct phone numbers!!!");
            }
            this.phones = phones;
            this.specialization = specialization;
            this.workTime = workTime;
        }

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

        TradingPoint[] tradingPoints = new TradingPoint[]{ptp, tp, ptp1};

        System.out.println("Enter a number to select an action");

        
        System.out.println("1 - print arr");
        System.out.println("2 - print arr elem");
        System.out.println("3 - write arr");
        System.out.println("4 - read arr");
        System.out.println("5 - end");


        Scanner myInput1 = new Scanner(System.in);

        boolean isEnd = true;

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            
            switch (numberImp) {
                case ("1"):
                    
                    Arrays.stream(tradingPoints).forEach(System.out::println);
                    break;
                case ("2"):
                    
                    System.out.println("Enter index of element\n");
                    Scanner myInput2 = new Scanner(System.in);
                    String numberImp1 = myInput2.nextLine();
                    int num = Integer.parseInt(numberImp1);
                    
                    if (num > tradingPoints.length){
                        System.out.println("Enter correct value!!!");
                        break;
                    }
                    System.out.println(tradingPoints[num]);
                    break;
                case ("3"):
                    
                    try {
                        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("safe.xml"));
                        XMLEncoder encoder = new XMLEncoder(fos);
                       
                        encoder.writeObject(tradingPoints);
                        encoder.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("4"):
                    
                    try {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("safe.xml"));
                        XMLDecoder decoder = new XMLDecoder(bis);
                        TradingPoint[] tradingPoint2 = (TradingPoint[]) decoder.readObject();
                        decoder.close();
                        bis.close();
                        Arrays.stream(tradingPoint2).forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ("5"):
                    
                    isEnd = false;
                    System.out.println("end");
                    break;
              
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
            }
        }
    }
}

 
 
3. ВАРІАНТИ ВИКОРИСТАННЯ:
- дебагер lldb;
- консоль;
- дебагер gdb;
 
ВИСНОВОК
При виконанні даної лабораторної роботи було набуто практичного досвіду з оволодіння навичками управління введенням/виведенням даних з використанням класів платформи Java SE.