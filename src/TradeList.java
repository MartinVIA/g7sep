import java.util.*;

public class TradeList {
    private ArrayList<Trade> trades;

    public TradeList() {
        trades = new ArrayList<>();
    }

    public void addTrade(Trade t) {
        trades.add(t);
    }

    public void removetrade(Trade t) {
        trades.remove(t);
    }

    public Trade getTrade(int index) {
        return trades.get(index);
    }

    public ArrayList<Trade> getAllTrades() {
        return new ArrayList<>(trades);
    }
}
