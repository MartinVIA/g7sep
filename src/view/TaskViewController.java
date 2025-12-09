package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Task;

public class TaskViewController {

    private final ClovervilleModelManager model;

    private ListView<Task> taskListView;
    private TextField nameField;
    private TextField descriptionField;
    private TextField pointsField;
    private ChoiceBox<String> typeChoiceBox;
    private Label messageLabel;

    public TaskViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    public Scene createScene() {
        return new Scene(createView(), 700, 400);
    }

    public VBox createView() {
        taskListView = new ListView<>();
        taskListView.setPrefWidth(420);

        refreshTaskList();

        Label titleLabel = new Label("Cloverville – Tasks");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));

        nameField = new TextField();
        nameField.setPromptText("Task name");

        descriptionField = new TextField();
        descriptionField.setPromptText("Task description");

        typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.getItems().addAll("green", "community");
        typeChoiceBox.setValue("green"); // default

        pointsField = new TextField();
        pointsField.setPromptText("Points awarded (e.g. 5)");

        Button addButton = new Button("Add task");
        addButton.setOnAction(e -> handleAddTask());

        messageLabel = new Label();

        VBox rightBox = new VBox(10,
                new Label("Add new task:"),
                nameField,
                descriptionField,
                typeChoiceBox,
                pointsField,
                addButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(taskListView);
        root.setRight(rightBox);
        root.setPadding(new Insets(10));

        VBox wrapper = new VBox(root);
        return wrapper;
    }

    private void refreshTaskList() {
        taskListView.getItems().setAll(model.getTaskList());
    }

    private void handleAddTask() {
        String name = nameField.getText().trim();
        String description = descriptionField.getText().trim();
        String type = typeChoiceBox.getValue();
        if (name.isEmpty()) {
            messageLabel.setText("Task name cannot be empty.");
            return;
        }
        if (type == null || type.isEmpty()) {
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

        model.addTask(name, type, pts, description);
        nameField.clear();
        descriptionField.clear();
        typeChoiceBox.setValue("green");
        pointsField.clear();
        refreshTaskList();
        messageLabel.setText("Task added.");
    }
}