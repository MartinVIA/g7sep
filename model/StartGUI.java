import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGUI extends Application {

    public void start(Stage primaryStage) {
        Button button1 = new Button("ButtOff");

        VBox layout = new VBox();
        layout.getChildren().add(button1);

        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Simple JavaFX Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
