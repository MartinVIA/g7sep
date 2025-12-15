package view;

import javafx.stage.Stage;
import model.ClovervilleModelManager;

/**
 * Handles switching between different views in the application.
 * This class maintains a reference to the primary stage and
 * is responsible for opening the main resident view at startup.
 *
 * @author Adam Terelak
 * @author Leon de Kuijper
 * @author Martin Chavez
 * @author Loke Hansen
 * @author Victor Țonu
 * @version 1.0
 */
public class ViewHandler {

  private Stage mainStage;
  private ClovervilleModelManager model;
  private ResidentViewController residentViewController;
  private TradeViewController tradeViewController;
  private TaskViewController taskViewController;

  /**
   * Constructs a ViewHandler with the given model.
   *
   * @param model the model manager used to share data between views
   */
  public ViewHandler(ClovervilleModelManager model) {
    this.model = model;
  }

  /**
   * Starts the view handler by setting the main stage
   * and opening the initial resident view.
   *
   * @param stage the primary stage of the application
   */
  public void start(Stage stage) {
    this.mainStage = stage;
    openResidentView();
  }

  /**
   * Opens the resident view and sets it as the current
   * scene on the main stage.
   */
  public void openResidentView() {
    ResidentViewController control = new ResidentViewController(model);
    mainStage.setScene(control.createScene());
    mainStage.setTitle("Cloverville's Resident");
    mainStage.show();
  }
}
