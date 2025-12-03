package model;

public class GreenPoints {
  private int greenPoints;
  private int pointGoal;

  public Date resetPeriod;

  public GreenPoints() {
    greenPoints = 0;
    pointGoal = 0;
  }

  public GreenPoints(int goal) {
    greenPoints = 0;
    pointGoal = goal;
  }

  public void setGoal(int goal) {
    pointGoal = goal;
  };

  public int getGoal() {
    return pointGoal;
  }

  public void resetPoints() {
    greenPoints = 0;
  }

  public void addPoints(int points) {
    greenPoints += points;
  }

  public int getPoints() {
    return greenPoints;
  }

  public String toString() {
    return "Greenpoints: " + greenPoints + " " + "Point goal: " + pointGoal;
  }
}