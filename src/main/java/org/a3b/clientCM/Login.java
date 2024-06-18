package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI LOGIN DOVE INSERIRE USERID E PASSWORD e accedere ai vari centri di monitoraggi o crearne di nuovi
        //creazione dei bottoni e TextField
        //bottoni con immagini
        //bottone con immagine back
        InputStream inback =  getClass().getResourceAsStream("/img/arrow.png");
        Image back = new Image(inback);
        ImageView backView = new ImageView(back);
        backView.setFitWidth(50);  // Imposta la larghezza desiderata
        backView.setFitHeight(50); // Imposta l'altezza desiderata
        backView.setPreserveRatio(true); // Mantiene le proporzioni originali
        Button backButton = new Button();
        backButton.setGraphic(backView);
        backButton.setStyle("-fx-background-color: transparent;"); // Rende trasparente lo sfondo del bottone



        Button login = new Button("LOGIN");
        TextField userID = new TextField();
        TextField password = new TextField();

        //bottone back
        backButton.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone login
        login.setOnAction(event -> {
            try {
                changeInOperator(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //SETTAGGIO bottoni e TextField
        userID.setPromptText("INSERISCI USERID");
        password.setPromptText("INSERISCI PASSWORD");



        VBox vb = new VBox();
        vb.getChildren().addAll(userID,password,login,backButton);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Scene scene = new Scene(vb, 1000, 1000);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena
    }
    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInOperator(Stage stage) throws Exception {
        new Operator().start(stage);
    }

}
