package utils;

import java.io.*;
import java.util.*;
import model.*;

public class JSONWriter {

    public static void saveResidentsToJSON(ArrayList<Resident> residents, String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < residents.size(); i++) {
            Resident r = residents.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": " + r.getId() + ",\n");
            sb.append("    \"firstName\": \"" + r.getFirstName() + "\",\n");
            sb.append("    \"lastName\": \"" + r.getLastName() + "\",\n");
            sb.append("    \"personalPoints\": " + r.getPersonalPoints() + ",\n");
            sb.append("    \"hasBoost\": " + r.getHasBoost() + "\n");
            sb.append("  }");
            if (i < residents.size() - 1)
                sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        writeFile(sb.toString(), filePath);
    }

    public static void savePersonalPointsToJSON(ArrayList<Resident> residents, String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (int i = 0; i < residents.size(); i++) {
            Resident r = residents.get(i);
            sb.append("  \"id\": " + r.getId() + ",\n");
            sb.append("  \"personalPoints\": " + r.getPersonalPoints() + "\n");
            if (i < residents.size() - 1)
                sb.append(",");
            sb.append("\n");
        }
        sb.append("}");
        writeFile(sb.toString(), filePath);
    }

    public static void saveTasksToJSON(ArrayList<Task> tasks, String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("  {\n");
            sb.append("    \"name\": \"" + escape(t.getName()) + "\",\n");
            sb.append("    \"description\": \"" + escape(t.getDescription()) + "\",\n");
            sb.append("    \"type\": \"" + escape(t.getType()) + "\",\n");
            sb.append("    \"points\": " + t.getPoints() + "\n");
            sb.append("  }");
            if (i < tasks.size() - 1)
                sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        writeFile(sb.toString(), filePath);
    }

    public static void saveTradesToJSON(ArrayList<Trade> trades, String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < trades.size(); i++) {
            Trade t = trades.get(i);
            sb.append("  {\n");
            sb.append("    \"name\": \"" + escape(t.getStringName()) + "\",\n");
            sb.append("    \"description\": \"" + escape(t.getDescription()) + "\",\n");
            sb.append("    \"pointCost\": " + t.getPointCost() + ",\n");
            sb.append("    \"traderName\": \"" + t.getTrader().getFirstName() + " " + t.getTrader().getLastName()
                    + "\"\n");
            sb.append("  }");
            if (i < trades.size() - 1)
                sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        writeFile(sb.toString(), filePath);
    }

    public static void saveGreenPointsToJSON(GreenPoints gp, String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"greenPoints\": " + gp.getPoints() + ",\n");
        sb.append("  \"pointGoal\": " + gp.getGoal() + "\n");
        sb.append("}");
        writeFile(sb.toString(), filePath);
    }

    private static void writeFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Saved JSON to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving JSON: " + e.getMessage());
        }
    }

    private static String escape(String str) {
        if (str == null)
            return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
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
