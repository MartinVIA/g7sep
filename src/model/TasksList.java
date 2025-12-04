package model;

import java.util.*;

import java.io.Serializable;

public class TasksList implements Serializable {
  private ArrayList<Task> tasks;

  public TasksList() {
    tasks = new ArrayList<Task>();
  }

  public int size() {
    return tasks.size();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void removeTask(Task task) {
    tasks.remove(task);
  }

  public void removeTask(int index) {
    if (index >= 0 && index < tasks.size()) {
      tasks.remove(index);
    }
  }

  public Task getTask(int index) {
    if (index < tasks.size() && index >= 0) {
      return tasks.get(index);
    }
    return null;
  }

  public List<Task> getTaskList() {
    return new ArrayList<>(tasks);
  }

  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    TasksList other = (TasksList) obj;
    return tasks.equals(other.tasks);
  }

  public String toString() {
    String list = "";
    for (int i = 0; i < tasks.size(); i++) {
      list += tasks.get(i) + "\n";
    }
    return list;
  }
}