package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

/**
 * Controller responsible for creating a view that allows a boost
 * to be applied to a specific resident.
 * If the resident already has a boost, the view will instead display
 * an informational message and prevent further interaction.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class AddBoostController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  /**
   * Constructs an AddBoostController with the given model and resident.
   *
   * @param model the model manager used to access application data
   * @param resident the resident for whom a boost may be applied
   */
  public AddBoostController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  /**
   * Creates and returns the JavaFX scene used to apply a boost
   * to the resident.
   * <p>
   * If the resident already has a boost, the scene will only display
   * a message informing the user that no boost is available.
   * Otherwise, the scene allows the user to apply a boost by pressing
   * a button.
   * </p>
   *
   * @return a Scene displaying the boost application view
   */
  public Scene createScene() {

    VBox root = new VBox(10);
    root.setPadding(new Insets(10));
    Label msg = new Label();

    // If they already have a boost → show message, nothing to do
    if (resident.getHasBoost()) {
      msg.setText("There is no boost available for this resident (already has a boost).");
      root.getChildren().add(msg);
      msg.setWrapText(true);
      return new Scene(root, 320, 100);
    }

    Label info = new Label("Apply a boost for this resident?");
    Button applyBtn = new Button("Apply boost");
    applyBtn.setOnAction(e -> {
      resident.setBoost(true);
      msg.setText("Boost applied to resident.");
    });

    root.getChildren().addAll(info, applyBtn, msg);
    return new Scene(root, 280, 140);
  }
}
