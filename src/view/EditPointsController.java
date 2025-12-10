package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

public class EditPointsController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    public EditPointsController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    public Scene createScene() {

        TextField pointsField = new TextField(
                String.valueOf(resident.getPersonalPoints()));
        Label msg = new Label();

        Button save = new Button("Save");
        save.setOnAction(e -> {
            String text = pointsField.getText().trim();

            if (!text.matches("\\d+")) {
                msg.setText("Points must be a positive Integer.");
                return;
            }

            int pts = Integer.parseInt(text);
            resident.setPersonalPoints(pts);

            msg.setText("Points updated.");

        });

        VBox root = new VBox(10,
                new Label("New points for resident:"),
                pointsField,
                save,
                msg);
        root.setPadding(new Insets(10));

        return new Scene(root, 280, 180);
    }
}
