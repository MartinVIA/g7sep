package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;

public class AddBoostController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    public AddBoostController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    public Scene createScene() {

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Label msg = new Label();

        // If they already have a boost → show message, nothing to do
        if (resident.getHasBoost()) {
            msg.setText("There is no boost available for this resident (already has a boost).");
            root.getChildren().add(msg);
            msg.setWrapText(true);
            return new Scene(root, 320, 100);
        }

        Label info = new Label("Apply a boost for this resident?");
        Button applyBtn = new Button("Apply boost");
        applyBtn.setOnAction(e -> {
            resident.setBoost(true);
            msg.setText("Boost applied to resident.");
            ((Stage) applyBtn.getScene().getWindow()).close();
        });

        root.getChildren().addAll(info, applyBtn, msg);
        return new Scene(root, 280, 140);
    }
}
