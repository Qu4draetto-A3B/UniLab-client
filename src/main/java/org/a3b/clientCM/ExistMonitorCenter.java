package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExistMonitorCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni
        Button home = new Button();
        Button back = new Button();

        //bottone back
        back.setOnAction(event -> {
            try {
                changeInCenterReg(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone home
        home.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(back,home);
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
