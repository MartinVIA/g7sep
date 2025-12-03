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
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;
import javafx.scene.control.cell.PropertyValueFactory;

public class StartGUI extends Application {

    private ClovervilleModelManager model;
    private TableView<Resident> residentsTable;

    private void refreshResidentsTable() {
        residentsTable.getItems().setAll(model.getAllResidents());

    }
    // method to refresh the Main menu once the addResidentPopup closes

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
            popup.setOnHidden(ev -> refreshResidentsTable());
            popup.show();
        });

        Button trade_add = new Button("Add New Trade");
        trade_add.setOnAction(e -> {
            Stage popup = new Stage();
            TradeViewController controller = new TradeViewController(model);
            popup.setScene(controller.createScene());
            popup.setTitle("Trades");
            popup.setOnHidden(ev -> refreshResidentsTable());
            popup.show();
        });

        Button task_add = new Button("Add New Task");
        Button community_points_add = new Button("Add Community Points");

        Button resident_edit = new Button("Edit existing resident");
        resident_edit.setOnAction(e -> {
            Resident selected = residentsTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No resident selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a resident in the table first.");
                alert.showAndWait();
                return;
            }
            Stage popup = new Stage();
            EditResidentController controller = new EditResidentController(model, selected);
            popup.setScene(controller.createScene());
            popup.setTitle("Edit Resident" + selected.getFirstName() + " " + selected.getLastName());
            popup.setOnHidden(ev -> refreshResidentsTable());
            popup.show();
        });

        Button trade_edit = new Button("Edit existing Trade");
        Button task_edit = new Button("Edit existing Task");
        Button community_points_edit = new Button("Edit Community Points");
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(50.0 / 100.0);
        Label temp = new Label("50/100");

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

        // I Chanched it to an arrayList so we can add ResidentViewControllers stored
        // data into Victors UI
        residentsTable = new TableView<>();

        TableColumn<Resident, String> firstNameCol = new TableColumn("First Name");
        TableColumn<Resident, String> lastNameCol = new TableColumn("Last Name");
        TableColumn<Resident, Integer> idCol = new TableColumn("ID");
        TableColumn<Resident, Double> pointsCol = new TableColumn("Points");
        TableColumn<Resident, Double> boostsCol = new TableColumn("Boosts");

        // and here we need to set each of the arrays inside of the arraylist to the
        // correct column that
        // Victor made so it matches up, and we can do that with this
        // import javafx.scene.control.cell.PropertyValueFactory;
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("personalPoints"));
        boostsCol.setCellValueFactory(new PropertyValueFactory<>("hasBoost"));

        residentsTable.setEditable(true);
        residentsTable.getColumns().addAll(firstNameCol, lastNameCol, idCol, pointsCol, boostsCol);
        refreshResidentsTable();

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

        // community points layout
        TableView greenTasks = new TableView<>();
        TableColumn pointsAmountCol = new TableColumn("Points Amount");
        TableColumn pointsDateCol = new TableColumn("Date Added");
        TableColumn pointsAddedByCol = new TableColumn("Added By");
        greenTasks.setEditable(true);
        greenTasks.getColumns().addAll(pointsAmountCol, pointsDateCol, pointsAddedByCol);

        VBox communityPointsBox = new VBox();
        communityPointsBox.setSpacing(5);
        communityPointsBox.setPadding(new Insets(10, 0, 0, 10));
        communityPointsBox.getChildren().add(progressBar);
        communityPointsBox.getChildren().add(greenTasks);

        // Main layout
        BorderPane root = new BorderPane();
        root.setTop(nav_bar);
        root.setCenter(residentsBox);
        root.setBottom(bottom_menu_resident);
        root.setPadding(new Insets(10));

        // Button actions
        resident_menu.setOnAction(e -> {
            nav_bar.getChildren().removeAll(progressBar, temp);
            root.setCenter(residentsBox);
            root.setBottom(bottom_menu_resident);
        });
        trade_menu.setOnAction(e -> {
            nav_bar.getChildren().removeAll(progressBar, temp);
            root.setCenter(tradesBox);
            root.setBottom(bottom_menu_trades);
        });
        task_menu.setOnAction(e -> {
            nav_bar.getChildren().removeAll(progressBar, temp);
            root.setCenter(tasksBox);
            root.setBottom(bottom_menu_tasks);
        });
        Community_points_menu.setOnAction(e -> {
            nav_bar.getChildren().addAll(progressBar, temp);
            root.setCenter(communityPointsBox);
            root.setBottom(bottom_menu_community_points);
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