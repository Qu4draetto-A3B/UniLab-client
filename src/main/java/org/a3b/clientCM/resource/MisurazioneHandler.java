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
package org.a3b.clientCM.resource;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.a3b.clientCM.App;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.ListaMisurazioni;
import org.a3b.commons.magazzeno.Misurazione;
import org.a3b.commons.utils.TipoDatoGeografico;

import java.util.ArrayList;
import java.util.List;
/**
 * Questa classe gestisce operazioni relative alle misurazioni climatiche per un'area geografica specifica.
 */
public class MisurazioneHandler {

    /**
     * L'area geografica associata alle misurazioni.
     */
    public static AreaGeografica area = null;

    /**
     * ListView utilizzata per visualizzare le note delle misurazioni.
     */
    public static ListView<String> listView = new ListView<>();

    /**
     * Inserisce una nuova misurazione climatica associata all'area geografica corrente.
     *
     * @param misurazione La misurazione climatica da inserire.
     * @return true se l'inserimento è avvenuto con successo, false altrimenti.
     */
    public static boolean insertMisurazione(Misurazione misurazione) {
        misurazione.setArea(area);
        try {
            Misurazione mis = App.server.inserisciParametriClimatici(misurazione).get();
            System.out.println(misurazione);
        } catch (Exception e) {
            System.out.println("Errore durante l'inserimento della misurazione: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Ottiene una visualizzazione delle medie dei parametri climatici per l'area geografica corrente.
     *
     * @return Un oggetto Pane contenente la visualizzazione delle medie dei parametri climatici.
     */
    public static Pane getMedia() {
        String s = "Nessuna misurazione relativa all'area : " + "[" + area.getGeoID() + "]" + area.getDenominazione();
        int[] tmpArrayValue = {0, 0, 0, 0, 0, 0, 0};

        List<String>[] arrayOfLists = new ArrayList[7];
        for (int i = 0; i < arrayOfLists.length; i++) {
            arrayOfLists[i] = new ArrayList<>();
        }

        try {
            ListaMisurazioni lm = App.server.getListaMisurazioni(-1, -1, -1, area.getGeoID(), null, null).get();
            for (Misurazione mis : lm) {
                int i = 0;
                for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
                    tmpArrayValue[i] = tmpArrayValue[i] + mis.getDato(tipo);
                    arrayOfLists[i].add(mis.getNota(tipo));
                    i++;
                }
            }
            return visualizza(tmpArrayValue, arrayOfLists, lm.size());
        } catch (Exception e) {
            System.out.println("Errore durante il recupero delle misurazioni: " + e.getMessage());
        }
        return null;
    }

    /**
     * Crea e restituisce un grafico a barre rappresentante i valori medi dei parametri climatici.
     *
     * @param values Gli array dei valori medi dei parametri.
     * @param note   Gli array delle note associate ai parametri.
     * @param count  Il numero totale di misurazioni.
     * @return Un oggetto Pane contenente il grafico a barre e la ListView delle note.
     */
    public static Pane visualizza(int[] values, List<String>[] note, int count) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("");
        yAxis.setLabel("Valore");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(area.getDenominazione());

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("PARAMETRI");
        barChart.setLegendVisible(false);
        String colorStyle = "-fx-bar-fill: " + Color.web("#01BFBF").toString().replace("0x", "#") + ";";
        int i = 0;
        for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
            List<String> tmp = note[i];
            XYChart.Data<String, Number> data = new XYChart.Data<>(tipo.name(), (float) values[i] / (float) count);
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                newNode.setStyle(colorStyle);
                newNode.setOnMouseClicked(event -> {
                    System.out.println("Hai cliccato su " + tipo.name());
                    listNote(tmp);
                });
            });
            dataSeries.getData().add(data);
            i++;
        }

        barChart.getData().add(dataSeries);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(barChart, listView);

        return hbox;
    }

    /**
     * Aggiorna la ListView delle note con una nuova lista di note.
     *
     * @param tmp La lista di note da visualizzare nella ListView.
     */
    public static void listNote(List<String> tmp) {
        listView.getItems().clear();
        for (String str : tmp) {
            if (str.length() > 0) listView.getItems().add(str);
        }
    }
}


