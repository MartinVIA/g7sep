package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

/**
 * Controller responsible for creating a view that allows
 * removing a boost from a resident.
 * If the resident does not have a boost, an informational
 * message is shown instead.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class RemoveBoostController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  /**
   * Constructs a RemoveBoostController with the given
   * model and resident.
   *
   * @param model the model manager used to access application data
   * @param resident the resident whose boost will be removed
   */
  public RemoveBoostController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  /**
   * Creates and returns the JavaFX scene used to remove
   * a boost from the resident.
   * If the resident has no boost, the scene will display
   * an informational message only.
   *
   * @return a Scene displaying the remove boost view
   */
  public Scene createScene() {

    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    Label msg = new Label();

    if (!resident.getHasBoost()) {
      msg.setText("The resident doesn't have any boost.");
      msg.getStyleClass().add("title");
      root.setPadding(new Insets(0));
      root.getChildren().add(msg);
      return new Scene(root, 300, 80);
    }

    Label info = new Label("Resident currently has a boost.");
    info.getStyleClass().add("title");
    Button removeBtn = new Button("Remove boost");
    removeBtn.setOnAction(e -> {
      resident.setBoost(false);
      msg.setText("Boost removed from resident.");
    });

    root.getChildren().addAll(info, removeBtn, msg);
    return new Scene(root, 280, 110);
  }
}
