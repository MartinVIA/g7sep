package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

public class TradeViewController {

    private final ClovervilleModelManager model;
    private ListView<Resident> residentListView;
    private Label messageLabel;
    private TextField offerNameField;
    private TextField priceField;
    private TextField description;

    public TradeViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    private void refreshResidentList() {
        residentListView.getItems().setAll(model.getAllResidents());
    }

    public Scene createScene() {
        residentListView = new ListView<>();
        residentListView.setPrefWidth(320);
        refreshResidentList();

        // titel
        Label titleLabel = new Label("Cloverville – Trades");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));

        offerNameField = new TextField();
        offerNameField.setPromptText("Offer's name");

        description = new TextField();
        description.setPromptText("Description of the trade");

        priceField = new TextField();
        priceField.setPromptText("The price fo the trade");

        Button confirmationButton = new Button("Confirm trade");
        confirmationButton.setOnAction(e -> handleCreateTrade());

        Button cancelButton = new Button("Close");
        cancelButton.requestFocus();
        cancelButton.setOnAction(e -> cancelButton.getScene().getWindow().hide());

        messageLabel = new Label();
        VBox rightBox = new VBox(
                10,
                new Label("Trade details:"),
                offerNameField,
                description,
                priceField,
                new Label("Select requesting resident:"),
                residentListView,
                confirmationButton,
                cancelButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(rightBox);
        root.setPadding(new Insets(10));

        return new Scene(root, 600, 400);
    }

    private void handleCreateTrade() {
        String name = offerNameField.getText().trim();
        String desc = description.getText().trim();
        String priceText = priceField.getText().trim();
        Resident selected = residentListView.getSelectionModel().getSelectedItem();

        if (name.isEmpty() || desc.isEmpty()) {
            messageLabel.setText("Offer name and description cannot be empty.");
            return;
        }

        if (selected == null) {
            messageLabel.setText("Select the resident who requested this trade.");
            return;
        }

        try {
            int price = Integer.parseInt(priceText);
            model.addTrade(name, desc, selected, price);
            messageLabel.setText("Trade created with point cost: " + price);
        } catch (NumberFormatException e) {
            messageLabel.setText("Price must be an number. Try again.");
            return;
        }

        offerNameField.clear();
        description.clear();
        priceField.clear();
        refreshResidentList();
        residentListView.getSelectionModel().clearSelection();

    }

}
