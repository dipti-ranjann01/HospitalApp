package Models;

import java.util.LinkedList;
import java.util.List;

public class Patient {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public List<Integer> getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId.add(bookingId);
    }

    private List<Integer> bookingId=new LinkedList<>();

    public Patient(String name) {
        this.name=name;
    }
}
