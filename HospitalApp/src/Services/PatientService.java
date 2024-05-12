package Services;

import Models.Doctor;
import Models.Patient;
import Models.Slot;

import java.util.LinkedList;
import java.util.List;

public class PatientService {
    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public Patient getPatientByName(String name)
    {
        for(Patient patient:patientList)
        {
            if(patient.getName().equals(name))
            {
                return patient;
            }
        }
        Patient patient=new Patient(name);
        patientList.add(patient);
        return patient;
    }

    List<Patient> patientList= new LinkedList<>();

    public void registerPatient(String name) {
        Patient patient=new Patient(name);
        patientList.add(patient);
        System.out.println(patient.getName() +" registered successfully");
    }

    public void showAppointmentsBooked(String name,DoctorService doctorService) {
        Patient patient=getPatientByName(name);
        for(int id:patient.getBookingId())
        {
            for(Doctor doctor:doctorService.doctorList)
            {
                for(Slot slot: doctor.getSlotList())
                {
                    if(slot.getBookingID()==id)
                    {
                        System.out.println("Booking id: "  + id + ", Dr. "  + doctor.getName()  + " " + slot.getStartTime());
                    }
                }
            }
        }
    }
}
