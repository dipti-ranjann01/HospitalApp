package Models;

import java.util.LinkedList;
import java.util.List;

public class Doctor {
    private String name;
    private String speciality;
    private List<Slot> slotList=new LinkedList<>();

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(Slot slot) {
        this.slotList.add(slot);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }



    public Doctor(String name, String speciality) {
        this.name=name;
        this.speciality=speciality;
    }
}
