package model;

/**
 * The class that manages the core components of the Cloverville
 */
public class Cloverville {

  private ResidentList allResidents;
  private TradeList tradeList;
  private TasksList tasksList;
  private GreenPoints greenPoints;

  /**
   * Creates an empty Cloverville instance with default lists
   * and a default green points goal (100)
   */
  public Cloverville() {
    allResidents = new ResidentList();
    tradeList = new TradeList();
    tasksList = new TasksList();
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
  public TasksList getTaskList() {
    return tasksList;

  }

  /**
   * Returns the shared green points tracker
   * 
   * @return Green points state
   */
  public GreenPoints getGreenPoints() {
    return greenPoints;
  }

}
