package model;

import java.util.*;

import java.io.Serializable;

/**
 * The class containing a list of Trade objects
 */
public class TradeList implements Serializable {
  private ArrayList<Trade> trades;

  /**
   * Creates an empty trade list
   */
  public TradeList() {
    trades = new ArrayList<Trade>();
  }

  /**
   * Returns number of trades
   * 
   * @return List size
   */
  public int size() {
    return trades.size();
  }

  /**
   * Adds a trade to the list
   * 
   * @param trade trade to add
   */
  public void addTrade(Trade trade) {
    trades.add(trade);
  }

  /**
   * Removes a trade if present
   * 
   * @param trade trade to remove
   */
  public void removeTrade(Trade trade) {
    if (trade != null) {
      trades.remove(trade);
    }
  }

  /**
   * Retrieves a trade by index
   * 
   * @param index Trade position
   * @return Trade object or null if out of range
   */
  public Trade getTrade(int index) {
    if (index >= 0 && index < trades.size()) {
      return trades.get(index);
    }
    return null;
  }

  /**
   * Returns a copy of trades
   * 
   * @return List copy
   */
  public ArrayList<Trade> getAllTrades() {
    return new ArrayList<Trade>(trades);
  }

  /**
   * Compares a trades list object with another object
   * 
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;

    TradeList other = (TradeList) obj;
    return this.trades.equals(other.trades);
  }

  /**
   * Returns a string representation of the trades list
   * 
   * @return a formatted String with all trades' name, description, trader,
   *         tradee, point cost and trade offer
   */
  public String toString() {
    String list = "";
    for (int i = 0; i < trades.size(); i++) {
      list += trades.get(i) + "\n";
    }
    return list;
  }
}