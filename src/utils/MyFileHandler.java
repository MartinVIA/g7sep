package utils;

import java.io.*;

public class MyFileHandler {
    public static void saveToFile(String filename, String content) throws IOException {
        try {
            FileWriter writer = new FileWriter("VillagerInfo.txt");
            writer.write("holla bitch");
            writer.close();

        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage());
        }
    }
}
