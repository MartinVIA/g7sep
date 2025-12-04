package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Task;

public class TaskViewController {

    private final ClovervilleModelManager model;

    private TableView<Task> taskTable;
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
        taskTable = new TableView<>();
        taskTable.setPrefWidth(420);

        TableColumn<Task, String> nameCol = new TableColumn<>("Task Description");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Task, Integer> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));

        TableColumn<Task, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Task, Boolean> statusCol = new TableColumn<>("Complete");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("completeTask"));

        taskTable.getColumns().addAll(nameCol, pointsCol, typeCol, statusCol);
        refreshTaskList();

        Label titleLabel = new Label("Cloverville – Tasks");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));

        nameField = new TextField();
        nameField.setPromptText("Task name");

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
                typeField,
                pointsField,
                addButton,
                completeButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(taskTable);
        root.setRight(rightBox);
        root.setPadding(new Insets(10));

        VBox wrapper = new VBox(root);
        return wrapper;
    }

    private void refreshTaskList() {
        taskTable.getItems().setAll(model.getTaskList());
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
        Task selected = taskTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Select a task first.");
            return;
        }
        selected.completeTask(null); // no resident assigned by default
        refreshTaskList();
        messageLabel.setText("Task marked complete.");
    }

}
