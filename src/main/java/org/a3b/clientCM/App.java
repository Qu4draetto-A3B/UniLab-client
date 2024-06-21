package org.a3b.clientCM;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
//MAIN

    @Override
    public void start(Stage stage) throws Exception {
        new Home().start(stage);
    }
    public static void main(String[] args) {
        launch();
    }

}