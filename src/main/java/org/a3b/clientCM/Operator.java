/*
 * Interdisciplinary Workshop B
 * Climate Monitoring
 * A.A. 2023-2024
 *
 * Authors:
 * - Iuri Antico, 753144, VA
 * - Beatrice Balzarini, 752257, VA
 * - Michael Bernasconi, 752259, VA
 * - Gabriele Borgia, 753262, VA
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
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
        Button backButton = CustomButton.backButton(stage, new Login()); //bottone back
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
        vb.getChildren().addAll(insertParam, createNewCenter, viewAree, settings, backButton, homeButton);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Handler.sceneSetter(stage, vb);
    }

    private void changeInSearchAree(Stage stage) throws Exception {
        new SearchArea(true).start(stage);
    }

    private void changeInInsertParam(Stage stage) throws Exception {
        new ClimateParameters().start(stage);
    }

    private void changeInNewMonitoringCenter(Stage stage) throws Exception {
        new NewMonitoringCenter(true).start(stage);
    }
}
