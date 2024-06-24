package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        Handler.sceneSetter(stage, vb);
    }

}
