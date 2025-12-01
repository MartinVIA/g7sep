package model;

public class PersonalPoints {
    private int personalPoints;
    private Date date;
    private Villager villager;

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

    public double calculateBoost() {
        double boost = 1.0;
        // how do we calculate boost?
        // i guess we compare the date of the latest task completion to the current date
        // and then decide on a boost value based on that
        date = date.today().copy();
        Date lastCompletionDate = villager.getLatestTrade();
        if (lastCompletionDate.getFullDate() > date.getFullDate()) {
        }

        return boost;
    }

    public void awardBoost() {
        // if calculateBoost > 0, award boost
    }
}
