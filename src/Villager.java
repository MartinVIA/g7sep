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

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasBoost() {
        return hasBoost;
    }

    public void setBoost(boolean hasBoost) {
        this.hasBoost = hasBoost;
    }

    public PersonalPoints getPersonalPoints() {
        return personalPoints;
    }

    public void setLatestTrade(Date latestTrade) {
        this.latestTrade = latestTrade;
    }

    public Date getLatestTrade() {
        return latestTrade;
    }

    public String toString() {
        return "Villager{" + "id=" + id + ", name='" + name + '\'' + ", boost=" + hasBoost + ", points="
                + personalPoints;
    }
}
