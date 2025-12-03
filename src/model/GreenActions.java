package model;

public class GreenActions extends Task {
    // maybe some atributes?
    private int greenPoints;

    public GreenActions(String name, String type, int greenPoints) {
        super(name, type);
        // isComplete is also here = false by default
        this.greenPoints = greenPoints;
    }

    public int getGreenPoints() {
        return greenPoints;
    }

    public void setGreenPoints(int greenPoints) {
        this.greenPoints = greenPoints;
    }

    public void completeTask(Resident resident) {
        // logic here
        super.markAsComplete();

    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        GreenActions other = (GreenActions) obj;
        return super.equals(other)
                && this.greenPoints == other.greenPoints;
    }

    public String toString() {
        return super.toString() +
                ", Green points: " + greenPoints;
    }
}