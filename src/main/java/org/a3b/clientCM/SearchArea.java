package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchArea extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Creazione del campo di testo per la barra di ricerca
        TextField searchField = new TextField();
        searchField.setPromptText("Cerca...");

        // Creazione della ListView per visualizzare i risultati
        ListView<String> listView = new ListView<>();
        listView.setOnKeyPressed(event -> handleKeyPress(event, listView));
        listView.setOnMouseClicked(event -> handleClick(event, listView));

        // Creazione dello ScrollPane per contenere la ListView


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
        VBox root = new VBox(10, searchField, listView);
        root.setPadding(new Insets(10));

        // Creazione della scena
        Scene scene = new Scene(root, 400, 300);

        // Impostazione della scena sullo Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Barra di Ricerca con Sbarra di Scorrimento in JavaFX");
        primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}