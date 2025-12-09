package model;

/**
 * Task subtype that contributes green points toward the community goal
 */
public class GreenActions extends Task {
    private int greenPointsAward;

    private GreenPoints greenPoints;

    /**
     * Creates a green action task
     * 
     * @param name        Task name
     * @param type        Task type label
     * @param greenPoints Points awarded toward the green goal
     */
    public GreenActions(String name, String type, int greenPoints) {
        super(name, type, greenPoints);
        this.greenPointsAward = greenPoints;
    }

    /**
     * Returns the green points value
     * 
     * @return Green points award
     */
    public int getGreenPoints() {
        return greenPointsAward;
    }

    /**
     * Updates the green points reward
     * 
     * @param greenPointsAward New reward amount
     */
    public void setGreenPoints(int greenPointsAward) {
        this.greenPointsAward = greenPointsAward;
        setPoints(greenPointsAward);
    }

    /**
     * Marks the task complete and records the resident's latest green action
     * 
     * @param resident Resident completing the task
     */
    public void completeTask(Resident resident) {
        Date now = new Date();
        setCompleteDate(now);
        resident.setLatestGreenAction(now);
        // super.markAsComplete();
    }

    /**
     * Compares a green action with another object
     * 
     * @param obj Object to compare
     * @return true if the objects are equal, false otherwise
     */

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        GreenActions other = (GreenActions) obj;
        return super.equals(other) && this.greenPointsAward == other.greenPointsAward;
    }

    /**
     * Provides a String representation of the green action's details
     * 
     * @return a formatted String with green action's name, description, type,
     *         completion of the task and green points award
     */
    public String toString() {
        return super.toString() + ", Green points award: " + greenPointsAward;
    }
}