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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class CustomButton {


    public static Button homeButton(Stage stage) {

        ImageView homeView = getImage("/img/home.png");

        Button button = new Button();
        button.setGraphic(homeView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> Handler.sceneChanger(stage, new Home()));

        return button;
    }

    public static Button backButton(Stage stage, Application screen) {

        ImageView homeView = getImage("/img/arrow.png");

        Button button = new Button();
        button.setGraphic(homeView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> Handler.sceneChanger(stage, screen));

        return button;
    }

    public static ImageView getImage(String imagePath) {
        InputStream imageStream = CustomButton.class.getResourceAsStream(imagePath);
        Image home = new Image(imageStream);
        ImageView homeView = new ImageView(home);
        homeView.setFitWidth(50);  // Imposta la larghezza desiderata
        homeView.setFitHeight(50); // Imposta l'altezza desiderata
        homeView.setPreserveRatio(true);
        return homeView;
    }

}
