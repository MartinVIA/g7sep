package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Task;

public class ManageTaskController {

    private final ClovervilleModelManager model;
    private final Task task;
    private final Runnable refreshCallback;

    public ManageTaskController(ClovervilleModelManager model, Task task, Runnable refreshCallback) {
        this.model = model;
        this.task = task;
        this.refreshCallback = refreshCallback;
    }

    public Scene createScene() {
        Label title = new Label("Manage " + task.getName());
        title.getStyleClass().add("title");

        Button changeNameBtn = new Button("Change name");
        Button changeDescriptionBtn = new Button("Change description");
        Button changePointsBtn = new Button("Change points");
        Button changeTypeBtn = new Button("Change type");
        Button markCompleteBtn = new Button("Mark complete");
        Button closeBtn = new Button("Close");

        changeNameBtn.setOnAction(e -> openChangeNamePopup());
        changeDescriptionBtn.setOnAction(e -> openChangeDescriptionPopup());
        changePointsBtn.setOnAction(e -> openChangePointsPopup());
        changeTypeBtn.setOnAction(e -> openChangeTypePopup());
        markCompleteBtn.setOnAction(e -> handleMarkComplete());
        closeBtn.setOnAction(e -> closeBtn.getScene().getWindow().hide());

        VBox root = new VBox(10, title, changeNameBtn, changeDescriptionBtn, changePointsBtn, changeTypeBtn,
                markCompleteBtn, closeBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 260, 255);
    }

    private void openChangeNamePopup() {
        Stage popup = new Stage();
        ChangeTaskNameController controller = new ChangeTaskNameController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change name of: " + task.getName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void openChangeDescriptionPopup() {
        Stage popup = new Stage();
        ChangeTaskDescriptionController controller = new ChangeTaskDescriptionController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change description of: " + task.getName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void openChangePointsPopup() {
        Stage popup = new Stage();
        ChangeTaskPointsController controller = new ChangeTaskPointsController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change points of: " + task.getName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void openChangeTypePopup() {
        Stage popup = new Stage();
        ChangeTaskTypeController controller = new ChangeTaskTypeController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change type of: " + task.getName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void handleMarkComplete() {
        Stage popup = new Stage();
        MarkTaskComplete controller = new MarkTaskComplete(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Mark task complete: " + task.getName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }
}
