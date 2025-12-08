package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Task;

public class ChangeTaskNameController {

    private final ClovervilleModelManager model;
    private final Task task;
    private TextField nameField;
    private Label messageLabel;

    public ChangeTaskNameController(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label label = new Label("New name:");
        nameField = new TextField(task.getName());
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> handleSave());
        messageLabel = new Label();

        VBox root = new VBox(10, label, nameField, saveButton, messageLabel);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 150);
    }

    private void handleSave() {
        String newName = nameField.getText().trim();
        if (newName.isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            return;
        }
        task.setName(newName);
        messageLabel.setText("Name changed successfully.");
    }
}
