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
import org.a3b.clientCM.resource.Controller;
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.SceneHandler;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI LOGIN DOVE INSERIRE USERID E PASSWORD e accedere ai vari centri di monitoraggi o crearne di nuovi
        //creazione dei bottoni e TextField
        //bottoni con immagini
        //bottone con immagine back

        Button backButton = CustomButton.backButton(stage, new Home());


        Button login = new Button("ACCEDI");
        TextField userID = new TextField();
        TextField password = new TextField();
        Label userLabel = new Label();


        //bottone back


        //bottone login
        login.setOnAction(event -> {
            try {
                if(Controller.loginControl(userID.getText(), password.getText())) {
                    changeInOperator(stage);
                } else {
                    userLabel.setText("Password o UserID errati");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //SETTAGGIO bottoni e TextField
        userID.setPromptText("INSERISCI USERID");
        password.setPromptText("INSERISCI PASSWORD");


        VBox vb = new VBox();
        vb.getChildren().addAll(userID, password, userLabel, login, backButton);

        vb.setAlignment(Pos.CENTER);


        //SCENA
        SceneHandler.sceneSetter(stage, vb);
        userLabel.requestFocus();
    }

    private void changeInOperator(Stage stage) throws Exception {
        new Operator().start(stage);
    }



}
