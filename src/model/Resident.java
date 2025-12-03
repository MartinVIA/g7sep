package model;

import utils.MyFileHandler;

public class Resident {
  private final int id;
  private String firstName;
  private String lastName;
  private boolean hasBoost;
  private PersonalPoints personalPoints;
  private Date latestTask;

  public Resident(int id, String firstName, String lastName, int points) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints(points);
    latestTask = null;
    MyFileHandler.appendResident(this, "CREATE");
  }

  public Resident(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints();
    latestTask = null;
    MyFileHandler.appendResident(this, "CREATE");
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean hasBoost() {
    return hasBoost;
  }

  public int getPersonalPoints() {
    return personalPoints.getPoints();
  }

  public Date getLatestTask() {
    return latestTask == null ? null : latestTask;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void setBoost(boolean hasBoost) {
    this.hasBoost = hasBoost;
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void setPersonalPoints(int points) {
    personalPoints.setPoints(points);
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void addPersonalPoints(int points) {
    personalPoints.addPoints(points);
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void resetPersonalPoints() {
    personalPoints.resetPoints();
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public void setLatestTask(Date latestTask) {
    this.latestTask = latestTask;
    MyFileHandler.appendResident(this, "UPDATE");
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    Resident other = (Resident) obj;
    return id == other.id
        && firstName.equals(other.firstName)
        && lastName.equals(other.lastName)
        && hasBoost == other.hasBoost
        && personalPoints.equals(other.personalPoints);
  }

  public String toString() {
    return "Resident id: " + id
        + ", name: " + firstName + " " + lastName
        + ", hasBoost: " + hasBoost
        + ", personalPoints: " + personalPoints.getPoints();
  }

  public String toFileString() {
    return id + "," + firstName + "," + lastName + "," + hasBoost + "," + personalPoints.getPoints();
  }
}