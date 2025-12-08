package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;
import model.Task;

public class MarkCompleteController {

    private final ClovervilleModelManager model;
    private final Task task;

    public MarkCompleteController(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label title = new Label("Mark task complete: " + task.getName());
        Label instructions = new Label("Select residents who completed the task:");

        ListView<Resident> residentListView = new ListView<>();
        residentListView.getItems().setAll(model.getAllResidents());
        residentListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        Button keepBtn = new Button("Keep Task");
        Button deleteBtn = new Button("Delete Task");
        Button cancelBtn = new Button("Cancel");

        keepBtn.setOnAction(e -> {
            var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
            if (!selectedResidents.isEmpty()) {
                for (Resident resident : selectedResidents) {
                    task.completeTask(resident);
                }
            }
            keepBtn.getScene().getWindow().hide();
            // if (refreshCallback != null) {
            // refreshCallback.run();
            // }
        });

        deleteBtn.setOnAction(e -> {
            var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
            if (!selectedResidents.isEmpty()) {
                for (Resident resident : selectedResidents) {
                    task.completeTask(resident);
                }
            }
            model.getTaskList().remove(task);
            deleteBtn.getScene().getWindow().hide();
            // if (refreshCallback != null) {
            // refreshCallback.run();
            // }
        });

        cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

        HBox buttons = new HBox(10, keepBtn, deleteBtn, cancelBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, title, instructions, residentListView, buttons);
        root.setPadding(new Insets(10));

        return new Scene(root, 400, 400);
    }
}
