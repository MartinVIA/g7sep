package model;

import java.io.Serializable;

/**
 * The class for tasks that can be completed by residents and award points
 * @author Victor Tonu
 * @author Adam Terelak
 * @author Martin Chavez
 */
public abstract class Task implements Serializable {

    private String name;
    private String description;
    private String type;
    private int points;
    private Date completeDate;

    /**
     * Creates a task and sets points to 0
     * @param name Task name
     * @param type Task type
     */
    public Task(String name, String type) {
        this.name = name;
        this.description = "";
        this.type = type;
        this.points = 0;
        completeDate = null;
    }

    /**
     * Creates a task with a specific points value
     * @param name Task name
     * @param type Task type
     * @param points Points award
     */
    public Task(String name, String type, int points) {
        this.name = name;
        this.description = "";
        this.type = type;
        this.points = points;
        completeDate = null;
    }

    /**
     * Returns the name of the task
     * @return Task name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the task
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task
     * @return task type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the points awarded when the task is done
     * @return points value
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the complete date of the task
     * @param date day when the task was completed
     */
    public void setCompleteDate(Date date) {
        completeDate = date;
    }

    /**
     * gets the complete date of the task
     * @return date when the task was completed
     */
    public Date getCompleteDate() {
        return completeDate;
    }

    /**
     * Sets the name of the task
     * @param name task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the task
     * @param description task description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the type of the task
     * @param type Task type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the points awarded when the task is done
     * @param points Points value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * An abstract method to execute completion logic and side effects for the
     * task subtype
     * @param resident Resident completing the task
     */
    public abstract void completeTask(Resident resident);

    /**
     * Compares a task object with another object
     * @param obj object to compare
     * @return true if the objects are equal, otherwise false
     */
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Task other = (Task) obj;
        return name.equals(other.name)
                && description.equals(other.description)
                && type.equals(other.type)
                && points == other.points;
    }

    /**
     * Returns a string representation of the task
     * @return a formatted string with task's name, description, type and
     * completion status
     */
    public String toString() {
        return "Name: " + name
                + ", Description: " + description
                + ", Type: " + type;
    }
}
