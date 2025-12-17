package utils;

import java.io.*;
import java.util.*;
import model.*;

/**
 * Utility class responsible for writing application data to JSON files.
 * This class provides static helper methods for exporting residents, tasks,
 * trades, and community green points into JSON format. The JSON is generated
 * manually using string builders to maintain full control over the output
 * structure without relying on external libraries.
 * @author Leon de Kuijper

 */
public class JSONWriter {

  /**
   * Writes a list of residents to a JSON file.
   * Each resident is stored as a JSON object containing id, first name,
   * last name, personal points, and boost status.
   * @param residents the list of residents to save
   * @param filePath the destination file path
   */
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

  /**
   * Writes only personal points data for residents to a JSON file.
   * This method exports a simplified representation focused on resident IDs
   * and their corresponding personal points.
   * @param residents the list of residents containing point data
   * @param filePath the destination file path
   */
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

  /**
   * Writes a list of tasks to a JSON file.
   * Each task is stored with its name, description, type, and awarded points.
   * @param tasks the list of tasks to save
   * @param filePath the destination file path
   */
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

  /**
   * Writes a list of trades to a JSON file.
   * Each trade includes its name, description, point cost,
   * and the name of the resident who initiated the trade.
   * @param trades the list of trades to save
   * @param filePath the destination file path
   */
  public static void saveTradesToJSON(ArrayList<Trade> trades, String filePath) {
    StringBuilder sb = new StringBuilder();
    sb.append("[\n");
    for (int i = 0; i < trades.size(); i++) {
      Trade t = trades.get(i);
      sb.append("  {\n");
      sb.append("    \"name\": \"" + escape(t.getStringName()) + "\",\n");
      sb.append("    \"description\": \"" + escape(t.getDescription()) + "\",\n");
      sb.append("    \"pointCost\": " + t.getPointCost() + ",\n");
      sb.append("    \"traderName\": \"" + t.getTrader().getFirstName() + " "
          + t.getTrader().getLastName() + "\"\n");
      sb.append("  }");
      if (i < trades.size() - 1)
        sb.append(",");
      sb.append("\n");
    }
    sb.append("]");
    writeFile(sb.toString(), filePath);
  }

  /**
   * Writes community green points data to a JSON file.
   * The JSON object includes the current green points, the point goal,
   * and the community reward description.
   * @param gp the GreenPoints object containing community data
   * @param filePath the destination file path
   */
  public static void saveGreenPointsToJSON(GreenPoints gp, String filePath) {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("  \"greenPoints\": " + gp.getPoints() + ",\n");
    sb.append("  \"pointGoal\": " + gp.getGoal() + ",\n");
    sb.append("  \"communityReward\": \"" + gp.getCommunityReward() + "\"\n");
    sb.append("}");
    writeFile(sb.toString(), filePath);
  }

  /**
   * Writes a string of content to a file.
   * @param content the content to write
   * @param filePath the destination file path
   */
  private static void writeFile(String content, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
      writer.write(content);
      System.out.println("Saved JSON to " + filePath);
    } catch (IOException e) {
      System.err.println("Error saving JSON: " + e.getMessage());
    }
  }

  /**
   * Escapes special characters in strings to ensure valid JSON output.
   * @param str the input string
   * @return the escaped string safe for JSON formatting
   */
  private static String escape(String str) {
    if (str == null)
      return "";
    return str.replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t");
  }
}
