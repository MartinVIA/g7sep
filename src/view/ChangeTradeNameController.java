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
 * changing the name of a trade.
 * The controller validates the input and updates the trade
 * name when saved.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ChangeTradeNameController {

<<<<<<< HEAD
    private final Trade trade;
=======
  private final Trade trade;
  private final Runnable onSaved;
>>>>>>> 5ddce2b9b5d8ae4b4888a815c1f30aaedfeec26b

  private TextField nameField;
  private Label messageLabel;

<<<<<<< HEAD
    public ChangeTradeNameController(Trade trade) {
        this.trade = trade;
=======
  /**
   * Constructs a ChangeTradeNameController with the given
   * trade and save callback.
   *
   * @param trade the trade whose name will be changed
   * @param onSaved a callback executed after the name is successfully saved
   */
  public ChangeTradeNameController(Trade trade, Runnable onSaved) {
    this.trade = trade;
    this.onSaved = onSaved;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the name of a trade.
   *
   * @return a Scene displaying the change trade name view
   */
  public Scene createScene() {
    Label label = new Label("Offer name:");
    nameField = new TextField(trade.getStringName());
    Button saveButton = new Button("Save");
    messageLabel = new Label();

    saveButton.setOnAction(e -> handleSave());

    VBox root = new VBox(10, label, nameField, saveButton, messageLabel);
    root.setPadding(new Insets(10));

    return new Scene(root, 300, 150);
  }

  /**
   * Handles saving the new trade name.
   * The method ensures the name is not empty
   * before updating the trade and executing
   * the save callback.
   */
  private void handleSave() {
    String newName = nameField.getText().trim();
    if (newName.isEmpty()) {
      messageLabel.setText("Name cannot be empty.");
      return;
>>>>>>> 5ddce2b9b5d8ae4b4888a815c1f30aaedfeec26b
    }

    trade.setName(newName);

    if (onSaved != null) onSaved.run();

<<<<<<< HEAD
        VBox root = new VBox(10, label, nameField, saveButton, messageLabel);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 150);
    }

    private void handleSave() {
        String newName = nameField.getText().trim();
        if (newName.isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            return;
        }

        trade.setName(newName);

        // sucess message
        messageLabel.setText("Changed name succesfully");
    }
=======
    messageLabel.setText("Changed name succesfully");
  }
>>>>>>> 5ddce2b9b5d8ae4b4888a815c1f30aaedfeec26b
}
