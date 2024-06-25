package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        Button backButton = CustomButton.backButton(stage, new Register());

        //bottone
        newcenter.setOnAction(event -> Handler.sceneChanger(stage, new NewMonitoringCenter()));

        existcenter.setOnAction(event -> Handler.sceneChanger(stage, new ExistMonitorCenter()));

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(existcenter, newcenter, backButton, homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Handler.sceneSetter(stage, vb); //mostra scena

    }



}
