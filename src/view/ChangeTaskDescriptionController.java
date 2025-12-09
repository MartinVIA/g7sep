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

public class ChangeTaskDescriptionController {

    private final ClovervilleModelManager model;
    private final Task task;
    private TextField descriptionField;
    private Label messageLabel;

    public ChangeTaskDescriptionController(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label label = new Label("New description:");
        descriptionField = new TextField(task.getDescription());
        Button saveButton = new Button("Confirm description change");
        saveButton.setOnAction(e -> handleSave());
        messageLabel = new Label();

        VBox root = new VBox(10, label, descriptionField, saveButton, messageLabel);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 150);
    }

    private void handleSave() {
        String newDescription = descriptionField.getText().trim();
        task.setDescription(newDescription);
        messageLabel.setText("Description changed successfully.");
    }
}
