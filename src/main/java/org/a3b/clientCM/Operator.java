package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;

public class Operator extends Application {
    //dopo aver schiacciato il login si apre una pagina con i vari bottoni:
    //inserire parametri, creare nuovo centro di monitoraggio, visualizza aree, e impostazioni
    @Override
    public void start(Stage stage) throws Exception {
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
        //bottoni
        Button insertParam = new Button("INSERISCI PARAMETRI");
        Button createNewCenter = new Button("CREA NUOVO CENTRO DI MONITORAGGIO");
        Button viewAree = new Button("VISUALIZZA AREE");
        Button settings = new Button("IMPOSTAZIONI");

        //bottone back
        backButton.setOnAction(event -> {
            try {
                changeInLogin(stage);
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

        //bottone inserimento parametri
        insertParam.setOnAction(event -> {
            try {
                changeInInsertParam(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //botton visualizza aree
        viewAree.setOnAction(event -> {
            try {
                changeInSearchAree(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone nuovo centro di monitoraggio
        createNewCenter.setOnAction(event -> {
            try {
                changeInNewMonitoringCenter(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(insertParam, createNewCenter, viewAree, settings,backButton,homeButton);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Scene scene = new Scene(vb, 800, 400); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena
    }

    private void changeInSearchAree(Stage stage) throws Exception {
        new SearchArea().start(stage);
    }

    private void changeInInsertParam(Stage stage) throws Exception {
        new ClimateParameters().start(stage);
    }
    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInLogin(Stage stage) throws Exception {
        new Login().start(stage);
    }
    private void changeInNewMonitoringCenter (Stage stage) throws Exception{
        new NewMonitoringCenter().start(stage);
    }
}
