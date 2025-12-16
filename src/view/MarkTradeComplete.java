package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;
import model.Trade;

/**
 * Controller responsible for creating a view that allows
 * marking a trade as completed.
 * The controller lets the user select one or more residents
 * and transfers points between the selected resident(s) and
 * the trade owner. The trade can optionally be removed after
 * completion.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class MarkTradeComplete {

  private final ClovervilleModelManager model;
  private final Trade trade;

  /**
   * Constructs a MarkTradeComplete controller with the given
   * model and trade.
   *
   * @param model the model manager used to access application data
   * @param trade the trade to be marked as completed
   */
  public MarkTradeComplete(ClovervilleModelManager model, Trade trade) {
    this.model = model;
    this.trade = trade;
  }

  /**
   * Creates and returns the JavaFX scene used to mark
   * the trade as completed.
   * The user can select multiple residents and choose whether
   * to keep or delete the trade after completion.
   *
   * @return a Scene displaying the mark trade complete view
   */
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
        for (Resident tradee : selectedResidents) {
          model.awardPointsToResident(tradee.getId(), trade.getPointCost());
          model.awardPointsToResident(trade.getTraderId(), -trade.getPointCost());
        }
      }
      keepBtn.getScene().getWindow().hide();
    });

    deleteBtn.setOnAction(e -> {
      var selectedResidents = residentListView.getSelectionModel().getSelectedItems();
      if (!selectedResidents.isEmpty()) {
        for (Resident tradee : selectedResidents) {
          model.awardPointsToResident(tradee.getId(), trade.getPointCost());
          model.awardPointsToResident(trade.getTraderId(), -trade.getPointCost());
        }
      }
      model.removeTrade(trade);
      deleteBtn.getScene().getWindow().hide();
    });

    cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

    HBox buttons = new HBox(10, keepBtn, deleteBtn, cancelBtn);
    buttons.setPadding(new Insets(10));

    VBox root = new VBox(10, title, instructions, residentListView, buttons);
    root.setPadding(new Insets(10));

    return new Scene(root, 400, 400);
  }
}
