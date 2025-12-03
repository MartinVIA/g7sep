package model;

public class Trade {
  private String name;
  private String description;
  private Resident trader;
  private Resident tradee;
  private int pointCost;
  private String tradeOffer;
  // private Date completeDate;
  private boolean isComplete;

  public Trade(String name, String description, Resident trader, int pointCost) {
    this.name = name;
    this.description = description;
    this.trader = trader;
    tradee = null;
    this.pointCost = pointCost;
    isComplete = false;
    // completeDate = null;
  }

  public Trade(String name, String description, Resident trader, String tradeOffer) {
    this.name = name;
    this.description = description;
    this.trader = trader;
    tradee = null;
    this.tradeOffer = tradeOffer;
    isComplete = false;
    // completeDate = null;
  }

  public String getTraderName() {
    return trader.getFirstName() + " " + trader.getLastName();
  }

  public Resident getTrader() {
    return trader;
  }

  public int getPointCost() {
    return pointCost;
  }

  public String getTradeOffer() {
    return tradeOffer;
  }

  public String getTradeeName() {
    return tradee.getFirstName() + " " + tradee.getLastName();
  }

  public Resident getTradee() {
    return tradee;
  }

  public String getStringName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  // public Date getCompleteDate() {
  // return completeDate;
  // }

  public void setTrader(Resident trader) {
    this.trader = trader;
  }

  public void setTradee(Resident tradee) {
    this.tradee = tradee;
  }

  public void completeTrade(Resident tradee) {
    setTradee(tradee);
    isComplete = true;
    // we dont fucking need it i guess
    // completeDate = completeDate.today().copy();
    // trader.setLatestTrade(completeDate.today().copy());
    // tradee.setLatestTrade(completeDate.today().copy());
  }

  public String toString() {
    return "Trade Name: " + name
        + ", Description: " + description
        + ", Trader: " + trader
        + ", Tradee: " + tradee
        + ", Point Cost: " + pointCost
        + ", Trade Offer: " + tradeOffer;
  }
}