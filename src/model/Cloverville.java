package model;

public class Cloverville {

  private ResidentList allResidents;
  private TradeList tradeList;
  private TasksList tasksList;
  private GreenPoints greenPoints;

  public Cloverville() {
    allResidents = new ResidentList();
    tradeList = new TradeList();
    tasksList = new TasksList();
    greenPoints = new GreenPoints(100); // default goal
  }

  public ResidentList getResidentList() {
    return allResidents;
  }

  public TradeList getTradeList() {
    return tradeList;
  }

  public TasksList getTaskList() {
    return tasksList;

  }

  public GreenPoints getGreenPoints() {
    return greenPoints;
  }

}
