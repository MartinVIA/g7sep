package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;

public class ManageResidentController
{
    private final ClovervilleModelManager model;
    private final Resident resident;

    public ManageResidentController(ClovervilleModelManager model, Resident resident)
    {
        this.model = model;
        this.resident = resident;
    }
    public Scene createScene()
    {
        Label title = new Label(
        "Manage " + resident.getFirstName() + " " + resident.getLastName());

    Button changeNameBtn   = new Button("Change name");
    Button changeIdBtn     = new Button("Change ID number");
    Button editPointsBtn   = new Button("Edit points");
    Button addBoostBtn     = new Button("Add a boost");
    Button removeBoostBtn  = new Button("Remove a boost");

    VBox root = new VBox(10, title,
        changeNameBtn, changeIdBtn, editPointsBtn, addBoostBtn, removeBoostBtn);
    root.setPadding(new Insets(10));

    return new Scene(root, 260, 220);   
    }
}
