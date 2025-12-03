package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;
import utils.MyFileHandler;

public class EditResidentController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  private TextField firstNameField;
  private TextField lastNameField;
  private TextField idField;
  private TextField pointsField;
  private CheckBox boostCheck;
  private Label messageLabel;

  public EditResidentController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  public Scene createScene() {

    firstNameField = new TextField(resident.getFirstName());
    lastNameField = new TextField(resident.getLastName());
    idField = new TextField(String.valueOf(resident.getId()));
    idField.setDisable(true); // we don't want to change the id
    pointsField = new TextField(String.valueOf(resident.getPersonalPoints()));
    boostCheck = new CheckBox("Has boost");
    boostCheck.setSelected(resident.getHasBoost());

    messageLabel = new Label();

    Button saveNameButton = new Button("Change name");
    saveNameButton.setOnAction(e -> handleChangeName());

    Button savePointsButton = new Button("Change points");
    savePointsButton.setOnAction(e -> handleChangePoints());

    Button toggleBoostButton = new Button("Apply boost setting");
    toggleBoostButton.setOnAction(e -> handleChangeBoost());

    // fast layout done by chatgpt can be changed idc
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(10));

    grid.add(new Label("First name:"), 0, 0);
    grid.add(firstNameField, 1, 0);
    grid.add(new Label("Last name:"), 0, 1);
    grid.add(lastNameField, 1, 1);
    grid.add(saveNameButton, 1, 2);

    grid.add(new Label("ID:"), 0, 3);
    grid.add(idField, 1, 3);

    grid.add(new Label("Points:"), 0, 5);
    grid.add(pointsField, 1, 5);
    grid.add(savePointsButton, 1, 6);

    grid.add(boostCheck, 1, 7);
    grid.add(toggleBoostButton, 1, 8);

    VBox root = new VBox(10, grid, messageLabel);
    root.setPadding(new Insets(10));

    return new Scene(root, 400, 350);
  }

  // methods for our messages

  private void handleChangeName() {
    String first = firstNameField.getText().trim();
    String last = lastNameField.getText().trim();

    if (first.isEmpty() || last.isEmpty()) {
      messageLabel.setText("Both first and last name must be filled.");
      return;
    }

    // example of “letters only” validation from your diagram
    if (!first.matches("[A-Za-z ]+") || !last.matches("[A-Za-z ]+")) {
      messageLabel.setText("Names should contain letters only (A–Z).");
      return;
    }

    resident.setFirstName(first);
    resident.setLastName(last);

    // now we check if bob tryes to change the name to the same name

    boolean sameFirst = first.equalsIgnoreCase(resident.getFirstName());
    boolean sameLast = last.equalsIgnoreCase(resident.getLastName());
    if (sameLast && sameFirst) {
      messageLabel.setText("Enter a different name");
      return;
      // this will only work for the original name that was entered but thats good
      // enough
    } else
      messageLabel.setText("Name updated.");
  }

  private void handleChangeId() {
    String text = idField.getText().trim();
    if (!text.matches("\\d+")) {
      messageLabel.setText("ID must contain digits only (0–9).");
      return;
    }

  }

  private void handleChangePoints() {
    String text = pointsField.getText().trim();
    if (!text.matches("\\d+")) {
      messageLabel.setText("Points must be a whole number (0–9).");
      return;
    }

    int pts = Integer.parseInt(text);
    resident.setPersonalPoints(pts);
    messageLabel.setText("Points updated.");
  }

  private void handleChangeBoost() {
    boolean hasBoost = boostCheck.isSelected();
    resident.setBoost(hasBoost);
    messageLabel.setText("Boost status updated.");
  }

}
