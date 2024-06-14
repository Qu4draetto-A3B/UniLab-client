package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OperatorReg extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //creazione dei bottoni e TextField
        Button existcenter = new Button("CENTRO DI MONITORAGGIO ESISTENTE");
        Button newcenter = new Button("NUOVO CENTRO DI MONITORAGGIO");
        Button home = new Button();
        Button back = new Button();

        //bottone back
        back.setOnAction(event -> {
            try {
                changeInOperator(stage);
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

        //bottone
        newcenter.setOnAction(event -> {
            try {
                changeInNewMonitoringCenter(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(existcenter, newcenter,back,home);

        //SCENA
        Scene scene = new Scene(vb, 800, 400); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena

    }

    private void changeInNewMonitoringCenter(Stage stage) throws Exception  {
        new NewMonitoringCenter().start(stage);
    }

    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInOperator(Stage stage) throws Exception {
        new Operator().start(stage);
    }

}
