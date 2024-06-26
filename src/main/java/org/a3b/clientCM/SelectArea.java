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
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Operatore;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class SelectArea extends Application {
    public void start(Stage stage) throws RemoteException {

        Pane vb = new Pane();
        TextField searchField = new TextField();
        searchField.setPromptText("Cerca...");
        ListaAree listaAree = new ListaAree();
        Button conferma = new Button("Conferma");
        ListaAree list = App.server.getAreeGeografiche().get();
        ListView<String> listView = new ListView<>();
        listView.setOnKeyPressed(event -> {
            try {
                handleKeyPress(event, listView, listaAree);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        listView.setOnMouseClicked(event -> {
            try {
                handleClick(event, listView, listaAree);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

        List<String> items = new ArrayList<>();
        for (AreaGeografica ag : list) {
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
        conferma.setOnAction(event -> {
            Handler.tmpCentro.setAree(listaAree);
            Handler.tmpCentro.setCenterID(1);
            Handler.tmpOperatore.setCentro(Handler.tmpCentro);
            Handler.tmpOperatore.setUid(1);
            CentroMonitoraggio cm;
            try {
                cm = App.server.registraCentroAree(Handler.tmpCentro).get();

            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch(Exception e){
                System.out.println("SONO QUI 1");
            }

            try {
                Operatore op = App.server.registrazione(Handler.tmpOperatore,Handler.tmpPassword).get();
                System.out.println(op.getUid());

                Handler.sceneChanger(stage, new Home());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch(Exception e){
                System.out.println("SONO QUI 2");
            }

        });
        // Layout principale
        VBox root = new VBox(10, searchField, listView, vb,conferma);
        root.setPadding(new Insets(10));

        // Creazione della scena
        Handler.sceneSetter(stage, root);
    }

    private void handleKeyPress(KeyEvent event, ListView<String> listView, ListaAree la) throws RemoteException {
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                long geoid = Handler.getGeoIDFromString(selectedItem);
                AreaGeografica ag = App.server.getAreaGeografica(geoid).get();
                System.out.println(ag.toString());
                la.add(ag);
            }
        }
    }

    private void handleClick(MouseEvent event, ListView<String> listView, ListaAree la) throws RemoteException {
        if (event.getClickCount() == 2) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                long geoid = Handler.getGeoIDFromString(selectedItem);
                AreaGeografica ag = App.server.getAreaGeografica(geoid).get();
                System.out.println(ag.toString());
                la.add(ag);
            }
        }
    }
}
