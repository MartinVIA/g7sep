package model;

import java.io.Serializable;

/**
 * Represents a resident with their details such as identity, name,
 * boost state, personal points and recent actions
 */
public class Resident implements Serializable {
  private final int id;
  private String firstName;
  private String lastName;
  private boolean hasBoost;
  private PersonalPoints personalPoints;
  private Date latestTask;
  private Date latestGreenAction;

  /**
   * Creates a resident with id, first name, last name and an initial personal
   * points balance
   * 
   * @param id        Unique identifier
   * @param firstName First name
   * @param lastName  Last name
   * @param points    Starting personal points
   */
  public Resident(int id, String firstName, String lastName, int points) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints(points);
    latestTask = null;
    latestGreenAction = null;
  }

  /**
   * Creates a resident with id, first name, last name and 0 personal points
   * 
   * @param id        Unique identifier
   * @param firstName First name
   * @param lastName  Last name
   */
  public Resident(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    hasBoost = false;
    personalPoints = new PersonalPoints();
    latestTask = null;
    latestGreenAction = null;
  }

  /**
   * Returns the unique ID of the resident
   * 
   * @return Unique identifier
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the first name of the resident
   * 
   * @return First name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name of the resident
   * 
   * @return Last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns whether the resident has an active boost
   * 
   * @return True if boost is active, false otherwise
   */
  public boolean getHasBoost() {
    return hasBoost;
  }

  /**
   * Returns the personal points balance
   * 
   * @return Personal points
   */
  public int getPersonalPoints() {
    return personalPoints.getPoints();
  }

  /**
   * Returns the date of the most recent community task completion
   * 
   * @return Date of latest community task, or null if none
   */
  public Date getLatestTask() {
    return latestTask == null ? null : latestTask;
  }

  /**
   * Returns the date of the most recent green action completion
   * 
   * @return Date of latest green action, or null if none
   */
  public Date getLatestGreenAction() {
    return latestGreenAction == null ? null : latestGreenAction;
  }

  /**
   * Updates the first name
   * 
   * @param firstName New first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Updates the last name
   * 
   * @param lastName New last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Enables or disables the boost flag
   * 
   * @param hasBoost New boost state
   */
  public void setBoost(boolean hasBoost) {
    this.hasBoost = hasBoost;
  }

  /**
   * Overwrites the personal points balance
   * 
   * @param points New balance
   */
  public void setPersonalPoints(int points) {
    personalPoints.setPoints(points);
  }

  /**
   * Adds points to the resident balance
   * 
   * @param points Points to add
   */
  public void addPersonalPoints(int points) {
    personalPoints.addPoints(points);
  }

  /**
   * Resets personal points to zero
   */
  public void resetPersonalPoints() {
    personalPoints.resetPoints();
  }

  /**
   * Sets the date of the most recent community task completion
   * 
   * @param latestTask Completion date
   */
  public void setLatestTask(Date latestTask) {
    this.latestTask = latestTask;
  }

  /**
   * Sets the date of the most recent green action completion
   * 
   * @param latestGreenAction Completion date
   */
  public void setLatestGreenAction(Date latestGreenAction) {
    this.latestGreenAction = latestGreenAction;
  }

  /**
   * Compares a resident object with another object
   * 
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
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

  /**
   * Returns a string representation of the resident
   * 
   * @return a Formatted string with resident details
   */
  public String toString() {
    return "Resident id: " + id
        + ", name: " + firstName + " " + lastName
        + ", hasBoost: " + hasBoost
        + ", personalPoints: " + personalPoints;
  }
}