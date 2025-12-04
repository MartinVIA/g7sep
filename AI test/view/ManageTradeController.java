package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Trade;

public class ManageTradeController {

    private final ClovervilleModelManager model;
    private final Trade trade;
    private final Runnable refreshCallback;

    public ManageTradeController(ClovervilleModelManager model, Trade trade, Runnable refreshCallback) {
        this.model = model;
        this.trade = trade;
        this.refreshCallback = refreshCallback;
    }

    public Scene createScene() {
        Label title = new Label("Manage trade: " + trade.getStringName());

        Button changeNameBtn = new Button("Change offer name");
        Button changeDescBtn = new Button("Change description");
        Button changeCostBtn = new Button("Change cost");
        Button closeBtn = new Button("Close");

        changeNameBtn.setOnAction(e -> openChangeNamePopup());
        changeDescBtn.setOnAction(e -> openChangeDescriptionPopup());
        changeCostBtn.setOnAction(e -> openChangeCostPopup());
        closeBtn.setOnAction(e -> closeBtn.getScene().getWindow().hide());

        VBox root = new VBox(10, title, changeNameBtn, changeDescBtn, changeCostBtn, closeBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 250, 200);
    }

    private void openChangeNamePopup() {
        Stage popup = new Stage();
        ChangeTradeNameController controller = new ChangeTradeNameController(trade, refreshCallback);
        popup.setScene(controller.createScene());
        popup.setTitle("Change name of: " + trade.getStringName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void openChangeDescriptionPopup() {
        Stage popup = new Stage();
        ChangeTradeDescriptionController controller = new ChangeTradeDescriptionController(trade, refreshCallback);
        popup.setScene(controller.createScene());
        popup.setTitle("Change description of: " + trade.getStringName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }

    private void openChangeCostPopup() {
        Stage popup = new Stage();
        ChangeTradeCostController controller = new ChangeTradeCostController(trade, refreshCallback);
        popup.setScene(controller.createScene());
        popup.setTitle("Change cost of: " + trade.getStringName());
        popup.setOnHidden(ev -> {
            if (refreshCallback != null) {
                refreshCallback.run();
            }
        });
        popup.show();
    }
}
