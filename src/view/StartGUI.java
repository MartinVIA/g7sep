package view;

import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import utils.JSONReader;
import utils.FileWriter;

public class StartGUI extends Application {

    private ClovervilleModelManager model;
    private TableView<Resident> residentsTable;
    private TableView<Task> taskTable;
    private TableView<Trade> tradesTable;

    private void refreshResidentsTable() {
        // residentsTable.getItems().setAll(model.getAllResidents());

        if (model != null) {
            residentsTable.getItems().clear();
            residentsTable.getItems().setAll(model.getAllResidents());
        }
    }

    private void refreshTradesTable() {
        if (model != null) {
            tradesTable.getItems().clear();
            tradesTable.getItems().setAll(model.getTradeList());
            // Save to JSON after refresh
            FileWriter.saveTradesToJSON(model.getTradeList(), "docs/file_operations_trades.json");
        }
    }

    private void refreshTasksTable() {
        taskTable.getItems().setAll(model.getTaskList());
        // Save to JSON after refresh
        FileWriter.saveTasksToJSON(model.getTaskList(), "docs/file_operations_tasks.json");
    }

    public void start(Stage primaryStage) {
        model = new ClovervilleModelManager();

        // Load residents and points from JSON files
        try {
            System.out.println("Loading data from JSON files...");
            List<Resident> residentsFromJSON = JSONReader.readResidentsFromJSON("docs/file_operations_residents.json");
            Map<Integer, Integer> pointsFromJSON = JSONReader
                    .readPersonalPointsFromJSON("docs/file_operations_personalpoints.json");

            // Add residents to model by first name and last name
            for (Resident resident : residentsFromJSON) {
                model.addResident(resident.getFirstName(), resident.getLastName());
            }

            // Now apply points from JSON
            for (Resident resident : model.getAllResidents()) {
                if (pointsFromJSON.containsKey(resident.getId())) {
                    Integer points = pointsFromJSON.get(resident.getId());
                    resident.setPersonalPoints(points);
                }
            }
            System.out.println("Loaded " + residentsFromJSON.size() + " residents from JSON");

            // Load tasks from JSON
            List<Task> tasksFromJSON = JSONReader.readTasksFromJSON("docs/file_operations_tasks.json");
            for (Task task : tasksFromJSON) {
                model.addTask(task);
            }
            System.out.println("Loaded " + tasksFromJSON.size() + " tasks from JSON");

            // Load trades from JSON
            List<Trade> tradesFromJSON = JSONReader.readTradesFromJSON("docs/file_operations_trades.json");
            for (Trade trade : tradesFromJSON) {
                model.addTrade(trade);
            }
            System.out.println("Loaded " + tradesFromJSON.size() + " trades from JSON");
        } catch (Exception e) {
            System.err.println("Error loading JSON data: " + e.getMessage());
            e.printStackTrace();
        }

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
            popup.setOnHidden(ev -> {
                refreshResidentsTable();
                // try {
                // FileWriter fw = new FileWriter(model);
                // fw.savePersonalPoints();
                // ;
                // fw.saveResidents();
                // } catch (Exception ex) {
                // System.err.println("Error saving residents: " + ex.getMessage());
                // }
            });
            popup.show();
        });

        Button trade_add = new Button("Add New Trade");
        trade_add.setOnAction(e -> {
            Stage popup = new Stage();
            TradeViewController controller = new TradeViewController(model);
            popup.setScene(controller.createScene());
            popup.setTitle("Trades");
            popup.setOnHidden(ev -> refreshTradesTable());
            popup.show();
        });

        Button task_add = new Button("Add New Task");
        task_add.setOnAction(e -> {
            Stage popup = new Stage();
            TaskViewController controller_task = new TaskViewController(model);
            popup.setScene(controller_task.createScene());
            popup.setTitle("Tasks");
            popup.setOnHidden(ev -> refreshTasksTable());
            popup.show();
        });

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
            ManageResidentController controller = new ManageResidentController(
                    model, selected, this::refreshResidentsTable);

            popup.setScene(controller.createScene());
            popup.setTitle("Manage Resident: "
                    + selected.getFirstName() + " " + selected.getLastName());
            popup.show();
        });

        Button trade_edit = new Button("Edit existing Trade");
        trade_edit.setOnAction(e -> {

            Trade selected = tradesTable.getSelectionModel().getSelectedItem();
            // select the trade
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No trade selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a trade in the table first.");
                alert.showAndWait();
                return;
            }
            // select the trade dumbass
            Stage popup = new Stage();
            ManageTradeController controller = new ManageTradeController(model,
                    selected, this::refreshTradesTable);
            popup.setScene(controller.createScene());
            popup.setTitle("Edit Trade");
            popup.show();
        });
        Button task_edit = new Button("Edit existing Task");
        Button community_points_edit = new Button("Edit Community Points");

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(50.0 / 100.0);
        // will need the score int
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

        // I Chanched it to an arrayList so we can add ResidentViewControllers stored
        // data into Victors UI
        residentsTable = new TableView<>();

        VBox residentsBox = new VBox();
        residentsBox.setSpacing(5);
        residentsBox.setPadding(new Insets(10, 0, 0, 10));
        residentsBox.getChildren().add(residentsTable);

        TableColumn<Resident, String> firstNameCol = new TableColumn("First Name");
        TableColumn<Resident, String> lastNameCol = new TableColumn("Last Name");
        TableColumn<Resident, Integer> idCol = new TableColumn("ID");
        TableColumn<Resident, Integer> pointsCol = new TableColumn("Points");
        TableColumn<Resident, Boolean> boostCol = new TableColumn("Boost");

        // import javafx.scene.control.cell.PropertyValueFactory;
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("personalPoints"));
        boostCol.setCellValueFactory(new PropertyValueFactory<>("hasBoost"));
        residentsTable.setEditable(true);
        residentsTable.getColumns().addAll(firstNameCol, lastNameCol, idCol, pointsCol, boostCol);
        refreshResidentsTable();

        tradesTable = new TableView<>();

        VBox tradesBox = new VBox();
        tradesBox.setSpacing(5);
        tradesBox.setPadding(new Insets(10, 0, 0, 10));
        tradesBox.getChildren().add(tradesTable);

        TableColumn<Trade, String> sellerCol = new TableColumn<>("Seller");
        TableColumn<Trade, Integer> priceCol = new TableColumn<>("Price");
        TableColumn<Trade, String> offerCol = new TableColumn<>("Offer");
        TableColumn<Trade, String> descriptionCol = new TableColumn<>("Description");
        // creates the table we see when we go to the trade tab

        sellerCol.setCellValueFactory(new PropertyValueFactory<>("traderName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pointCost"));
        offerCol.setCellValueFactory(new PropertyValueFactory<>("stringName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        // A tableView stores whole Trade objects, but each TableColumn must be told
        // which part of the Trade should appear in that colummn.
        // setCellValueFactory tells the colummn which get method in the Trade class to
        // call.
        // For example, sellerCol uses getTraderName(), priceCol uses getPointCost()
        // Without setCellValueFactory, the table would not know what to show.

        tradesTable.setEditable(true);
        tradesTable.getColumns().addAll(sellerCol, priceCol, offerCol, descriptionCol);
        refreshTradesTable();

        // task list
        taskTable = new TableView<>();
        taskTable.setPrefWidth(420);

        TableColumn<Task, String> nameCol = new TableColumn<>("Task Name");
        TableColumn<Task, String> descCol = new TableColumn<>("Task Description");
        TableColumn<Task, Integer> pointsColTasks = new TableColumn<>("description");
        TableColumn<Task, String> typeCol = new TableColumn<>("Type");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("TaskName"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        pointsColTasks.setCellValueFactory(new PropertyValueFactory<>("points"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ;
        taskTable.getColumns().addAll(nameCol, descCol, pointsColTasks, typeCol);
        refreshTasksTable();

        VBox tasksBox = new VBox();
        tasksBox.setSpacing(5);
        tasksBox.setPadding(new Insets(10, 0, 0, 10));
        tasksBox.getChildren().add(taskTable);

        // community points layout
        TableView greenTasks = new TableView<>();
        TableColumn pointsAmountCol = new TableColumn("Points Amount");
        TableColumn pointsDateCol = new TableColumn("Date Added");
        TableColumn pointsAddedByCol = new TableColumn("Added By");
        greenTasks.setEditable(true);
        greenTasks.setPrefHeight(350);
        greenTasks.getColumns().addAll(pointsAmountCol, pointsDateCol, pointsAddedByCol);

        VBox communityPointsBox = new VBox();
        communityPointsBox.setSpacing(5);
        communityPointsBox.setPadding(new Insets(10, 0, 0, 10));
        progressBar.setPrefWidth(450);
        communityPointsBox.getChildren().add(greenTasks);
        communityPointsBox.getChildren().add(new Label("Progress toward next community reward: 50/100 green points"));
        // community points variable needed
        communityPointsBox.getChildren().add(progressBar);

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
            root.setCenter(communityPointsBox);
            root.setBottom(bottom_menu_community_points);
        });
        community_points_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add Community Points");
            TextField pointsField = new TextField();
            pointsField.setPromptText("Points Amount");
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                // comunity points compatibility
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
        primaryStage.setResizable(false);
        primaryStage.setTitle("I don wanna do this anympre send help");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
