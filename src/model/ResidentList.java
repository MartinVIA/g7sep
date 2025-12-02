package model;

import java.util.*;

public class ResidentList {
    private final List<Resident> residents;

    public ResidentList() {
        residents = new ArrayList<Resident>();
    }

    public void addResident(Resident v) {
        residents.add(v);
    }

    public void removeResident(Resident v) {
        residents.remove(v);
    }

    public Resident getResident(int index) {
        if (index < residents.size() && index >= 0) {
            return residents.get(index);
        } else
            return null;
    }

    public Resident getByID(int id) {
        for (int i = 0; i < residents.size(); i++) {
            if (residents.get(i).getId() == id) {
                return residents.get(i);
            }
        }
        return null;
    }

    public List<Resident> getAllResidents() {
        return new ArrayList<>(residents);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        ResidentList other = (ResidentList) obj;
        return residents.equals(other.residents);
    }

    public String toString() {
        String list = "";
        for (int i = 0; i < residents.size(); i++) {
            list += residents.get(i) + "\n";
        }
        return list;
    }
}
