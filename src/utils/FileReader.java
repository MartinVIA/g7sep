package utils;

import java.io.*;
import model.ResidentList;
import model.TasksList;
import model.TradeList;

public class FileReader {
  public static void main(String[] args) {
    // Read ResidentList from file
    try {
      FileInputStream residentFileIn = new FileInputStream("personal_points.bin");
      ObjectInputStream readResidents = new ObjectInputStream(residentFileIn);
      try {
        ResidentList residentList = (ResidentList) readResidents.readObject();
        for (int i = 0; i < residentList.size(); i++) {
          System.out.println(residentList.getResidentByIndex(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readResidents.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }

    // Read TasksList from file
    try {
      FileInputStream tasksFileIn = new FileInputStream("tasks.bin");
      ObjectInputStream readTasks = new ObjectInputStream(tasksFileIn);
      try {
        TasksList tasksList = (TasksList) readTasks.readObject();
        for (int i = 0; i < tasksList.size(); i++) {
          System.out.println(tasksList.getTask(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readTasks.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }

    // Read TradeList from file
    try {
      FileInputStream tradeFileIn = new FileInputStream("trades.bin");
      ObjectInputStream readTrades = new ObjectInputStream(tradeFileIn);
      try {
        TradeList tradeList = (TradeList) readTrades.readObject();
        for (int i = 0; i < tradeList.size(); i++) {
          System.out.println(tradeList.getTrade(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readTrades.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }
}