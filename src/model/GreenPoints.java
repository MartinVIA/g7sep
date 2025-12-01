package model;

public class GreenPoints {
    private int greenPoints;
    private int pointGoal;

    public Date resetPeriod;

    public void setGoal(int goal) {
        pointGoal = goal;
    };

    public int getGoal() {
        return pointGoal;
    }

    public void resetPoints() {
        greenPoints = 0;
    }

    public void addPoints(int addedPoints) {
        greenPoints = greenPoints + addedPoints;
    }

    public int getPoints() {
        return greenPoints;
    }

    public String toString() {
        return "Greenpoints: " + greenPoints + " " + "Point goal: " + pointGoal;
    }
}