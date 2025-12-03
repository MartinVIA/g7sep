package model;

import java.util.ArrayList;
import java.util.List;

public class TasksList {
  private List<Task> tasks;

  public TasksList() {
    tasks = new ArrayList<Task>();
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

  public List<Task> getTasks() {
    return new ArrayList<>(tasks);
  }

  public int getSize() {
    return tasks.size();
  }

  public Task getTask(int index) {
    if (index < tasks.size() && index >= 0) {
      return tasks.get(index);
    }
    return null;
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