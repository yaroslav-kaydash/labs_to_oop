package kaydashyaroslav16;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedListContainer<T> implements Iterable<T>, Serializable {
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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

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
    public class LinkedListIterator implements Iterator<T> {
        //список для итерации
        private Node<T> current;

        //конструктор которий присваивает значение current из списку
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

    //преврашает твой список в масив колекции
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

    //возрашает список с файла под именем 
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
        Arrays.sort(arr, comparator);
        clear();
        for (TradingPoint tp : arr) {
            add((T) tp);
        }
    }
}