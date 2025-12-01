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

    public int getAmount_points() {
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

    public void setAmount_points(int amount_points) {
        this.amount_points = amount_points;
    }

    public void setCompleteTask(boolean completeTask) {
        this.completeTask = completeTask;
    }

}
