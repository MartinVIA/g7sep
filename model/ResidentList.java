package model;

import java.util.*;
import java.io.Serializable;

/**
 * The class containing a list of Resident objects
 */
public class ResidentList implements Serializable {
  private ArrayList<Resident> residents;

  /**
   * Creates an empty resident list
   */
  public ResidentList() {
    residents = new ArrayList<Resident>();
  }

  /**
   * Returns number of residents
   *
   * @return List size
   */
  public int size() {
    return residents.size();
  }

  /**
   * Adds a resident to the list
   *
   * @param resident Resident to add
   */
  public void addResident(Resident resident) {
    residents.add(resident);
  }

  /**
   * Removes a resident if the resident is present
   *
   * @param resident Resident to remove
   */
  public void removeResident(Resident resident) {
    residents.remove(resident);
  }

  /**
   * Finds a resident by id
   *
   * @param id Identifier to look up
   * @return Matching resident or null when not found
   */
  public Resident getResidentById(int id) {
    for (int i = 0; i < residents.size(); i++) {
      if (residents.get(i).getId() == id) {
        return residents.get(i);
      }
    }
    return null;
  }

  /**
   * Returns a copy of the internal list
   *
   * @return List copy
   */
  public ArrayList<Resident> getAllResidents() {
    return new ArrayList<Resident>(residents);
  }

  /**
   * Compares a resident list object with another object
   *
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    ResidentList other = (ResidentList) obj;
    return residents.equals(other.residents);
  }

  /**
   * Returns a string representation of the resident list
   *
   * @return a Formatted string with residents' details
   */
  public String toString() {
    String list = "";
    for (int i = 0; i < residents.size(); i++) {
      list += residents.get(i) + "\n";
    }
    return list;
  }
}