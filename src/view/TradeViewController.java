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

/**
 * Controller responsible for creating a view that allows creating new trades.
 * The view lets the user enter trade details and select a resident who requests
 * the trade.
 * @author Loke Hansen
 * @version 1.0
 */
public class TradeViewController {

    private final ClovervilleModelManager model;
    private ListView<Resident> residentListView;
    private Label messageLabel;
    private TextField offerNameField;
    private TextField priceField;
    private TextField description;

    /**
     * Constructs a TradeViewController with the given model.
     * @param model the model manager used to access and modify trade data
     */
    public TradeViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    /**
     * Creates and returns the JavaFX scene used to create a new trade.
     * @return a Scene displaying the trade creation view
     */
    public Scene createScene() {
        residentListView = new ListView<>();
        residentListView.setPrefWidth(320);
        refreshResidentList();

        Label titleLabel = new Label("Cloverville - Trades");
        titleLabel.getStyleClass().add("title");
        Label subtitleLabel = new Label("Trade details:");
        subtitleLabel.getStyleClass().add("title");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(2,5,7,5));

        
        Label offerLabel= new Label("Offer's name");
        offerNameField = new TextField();
        offerNameField.setPromptText("Offer name");
        
        Label descrptionLabel = new Label("Description of the trade");
        description = new TextField();
        description.setPromptText("Trade description");
        
        Label priceLabel = new Label("The price of the trade");
        priceField = new TextField();
        priceField.setPromptText("Trade price");

        Button confirmationButton = new Button("Confirm trade");
        confirmationButton.setOnAction(e -> handleCreateTrade());

        Button cancelButton = new Button("Close");
        cancelButton.requestFocus();
        cancelButton.setOnAction(e -> cancelButton.getScene().getWindow().hide());

        messageLabel = new Label();
        VBox rightBox = new VBox(
                10,
                subtitleLabel,
                offerLabel,
                offerNameField,
                descrptionLabel,
                description,
                priceLabel,
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

        return new Scene(root, 600, 500);
    }

    /**
     * Handles creation of a new trade using the entered values. The method
     * validates the input fields and selected resident before adding the trade
     * through the model.
     */
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
            messageLabel.setText("Price must be a number. Try again.");
            return;
        }

        offerNameField.clear();
        description.clear();
        priceField.clear();
        residentListView.getSelectionModel().clearSelection();
    }

    /**
     * Refreshes the resident list view by reloading residents from the model.
     */
    private void refreshResidentList() {
        residentListView.getItems().setAll(model.getAllResidents());
    }
}
