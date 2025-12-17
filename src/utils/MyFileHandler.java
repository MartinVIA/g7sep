package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class that handles low-level file operations for both text and binary files.
 *
 * This class provides reusable methods for writing and reading:
 * - text content (single string or array of strings)
 * - serialized objects in binary format (single object or array of objects)
 *
 * Higher-level utility classes such as {@link FileReader} and {@link FileWriter}
 * use this class to persist and load application data.
 */
public class MyFileHandler {

  /**
   * Writes a string to a text file.
   * If the file already exists, its content is overwritten.
   *
   * @param fileName the name (or path) of the file to write to
   * @param str the string to write to the file
   * @throws FileNotFoundException if the file cannot be created or opened
   */
  public static void writeToTextFile(String fileName, String str) throws FileNotFoundException {
    writeText(fileName, str, false);
  }

  /**
   * Appends a string to a text file.
   * If the file does not exist, it is created.
   *
   * @param fileName the name (or path) of the file to append to
   * @param str the string to append to the file
   * @throws FileNotFoundException if the file cannot be created or opened
   */
  public static void appendToTextFile(String fileName, String str) throws FileNotFoundException {
    writeText(fileName, str, true);
  }

  /**
   * Writes an array of strings to a text file.
   * If the file already exists, its content is overwritten.
   *
   * @param fileName the name (or path) of the file to write to
   * @param strs the array of strings to write to the file
   * @throws FileNotFoundException if the file cannot be created or opened
   */
  public static void writeArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
    writeText(fileName, strs, false);
  }

  /**
   * Appends an array of strings to a text file.
   * If the file does not exist, it is created.
   *
   * @param fileName the name (or path) of the file to append to
   * @param strs the array of strings to append to the file
   * @throws FileNotFoundException if the file cannot be created or opened
   */
  public static void appendArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException {
    writeText(fileName, strs, true);
  }

  /**
   * Reads the first line from a text file and returns it as a string.
   *
   * @param fileName the name (or path) of the file to read from
   * @return the first line in the file
   * @throws FileNotFoundException if the file does not exist
   */
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

  /**
   * Reads all lines from a text file and returns them as an array of strings.
   *
   * @param fileName the name (or path) of the file to read from
   * @return an array containing all lines from the file
   * @throws FileNotFoundException if the file does not exist
   */
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

  /**
   * Writes an object to a binary file using Java serialization.
   *
   * @param fileName the name (or path) of the file to write to
   * @param obj the object to serialize and save
   * @throws FileNotFoundException if the file cannot be created or opened
   * @throws IOException if an I/O error occurs while writing the file
   */
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

  /**
   * Writes an array of objects to a binary file using Java serialization.
   * Objects are written in the order they appear in the array.
   *
   * @param fileName the name (or path) of the file to write to
   * @param objs the array of objects to serialize and save
   * @throws FileNotFoundException if the file cannot be created or opened
   * @throws IOException if an I/O error occurs while writing the file
   */
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

  /**
   * Reads a single object from a binary file using Java deserialization.
   * The returned object must be cast to the correct type by the caller.
   *
   * @param fileName the name (or path) of the file to read from
   * @return the deserialized object, or {@code null} if the file is empty
   * @throws FileNotFoundException if the file does not exist
   * @throws IOException if an I/O error occurs while reading the file
   * @throws ClassNotFoundException if the class of the serialized object cannot be found
   */
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
        // File is empty
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

  /**
   * Reads all objects from a binary file using Java deserialization.
   * The returned array contains the objects in the order they were written.
   * Each object must be cast to its correct type by the caller.
   *
   * @param fileName the name (or path) of the file to read from
   * @return an array containing all deserialized objects
   * @throws FileNotFoundException if the file does not exist
   * @throws IOException if an I/O error occurs while reading the file
   * @throws ClassNotFoundException if the class of a serialized object cannot be found
   */
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

  /**
   * Internal helper method used by {@link #writeToTextFile(String, String)}
   * and {@link #appendToTextFile(String, String)}.
   *
   * @param fileName the file name (or path)
   * @param str the string to write
   * @param append true to append, false to overwrite
   * @throws FileNotFoundException if the file cannot be created or opened
   */
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

  /**
   * Internal helper method used by {@link #writeArrayToTextFile(String, String[])}
   * and {@link #appendArrayToTextFile(String, String[])}.
   *
   * @param fileName the file name (or path)
   * @param strs the array of strings to write
   * @param append true to append, false to overwrite
   * @throws FileNotFoundException if the file cannot be created or opened
   */
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
}
