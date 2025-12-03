package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ClovervilleModelManager;
import model.Resident;

public class ChangeNameController {

    private final ClovervilleModelManager model;
    private final Resident resident;

    public ChangeNameController(ClovervilleModelManager model, Resident resident) {
        this.model = model;
        this.resident = resident;
    }

    public Scene createScene() {
        TextField firstField = new TextField(resident.getFirstName());
        TextField lastField = new TextField(resident.getLastName());
        Label msg = new Label();

        Button save = new Button("Save");
        save.setOnAction(e -> {
            String f = firstField.getText().trim();
            String l = lastField.getText().trim();

            if (f.isEmpty() || l.isEmpty()) {
                msg.setText("Both names must be filled.");
                return;
            }
            if (!f.matches("[A-Za-z]+") || !l.matches("[A-Za-z]+")) {
                msg.setText("Names must contain letters only (A–Z).");
                return;
            }
            if (f.equalsIgnoreCase(resident.getFirstName())
                    && l.equalsIgnoreCase(resident.getLastName())) {
                msg.setText("The entered name matches the existing name. Try again.");
                return;
            }

            resident.setFirstName(f);
            resident.setLastName(l);
            msg.setText("Name updated.");
            ((Stage) save.getScene().getWindow()).close();
        });

        VBox root = new VBox(10,
                new Label("First name:"), firstField,
                new Label("Last name:"), lastField,
                save, msg);
        root.setPadding(new Insets(10));

        return new Scene(root, 300, 220);
    }
}
