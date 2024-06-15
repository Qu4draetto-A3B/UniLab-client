package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchArea extends Application {
    //SCHERMATA PER CERCARE E VISUALIZZARE AREA GEOGRAFICA
    @Override
    public void start(Stage stage) {
        //SCHERMATA DI VISUALIZZAIZONE DEI CENTRI ESISTENTI
        //bottoni con immagini
        //input per immagini
        InputStream inhome = getClass().getResourceAsStream("/img/home.png");
        InputStream inback =  getClass().getResourceAsStream("/img/arrow.png");
        Image back = new Image(inback);
        ImageView backView = new ImageView(back);
        backView.setFitWidth(50);  // Imposta la larghezza desiderata
        backView.setFitHeight(50); // Imposta l'altezza desiderata
        backView.setPreserveRatio(true); // Mantiene le proporzioni originali
        Button backButton = new Button();
        backButton.setGraphic(backView);
        backButton.setStyle("-fx-background-color: transparent;"); // Rende trasparente lo sfondo del bottone




        // Creazione del campo di testo per la barra di ricerca
        TextField searchField = new TextField();
        searchField.setPromptText("Cerca...");

        backButton.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Creazione della ListView per visualizzare i risultati
        ListView<String> listView = new ListView<>();
        listView.setOnKeyPressed(event -> handleKeyPress(event, listView));
        listView.setOnMouseClicked(event -> handleClick(event, listView));


        // Lista di esempio (puoi sostituirla con dati reali)
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add("Elemento " + i);
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
        VBox root = new VBox(10, searchField, listView,backButton);
        root.setPadding(new Insets(10));

        // Creazione della scena
        Scene scene = new Scene(root, 800, 400);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");

        // Impostazione della scena sullo Stage
        stage.setScene(scene);
        stage.show();
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

    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }

}