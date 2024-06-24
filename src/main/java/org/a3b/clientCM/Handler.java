package org.a3b.clientCM;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Handler {

    public static void sceneSetter(Stage stage, Parent parent) {
        Scene scene = new Scene(parent, 800, 450);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");
        stage.show(); //mostra scena
    }


}
