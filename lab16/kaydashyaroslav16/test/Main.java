package kaydashyaroslav16;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public static int laba13(LinkedListContainer<TradingPoint> listContainer) {
        int counter = 0; // Initialize counter to 0
        for (TradingPoint tp : listContainer) {
            List<String> phones = tp.getPhones();
            long ukrainianPhoneCount = phones.stream().filter(Main::checkUkrainianPhoneNumber).count();
            counter += ukrainianPhoneCount;
        }
        return counter;
}

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

        //заполняем колекцию обектами
        listContainer.add(point1);
        listContainer.add(point2);
        listContainer.add(point3);

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        //удаляем один из обектов
        listContainer.remove(point2);

        System.out.println("After removing point2 from collection");

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        System.out.println("Doest point1 contains in collection: " + listContainer.contains(point1));
        System.out.println("Doest point2 contains in collection: " + listContainer.contains(point2));

        //пишем свой компоратор для сортировки и так в каждом сортировачном методе
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
        //сначала проверяем на наличие виходних
        return (tradingPoint.getStartTime().getDayOfWeek().equals(tradingPoint.getEndTime().getDayOfWeek()))
                //теперь проверяем на наличие отдиха
                && tradingPoint.getLunchBreak() == 0;
    }

    public static boolean haveShortPhoneNumber(TradingPoint tradingPoint) {
        String regex = "^\\d{4}$"; // Регулярний вираз для пошуку рядків довжиною 4, що містять лише цифри
        Pattern pattern = Pattern.compile(regex);
        //берем из tradingPoint список телефоном и проходимся по каждому елементу используя java8(stream) и
        //используем метод anyMatch которий возрошаем значение true есл хотя би один елемент будет true
        return tradingPoint.getPhones().stream().anyMatch(s -> pattern.matcher(s).matches());
    }

    public static boolean checkUkrainianPhoneNumber(String phone) {
        //список контактних телефонов українських операторів мобільного зв'язку.
        String regex = "^(050|063|066|067|068|093|095|096|097|098|099)\\d{7}$";
        return phone.matches(regex);
    }

    public static boolean haveUkrainianPhoneNumber(TradingPoint tradingPoint) {
        //берем из tradingPoint список телефоном и проходимся по каждому елементу используя java8(stream) и
        //используем метод anyMatch которий возрошаем значение true есл хотя би один елемент будет true
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
        // end flag
        boolean isEnd = true;

        //проверка на наличее елемента -auto в args[] - args хранит такие переменние которие ти вводиш
        // пример запуска:java Main.java -auto /ето запуск с параметром
        // пример запуска:java Main.java  /ето запуск с БЕЗ параметров
        if (args.length > 0) {
            //проверяем на наличие конкретного параметро под именем -auto
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

        //first input
        Scanner myInput1 = new Scanner(System.in);

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            ///switch use first input as parameter
            //use first input parameter
            switch (numberImp) {
                case ("1"):
                    //печатаем list
                    for (TradingPoint tradingPoint : listContainer) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("2"):
                    //заполняем колекцию обектами
                    listContainer.add(point1);
                    break;
                case ("3"):
                    //удаляем один из обектов
                    listContainer.remove(point1);
                    break;
                case ("4"):
                    //поиск елемента в колекции
                    System.out.println("Doest point1 contains in collection: " + listContainer.contains(point1));
                    break;
                case ("5"):
                    //пишем свой компоратор для сортировки и так в каждом сортировачном методе
                    listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
                        @Override
                        public int compare(TradingPoint tp1, TradingPoint tp2) {
                            return tp1.getName().compareTo(tp2.getName());
                        }
                    });
                    break;
                case ("6"):
                    System.out.println("After NOT serializable saving");
                    //сохраням колекцию в xml файл
                    listContainer.saveToFileXml();
                    //витаскиваем с xml файла
                    LinkedListContainer<TradingPoint> tradingPoints1 = listContainer.loadFromFileXml();
                    //печатаем доставшую колекцию
                    for (TradingPoint tradingPoint : tradingPoints1) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("7"):
                    System.out.println("After serializable saving");
                    //сохраням колекцию в файл
                    listContainer.saveToFile("safe.bin");
                    //витаскиваем с файла
                    LinkedListContainer<TradingPoint> tradingPoints2 = listContainer.loadFromFile("safe.bin");
                    //печатаем доставшую колекцию
                    for (TradingPoint tradingPoint : tradingPoints2) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("8"):
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