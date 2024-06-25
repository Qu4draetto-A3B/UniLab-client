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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewMonitoringCenter extends Application {

    private final boolean login;

    public NewMonitoringCenter(boolean login) {
        this.login = login;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = setButton(stage);
        Button backButton = CustomButton.backButton(stage, new CenterReg()); //bottone back
        Button homeButton = CustomButton.homeButton(stage); //bottone home
        //DOPO AVER SCHIACCIATO NUOVO CENTRO DI MONITORAGGIO
        //TUTTI GLI ATTRIBUTI DEL CENTRO DI MONITORAGGIO
        //BOTTONI E TEXTFIELD
        TextField name = new TextField();
        TextField street = new TextField();
        TextField civicnumber = new TextField();
        TextField zipcode = new TextField();
        TextField town = new TextField();
        TextField province = new TextField();

        Button enter = new Button("CONFERMA");
        //bottoni con immagini


        //SET TEXTFIELD
        town.setPromptText("INSERISCI CITTA");
        province.setPromptText("INSERISCI PROVINCIA");
        street.setPromptText("INSERISCI STRADA");
        civicnumber.setPromptText("INSERISCI NUMEROCIVICO");
        zipcode.setPromptText("INSERISCI ZIPCODE");


        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(town, province, street, civicnumber, zipcode, enter, pane);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Handler.sceneSetter(stage, vb);

    }

    private Pane setButton(Stage stage) {
        VBox vb = new VBox();
        Button homeButton = CustomButton.homeButton(stage);
        Button backButton;
        if (login) {
            backButton = CustomButton.backButton(stage, new Operator()); //bottone back
        } else {
            backButton = CustomButton.backButton(stage, new CenterReg());
        }
        vb.getChildren().addAll(homeButton, backButton);
        return vb;
    }
}
