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

/**
 * Controller responsible for creating a view that allows
 * editing the personal points of a resident.
 * The controller validates the input before updating
 * the resident’s points.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class EditPointsController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  /**
   * Constructs an EditPointsController with the given
   * model and resident.
   *
   * @param model the model manager used to access application data
   * @param resident the resident whose personal points will be edited
   */
  public EditPointsController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  /**
   * Creates and returns the JavaFX scene used to edit
   * the personal points of a resident.
   *
   * @return a Scene displaying the edit points view
   */
  public Scene createScene() {
      Label titleLabel = new Label("New points for resident: "+resident.getLastName()+" "+resident.getLastName());
      titleLabel.getStyleClass().add("title");


    TextField pointsField = new TextField(
        String.valueOf(resident.getPersonalPoints()));
    Label msg = new Label();

    Button save = new Button("Save");
    save.setOnAction(e -> {
      String text = pointsField.getText().trim();

      if (!text.matches("\\d+")) {
        msg.setText("Points must be a whole number (0-9).");
        return;
      }

      int pts = Integer.parseInt(text);
      resident.setPersonalPoints(pts);

      msg.setText("Points updated.");
      ((Stage) save.getScene().getWindow()).close();
    });

    VBox root = new VBox(10,
        titleLabel,
        pointsField,
        save,
        msg);
    root.setPadding(new Insets(10));

    return new Scene(root, 280, 130);
  }
}
