package model;

public class GreenActions extends Task {
    // maybe some atributes?
    private int greenPointsAward;

    private GreenPoints greenPoints;

    public GreenActions(String name, String type, int greenPoints) {
        super(name, type);
        // isComplete is also here = false by default
        this.greenPointsAward = greenPointsAward;
    }

    public int getGreenPoints() {
        return greenPointsAward;
    }

    public void setGreenPoints(int greenPointsAward) {
        this.greenPointsAward = greenPointsAward;
    }

    public void completeTask(Resident resident) {
        // logic here
        super.markAsComplete();
        greenPoints.addPoints(greenPointsAward);
        resident.settLatestGreenAction()
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        GreenActions other = (GreenActions) obj;
        return super.equals(other)
                && this.greenPointsAward == other.greenPointsAward;
    }

    public String toString() {
        return super.toString() +
                ", Green points award: " + greenPointsAward;
    }
}