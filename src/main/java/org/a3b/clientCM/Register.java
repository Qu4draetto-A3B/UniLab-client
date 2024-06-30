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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.*;
import org.a3b.commons.magazzeno.Operatore;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI REGISTRAZIONE CON TUTTI GLI ATTRIBUTI
        //creazione dei bottoni e TextField
        Label invisibleLabel = new Label();
        TextField name = new TextField();
        TextField surname = new TextField();
        TextField CF = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();

        //bottone reg
        Button backButton = CustomButton.backButton(stage, new Home());

        Button next = new Button("AVANTI");
        next.setOnAction(event -> {

            String[] str = {name.getText(), surname.getText(),CF.getText(), email.getText(), password.getText()};
            if(Controller.validOperatore(str)) {
                SceneHandler.sceneChanger(stage, new CenterReg());
            } else {
                invisibleLabel.setText("PARAMETRO/I NON VALIDO/I");
            }
        });




        //oggetto operatore di supporto alla registrazione
        Operatore op = new Operatore();






        //SETTAGGIO bottoni e TextField
        name.setPromptText("INSERISCI NOME");
        surname.setPromptText("INSERISCI COGNOME");
        CF.setPromptText("INSERISCI CODICE FISCALE");
        email.setPromptText("INSERISCI EMAIL");
        password.setPromptText("INSERISCI PASSWORD");

        VBox vb = new VBox();
        vb.getChildren().addAll(name, surname, CF, email, password, invisibleLabel, next, backButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        SceneHandler.sceneSetter(stage, vb);
        invisibleLabel.requestFocus();

    }



}
