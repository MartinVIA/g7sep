package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGUI extends Application {

    public void start(Stage primaryStage) {

        Button villager_menu = new Button("Villagers");
        Button trade_menu = new Button("Trades");
        Button task_menu = new Button("Tasks");
        Button Community_points_menu = new Button("Community Points");

        Button villager_add = new Button("Add New Villager");
        Button trade_add = new Button("Add New Trade");
        Button task_add = new Button("Add New Task");

        VBox layout = new VBox();
        layout.getChildren().add(villager_add);

        Scene scene = new Scene(layout, 300, 200);

        primaryStage.setTitle("Gee yuu waii");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
