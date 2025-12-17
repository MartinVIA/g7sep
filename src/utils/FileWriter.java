package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import model.GreenPoints;
import model.Resident;
import model.ResidentList;
import model.Task;
import model.TaskList;
import model.Trade;
import model.TradeList;

/**
 * Utility class responsible for writing application data to binary files.
 *
 * This class provides static helper methods for persisting residents,
 * tasks, trades, and community green points. Data is written in binary
 * format using {@link MyFileHandler}.
 */
public class FileWriter {

  /**
   * Saves a list of residents to a binary file.
   *
   * The provided list of residents is wrapped in a {@link ResidentList}
   * before being written to the file.
   *
   * @param residents the list of residents to save
   * @param filePath the path to the binary residents file
   */
  public static void saveResidentsToBinary(ArrayList<Resident> residents, String filePath) {
    try {
      ResidentList list = new ResidentList();
      for (Resident r : residents) {
        list.addResident(r);
      }
      MyFileHandler.writeToBinaryFile(filePath, list);
    } catch (FileNotFoundException e) {
      System.out.println("Residents file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error saving residents to binary: " + e.getMessage());
    }
  }

  /**
   * Saves a list of tasks to a binary file.
   *
   * The provided list of tasks is wrapped in a {@link TaskList}
   * before being written to the file.
   *
   * @param tasks the list of tasks to save
   * @param filePath the path to the binary tasks file
   */
  public static void saveTasksToBinary(ArrayList<Task> tasks, String filePath) {
    try {
      TaskList list = new TaskList();
      for (Task t : tasks) {
        list.addTask(t);
      }
      MyFileHandler.writeToBinaryFile(filePath, list);
    } catch (FileNotFoundException e) {
      System.out.println("Tasks file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error saving tasks to binary: " + e.getMessage());
    }
  }

  /**
   * Saves a list of trades to a binary file.
   *
   * The provided list of trades is wrapped in a {@link TradeList}
   * before being written to the file.
   *
   * @param trades the list of trades to save
   * @param filePath the path to the binary trades file
   */
  public static void saveTradesToBinary(ArrayList<Trade> trades, String filePath) {
    try {
      TradeList list = new TradeList();
      for (Trade t : trades) {
        list.addTrade(t);
      }
      MyFileHandler.writeToBinaryFile(filePath, list);
    } catch (FileNotFoundException e) {
      System.out.println("Trades file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error saving trades to binary: " + e.getMessage());
    }
  }

  /**
   * Saves the community green points to a binary file.
   *
   * @param gp the {@link GreenPoints} object to save
   * @param filePath the path to the binary green points file
   */
  public static void saveGreenPointsToBinary(GreenPoints gp, String filePath) {
    try {
      MyFileHandler.writeToBinaryFile(filePath, gp);
    } catch (FileNotFoundException e) {
      System.out.println("GreenPoints file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error saving GreenPoints to binary: " + e.getMessage());
    }
  }
}
