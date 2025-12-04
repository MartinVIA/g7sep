package model;

import java.util.ArrayList;
import java.util.List;

public class ClovervilleModelManager {

  private Cloverville cloverville;

  private final List<Trade> trades = new ArrayList<>();

  public ClovervilleModelManager() {
    cloverville = new Cloverville();
  }
  

  public ArrayList<Resident> getAllResidents() {
    return cloverville.getResidentList().getAllResidents();
  }

  public void addResident(String firstName, String lastName) {
    int nextId = cloverville.getResidentList().getAllResidents().size() + 1;
    Resident v = new Resident(nextId, firstName, lastName);
    cloverville.getResidentList().addResident(v);
  }

  public void addTask(String Name, String Type) {
    addTask(Name, Type, 0);
  }

  public void addTask(String name, String type, int points) {
    Task b;
    if (type != null && type.toLowerCase().contains("green")) {
      b = new GreenActions(name, type, points);
    } else {
      b = new CommunityTasks(name, type, points);
    }
    cloverville.getTaskList().addTask(b);
  }

  public ArrayList<Trade> getTradeList() {
    // return new ArrayList<Trade>(trades);
    return cloverville.getTradeList().getAllTrades();
  }

  public void addTrade(String name, String description, Resident trader, int pointCost) {
    cloverville.getTradeList().addTrade(new Trade(name, description, trader, pointCost));
  }

  public void addTradeWithOffer(String name, String description, Resident trader, String tradeOffer) {
    cloverville.getTradeList().addTrade(new Trade(name, description, trader, tradeOffer));
  }

  public ArrayList<Task> getTaskList() {
    return cloverville.getTaskList().getAllTasks();
  }

  public void awardPointsToResident(int residentId, int points) {
    Resident v = cloverville.getResidentList().getResidentById(residentId);
    if (v != null) {
      v.addPersonalPoints(points);
    }
  }
}
