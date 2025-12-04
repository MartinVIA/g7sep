package utils;

import model.Trade;
import model.TradeList;

import java.io.*;
import java.util.ArrayList;

public class TradeXML {
    private TradeList tradeList;
    private ArrayList<Trade> trades;

    public TradeXML() {
        trades = new ArrayList<>();
        readTrades();
        writeTrades();
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void readTrades() {
        try (FileInputStream fileIn = new FileInputStream("trades.bin");
                ObjectInputStream read = new ObjectInputStream(fileIn)) {

            tradeList = (TradeList) read.readObject();

            // Populate the trades ArrayList
            trades = tradeList.getAllTrades();

        } catch (FileNotFoundException e) {
            System.out.println("File not found, or could not be opened");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IO Error reading file");
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void writeTrades() {
        try (FileOutputStream fileOut = new FileOutputStream("trades.xml");
                PrintWriter write = new PrintWriter(fileOut)) {

            write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            write.println("<trades>");
            for (Trade trade : trades) {
                write.println("<trade>");
                write.println("<name>" + trade.getStringName() + "</name>");
                write.println("<description>" + trade.getDescription() + "</description>");
                write.println("<pointCost>" + trade.getPointCost() + "</pointCost>");
                write.println("<tradeOffer>" + trade.getTradeOffer() + "</tradeOffer>");

                if (trade.getTrader() != null) {
                    write.println("<trader>" + trade.getTraderName() + "</trader>");
                } else {
                    write.println("<trader>null</trader>");
                }

                if (trade.getTradee() != null) {
                    write.println("<tradee>" + trade.getTradeeName() + "</tradee>");
                } else {
                    write.println("<tradee>null</tradee>");
                }

                write.println("</trade>");
            }
            write.println("</trades>");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
