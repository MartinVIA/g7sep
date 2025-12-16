package view;

import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import utils.FileReader;
import utils.FileWriter;
import utils.JSONWriter;

/**
 * Main JavaFX application window for Cloverville. This class loads stored data,
 * builds the primary user interface, and provides navigation between residents,
 * trades, tasks, and community points.
 *
 * @author Adam Terelak
 * @author Leon de Kuijper
 * @author Martin Chavez
 * @author Loke Hansen
 * @author Victor Țonu
 * @version 1.0
 */
public class StartGUI extends Application {

    private ClovervilleModelManager model;
    private TableView<Resident> residentsTable;
    private TableView<Task> taskTable;
    private TableView<Trade> tradesTable;

    /**
     * Refreshes the residents table by reloading resident data from the model.
     * The updated resident list is also saved to binary and JSON files.
     */
    private void refreshResidentsTable() {
        if (model != null) {
            residentsTable.getItems().clear();
            residentsTable.getItems().setAll(model.getAllResidents());
            FileWriter.saveResidentsToBinary(model.getAllResidents(), "residents.bin");
            JSONWriter.saveResidentsToJSON(model.getAllResidents(), "docs/file_operations_residents.json");
        }
    }

    /**
     * Refreshes the trades table by reloading trade data from the model.
     */
    private void refreshTradesTable() {
        if (model != null) {
            tradesTable.getItems().clear();
            tradesTable.getItems().setAll(model.getTradeList());
        }
    }

    /**
     * Refreshes the tasks table by reloading task data from the model.
     */
    private void refreshTasksTable() {
        if (model != null) {
            taskTable.getItems().clear();
            taskTable.getItems().setAll(model.getTaskList());
        }
    }

    ProgressBar progressBar = new ProgressBar();
    Label progressLabel = new Label();

    /**
     * Updates the community points progress label and progress bar based on the
     * current green points and goal stored in the model.
     */
    private void refreshCommunityPointsTable() {
        if (model != null) {
            progressLabel.setText("Progress toward next green reward: " + model.getGreenPoints()
                    + "/" + model.getGreenPointsGoal() + " green points");
            progressBar.setProgress((double) model.getGreenPoints() / model.getGreenPointsGoal());
        }
    }

    /**
     * Starts the JavaFX application. The method initializes the model, loads
     * persisted data from files, builds the GUI layout, and displays the main
     * application window.
     *
     * @param primaryStage the main stage provided by the JavaFX runtime
     */
    @Override
    public void start(Stage primaryStage) {
        model = new ClovervilleModelManager();

        // Load data from binary files
        try {
            System.out.println("Loading data from binary files...");
            ArrayList<Resident> residents = FileReader.readResidentsFromBinary("residents.bin");
            model.importResidents(residents);
            System.out.println("Loaded " + residents.size() + " Residents");

            ArrayList<Task> tasks = FileReader.readTasksFromBinary("tasks.bin");
            model.importTasks(tasks);
            System.out.println("Loaded " + tasks.size() + " Tasks");

            ArrayList<Trade> trades = FileReader.readTradesFromBinary("trades.bin");
            model.importTrades(trades);
            System.out.println("Loaded " + trades.size() + " Trades");

            GreenPoints greenPoints = FileReader.readGreenPointsFromBinary("community.bin");
            model.importGreenPoints(greenPoints);
            System.out.println("Loaded GreenPoints");

        } catch (Exception e) {
            System.err.println("Error loading binary data: " + e.getMessage());
            e.printStackTrace();
        }

        Button resident_menu = new Button("Residents");
        Button trade_menu = new Button("Trades");
        Button task_menu = new Button("Tasks");
        Button green_points_menu = new Button("Green Points");
        Image clovervilleImage = new Image("file:./docs/img/clovervilleLogo.png");
        ImageView displayCloverImage = new ImageView(clovervilleImage);
        displayCloverImage.setX(0);
        displayCloverImage.setY(0);
        displayCloverImage.setFitHeight(20);

        Button resident_add = new Button("Add New Resident");
        resident_add.setOnAction(e -> {
            Stage popup = new Stage();
            ResidentViewController controller = new ResidentViewController(model);
            popup.setScene(controller.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));

            popup.setTitle("Cloverville's Resident");
            popup.setOnHidden(ev -> {
                refreshResidentsTable();
            });
            popup.show();
        });

