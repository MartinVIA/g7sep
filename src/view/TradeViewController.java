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

public class TradeViewController {
    private final ClovervilleModelManager model;

    private ListView<Resident> residentListView;

    private Label messageLabel;

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
    }

}