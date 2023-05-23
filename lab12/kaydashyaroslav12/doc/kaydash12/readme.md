Лабораторна робота № 12. Регулярні вирази. Обробка тексту.        
Мета: Ознайомлення з принципами використання регулярних виразів для обробки тексту.

1 ВИМОГИ :
1.1 Розробник :

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  3-05-2023. 

 1.2 Загальне завдання :
	Використовуючи програми рішень попередніх задач, продемонструвати ефективне (оптимальне) використання регулярних виразів при вирішенні прикладної задачі.
	Передбачити можливість незначної зміни умов пошуку.
	Продемонструвати розроблену функціональність в діалоговому та автоматичному режимах.


1.3 Індивідуальне завдання :
	Довідник покупця. Знайти всі торгові точки, що працюють без вихідних та перерви на обід, що надають для консультації короткий телефонний номер та мають контактні телефони українських операторів мобільного зв'язку.
	
2 ОПИС ПРОГРАМИ: 
	Программа перевіряє мою колекцію на такі фактори як:
	- чи є у колекції вихідні та вільний час;
	- чи є у колекції телефони з Укр. номерами;
	- чи є у колекції короткий номер; 
	Все це программа робить за допомогою - RegEx

2.1 Засоби ООП:
	import java.beans.XMLDecoder;
	import java.beans.XMLEncoder;
	import java.io.*;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.*;
	import java.util.regex.Pattern;

2.2 Ієрархія та структура класів  :
	Має тількі один класс.
   
2.3 Важливі фрагменти програми:

public class Main {
    
    public static class TradingPoint implements Serializable {
        
        private String name;
     
        private String address;
     
        private List<String> phones;
       
        private String specialization;
      
        private LocalDateTime startTime;
       
        private LocalDateTime endTime;
      
        private int lunchBreak;

        public int getLunchBreak() {
            return lunchBreak;
        }

