Лабораторна робота № 15. Коллекції в Java.
Мета: Ознайомлення з бібліотекою колекцій Java SE. Використання колекцій для розміщення об'єктів розроблених класів.

1 ВИМОГИ 
1.1 Розробник :
	-  Кайдаш Ярослав; 
	-  студент групи КН-921Д; 
	-  19-05-2023. 

 1.2 Загальне завдання :
	1)Розробити консольну програму для реалізації завдання обробки даних згідно прикладної області.
	2)Для розміщення та обробки даних використовувати контейнери (колекції) і алгоритми з Java Collections Framework.
	3)Забезпечити обробку колекції об'єктів: додавання, видалення, пошук, сортування згідно розділу Прикладної задачі номер 10.
	4)Передбачити можливість довготривалого зберігання даних: 1.за допомогою стандартної серіалізації; 
	2.не використовуючи протокол серіалізації.
	5)Продемонструвати розроблену функціональність в діалоговому та автоматичному режимах за результатом обробки параметрів командного рядка.


1.3 Індивідуальне завдання :
	Довідник покупця. Знайти всі торгові точки, що працюють без вихідних та перерви на обід, що надають для консультації короткий телефонний номер та мають контактні телефони українських операторів мобільного зв'язку.
	
2 ОПИС ПРОГРАМИ 
	Програма функціонує в діалоговому та автоматичному режимах, може додавати, видаляти, шукати, сортувати згідно розділу Прикладні задачі л.р. №10; зберігати данні за допомогою стандартної серіалізації та не використовуючи протокол серіалізації.

2.1 Засоби ООП:
	import java.beans.XMLDecoder;
	import java.beans.XMLEncoder;
	import java.io.*;
	import java.util.Arrays;
	import java.util.Comparator;
	import java.util.Iterator;
	import java.beans.XMLDecoder;
	import java.beans.XMLEncoder;
	import java.io.*;
	import java.time.LocalDateTime;
	import java.util.*;
	import java.util.regex.Pattern;
	import java.io.Serializable;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.ArrayList;
	import java.util.List;


2.2 Ієрархія та структура класів  :

	Має тількі один класс.
   
2.3 Важливі фрагменти програми:

public class Main {

    private static void automatic(){
        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);
        LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

        listContainer.add(point1);
        listContainer.add(point2);
        listContainer.add(point3);

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        listContainer.remove(point2);

        System.out.println("After removing point2 from collection");

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        System.out.println("Doest point1 contains in collection: " + listContainer.contains(point1));
        System.out.println("Doest point2 contains in collection: " + listContainer.contains(point2));
        
        listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
            @Override
            public int compare(TradingPoint tp1, TradingPoint tp2) {
                return tp1.getName().compareTo(tp2.getName());
            }
        });

        System.out.println("After sorting by name");

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        System.out.println("After NOT serializable saving");

        listContainer.saveToFileXml();
        LinkedListContainer<TradingPoint> tradingPoints1 = listContainer.loadFromFileXml();

        for (TradingPoint tradingPoint : tradingPoints1) {
            System.out.println(tradingPoint);
        }

        System.out.println("After serializable saving");

        listContainer.saveToFile("safe.bin");
        LinkedListContainer<TradingPoint> tradingPoints2 = listContainer.loadFromFile("safe.bin");

        for (TradingPoint tradingPoint : tradingPoints2) {
            System.out.println(tradingPoint);
        }

    }

    public static boolean hardWorkCheck(TradingPoint tradingPoint) {
     
        return (tradingPoint.getStartTime().getDayOfWeek().equals(tradingPoint.getEndTime().getDayOfWeek()))
                && tradingPoint.getLunchBreak() == 0;
    }

    public static boolean haveShortPhoneNumber(TradingPoint tradingPoint) {
        String regex = "^\\d{4}$"; 

        Pattern pattern = Pattern.compile(regex);
    
        return tradingPoint.getPhones().stream().anyMatch(s -> pattern.matcher(s).matches());
    }

    public static boolean checkUkrainianPhoneNumber(String phone) {
     
        String regex = "^(050|063|066|067|068|093|095|096|097|098|099)\\d{7}$";
        return phone.matches(regex);
    }

    public static boolean haveUkrainianPhoneNumber(TradingPoint tradingPoint) {
    
        return tradingPoint.getPhones().stream().anyMatch(Main::checkUkrainianPhoneNumber);
    }
    public static void main(String[] args) {
        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);

        LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

        listContainer.add(point2);
        listContainer.add(point3);

        boolean isEnd = true;

        if (args.length > 0) {
            if (Objects.equals(args[0], "-auto")) {
                automatic();
                isEnd = false;
            }
        }

        if (isEnd) {
            System.out.println("1 - print list");
            System.out.println("2 - add elem to list");
            System.out.println("3 - delete elem to list");
            System.out.println("4 - check if elem contains in list");
            System.out.println("5 - sort list by name");
            System.out.println("6 - NOT serializable saving");
            System.out.println("7 - serializable saving");
            System.out.println("8 - end");
        }

        Scanner myInput1 = new Scanner(System.in);

        while (isEnd) {
            String numberImp = myInput1.nextLine();

            switch (numberImp) {
                case ("1"):
                    for (TradingPoint tradingPoint : listContainer) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("2"):
                    listContainer.add(point1);
                    break;
                case ("3"):
                    listContainer.remove(point1);
                    break;
                case ("4"):
                    System.out.println("Doest point1 contains in collection: " + listContainer.contains(point1));
                    break;
                case ("5"):
                    listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
                        @Override
                        public int compare(TradingPoint tp1, TradingPoint tp2) {
                            return tp1.getName().compareTo(tp2.getName());
                        }
                    });
                    break;
                case ("6"):
                    System.out.println("After NOT serializable saving");
                    listContainer.saveToFileXml();
                    LinkedListContainer<TradingPoint> tradingPoints1 = listContainer.loadFromFileXml();
                    for (TradingPoint tradingPoint : tradingPoints1) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("7"):
                    System.out.println("After serializable saving");
                    listContainer.saveToFile("safe.bin");
                    LinkedListContainer<TradingPoint> tradingPoints2 = listContainer.loadFromFile("safe.bin");
                    for (TradingPoint tradingPoint : tradingPoints2) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("8"):
                    isEnd = false;
                    System.out.println("end");
                    break;
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
            }
        }
    }
}


3 ВАРІАНТИ ВИКОРИСТАННЯ:
	- дебагер lldb;
	- консоль;
	- дебагер gdb;
 
 
ВИСНОВОК:
	При виконанні даної лабораторної роботи було набуто практичного досвіду з вимірюванням часу паралельних та послідовних обчислень та демонстрацією ефективності паралельної обробки.