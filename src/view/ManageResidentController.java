package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;

/**
 * Controller responsible for creating a view that allows managing a specific
 * resident. The controller provides options for changing the resident’s name,
 * editing points, managing boost status, and removing the resident.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ManageResidentController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    /**
     * Constructs a ManageResidentController with the given model and resident.
     *
     * @param model the model manager used to access application data
     * @param resident the resident to be managed
     */
    public ManageResidentController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    /**
     * Creates and returns the JavaFX scene used to manage the selected
     * resident.
     *
     * @return a Scene displaying the manage resident view
     */
    public Scene createScene() {
        Label title = new Label("Manage " + resident.getFirstName() + " " + resident.getLastName());
        title.getStyleClass().add("title");

        Button changeNameBtn = new Button("Change name");
        Button editPointsBtn = new Button("Edit points");
        Button addBoostBtn = new Button("Add a boost");
        Button removeBoostBtn = new Button("Remove a boost");
        Button removeResidentBtn = new Button("Remove the resident");
        Button closeBtn = new Button("Close");

        changeNameBtn.setOnAction(e -> openChangeNamePopup());
        editPointsBtn.setOnAction(e -> openEditPointsPopup());
        addBoostBtn.setOnAction(e -> openAddBoostPopup());
        removeBoostBtn.setOnAction(e -> openRemoveBoostPopup());
        
        removeResidentBtn.getStyleClass().add("red-border");
        removeResidentBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Removal");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("This will remove the resident and their corresponding information.");
            alert.showAndWait().ifPresent(response -> {
                if (response.getButtonData().isDefaultButton()) {
                    model.resetAllPersonalPoints();
                    // refreshResidentsTable();

                    model.removeResident(resident);
                    removeResidentBtn.getScene().getWindow().hide();
                    // FileWriter.saveResidentsToBinary(model.getAllResidents(), "residents.bin");
                    // JSONWriter.saveResidentsToJSON(model.getAllResidents(), "docs/file_operations_residents.json");

                    Alert done = new Alert(Alert.AlertType.INFORMATION);
                    done.setTitle("Resident Removed");
                    done.setHeaderText("Resident Management");
                    done.setContentText("The resident and their information have been removed");
                    done.showAndWait();
                }
            });
        });
        closeBtn.setOnAction(e -> closeBtn.getScene().getWindow().hide());

        VBox root = new VBox(10, title, changeNameBtn, editPointsBtn, addBoostBtn, removeBoostBtn, removeResidentBtn, closeBtn);
        root.setPadding(new Insets(10));

        return new Scene(root, 260, 260);
    }

    /**
     * Opens a popup window for changing the resident’s name.
     */
    private void openChangeNamePopup() {
        Stage popup = new Stage();
        ChangeNameController controller = new ChangeNameController(model, resident);
        popup.setScene(controller.createScene());
        popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
        popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
        popup.setTitle("Change name of: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    /**
     * Opens a popup window for editing the resident’s points.
     */
    private void openEditPointsPopup() {
        Stage popup = new Stage();
        EditPointsController controller = new EditPointsController(model, resident);
        popup.setScene(controller.createScene());
        popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
        popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
        popup.setTitle("Edit points of Resident: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    /**
     * Opens a popup window for adding a boost to the resident.
     */
    private void openAddBoostPopup() {
        Stage popup = new Stage();
        AddBoostController controller = new AddBoostController(model, resident);
        popup.setScene(controller.createScene());
        popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
        popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
        popup.setTitle("Add a boost to: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }

    /**
     * Opens a popup window for removing the boost from the resident.
     */
    private void openRemoveBoostPopup() {
        Stage popup = new Stage();
        RemoveBoostController controller = new RemoveBoostController(model, resident);
        popup.setScene(controller.createScene());
        popup.getScene().getStylesheets().add("file:./docs/FxStyles.css");
        popup.getIcons().add(new Image("file:./docs/img/leaveicon.png"));
        popup.setTitle("Remove boost from: " + resident.getFirstName() + " " + resident.getLastName());
        popup.show();
    }
}
