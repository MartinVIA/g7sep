package model;

import java.io.Serializable;

public abstract class Task implements Serializable {
  private String name;
  private String type;
  // private int amountOfPoints;
  private boolean isComplete;
  private Date completeDate;

  public Task(String name, String type) {
    this.name = name;
    this.type = type;
    // this.amountOfPoints = amountOfPoints;
    isComplete = false;
    completeDate = null;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  // public int getAmountOfPoints() {
  // return amountOfPoints;
  // }

  public void markAsComplete() {
    isComplete = true;
  }

  public boolean isCompleteTask() {
    return isComplete;
  }

  public void setCompleteDate(Date date) {
    completeDate = date;
  }

  public Date getCompleteDate() {
    return completeDate;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  // public void setAmountOfPoints(int amountOfPoints) {
  // this.amountOfPoints = amountOfPoints;
  // }

  // idk if it's going to work for multiple residents
  // public void completeTask(Resident resident) {
  // completeTask = true;
  // resident.addPersonalPoints(amountOfPoints);
  // completeDate = completeDate.today().copy();
  // resident.setLatestTask(completeDate.today().copy());
  // }

  public abstract void completeTask(Resident resident);

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    Task other = (Task) obj;
    return name.equals(other.name)
        && type.equals(other.type)
        && isComplete == other.isComplete;
  }

  public String toString() {
    return "Name: " + name +
        ", Type: " + type +
        ", Complete Task: " + isComplete;
  }
}