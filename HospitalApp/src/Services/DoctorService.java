package Services;

import Models.Doctor;
import Models.Patient;
import Models.Slot;
import Enum.BookingStatus;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DoctorService {
    List<Doctor> doctorList=new LinkedList<>();
    int bookingId=1000;

    public void registerDoc(String name, String speciality) {
        Doctor doctor=new Doctor(name,speciality);
        doctorList.add(doctor);
        System.out.println("Welcome Dr. " + doctor.getName());
    }

    public void markDocAvail(String name, String slotList) {
        Doctor doctor=findDocByName(name,doctorList);
        if(slotList.contains(",")) {
            String[] slots = slotList.split(",");
            for (String slot : slots) {
                String startSlot = slot.split("-")[0];
                String endSlot = slot.split("-")[1];
                if (Integer.parseInt(startSlot.split(":")[0]) < 9) {
                    System.out.println("The start time should be after 09:00");
                } else if (Integer.parseInt(endSlot.split(":")[0]) >= 21 && Integer.parseInt(endSlot.split(":")[1]) > 0) {
                    System.out.println("The end time should be before 21:00");
                } else if (Integer.parseInt(endSlot.split(":")[0]) - Integer.parseInt(startSlot.split(":")[0]) == 1 &&
                        Integer.parseInt(endSlot.split(":")[1]) - Integer.parseInt(startSlot.split(":")[1]) == 0) {
                    Slot newSlot = new Slot(startSlot, endSlot);
                    doctor.setSlotList(newSlot);
                    System.out.println("Done Doc!");
                } else {
                    System.out.println("Sorry Dr. " + doctor.getName() + " slots are 60 mins only");
                }
            }
        }
        else{
            String startSlot = slotList.split("-")[0];
            String endSlot = slotList.split("-")[1];
            if (Integer.parseInt(startSlot.split(":")[0]) < 9) {
                System.out.println("The start time should be after 09:00");
            } else if (Integer.parseInt(endSlot.split(":")[0]) >= 21 && Integer.parseInt(endSlot.split(":")[1]) > 0) {
                System.out.println("The end time should be before 21:00");
            } else if (Integer.parseInt(endSlot.split(":")[0]) - Integer.parseInt(startSlot.split(":")[0]) == 1 &&
                    Integer.parseInt(endSlot.split(":")[1]) - Integer.parseInt(startSlot.split(":")[1]) == 0) {
                Slot newSlot = new Slot(startSlot, endSlot);
                doctor.setSlotList(newSlot);
                System.out.println("Done Doc!");
            } else {
                System.out.println("Sorry Dr. " + doctor.getName() + " slots are 60 mins only");
            }
        }
    }

    private Doctor findDocByName(String name, List<Doctor> doctorList) {
        for(Doctor doctor:doctorList)
        {
            if(doctor.getName().equals(name))
            {
                return doctor;
            }
        }
        throw new RuntimeException("Doctor not registered.");
    }

    public void showAvailByspeciality(String speciality) {
        int count=0;
        for(Doctor doctor: doctorList)
        {
            if(doctor.getSpeciality().equals(speciality))
            {
                for(Slot slot:doctor.getSlotList())
                {
                    if(slot.getBookingStatus()==BookingStatus.OPEN) {
                        System.out.println("Dr. " + doctor.getName() + ": (" + slot.getStartTime() + "-" + slot.getEndTime() + ")");
                        count++;
                    }
                }
            }
        }
        if(count==0)
        {
            System.out.println("No one found with the same speciality");
        }
    }

    public void bookAppointment(String patientName, String docName, String slotStartTime, String waitList, PatientService patientService) {
        for(Doctor doctor: doctorList)
        {
            if(docName.contains(doctor.getName()))
            {
                for(Slot slot:doctor.getSlotList())
                {
                    if(slotStartTime.contains(slot.getStartTime()) && slot.getBookingStatus()== BookingStatus.OPEN)
                    {
                        slot.setBookingStatus(BookingStatus.BOOKED);
                        slot.setBookingID(bookingId);
                        Patient patient =patientService.getPatientByName(patientName);
                        patient.setBookingId(bookingId);
                        System.out.println("Booked. Booking id: " + bookingId);
                        if(bookingId==9999)
                        {
                            bookingId=1000;
                        }
                        else{
                            bookingId++;
                        }
                        return;
                    }
                    else if(slotStartTime.contains(slot.getStartTime()) && slot.getBookingStatus()== BookingStatus.BOOKED && (waitList.equals("") ||waitList.equals("waitlist=false")))
                    {
                        System.out.println("This slot is already booked");
                    }
                    else if(slotStartTime.contains(slot.getStartTime()) && slot.getBookingStatus()== BookingStatus.BOOKED && waitList.equals("waitlist=true"))
                    {
                        Patient patient=patientService.getPatientByName(patientName);
                        patient.setBookingId(bookingId);
                        slot.setWaitPatient(patient,bookingId);
                        System.out.println("Added to the waitlist. Booking id: " + bookingId);
                        if(bookingId==9999)
                        {
                            bookingId=1000;
                        }
                        else{
                            bookingId++;
                        }
                    }
                }
            }
        }

    }

    public void cancelBookingId(int id) {
        for(Doctor doctor: doctorList)
        {
            for(Slot slot:doctor.getSlotList())
            {
                if(slot.getBookingID()==id) {
                    System.out.println("Booking Cancelled");
                    if (!slot.getWaitPatient().isEmpty()) {
                        for (Map.Entry<Patient, Integer> waitPatient : slot.getWaitPatient().entrySet()) {
                            slot.setBookingID(waitPatient.getValue());
                            slot.removeWaitPatient(waitPatient.getKey());
                            System.out.println("Booking confirmed for Booking id: " + waitPatient.getValue());
                        }
                    } else {
                        slot.setBookingID(-1);
                        slot.setBookingStatus(BookingStatus.OPEN);
                    }
                    return;
                }
            }
        }
        System.out.println("Booking id not found");
    }

}
