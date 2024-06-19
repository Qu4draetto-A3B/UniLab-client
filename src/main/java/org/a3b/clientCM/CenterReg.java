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

public class CenterReg extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DOPO CHE SI SCHIACCIA LOGIN CON TUTTI GLI ATTRIBUTI
        //SERVE PER GESTIRE IL NUOVO CENTRO DI MONITORAGGIO O USARNE UNO GIA ESISTENTE
        //creazione dei bottoni e TextField
        Button existcenter = new Button("CENTRO DI MONITORAGGIO ESISTENTE");
        Button newcenter = new Button("NUOVO CENTRO DI MONITORAGGIO");
        //bottoni con immagini
        Button homeButton = CustomButton.homeButton(stage);
        Button backButton = CustomButton.backButton(stage,new Register());

        //bottone con immagine Home



        //bottone con immagine back







        //bottone
        newcenter.setOnAction(event -> {
            try {
                changeInNewMonitoringCenter(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        existcenter.setOnAction(event -> {
            try {
                changeInExistCenter(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(existcenter, newcenter,backButton,homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Scene scene = new Scene(vb, 1000, 1000);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena

    }

    private void changeInNewMonitoringCenter(Stage stage) throws Exception  {
        new NewMonitoringCenter(false).start(stage);
    }
    private void changeInExistCenter(Stage stage) throws Exception  {
        new ExistMonitorCenter().start(stage);
    }
}
