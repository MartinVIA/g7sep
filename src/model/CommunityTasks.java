package model;

public class CommunityTasks extends Task {
    // some attributes?
    private int personalPoints;

    public CommunityTasks(String name, String type, int personalPoints) {
        super(name, type);
        this.personalPoints = personalPoints;
    }

    public int getPersonalPoints() {
        return personalPoints;
    }

    public void setPersonalPoints(int personalPoints) {
        this.personalPoints = personalPoints;
    }

    public void completeTask(Resident resident) {
        // Logic to complete the community task
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        CommunityTasks other = (CommunityTasks) obj;
        return super.equals(other)
                && this.personalPoints == other.personalPoints;
    }

    public String toString() {
        return super.toString() +
                ", Personal points: " + personalPoints;
    }
}