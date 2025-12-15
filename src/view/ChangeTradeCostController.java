package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trade;

/**
 * Controller responsible for creating a view that allows
 * changing the point cost of a trade.
 * The controller validates the input before applying
 * the new cost and notifies a callback when saved.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ChangeTradeCostController {

  private final Trade trade;
  private final Runnable onSaved;

  private TextField costField;
  private Label messageLabel;

  /**
   * Constructs a ChangeTradeCostController with the given
   * trade and save callback.
   *
   * @param trade the trade whose point cost will be changed
   * @param onSaved a callback executed after the cost is successfully saved
   */
  public ChangeTradeCostController(Trade trade, Runnable onSaved) {
    this.trade = trade;
    this.onSaved = onSaved;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the point cost of a trade.
   *
   * @return a Scene displaying the change trade cost view
   */
  public Scene createScene() {
    Label label = new Label("Cost (points):");
    costField = new TextField(String.valueOf(trade.getPointCost()));
    Button saveButton = new Button("Save");
    messageLabel = new Label();

    saveButton.setOnAction(e -> handleSave());

    VBox root = new VBox(10, label, costField, saveButton, messageLabel);
    root.setPadding(new Insets(10));

    return new Scene(root, 300, 150);
  }

  /**
   * Handles saving the new trade cost.
   * The method ensures the entered value is not empty
   * and is a valid whole number before updating the trade.
   * A confirmation message is shown after a successful save.
   */
  private void handleSave() {
    String text = costField.getText().trim();
    if (text.isEmpty()) {
      messageLabel.setText("Cost cannot be empty.");
      return;
    }

    int newCost;
    try {
      newCost = Integer.parseInt(text);
    } catch (NumberFormatException e) {
      messageLabel.setText("Cost must be a whole number.");
      return;
    }

    trade.setPointCost(newCost);

    if (onSaved != null) onSaved.run();

    messageLabel.setText("Changed Cost succesfully");
  }
}
