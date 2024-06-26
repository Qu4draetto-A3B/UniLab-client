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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.Operatore;

public class Handler {
    public static Operatore tmpOperatore = new Operatore();
    public static CentroMonitoraggio tmpCentro = new CentroMonitoraggio();
    public static String tmpPassword = "";


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

    public static void setTmpOperator(String[] attributi){
        tmpOperatore.setNome(attributi[0]);
        tmpOperatore.setCognome(attributi[1]);
        tmpOperatore.setCf(attributi[2]);
        tmpOperatore.setEmail(attributi[3]);

        tmpPassword = attributi[4];

    }

    public static void setTmpCentro(String[] attributi){
        tmpCentro.setNome(attributi[0]);
        tmpCentro.setNomeVia(attributi[1]);
        tmpCentro.setCivico(Integer.parseInt(attributi[2]));
        tmpCentro.setCap(Integer.parseInt(attributi[3]));
        tmpCentro.setComune(attributi[4]);
        tmpCentro.setProvincia(attributi[5]);
    }

    public static long getGeoIDFromString(String input){
        int spaceIndex = input.indexOf(' ');

        return  Long.parseLong(input.substring(1, spaceIndex-1));
    }


}
