package kaydashyaroslav10;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
	
    public static class TradingPoint implements Serializable {
        //Торгівельна точка: назва
        private String name;
        //Торгівельна точка: адреса
        private String address;
        //Торгівельна точка: телефони
        private List<String> phones;
        //Торгівельна точка: спеціалізація
        private String specialization;
        //Торгівельна точка: початок роботи
        private LocalDateTime startTime;
        //Торгівельна точка: кінець роботи
        private LocalDateTime endTime;


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

        public List getPhones() {
            return phones;
        }

        public void setPhones(List<String> phones) {
            this.phones = phones;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }



        @Override
        public String toString() {
            return "TradingPoint{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phones=" + phones +
                    ", specialization='" + specialization + '\'' +
                    ", work time= " + startTime.getDayOfWeek() + " - " + endTime.getDayOfWeek() +
                    startTime.format(DateTimeFormatter.ofPattern(" HH:mm ")) + "-" +
                    endTime.format(DateTimeFormatter.ofPattern(" HH:mm "))  + '}';
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public TradingPoint(String name, String address, List<String> phones, String specialization, LocalDateTime startTime, LocalDateTime endTime) {
            this.name = name;
            this.address = address;
            this.phones = phones;
            this.specialization = specialization;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public TradingPoint() {
            this.name = "no name";
            this.address = "no address";
            this.phones = List.of("no phones");
            this.specialization = "no specialization";
            this.startTime = LocalDateTime.now();
            this.endTime = LocalDateTime.now();
        }
    }




    public static class LinkedListContainer<T> implements Iterable<T>, Serializable {
        //head нужен для реализации LinkedList  - ето список
        private Node<T> head;
        //размер списка
        private int size;

        //конструктор без параметров нужен для реализации сохранения колекции без сериализации
        public LinkedListContainer() {
            head = null;
            size = 0;
        }

        //геттер нужен для реализации сохранения колекции без сериализации
        public Node<T> getHead() {
            return head;
        }

        //сеттер нужен для реализации сохранения колекции без сериализации
        public void setHead(Node<T> head) {
            this.head = head;
        }

        //что то типа реализация связанного списка
        public static class Node<T> implements Serializable {
            private T data;
            private Node<T> next;

            public Node(T data) {
                this.data = data;
                next = null;
            }

            public Node() {

            }
        }

        //возрашает количество елементво в списке
        public int size() {
            return size;
        }

        //проверяет на наличие елементво в списке
        public boolean isEmpty() {
            return size == 0;
        }

        //проверяет на наличие конкретного елемента в списке (если такой есть возрошает true)
        public boolean contains(Object o) {
            for (T item : this) {
                if (item.equals(o)) {
                    return true;
                }
            }
            return false;
        }

        //возрашаем итератор
        public Iterator<T> iterator() {
            return new LinkedListIterator();
        }

        //реализуем итератор
        class LinkedListIterator implements Iterator<T> {
            //список для итерации
            private Node<T> current;

            //конструктор которий присваивает значение current из твоему списку
            public LinkedListIterator() {
                current = head;
            }
            //проверяет на наличие следуюшего елемента

            @Override
            public boolean hasNext() {
                return current != null;
            }

            //возрашает следуюший елемент
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }

            //просто заглушка можно реализовать но не обезательно
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        //преврашает твой список в масив твоей колекции
        public TradingPoint[] toArray() {
            TradingPoint[] arr = new TradingPoint[size];
            int i = 0;
            for (T item : this) {
                arr[i++] = (TradingPoint) item;
            }
            return arr;
        }

        //добавляем елемент в список (тут примерно показа реализация связанного списка)
        public boolean add(T item) {
            Node<T> newNode = new Node<>(item);
            if (head == null) {
                head = newNode;
            } else {
                Node<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
            return true;
        }

        //удаляет елемент в список (тут примерно показа реализация связанного списка)
        public boolean remove(Object o) {
            if (head == null) {
                return false;
            }
            if (head.data.equals(o)) {
                head = head.next;
                size--;
                return true;
            }
            Node<T> current = head;
            while (current.next != null && !current.next.data.equals(o)) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
                size--;
                return true;
            }
            return false;
        }

        //чистит список
        public void clear() {
            head = null;
            size = 0;
        }

        //сохраняет список использую сериализацию(Serialisation)
        public void saveToFile(String fileName) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //возрашает список с файла под именем (которое ти напишеш в методе)
        public static LinkedListContainer<TradingPoint> loadFromFile(String fileName) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                return (LinkedListContainer<TradingPoint>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        //сохраняет список НЕ ИСПОЛЬЗУЯ сериализацию
        public void saveToFileXml() {
            try {
                //create several objects for correct writing to the file
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("safe.xml"));
                XMLEncoder encoder = new XMLEncoder(fos);
                //writeObject - a method that writes an object to an XML file
                encoder.writeObject(this);
                encoder.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //возрашает список с файла под именем (safe.xml)  НЕ ИСПОЛЬЗУЯ сериализацию
        public static LinkedListContainer<TradingPoint> loadFromFileXml() {
            try {
                //create several objects for the correct taking from the file
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("safe.xml"));
                XMLDecoder decoder = new XMLDecoder(bis);
                //drag the object from the file
                LinkedListContainer<TradingPoint> appleCollection = (LinkedListContainer<TradingPoint>) decoder.readObject();
                decoder.close();
                bis.close();
                //print object
                return appleCollection;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //Comparator используется в методе Arrays.sort()
        public void sortByTradingPoint(Comparator<TradingPoint> comparator) {
            // Convert the linked list to an array, sort the array using the comparator, then convert back to a linked list
            TradingPoint[] arr = this.toArray();
            //вот наш компоратор для каждого етого метода пишется свой компоратор
            Arrays.sort(arr, comparator);
            clear();
            for (TradingPoint tp : arr) {
                add((T) tp);
            }
        }
    }
    //создаем екзампляри для заполнение нашего list
    static TradingPoint point1 = new TradingPoint("some name", "some address", List.of("0000"), "some thing",
            LocalDateTime.of(2004, 1, 1, 8, 30),
            LocalDateTime.of(2010, 2, 5, 19, 30));
    static TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("123", "321"), "cheese",
            LocalDateTime.of(2002, 3, 1, 10, 30),
            LocalDateTime.of(2003, 4, 5, 18, 0));
    static TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("999"), "apples",
            LocalDateTime.of(2000, 5, 1, 6, 30),
            LocalDateTime.of(2022, 6, 5, 10, 45));

    //метод для реализации автоматичного режима
    private static void automatic(LinkedListContainer<TradingPoint> listContainer){

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }
        //пишем компоратор для сортировки и так в каждом сортировачном методе
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

        listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
            @Override
            public int compare(TradingPoint tp1, TradingPoint tp2) {
                return tp1.getAddress().compareTo(tp2.getAddress());
            }
        });

        System.out.println("After sorting by address");

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

        listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
            @Override
            public int compare(TradingPoint tp1, TradingPoint tp2) {
                return tp1.getSpecialization().compareTo(tp2.getSpecialization());
            }
        });

        System.out.println("After sorting by specialization");

        for (TradingPoint tradingPoint : listContainer) {
            System.out.println(tradingPoint);
        }

    }
    public static void main(String[] args) {
        LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

        listContainer.add(point1);
        listContainer.add(point2);
        listContainer.add(point3);

        // end flag
        boolean isEnd = true;

        //проверка на наличее елемента -auto в args[] - args хранит такие переменние которие ти вводиш
        if (args.length > 0){
            //проверяем на наличие конкретного параметро под именем -auto
            if (Objects.equals(args[0], "-auto")){
                automatic(listContainer);
                isEnd = false;
            }
        }

        if (isEnd) {
            System.out.println("1 - print list");
            System.out.println("2 - sort list by name");
            System.out.println("3 - sort list by address");
            System.out.println("4 - sort list by specialization");
            System.out.println("5 - end");
        }


        //first input
        Scanner myInput1 = new Scanner(System.in);

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            ///switch use first input as parameter
            //use first input parameter
            switch (numberImp) {
                case ("1"):
                    //print list
                    for (TradingPoint tradingPoint : listContainer) {
                        System.out.println(tradingPoint);
                    }
                    break;
                case ("2"):
                    //sort list by name
                    listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
                        @Override
                        public int compare(TradingPoint tp1, TradingPoint tp2) {
                            return tp1.getName().compareTo(tp2.getName());
                        }
                    });
                    break;
                case ("3"):
                    //sort list by address
                    listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
                        @Override
                        public int compare(TradingPoint tp1, TradingPoint tp2) {
                            return tp1.getAddress().compareTo(tp2.getAddress());
                        }
                    });
                    break;
                case ("4"):
                    //sort list by address
                    listContainer.sortByTradingPoint(new Comparator<TradingPoint>() {
                        @Override
                        public int compare(TradingPoint tp1, TradingPoint tp2) {
                            return tp1.getSpecialization().compareTo(tp2.getSpecialization());
                        }
                    });
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