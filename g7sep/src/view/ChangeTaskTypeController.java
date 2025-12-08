package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Task;

public class ChangeTaskTypeController {

    private final ClovervilleModelManager model;
    private final Task task;

    public ChangeTaskTypeController(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label title = new Label("Change type for: " + task.getName());
        Label currentType = new Label("Current type: " + task.getType());

        ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.getItems().addAll("green", "community");
        typeChoiceBox.setValue(task.getType());

        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");

        saveBtn.setOnAction(e -> {
            String newType = typeChoiceBox.getValue();
            if (newType != null && !newType.equals(task.getType())) {
                model.updateTask(task, task.getName(), task.getDescription(), newType, task.getPoints());
            }
            saveBtn.getScene().getWindow().hide();
        });

        cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

        VBox root = new VBox(10, title, currentType, new Label("New type:"), typeChoiceBox, saveBtn, cancelBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 200);
    }
}
