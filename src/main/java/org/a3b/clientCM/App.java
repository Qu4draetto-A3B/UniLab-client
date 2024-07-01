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

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.stage.Stage;
import org.a3b.commons.ServicesCM;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.Operatore;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * JavaFX App
 */
public class App extends Application {
//MAIN
    public static ServicesCM server;
    public static Operatore operatore = null;
    public static CentroMonitoraggio centro = null;
    public static void main(String[] args) throws Exception {
        Dotenv env = Dotenv.configure()
                .filename((args.length > 0) ? args[0] : "config.env")
                .ignoreIfMissing()
                .load();

        Registry reg = LocateRegistry.getRegistry(env.get("RMI_HOST", "localhost"));
        server = (ServicesCM) reg.lookup("CM");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        new Home().start(stage);
    }

}