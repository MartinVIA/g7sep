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
 * changing the description of a task.
 * The controller updates the task description based on
 * user input.
 *
 * @author Victor Tonu
 * @version 1.0
 */
public class ChangeTaskDescriptionController {

  private final ClovervilleModelManager model;
  private final Task task;
  private TextField descriptionField;
  private Label messageLabel;

  /**
   * Constructs a ChangeTaskDescriptionController with the given
   * model and task.
   *
   * @param model the model manager used to access application data
   * @param task the task whose description will be changed
   */
  public ChangeTaskDescriptionController(ClovervilleModelManager model, Task task) {
    this.model = model;
    this.task = task;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the description of the task.
   *
   * @return a Scene displaying the change task description view
   */
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

  /**
   * Handles saving the new task description.
   * The method updates the task with the value
   * entered in the text field and displays
   * a confirmation message.
   */
  private void handleSave() {
    String newDescription = descriptionField.getText().trim();
    task.setDescription(newDescription);
    messageLabel.setText("Description changed successfully.");
  }
}
