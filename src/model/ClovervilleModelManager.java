package model;

import java.util.List;
import model.ResidentList;
import model.Resident;
import model.Cloverville;
import model.PersonalPoints;
import model.Trade;
import model.TradeList;
import model.TasksList;
import model.Task;
import java.util.ArrayList;

public class ClovervilleModelManager {
  private Cloverville cloverville;

  private final List<Trade> trades = new ArrayList<>();

  public ClovervilleModelManager() {
    cloverville = new Cloverville();
  }

  public List<Resident> getAllResidents() {
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
    cloverville.getAllTasks().addTask(b);
  }

  public void addTrade(String name, String description, Resident trader, int pointCost) {
    trades.add(new Trade(name, description, trader, pointCost));
  }

  public void addTradeWithOffer(String name, String description, Resident trader, String tradeOffer) {
    trades.add(new Trade(name, description, trader, tradeOffer));
  }

  public ArrayList<Trade> getAllTrades() {
    return new ArrayList<>(trades);
  }

  public java.util.List<Task> getAllTasks() {
    return cloverville.getAllTasks().getTasks();
  }

  public void awardPointsToResident(int residentId, int points) {
    Resident v = cloverville.getResidentList().getResidentById(residentId);
    if (v != null) {
      v.addPersonalPoints(points);
    }
  }
}