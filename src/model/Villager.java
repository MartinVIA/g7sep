package model;

public class Villager {
  private final int id;
  private String name;
  private boolean hasBoost;
  private final PersonalPoints personalPoints;
  private Date latestTrade;

  public Villager(int id, String name, boolean hasBoost, PersonalPoints personalPoints) {
    this.id = id;
    this.name = name;
    this.hasBoost = hasBoost;
    this.personalPoints = personalPoints;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public PersonalPoints getPersonalPoints() {
    return personalPoints;
  }

  public Date getLatestTrade() {
    return latestTrade;
  }

  public boolean hasBoost() {
    return hasBoost;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBoost(boolean hasBoost) {
    this.hasBoost = hasBoost;
  }

  public void setLatestTrade(Date latestTrade) {
    this.latestTrade = latestTrade;
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass())
      return false;

    Villager other = (Villager) obj;
    return id == other.id && name.equals(other.name)
        && hasBoost == other.hasBoost
        && personalPoints.equals(other.personalPoints);
  }

  public String toString() {
    return "Villager{" + "id=" + id + ", name='" + name + '\'' + ", boost=" + hasBoost + ", points="
        + personalPoints;
  }
}
