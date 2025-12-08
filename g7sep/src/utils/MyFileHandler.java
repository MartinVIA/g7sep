package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class MyFileHandler {
    // Writes the given string to a file with the given file name
    public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, false);
    }

    // Appends the given string to a file with the given file name
    public static void appendToTextFile(String fileName, String str) throws FileNotFoundException {
        writeText(fileName, str, true);
    }

    // writeToTextFile and appendToTextFile are almost identical - only the boolean
    // in the constructor
    // of the FileOutputStream differs. So I made this private method that both
    // methods call
    private static void writeText(String fileName, String str, boolean append) throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);
            writeToFile.println(str);
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    // Writes the strings in the given array to a file with the given file name
    public static void writeArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
        writeText(fileName, strs, false);
    }

    // Appends the strings in the given array to a file with the given file name
    public static void appendArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
        writeText(fileName, strs, true);
    }

    // Again, the writeArrayToTextFile and appendArrayToTextFile methods are almost
    // identical.
    // So I made this private method that both methods call
    private static void writeText(String fileName, String[] strs, boolean append) throws FileNotFoundException {
        PrintWriter writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
            writeToFile = new PrintWriter(fileOutStream);

            for (int i = 0; i < strs.length; i++) {
                writeToFile.println(strs[i]);
            }
        } finally {
            if (writeToFile != null) {
                writeToFile.close();
            }
        }
    }

    // Reads the first line from the file with the given file name and returns it as
    // a String
    public String readFromTextFile(String fileName) throws FileNotFoundException {
        Scanner readFromFile = null;
        String str = "";

        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);
            str = readFromFile.nextLine();
        } finally {
            if (readFromFile != null) {
                readFromFile.close();
            }
        }
        return str;
    }

    // Reads all lines from the file with the given file name and returns them as a
    // String[]
    public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException {
        Scanner readFromFile = null;
        ArrayList<String> strs = new ArrayList<String>();

        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new Scanner(fileInStream);

            while (readFromFile.hasNext()) {
                strs.add(readFromFile.nextLine());
            }
        } finally {
            if (readFromFile != null) {
                readFromFile.close();
            }
        }

        String[] strsArray = new String[strs.size()];
        return strs.toArray(strsArray);
    }

    // Writes the given object to a file with the given file name
    public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            writeToFile.writeObject(obj);
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    // Writes the objects in the given array to a file with the given file name
    public static void writeArrayToBinaryFile(String fileName, Object[] objs)
            throws FileNotFoundException, IOException {
        ObjectOutputStream writeToFile = null;

        try {
            FileOutputStream fileOutStream = new FileOutputStream(fileName);
            writeToFile = new ObjectOutputStream(fileOutStream);

            for (int i = 0; i < objs.length; i++) {
                writeToFile.writeObject(objs[i]);
            }
        } finally {
            if (writeToFile != null) {
                try {
                    writeToFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }
    }

    // Reads the first object from the file with the given file name and returns it.
    // Whoever calls the method will need to cast it from type Object to its actual
    // type
    public static Object readFromBinaryFile(String fileName)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        Object obj = null;
        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            try {
                obj = readFromFile.readObject();
            } catch (EOFException eof) {
                // Done reading
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return obj;
    }

    // Reads all objects from the file with the given file name and returns them as
    // an Object[].
    // Whoever calls the method will need to cast the Objects to their actual type
    public static Object[] readArrayFromBinaryFile(String fileName)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Object> objs = new ArrayList<Object>();

        ObjectInputStream readFromFile = null;
        try {
            FileInputStream fileInStream = new FileInputStream(fileName);
            readFromFile = new ObjectInputStream(fileInStream);
            while (true) {
                try {
                    objs.add(readFromFile.readObject());
                } catch (EOFException eof) {
                    // Done reading
                    break;
                }
            }
        } finally {
            if (readFromFile != null) {
                try {
                    readFromFile.close();
                } catch (IOException e) {
                    System.out.println("IO Error closing file " + fileName);
                }
            }
        }

        return objs.toArray();
    }
}

