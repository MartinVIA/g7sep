package utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Resident;

public class MyFileHandler {
    private static final Path INFO_DIR = Paths.get("src", "Info");
    private static final Path AUDIT_FILE = INFO_DIR.resolve("ResidentInfo.txt");
    private static final Path CSV_FILE = INFO_DIR.resolve("ResidentSnapshot.csv");
    private static final DateTimeFormatter SNAPSHOT_TS = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    static {
        try {
            Files.createDirectories(INFO_DIR);
            if (Files.notExists(AUDIT_FILE)) {
                Files.createFile(AUDIT_FILE);
            }
            if (Files.notExists(CSV_FILE)) {
                Files.createFile(CSV_FILE);
                try (BufferedWriter bw = Files.newBufferedWriter(CSV_FILE, StandardOpenOption.WRITE)) {
                    bw.write("ID,FirstName,LastName,HasBoost,PersonalPoints,Action,Timestamp");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void appendResident(Resident r, String action) {
        // Audit log (human readable)
        String timestamp = java.time.Instant.now().toString();
        String auditLine = String.format("Timestamp:%s | Action:%s | %s", timestamp, action, r.toFileString());
        try {
            Files.write(AUDIT_FILE,
                    (auditLine + System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CSV log (machine readable)
        String csvLine = String.format("%d,%s,%s,%b,%d,%s,%s",
                r.getId(), escapeCsv(r.getFirstName()), escapeCsv(r.getLastName()), r.hasBoost(), r.getPersonalPoints(),
                action, timestamp);
        try {
            Files.write(CSV_FILE, (csvLine + System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void saveAllResidents(List<Resident> residents) {
        String ts = SNAPSHOT_TS.format(LocalDateTime.now());
        Path full = INFO_DIR.resolve("ResidentSnapshot_FULL_" + ts + ".csv");
        try (BufferedWriter bw = Files.newBufferedWriter(full, StandardOpenOption.CREATE_NEW)) {
            bw.write("ID,FirstName,LastName,HasBoost,PersonalPoints");
            bw.newLine();
            for (Resident r : residents) {
                String line = String.format("%d,%s,%s,%b,%d",
                        r.getId(), escapeCsv(r.getFirstName()), escapeCsv(r.getLastName()), r.hasBoost(),
                        r.getPersonalPoints());
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String escapeCsv(String s) {
        if (s == null)
            return "";
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

    // Keep a compatibility method if other code calls saveToFile
    public static synchronized void saveToFile(String filename, String content) throws IOException {
        Path p = INFO_DIR.resolve(filename);
        try {
            Files.createDirectories(INFO_DIR);
            Files.write(p, (content + System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage(), e);
        }
    }

    // READ METHODS
    public static synchronized List<String> readAuditLog() {
        try {
            return Files.readAllLines(AUDIT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    public static synchronized List<String> readCsvFile() {
        try {
            return Files.readAllLines(CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

    public static synchronized String readAuditLogAsString() {
        try {
            return Files.readString(AUDIT_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized String readCsvFileAsString() {
        try {
            return Files.readString(CSV_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized List<String> readAllSnapshotFiles() {
        List<String> snapshots = new java.util.ArrayList<>();
        try {
            var snapshotFiles = Files.list(INFO_DIR)
                    .filter(p -> p.getFileName().toString().startsWith("ResidentSnapshot_FULL_"))
                    .sorted(java.util.Comparator.reverseOrder())
                    .toList();
            for (Path p : snapshotFiles) {
                snapshots.add(p.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snapshots;
    }

    public static synchronized List<String> readSnapshotFile(String filename) {
        try {
            Path p = INFO_DIR.resolve(filename);
            return Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }

}
