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
package org.a3b.clientCM.resource;


import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.a3b.clientCM.Home;

import java.io.InputStream;
/**
 * Questa classe fornisce metodi di utilità per creare pulsanti personalizzati in JavaFX con funzionalità specifiche.
 */
public class CustomButton {

    /**
     * Crea un pulsante "home" con un'immagine specificata e un'azione associata.
     *
     * @param stage Il palcoscenico principale dell'applicazione.
     * @return Un oggetto Button che rappresenta il pulsante "home".
     */
    public static Button homeButton(Stage stage) {
        ImageView homeView = getImage("/img/home.png");

        Button button = new Button();
        button.setGraphic(homeView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> SceneHandler.sceneChanger(stage, new Home()));

        return button;
    }

    /**
     * Crea un pulsante "indietro" con un'immagine specificata e un'azione associata.
     *
     * @param stage  Il palcoscenico principale dell'applicazione.
     * @param screen L'applicazione di destinazione verso cui tornare.
     * @return Un oggetto Button che rappresenta il pulsante "indietro".
     */
    public static Button backButton(Stage stage, Application screen) {
        ImageView backView = getImage("/img/arrow.png");

        Button button = new Button();
        button.setGraphic(backView);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(event -> SceneHandler.sceneChanger(stage, screen));

        return button;
    }

    /**
     * Carica e restituisce un'ImageView con l'immagine specificata dal percorso della risorsa.
     *
     * @param imagePath Il percorso alla risorsa dell'immagine.
     * @return Un oggetto ImageView che visualizza l'immagine caricata.
     */
    public static ImageView getImage(String imagePath) {
        InputStream imageStream = CustomButton.class.getResourceAsStream(imagePath);
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);  // Imposta la larghezza desiderata
        imageView.setFitHeight(50); // Imposta l'altezza desiderata
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
