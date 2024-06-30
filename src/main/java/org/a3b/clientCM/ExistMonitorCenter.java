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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.RegisterHandler;
import org.a3b.clientCM.resource.SceneHandler;
import org.a3b.clientCM.resource.SubList;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.ListaCentri;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class ExistMonitorCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni con immagini
        ListaCentri lc = App.server.getCentriMonitoraggio().get();

        ListView<SubList> list = new ListView<>();
        for(CentroMonitoraggio cm : lc){
            list.getItems().add(new SubList(cm));
        }
        list.setOnKeyPressed(event -> {
            try {
                handleKeyPress(event, list);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        list.setOnMouseClicked(event -> {
            try {
                handleClick(event, list);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });


        Button homeButton = CustomButton.homeButton(stage);
        Button backButton = CustomButton.backButton(stage, new CenterReg());
        //bottone con immagine Home


        //bottone back

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(list,backButton, homeButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        SceneHandler.sceneSetter(stage, vb);
    }

    private void handleKeyPress(KeyEvent event, ListView<SubList> listView) throws RemoteException {
        if (event.getCode() == KeyCode.ENTER) {
            SubList selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RegisterHandler.tmpCentro = selectedItem.getCentro();
                RegisterHandler.newOperator();
            }
        }
    }

    private void handleClick(MouseEvent event, ListView<SubList> listView) throws RemoteException {
        if (event.getClickCount() == 2) {
            SubList selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RegisterHandler.tmpCentro = selectedItem.getCentro();
                RegisterHandler.newOperator();
            }
        }
    }


}
