package utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import model.*;

/**
 * Utility class responsible for reading application data from JSON files.
 *
 * This class provides static helper methods for loading residents, personal
 * points, tasks, and trades from JSON-formatted files. The JSON parsing is
 * implemented manually without the use of external libraries and is intended
 * for simple, well-defined data structures.
 *
 * @author Leon de Kuijper
 * @author Martin Chavez
 */
public class JSONReader {

  /**
   * Reads a list of residents from a JSON file.
   *
   * The JSON file is expected to contain an array of objects with fields such
   * as id, firstName, and lastName.
   *
   * @param filePath the path to the JSON residents file
   * @return a list of {@link Resident} objects parsed from the file
   */
  public static List<Resident> readResidentsFromJSON(String filePath) {
    List<Resident> residents = new ArrayList<>();
    try {
      Path p = Paths.get(filePath);
      if (!Files.exists(p)) {
        return residents;
      }
      String content = Files.readString(p, StandardCharsets.UTF_8).trim();
      if (content.isEmpty()) {
        return residents;
      }

      if (content.startsWith("[")) {
        content = content.substring(1);
      }
      if (content.endsWith("]")) {
        content = content.substring(0, content.length() - 1);
      }
      if (content.trim().isEmpty()) {
        return residents;
      }

      List<String> objects = splitTopLevelObjects(content);
      for (String obj : objects) {
        Map<String, String> map = parseJsonObject(obj);
        String idStr = map.get("id");
        String first = map.getOrDefault("firstName", "");
        String last = map.getOrDefault("lastName", "");

        try {
          int id = Integer.parseInt(idStr.trim());
          Resident r = new Resident(id, last, first, 0);
          residents.add(r);
        } catch (Exception e) {
          // Invalid resident entry is skipped
        }
      }
    } catch (Exception e) {
      System.err.println("Error reading residents JSON: " + e.getMessage());
    }
    return residents;
  }

  /**
   * Reads personal points for residents from a JSON file.
   *
   * The JSON file is expected to contain key-value pairs where the key
   * represents the resident ID and the value represents the number of points.
   *
   * @param filePath the path to the JSON personal points file
   * @return a map containing resident IDs and their corresponding points
   */
  public static Map<Integer, Integer> readPersonalPointsFromJSON(String filePath) {
    Map<Integer, Integer> map = new HashMap<>();
    try {
      Path p = Paths.get(filePath);
      if (!Files.exists(p)) {
        return map;
      }
      String content = Files.readString(p, StandardCharsets.UTF_8).trim();
      if (content.isEmpty()) {
        return map;
      }

      if (content.startsWith("{")) {
        content = content.substring(1);
      }
      if (content.endsWith("}")) {
        content = content.substring(0, content.length() - 1);
      }
      if (content.trim().isEmpty()) {
        return map;
      }

      String[] parts = content.split(",");
      for (String part : parts) {
        String[] kv = part.split(":", 2);
        if (kv.length != 2) {
          continue;
        }

        String key = kv[0].trim();
        String val = kv[1].trim();

        if (key.startsWith("\"") && key.endsWith("\"")) {
          key = key.substring(1, key.length() - 1);
        }

        try {
          int id = Integer.parseInt(key);
          int points = Integer.parseInt(val.replaceAll("\"", ""));
          map.put(id, points);
        } catch (NumberFormatException e) {
          // Invalid entry is skipped
        }
      }
    } catch (Exception e) {
      System.err.println("Error reading personal points JSON: " + e.getMessage());
    }
    return map;
  }

