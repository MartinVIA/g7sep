package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import view.ViewHandler;

public class RunGUI extends Application {

  public void start(Stage primaryStage) throws Exception {
    ClovervilleModelManager model = new ClovervilleModelManager();
    // creates model with everything we need villagers, tasks, trades, points
    ViewHandler viewHandler = new ViewHandler(model);
    // creates the corresponder with the GUI for functionality
    viewHandler.start(primaryStage);
    // opens the window
  }

  public static void main(String[] args) {
    launch(args);
  }
  // launches it obv
}
