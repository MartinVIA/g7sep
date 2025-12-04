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
import utils.FileWriter;

public class ResidentViewController {

  // reference to the model so we can get residents, add residents, award points
  // ndat
  private final ClovervilleModelManager model;

  private ListView<Resident> residentListView;
  private TextField firstNameField;
  private TextField lastNameField;

  private TextField pointsField;
  private Label messageLabel;

  // constructing builder bob
  public ResidentViewController(ClovervilleModelManager model) {
    this.model = model;
  }

  // make scene
  public Scene createScene() {
    residentListView = new ListView<>();
    residentListView.setPrefWidth(320);
    refreshResidentList();

    // title
    Label titleLabel = new Label("Cloverville - Residents & Personal Points");
    HBox topBox = new HBox(titleLabel);
    topBox.setPadding(new Insets(10));

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

    // viewhandler makes the scene
    return new Scene(root, 700, 400);
  }

  // methods for buttons

  private void refreshResidentList() {
    residentListView.getItems().setAll(model.getAllResidents());
  }

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
      // Persist personal points to XML/JS so front-end can read them
      try {
        FileWriter fw = new FileWriter(model);
        fw.savePersonalPoints();
      } catch (Exception ex) {
        // non-fatal: log and continue
        System.err.println("Failed to save personal points: " + ex.getMessage());
      }
    } catch (NumberFormatException e) {
      messageLabel.setText("Points must be a whole number.");
    }
  }
}