package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Task;

/**
 * Controller responsible for creating a view that allows managing a specific
 * task. The controller provides options for modifying task details, changing
 * its type, marking it as completed, and refreshing related views when changes
 * occur.
 *
 * @author Victor Tonu
 * @version 1.0
 */
public class ManageTaskController {

    private final ClovervilleModelManager model;
    private final Task task;

<<<<<<< HEAD
    public ManageTaskController(ClovervilleModelManager model, Task task) {
=======
    /**
     * Constructs a ManageTaskController with the given model, task, and refresh
     * callback.
     *
     * @param model the model manager used to access application data
     * @param task the task to be managed
     * @param refreshCallback a callback executed after task changes
     */
    public ManageTaskController(ClovervilleModelManager model, Task task, Runnable refreshCallback) {
>>>>>>> 5ddce2b9b5d8ae4b4888a815c1f30aaedfeec26b
        this.model = model;
        this.task = task;
    }

    /**
     * Creates and returns the JavaFX scene used to manage the selected task.
     *
     * @return a Scene displaying the manage task view
     */
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

    /**
     * Opens a popup window for changing the task name. The refresh callback is
     * executed when the window closes.
     */
    private void openChangeNamePopup() {
        Stage popup = new Stage();
        ChangeTaskNameController controller = new ChangeTaskNameController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change name of: " + task.getName());
        popup.show();
    }

    /**
     * Opens a popup window for changing the task description. The refresh
     * callback is executed when the window closes.
     */
    private void openChangeDescriptionPopup() {
        Stage popup = new Stage();
        ChangeTaskDescriptionController controller = new ChangeTaskDescriptionController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change description of: " + task.getName());
        popup.show();
    }

    /**
     * Opens a popup window for changing the task points. The refresh callback
     * is executed when the window closes.
     */
    private void openChangePointsPopup() {
        Stage popup = new Stage();
        ChangeTaskPointsController controller = new ChangeTaskPointsController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change points of: " + task.getName());
        popup.show();
    }

    /**
     * Opens a popup window for changing the task type. The refresh callback is
     * executed when the window closes.
     */
    private void openChangeTypePopup() {
        Stage popup = new Stage();
        ChangeTaskTypeController controller = new ChangeTaskTypeController(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Change type of: " + task.getName());
        popup.show();
    }

    /**
     * Opens a popup window for marking the task as completed. The refresh
     * callback is executed when the window closes.
     */
    private void handleMarkComplete() {
        Stage popup = new Stage();
        MarkTaskComplete controller = new MarkTaskComplete(model, task);
        popup.setScene(controller.createScene());
        popup.setTitle("Mark task complete: " + task.getName());
        popup.show();
    }
}
