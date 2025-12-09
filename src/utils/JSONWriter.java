package utils;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import model.*;

public class JSONWriter {
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
                writer.write("    \"traderName\": \"" + escapeJson(trade.getTraderName()) + "\"\n");
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
}
