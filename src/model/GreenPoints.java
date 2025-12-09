package model;

import java.io.Serializable;

/**
 * Tracks community green points and goal state.
 */
public class GreenPoints implements Serializable {
  private int greenPoints;
  private int pointGoal;
  private boolean isReached;

  public Date resetPeriod;

  /**
   * Creates a tracker with a goal and zero starting points.
   * @param goal target points to reach
   */
  public GreenPoints(int goal) {
    greenPoints = 0;
    pointGoal = goal;
    isReached = false;
  }

  /**
   * Updates the goal threshold.
   * @param goal new goal value
   */
  public void setGoal(int goal) {
    pointGoal = goal;
  };

  public int getGoal() {
    return pointGoal;
  }

  /**
   * Resets accumulated points to zero.
   */
  public void resetPoints() {
    greenPoints = 0;
  }

  /**
   * Adds points toward the goal.
   * @param points points to add
   */
  public void addPoints(int points) {
    greenPoints += points;
  }

  public int getPoints() {
    return greenPoints;
  }

  /**
   * Checks whether the goal has been met or exceeded.
   * @return true when goal reached
   */
  public boolean isGoalReached() {
    return greenPoints >= pointGoal;
  }

  /**
   * Marks the goal as reached without altering points.
   */
  public void goalReached() {
    isReached = true;
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    GreenPoints other = (GreenPoints) obj;
    return greenPoints == other.greenPoints
        && pointGoal == other.pointGoal
        && isReached == other.isReached;
  }

  public String toString() {
    return "Greenpoints: " + greenPoints + " " + "Point goal: " + pointGoal;
  }
}