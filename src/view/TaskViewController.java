package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ClovervilleModelManager;

public class TaskViewController {

    private ClovervilleModelManager model;

    public TaskViewController(ClovervilleModelManager model) {
        this.model = model;
    }

    public Scene createScene() {
        VBox root = new VBox();
        root.getChildren().add(new Label("Tasks"));
        return new Scene(root, 400, 300);
    }

}