  /**
   * Reads a list of tasks from a JSON file.
   *
   * The JSON file is expected to contain an array of task objects with name,
   * type, and points fields. The task type determines whether a
   * {@link GreenActions} or {@link CommunityTasks} object is created.
   *
   * @param filePath the path to the JSON tasks file
   * @return a list of {@link Task} objects parsed from the file
   */
  public static List<Task> readTasksFromJSON(String filePath) {
    List<Task> tasks = new ArrayList<>();
    try {
      Path p = Paths.get(filePath);
      if (!Files.exists(p)) {
        return tasks;
      }
      String content = Files.readString(p, StandardCharsets.UTF_8).trim();
      if (content.isEmpty()) {
        return tasks;
      }

      if (content.startsWith("[")) {
        content = content.substring(1);
      }
      if (content.endsWith("]")) {
        content = content.substring(0, content.length() - 1);
      }
      if (content.trim().isEmpty()) {
        return tasks;
      }

      List<String> objects = splitTopLevelObjects(content);
      for (String obj : objects) {
        Map<String, String> map = parseJsonObject(obj);
        String name = map.getOrDefault("name", "");
        String type = map.getOrDefault("type", "");
        String pointsStr = map.getOrDefault("points", "0");

        if (name.isEmpty() || type.isEmpty()) {
          continue;
        }

        try {
          int points = Integer.parseInt(pointsStr.trim());
          Task task = type.equalsIgnoreCase("green_action")
              ? new GreenActions(name, type, points)
              : new CommunityTasks(name, type, points);
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

  /**
   * Reads a list of trades from a JSON file.
   *
   * The JSON file is expected to contain an array of trade objects with name,
   * description, and pointCost fields.
   *
   * @param filePath the path to the JSON trades file
   * @return a list of {@link Trade} objects parsed from the file
   */
  public static List<Trade> readTradesFromJSON(String filePath) {
    List<Trade> trades = new ArrayList<>();
    try {
      Path p = Paths.get(filePath);
      if (!Files.exists(p)) {
        return trades;
      }
      String content = Files.readString(p, StandardCharsets.UTF_8).trim();
      if (content.isEmpty()) {
        return trades;
      }

      if (content.startsWith("[")) {
        content = content.substring(1);
      }
      if (content.endsWith("]")) {
        content = content.substring(0, content.length() - 1);
      }
      if (content.trim().isEmpty()) {
        return trades;
      }

      List<String> objects = splitTopLevelObjects(content);
      for (String obj : objects) {
        Map<String, String> map = parseJsonObject(obj);
        try {
          String name = map.get("name");
          String description = map.get("description");
          int pointCost = Integer.parseInt(map.get("pointCost").trim());
          Trade trade = new Trade(name, description, null, pointCost);
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

  /**
   * Splits a JSON array string into individual top-level JSON objects.
   *
   * @param content the JSON content without surrounding brackets
   * @return a list of JSON object strings
   */
  private static List<String> splitTopLevelObjects(String content) {
    List<String> objs = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    int depth = 0;
    for (int i = 0; i < content.length(); i++) {
      char c = content.charAt(i);
      cur.append(c);
      if (c == '{') {
        depth++;
      } else if (c == '}') {
        depth--;
      }
      if (depth == 0 && cur.length() > 0) {
        String s = cur.toString().trim();
        if (s.startsWith(",")) {
          s = s.substring(1).trim();
        }
        objs.add(s);
        cur.setLength(0);
      }
    }
    return objs;
  }

  /**
   * Parses a single JSON object string into a key-value map.
   *
   * @param obj the JSON object string
   * @return a map containing field names and their values
   */
  private static Map<String, String> parseJsonObject(String obj) {
    Map<String, String> map = new HashMap<>();
    String s = obj.trim();
    if (s.startsWith("{")) {
      s = s.substring(1);
    }
    if (s.endsWith("}")) {
      s = s.substring(0, s.length() - 1);
    }

    List<String> parts = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    boolean inQuotes = false;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '"') {
        inQuotes = !inQuotes;
      }
      if (c == ',' && !inQuotes) {
        parts.add(cur.toString());
        cur.setLength(0);
      } else {
        cur.append(c);
      }
    }
    if (cur.length() > 0) {
      parts.add(cur.toString());
    }

    for (String part : parts) {
      String[] kv = part.split(":", 2);
      if (kv.length != 2) {
        continue;
      }
      String key = kv[0].trim();
      String val = kv[1].trim();

      if (key.startsWith("\"") && key.endsWith("\"")) {
        key = key.substring(1, key.length() - 1);
      }
      if (val.startsWith("\"") && val.endsWith("\"")) {
        val = val.substring(1, val.length() - 1);
      }
      map.put(key, val);
    }
    return map;
  }
}
