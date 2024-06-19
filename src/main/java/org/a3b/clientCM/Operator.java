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

public class Operator extends Application {
    //dopo aver schiacciato il login si apre una pagina con i vari bottoni:
    //inserire parametri, creare nuovo centro di monitoraggio, visualizza aree, e impostazioni
    @Override
    public void start(Stage stage) throws Exception {
        //bottoni con immagini
        Button backButton = CustomButton.backButton(stage,new Login()); //bottone back
        Button homeButton = CustomButton.homeButton(stage); //bottone home
        //bottoni
        Button insertParam = new Button("INSERISCI PARAMETRI");
        Button createNewCenter = new Button("CREA NUOVO CENTRO DI MONITORAGGIO");
        Button viewAree = new Button("VISUALIZZA AREE");
        Button settings = new Button("IMPOSTAZIONI");



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
        Scene scene = new Scene(vb, 1000, 1000, Color.WHITE); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena
    }

    private void changeInSearchAree(Stage stage) throws Exception {
        new SearchArea(true).start(stage);
    }

    private void changeInInsertParam(Stage stage) throws Exception {
        new ClimateParameters().start(stage);
    }

    private void changeInNewMonitoringCenter (Stage stage) throws Exception{
        new NewMonitoringCenter(true).start(stage);
    }
}
