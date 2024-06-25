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
        listView.setOnKeyPressed(event -> handleKeyPress(event, listView));
        listView.setOnMouseClicked(event -> handleClick(event, listView));


        // Lista di esempio (puoi sostituirla con dati reali)
        List<String> items = new ArrayList<>();
        for(AreaGeografica ag : list){
            items.add(ag.toString());
        }

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
        Handler.sceneSetter(stage, root);
    }

    private void handleKeyPress(KeyEvent event, ListView<String> listView) {
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                System.out.println("Elemento selezionato: " + selectedItem);
            }
        }
    }

    private void handleClick(MouseEvent event, ListView<String> listView) {
        if (event.getClickCount() == 2) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                System.out.println("Elemento selezionato con doppio clic: " + selectedItem);
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