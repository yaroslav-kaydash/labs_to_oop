Лабораторна робота № 7.Об'єктно-орієнтована декомпозиція.

        
Мета: Використання об'єктно-орієнтованого підходу для розробки об'єкта предметної (прикладної) галузі.

1 ВИМОГИ :
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

1.2 Загальне завдання :

	Використовуючи об'єктно-орієнтований аналіз, реалізувати класи для представлення сутностей відповідно прикладної задачі - domain-об'єктів.
	Забезпечити та продемонструвати коректне введення та відображення кирилиці.
	Продемонструвати можливість управління масивом domain-об'єктів.

1.3 Індивідуальне завдання: 

	5.Довідник покупця
	Торгівельна точка: назва; адреса; телефони (кількість не обмежена); спеціалізація; час роботи (з зазначенням днів тижня).

2.ОПИС ПРОГРАМИ :
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
	java.util.stream.Stream.


2.2 Ієрархія та структура класів:  
	Має тількі один класс.
   
2.3 Важливі фрагменти програми:

public class Main {

    static class WorkTime {
        String workTime;

        boolean isVariableTime(String time) {
            Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
            Matcher m = p.matcher(time);
            return m.matches();
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

    static class TradingPoint {

    String name;
    String address;

    
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

static class ProgressiveTradingPoint extends TradingPoint {

    List<BigInteger>phones;
    String specialization;
    WorkTime workTime;

    
    private boolean isValidPhoneNumber(List<BigInteger> t) {
        for (BigInteger i : t) {
            if (i.toString().length() != 9) {
                return false;
            }
        }
        return true;
    }

    
    public ProgressiveTradingPoint(String name, String address, List<BigInteger> phones, String specialization, WorkTime workTime) {
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

                
                TradingPoint[] tradingPoints = new TradingPoint[]{ptp, tp, ptp1};

                
                Arrays.stream(tradingPoints).forEach(System.out::println);

                
                System.out.println(ptp.getAddress());
               
                ptp.setAddress("new address");
                
                System.out.println(ptp.getAddress());
            }
        }
 
 
3. ВАРІАНТИ ВИКОРИСТАННЯ:
- дебагер lldb;
- консоль;
- дебагер gdb;
 
 
 
ВИСНОВОК
При виконанні даної лабораторної роботи було набуто практичного досвіду з навичок використання об'єктно-орієнтованого підходу для розробки об'єкта предметної (прикладної) галузі.