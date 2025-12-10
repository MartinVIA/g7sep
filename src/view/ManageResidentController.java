package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;

public class ManageResidentController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    public ManageResidentController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    public Scene createScene() {
        Label title = new Label("Manage " + resident.getFirstName() + " " + resident.getLastName());

        Button changeNameBtn = new Button("Change name");
        Button editPointsBtn = new Button("Edit points");
        Button addBoostBtn = new Button("Add a boost");
        Button removeBoostBtn = new Button("Remove a boost");
        Button closeBtn = new Button("Close");

        changeNameBtn.setOnAction(e -> openChangeNamePopup());
        editPointsBtn.setOnAction(e -> openEditPointsPopup());
        addBoostBtn.setOnAction(e -> openAddBoostPopup());
        removeBoostBtn.setOnAction(e -> openRemoveBoostPopup());
        closeBtn.setOnAction(e -> closeBtn.getScene().getWindow().hide());

        VBox root = new VBox(10, title, changeNameBtn, editPointsBtn, addBoostBtn, removeBoostBtn, closeBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 260, 250);
    }

    private void openChangeNamePopup() {
        Stage popup = new Stage();
        ChangeNameController controller = new ChangeNameController(model, resident);
        popup.setScene(controller.createScene());
        popup.setTitle("Change name of: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    private void openEditPointsPopup() {
        Stage popup = new Stage();
        EditPointsController controller = new EditPointsController(model, resident);
        popup.setScene(controller.createScene());
        popup.setTitle("Edit points of Resident: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    private void openAddBoostPopup() {
        Stage popup = new Stage();
        AddBoostController controller = new AddBoostController(model, resident);
        popup.setScene(controller.createScene());
        popup.setTitle("Add a boost to: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    private void openRemoveBoostPopup() {
        Stage popup = new Stage();
        RemoveBoostController controller = new RemoveBoostController(model, resident);
        popup.setScene(controller.createScene());
        popup.setTitle("Remove boost from: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }
}
