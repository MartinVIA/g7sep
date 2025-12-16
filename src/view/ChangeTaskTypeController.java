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

/**
 * Controller responsible for creating a view that allows
 * changing the type of a task.
 * The controller lets the user select a new task type
 * and applies the change if confirmed.
 *
 * @author Victor Tonu
 * @version 1.0
 */
public class ChangeTaskTypeController {

  private final ClovervilleModelManager model;
  private final Task task;

  /**
   * Constructs a ChangeTaskTypeController with the given
   * model and task.
   *
   * @param model the model manager used to access application data
   * @param task the task whose type will be changed
   */
  public ChangeTaskTypeController(ClovervilleModelManager model, Task task) {
    this.model = model;
    this.task = task;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the type of a task.
   * The scene allows the user to select between the
   * available task types and save or cancel the change.
   *
   * @return a Scene displaying the change task type view
   */
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
        task.setType(newType);
      }
      saveBtn.getScene().getWindow().hide();
    });

    cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

    VBox root = new VBox(10, title, currentType, new Label("New type:"), typeChoiceBox, saveBtn, cancelBtn);
    root.setPadding(new Insets(10));

    return new Scene(root, 300, 200);
  }
}
