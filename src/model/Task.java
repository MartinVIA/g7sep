package model;

public class Task {
    private String name;
    private String type;
    private int amount_points;
    private boolean completeTask;

    public Task(String name, String type, int amount_points) {
        this.name = name;
        this.type = type;
        this.amount_points = amount_points;
        this.completeTask = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmountOfPoints() {
        return amount_points;
    }

    public boolean isCompleteTask() {
        return completeTask;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmountOfPoints(int amount_points) {
        this.amount_points = amount_points;
    }

    public void setCompleteTask(boolean completeTask) {
        this.completeTask = completeTask;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        Task other = (Task) obj;
        return name.equals(other.name) &&
                type.equals(other.type) &&
                amount_points == other.amount_points &&
                completeTask == other.completeTask;
    }

    public String toString() {
        return "Name: " + name +
                ", Type: " + type +
                ", Amount of Points: " + amount_points +
                ", Complete Task: " + completeTask;
    }
}
