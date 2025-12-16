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

/**
 * Controller responsible for creating a view that allows
 * changing the name of a task.
 * The controller validates the input before updating
 * the task name.
 *
 * @author Victor Tonu
 * @version 1.0
 */
public class ChangeTaskNameController {

  private final ClovervilleModelManager model;
  private final Task task;
  private TextField nameField;
  private Label messageLabel;

  /**
   * Constructs a ChangeTaskNameController with the given
   * model and task.
   *
   * @param model the model manager used to access application data
   * @param task the task whose name will be changed
   */
  public ChangeTaskNameController(ClovervilleModelManager model, Task task) {
    this.model = model;
    this.task = task;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the name of a task.
   *
   * @return a Scene displaying the change task name view
   */
  public Scene createScene() {
    Label label = new Label("New name:");
    nameField = new TextField(task.getName());
    Button saveButton = new Button("Confirm name change");
    saveButton.setOnAction(e -> handleSave());
    messageLabel = new Label();

    VBox root = new VBox(10, label, nameField, saveButton, messageLabel);
    root.setPadding(new Insets(10));

    return new Scene(root, 300, 150);
  }

  /**
   * Handles saving the new task name.
   * The method ensures the name is not empty
   * before updating the task and displaying
   * a confirmation message.
   */
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
