package model;

import java.util.ArrayList;

/**
 * The class that handles all operations for the Cloverville system
 */
public class ClovervilleModelManager {

    private Cloverville cloverville;

    /**
     * Creates a manager with a fresh Cloverville instance
     */
    public ClovervilleModelManager() {
        cloverville = new Cloverville();
    }

    /**
     * Returns all residents
     * 
     * @return List of residents
     */
    public ArrayList<Resident> getAllResidents() {
        return cloverville.getResidentList().getAllResidents();
    }

    /**
     * Adds a new resident with an auto-incremented ID
     * 
     * @param firstName Resident first name
     * @param lastName  Resident last name
     */
    public void addResident(String firstName, String lastName) {
        int nextId = cloverville.getResidentList().getAllResidents().size() + 1;
        Resident v = new Resident(nextId, firstName, lastName);
        cloverville.getResidentList().addResident(v);
    }

    /**
     * Returns all tasks
     * 
     * @return List of tasks
     */
    public ArrayList<Task> getTaskList() {
        return cloverville.getTaskList().getAllTasks();
    }

    /**
     * Adds a new task of the appropriate subtype based on its type label
     * 
     * @param name        Task name
     * @param type        Task type
     * @param points      Points award
     * @param description Description of the task
     */
    public void addTask(String name, String type, int points, String description) {
        Task task;

        if (type != null && type.toLowerCase().contains("green")) {
            task = new GreenActions(name, type, points);
        } else {
            task = new CommunityTasks(name, type, points);
        }
        task.setDescription(description);
        cloverville.getTaskList().addTask(task);
    }

    /**
     * Updates attributes of an existing task
     * 
     * @param task        Task to update
     * @param name        New name
     * @param description New description
     * @param type        New type label
     * @param points      New points value
     */
    public void updateTask(Task task, String name, String description, String type, int points) {
        task.setName(name);
        task.setDescription(description);
        task.setType(type);
        task.setPoints(points);
    }

    /**
     * Returns all trades
     * 
     * @return List of trades
     */
    public ArrayList<Trade> getTradeList() {
        // return new ArrayList<Trade>(trades);
        return cloverville.getTradeList().getAllTrades();
    }

    /**
     * Adds a trade that awards points
     * 
     * @param name        Trade title
     * @param description Trade description
     * @param trader      Resident offering the trade
     * @param pointCost   Cost in personal points
     */
    public void addTrade(String name, String description, Resident trader, int pointCost) {
        cloverville.getTradeList().addTrade(new Trade(name, description, trader, pointCost));
    }

    /**
     * Adds a trade that awards an other item
     * 
     * @param name        Trade title
     * @param description Trade description
     * @param trader      Resident offering the trade
     * @param tradeOffer  String describing the offer
     */
    // public void addTradeWithOffer(String name, String description, Resident
    // trader, String tradeOffer) {
    // cloverville.getTradeList().addTrade(new Trade(name, description, trader,
    // tradeOffer));
    // }

    /**
     * Awards personal points to a resident ID
     * 
     * @param residentId Resident ID
     * @param points     Points to add
     */
    public void awardPointsToResident(int residentId, int points) {
        Resident v = cloverville.getResidentList().getResidentById(residentId);
        if (v != null) {
            v.addPersonalPoints(points);
        }
    }

    public void awardBoostToResident(int residentId) {
        Resident v = cloverville.getResidentList().getResidentById(residentId);
        cloverville.awardBoost(v);
    }

    public void resetAllPersonalPoints() {
        for (Resident resident : getAllResidents()) {
            resident.resetPersonalPoints();
        }
    }

    /**
     * Adds green points toward the community goal
     * 
     * @param points Points to add
     */
    public void addGreenPoints(int points) {
        cloverville.getGreenPoints().addPoints(points);
    }

    /**
     * Gets current green points
     * 
     * @return Green points
     */
    public int getGreenPoints() {
        return cloverville.getGreenPoints().getPoints();
    }

    public GreenPoints getGreenPointsObject() {
        return cloverville.getGreenPoints();
    }

    public String getCommunityReward() {
        return cloverville.getGreenPoints().getCommunityReward();
    }

    /**
     * Gets current green points goal
     * 
     * @return Goal value
     */
    public int getGreenPointsGoal() {
        return cloverville.getGreenPoints().getGoal();
    }

    public void setGreenPointsGoal(int goal) {
        cloverville.getGreenPoints().setGoal(goal);
    }

    public void importResidents(ArrayList<Resident> residents) {
        for (Resident resident : residents) {
            cloverville.getResidentList().addResident(resident);
        }
    }

    public void importTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            cloverville.getTaskList().addTask(task);
        }
    }

    public void importTrades(ArrayList<Trade> trades) {
        for (Trade trade : trades) {
            cloverville.getTradeList().addTrade(trade);
        }
    }

    public void importGreenPoints(GreenPoints gp) {
        if (gp != null) {
            cloverville.setGreenPoints(gp);
        }
    }

    public void removeTask(Task task) {
        cloverville.getTaskList().removeTask(task);
    }

    public void removeTrade(Trade trade) {
        cloverville.getTradeList().removeTrade(trade);
    }

    public void removeResident(Resident resident) {
        cloverville.getResidentList().removeResident(resident);
    }
}

