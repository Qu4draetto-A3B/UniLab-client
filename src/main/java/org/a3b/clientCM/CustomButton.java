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
        button.setOnAction(event -> {
            try {
                new Home().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return button;
    }

    public static Button backButton(Stage stage, Application screen) {

        ImageView homeView = getImage("/img/arrow.png");

        Button button = new Button();
        button.setGraphic(homeView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> {
            try {
                screen.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

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
