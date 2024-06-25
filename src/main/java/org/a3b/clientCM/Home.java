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

        Button search = new Button("VISUALIZZA AREA");
        Button reg = new Button("REGISTRAZIONE");
        Button login = new Button("LOGIN");

        //SETTAGGIO bottoni e TextField

        search.setOnAction(event -> {
            try {
                changeInSearch(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone login evento");
            }
        });
        reg.setOnAction(event -> {
            try {
                changeInOperator(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone reg evento");
            }
        });

        login.setOnAction(event -> {
            try {
                changeInLogin(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone login evento");
            }
        });


        //VBOX
        VBox vb = new VBox();
        vb.getChildren().add(imageViewLogo);
        vb.getChildren().addAll(search, reg, login);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Handler.sceneSetter(stage, vb);

    }

    private void changeInLogin(Stage stage) throws Exception {
        new Login().start(stage);
    }

    private void changeInOperator(Stage stage) throws Exception {
        new Register().start(stage);
    }

    private void changeInSearch(Stage stage) throws Exception {
        new SearchArea(false).start(stage);
    }
}
