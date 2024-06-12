package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //creazione dei bottoni e TextField
        Button back = new Button();
        TextField userID = new TextField();
        TextField password = new TextField();

        //bottone back
        back.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //SETTAGGIO bottoni e TextField
        userID.setPromptText("INSERISCI USERID");
        password.setPromptText("INSERISCI PASSWORD");

        VBox vb = new VBox();
        vb.getChildren().addAll(userID,password,back);

        //SCENA
        Scene scene = new Scene(vb, 800, 400); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena
    }
    public void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
}
