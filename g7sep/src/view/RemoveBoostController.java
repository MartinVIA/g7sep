package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

public class RemoveBoostController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    public RemoveBoostController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    public Scene createScene() {

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label msg = new Label();

        // If no boost error!!!!!
        if (!resident.getHasBoost()) {
            msg.setText("The resident doesn’t have any boost.");
            root.getChildren().add(msg);
            return new Scene(root, 280, 100);
        }

        Label info = new Label("Resident currently has a boost.");
        Button removeBtn = new Button("Remove boost");
        removeBtn.setOnAction(e -> {
            resident.setBoost(false);
            msg.setText("Boost removed from resident.");
        });

        root.getChildren().addAll(info, removeBtn, msg);
        return new Scene(root, 280, 140);
    }
}
