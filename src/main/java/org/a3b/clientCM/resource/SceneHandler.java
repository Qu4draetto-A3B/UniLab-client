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

public class SceneHandler {


    public static void sceneSetter(Stage stage, Parent parent) {
        Scene scene = new Scene(parent, 800, 450);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");
        stage.show(); //mostra scena
    }

    public static void sceneChanger(Stage stage, Application screen){
        try{
            screen.start(stage);
        } catch(Exception e){

        }
    }



}
