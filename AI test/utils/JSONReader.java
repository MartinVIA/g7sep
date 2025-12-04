package utils;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import model.*;

public class JSONReader {
    public static List<Resident> readResidentsFromJSON(String filePath) {
        // reuse same logic as src JSONReader
        List<Resident> residents = new ArrayList<>();
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p))
                return residents;
            String content = Files.readString(p, StandardCharsets.UTF_8).trim();
            if (content.isEmpty())
                return residents;
            if (content.startsWith("["))
                content = content.substring(1);
            if (content.endsWith("]"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return residents;
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
            if (content.startsWith("{"))
                content = content.substring(1);
            if (content.endsWith("}"))
                content = content.substring(0, content.length() - 1);
            if (content.trim().isEmpty())
                return map;
            String[] parts = content.split(",");
            for (String part : parts) {
                String[] kv = part.split(":", 2);
                if (kv.length != 2)
                    continue;
                String key = kv[0].trim();
                String val = kv[1].trim();
                if (key.startsWith("\"") && key.endsWith("\""))
                    key = key.substring(1, key.length() - 1);
                try {
                    int id = Integer.parseInt(key);
                    int points = Integer.parseInt(val.replaceAll("\"", ""));
                    map.put(id, points);
                } catch (NumberFormatException e) {
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading personal points JSON: " + e.getMessage());
        }
        return map;
    }

    // helper methods (copied from src)
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
}
