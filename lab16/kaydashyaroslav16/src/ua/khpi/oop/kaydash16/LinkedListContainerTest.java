package kaydashyaroslav16;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
public class LinkedListContainerTest extends TestCase {

    private LinkedListContainer<String> linkedList;

    @Before
    public void setUp() {
        linkedList = new LinkedListContainer<>();
    }

    @Test
    public void testConstructor() {
        assertNull(linkedList.getHead());
        assertEquals(0, linkedList.size());
    }
    @Test
    public void testSize() {
        assertEquals(0, linkedList.size());
        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");
        assertEquals(3, linkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(linkedList.isEmpty());
        linkedList.add("one");
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testContains() {
        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");
        assertTrue(linkedList.contains("two"));
        assertFalse(linkedList.contains("four"));
    }

    @Test
    public void testAdd() {
        assertTrue(linkedList.add("one"));
        assertTrue(linkedList.add("two"));
        assertTrue(linkedList.add("three"));
        assertEquals(3, linkedList.size());
    }

    @Test
    public void testRemove() {
        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");

        assertTrue(linkedList.remove("two"));
        assertFalse(linkedList.contains("two"));
        assertEquals(2, linkedList.size());

        assertFalse(linkedList.remove("four"));
    }

    @Test
    public void testClear() {
        LinkedListContainer<Integer> list = new LinkedListContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(3, list.size());
        list.clear();
        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        LinkedListContainer<TradingPoint> list = new LinkedListContainer<>();
        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);
        list.add(point1);
        list.add(point2);
        list.add(point3);
        TradingPoint[] arr = list.toArray();
        Assert.assertEquals(3, arr.length);
        Assert.assertEquals(point1, arr[0]);
        Assert.assertEquals(point2, arr[1]);
        Assert.assertEquals(point3, arr[2]);
    }

    @Test
    public void testSaveAndLoadFromFile() {
        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");

        linkedList.saveToFile("testLinkedList.bin");

        LinkedListContainer<TradingPoint> loadedList = LinkedListContainer.loadFromFile("testLinkedList.bin");
        assertNotNull(loadedList);
        assertEquals(3, loadedList.size());
        assertTrue(loadedList.contains("one"));
        assertTrue(loadedList.contains("two"));
        assertTrue(loadedList.contains("three"));
    }

    @Test
    public void testSortByTradingPoint() {
        LinkedListContainer<TradingPoint> list = new LinkedListContainer<>();

        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);

        list.add(point1);
        list.add(point2);
        list.add(point3);

        list.sortByTradingPoint(new Comparator<TradingPoint>() {
            @Override
            public int compare(TradingPoint tp1, TradingPoint tp2) {
                return tp1.getName().compareTo(tp2.getName());
            }
        });

        TradingPoint[] expectedArray = {
                point3,
                point2,
                point1
        };
        Assert.assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    public void testHasNext() {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        Iterator<String> iterator = linkedList.iterator();

        // Переконайтесь, що hasNext() повертає true для першого елементу
        Assert.assertTrue(iterator.hasNext());

        // Перехід до наступного елемента
        iterator.next();

        // Переконайтесь, що hasNext() повертає true для другого елементу
        Assert.assertTrue(iterator.hasNext());

        // Перехід до наступного елемента
        iterator.next();

        // Переконайтесь, що hasNext() повертає true для третього елементу
        Assert.assertTrue(iterator.hasNext());

        // Перехід до наступного елемента
        iterator.next();

        // Переконайтесь, що hasNext() повертає false після досягнення кінця списку
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext() {
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        Iterator<String> iterator = linkedList.iterator();

        // Переконайтесь, що функція next() повертає правильний перший елемент
        Assert.assertEquals("1", iterator.next());

        // Переконайтесь, що функція next() повертає правильний другий елемент
        Assert.assertEquals("2", iterator.next());

        // Переконайтесь, що функція next() повертає правильний третій елемент
        Assert.assertEquals("3", iterator.next());
    }

    @Test
    public void testSaveToFileXml() {
        // Виклик методу для збереження об'єкту в XML-файл
        linkedList.saveToFileXml();

        // Стверджуємо, що файл існує
        File file = new File("safe.xml");
        assertTrue(file.exists());
    }

    @Test
    public void testLoadFromFileXml() {
        // Виклик методу для збереження об'єкту в XML-файл
        linkedList.saveToFileXml();

        // Виклик методу для завантаження об'єкту з XML-файлу
        LinkedListContainer<TradingPoint> loadedContainer = LinkedListContainer.loadFromFileXml();

        // Стверджувати, що завантажений об'єкт не є нульовим
        assertNotNull(loadedContainer);

        // Стверджувати, що завантажений об'єкт містить ті самі дані, що й оригінальний об'єкт
        assertEquals(linkedList.size(), loadedContainer.size());
    }

    @Test
    public void testSetHead() {
        // Перевірте порожній список
        assertNull(linkedList.getHead());

        // Установку тесту обнулено
        Node<String> node = new Node<>("A");
        linkedList.setHead(node);
        assertEquals(node, linkedList.getHead());

        // Тестове налаштування має ненульове значення
        Node<String> newNode = new Node<>("B");
        linkedList.setHead(newNode);
        assertEquals(newNode, linkedList.getHead());
        assertNotEquals(node, linkedList.getHead());
    }

    // Допоміжний клас для Node в LinkedListContainer
    private static class Node<T> extends LinkedListContainer.Node<String> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}