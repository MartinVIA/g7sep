package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trade;

public class ChangeTradeNameController {

    private final Trade trade;

    private TextField nameField;
    private Label messageLabel;

    public ChangeTradeNameController(Trade trade) {
        this.trade = trade;
    }

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
}
