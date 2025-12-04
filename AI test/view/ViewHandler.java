package view;

import javafx.stage.Stage;
import model.ClovervilleModelManager;
import view.ResidentViewController;
import view.TradeViewController;
import view.TaskViewController;

public class ViewHandler {

  // this class is there for the overall window (main stage), it holds a reference
  // to model and opens a specific viewController, either the trading system or
  // the point system

  private Stage mainStage;
  private ClovervilleModelManager model;
  private ResidentViewController residentViewController;
  private TradeViewController tradeViewController;
  private TaskViewController taskViewController;

  public ViewHandler(ClovervilleModelManager model) {
    this.model = model;
  }

  public void start(Stage stage) {
    this.mainStage = stage;
    openResidentView();
    // opens a screen specificly the main page
  }

  public void openResidentView() {
    ResidentViewController control = new ResidentViewController(model);
    mainStage.setScene(control.createScene());
    mainStage.setTitle("Cloverville's Resident");
    mainStage.show();
  }

}