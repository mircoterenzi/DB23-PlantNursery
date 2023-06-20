package it.unibo.nursery;

import it.unibo.nursery.view.impl.FxAppView;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlantNurseryApp extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        new FxAppView(primaryStage);
    }
    
}
