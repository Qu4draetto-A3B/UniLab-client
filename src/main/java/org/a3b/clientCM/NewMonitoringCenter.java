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
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.RegisterHandler;
import org.a3b.clientCM.resource.SceneHandler;

public class NewMonitoringCenter extends Application {

    private TextField name = new TextField();
    private TextField street = new TextField();
    private TextField civicnumber = new TextField();
    private TextField zipcode = new TextField();
    private TextField town = new TextField();
    private TextField province = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = setButton(stage);
        //DOPO AVER SCHIACCIATO NUOVO CENTRO DI MONITORAGGIO
        //TUTTI GLI ATTRIBUTI DEL CENTRO DI MONITORAGGIO
        //BOTTONI E TEXTFIELD


        Button enter = new Button("CONFERMA");
        //bottoni con immagini


        //SET TEXTFIELD
        name.setPromptText("INSERISCI NOME CENTRO");
        town.setPromptText("INSERISCI CITTA");
        province.setPromptText("INSERISCI PROVINCIA");
        street.setPromptText("INSERISCI STRADA");
        civicnumber.setPromptText("INSERISCI NUMEROCIVICO");
        zipcode.setPromptText("INSERISCI ZIPCODE");


        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(name,town, province, street, civicnumber, zipcode, pane);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        SceneHandler.sceneSetter(stage, vb);

    }

    private Pane setButton(Stage stage) {
        VBox vb = new VBox();
        Button backButton;
        Button button = new Button();
        if (App.operatore != null) {
            backButton = CustomButton.backButton(stage, new Operator());
            button.setText("CREA CENTRO");

        } else {
            backButton = CustomButton.backButton(stage, new Home());
            button.setText("REGISTRATI");
            button.setOnAction(event -> {
                String[] tmp = {name.getText(),street.getText(), civicnumber.getText(), zipcode.getText(), town.getText(),province.getText()};
                RegisterHandler.setTmpCentro(tmp);
                System.out.println(RegisterHandler.tmpCentro.toString());
                SceneHandler.sceneChanger(stage,new SelectArea());
            });
        }
        vb.getChildren().addAll(backButton,button);

        return vb;
    }
}
