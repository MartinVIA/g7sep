package model;

public class GreenActions extends Task {
    // maybe some atributes?
    private int greenPoints;

    public GreenActions(String name, String type, int greenPoints) {
        super(name, type);
        this.greenPoints = greenPoints;
    }

    public int getGreenPoints() {
        return greenPoints;
    }

    public void setGreenPoints(int greenPoints) {
        this.greenPoints = greenPoints;
    }

    public void completeTask(Resident resident) {
        // Logic to complete the green action task

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