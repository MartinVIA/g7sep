package model;

import java.util.*;

public class Trade {
    private String name;
    private String description;
    private Resident Trader;
    private Resident Tradee;
    private int pointCost;
    private String tradeOffer;
    private Date completeDate;
    private boolean isComplete;

    public Trade(String name, String description, Resident trader, int pointCost) {
        this.name = name;
        this.description = description;
        this.Trader = Trader;
        Tradee = null;
        this.pointCost = pointCost;
        isComplete = false;
        completeDate = null;
    }

    public Trade(String name, String description, Resident trader, String tradeOffer) {
        this.name = name;
        this.description = description;
        this.Trader = Trader;
        Tradee = null;
        this.tradeOffer = tradeOffer;
        isComplete = false;
        completeDate = null;
    }

    public void setTrader() {
        this.Trader = Trader;
    }

    public String getTraderName() {
        return Trader.getName();
    }

    public Resident getTrader() {
        return Trader;
    }

    public String getTradeeName() {
        return Tradee.getName();
    }

    public void setTradee() {
        this.Tradee = Tradee;
    }

    public Resident getTradee() {
        return Tradee;
    }

    public String getStringName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void CompleteTrade() {
        if (Tradee != null) {
            isComplete = true;
            completeDate = completeDate.today().copy();
            Trader.setLatestTrade(completeDate.today().copy());
            Tradee.setLatestTrade(completeDate.today().copy());
        }
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public String toString() {
        return "Trade Name: " + name + ", Description: " + description + ", Trader: " + Trader + ", Tradee: " + Tradee
                + ", Point Cost: " + pointCost + ", Trade Offer: " + tradeOffer;
    }
}