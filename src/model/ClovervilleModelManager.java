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

public class ClovervilleModelManager {
  private Cloverville cloverville;

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

  public void awardPointsToResident(int residentId, int points) {
    Resident v = cloverville.getResidentList().getResidentById(residentId);
    if (v != null) {
      v.addPersonalPoints(points);
    }
  }
}