        Button trade_add = new Button("Add New Trade");
        trade_add.setOnAction(e -> {
            Stage popup = new Stage();
            TradeViewController controller = new TradeViewController(model);
            popup.setScene(controller.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.setTitle("Trades");
            popup.setOnHidden(ev -> {
                refreshTradesTable();
                FileWriter.saveTradesToBinary(model.getTradeList(), "trades.bin");
                JSONWriter.saveTradesToJSON(model.getTradeList(), "docs/file_operations_trades.json");
            });
            popup.show();
        });

        Button task_add = new Button("Add New Task");
        task_add.setOnAction(e -> {
            Stage popup = new Stage();
            TaskViewController controller_task = new TaskViewController(model);
            popup.setScene(controller_task.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.setTitle("Tasks");
            popup.setOnHidden(ev -> {
                refreshTasksTable();
                FileWriter.saveTasksToBinary(model.getTaskList(), "tasks.bin");
                JSONWriter.saveTasksToJSON(model.getTaskList(), "docs/file_operations_tasks.json");
            });
            popup.show();
        });

        Button green_points_add = new Button("Add Green Points");

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
            ManageResidentController controller = new ManageResidentController(model, selected);
            popup.setScene(controller.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.setTitle("Manage Resident: "
                    + selected.getFirstName() + " " + selected.getLastName());
            popup.setOnHidden(ev -> {
                refreshResidentsTable();
                FileWriter.saveResidentsToBinary(model.getAllResidents(), "residents.bin");
                JSONWriter.saveResidentsToJSON(model.getAllResidents(), "docs/file_operations_residents.json");
            });
            popup.show();
        });
        Button Resident_reset_all_points = new Button("Reset all personal points");
        Resident_reset_all_points.getStyleClass().add("red-border");
        Resident_reset_all_points.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Reset");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("This will reset all of the personal points for all of the residents");
            alert.showAndWait().ifPresent(response -> {
                if (response.getButtonData().isDefaultButton()) {
                    model.resetAllPersonalPoints();
                    refreshResidentsTable();
                    FileWriter.saveResidentsToBinary(model.getAllResidents(), "residents.bin");
                    JSONWriter.saveResidentsToJSON(model.getAllResidents(), "docs/file_operations_residents.json");

                    Alert done = new Alert(Alert.AlertType.INFORMATION);
                    done.setTitle("Points reset");
                    done.setHeaderText("Personal points");
                    done.setContentText("All of the residents personal points have been reset");
                    done.showAndWait();
                }
            });

        });

        Button trade_edit = new Button("Edit existing Trade");
        trade_edit.setOnAction(e -> {

            Trade selected = tradesTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No trade selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a trade in the table first.");
                alert.showAndWait();
                return;
            }

            Stage popup = new Stage();
            ManageTradeController controller = new ManageTradeController(model, selected, () -> {
                refreshTradesTable();
                FileWriter.saveTradesToBinary(model.getTradeList(), "trades.bin");
                JSONWriter.saveTradesToJSON(model.getTradeList(), "docs/file_operations_trades.json");
            });
            popup.setScene(controller.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.setTitle("Edit Trade");
            popup.show();
        });
        Button task_edit = new Button("Edit existing Task");
        task_edit.setOnAction(e -> {
            Task selected = taskTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No task selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a task in the table first.");
                alert.showAndWait();
                return;
            }

            Stage popup = new Stage();
            ManageTaskController controller = new ManageTaskController(model, selected, () -> {
                refreshTasksTable();
                FileWriter.saveTasksToBinary(model.getTaskList(), "tasks.bin");
                JSONWriter.saveTasksToJSON(model.getTaskList(), "docs/file_operations_tasks.json");
            });
            popup.setScene(controller.createScene());
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.setTitle("Manage Task: " + selected.getName());
            popup.show();
        });

        Button community_points_edit = new Button("Edit Green Points");

        HBox bottom_menu_resident = new HBox();
        bottom_menu_resident.getChildren().addAll(resident_add, resident_edit, Resident_reset_all_points);
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
        bottom_menu_community_points.getChildren().addAll(green_points_add, community_points_edit);
        bottom_menu_community_points.setPrefWidth(300);
        bottom_menu_community_points.setSpacing(10);

        HBox nav_bar = new HBox();
        nav_bar.getChildren().addAll(resident_menu, trade_menu, task_menu, green_points_menu);

        residentsTable = new TableView<>();

        VBox residentsBox = new VBox();
        residentsBox.setSpacing(5);
        residentsBox.setPadding(new Insets(10, 0, 0, 10));
        residentsBox.getChildren().add(residentsTable);

        TableColumn<Resident, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Resident, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Resident, Integer> idCol = new TableColumn<>("ID");
        TableColumn<Resident, Integer> pointsCol = new TableColumn<>("Points");
        TableColumn<Resident, Boolean> boostCol = new TableColumn<>("Boost");

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

        sellerCol.setCellValueFactory(new PropertyValueFactory<>("traderName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pointCost"));
        offerCol.setCellValueFactory(new PropertyValueFactory<>("stringName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        tradesTable.setEditable(true);
        tradesTable.getColumns().addAll(sellerCol, priceCol, offerCol, descriptionCol);
        refreshTradesTable();

        taskTable = new TableView<>();
        taskTable.setPrefWidth(420);

        TableColumn<Task, String> nameCol = new TableColumn<>("Task Name");
        TableColumn<Task, String> descCol = new TableColumn<>("Task Description");
        TableColumn<Task, Integer> pointsColTasks = new TableColumn<>("Points awarded");
        TableColumn<Task, String> typeCol = new TableColumn<>("Type");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        pointsColTasks.setCellValueFactory(new PropertyValueFactory<>("points"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        taskTable.getColumns().addAll(nameCol, descCol, pointsColTasks, typeCol);
        refreshTasksTable();

        VBox tasksBox = new VBox();
        tasksBox.setSpacing(5);
        tasksBox.setPadding(new Insets(10, 0, 0, 10));
        tasksBox.getChildren().add(taskTable);

        TableView greenTasks = new TableView<>();
        TableColumn pointsAmountCol = new TableColumn<>("Points Amount");
        TableColumn pointsDateCol = new TableColumn<>("Date Added");
        TableColumn pointsAddedByCol = new TableColumn<>("Added By");
        greenTasks.setEditable(true);
        greenTasks.setPrefHeight(350);
        greenTasks.getColumns().addAll(pointsAmountCol, pointsDateCol, pointsAddedByCol);

        VBox communityPointsBox = new VBox();
        communityPointsBox.setSpacing(5);
        communityPointsBox.setPadding(new Insets(10, 0, 0, 10));
        progressBar.setPrefWidth(450);
        communityPointsBox.getChildren().add(greenTasks);
        communityPointsBox.getChildren().add(progressLabel);
        progressBar.setProgress((double) model.getGreenPoints() / model.getGreenPointsGoal());
        communityPointsBox.getChildren().add(progressBar);
        refreshCommunityPointsTable();

        BorderPane root = new BorderPane();
        root.setTop(nav_bar);
        root.setCenter(residentsBox);
        root.setBottom(bottom_menu_resident);
        root.setPadding(new Insets(10));

        resident_menu.setOnAction(e -> {
            root.setCenter(residentsBox);
            root.setBottom(bottom_menu_resident);
            refreshResidentsTable();
        });
        trade_menu.setOnAction(e -> {
            root.setCenter(tradesBox);
            root.setBottom(bottom_menu_trades);
            refreshTradesTable();
        });
        task_menu.setOnAction(e -> {
            root.setCenter(tasksBox);
            root.setBottom(bottom_menu_tasks);
            refreshTasksTable();
        });
        green_points_menu.setOnAction(e -> {
            root.setCenter(communityPointsBox);
            root.setBottom(bottom_menu_community_points);
            refreshCommunityPointsTable();
        });
        green_points_add.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Add/Remove Green Points");
            TextField pointsField = new TextField();
            pointsField.setPromptText("Points Amount (negative to remove)");
            Button submitButton = new Button("Submit");
            submitButton.requestFocus();
            submitButton.setOnAction(ev -> {
                try {
                    int points = Integer.parseInt(pointsField.getText());
                    model.addGreenPoints(points);
                    FileWriter.saveGreenPointsToBinary(model.getGreenPointsObject(), "community.bin");
                    JSONWriter.saveGreenPointsToJSON(model.getGreenPointsObject(),
                            "docs/file_operations_community.json");
                    popup.close();
                    progressBar.setProgress((double) model.getGreenPoints() / model.getGreenPointsGoal());
                    communityPointsBox.getChildren().set(1, new Label("Progress toward next green reward: "
                            + model.getGreenPoints() + "/" + model.getGreenPointsGoal() + " green points"));
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid number.");
                    alert.showAndWait();
                }
            });
            VBox layout = new VBox(15);
            layout.getChildren().addAll(pointsField, submitButton);
            layout.setPadding(new Insets(10));
            popup.setScene(new Scene(layout, 320, 80));
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.show();
        });
        community_points_edit.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Edit Green Points");
            TextField goalField = new TextField();
            goalField.setPromptText("New Goal Amount");
            goalField.setText(String.valueOf(model.getGreenPointsGoal()));

            TextField rewardField = new TextField();
            rewardField.setPromptText("Community Reward Description");
            rewardField.setText(model.getGreenPointsObject().getCommunityReward());

            Button submitButton = new Button("Submit");
            submitButton.setOnAction(ev -> {
                try {
                    int goal = Integer.parseInt(goalField.getText());
                    String reward = rewardField.getText();

                    if (reward.matches("-?\\d+(\\.\\d+)?")) {
                        throw new IllegalArgumentException("Reward description cannot be a number.");
                    }

                    model.setGreenPointsGoal(goal);
                    model.getGreenPointsObject().setCommunityReward(reward);

                    FileWriter.saveGreenPointsToBinary(model.getGreenPointsObject(), "community.bin");
                    JSONWriter.saveGreenPointsToJSON(model.getGreenPointsObject(),
                            "docs/file_operations_community.json");
                    popup.close();
                    progressBar.setProgress((double) model.getGreenPoints() / model.getGreenPointsGoal());

                    String currentReward = model.getGreenPointsObject().getCommunityReward();
                    String displayReward = (currentReward == null || currentReward.isEmpty()) ? "next green reward"
                            : currentReward;

                    communityPointsBox.getChildren().set(1, new Label("Progress toward " + displayReward + ": "
                            + model.getGreenPoints() + "/" + model.getGreenPointsGoal() + " green points"));
                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid input for point goal");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid number for the goal");
                    alert.showAndWait();
                } catch (IllegalArgumentException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            });
            VBox layout = new VBox(15);
            layout.getChildren().addAll(goalField, rewardField, submitButton);
            layout.setPadding(new Insets(10));
            popup.setScene(new Scene(layout, 300, 130));
            popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
            popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
            popup.show();
        });
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("file:./docs/FxStyles.css");
        primaryStage.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cloverville Community Management");
        primaryStage.show();
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