        public void setLunchBreak(int lunchBreak) {
            this.lunchBreak = lunchBreak;
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

        public List<String> getPhones() {
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
                    endTime.format(DateTimeFormatter.ofPattern(" HH:mm ")) +
                    ", lunch break= " + lunchBreak + " minutes"
                    + '}';
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

        public boolean validate() {
            
            if (!name.matches("[A-Za-z\\s-]+")) {
                return false;
            }

            if (!address.matches("[A-Za-z0-9\\s,]+")) {
                return false;
            }

            for (String phone : phones) {
                if (!phone.matches("\\d{10}|\\d{3}-\\d{3}-\\d{4}|\\d{4}")) {
                    return false;
                }
            }

            if (!specialization.matches("[A-Za-z\\s]+")) {
                return false;
            }

            return true;
        }

        public TradingPoint(String name, String address, List<String> phones,
                            String specialization, LocalDateTime startTime,
                            LocalDateTime endTime, int lunchBreak) {
            this.name = name;
            this.address = address;
            this.phones = phones;
            this.specialization = specialization;
            this.startTime = startTime;
            this.endTime = endTime;
            this.lunchBreak = lunchBreak;
        }

        public TradingPoint() {
            this.name = "no name";
            this.address = "no address";
            this.phones = List.of("no phones");
            this.specialization = "no specialization";
            this.startTime = LocalDateTime.now();
            this.endTime = LocalDateTime.now();
            this.lunchBreak = 0;
        }
    }


    public static class LinkedListContainer<T> implements Iterable<T>, Serializable {
    
        private Node<T> head;
        //размер списка
        private int size;

        public LinkedListContainer() {
            head = null;
            size = 0;
        }

        public Node<T> getHead() {
            return head;
        }

        public void setHead(Node<T> head) {
            this.head = head;
        }

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

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean contains(Object o) {
            for (T item : this) {
                if (item.equals(o)) {
                    return true;
                }
            }
            return false;
        }

        public Iterator<T> iterator() {
            return new LinkedListIterator();
        }

        class LinkedListIterator implements Iterator<T> {
          
            private Node<T> current;

            public LinkedListIterator() {
                current = head;
            }
      

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public TradingPoint[] toArray() {
            TradingPoint[] arr = new TradingPoint[size];
            int i = 0;
            for (T item : this) {
                arr[i++] = (TradingPoint) item;
            }
            return arr;
        }

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

        public void clear() {
            head = null;
            size = 0;
        }

        public void saveToFile(String fileName) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static LinkedListContainer<TradingPoint> loadFromFile(String fileName) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                return (LinkedListContainer<TradingPoint>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void saveToFileXml() {
            try {
                BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("safe.xml"));
                XMLEncoder encoder = new XMLEncoder(fos);
          encoder.writeObject(this);
                encoder.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static LinkedListContainer<TradingPoint> loadFromFileXml() {
            try {
                 BufferedInputStream bis = new BufferedInputStream(new FileInputStream("safe.xml"));
                XMLDecoder decoder = new XMLDecoder(bis);
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

        public void sortByTradingPoint(Comparator<TradingPoint> comparator) {
           
            TradingPoint[] arr = this.toArray();
             sortByTradingPointName/sortByTradingPointAddress
            Arrays.sort(arr, comparator);
            clear();
            for (TradingPoint tp : arr) {
                add((T) tp);
            }
        }
    }

    static TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
            LocalDateTime.of(2004, 1, 1, 8, 30),
            LocalDateTime.of(2004, 1, 1, 19, 30), 0);
    static TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
            LocalDateTime.of(2002, 3, 1, 10, 30),
            LocalDateTime.of(2003, 4, 5, 18, 0), 45);
    static TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
            LocalDateTime.of(2000, 5, 1, 6, 30),
            LocalDateTime.of(2022, 6, 5, 10, 45), 60);

    private static void automatic(LinkedListContainer<TradingPoint> listContainer) {
       
        for (TradingPoint tradingPoint : listContainer) {
            if (hardWorkCheck(tradingPoint)) {
                System.out.println("Hard work time: " + tradingPoint);
            }
        }
       
        for (TradingPoint tradingPoint : listContainer) {
            if (haveShortPhoneNumber(tradingPoint)) {
                System.out.println("Short phone number: " + tradingPoint);
            }
        }
       
        for (TradingPoint tradingPoint : listContainer) {
            if (haveUkrainianPhoneNumber(tradingPoint)) {
                System.out.println("Ukrainian phone number: " + tradingPoint);
            }
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
        LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

        listContainer.add(point1);
        listContainer.add(point2);
        listContainer.add(point3);

        boolean isEnd = true;

        if (args.length > 0) {
       
            if (Objects.equals(args[0], "-auto")) {
                automatic(listContainer);
                isEnd = false;
            }
        }

        if (isEnd) {
            System.out.println("1 - print list");
            System.out.println("2 - print all trading points that have hard work time");
            System.out.println("3 - print all trading points that have short phone number");
            System.out.println("4 - print all trading points that have ukrainian phone number");
            System.out.println("5 - end");
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
               
                    for (TradingPoint tradingPoint : listContainer) {
                        if (hardWorkCheck(tradingPoint)) {
                            System.out.println("Hard work time: " + tradingPoint);
                        }
                    }
                    break;
                case ("3"):
                 
                    for (TradingPoint tradingPoint : listContainer) {
                        if (haveShortPhoneNumber(tradingPoint)) {
                            System.out.println("Short phone number: " + tradingPoint);
                        }
                    }
                    break;
                case ("4"):
  
                    for (TradingPoint tradingPoint : listContainer) {
                        if (haveUkrainianPhoneNumber(tradingPoint)) {
                            System.out.println("Ukrainian phone number: " + tradingPoint);
                        }
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

 
3 ВАРІАНТИ ВИКОРИСТАННЯ:
	- дебагер lldb;
	- консоль;
	- дебагер gdb;
 
 
ВИСНОВОК:
	При виконанні даної лабораторної роботи було набуто практичного досвіду з принципами використання регулярних виразів для обробки тексту.