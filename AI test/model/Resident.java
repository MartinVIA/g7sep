package model;

import java.io.Serializable;

public class Resident implements Serializable {
  private final int id;
  private String firstName;
  private String lastName;
  private boolean hasBoost;
  private PersonalPoints personalPoints;
  private Date latestTask;
  private Date latestGreenAction;

  public Resident(int id, String firstName, String lastName, int points) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints(points);
    latestTask = null;
    latestGreenAction = null;
  }

  public Resident(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints();
    latestTask = null;
    latestGreenAction = null;
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

  public boolean getHasBoost() {
    return hasBoost;
  }

  public int getPersonalPoints() {
    return personalPoints.getPoints();
  }

  public Date getLatestTask() {
    return latestTask == null ? null : latestTask;
  }

  public Date getLatestGreenAction() {
    return latestGreenAction == null ? null : latestGreenAction;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBoost(boolean hasBoost) {
    this.hasBoost = hasBoost;
  }

  public void setPersonalPoints(int points) {
    personalPoints.setPoints(points);
  }

  public void addPersonalPoints(int points) {
    personalPoints.addPoints(points);
  }

  public void resetPersonalPoints() {
    personalPoints.resetPoints();
  }

  public void setLatestTask(Date latestTask) {
    this.latestTask = latestTask;
  }

  public void setLatestGreenAction(Date latestGreenAction) {
    this.latestGreenAction = latestGreenAction;
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
    // commented this cause it caused problems
    // while editing the attributes of the resident
    // && latestTask.equals(other.latestTask)
    // && latestGreenAction.equals(other.latestGreenAction);
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