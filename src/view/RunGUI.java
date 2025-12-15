package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ClovervilleModelManager;

/**
 * Entry point for launching the Cloverville JavaFX application.
 * This class initializes the model and starts the GUI using
 * the ViewHandler.
 *
 * @author Adam Terelak
 * @author Leon de Kuijper
 * @author Martin Chavez
 * @author Loke Hansen
 * @author Victor Țonu
 * @version 1.0
 */
public class RunGUI extends Application {

  /**
   * Starts the JavaFX application.
   * The method initializes the model, creates the view handler,
   * and opens the primary application window.
   *
   * @param primaryStage the main stage provided by the JavaFX runtime
   * @throws Exception if the application fails to start
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    ClovervilleModelManager model = new ClovervilleModelManager();
    ViewHandler viewHandler = new ViewHandler(model);
    viewHandler.start(primaryStage);
  }

  /**
   * Launches the JavaFX application.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    launch(args);
  }
}
