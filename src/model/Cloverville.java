package model;

/**
 * This class that manages the core components of the lists and clovervllles
 * system
 *
 * @author Victor Tonu
 * @author Adam Terelak
 */
public class Cloverville {

    private ResidentList allResidents;
    private TradeList tradeList;
    private TaskList TaskList;
    private GreenPoints greenPoints;
    private PersonalPoints personalPoints;

    /**
     * Creates an empty Cloverville instance with lists and sets the green
     * points goal of 100
     */
    public Cloverville() {
        allResidents = new ResidentList();
        tradeList = new TradeList();
        TaskList = new TaskList();
        greenPoints = new GreenPoints(100);
    }

    /**
     * Returns the managed residents list
     *
     * @return Resident list container
     */
    public ResidentList getResidentList() {
        return allResidents;
    }

    /**
     * Returns the managed trade list
     *
     * @return Trade list container
     */
    public TradeList getTradeList() {
        return tradeList;
    }

    /**
     * Returns the managed task list
     *
     * @return Task list container
     */
    public TaskList getTaskList() {
        return TaskList;

    }

    /**
     * Returns the shared green points
     *
     * @return Green points
     */
    public GreenPoints getGreenPoints() {
        return greenPoints;
    }

    /**
     * Sets green points to chosen amount
     *
     * @param resident The chosen resident
     */

    public void setGreenPoints(GreenPoints greenPoints) {
        this.greenPoints = greenPoints;
    }

    /**
     * Awards boost to selected resident, helper method that forwords the call
     * to personalPoints
     */
    public void awardBoost(Resident resident) {
        personalPoints.awardBoost(resident);
    }
}
