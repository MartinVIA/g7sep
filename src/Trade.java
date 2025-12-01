import java.util.*;

public class Trade {
    private String name;
    private String description;
    private Villager Trader;
    private Villager Tradee;
    private int pointCost;
    private String tradeOffer;
    private Date completeDate;
    private boolean isComplete;
    private ArrayList<Villager> villagers;

    public Trade(String name, String description, Villager trader, int pointCost) {
        this.name = name;
        this.description = description;
        this.Trader = Trader;
        Tradee = null;
        this.pointCost = pointCost;
        isComplete = false;
        completeDate = null;
        villagers = new ArrayList<>();
    }

    public Trade(String name, String description, Villager trader, String tradeOffer) {
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

    public Villager getTrader() {
        return Trader;
    }

    public String getTradeeName() {
        return Tradee.getName();
    }

    public void setTradee() {
        this.Tradee = Tradee;
    }

    public Villager getTradee() {
        return Tradee;
    }

    public String getStringName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void isComplete() {
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