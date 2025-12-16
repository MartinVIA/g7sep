package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;
import model.Resident;

/**
 * Controller responsible for creating a view that allows
 * changing the first and last name of a resident.
 * The controller validates the input before applying
 * the name changes to ensure correctness.
 *
 * @author Loke Hansen
 * @version 1.0
 */
public class ChangeNameController {

  private final ClovervilleModelManager model;
  private final Resident resident;

  /**
   * Constructs a ChangeNameController with the given model
   * and resident whose name can be changed.
   *
   * @param model the model manager used to access application data
   * @param resident the resident whose name will be updated
   */
  public ChangeNameController(ClovervilleModelManager model, Resident resident) {
    this.model = model;
    this.resident = resident;
  }

  /**
   * Creates and returns the JavaFX scene used to change
   * the resident’s first and last name.
   * The method validates that both names are filled,
   * contain only letters, and differ from the current name.
   *
   * @return a Scene displaying the change name view
   */
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
    });

    VBox root = new VBox(10,
        new Label("First name:"), firstField,
        new Label("Last name:"), lastField,
        save, msg);
    root.setPadding(new Insets(10));

    return new Scene(root, 300, 220);
  }
}
