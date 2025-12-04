package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Task;

public class TaskViewController {

    private final ClovervilleModelManager model;

    private ListView<Task> taskList;
    private TextField nameField;
    private TextField pointsField;
    private TextField typeField;
    private Label messageLabel;

    public TaskViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    public Scene createScene() {
        return new Scene(createView(), 700, 400);
    }

    public VBox createView() {
        taskList = new ListView<>();
        taskList.setPrefWidth(320);
        taskList.setCellFactory(lv -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String status = item.isCompleteTask() ? "(Complete)" : "";
                    setText(item.getName() + " [" + item.getType() + "] - " + item.getPoints() + " pts " + status);
                }
            }
        });
        refreshTaskList();

        Label titleLabel = new Label("Cloverville – Tasks");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));

        nameField = new TextField();
        nameField.setPromptText("Task name");

        // descField = new TextField();
        // descField.setPromptText("Type (description)");

        typeField = new TextField();
        typeField.setPromptText("Type (e.g. community or green)");

        pointsField = new TextField();
        pointsField.setPromptText("Points awarded (e.g. 5)");

        Button addButton = new Button("Add task");
        addButton.setOnAction(e -> handleAddTask());

        Button completeButton = new Button("Mark complete");
        completeButton.setOnAction(e -> handleCompleteTask());

        messageLabel = new Label();

        VBox rightBox = new VBox(10,
                new Label("Add new task:"),
                nameField,
                // descField,
                typeField,
                pointsField,
                addButton,
                completeButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(taskList);
        root.setRight(rightBox);
        root.setPadding(new Insets(10));

        VBox wrapper = new VBox(root);
        return wrapper;
    }

    private void refreshTaskList() {
        taskList.getItems().setAll(model.getTaskList());
    }

    private void handleAddTask() {
        String name = nameField.getText().trim();
        String type = typeField.getText().trim();
        if (name.isEmpty()) {
            messageLabel.setText("Task name cannot be empty.");
            return;
        }
        if (type.isEmpty()) {
            messageLabel.setText("Task type cannot be empty.");
            return;
        }

        int pts = 0;
        String ptsText = pointsField.getText().trim();
        if (!ptsText.isEmpty()) {
            try {
                pts = Integer.parseInt(ptsText);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Points must be a whole number.");
                return;
            }
        }

        model.addTask(name, type, pts);
        nameField.clear();
        typeField.clear();
        pointsField.clear();
        refreshTaskList();
        messageLabel.setText("Task added.");
    }

    private void handleCompleteTask() {
        Task selected = taskList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Select a task first.");
            return;
        }
        selected.completeTask(null); // no resident assigned by default
        refreshTaskList();
        messageLabel.setText("Task marked complete.");
    }

}
