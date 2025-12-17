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
 * Utility class responsible for reading persisted application data
 * from binary files and converting them into usable model objects.
 * The class provides static helper methods for loading residents,
 * tasks, trades, and community green points at application startup.
 * All file handling is delegated to MyFileHandler.
 * @author Leon de Kuijper
 * @author Adam Terelak
 * @author Martin Chavez
 */
public class FileReader {

  /**
   * Reads a list of residents from a binary file.
   * The binary file is expected to contain a ResidentList
   * object. If the file does not exist or cannot be read, an empty
   * list is returned.
   * @param filePath the path to the binary residents file
   * @return an ArrayList containing all loaded residents
   */
  public static ArrayList<Resident> readResidentsFromBinary(String filePath) {
    ResidentList residentList = new ResidentList();
    try {
      residentList = (ResidentList) MyFileHandler.readFromBinaryFile(filePath);
    } catch (FileNotFoundException e) {
      System.out.println("Residents file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error reading residents from binary: " + e.getMessage());
    }
    return residentList.getAllResidents();
  }

  /**
   * Reads a list of tasks from a binary file.
   * The binary file is expected to contain a TaskList
   * object. If the file does not exist or cannot be read,
   * an empty list is returned.
   * @param filePath the path to the binary tasks file
   * @return an ArrayList containing all loaded tasks
   */
  public static ArrayList<Task> readTasksFromBinary(String filePath) {
    TaskList taskList = new TaskList();
    try {
      taskList = (TaskList) MyFileHandler.readFromBinaryFile(filePath);
    } catch (FileNotFoundException e) {
      System.out.println("Tasks file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error reading tasks from binary: " + e.getMessage());
    }
    return taskList.getAllTasks();
  }

  /**
   * Reads a list of trades from a binary file.
   * The binary file is expected to contain a TradeList
   * object. If the file does not exist or cannot be read,
   * an empty list is returned.
   * @param filePath the path to the binary trades file
   * @return an ArrayList containing all loaded trades
   */
  public static ArrayList<Trade> readTradesFromBinary(String filePath) {
    TradeList tradeList = new TradeList();
    try {
      tradeList = (TradeList) MyFileHandler.readFromBinaryFile(filePath);
    } catch (FileNotFoundException e) {
      System.out.println("Trades file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error reading trades from binary: " + e.getMessage());
    }
    return tradeList.getAllTrades();
  }

  /**
   * Reads community green points from a binary file.
   * The binary file is expected to contain a GreenPoints
   * object. If the file does not exist or cannot be read,
   * null is returned.
   * @param filePath the path to the binary green points file
   * @return a GreenPoints object, or null if loading fails
   */
  public static GreenPoints readGreenPointsFromBinary(String filePath) {
    GreenPoints greenPoints = null;
    try {
      greenPoints = (GreenPoints) MyFileHandler.readFromBinaryFile(filePath);
    } catch (FileNotFoundException e) {
      System.out.println("GreenPoints file not found: " + filePath);
    } catch (Exception e) {
      System.err.println("Error reading GreenPoints from binary: " + e.getMessage());
    }
    return greenPoints;
  }
}
