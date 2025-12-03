package utils;

import java.util.*;
import model.ResidentList;
import model.TasksList;
import model.TradeList;

import java.io.*;

public class FileReader {
  public static void main(String[] args) {
    // Read ResidentList from file
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
  }
}