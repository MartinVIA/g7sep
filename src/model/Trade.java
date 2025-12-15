package model;

import java.io.Serializable;

/**
 * The class for trades between residents, either point-based or offer-based
 */
public class Trade implements Serializable {

  private String name;
  private String description;
  private Resident trader;
  private Resident tradee;
  private int pointCost;
  private String tradeOffer;
  private boolean isComplete;

  /**
   * Creates a trade defined by points
   * 
   * @param name        Trade name
   * @param description Trade description
   * @param trader      Resident offering the trade
   * @param pointCost   Cost in personal points
   */
  public Trade(String name, String description, Resident trader, int pointCost) {
    this.name = name;
    this.description = description;
    this.trader = trader;
    tradee = null;
    this.pointCost = pointCost;
    isComplete = false;
  }

  /**
   * Creates a trade defined by an offer
   * 
   * @param name        Trade name
   * @param description Trade description
   * @param trader      Resident offering the trade
   * @param tradeOffer  String describing the offer
   */
  public Trade(String name, String description, Resident trader, String tradeOffer) {
    this.name = name;
    this.description = description;
    this.trader = trader;
    tradee = null;
    this.tradeOffer = tradeOffer;
    isComplete = false;
  }

  /**
   * Returns the name of the trade
   * 
   * @return Trade name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description of the trade
   * 
   * @return Trade description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the trader's first and last name
   * 
   * @return Trader's full name
   */
  public String getTraderName() {
    return trader.getFirstName() + " " + trader.getLastName();
  }

  /**
   * Returns the trader of the trade
   * 
   * @return Trader resident
   */
  public Resident getTrader() {
    return trader;
  }

  /**
   * Returns the trader's ID
   * 
   * @return Trader id
   */
  public int getTraderId() {
    return trader.getId();
  }
  // no getTradeeId because that is part of the View>

  /**
   * Returns the point cost of the trade
   * 
   * @return Point cost
   */
  public int getPointCost() {
    return pointCost;
  }

  /**
   * Returns the tradee's first and last name
   * 
   * @return Tradee's full name
   */
  public String getTradeeName() {
    return tradee.getFirstName() + " " + tradee.getLastName();
  }
  
  /**
   * Returns the tradee of the trade
   * 
   * @return Tradee resident
  */
  public Resident getTradee() {
   return tradee;
  }
  
  /**
   * Returns the name of the trade
   * 
   * @return Trade name
  */
  public String getStringName() {
    return name;
  }
  //this one repeats, we need review
  
  /**
   * Sets the name of the trade
   * 
   * @param name New trade name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of the trade
   * 
   * @param description New trade description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the point cost of the trade
   * 
   * @param pointCost New point cost
   */
  public void setPointCost(int pointCost) {
    this.pointCost = pointCost;
  }

  /**
   * Sets the trader of the trade
   * 
   * @param trader New trader resident
   */
  public void setTrader(Resident trader) {
    this.trader = trader;
  }

  /**
   * Sets the tradee of the trade
   * 
   * @param tradee New tradee resident
   */
  public void setTradee(Resident tradee) {
    this.tradee = tradee;
  }

  /**
   * Completes the trade by setting the tradee and marking completion
   * 
   * @param tradee Resident accepting the trade
   */
  public void completeTrade(Resident tradee) {
    setTradee(tradee);
    isComplete = true;
  }

  /**
   * Compares a trade object with another object
   * 
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }

    Trade other = (Trade) obj;
    return name.equals(other.name)
        && description.equals(other.description)
        && trader.equals(other.trader)
        && pointCost == other.pointCost;
  }

  /**
   * Returns a string representation of the trade
   * 
   *         
   * @return a formatted string with trade's name, description, trader, tradee, point cost and trade offer
   */
  public String toString() {
    return "Trade Name: " + name
        + ", Description: " + description
        + ", Trader: " + trader

        
        + ", Trade Offer: " + tradeOffer;
  }
}
