package model;

import java.util.ArrayList;

public class Cloverville {

    private VillagerList allVillagers;
    private TradeList tradeList;
    private TasksList tasksList;

    public Cloverville() {
        allVillagers = new VillagerList();
        tradeList = new TradeList();
        tasksList = new TasksList();
    }

    public VillagerList getVillagerList() {
        return allVillagers;
    }

    public TradeList getAllTrades() {
        return tradeList;
    }

    public TasksList getAllTasks() {
        return tasksList;
    }

}
