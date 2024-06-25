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
import javafx.stage.Stage;
import org.a3b.commons.ServicesCM;
import org.a3b.commons.magazzeno.Operatore;
import org.a3b.serverCM.ServerCM;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * JavaFX App
 */
public class App extends Application {
//MAIN
    public static ServicesCM server;
    public static Operatore operatore = null;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Registry reg = LocateRegistry.getRegistry();
        server = (ServicesCM) reg.lookup("CM");
        new Home().start(stage);
    }

}