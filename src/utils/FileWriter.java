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

public class FileWriter {
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
                        System.err.println("Error reading residents from binary: " + e.getMessage());
                }
        }

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
