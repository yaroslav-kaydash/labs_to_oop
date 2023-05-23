package kaydashyaroslav16;

import org.junit.Test;
import org.junit.Before;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class TradingPointTest {
    private TradingPoint tradingPoint;

    @Before
    public void setUp() {
        tradingPoint = new TradingPoint();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(tradingPoint);
        assertEquals("no name", tradingPoint.getName());
        assertEquals("no address", tradingPoint.getAddress());
        assertEquals(List.of("no phones"), tradingPoint.getPhones());
        assertEquals("no specialization", tradingPoint.getSpecialization());
        assertNotNull(tradingPoint.getStartTime());
        assertNotNull(tradingPoint.getEndTime());
        assertEquals(0, tradingPoint.getLunchBreak());
    }

    @Test
    public void testParameterizedConstructor() {
        LocalDateTime startTime = LocalDateTime.of(2022, 1, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 1, 1, 18, 0);
        List<String> phones = new ArrayList<>();
        phones.add("1234567890");
        phones.add("9876543210");

        tradingPoint = new TradingPoint("Shop1", "123 Main St", phones, "Grocery", startTime, endTime, 30);

        assertNotNull(tradingPoint);
        assertEquals("Shop1", tradingPoint.getName());
        assertEquals("123 Main St", tradingPoint.getAddress());
        assertEquals(phones, tradingPoint.getPhones());
        assertEquals("Grocery", tradingPoint.getSpecialization());
        assertEquals(startTime, tradingPoint.getStartTime());
        assertEquals(endTime, tradingPoint.getEndTime());
        assertEquals(30, tradingPoint.getLunchBreak());
    }

   
    @Test
    public void testValidateWithValidFieldsShouldReturnTrue() {
        tradingPoint.setName("Test Trading Point");
        tradingPoint.setAddress("1234 Test Street");
        List<String> phones = new ArrayList<>();
        phones.add("1234567890");
        tradingPoint.setPhones(phones);
        tradingPoint.setSpecialization("Test Specialization");
        tradingPoint.setStartTime(LocalDateTime.of(2023, 1, 1, 9, 0));
        tradingPoint.setEndTime(LocalDateTime.of(2023, 1, 1, 17, 0));
        tradingPoint.setLunchBreak(30);

        assertTrue(tradingPoint.validate());
    }

    @Test
    public void testValidateWithInvalidNameShouldReturnFalse() {
        tradingPoint.setName("Test123 Trading Point");
        tradingPoint.setAddress("1234 Test Street");
        List<String> phones = new ArrayList<>();
        phones.add("1234567890");
        tradingPoint.setPhones(phones);
        tradingPoint.setSpecialization("Test Specialization");
        tradingPoint.setStartTime(LocalDateTime.of(2023, 1, 1, 9, 0));
        tradingPoint.setEndTime(LocalDateTime.of(2023, 1, 1, 17, 0));
        tradingPoint.setLunchBreak(30);

        assertFalse(tradingPoint.validate());
    }

    @Test
    public void testValidateWithInvalidAddressShouldReturnFalse() {
        tradingPoint.setName("Test Trading Point");
        tradingPoint.setAddress("1234 Test Street!");
        List<String> phones = new ArrayList<>();
        phones.add("1234567890");
        tradingPoint.setPhones(phones);
        tradingPoint.setSpecialization("Test Specialization");
        tradingPoint.setStartTime(LocalDateTime.of(2023, 1, 1, 9, 0));
        tradingPoint.setEndTime(LocalDateTime.of(2023, 1, 1, 17, 0));
        tradingPoint.setLunchBreak(30);

        assertFalse(tradingPoint.validate());
    }

    @Test
    public void testValidateWithInvalidPhonesShouldReturnFalse() {
        tradingPoint.setName("Test Trading Point");
        tradingPoint.setAddress("1234 Test Street");
        List<String> phones = new ArrayList<>();
        phones.add("123456789");
        tradingPoint.setPhones(phones);
        tradingPoint.setSpecialization("Test Specialization");
        tradingPoint.setStartTime(LocalDateTime.of(2023, 1, 1, 9, 0));
        tradingPoint.setEndTime(LocalDateTime.of(2023, 1, 1, 17, 0));
        tradingPoint.setLunchBreak(30);

        assertFalse(tradingPoint.validate());
    }
    

}