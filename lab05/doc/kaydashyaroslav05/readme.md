Лабораторна робота № 5.Розробка власних контейнерів.Ітератори.

        
Мета: Набуття навичок розробки власних контейнерів та використання ітераторів.

     
1 ВИМОГИ 
1.1 Розробник 

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  29-11-2022. 

1.2 Загальне завдання 

Розробити клас-контейнер, що ітерується для збереження початкових даних завдання л.р. номер 3 у вигляді масиву рядків з можливістю додавання, видалення і зміни елементів.

В контейнері реалізувати та продемонструвати наступні методи:
	String toString() повертає вміст контейнера у вигляді рядка;
	void add(String string) додає вказаний елемент до кінця контейнеру;
	void clear() видаляє всі елементи з контейнеру;
	boolean remove(String string) видаляє перший випадок вказаного елемента з контейнера;
	Object[] toArray() повертає масив, що містить всі елементи у контейнері;
	int size() повертає кількість елементів у контейнері;
	boolean contains(String string) повертає true, якщо контейнер містить вказаний елемент;
	boolean containsAll(Container container) повертає true, якщо контейнер містить всі елементи з зазначеного у параметрах;
	public Iterator<String> iterator() повертає ітератор відповідно до Interface Iterable.

В класі ітератора відповідно до Interface Iterator реалізувати методи:
public boolean hasNext();
	public String next();
	public void remove().
	Продемонструвати роботу ітератора за допомогою циклів while и for each.
	Забороняється використання контейнерів (колекцій) і алгоритмів з Java Collections Framework.



1.3 Індивідуальне завдання 
-

2.ОПИС ПРОГРАМИ :
Моя программа демонструє користувачу методи моєї коллекції.

2.1 Засоби ООП:
Java code convention;
JDK;
java.util.Objects;
java.util.Scanner;
java.util.*.


2.2 Ієрархія та структура класів  :
Має тількі один класс.
   
2.3 Важливі фрагменти програми

    private static String str = "sad , ,sda , tt,22,!,23a4 3 4 6 ^ a b c a,b,c";

    static void removeSigns() {
       
        StringBuilder stringBuilder = new StringBuilder();
        
        char[] chars = str.toString().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            
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


    
    static class MyCollection {

        
        String[] arr;
        
        int count = 0;

        
        private MyCollection() {
            
            arr = new String[2^32];
        }

        private MyCollection(String[] s) {
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
    }

    public static void main(String[] args) {
       
        MyCollection myCollection = new MyCollection();
        
        for (int i = 0; i < 10; i++) {
            
            Scanner myInput = new Scanner(System.in);
            
            String myStr = myInput.nextLine();
            
            fillString(myStr);
            
            myCollection.add(str);
            
            doLogic();
            
            if (i == 2) {
                myCollection.add("removed");
                myCollection.add("iterationRemove");
            }
        }

        
        System.out.println(myCollection.toString());

        
        Iterator iterator = myCollection.iterator();

        
        while (iterator.hasNext()) {
            
            Object next = iterator.next();
            if (next.toString().equals("iterationRemove")) {
                System.out.println("iterationRemove was removed");
               
                iterator.remove();
            }
        }
        System.out.println(myCollection.toString());

        if (myCollection.contains("removed")) {
            System.out.println("MyCollection contains removed: " + myCollection.remove("removed"));
        }
        System.out.println(myCollection.toString());

        String[] myCollArray = (String[]) myCollection.toArray();
        System.out.println("Checking containsAll method: " + myCollection.containsAll(myCollArray));

        for (int i = 0; i < myCollection.size(); i++) {
            System.out.println(myCollArray[i]);
        }
        System.out.println(myCollection.toString());
    }

}

 
 
 
3.ВАРІАНТИ ВИКОРИСТАННЯ:
- дебагер lldb;
- консоль;
- дебагер gdb;
 
 
ВИСНОВОК:
При виконанні даної лабораторної роботи було набуто практичного досвіду з навичок розробки власних контейнерів та використання ітераторів.