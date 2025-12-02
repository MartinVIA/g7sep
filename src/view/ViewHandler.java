package view;

import javafx.stage.Stage;
import model.ClovervilleModelManager;

public class ViewHandler {

    // this class is there for the overall window (main stage), it holds a reference
    // to model and opens a specific viewController, either the trading system or
    // the point system

    private Stage mainStage;
    // the window
    private ClovervilleModelManager model;

    public ViewHandler(ClovervilleModelManager model) {
        this.model = model;
    }

    public void start(Stage stage) {
        this.mainStage = stage;
        openStartView();
        // opens a screen specificly the main page
    }

    public void openStartGUI() {
        StartGUI control = new StartGUI(model);
        mainStage.setScene(control.createScene());
        mainStage.setTitle("Main Menu");
        mainStage.show();
    }

    public void openStartView() {
        StartViewController control = new StartViewController(model);
        mainStage.setScene(control.createScene());
        mainStage.setTitle("Main Menu");
        mainStage.show();
    }

    public void openVillagerView() {
        VillagerViewController control = new VillagerViewController(model);
        mainStage.setScene(control.createScene());
        mainStage.setTitle("Cloverville's villagers");
        mainStage.show();
    }

    public void openTradeView() {
        TradeViewController control = new TradeViewController(model);
        mainStage.setScene(control.createScene());
        mainStage.setTitle("Cloverville's Trades");
        mainStage.show();
    }
}