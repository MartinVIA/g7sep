package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Trade;

public class ChangeTradeCostController {

    private final Trade trade;
    private final Runnable onSaved;

    private TextField costField;
    private Label messageLabel;

    public ChangeTradeCostController(Trade trade, Runnable onSaved) {
        this.trade = trade;
        this.onSaved = onSaved;
    }

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

        if (onSaved != null)
            onSaved.run();

        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }
}
