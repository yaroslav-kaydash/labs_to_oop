Лабораторна робота № 6.Серіалізація/десеріалізація об'єктів.Бібліотека класів користувача.

        
Мета: Тривале зберігання та відновлення стану об'єктів; ознайомлення з принципами серіалізації/десеріалізації об'єктів;
використання бібліотек класів користувача.

1 ВИМОГИ :
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

1.2 Загальне завдання :

	Реалізувати і продемонструвати тривале зберігання/відновлення раніше розробленого контейнера за допомогою серіалізації/десеріалізації.

	Обмінятися відкомпільованим (без початкового коду) службовим класом (Utility Class) рішення задачі л.р номер 3 з іншим студентом (визначає викладач).

	Продемонструвати послідовну та вибіркову обробку елементів розробленого контейнера за допомогою власного і отриманого за обміном службового класу.

	Реалізувати та продемонструвати порівняння, сортування та пошук елементів у контейнері.

	Розробити консольну програму та забезпечити діалоговий режим роботи з користувачем для демонстрації та тестування рішення.

1.3 Індивідуальне завдання 
-

2 ОПИС ПРОГРАМИ :
	Моя программа демонструє користувачу методи моєї коллекції.
	Інша программа упорядковує рядки, а потім виводить їх за алфавітом (перший пріоритет) та в порядку зростання їх довжини (другий пріоритет).

2.1 Засоби ООП:
Java code convention;
JDK;
java.util.Objects;
java.util.Scanner;
java.util.*;
java.io.*.


2.2 Ієрархія та структура класів  :
class Main;
class Utill.
   
2.3 Важливі фрагменти програми:

