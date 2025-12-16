package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Trade;

/**
 * Controller responsible for creating a view that allows
 * managing a specific trade.
 * The controller provides options for changing the trade’s
 * name, description, and point cost.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ManageTradeController {

  private final ClovervilleModelManager model;
  private final Trade trade;
  private final Runnable onClose;

  /**
   * Constructs a ManageTradeController with the given
   * model, trade, and close callback.
   *
   * @param model the model manager used to access application data
   * @param trade the trade to be managed
   * @param onClose a callback executed when changes are made
   */
  public ManageTradeController(ClovervilleModelManager model, Trade trade, Runnable onClose) {
    this.model = model;
    this.trade = trade;
    this.onClose = onClose;
  }

  /**
   * Creates and returns the JavaFX scene used to manage
   * the selected trade.
   *
   * @return a Scene displaying the manage trade view
   */
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

  /**
   * Opens a popup window for changing the trade name.
   */
  private void openChangeNamePopup() {
    Stage popup = new Stage();
    ChangeTradeNameController controller =
        new ChangeTradeNameController(trade, onClose);
    popup.setScene(controller.createScene());
    popup.setTitle("Change name of: " + trade.getStringName());
    popup.show();
  }

  /**
   * Opens a popup window for changing the trade description.
   */
  private void openChangeDescriptionPopup() {
    Stage popup = new Stage();
    ChangeTradeDescriptionController controller =
        new ChangeTradeDescriptionController(trade, onClose);
    popup.setScene(controller.createScene());
    popup.setTitle("Change description of: " + trade.getStringName());
    popup.show();
  }

  /**
   * Opens a popup window for changing the trade cost.
   */
  private void openChangeCostPopup() {
    Stage popup = new Stage();
    ChangeTradeCostController controller =
        new ChangeTradeCostController(trade, onClose);
    popup.setScene(controller.createScene());
    popup.setTitle("Change cost of: " + trade.getStringName());
    popup.show();
  }
}
