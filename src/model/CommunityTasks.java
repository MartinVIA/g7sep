package model;

/**
 * Task subtype that awards personal points for community contributions
 */
public class CommunityTasks extends Task {
  private int personalPointsAward;
  private PersonalPoints personalPoints;

  /**
   * Creates a community task
   *
   * @param name                Task name
   * @param type                Task type
   * @param personalPointsAward Points earned on completion
   */
  public CommunityTasks(String name, String type, int personalPointsAward) {
    super(name, type, personalPointsAward);
    this.personalPointsAward = personalPointsAward;
    this.personalPoints = new PersonalPoints();
  }

  /**
   * Returns the personal points reward amount
   *
   * @return Points awarded
   */
  public int getPersonalPoints() {
    return personalPointsAward;
  }

  /**
   * Updates the personal points reward
   *
   * @param personalPointsAward New reward amount
   */
  public void setPersonalPoints(int personalPointsAward) {
    this.personalPointsAward = personalPointsAward;
    setPoints(personalPointsAward);
  }

  /**
   * Adds personal points to a resident
   *
   * @param resident Resident to award
   * @param points   Points to add
   */
  public void awardPersonalPoints(Resident resident, int points) {
    resident.addPersonalPoints(points);
  }

  /**
   * Marks the task complete, handles boosts, and updates resident state
   *
   * @param resident Resident completing the task
   */
  public void completeTask(Resident resident) {
    Date now = new Date();
    setCompleteDate(now);

    if (resident.getHasBoost())
      resident.setBoost(false);

    personalPoints.awardBoost(resident);

    resident.addPersonalPoints(personalPointsAward);
    resident.setLatestTask(now);
  }

  /**
   * Compares a community task with another object
   *
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    CommunityTasks other = (CommunityTasks) obj;
    return super.equals(other) && this.personalPointsAward == other.personalPointsAward;
  }

  /**
   * Provides a String representation of the community task's details
   *
   * @return a formatted String with community task's name, description, type,
   *         completion of the task and personal points award
   */
  public String toString() {
    return super.toString() + ", Personal points award: " + personalPointsAward;
  }
}