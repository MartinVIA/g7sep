package view;

import javafx.stage.Stage;
import model.ClovervilleModelManager;

public class ViewHandler {
    private Stage mainStage;
    private ClovervilleModelManager model;

    public ViewHandler(ClovervilleModelManager model) {
        this.model = model;
    }

    public void start(Stage stage) {
        this.mainStage = stage;
        openVillagerView();
        // opens a screen
    }

    public void openVillagerView() {
        VillagerViewController control = new VillagerViewController(model);
        mainStage.setScene(control.createScene());
        mainStage.setTitle("Cloverville's villagers");
        mainStage.show();
    }
}