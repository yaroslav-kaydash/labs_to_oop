package kaydashyaroslav16;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;


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
        // Create a TradingPoint object with a phone number that matches the regex
        TradingPoint tradingPoint1 = new TradingPoint();
        tradingPoint1.setPhones(List.of("1234"));
        assertTrue(Main.haveShortPhoneNumber(tradingPoint1));

        // Create a TradingPoint object with phone numbers that do not match the regex
        TradingPoint tradingPoint2 = new TradingPoint();
        tradingPoint2.setPhones(List.of("12345"));
        tradingPoint2.setPhones(List.of("123"));
        assertFalse(Main.haveShortPhoneNumber(tradingPoint2));
    }

    @Test
    public void testCheckUkrainianPhoneNumber() {
        // Test phone numbers that match the regex
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

        // Test phone numbers that do not match the regex
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