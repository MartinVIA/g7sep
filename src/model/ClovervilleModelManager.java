package model;

import java.util.List;
import model.VillagerList;
import model.Villager;
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

    public List<Villager> getAllVillagers() {
        return cloverville.getVillagerList().getAllVillagers();
    }

    public void addVillager(String name) {
        int nextId = cloverville.getVillagerList().getAllVillagers().size() + 1;
        Villager v = new Villager(nextId, name, false, new PersonalPoints(0, 0, 0));
        cloverville.getVillagerList().addVillager(v);
    }

    public void awardPointsToVillager(int villagerId, int points) {
        Villager v = cloverville.getVillagerList().getByID(villagerId);
        if (v != null) {
            v.getPersonalPoints().addPoints(points);
        }
    }
}