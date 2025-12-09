package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.GreenActions;
import model.Resident;
import model.Trade;

public class MarkTradeComplete {

    private final ClovervilleModelManager model;
    private final Trade trade;

    public MarkTradeComplete(ClovervilleModelManager model, Trade trade) {
        this.model = model;
        this.trade = trade;
    }

    public Scene createScene() {
        Label title = new Label("Mark trade complete: " + trade.getName());
        Label instructions = new Label("Select residents who completed the task:");

        ListView<Resident> residentListView = new ListView<>();
        residentListView.getItems().setAll(model.getAllResidents());
        residentListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        Button keepBtn = new Button("Confirm trade completion.");
        Button cancelBtn = new Button("Cancel");

        keepBtn.setOnAction(e -> {
            var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
            if (!selectedResidents.isEmpty()) {
            } else {
                AlertBox.display("No residents selected",
                        "Please select at least one resident to mark the trade as complete.");
            }
            keepBtn.getScene().getWindow().hide();
            // if (refreshCallback != null) {
            // refreshCallback.run();
            // }
        });

        cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

        HBox buttons = new HBox(10, keepBtn, cancelBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, title, instructions, residentListView, buttons);
        root.setPadding(new Insets(10));

        return new Scene(root, 400, 400);
    }
}
