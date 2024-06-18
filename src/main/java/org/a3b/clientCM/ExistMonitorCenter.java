package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class ExistMonitorCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni con immagini
        //bottone con immagine Home
        InputStream inhome = getClass().getResourceAsStream("/img/home.png");
        Image home = new Image(inhome);
        ImageView homeView = new ImageView(home);
        homeView.setFitWidth(50);  // Imposta la larghezza desiderata
        homeView.setFitHeight(50); // Imposta l'altezza desiderata
        homeView.setPreserveRatio(true); // Mantiene le proporzioni originali
        Button homeButton = new Button();
        homeButton.setGraphic(homeView);
        homeButton.setStyle("-fx-background-color: transparent;"); // Rende trasparente lo sfondo del bottone


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




        //bottone back
        backButton.setOnAction(event -> {
            try {
                changeInCenterReg(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone home
        homeButton.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(backButton,homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Scene scene = new Scene(vb, 800, 400, Color.WHITE); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");

    }
    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInCenterReg(Stage stage) throws Exception {
        new CenterReg().start(stage);
    }
}
