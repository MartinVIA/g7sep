package model;

import java.util.*;

public class VillagerList {
    private final List<Villager> villagers;

    public VillagerList() {
        villagers = new ArrayList<Villager>();
    }

    public void addVillager(Villager v) {
        villagers.add(v);
    }

    public void removeVillager(Villager v) {
        villagers.remove(v);
    }

    public Villager getVillager(int index) {
        if (index < villagers.size()) {
            return villagers.get(index);
        } else
            return null;
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

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        VillagerList other = (VillagerList) obj;
        return villagers.equals(other.villagers);
    }

    public String toString() {
        String list = "";
        for (int i = 0; i < villagers.size(); i++) {
            list += villagers.get(i) + "\n";
        }
        return list;
    }
}
