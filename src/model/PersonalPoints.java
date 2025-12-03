package model;

public class PersonalPoints {
  private int personalPoints;
  private Date date;
  // private Resident resident;

  public PersonalPoints() {
    personalPoints = 0;
  }

  public PersonalPoints(int points) {
    personalPoints = points;
  }

  public void setPoints(int points) {
    personalPoints = points;
  }

  public int getPoints() {
    return personalPoints;
  }

  public void addPoints(int points) {
    setPoints(getPoints() + points);
  }

  public void resetPoints() {
    setPoints(0);
  }

  public double calculateBoost(Resident resident) {
    double boost = 1.0; // default no boost
    // how do we calculate boost?
    // i guess we compare the date of the latest task completion to the current date
    // and then decide on a boost value based on that
    Date currentDate = date.today();
    Date lastCompletionDate = resident.getLatestTask();
    long diff = currentDate.getNumOfDays() - lastCompletionDate.getNumOfDays();
    // example boost values
    if (diff > 14)
      boost += 0.2; // 1.2 multiplier if more than 2 weeks
    else if (diff > 30)
      boost += 0.4; // 1.4 multiplier if more than 1 month
    else if (diff > 60)
      boost += 0.8; // 1.8 multiplier if more than 2 months

    // this is handleded in the tasks completion methods
    // resident.setLatestTask(currentDate);

    return boost;
  }

  public void awardBoost(Resident resident) {
    // only award boost if resident does not already have one
    if (!resident.hasBoost()) {
      double boost = calculateBoost(resident);
      // if calculateBoost > 0, award boost
      if (boost > 1.0) {
        personalPoints = (int) (personalPoints * boost);
        resident.setBoost(true);
        // okay cool it works but
        // how do we make it so it resets
      }
    }
  }

  public boolean equals(Object obj) {
    if (obj == null && obj.getClass() != this.getClass())
      return false;

    PersonalPoints other = (PersonalPoints) obj;

    return this.personalPoints == other.personalPoints;
  }

  public String toString() {
    return "Personal points: " + personalPoints;
  }
}