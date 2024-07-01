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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.*;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Misurazione;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClimateParameters extends Application {
//Classe utilizzata per creare una misuraziore relaztiva ad una area geografica
    @Override
    public void start(Stage stage) throws RemoteException {
        TextField searchField = new TextField();
        searchField.setPromptText("Cerca...");
        Label invisible = new Label();
        Button backButton = CustomButton.backButton(stage, new Operator()); // Bottone back
        ListaAree listaAree= App.centro.getAree();
        ListView<String> listView = new ListView<>();
        listView.setOnKeyPressed(event -> handleKeyPress(stage, event, listView));
        listView.setOnMouseClicked(event -> handleClick(stage, event, listView));

        List<String> items = new ArrayList<>();
        for(AreaGeografica ag : listaAree){
            items.add(ag.toString());
        }

        listView.getItems().setAll(items);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            List<String> filteredItems = items.stream()
                    .filter(item -> item.toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            listView.getItems().setAll(filteredItems);
        });


        ParametersTable pt = new ParametersTable();
        TableView<RowParametres> tv = pt.getTableView();
        Button invia = new Button("Invio");
        VBox vb = new VBox(invisible,searchField,listView, tv, invia, backButton);

        invia.setOnAction(event -> {
            if(MisurazioneHandler.area != null) {
                MisurazioneHandler.insertMisurazione(pt.getTableParameter());
                MisurazioneHandler.area = null;
                SceneHandler.sceneChanger(stage, this);
            } else {
                invisible.setText("SELEZIONARE UN'AREA GEOGRAFICA");
            }
        });

        SceneHandler.sceneSetter(stage, vb);


    }

    private void handleKeyPress(Stage stage, KeyEvent event, ListView<String> listView){
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    long geoid = RegisterHandler.getGeoIDFromString(selectedItem);
                    MisurazioneHandler.area = App.server.getAreaGeografica(geoid).get();
                } catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }

    private void handleClick(Stage stage, MouseEvent event, ListView<String> listView) {
        if (event.getClickCount() == 2) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    long geoid = RegisterHandler.getGeoIDFromString(selectedItem);
                    MisurazioneHandler.area = App.server.getAreaGeografica(geoid).get();
                } catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }

}
