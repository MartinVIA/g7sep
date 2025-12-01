import java.util.*;

public class VillagerList {
    private final List<Villager> villagers;

    public VillagerList() {
        villagers = new ArrayList<>();
    }

    public void addVillager(Villager v) {

        villagers.add(v);
    }

    public void removeVillager(Villager v) {
        villagers.remove(v);
    }

    public Villager getVillager(int index) {
        return villagers.get(index);
    }

    public Villager getByID(int id) {
        for (int i = 0; i < villagers.size(); i++) {
            if (villagers.get(i).getId() == id) {
                return villagers.get(i);
            }
        }
        return null;
    }

    public List<Villager> getAllVillagers() {
        return new ArrayList<>(villagers);
    }

}
