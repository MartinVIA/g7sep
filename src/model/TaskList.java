package model;

import java.util.*;

import java.io.Serializable;

/**
 * The class containing a list of Task objects
 */
public class TaskList implements Serializable {
  private ArrayList<Task> tasks;

  /**
   * Creates an empty tasks list
   */
  public TaskList() {
    tasks = new ArrayList<Task>();
  }

  /**
   * Returns number of tasks
   * 
   * @return List size
   */
  public int size() {
    return tasks.size();
  }

  /**
   * Adds a task to the list
   * 
   * @param task Task to add
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * Removes a task if present
   * 
   * @param task Task to remove
   */
  public void removeTask(Task task) {
    if (task != null) {
      tasks.remove(task);
    }
  }

  /**
   * Removes a task by index when valid
   * 
   * @param index Task position to remove
   */
  public void removeTask(int index) {
    if (index >= 0 && index < tasks.size()) {
      tasks.remove(index);
    }
  }

  /**
   * Retrieves a task by index
   * 
   * @param index Task position
   * @return Task or null if out of range
   */
  public Task getTask(int index) {
    if (index < tasks.size() && index >= 0) {
      return tasks.get(index);
    }
    return null;
  }

  /**
   * Returns a copy of the tasks list
   * 
   * @return List copy
   */
  public ArrayList<Task> getAllTasks() {
    return new ArrayList<Task>(tasks);
  }

  /**
   * Compares a tasks list object with another object
   * 
   * @param obj Object to compare
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    TaskList other = (TaskList) obj;
    return tasks.equals(other.tasks);
  }

  /**
   * Returns a string representation of the tasks list
   * 
   * @return a formatted String with all tasks' names, descriptions, types and
   *         completion status
   */
  public String toString() {
    String list = "";
    for (int i = 0; i < tasks.size(); i++) {
      list += tasks.get(i) + "\n";
    }
    return list;
  }
}