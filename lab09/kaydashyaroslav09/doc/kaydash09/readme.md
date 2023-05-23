Лабораторна робота № 9. Параметризація в Java.

        
Мета: Вивчення принципів параметризації в Java. Розробка параметризованих класів та методів.
1 ВИМОГИ 
1.1 Розробник :
	-  Кайдаш Ярослав; 
	-  студент групи КН-921Д; 
	-  29-04-2022. 

1.2 Загальне завдання :
	Створити власний клас-контейнер, що параметризується (Generetice Type), на основі зв'язаних списків для реалізації колекції domain-об’єктів лабораторнї роботи номер 7.
	Для розроблених класів-контейнерів забезпечити можливість використання їх об'єктів у циклі foreach в якості джерела даних.
	Забезпечити можливість збереження та відновлення колекції об'єктів: 1) за допомогою стандартної серіалізації; 2) не використовуючи протокол серіалізації.
	Продемонструвати розроблену функціональність: створення контейнера, додавання елементів, видалення елементів, очищення контейнера, перетворення у масив, перетворення у рядок, перевірку на наявність елементів.
	Забороняється використання контейнерів (колекцій) з Java Collections Framework.


1.3 Індивідуальне завдання :
	Довідник покупця
	Торгівельна точка: назва; адреса; телефони (кількість не обмежена); спеціалізація; час роботи (з зазначенням днів тижня).

2 ОПИС ПРОГРАМИ :
Программа класс-контейнер забезпечує можливість збереження та відновлення колекції обьєктів.

2.1 Засоби ООП:
	import java.beans.XMLDecoder;
	import java.beans.XMLEncoder;
	import java.io.*;
	import java.util.*;
	import java.util.Arrays;
	import java.io.Serializable;
	import java.util.Iterator;
	import java.util.Objects;

2.2 Ієрархія та структура класів  :
	Має два классу (MyCollection and LinkedList).
   
2.3 Важливі фрагменти програми:


public class Main {
    public static void main(String[] args) {
        LinkedListContainer<MyCollection> list = new LinkedListContainer<>();

        MyCollection myCollection1 = new MyCollection(new String[]{"Андрій"});
        MyCollection myCollection2 = new MyCollection(new String[]{"Slim"});
        MyCollection myCollection3 = new MyCollection(new String[]{"Me"});

        list.add(myCollection1);
        list.add(myCollection2);
        list.add(myCollection3);

        for (MyCollection item : list) {
            System.out.println(item + " ");
        }

        list.remove(myCollection2);

        System.out.println("Does our collection contains myCollection1 " + list.contains(myCollection1));
        System.out.println("Does our collection contains myCollection2 " + list.contains(myCollection2));
        System.out.println("Does our collection contains myCollection3 " + list.contains(myCollection3));

        for (MyCollection item : list) {
            System.out.println(item + " /after remove");
        }

        MyCollection[] appleCollections1 = list.toArray();
        System.out.println(Arrays.toString(appleCollections1) + " /Array");

list.saveToFile("list.bin"); LinkedListContainer<MyCollection> loadedList = LinkedListContainer.loadFromFile("list.bin");
        for (MyCollection item : loadedList) {
            System.out.println(item + " /after save by Serialisation");
        }

        list.saveToFileXml();
        LinkedListContainer<MyCollection> appleCollections = LinkedListContainer.loadFromFileXml();
        for (MyCollection item : appleCollections) {
            System.out.println(item + " /after save without Serialisation");
        }
    }
}
 
 
3 ВАРІАНТИ ВИКОРИСТАННЯ:

	- дебагер lldb;
	- консоль;
	- дебагер gdb;
 
ВИСНОВОК:
	При виконанні даної лабораторної роботи було набуто практичного досвіду з вивчення принципів параметризації в Java та розробкою параметризованих класів та методів.