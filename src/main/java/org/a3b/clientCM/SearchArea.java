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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.*;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.ListaAree;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchArea extends Application {

    //SCHERMATA PER CERCARE E VISUALIZZARE AREA GEOGRAFICA
    @Override
    public void start(Stage stage) throws RemoteException {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        Pane vb = setButton(stage);


        // Creazione del campo di testo per la barra di ricerca
        TextField searchField = new TextField();
        searchField.setPromptText("Cerca...");


        // Creazione della ListView per visualizzare i risultati

        ListaAree list = App.server.getAreeGeografiche().get();
        ListView<String> listView = new ListView<>();

        listView.setOnKeyPressed(event -> handleKeyPress(stage, event, listView));
        listView.setOnMouseClicked(event -> handleClick(stage, event, listView));


        // Lista di esempio (puoi sostituirla con dati reali)
        List<String> items = new ArrayList<>();
        for(AreaGeografica ag : list){
            items.add(ag.toString());
        }

        listView.getItems().addAll(items);

        // Aggiungi un listener alla proprietà textProperty del TextField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            List<String> filteredItems = items.stream()
                    .filter(item -> item.toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            listView.getItems().setAll(filteredItems);
        });

        // Layout principale
        VBox root = new VBox(10, searchField, listView, vb);
        root.setPadding(new Insets(10));

        // Creazione della scena
        SceneHandler.sceneSetter(stage, root);
    }

    private void handleKeyPress(Stage stage,KeyEvent event, ListView<String> listView){
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    long geoid = RegisterHandler.getGeoIDFromString(selectedItem);
                    MisurazioneHandler.area = App.server.getAreaGeografica(geoid).get();
                    System.out.println(MisurazioneHandler.area.toString());
                    SceneHandler.sceneChanger(stage, new VisualArea());
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
                    System.out.println(MisurazioneHandler.area.toString());
                    SceneHandler.sceneChanger(stage, new VisualArea());

                } catch(Exception e){
                    System.err.println(e);
                }
            }
        }
    }

    private Pane setButton(Stage stage) {
        VBox vb = new VBox();
        Button backButton;
        if (App.operatore != null) {
            backButton = CustomButton.backButton(stage, new Operator());
        } else {
            backButton = CustomButton.backButton(stage, new Home());
        }
        vb.getChildren().addAll(backButton);

        return vb;
    }


}