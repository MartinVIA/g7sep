package utils;

import java.io.*;
import model.Resident;

public class MyFileHandler {
    public static void saveToFile(String filename, String content) throws IOException {
        try {
            FileWriter writer = new FileWriter("ResidentInfo.txt", true);
            writer.write(resident.toFileString() + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage());
        }
    }
}
