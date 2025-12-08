package utils;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import model.*;

public class JSONReader {
    public static List<Resident> readResidentsFromJSON(String filePath) {
        List<Resident> residents = new ArrayList<>();
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p))
                return residents;
            String content = Files.readString(p, StandardCharsets.UTF_8).trim();
            if (content.isEmpty())
                return residents;
            // Expecting an array of objects: [{"id":1,"firstName":"A","lastName":"B"},...]
            // Simple parser: split objects by '},{' after trimming brackets
            if (content.startsWith("["))
                content = content.substring(1);
            if (content.endsWith("]"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return residents;
            // Split but account for single object
            List<String> objects = splitTopLevelObjects(content);
            for (String obj : objects) {
                Map<String, String> map = parseJsonObject(obj);
                String idStr = map.get("id");
                String first = map.getOrDefault("firstName", "");
                String last = map.getOrDefault("lastName", "");
                try {
                    int id = Integer.parseInt(idStr.trim());
                    // Use constructor: id, lastName, firstName, points=0
                    Resident r = new Resident(id, last, first, 0);
                    residents.add(r);
                } catch (Exception e) {
                    // skip invalid
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading residents JSON: " + e.getMessage());
        }
        return residents;
    }

    public static Map<Integer, Integer> readPersonalPointsFromJSON(String filePath) {
        Map<Integer, Integer> map = new HashMap<>();
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p))
                return map;
            String content = Files.readString(p, StandardCharsets.UTF_8).trim();
            if (content.isEmpty())
                return map;
            // Expecting object: {"1":250,"2":100}
            if (content.startsWith("{"))
                content = content.substring(1);
            if (content.endsWith("}"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return map;
            // Split by top-level commas
            String[] parts = content.split(",");
            for (String part : parts) {
                String[] kv = part.split(":", 2);
                if (kv.length != 2)
                    continue;
                String key = kv[0].trim();
                String val = kv[1].trim();
                // remove quotes if present
                if (key.startsWith("\"") && key.endsWith("\""))
                    key = key.substring(1, key.length() - 1);
                try {
                    int id = Integer.parseInt(key);
                    int points = Integer.parseInt(val.replaceAll("\"", ""));
                    map.put(id, points);
                } catch (NumberFormatException e) {
                    // skip
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading personal points JSON: " + e.getMessage());
        }
        return map;
    }

    public static List<Task> readTasksFromJSON(String filePath) {
        List<Task> tasks = new ArrayList<>();
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p))
                return tasks;
            String content = Files.readString(p, StandardCharsets.UTF_8).trim();
            if (content.isEmpty())
                return tasks;
            // Expecting an array of objects: [{"name":"...","type":"...","points":10},...]
            if (content.startsWith("["))
                content = content.substring(1);
            if (content.endsWith("]"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return tasks;
            List<String> objects = splitTopLevelObjects(content);
            for (String obj : objects) {
                Map<String, String> map = parseJsonObject(obj);
                String name = map.getOrDefault("name", "");
                String type = map.getOrDefault("type", "");
                String pointsStr = map.getOrDefault("points", "0");

                // Skip empty tasks
                if (name.isEmpty() || type.isEmpty()) {
                    continue;
                }

                try {
                    int points = Integer.parseInt(pointsStr.trim());
                    // Create appropriate task type based on type field
                    Task task;
                    if (type.equalsIgnoreCase("green_action")) {
                        task = new GreenActions(name, type, points);
                    } else {
                        task = new CommunityTasks(name, type, points);
                    }
                    tasks.add(task);
                } catch (Exception e) {
                    System.err.println("Error parsing task: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading tasks JSON: " + e.getMessage());
        }
        return tasks;
    }

    public static List<Trade> readTradesFromJSON(String filePath) {
        List<Trade> trades = new ArrayList<>();
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p))
                return trades;
            String content = Files.readString(p, StandardCharsets.UTF_8).trim();
            if (content.isEmpty())
                return trades;
            // Expecting an array of objects:
            // [{"name":"...","description":"...","pointCost":10},...]
            if (content.startsWith("["))
                content = content.substring(1);
            if (content.endsWith("]"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return trades;
            List<String> objects = splitTopLevelObjects(content);
            for (String obj : objects) {
                Map<String, String> map = parseJsonObject(obj);
                String name = map.getOrDefault("name", "");
                String description = map.getOrDefault("description", "");
                String pointCostStr = map.getOrDefault("pointCost", "0");

                // Skip empty trades
                if (name.isEmpty()) {
                    continue;
                }

                try {
                    int pointCost = Integer.parseInt(pointCostStr.trim());
                    Trade trade = new Trade(name, description, pointCost);
                    trades.add(trade);
                } catch (Exception e) {
                    System.err.println("Error parsing trade: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading trades JSON: " + e.getMessage());
        }
        return trades;
    }

    private static List<String> splitTopLevelObjects(String content) {
        List<String> objs = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            cur.append(c);
            if (c == '{')
                depth++;
            else if (c == '}')
                depth--;
            if (depth == 0 && cur.length() > 0) {
                String s = cur.toString().trim();
                if (s.startsWith(","))
                    s = s.substring(1).trim();
                objs.add(s);
                cur.setLength(0);
            }
        }
        if (cur.length() > 0) {
            String s = cur.toString().trim();
            if (!s.isEmpty())
                objs.add(s);
        }
        return objs;
    }

    private static Map<String, String> parseJsonObject(String obj) {
        Map<String, String> map = new HashMap<>();
        String s = obj.trim();
        if (s.startsWith("{"))
            s = s.substring(1);
        if (s.endsWith("}"))
            s = s.substring(0, s.length() - 1);
        // split by commas not inside quotes
        List<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '"')
                inQuotes = !inQuotes;
            if (c == ',' && !inQuotes) {
                parts.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        if (cur.length() > 0)
            parts.add(cur.toString());
        for (String part : parts) {
            String[] kv = part.split(":", 2);
            if (kv.length != 2)
                continue;
            String key = kv[0].trim();
            String val = kv[1].trim();
            if (key.startsWith("\"") && key.endsWith("\""))
                key = key.substring(1, key.length() - 1);
            if (val.startsWith("\"") && val.endsWith("\""))
                val = val.substring(1, val.length() - 1);
            map.put(key, val);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println("Testing JSONReader...");
        List<Resident> residents = readResidentsFromJSON("file_operations_residents.json");
        System.out.println("Loaded " + residents.size() + " residents");
        Map<Integer, Integer> points = readPersonalPointsFromJSON("file_operations_personalpoints.json");
        System.out.println("Loaded points for " + points.size() + " residents");
    }
}
