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
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.SceneHandler;

public class ExistMonitorCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni con immagini

        Button homeButton = CustomButton.homeButton(stage);
        Button backButton = CustomButton.backButton(stage, new CenterReg());
        //bottone con immagine Home


        //bottone back

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(backButton, homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        SceneHandler.sceneSetter(stage, vb);
    }

}
