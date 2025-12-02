package model;

import java.util.*;

public class TradeList {
    private ArrayList<Trade> trades;

    public TradeList() {
        trades = new ArrayList<Trade>();
    }

    public void addTrade(Trade t) {
        trades.add(t);
    }

    public void removetrade(Trade t) {
        trades.remove(t);
    }

    public Trade getTrade(int index) {
        return trades == null ? trades.get(index) : null;
    }

    public ArrayList<Trade> getAllTrades() {
        return new ArrayList<Trade>(trades);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        TradeList other = (TradeList) obj;
        return this.trades.equals(other.trades);
    }

    public String toString() {
        String list = "";
        for (int i = 0; i < trades.size(); i++) {
            list += trades.get(i) + "\n";
        }
        return list;
    }
}
