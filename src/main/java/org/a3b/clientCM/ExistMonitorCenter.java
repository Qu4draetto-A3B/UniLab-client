package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class ExistMonitorCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni con immagini

        Button homeButton = CustomButton.homeButton(stage);
        Button backButton = CustomButton.backButton(stage,new CenterReg());
        //bottone con immagine Home




        //bottone back

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(backButton,homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Scene scene = new Scene(vb, 1000, 1000, Color.WHITE); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");

    }

}
