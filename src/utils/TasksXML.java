package utils;

import model.Task;
import model.TasksList;
import model.Date;

import java.io.*;
import java.util.ArrayList;

public class TasksXML {
  private TasksList tasksList;
  private ArrayList<Task> tasks;

  public TasksXML() {
    tasks = new ArrayList<>();
    readTasks();
    writeTasks();
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }

  public void readTasks() {
    try (FileInputStream fileIn = new FileInputStream("tasks.bin");
        ObjectInputStream read = new ObjectInputStream(fileIn)) {

      tasksList = (TasksList) read.readObject();

      // Populate the tasks ArrayList from tasksList
      tasks = new ArrayList<>(tasksList.getTasks());

    } catch (FileNotFoundException e) {
      System.out.println("File not found, or could not be opened");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("IO Error reading file");
      e.printStackTrace();
      System.exit(1);
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found");
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void writeTasks() {
    try (FileOutputStream fileOut = new FileOutputStream("tasks.xml");
        PrintWriter write = new PrintWriter(fileOut)) {

      write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      write.println("<tasks>");
      for (Task task : tasks) {
        write.println("<task>");
        write.println("<name>" + task.getName() + "</name>");
        write.println("<type>" + task.getType() + "</type>");
        write.println("<points>" + task.getPoints() + "</points>");
        write.println("<isComplete>" + task.isCompleteTask() + "</isComplete>");

        Date completeDate = task.getCompleteDate();
        if (completeDate != null) {
          write.println("<completeDate>" + completeDate.toString() + "</completeDate>");
        } else {
          write.println("<completeDate>null</completeDate>");
        }

        write.println("</task>");
      }
      write.println("</tasks>");
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      System.exit(1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
