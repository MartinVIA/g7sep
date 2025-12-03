package model;

public class CommunityTasks extends Task {
    // some attributes?
    private int personalPointsAward;
    // private Date completeDate;

    public CommunityTasks(String name, String type, int personalPointsAward) {
        super(name, type);
        // isComplete = false;
        this.personalPointsAward = personalPointsAward;
        // completeDate = null;
    }

    public int getPersonalPoints() {
        return personalPointsAward;
    }

    public void setPersonalPoints(int personalPointsAward) {
        this.personalPointsAward = personalPointsAward;
    }

    public void awardPersonalPoints(Resident resident, int points) {
        resident.addPersonalPoints(points);
    }

    public void completeTask(Resident resident) {
        // logic here
        Date now = new Date();
        setCompleteDate(now);
        resident.addPersonalPoints(personalPointsAward);
        resident.setLatestTask(now);
        super.markAsComplete();
        // i think it works???
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        CommunityTasks other = (CommunityTasks) obj;
        return super.equals(other)
                && this.personalPointsAward == other.personalPointsAward;
    }

    public String toString() {
        return super.toString() +
                ", Personal points award: " + personalPointsAward;
    }
}