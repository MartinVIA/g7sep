package utils;

import java.io.*;
import java.util.ArrayList;
import model.Resident;
import model.ResidentList;
import model.Task;
import model.TaskList;
import model.Trade;
import model.TradeList;
import model.GreenPoints;

public class FileReader {
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

    public static ArrayList<Trade> readTradesFromBinary(String filePath) {
        TradeList tradeList = new TradeList();
        try {
            tradeList = (TradeList) MyFileHandler.readFromBinaryFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Trades file not found: " + filePath);
        } catch (Exception e) {
            System.err.println("Error reading Trades from binary: " + e.getMessage());
        }
        return tradeList.getAllTrades();
    }

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
