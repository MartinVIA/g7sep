package model;

public class GreenActions extends Task {
    // maybe some atributes?
    private int greenPointsAward;

    private GreenPoints greenPoints;

    public GreenActions(String name, String type, int greenPoints) {
        super(name, type);
        this.greenPointsAward = greenPointsAward;
        // isComplete = false; - inherited
        // completeDate = null; - inherited
    }

    public int getGreenPoints() {
        return greenPointsAward;
    }

    public void setGreenPoints(int greenPointsAward) {
        this.greenPointsAward = greenPointsAward;
    }

    public void completeTask(Resident resident) {
        // logic here
        Date now = new Date();
        setCompleteDate(now);
        greenPoints.addPoints(greenPointsAward);
        resident.setLatestGreenAction(now);
        super.markAsComplete();
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