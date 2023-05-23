package kaydashyaroslav16;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TradingPoint implements Serializable {
    //Торгівельна точка: назва
    private String name;
    //Торгівельна точка: адреса
    private String address;
    //Торгівельна точка: телефони
    private List<String> phones;
    //Торгівельна точка: спеціалізація
    private String specialization;
    //Торгівельна точка: початок роботи
    private LocalDateTime startTime;
    //Торгівельна точка: кінець роботи
    private LocalDateTime endTime;
    //Торгівельна точка: перерва
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
//
    public void setPhones(List<String> phones) {
        this.phones = new ArrayList<>(phones);
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
        // Validate name (only allow letters, spaces, and hyphens)
        if (!name.matches("[A-Za-z\\s-]+")) {
            return false;
        }

        // Validate address (only allow letters, numbers, spaces, and commas)
        if (!address.matches("[A-Za-z0-9\\s,]+")) {
            return false;
        }

        // Validate phones (only allow digits and hyphens, and each phone number must be 10 digits)
        for (String phone : phones) {
            if (!phone.matches("\\d{10}|\\d{3}-\\d{3}-\\d{4}|\\d{4}")) {
                return false;
            }
        }

        // Validate specialization (only allow letters and spaces)
        if (!specialization.matches("[A-Za-z\\s]+")) {
            return false;
        }

        // All fields are valid
        return true;
    }

    public TradingPoint(String name, String address, List<String> phones,
                        String specialization, LocalDateTime startTime,
                        LocalDateTime endTime, int lunchBreak) {
        this.name = name;
        this.address = address;
        this.phones = new ArrayList<>(phones);
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