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

        Button keepBtn = new Button("Confirm & keep trade");
        Button deleteBtn = new Button("Confirm & delete trade");
        Button cancelBtn = new Button("Cancel");

        keepBtn.setOnAction(e -> {
            var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
            if (!selectedResidents.isEmpty()) {
                for (Resident resident : selectedResidents) {
                    model.awardPointsToResident(resident.getId(), -trade.getPointCost());
                    model.awardPointsToResident(trade.getTraderId(), trade.getPointCost());
                }
            }
            keepBtn.getScene().getWindow().hide();
            // if (refreshCallback != null) {
            // refreshCallback.run();
            // }
        });

        deleteBtn.setOnAction(e -> {
            var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
            if (!selectedResidents.isEmpty()) {
                for (Resident resident : selectedResidents) {
                    model.awardPointsToResident(resident.getId(), -trade.getPointCost());
                    model.awardPointsToResident(trade.getTraderId(), trade.getPointCost());
                }
            }
            model.removeTrade(trade);
            deleteBtn.getScene().getWindow().hide();
            // if (refreshCallback != null) {
            // refreshCallback.run();
            // }
        });

        cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

        HBox buttons = new HBox(10, keepBtn, deleteBtn, cancelBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, title, instructions, residentListView, buttons);
        root.setPadding(new Insets(10));

        return new Scene(root, 400, 400);
    }
}
