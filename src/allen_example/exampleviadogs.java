package allen_example;

import model.*;
import model.ResidentList;
import model.TasksList;
import model.TradeList;

import java.io.*;

public class FileReader {
  public static void main(String[] args) {
    try {
      FileInputStream residentFileIn = new FileInputStream("customers.bin");
      ObjectInputStream readResidents = new ObjectInputStream(residentFileIn);
      try {
        ResidentList residentList = (ResidentList) readResidents.readObject();
        for (int i = 0; i < residentList.size(); i++) {
          System.out.println(residentList.get(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readCustomers.close();
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

    try {
      FileInputStream tasksFileIn = new FileInputStream("tasks.bin");
      ObjectInputStream readTasks = new ObjectInputStream(tasksFileIn);
      try {
        KennelList kennelList = (KennelList) readBookings.readObject();
        for (int i = 0; i < kennelList.size(); i++) {
          System.out.println(kennelList.get(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readBookings.close();
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

    try {
      FileInputStream purchasesFileIn = new FileInputStream("purchases.bin");
      ObjectInputStream readPurchases = new ObjectInputStream(purchasesFileIn);
      try {
        PurchaseList purchaseList = (PurchaseList) readPurchases.readObject();
        for (int i = 0; i < purchaseList.size(); i++) {
          System.out.println(purchaseList.get(i));
        }
      } catch (EOFException eof) {
        System.out.println("End of file");
      }
      readPurchases.close();
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
}