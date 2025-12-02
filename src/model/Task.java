package model;

public class Task {
    private String name;
    private String type;
    private int amountOfPoints;
    private boolean completeTask;
    private GreenPoints greenPoints;

    public Task(String name, String type, int amountOfPoints) {
        this.name = name;
        this.type = type;
        this.amountOfPoints = amountOfPoints;
        this.completeTask = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAmountOfPoints() {
        return amountOfPoints;
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

    public void setAmountOfPoints(int amountOfPoints) {
        this.amountOfPoints = amountOfPoints;
    }

    public void completeTask() {
        completeTask = true;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;

        Task other = (Task) obj;
        return name.equals(other.name) &&
                type.equals(other.type) &&
                amountOfPoints == other.amountOfPoints &&
                completeTask == other.completeTask;
    }

    public String toString() {
        return "Name: " + name +
                ", Type: " + type +
                ", Amount of Points: " + amountOfPoints +
                ", Complete Task: " + completeTask;
    }
}
