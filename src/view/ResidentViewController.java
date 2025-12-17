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

/**
 * Controller responsible for creating the main resident view. The view displays
 * a list of residents and provides functionality for adding new residents and
 * awarding personal points to a selected resident.
 * @author Loke Hansen
 * @version 1.0
 */
public class ResidentViewController {

    private final ClovervilleModelManager model;

    private ListView<Resident> residentListView;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField pointsField;
    private Label messageLabel;

    /**
     * Constructs a ResidentViewController with the given model.
     * @param model the model manager used to access and modify resident data
     */
    public ResidentViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    /**
     * Creates and returns the JavaFX scene showing the resident list and
     * controls for adding residents and awarding points.
     * @return a Scene displaying the resident view
     */
    public Scene createScene() {
        residentListView = new ListView<>();
        residentListView.setPrefWidth(320);
        refreshResidentList();

        Label titleLabel = new Label("Cloverville - Residents & Personal Points");
        titleLabel.getStyleClass().add("title");
        HBox topBox = new HBox(titleLabel);
        topBox.setPadding(new Insets(0, 5, 8, 5));

        firstNameField = new TextField();
        firstNameField.setPromptText("First name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last name");

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
                firstNameField,
                lastNameField,
                addButton,
                awardLabel,
                pointsField,
                awardButton,
                messageLabel);
        rightBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(residentListView);
        root.setRight(rightBox);
        root.setPadding(new Insets(10));

        return new Scene(root, 700, 400);
    }

    /**
     * Refreshes the resident list view by reloading residents from the model.
     */
    private void refreshResidentList() {
        residentListView.getItems().setAll(model.getAllResidents());
    }

    /**
     * Handles adding a new resident using the entered first and last name. The
     * method validates that the name fields are not empty, adds the resident
     * through the model, and refreshes the list.
     */
    private void handleAddResident() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        if (firstName.isEmpty() && lastName.isEmpty()) {
            messageLabel.setText("Name cannot be empty.");
            return;
        }

        model.addResident(firstName, lastName);
        firstNameField.clear();
        lastNameField.clear();
        refreshResidentList();
        messageLabel.setText("Resident added.");
    }

    /**
     * Handles awarding points to the currently selected resident. The method
     * validates that a resident is selected and that the entered points value
     * is a valid whole number before updating the model and refreshing the
     * list.
     */
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
                    "Awarded " + pts + " points to [" + selected.getFirstName() + " " + selected.getLastName() + "] .");
            messageLabel.setWrapText(true);

        } catch (NumberFormatException e) {
            messageLabel.setText("Points must be a whole number.");
        }
    }
}
