package utils;

import java.io.*;
import java.util.*;
import model.*;

public class FileWriter {

        // Save tasks to JSON
        public static void saveTasksToJSON(List<Task> tasks, String filePath) {
                try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
                        writer.write("[\n");
                        for (int i = 0; i < tasks.size(); i++) {
                                Task task = tasks.get(i);
                                writer.write("  {\n");
                                writer.write("    \"name\": \"" + escapeJson(task.getName()) + "\",\n");
                                writer.write("    \"type\": \"" + escapeJson(task.getType()) + "\",\n");
                                writer.write("    \"points\": " + task.getPoints() + "\n");
                                writer.write("  }");
                                if (i < tasks.size() - 1) {
                                        writer.write(",");
                                }
                                writer.write("\n");
                        }
                        writer.write("]\n");
                        System.out.println("Successfully saved " + tasks.size() + " tasks to " + filePath);
                } catch (IOException e) {
                        System.err.println("Error saving tasks to JSON: " + e.getMessage());
                        e.printStackTrace();
                }
        }

        // Save trades to JSON
        public static void saveTradesToJSON(List<Trade> trades, String filePath) {
                try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
                        writer.write("[\n");
                        for (int i = 0; i < trades.size(); i++) {
                                Trade trade = trades.get(i);
                                writer.write("  {\n");
                                writer.write("    \"name\": \"" + escapeJson(trade.getStringName()) + "\",\n");
                                writer.write("    \"description\": \"" + escapeJson(trade.getDescription()) + "\",\n");
                                writer.write("    \"pointCost\": " + trade.getPointCost() + ",\n");
                                String traderName = trade.getTraderName() != null ? trade.getTraderName() : "Unknown";
                                writer.write("    \"traderName\": \"" + escapeJson(traderName) + "\"\n");
                                writer.write("  }");
                                if (i < trades.size() - 1) {
                                        writer.write(",");
                                }
                                writer.write("\n");
                        }
                        writer.write("]\n");
                        System.out.println("Successfully saved " + trades.size() + " trades to " + filePath);
                } catch (IOException e) {
                        System.err.println("Error saving trades to JSON: " + e.getMessage());
                        e.printStackTrace();
                }
        }

        // Helper method to escape JSON strings
        private static String escapeJson(String str) {
                if (str == null)
                        return "";
                return str.replace("\\", "\\\\")
                                .replace("\"", "\\\"")
                                .replace("\n", "\\n")
                                .replace("\r", "\\r")
                                .replace("\t", "\\t");
        }

        public static void main(String[] args) {
                // write residentlist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("customers.bin"))) {
                        ResidentList residentList = new ResidentList();
                        ArrayList<Resident> residents = new ArrayList<>();
                        residents.add(new Resident(1, "Green", "Bob", 9999999));
                        residents.add(new Resident(2, "Green", "Smith", 0));
                        residents.add(new Resident(3, "Charlie", "Brown", 0));
                        residents.add(new Resident(4, "Diana", "White", 0));

                        for (Resident resident : residents) {
                                residentList.addResident(resident);
                        }

                        out.writeObject(residentList);
                        System.out.println("Success writing residents");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write taskslist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("tasks.bin"))) {
                        TasksList tasksList = new TasksList();

                        tasksList.addTask(new GreenActions("Recycle paper", "green_action", 10));
                        tasksList.addTask(new GreenActions("Plant a tree", "green_action", 50));
                        tasksList.addTask(new CommunityTasks("Park cleanup", "community_task", 20));
                        tasksList.addTask(new CommunityTasks("Help neighbor", "community_task", 15));

                        out.writeObject(tasksList);
                        System.out.println("Success writing tasks");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // write tradelist
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("trades.bin"))) {
                        TradeList tradeList = new TradeList();
                        ArrayList<Trade> trades = new ArrayList<>();
                        trades.add(new Trade("Pot for 5 apples", "xd", 20));

                        for (Trade trade : trades) {
                                tradeList.addTrade(trade);
                        }

                        out.writeObject(tradeList);
                        System.out.println("Success writing trades");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}
