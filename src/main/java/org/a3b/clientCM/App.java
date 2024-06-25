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