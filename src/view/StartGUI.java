package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Resident;

public class StartGUI extends Application {

    private ClovervilleModelManager model;
    private TableView<Resident> table;

    public void start(Stage primaryStage) {

        model = new ClovervilleModelManager();

        Button resident_menu = new Button("Residents");
        Button trade_menu = new Button("Trades");
        Button task_menu = new Button("Tasks");
        Button Community_points_menu = new Button("Community Points");

        Button resident_add = new Button("Add New Resident");
        resident_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Resident");
            TextField firstNameField = new TextField();
            firstNameField.setPromptText("First Name");
            TextField lastNameField = new TextField();
            lastNameField.setPromptText("Last Name");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                popup.close();
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(firstNameField, lastNameField, submitButton);
            layout.setPadding(new Insets(10, 10, 10, 10));

            popup.setScene(new Scene(layout, 300, 150));
            popup.show();
        });
        Button trade_add = new Button("Add New Trade");

        Button task_add = new Button("Add New Task");

        Button resident_edit = new Button("Edit existing resident");

        HBox bottom_menu = new HBox();
        bottom_menu.getChildren().addAll(resident_add, resident_edit);
        bottom_menu.setSpacing(10);
        bottom_menu.setPrefWidth(300);

        HBox nav_bar = new HBox();
        nav_bar.getChildren().addAll(resident_menu, trade_menu, task_menu, Community_points_menu);
        nav_bar.setPrefWidth(300);

        TableView table = new TableView<>();
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn idCol = new TableColumn("ID");
        TableColumn pointsCol = new TableColumn("Points");
        TableColumn boostsCol = new TableColumn("Boosts");
        table.setEditable(true);
        table.getColumns().addAll(firstNameCol, lastNameCol, idCol, pointsCol, boostsCol);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(table);
        ListView<String> listView = new ListView<>();

        VBox list = new VBox();
        list.setSpacing(5);
        list.setPadding(new Insets(10, 0, 0, 10));
        list.getChildren().add(table);

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
