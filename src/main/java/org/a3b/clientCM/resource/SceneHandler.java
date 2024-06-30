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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Questa classe fornisce metodi statici per gestire le scene JavaFX all'interno di un'applicazione.
 */
public class SceneHandler {

    /**
     * Imposta una nuova scena sullo stage specificato.
     *
     * @param stage Lo stage su cui impostare la nuova scena.
     * @param parent Il nodo radice della nuova scena.
     */
    public static void sceneSetter(Stage stage, Parent parent) {
        Scene scene = new Scene(parent, 800, 450);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); // Imposta la nuova scena sullo stage
        stage.setTitle("Climate Monitoring");
        stage.show(); // Mostra la nuova scena
    }

    /**
     * Cambia la scena corrente dell'applicazione utilizzando un'istanza di Application.
     *
     * @param stage Lo stage su cui cambiare la scena.
     * @param screen L'istanza di Application che rappresenta la nuova schermata da visualizzare.
     */
    public static void sceneChanger(Stage stage, Application screen) {
        try {
            screen.start(stage); // Avvia la nuova schermata utilizzando il metodo start() dell'istanza di Application
        } catch (Exception e) {
            // Gestione dell'eccezione nel caso in cui la nuova schermata non possa essere avviata
            e.printStackTrace();
        }
    }
}
