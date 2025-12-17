package model;

import java.io.Serializable;

/**
 * Tracks community green points and goal state.
 * @author Victor Tonu
 * @author Adam Terelak
 * @author Martin Chavez
 */
public class GreenPoints implements Serializable {

    private int greenPoints;
    private int pointGoal;
    private String communityReward;
    private boolean isReached;

    public Date resetPeriod;

    /**
     * Creates a tracker with zero starting points and zero goal
     * @param goal points to reach
     */
    public GreenPoints() {
        greenPoints = 0;
        pointGoal = 100;
        communityReward = "";
        isReached = false;
    }

    /**
     * Creates a tracker with a goal and zero starting points
     * @param goal points to reach
     */
    public GreenPoints(int goal) {
        greenPoints = 0;
        pointGoal = goal;
        communityReward = "";
        isReached = false;
    }

    /**
     * sets the goal threshold
     * @param goal New updated goal value
     */
    public void setGoal(int goal) {
        pointGoal = goal;
    }

    ;
    /** 
     * gets the goal
     * @return pointGoal
     */
  public int getGoal() {
        return pointGoal;
    }
    /**
     * sets the community reward
     * @return reward
      */
    public void setCommunityReward(String reward) {
        communityReward = reward;
    }
    /**
     * Gets the reward 
     * @return communityReward
     */
    public String getCommunityReward() {
        return communityReward;
    }

    /**
     * Resets green points to zero
     */
    public void resetPoints() {
        greenPoints = 0;
    }

    /**
     * Adds points toward the goal
     * @param points Points to add
     */
    public void addPoints(int points) {
        greenPoints += points;
    }
    /**
     * gets the greenpoints
     * @return greenPoints
     */

    public int getPoints() {
        return greenPoints;
    }

    /**
     * Checks whether the goal has been met or exceeded
     * @return True when goal reached
     */
    public boolean isGoalReached() {
        return greenPoints >= pointGoal;
    }

    /**
     * Marks the goal as complete, resets points and creates a new GreenPoints object
     */
    public void completeGoal() {
        if (isGoalReached()) {
            isReached = true;
            resetPoints();
            setGoal(100);
        }
    }

    /**
     * Compares green points object with another object
     * @param obj Object to compare
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        GreenPoints other = (GreenPoints) obj;
        return greenPoints == other.greenPoints
                && pointGoal == other.pointGoal
                && isReached == other.isReached;
    }

    /**
     * Returns a string representation of the green points
     * @return a Formatted string with green points and goal
     */
    public String toString() {
        return "Green points: " + greenPoints + " " + "Point goal: " + pointGoal;
    }
}
