Лабораторна робота № 16. Модульне тестування.
   
Мета: Розробка модульних тестів з використанням JUnit 5.
1 ВИМОГИ :
1.1 Розробник :

-  Кайдаш Ярослав; 
-  студент групи КН-921Д; 
-  22-05-2023. 

 1.2 Загальне завдання :
	Розробити та додати модульні тести до програм попередніх лабораторних робіт. Забезпечити розділення на рівні початкового коду, тести розташовувати в директоріях з назвою test.
	Перевірити всі public-методи власного контейнера та його ітератора, які були створені при віконанні завдання лабораторної роботи "9. Параметризація в Java". Забезпечити покриття коду не менше 80%.
	Перевірити методи, що забезпечують валідацію даних в програмі рішення завдання лабораторної роботи "11. Регулярні вирази. Перевірка даних".
	Перевірити вирішення прикладної задачі лабораторної роботи "12. Регулярні вирази. Обробка тексту".
	Перевірити методи обробки контейнера лабораторної роботи "13. Паралельне виконання. Multithreading". Перевіряти тільки обробку даних, виключаючи multithreading (див. п.4).


1.3 Індивідуальне завдання :
		Довідник покупця. Знайти всі торгові точки, що працюють без вихідних та перерви на обід, що надають для консультації короткий телефонний номер та мають контактні телефони українських операторів мобільного зв'язку.

2 ОПИС ПРОГРАМИ :

	Програма тестує усі наявні методи.

2.1 Засоби ООП :

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
	import junit.framework.TestCase;
	import java.util.ArrayList;


2.2 Ієрархія та структура класів  :
	Має тількі один класс.
   
2.3 Важливі фрагменти програми:

public class MainTest extends TestCase {

    @Test
    public void testHardWorkCheck() {
        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);

        assertTrue(Main.hardWorkCheck(point1));

        assertFalse(Main.hardWorkCheck(point2));

        assertFalse(Main.hardWorkCheck(point3));
    }

    @Test
    public void testHaveShortPhoneNumber() {
        TradingPoint tradingPoint1 = new TradingPoint();
        tradingPoint1.setPhones(List.of("1234"));
        assertTrue(Main.haveShortPhoneNumber(tradingPoint1));

        TradingPoint tradingPoint2 = new TradingPoint();
        tradingPoint2.setPhones(List.of("12345"));
        tradingPoint2.setPhones(List.of("123"));
        assertFalse(Main.haveShortPhoneNumber(tradingPoint2));
    }

    @Test
    public void testCheckUkrainianPhoneNumber() {
        assertTrue(Main.checkUkrainianPhoneNumber("0501234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0631234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0661234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0671234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0681234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0931234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0951234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0961234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0971234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0981234567"));
        assertTrue(Main.checkUkrainianPhoneNumber("0991234567"));

        assertFalse(Main.checkUkrainianPhoneNumber("08001234567"));
        assertFalse(Main.checkUkrainianPhoneNumber("05012345678"));
        assertFalse(Main.checkUkrainianPhoneNumber("05012345"));
    }

    @Test
    public void testLaba13() {
        LinkedListContainer<TradingPoint> listContainer = new LinkedListContainer<>();

        TradingPoint point1 = new TradingPoint("someName", "someAddress", List.of("000-000-0000"), "someThing",
                LocalDateTime.of(2004, 1, 1, 8, 30),
                LocalDateTime.of(2004, 1, 1, 19, 30), 0);
        TradingPoint point2 = new TradingPoint("Cheese", "cheese", List.of("000-000-0000", "1234567891", "1234"), "cheese",
                LocalDateTime.of(2002, 3, 1, 10, 30),
                LocalDateTime.of(2003, 4, 5, 18, 0), 45);
        TradingPoint point3 = new TradingPoint("Apple", "yellow apple", List.of("0974567891"), "apples",
                LocalDateTime.of(2000, 5, 1, 6, 30),
                LocalDateTime.of(2022, 6, 5, 10, 45), 60);


        listContainer.add(point1);
        listContainer.add(point2);
        listContainer.add(point3);

        assertEquals(1, Main.laba13(listContainer));
    }
}


3 ВАРІАНТИ ВИКОРИСТАННЯ:
	- дебагер lldb;
	- консоль;
	- дебагер gdb;
 
ВИСНОВОК:
	При виконанні даної лабораторної роботи було набуто навичкив розробки модульних тестів з використанням JUnit 5.