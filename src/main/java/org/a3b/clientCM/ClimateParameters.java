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
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.ParametersTable;
import org.a3b.clientCM.resource.RowParametres;
import org.a3b.clientCM.resource.SceneHandler;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;

import java.rmi.RemoteException;

public class ClimateParameters extends Application {
//Classe utilizzata per creare una misuraziore relaztiva ad una area geografica
    @Override
    public void start(Stage stage) throws RemoteException {

        Button backButton = CustomButton.backButton(stage, new Operator()); // Bottone back
        ListaAree listaAree=new ListaAree();

        // Crea il TableView
        CentroMonitoraggio cm = App.centro;

        System.out.println("CENTRO :\n"+cm+"\nLISTA AREE : "+cm.getAree().toString());
        ParametersTable pt = new ParametersTable();
        TableView<RowParametres> tv = pt.getTableView();
        Button b = new Button("Invio");
        VBox vb = new VBox(tv, b, backButton);

        b.setOnAction(event -> {
            String str = pt.getTableParameter();
            System.out.println(str);

        });

        SceneHandler.sceneSetter(stage, vb);


    }
}
