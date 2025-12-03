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
        Label title = new Label(
                "Manage " + resident.getFirstName() + " " + resident.getLastName());

        Button changeNameBtn = new Button("Change name");
        changeNameBtn.setOnAction(e -> {
            Stage popup = new Stage();
            ChangeNameController controller = new ChangeNameController(model, resident);
            popup.setScene(controller.createScene());
            popup.setTitle("Change name of: "
                    + resident.getFirstName() + " " + resident.getLastName());
            popup.show();
        });

        Button editPointsBtn = new Button("Edit points");
        editPointsBtn.setOnAction(e -> {
            Stage popup = new Stage();
            EditPointsController controller = new EditPointsController(model, resident);
            popup.setScene(controller.createScene());
            popup.setTitle("Edit points of Resident: " + resident.getFirstName() + " " + resident.getLastName());
            popup.show();
        });
        Button addBoostBtn = new Button("Add a boost");
        addBoostBtn.setOnAction(e -> {
            Stage popup = new Stage();
            AddBoostController controller = new AddBoostController(model, resident);
            popup.setScene(controller.createScene());
            popup.setTitle("Add a boost to: " + resident.getFirstName() + " " + resident.getLastName());
            popup.show();
        });
        Button removeBoostBtn = new Button("Remove a boost");
        removeBoostBtn.setOnAction(e -> {
            Stage popup = new Stage();
            RemoveBoostController controller = new RemoveBoostController(model, resident);
            popup.setScene(controller.createScene());
            popup.setTitle("Romove boost from: " + resident.getFirstName() + " " + resident.getLastName());
            popup.show();
        });
        VBox root = new VBox(10, title,
                changeNameBtn, editPointsBtn, addBoostBtn, removeBoostBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 260, 220);
    }
}