//1 - Main

    public static class MyCollection implements Serializable {

        
        String[] arr;
        
        int count = 0;

        
        MyCollection() {
            
            arr = new String[2^32];
        }

        
        MyCollection(String[] s) {
            arr = s;
            count = s.length;
        }

        
        public int size() {
            return count;
        }


        
        public boolean contains(String o) {
            
            String[] copyArr = arr.clone();
            System.arraycopy(copyArr, 0, arr, 0, size());
            
            for (String s : copyArr) {
                if (s != null && s.equals(o)) {
                    return true;
                }
            }
            return false;
        }

        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size(); i++) {
                sb.append(arr[i] + " ");
            }
            return sb.toString();
        }


        public Iterator iterator() {
            return new Iterator() {

                 iteration
                int iterCount = 0;

                 the presence of the following object
                @Override
                public boolean hasNext() {
                    return iterCount < size() && arr[iterCount] != null;
                }

                 next object
                @Override
                public Object next() {
                    return arr[iterCount++];
                }

                
                @Override
                public void remove() {
                    int i = iterCount - 1;
                    String[] copyArr = new String[arr.length - 1];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    arr = copyArr;
                    count--;
                }
            };
        }

        
        public Object[] toArray() {
            String[] copyArr = new String[size()];
            System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
            return copyArr;
        }

        
        public boolean add(Object o) {
            arr[count++] = o.toString();
            int maximum = 2^32;
            return size() < maximum;
        }

        
        public boolean remove(Object o) {
            for (int i = 0; i < size(); i++) {
                
                if (Objects.equals(arr[i], o.toString())) {
                    String[] copyArr = new String[size()];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    
                    arr = copyArr;
                    count--;
                    return true;
                }
            }
            return false;
        }

        
        public void clear() {
            arr = new String[2^32];
            count = 0;
        }

        
        public boolean containsAll(String[] c) {
            int sameCount = 0;
            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < c.length; j++) {
                    if (Objects.equals(arr[i], c[j])) {
                        sameCount++;
                        break;
                    }
                }
            }
            return sameCount == c.length;
        }

       
        public void sort() {
            String temp;
            for (int j = 0; j < size(); j++) {
                for (int i = j + 1; i < size(); i++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
        }

        
        public String compareElem(int firIndex, int secIndex) {
            if (firIndex > size() || secIndex > size() || firIndex < 0 || secIndex < 0) {
                return "Enter correct index!!!";
            }
            String firstEl = arr[firIndex];
            String secondEl = arr[secIndex];
            int res = firstEl.compareTo(secondEl);
            return String.format("Comparing elements: %s and %s; \nresult: %d", firstEl, secondEl, res);
        }

    }

    private static String str = "sad , ,sda , tt,22,!,23a4 3 4 6 ^ a b c a,b,c";

   
    static void removeSigns() {
        
        StringBuilder stringBuilder = new StringBuilder();
         variable we make an array char
        char[] chars = str.toString().toCharArray();

        
        for (int i = 0; i < chars.length; i++) {
            
          Character.isAlphabetic() - returns true if character(char) is a letter
            if (Character.isAlphabetic(chars[i]) || chars[i] == ' ' || chars[i] == ',') {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

    
    static void removeDuplicate() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            if (chars[i] != chars[i + 1] || Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
            }
        }
        str = stringBuilder.toString();
    }

    
    static void depthBeetwen() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                break;
            }
            stringBuilder.append(chars[i]);
           
            if (Character.isAlphabetic(chars[i + 1]) && chars[i] == ',') {
                stringBuilder. append(" ");
            }
        }
        str = stringBuilder.toString();
    }

    
    static void fillString(String s) {
        str = s;
    }

    
    static void doLogic() {
        
        removeSigns();
         
        removeDuplicate();
        
        depthBeetwen();
       
        System.out.println(str);
    }

    
    static void fillCollection(MyCollection myCollection) {
        //make a cycle of 10 actions
        for (int i = 0; i < 10; i++) {
            //create an object of the Scanner class in order to read data from the console
            Scanner myInput = new Scanner(System.in);
            //create a variable that will receive data from the console
            String myStr = myInput.nextLine();
            //assign value to external variable
            fillString(myStr);
            //add values ​​from console to my collection
            myCollection.add(str);
            //execute logic 3 labi
            doLogic();
        }
    }

    
    static MyCollection readObject() {
        try {
            FileInputStream fos = new FileInputStream("safe.bin");
            ObjectInputStream outputStream = new ObjectInputStream(fos);
            MyCollection fileCollection = (MyCollection) outputStream.readObject();
            return fileCollection;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new MyCollection();
    }

    //write to file
    static void writeObject(MyCollection writeCollection) {
        try {
            FileOutputStream fos = new FileOutputStream("safe.bin");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(writeCollection);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        MyCollection myCollection = new MyCollection();

        System.out.println("Enter a number to select an action");

        
        System.out.println("1 - create collection and do logic(lab3)");
        System.out.println("2 - do compare of elements");
        System.out.println("3 - sort collection");
        System.out.println("4 - find element(findingElem)");
        System.out.println("5 - write and read file");
        System.out.println("6 - end");


        
        Scanner myInput1 = new Scanner(System.in);

        
        boolean isEnd = true;
        while (isEnd) {
            String numberImp = myInput1.nextLine();
            
            switch (numberImp) {
                case("1"):
                    
                    fillCollection(myCollection);
                    break;
                case("2"):
                    
                    System.out.println(myCollection.compareElem(1, 3));
                    break;
                case("3"):
                    System.out.println("Collection before sorting: " + myCollection);
                    
                    myCollection.sort();
                    System.out.println("After sorting: " + myCollection);
                    break;
                case("4"):
                    
                    String findingElem = "findingElem";
                    
                    String format = String.format("We search : %s \nDoes we find anything: %b ", findingElem, myCollection.contains(findingElem));
                    System.out.println(format);
                    break;
                case("5"):
                    
                    writeObject(myCollection);
                    MyCollection myCollection1 = readObject();
                    System.out.println(myCollection1);
                    break;
                case("6"):
                    //
                    isEnd=false;
                    System.out.println("end");
                    break;
                
                default:
                    System.out.println("Enter one of the numbers from 1 to 5 for the correct robot");
            }
        }
    }
}
 


//2 - Utill

public static class MyCollection implements Serializable {

        
        String[] arr;
        
        int count = 0;

        
        MyCollection() {
            
            arr = new String[2^32];
        }

       
        MyCollection(String[] s) {
            arr = s;
            count = s.length;
        }

        
        public int size() {
            return count;
        }


        
        public boolean contains(String o) {
            
            String[] copyArr = arr.clone();
            System.arraycopy(copyArr, 0, arr, 0, size());
            
            for (String s : copyArr) {
                if (s != null && s.equals(o)) {
                    return true;
                }
            }
            return false;
        }

        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size(); i++) {
                sb.append(arr[i] + " ");
            }
            return sb.toString();
        }


        public Iterator iterator() {
            return new Iterator() {

                
                int iterCount = 0;

                
                @Override
                public boolean hasNext() {
                    return iterCount < size() && arr[iterCount] != null;
                }

                
                @Override
                public Object next() {
                    return arr[iterCount++];
                }

                
                @Override
                public void remove() {
                    int i = iterCount - 1;
                    String[] copyArr = new String[arr.length - 1];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    arr = copyArr;
                    count--;
                }
            };
        }

        
        public Object[] toArray() {
            String[] copyArr = new String[size()];
            System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
            return copyArr;
        }

        
        public boolean add(Object o) {
            arr[count++] = o.toString();
            int maximum = 2^32;
            return size() < maximum;
        }

        
        public boolean remove(Object o) {
            for (int i = 0; i < size(); i++) {
                
                if (Objects.equals(arr[i], o.toString())) {
                    String[] copyArr = new String[size()];
                    System.arraycopy(arr, 0, copyArr, 0, i);
                    System.arraycopy(arr, i + 1, copyArr, i, copyArr.length - i - 1);
                    
                    arr = copyArr;
                    count--;
                    return true;
                }
            }
            return false;
        }

        
        public void clear() {
            arr = new String[2^32];
            count = 0;
        }

        
        public boolean containsAll(String[] c) {
            int sameCount = 0;
            for (int i = 0; i < size(); i++) {
                for (int j = 0; j < c.length; j++) {
                    if (Objects.equals(arr[i], c[j])) {
                        sameCount++;
                        break;
                    }
                }
            }
            return sameCount == c.length;
        }

        
        public void sort() {
            String temp;
            for (int j = 0; j < size(); j++) {
                for (int i = j + 1; i < size(); i++) {
                    if (arr[i].compareTo(arr[j]) < 0) {
                        temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
        }

        
        public String compareElem(int firIndex, int secIndex) {
            if (firIndex > size() || secIndex > size() || firIndex < 0 || secIndex < 0) {
                return "Enter correct index!!!";
            }
            String firstEl = arr[firIndex];
            String secondEl = arr[secIndex];
            int res = firstEl.compareTo(secondEl);
            return String.format("Comparing elements: %s and %s; \nresult: %d", firstEl, secondEl, res);
        }

    }

    private static Scanner scanner;
    private static String textTwo;
    private static String textOne;

    
    static void sortText(String textOne, String textTwo) {
        System.out.print("\n");
        sortAplphabet(textOne, textTwo);
        count(textOne, textTwo);
    }

    
    static void sortAplphabet(String textOne, String textTwo) {
        String letterOne = Character.toString(textOne.charAt(0));
        String letterTwo = Character.toString(textTwo.charAt(0));

        ArrayList<String> abc = new ArrayList<String>();
        abc.add(letterOne);
        abc.add(letterTwo);

        Collections.sort(abc);
        Iterator<String> itr = abc.iterator();

        if (itr.next() == letterOne) {
            System.out.println("First letter is: " + letterOne);
            System.out.println(textOne);
            System.out.println("Second letter is: " + letterTwo);
            System.out.println(textTwo);
        } else {
            System.out.println("Second letter is: " + letterTwo);
            System.out.println(textTwo);
            System.out.println("First letter is: " + letterOne);
            System.out.println(textOne);
        }
        System.out.println("\n");

    }

    
    static void count(String textOne, String textTwo) {

        int lengthOne = textOne.length();
        int lengthTwo = textTwo.length();

        if (lengthOne > lengthTwo) {
            System.out.println("Count length text One: " + textOne.length());
            System.out.println(textOne);
            System.out.println("Count length text Two: " + textTwo.length());
            System.out.println(textTwo);

        } else {
            System.out.println("Count length text Two: " + textTwo.length());
            System.out.println(textTwo);
            System.out.println("Count length text One: " + textOne.length());
            System.out.println(textOne);
        }

    }

    
    static MyCollection readObject() {
        try {
            FileInputStream fos = new FileInputStream("safe.bin");
            ObjectInputStream outputStream = new ObjectInputStream(fos);
            MyCollection fileCollection = (MyCollection) outputStream.readObject();
            return fileCollection;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new MyCollection();
    }

    
    static void writeObject(MyCollection writeCollection) {
        try {
            FileOutputStream fos = new FileOutputStream("safe.bin");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(writeCollection);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        System.out.println("1 line: ");
        textOne = scanner.nextLine();
        System.out.println("2 line: ");
        textTwo = scanner.nextLine();

        sortText(textOne, textTwo);

        
        MyCollection myCollection = new MyCollection();

        System.out.println("Enter a number to select an action");

        
        System.out.println("1 - create collection and do logic(lab3)");
        System.out.println("2 - do compare of elements");
        System.out.println("3 - sort collection");
        System.out.println("4 - find element(findingElem)");
        System.out.println("5 - write and read file");
        System.out.println("6 - end");


        
        Scanner myInput1 = new Scanner(System.in);

        
        boolean isEnd = true;

        while (isEnd) {
            String numberImp = myInput1.nextLine();
            
            switch (numberImp) {
                case("1"):
                    
                    myCollection = new MyCollection(new String[]{textOne, textTwo});
                    break;
                case("2"):
                     System.out.println(myCollection.compareElem(0, 1));
                    break;
                case("3"):
                    System.out.println("Collection before sorting: " + myCollection);
                    
                    myCollection.sort();
                    System.out.println("After sorting: " + myCollection);
                    break;
                case("4"):
                    
                    String findingElem = "findingElem";
                    
                    String format = String.format("We search : %s \nDoes we find anything: %b ", findingElem, myCollection.contains(findingElem));
                    System.out.println(format);
                    break;
                case("5"):
                    
                    writeObject(myCollection);
                    MyCollection myCollection1 = readObject();
                    System.out.println(myCollection1);
                    break;
                case("6"):
                    
                    isEnd=false;
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
При виконанні даної лабораторної роботи було набуто практичного досвіду з навичок тривалого зберігання та відновлення стану об'єктів, ознайомлення з принципами серіалізації/десеріалізації об'єктів,
використання бібліотек класів користувача.