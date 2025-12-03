package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;

import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

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
    }

    public void handleCreateTrade() {
        // code i don t know how to do
    }
}