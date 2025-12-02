package model;

public class PersonalPoints {
  private int personalPoints;
  private Date date;
  private Villager villager;

  public PersonalPoints() {
    personalPoints = 0;
  }

  public void setPoints(int points) {
    personalPoints = points;
  }

  public int getPoints() {
    return personalPoints;
  }

  public void awardPoints(int points) {
    personalPoints += points;
  }

  public void resetPoints() {
    personalPoints = 0;
  }

  public double calculateBoost(Villager villager) {
    double boost = 1.0;
    // how do we calculate boost?
    // i guess we compare the date of the latest task completion to the current date
    // and then decide on a boost value based on that
    Date currentDate = date.today();
    Date lastCompletionDate = villager.getLatestTrade();
    long diff = currentDate.getNumOfDays() - lastCompletionDate.getNumOfDays();
    if (diff > 14)
      boost += 0.2;
    else if (diff > 30)
      boost += 0.4;
    else if (diff > 60)
      boost += 0.8;

    villager.setLatestTrade(currentDate);

    return boost;
  }

  public void awardBoost(Villager villager) {
    // if calculateBoost > 0, award boost
    double boost = calculateBoost(villager);
    if (boost > 1.0) {
      personalPoints = (int) (personalPoints * boost);
    }
  }

  public boolean equals(Object obj) {
    if (obj == null && obj.getClass() != this.getClass())
      return false;

    PersonalPoints other = (PersonalPoints) obj;

    return this.personalPoints == other.personalPoints
        && this.date.equals(other.date)
        && this.villager.equals(other.villager);
  }

  public String toString() {
    return "personal points: " + personalPoints;
  }
}
