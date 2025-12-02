package model;

public class Villager {
  private final int id;
  private String name;
  private boolean hasBoost;
  private int points;
  private PersonalPoints personalPoints;
  private Date latestTrade;

  public Villager(int id, String name) {
    this.id = id;
    this.name = name;
    hasBoost = false;
    points = 0;
    latestTrade = null;
  }

  public Villager(int id, String name, int points) {
    this.id = id;
    this.name = name;
    hasBoost = false;
    this.points = points;
    latestTrade = null;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean hasBoost() {
    return hasBoost;
  }

  public int getPersonalPoints() {
    return personalPoints.getPoints();
  }

  public Date getLatestTrade() {
    return latestTrade == null ? null : latestTrade;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBoost(boolean hasBoost) {
    this.hasBoost = hasBoost;
  }

  public void setPersonalPoints(int points) {
    personalPoints.setPoints(points);
  }

  public void addPersonalPoints(int points) {
    personalPoints.awardPoints(points);
  }

  public void resetPersonalPoints() {
    personalPoints.resetPoints();
  }

  public void setLatestTrade(Date latestTrade) {
    this.latestTrade = latestTrade;
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    Villager other = (Villager) obj;
    return id == other.id
        && name.equals(other.name)
        && hasBoost == other.hasBoost
        && personalPoints.equals(other.personalPoints);
  }

  public String toString() {
    return "Villager{" + "id=" + id + ", name='" + name + '\'' + ", boost=" + hasBoost + ", points="
        + personalPoints;
  }
}
