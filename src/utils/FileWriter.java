package utils;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import model.*;

public class FileWriter {
        private ClovervilleModelManager model;

        public FileWriter(ClovervilleModelManager model) {
                this.model = model;
        }

        public void saveAllData() {
                saveResidents();
                savePersonalPoints();
                saveTasks();
                saveTrades();
        }

        public void saveResidents() {
                ResidentList residentList = new ResidentList();
                for (Resident resident : model.getAllResidents()) {
                        residentList.addResident(resident);
                }

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("Personal_points.bin"))) {
                        out.writeObject(residentList);
                        System.out.println("Success writing residents");

                        // Write JSON for web: array of {id, firstName, lastName}
                        StringBuilder sb = new StringBuilder();
                        sb.append("[\n");
                        boolean first = true;
                        for (Resident r : model.getAllResidents()) {
                                if (!first)
                                        sb.append(",\n");
                                first = false;
                                sb.append("  {\"id\":").append(r.getId())
                                                .append(",\"firstName\":\"").append(escapeJson(r.getFirstName()))
                                                .append("\"")
                                                .append(",\"lastName\":\"").append(escapeJson(r.getLastName()))
                                                .append("\"}");
                        }
                        sb.append("\n]");

                        Path outPath = Paths.get("docs/file_operations_residents.json");
                        Files.writeString(outPath, sb.toString(), StandardCharsets.UTF_8);
                        System.out.println("Wrote residents JSON to: " + outPath.toAbsolutePath());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void savePersonalPoints() {
                try {
                        // Write JSON mapping; object mapping id -> points
                        StringBuilder sb = new StringBuilder();
                        sb.append("{\n");
                        boolean first = true;
                        for (Resident r : model.getAllResidents()) {
                                if (!first)
                                        sb.append(",\n");
                                first = false;
                                sb.append("  \"").append(r.getId()).append("\":").append(r.getPersonalPoints());
                        }
                        sb.append("\n}");

                        Path outPath = Paths.get("docs/file_operations_personalpoints.json");
                        Files.writeString(outPath, sb.toString(), StandardCharsets.UTF_8);
                        System.out.println("Wrote personal points JSON to: " + outPath.toAbsolutePath());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void saveTasks() {
                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("tasks.bin"))) {
                        TasksList tasksList = new TasksList();

                        for (Task task : model.getTaskList()) {
                                tasksList.addTask(task);
                        }

                        out.writeObject(tasksList);
                        System.out.println("Success writing tasks");

                        // Write tasks JSON array
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        boolean first = true;
                        for (Task t : model.getTaskList()) {
                                if (!first)
                                        sb.append(",");
                                first = false;
                                sb.append("{\"name\":\"").append(escapeJson(t.getName())).append("\",")
                                                .append("\"type\":\"").append(escapeJson(t.getType())).append("\"}");
                        }
                        sb.append("]");
                        Path outPath = Paths.get("docs/file_operations_tasks.json");
                        Files.writeString(outPath, sb.toString(), StandardCharsets.UTF_8);
                        System.out.println("Wrote tasks JSON to: " + outPath.toAbsolutePath());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public void saveTrades() {
                try {
                        // Still write binary if trades are serializable
                        try (ObjectOutputStream out = new ObjectOutputStream(
                                        new FileOutputStream("trades.bin"))) {
                                // If model exposes trades, serialize them; otherwise skip
                                try {
                                        Object tradeListObj = model.getTradeList();
                                        if (tradeListObj != null) {
                                                out.writeObject(tradeListObj);
                                                System.out.println("Success writing trades");
                                        }
                                } catch (Throwable ex) {
                                        // model may not have trades or serialization may fail
                                }
                        }

                        // Write empty trades JSON placeholder (adjust when model provides data)
                        Path outPath = Paths.get("docs/file_operations_trades.json");
                        Files.writeString(outPath, "[]", StandardCharsets.UTF_8);
                        System.out.println("Wrote trades JSON to: " + outPath.toAbsolutePath());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private static String escapeJson(String s) {
                if (s == null)
                        return "";
                return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
        }

        public static void main(String[] args) {
                // For standalone testing with hardcoded data
                ResidentList residentList = new ResidentList();

                try (ObjectOutputStream out = new ObjectOutputStream(
                                new FileOutputStream("residents.bin"))) {
                        out.writeObject(residentList);
                        System.out.println("Success writing residents");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // Write resident points JSON with formatting
                try {
                        StringBuilder sb = new StringBuilder();
                        sb.append("{\n");
                        boolean first = true;
                        for (Resident r : residentList.getAllResidents()) {
                                if (!first)
                                        sb.append(",\n");
                                first = false;
                                sb.append("  \"").append(r.getId()).append("\":").append(r.getPersonalPoints());
                        }
                        sb.append("\n}");
                        Path outPath = Paths.get("docs/file_operations_personalpoints.json");
                        Files.writeString(outPath, sb.toString(), StandardCharsets.UTF_8);
                        System.out.println("Wrote personal points JSON to: " + outPath.toAbsolutePath());
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Done writing");
        }
}
