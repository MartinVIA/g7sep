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
import model.ClovervilleModelManager;

public class StartGUI extends Application {
    private ClovervilleModelManager model;

    public void start(Stage primaryStage) {
        model = new ClovervilleModelManager();
        Button resident_menu = new Button("Residents");
        Button trade_menu = new Button("Trades");
        Button task_menu = new Button("Tasks");
        Button Community_points_menu = new Button("Community Points");

        Button resident_add = new Button("Add New Resident");
        resident_add.setOnAction(e -> {
            Stage popup = new Stage();
            ResidentViewController controller = new ResidentViewController(model);
            popup.setScene(controller.createScene());
            popup.setTitle("Cloverville's Resident");
            popup.show();
        });

        Button trade_add = new Button("Add New Trade");
        Button task_add = new Button("Add New Task");
        Button community_points_add = new Button("Add Community Points");

        Button resident_edit = new Button("Edit existing resident");
        Button trade_edit = new Button("Edit existing Trade");
        Button task_edit = new Button("Edit existing Task");
        Button community_points_edit = new Button("Edit Community Points");

        HBox bottom_menu_resident = new HBox();
        bottom_menu_resident.getChildren().addAll(resident_add, resident_edit);
        bottom_menu_resident.setSpacing(10);
        bottom_menu_resident.setPrefWidth(300);

        HBox bottom_menu_trades = new HBox();
        bottom_menu_trades.getChildren().addAll(trade_add, trade_edit);
        bottom_menu_trades.setSpacing(10);
        bottom_menu_trades.setPrefWidth(300);

        HBox bottom_menu_tasks = new HBox();
        bottom_menu_tasks.getChildren().addAll(task_add, task_edit);
        bottom_menu_tasks.setSpacing(10);
        bottom_menu_tasks.setPrefWidth(300);

        HBox bottom_menu_community_points = new HBox();
        bottom_menu_community_points.getChildren().addAll(community_points_add, community_points_edit);
        bottom_menu_community_points.setPrefWidth(300);
        bottom_menu_community_points.setSpacing(10);

        HBox nav_bar = new HBox();
        nav_bar.getChildren().addAll(resident_menu, trade_menu, task_menu, Community_points_menu);
        nav_bar.setPrefWidth(300);

        // residents list
        TableView residentsTable = new TableView<>();
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn idCol = new TableColumn("ID");
        TableColumn pointsCol = new TableColumn("Points");
        TableColumn boostsCol = new TableColumn("Boosts");
        residentsTable.setEditable(true);
        residentsTable.getColumns().addAll(firstNameCol, lastNameCol, idCol, pointsCol, boostsCol);

        VBox residentsBox = new VBox();
        residentsBox.setSpacing(5);
        residentsBox.setPadding(new Insets(10, 0, 0, 10));
        residentsBox.getChildren().add(residentsTable);

        // trades list
        TableView tradesTable = new TableView<>();
        TableColumn sellerCol = new TableColumn("Seller");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn offerCol = new TableColumn("Offer");
        TableColumn descCol = new TableColumn("Description");
        tradesTable.setEditable(true);
        tradesTable.getColumns().addAll(sellerCol, priceCol, offerCol, descCol);

        VBox tradesBox = new VBox();
        tradesBox.setSpacing(5);
        tradesBox.setPadding(new Insets(10, 0, 0, 10));
        tradesBox.getChildren().add(tradesTable);

        // task list
        TableView tasksTable = new TableView<>();
        TableColumn taskDescCol = new TableColumn("Task Description");
        TableColumn taskPointsCol = new TableColumn("Points");
        TableColumn taskAssignedCol = new TableColumn("Assigned To");
        TableColumn taskStatusCol = new TableColumn("Status");
        tasksTable.setEditable(true);
        tasksTable.getColumns().addAll(taskDescCol, taskPointsCol, taskAssignedCol, taskStatusCol);

        VBox tasksBox = new VBox();
        tasksBox.setSpacing(5);
        tasksBox.setPadding(new Insets(10, 0, 0, 10));
        tasksBox.getChildren().add(tasksTable);

        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(nav_bar);
        root.setCenter(residentsBox);
        root.setBottom(bottom_menu_resident);
        root.setPadding(new Insets(10));

        // Button actions
        resident_menu.setOnAction(e -> {
            root.setCenter(residentsBox);
            root.setBottom(bottom_menu_resident);
        });
        trade_menu.setOnAction(e -> {
            root.setCenter(tradesBox);
            root.setBottom(bottom_menu_trades);
        });
        task_menu.setOnAction(e -> {
            root.setCenter(tasksBox);
            root.setBottom(bottom_menu_tasks);
        });
        Community_points_menu.setOnAction(e -> {
            root.setCenter(new VBox(new Label("Community Points - TODO")));
            root.setBottom(bottom_menu_community_points);
        });
        // Popups
        resident_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Resident");
            TextField firstNameField = new TextField();
            firstNameField.setPromptText("First Name");
            TextField lastNameField = new TextField();
            lastNameField.setPromptText("Last Name");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                // Here you would normally handle the input data
                popup.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(firstNameField, lastNameField, submitButton);
            layout.setPadding(new Insets(10, 10, 10, 10));
            popup.setScene(new Scene(layout, 300, 150));
            popup.show();
        });
        trade_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Trade");
            TextField sellerField = new TextField();
            sellerField.setPromptText("Seller");
            TextField priceField = new TextField();
            priceField.setPromptText("Price");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                popup.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(sellerField, priceField, submitButton);
            layout.setPadding(new Insets(10, 10, 10, 10));
            popup.setScene(new Scene(layout, 300, 150));
            popup.show();
        });
        task_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Task");
            TextField taskField = new TextField();
            taskField.setPromptText("Task Description");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                popup.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(taskField, submitButton);
            layout.setPadding(new Insets(10, 10, 10, 10));
            popup.setScene(new Scene(layout, 300, 150));
            popup.show();
        });
        community_points_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Community Points");
            TextField pointsField = new TextField();
            pointsField.setPromptText("Points Amount");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                popup.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(pointsField, submitButton);
            layout.setPadding(new Insets(10, 10, 10, 10));
            popup.setScene(new Scene(layout, 300, 150));
            popup.show();
        });

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("I don't wanna do this anympre send help");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
