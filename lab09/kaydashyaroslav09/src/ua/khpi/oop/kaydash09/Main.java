package kaydashyaroslav09;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //создаем связанний список
        LinkedListContainer<MyCollection> list = new LinkedListContainer<>();

        //создаем колекции для добавление в список
        MyCollection myCollection1 = new MyCollection(new String[]{"Микола"});
        MyCollection myCollection2 = new MyCollection(new String[]{"Slim"});
        MyCollection myCollection3 = new MyCollection(new String[]{"Me"});
        //добавляем в список
        list.add(myCollection1);
        list.add(myCollection2);
        list.add(myCollection3);
        //печатаем список
        for (MyCollection item : list) {
            System.out.println(item + " ");
        }

        //удаляем одну из колекций
        list.remove(myCollection2);
        //проверяем на наличие колекций
        System.out.println("Does our collection contains myCollection1 " + list.contains(myCollection1));
        System.out.println("Does our collection contains myCollection2 " + list.contains(myCollection2));
        System.out.println("Does our collection contains myCollection3 " + list.contains(myCollection3));
        //печатаем колекцию после удаления
        for (MyCollection item : list) {
            System.out.println(item + " /after remove");
        }

        //преврашаем список в масив
        MyCollection[] appleCollections1 = list.toArray();
        //преврашаем масив в строку
        System.out.println(Arrays.toString(appleCollections1) + " /Array");


        // Збереження та відновлення колекції за допомогою сериализации
        list.saveToFile("list.bin");
        LinkedListContainer<MyCollection> loadedList = LinkedListContainer.loadFromFile("list.bin");
        for (MyCollection item : loadedList) {
            System.out.println(item + " /after save by Serialisation");
        }



        // Збереження та відновлення колекції без сериализации
        list.saveToFileXml();
        LinkedListContainer<MyCollection> appleCollections = LinkedListContainer.loadFromFileXml();
        for (MyCollection item : appleCollections) {
            System.out.println(item + " /after save without Serialisation");
        }
    }
}