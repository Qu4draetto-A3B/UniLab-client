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
import org.a3b.clientCM.resource.RegisterHandler;
import org.a3b.clientCM.resource.SceneHandler;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Operatore;
import org.a3b.commons.result.Result;

import java.rmi.RemoteException;
import java.util.ArrayList;
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

            CentroMonitoraggio cm = null;
            try {
                RegisterHandler.tmpCentro.setAree(listaAree);
                cm = App.server.registraCentroAree(RegisterHandler.tmpCentro).get();
                cm = App.server.alterListaAree(cm,listaAree).get();

                RegisterHandler.tmpOperatore.setCentro(cm);
                App.operatore = App.server.registrazione(RegisterHandler.tmpOperatore, RegisterHandler.tmpPassword).get();
                App.centro = cm;
                System.out.println(App.operatore.getUid());

                SceneHandler.sceneChanger(stage, new Operator());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch(Exception e){
                System.out.println();
            }


        });
        // Layout principale
        VBox root = new VBox(10, searchField, listView, vb,conferma);
        root.setPadding(new Insets(10));

        // Creazione della scena
        SceneHandler.sceneSetter(stage, root);
    }

    private void handleKeyPress(KeyEvent event, ListView<String> listView, ListaAree la) throws RemoteException {
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                long geoid = RegisterHandler.getGeoIDFromString(selectedItem);
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
                long geoid = RegisterHandler.getGeoIDFromString(selectedItem);
                AreaGeografica ag = App.server.getAreaGeografica(geoid).get();
                System.out.println(ag.toString());
                la.add(ag);
            }
        }
    }
}
