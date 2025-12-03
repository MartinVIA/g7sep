package model;

import java.util.ArrayList;

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

  public TradeList getAllTrades() {
    return tradeList;
  }

  public TasksList getAllTasks() {
    return tasksList;
  }

}