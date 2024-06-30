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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.SceneHandler;

import java.io.InputStream;

public class Home extends Application {
    //SCHERMATA INIZIALE CON LOGO + REGISTRAZIONE + LOGIN
    @Override
    public void start(Stage stage) throws Exception {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        //IMMAGINE:
        InputStream in = getClass().getResourceAsStream("/img/logoclimatemonitoring.png");
        Image logo = new Image(in); // Assicurati che il percorso sia corretto
        ImageView imageViewLogo = new ImageView(logo);
        double side = 200;
        imageViewLogo.setFitWidth(side); //larghezza
        imageViewLogo.setFitHeight(side); //altezza

        //Creazione button
        Button search = new Button("VISUALIZZA AREA");
        Button reg = new Button("REGISTRAZIONE");
        Button login = new Button("LOGIN");


        search.setOnAction(event -> SceneHandler.sceneChanger(stage,new SearchArea()));
        reg.setOnAction(event -> SceneHandler.sceneChanger(stage,new Register()));
        login.setOnAction(event -> SceneHandler.sceneChanger(stage, new Login()));


        //VBOX
        VBox vb = new VBox();
        vb.getChildren().add(imageViewLogo);
        vb.getChildren().addAll(search, reg, login);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        SceneHandler.sceneSetter(stage, vb);

    }

}
