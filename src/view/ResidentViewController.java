package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

public class ResidentViewController {

    // reference to the model so we can get residents, add residents, award
    // points...
    private final ClovervilleModelManager model;

    // GUI components we need to update from event handlers
    private ListView<Resident> residentListView;
    private TextField nameField;
    private TextField pointsField;
    private Label messageLabel;

    // 🔹 This is the constructor ViewHandler is trying to use
    public ResidentViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    // 🔹 ViewHandler will call this to build the whole screen and get a Scene
    public Scene createScene() {
        // --- CENTER: list of residents ---
        residentListView = new ListView<>();
        residentListView.setPrefWidth(320);
        refreshResidentList();

        // --- TOP: title ---
        Label titleLabel = new Label("Cloverville – Residents & Personal Points");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(10));

        // --- RIGHT: controls for adding residents & giving points ---
        nameField = new TextField();
        nameField.setPromptText("Resident name");

        Button addButton = new Button("Add resident");
        addButton.setOnAction(e -> handleAddResident());

        Label addLabel = new Label("Add new resident:");

        Label awardLabel = new Label("Award points to selected resident:");
        pointsField = new TextField();
        pointsField.setPromptText("Points (e.g. 5)");

        Button awardButton = new Button("Award points");
        awardButton.setOnAction(e -> handleAwardPoints());

        messageLabel = new Label();

        VBox rightBox = new VBox(
                10,
                addLabel,
                nameField,
                addButton,
                awardLabel,
                pointsField,
                awardButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        // --- ROOT LAYOUT ---
        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(residentListView);
        root.setRight(rightBox);
        root.setPadding(new Insets(10));

        // build and return the Scene; ViewHandler puts it on the Stage
        return new Scene(root, 700, 400);
    }

    // --- helper methods used by the buttons ---

    private void refreshResidentList() {
        residentListView.getItems().setAll(model.getAllResidents());
    }

    private void handleAddResident() {
        String firstName = nameField.getText().trim();
        String lastName = nameField.getText().trim();
        if (firstName.isEmpty() && lastName.isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            return;
        }

        model.addResident(firstName, lastName);
        nameField.clear();
        refreshResidentList();
        messageLabel.setText("Resident added.");
    }

    private void handleAwardPoints() {
        Resident selected = residentListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Select a resident in the list first.");
            return;
        }

        String text = pointsField.getText().trim();
        if (text.isEmpty()) {
            messageLabel.setText("Enter how many points to award.");
            return;
        }

        try {
            int pts = Integer.parseInt(text);
            model.awardPointsToResident(selected.getId(), pts);
            refreshResidentList();
            messageLabel.setText(
                    "Awarded " + pts + " points to " + selected.getFirstName() + "." + selected.getLastName() + " .");
        } catch (NumberFormatException e) {
            messageLabel.setText("Points must be a whole number.");
        }
    }
}
