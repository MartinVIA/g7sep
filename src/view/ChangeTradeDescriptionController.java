package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Trade;

public class ChangeTradeDescriptionController {

    private final Trade trade;

    private TextField descriptionField;
    private Label messageLabel;

    public ChangeTradeDescriptionController(Trade trade) {
        this.trade = trade;
    }

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

    private void handleSave() {
        String newDescription = descriptionField.getText().trim();

        if (newDescription.isEmpty()) {
            messageLabel.setText("Description cannot be empty.");
            return;
        }

        // Update the trade object
        trade.setDescription(newDescription);

        // sucess message
        messageLabel.setText("Changed Description succesfully");
    }
}
