package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Operator extends Application {
    //dopo aver schiacciato il login si apre una pagina con i vari bottoni:
    //inserire parametri, creare nuovo centro di monitoraggio, visualizza aree, e impostazioni
    @Override
    public void start(Stage stage) throws Exception {
        //bottoni con immagini

        //bottoni
        Button insertParam = new Button("INSERISCI PARAMETRI");
        Button createNewCenter = new Button("CREA NUOVO CENTRO DI MONITORAGGIO");
        Button viewAree = new Button("VISUALIZZA AREE");
        Button settings = new Button("IMPOSTAZIONI");
        Button exit = new Button("ESCI");

        //bottone inserimento parametri
        insertParam.setOnAction(event -> {
            try {
                changeInInsertParam(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        exit.setOnAction(event ->{
            App.operatore = null;
            try {
                new Home().start(stage);
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
        vb.getChildren().addAll(insertParam, createNewCenter, viewAree, settings, exit);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Handler.sceneSetter(stage, vb);
    }

    private void changeInSearchAree(Stage stage) throws Exception {
        new SearchArea().start(stage);
    }

    private void changeInInsertParam(Stage stage) throws Exception {
        new ClimateParameters().start(stage);
    }

    private void changeInNewMonitoringCenter(Stage stage) throws Exception {
        new NewMonitoringCenter().start(stage);
    }
}
