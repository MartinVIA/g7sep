package model;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
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
        bottom_menu.getChildren().addAll(villager_add,villager_edit);
        bottom_menu.setSpacing(10);
        bottom_menu.setPrefWidth(300);

        HBox nav_bar = new HBox();
        nav_bar.getChildren().addAll(villager_menu,trade_menu,task_menu,Community_points_menu);
        nav_bar.setPrefWidth(300);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        TableView table = new TableView<>();
        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        ListView<String> listView = new ListView<>();

         
        final VBox list = new VBox();
        list.setSpacing(5);
        list.setPadding(new Insets(10, 0, 0, 10));
        list.getChildren().addAll(label, table);

        BorderPane root = new BorderPane();
        root.setTop(nav_bar);
        root.setCenter(list);
        root.setBottom(bottom_menu);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("I don't wanna do this anympre send help");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
