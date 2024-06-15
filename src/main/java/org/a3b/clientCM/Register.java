package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI REGISTRAZIONE CON TUTTI GLI ATTRIBUTI
        //creazione dei bottoni e TextField
        InputStream inback =  getClass().getResourceAsStream("/img/arrow.png");
        Image back = new Image(inback);
        ImageView backView = new ImageView(back);
        backView.setFitWidth(50);  // Imposta la larghezza desiderata
        backView.setFitHeight(50); // Imposta l'altezza desiderata
        backView.setPreserveRatio(true); // Mantiene le proporzioni originali
        Button backButton = new Button();
        backButton.setGraphic(backView);
        backButton.setStyle("-fx-background-color: transparent;"); // Rende trasparente lo sfondo del bottone
        Button reg = new Button("REGISTRAZIONE");

        TextField userID = new TextField();
        TextField name = new TextField();
        TextField surname = new TextField();
        TextField CF = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();


        //bottone back
        backButton.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone reg
        reg.setOnAction(event -> {
            try {
                changeInOperatorReg(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //SETTAGGIO bottoni e TextField
        userID.setPromptText("INSERISCI USERID");
        name.setPromptText("INSERISCI NOME");
        surname.setPromptText("INSERISCI COGNOME");
        CF.setPromptText("INSERISCI CODICE FISCALE");
        email.setPromptText("INSERISCI EMAIL");
        password.setPromptText("INSERISCI PASSWORD");
        //---------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------
        //---------------------GESTIRE LA ROBA DEL CENTRO COME ATTRIBUTO-------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------------------
        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(userID,name,surname,CF,email,password,reg,backButton);

        //SCENA
        Scene scene = new Scene(vb, 800, 400); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena




    }

    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInOperatorReg(Stage stage) throws Exception {
        new CenterReg().start(stage);
    }
}
