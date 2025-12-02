package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        Button villager_edit = new Button("Edit existing villager");

        HBox bottom_menu = new HBox();
        bottom_menu.getChildren().addAll(villager_add, villager_edit);
        bottom_menu.setSpacing(10);
        bottom_menu.setPrefWidth(300);

        HBox nav_bar = new HBox();
        nav_bar.getChildren().addAll(villager_menu, trade_menu, task_menu, Community_points_menu);
        nav_bar.setPrefWidth(300);

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(12);

        root.add(bottom_menu, 0, 2);
        root.add(nav_bar, 0, 0);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
