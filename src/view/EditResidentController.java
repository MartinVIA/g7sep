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

/**
 * Controller responsible for creating a view that allows
 * editing the details of a resident.
 * The controller supports changing the resident’s name,
 * personal points, and boost status.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class EditResidentController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  private TextField firstNameField;
  private TextField lastNameField;
  private TextField idField;
  private TextField pointsField;
  private CheckBox boostCheck;
  private Label messageLabel;

  /**
   * Constructs an EditResidentController with the given
   * model and resident.
   *
   * @param model the model manager used to access application data
   * @param resident the resident whose data will be edited
   */
  public EditResidentController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  /**
   * Creates and returns the JavaFX scene used to edit
   * the resident’s information.
   * The scene allows changing the name, points,
   * and boost status of the resident.
   *
   * @return a Scene displaying the edit resident view
   */
  public Scene createScene() {

    firstNameField = new TextField(resident.getFirstName());
    lastNameField = new TextField(resident.getLastName());
    idField = new TextField(String.valueOf(resident.getId()));
    idField.setDisable(true);
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

  /**
   * Handles changing the resident’s first and last name.
   * The method validates that both names are filled and
   * contain only letters before updating the resident.
   */
  private void handleChangeName() {
    String first = firstNameField.getText().trim();
    String last = lastNameField.getText().trim();

    if (first.isEmpty() || last.isEmpty()) {
      messageLabel.setText("Both first and last name must be filled.");
      return;
    }

    if (!first.matches("[A-Za-z ]+") || !last.matches("[A-Za-z ]+")) {
      messageLabel.setText("Names should contain letters only (A–Z).");
      return;
    }

    resident.setFirstName(first);
    resident.setLastName(last);

    boolean sameFirst = first.equalsIgnoreCase(resident.getFirstName());
    boolean sameLast = last.equalsIgnoreCase(resident.getLastName());
    if (sameLast && sameFirst) {
      messageLabel.setText("Enter a different name");
      return;
    } else
      messageLabel.setText("Name updated.");
  }

  /**
   * Handles validation for changing the resident ID.
   * The ID field is disabled, so this method is currently
   * not used for updating the resident.
   */
  private void handleChangeId() {
    String text = idField.getText().trim();
    if (!text.matches("\\d+")) {
      messageLabel.setText("ID must contain digits only (0–9).");
      return;
    }
  }

  /**
   * Handles changing the resident’s personal points.
   * The method validates that the entered value is a
   * whole number before updating the resident.
   */
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

  /**
   * Handles changing the resident’s boost status.
   * The boost value is updated based on the checkbox selection.
   */
  private void handleChangeBoost() {
    boolean hasBoost = boostCheck.isSelected();
    resident.setBoost(hasBoost);
    messageLabel.setText("Boost status updated.");
  }
}
