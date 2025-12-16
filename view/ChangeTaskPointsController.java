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
 * changing the number of points assigned to a task.
 * The controller validates the input before updating
 * the task points.
 *
 * @author Victor Tonu
 * @version 1.0
 */
public class ChangeTaskPointsController {

  private final ClovervilleModelManager model;
  private final Task task;
  private TextField pointsField;
  private Label messageLabel;

  /**
   * Constructs a ChangeTaskPointsController with the given
   * model and task.
   *
   * @param model the model manager used to access application data
   * @param task the task whose points will be changed
   */
  public ChangeTaskPointsController(ClovervilleModelManager model, Task task) {
    this.model = model;
    this.task = task;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the points value of a task.
   *
   * @return a Scene displaying the change task points view
   */
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

  /**
   * Handles saving the new task points value.
   * The method ensures the entered value is a valid
   * non-negative integer before updating the task.
   * An appropriate message is displayed to the user.
   */
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
