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
 * changing the description of a trade.
 * The controller validates the input and updates the trade
 * description when saved.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ChangeTradeDescriptionController {

  private final Trade trade;
  private final Runnable onSaved;

  private TextField descriptionField;
  private Label messageLabel;

  /**
   * Constructs a ChangeTradeDescriptionController with the given
   * trade and save callback.
   *
   * @param trade the trade whose description will be changed
   * @param onSaved a callback executed after the description is successfully saved
   */
  public ChangeTradeDescriptionController(Trade trade, Runnable onSaved) {
    this.trade = trade;
    this.onSaved = onSaved;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the description of a trade.
   *
   * @return a Scene displaying the change trade description view
   */
  public Scene createScene() {
    Label label = new Label("Description:");
    descriptionField = new TextField(trade.getDescription());
    Button saveButton = new Button("Save");
    messageLabel = new Label();

    saveButton.setOnAction(e -> handleSave());

    VBox root = new VBox(10, label, descriptionField, saveButton, messageLabel);
    root.setPadding(new Insets(10));

    return new Scene(root, 350, 150);
  }

  /**
   * Handles saving the new trade description.
   * The method ensures the description is not empty
   * before updating the trade and executing the save callback.
   * A confirmation message is shown after a successful update.
   */
  private void handleSave() {
    String newDescription = descriptionField.getText().trim();

    if (newDescription.isEmpty()) {
      messageLabel.setText("Description cannot be empty.");
      return;
    }

    trade.setDescription(newDescription);

    if (onSaved != null) onSaved.run();

    messageLabel.setText("Changed Description succesfully");
  }
}
