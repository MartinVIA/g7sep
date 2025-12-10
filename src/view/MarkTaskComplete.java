package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.GreenActions;
import model.PersonalPoints;
import model.Resident;
import model.Task;
import utils.FileWriter;
import utils.JSONWriter;

public class MarkTaskComplete {

    private final ClovervilleModelManager model;
    private final Task task;

    public MarkTaskComplete(ClovervilleModelManager model, Task task) {
        this.model = model;
        this.task = task;
    }

    public Scene createScene() {
        Label title = new Label("Mark task complete: " + task.getName());
        Label instructions = new Label("Select resident who completed the task:");

        ListView<Resident> residentListView = new ListView<>();
        residentListView.getItems().setAll(model.getAllResidents());
        residentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Button keepBtn = new Button("Confirm keep task");
        Button deleteBtn = new Button("Confirm delete task");
        Button cancelBtn = new Button("Cancel");

        keepBtn.setOnAction(e -> {
            Resident selectedResident = residentListView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                completeTaskForResident(selectedResident);
            }
            keepBtn.getScene().getWindow().hide();
        });

        deleteBtn.setOnAction(e -> {
            Resident selectedResident = residentListView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                completeTaskForResident(selectedResident);
            }
            model.removeTask(task);
            deleteBtn.getScene().getWindow().hide();
        });

        cancelBtn.setOnAction(e -> cancelBtn.getScene().getWindow().hide());

        HBox buttons = new HBox(10, keepBtn, deleteBtn, cancelBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, title, instructions, residentListView, buttons);
        root.setPadding(new Insets(10));

        return new Scene(root, 400, 400);
    }

    private void completeTaskForResident(Resident resident) {
        if (task instanceof GreenActions) {
            // task.completeTask(resident);
            model.addGreenPoints(task.getPoints());
            FileWriter.saveGreenPointsToBinary(model.getGreenPointsObject(), "community.bin");
            JSONWriter.saveGreenPointsToJSON(model.getGreenPointsObject(), "docs/file_operations_community.json");
        } else {
            int points = task.getPoints();
            if (resident.getHasBoost()) {
                double boostMultiplier = new PersonalPoints().calculateBoost(resident);
                points = (int) Math.round(points * boostMultiplier);
                resident.setBoost(false);
            }

            model.awardPointsToResident(resident.getId(), points);
            resident.setLatestTask(new model.Date());
            task.setCompleteDate(new model.Date());

            FileWriter.saveResidentsToBinary(model.getAllResidents(), "residents.bin");
            JSONWriter.saveResidentsToJSON(model.getAllResidents(), "docs/file_operations_residents.json");
        }
    }
}