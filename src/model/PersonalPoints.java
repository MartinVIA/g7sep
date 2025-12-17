package model;

import java.io.Serializable;

/**
 * Tracks personal points for a resident and provides boost calculations based
 * on task completion recency
 * @author Loke Hansen
 * @author Victor Tonu
 * @author Adam Terelak
 * @author Martin Chavez
 * @author Leon de Kuijper
 */
public class PersonalPoints implements Serializable {

    private int personalPoints;
    private Date date;

    /**
     * Creates a personal points tracker starting at zero points
     */
    public PersonalPoints() {
        personalPoints = 0;
    }

    /**
     * Creates a personal points tracker with an initial point balance
     * @param points Starting points
     */
    public PersonalPoints(int points) {
        personalPoints = points;
    }

    /**
     * Sets the point balance
     * @param points New balance
     */
    public void setPoints(int points) {
        personalPoints = points;
    }

    /**
     * Returns the current point balance
     * @return Points total
     */
    public int getPoints() {
        return personalPoints;
    }

    /**
     * Adds points to the current balance
     * @param points Points to add
     */
    public void addPoints(int points) {
        setPoints(getPoints() + points);
    }

    /**
     * Resets the balance to zero
     */
    public void resetPoints() {
        setPoints(0);
    }

    /**
     * Calculates a boost multiplier based on days since the resident's last
     * task completion
     * @param resident Resident to evaluate
     * @return Boost multiplier
     */
    public double calculateBoost(Resident resident) {
        double boost = 1.0;

        Date currentDate = new Date().today();
        Date lastCompletionDate = resident.getLatestTask();

        if (lastCompletionDate == null) {
            return 1.2;
        }

        long diff = currentDate.getNumOfDays() - lastCompletionDate.getNumOfDays();

        if (diff > 60) {
            boost += 0.8; 
        }else if (diff > 30) {
            boost += 0.4; 
        }else if (diff > 14) {
            boost += 0.3; 
        }else if (diff <= 14) {
            boost += 0.2;
        }

        return boost;
    }

    /**
     * Applies a boost to the stored points if the resident qualifies and does
     * not already have one active
     * @param resident Resident receiving boost
     */
    public void awardBoost(Resident resident) {
        if (!resident.getHasBoost()) {
            double boost = calculateBoost(resident);
            if (boost > 1.0) {
                personalPoints = (int) (personalPoints * boost);
                resident.setBoost(true);
            }
        }
    }

    /**
     * Compares a personal points object with another object
     * @param obj Object to compare
     * @return true if the objects are equal, otherwise false
     */
    public boolean equals(Object obj) {
        if (obj == null && obj.getClass() != this.getClass()) {
            return false;
        }

        PersonalPoints other = (PersonalPoints) obj;

        return this.personalPoints == other.personalPoints;
    }

    /**
     * Provides a String representation of the personal points balance
     * @return a formatted String with the current personal points
     */
    public String toString() {
        return "" + personalPoints;
    }
}
