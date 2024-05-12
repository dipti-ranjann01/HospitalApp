package Models;
import Enum.BookingStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slot {
    public Slot(String startTime, String endTime) {
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String startTime;
    private String endTime;

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    private BookingStatus bookingStatus = BookingStatus.OPEN;

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    private int bookingID;

    public Map<Patient,Integer> getWaitPatient() {
        return waitPatient;
    }

    public void setWaitPatient(Patient waitPatient,int bookingId) {
        this.waitPatient.put(waitPatient,bookingId);
    }

    private Map<Patient,Integer> waitPatient = new HashMap<>();

    public void removeWaitPatient(Patient waitPatient) {
        this.waitPatient.remove(waitPatient);
    }
}
