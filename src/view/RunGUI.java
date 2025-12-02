package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ClovervilleModelManager;

public class RunGUI extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Create the model
    ClovervilleModelManager model = new ClovervilleModelManager();

    // Create the view handler and let it open the first screen
    ViewHandler viewHandler = new ViewHandler(model);
    viewHandler.start(primaryStage);
  }

  public static void main(String[] args) {
    launch(args); // starts JavaFX and calls start(...)
  }
}
