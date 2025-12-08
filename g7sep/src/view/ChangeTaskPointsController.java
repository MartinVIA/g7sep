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

public class ChangeTaskPointsController {

    private final ClovervilleModelManager model;
    private final Task task;
    private TextField pointsField;
    private Label messageLabel;

    public ChangeTaskPointsController(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label label = new Label("New points:");
        pointsField = new TextField(String.valueOf(task.getPoints()));
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> handleSave());
        messageLabel = new Label();

        VBox root = new VBox(10, label, pointsField, saveButton, messageLabel);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 150);
    }

    private void handleSave() {
        String pointsText = pointsField.getText().trim();
        try {
            int newPoints = Integer.parseInt(pointsText);
            if (newPoints < 0) {
                messageLabel.setText("Points must be non-negative.");
                return;
            }
            task.setPoints(newPoints);
            messageLabel.setText("Points changed successfully.");
        } catch (NumberFormatException e) {
            messageLabel.setText("Points must be a valid number.");
        }
    }
}
