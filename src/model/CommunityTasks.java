package model;

public class CommunityTasks extends Task {
    // some attributes?
    private int personalPoints;
    private Date completeDate;

    public CommunityTasks(String name, String type, int personalPoints) {
        super(name, type);
        // isComplete is also here = false by default
        this.personalPoints = personalPoints;
        completeDate = null;
    }

    public int getPersonalPoints() {
        return personalPoints;
    }

    public void setPersonalPoints(int personalPoints) {
        this.personalPoints = personalPoints;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void awardPersonalPoints(Resident resident, int points) {
        resident.addPersonalPoints(points);
    }

    public void completeTask(Resident resident) {
        // logic here
        completeDate = completeDate.today();
        resident.addPersonalPoints(personalPoints);
        resident.setLatestTask(completeDate);
        super.markAsComplete();
        // i think it works???
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