package model;

public class Cloverville {

  private ResidentList allResidents;
  private TradeList tradeList;
  private TasksList tasksList;

  public Cloverville() {
    allResidents = new ResidentList();
    tradeList = new TradeList();
    tasksList = new TasksList();
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

}