/*
 * public class MyFileHandler {
 * private static final Path INFO_DIR = Paths.get("src", "Info");
 * private static final Path AUDIT_FILE = INFO_DIR.resolve("ResidentInfo.txt");
 * private static final Path CSV_FILE =
 * INFO_DIR.resolve("ResidentSnapshot.csv");
 * private static final DateTimeFormatter SNAPSHOT_TS =
 * DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
 * 
 * static {
 * try {
 * Files.createDirectories(INFO_DIR);
 * if (Files.notExists(AUDIT_FILE)) {
 * Files.createFile(AUDIT_FILE);
 * }
 * if (Files.notExists(CSV_FILE)) {
 * Files.createFile(CSV_FILE);
 * try (BufferedWriter bw = Files.newBufferedWriter(CSV_FILE,
 * StandardOpenOption.WRITE)) {
 * bw.write("ID,FirstName,LastName,HasBoost,PersonalPoints,Action,Timestamp");
 * bw.newLine();
 * }
 * }
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * public static synchronized void appendResident(Resident r, String action) {
 * // Audit log (human readable)
 * String timestamp = java.time.Instant.now().toString();
 * String auditLine = String.format("Timestamp:%s | Action:%s | %s", timestamp,
 * action, r.toFileString());
 * try {
 * Files.write(AUDIT_FILE,
 * (auditLine +
 * System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
 * StandardOpenOption.CREATE, StandardOpenOption.APPEND);
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * 
 * // CSV log (machine readable)
 * String csvLine = String.format("%d,%s,%s,%b,%d,%s,%s",
 * r.getId(), escapeCsv(r.getFirstName()), escapeCsv(r.getLastName()),
 * r.hasBoost(), r.getPersonalPoints(),
 * action, timestamp);
 * try {
 * Files.write(CSV_FILE, (csvLine +
 * System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
 * StandardOpenOption.CREATE, StandardOpenOption.APPEND);
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * public static synchronized void saveAllResidents(List<Resident> residents) {
 * String ts = SNAPSHOT_TS.format(LocalDateTime.now());
 * Path full = INFO_DIR.resolve("ResidentSnapshot_FULL_" + ts + ".csv");
 * try (BufferedWriter bw = Files.newBufferedWriter(full,
 * StandardOpenOption.CREATE_NEW)) {
 * bw.write("ID,FirstName,LastName,HasBoost,PersonalPoints");
 * bw.newLine();
 * for (Resident r : residents) {
 * String line = String.format("%d,%s,%s,%b,%d",
 * r.getId(), escapeCsv(r.getFirstName()), escapeCsv(r.getLastName()),
 * r.hasBoost(),
 * r.getPersonalPoints());
 * bw.write(line);
 * bw.newLine();
 * }
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * }
 * 
 * private static String escapeCsv(String s) {
 * if (s == null)
 * return "";
 * if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
 * return "\"" + s.replace("\"", "\"\"") + "\"";
 * }
 * return s;
 * }
 * 
 * // Keep a compatibility method if other code calls saveToFile
 * public static synchronized void saveToFile(String filename, String content)
 * throws IOException {
 * Path p = INFO_DIR.resolve(filename);
 * try {
 * Files.createDirectories(INFO_DIR);
 * Files.write(p, (content +
 * System.lineSeparator()).getBytes(java.nio.charset.StandardCharsets.UTF_8),
 * StandardOpenOption.CREATE, StandardOpenOption.APPEND);
 * } catch (IOException e) {
 * throw new IOException("Error writing to file: " + e.getMessage(), e);
 * }
 * }
 * 
 * // READ METHODS
 * public static synchronized List<String> readAuditLog() {
 * try {
 * return Files.readAllLines(AUDIT_FILE);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return new java.util.ArrayList<>();
 * }
 * }
 * 
 * public static synchronized List<String> readCsvFile() {
 * try {
 * return Files.readAllLines(CSV_FILE);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return new java.util.ArrayList<>();
 * }
 * }
 * 
 * public static synchronized String readAuditLogAsString() {
 * try {
 * return Files.readString(AUDIT_FILE);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return "";
 * }
 * }
 * 
 * public static synchronized String readCsvFileAsString() {
 * try {
 * return Files.readString(CSV_FILE);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return "";
 * }
 * }
 * 
 * public static synchronized List<String> readAllSnapshotFiles() {
 * List<String> snapshots = new java.util.ArrayList<>();
 * try {
 * var snapshotFiles = Files.list(INFO_DIR)
 * .filter(p -> p.getFileName().toString().startsWith("ResidentSnapshot_FULL_"))
 * .sorted(java.util.Comparator.reverseOrder())
 * .toList();
 * for (Path p : snapshotFiles) {
 * snapshots.add(p.getFileName().toString());
 * }
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * return snapshots;
 * }
 * 
 * public static synchronized List<String> readSnapshotFile(String filename) {
 * try {
 * Path p = INFO_DIR.resolve(filename);
 * return Files.readAllLines(p);
 * } catch (IOException e) {
 * e.printStackTrace();
 * return new java.util.ArrayList<>();
 * }
 * }
 * 
 * }
 */