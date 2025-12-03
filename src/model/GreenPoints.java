package model;

public class GreenPoints {
  private int greenPoints;
  private int pointGoal;
  private boolean isReached;

  public Date resetPeriod;

  public GreenPoints(int goal) {
    greenPoints = 0;
    pointGoal = goal;
    isReached = false;
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

  public boolean isGoalReached() {
    return greenPoints >= pointGoal;
  }

  public void goalReached() {
    isReached = true;
  }

  // equals method

  public String toString() {
    return "Greenpoints: " + greenPoints + " " + "Point goal: " + pointGoal;
  }
}