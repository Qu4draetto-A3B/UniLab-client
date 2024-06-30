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
import javafx.scene.layout.Pane;
import org.a3b.clientCM.App;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.ListaMisurazioni;
import org.a3b.commons.magazzeno.Misurazione;
import org.a3b.commons.utils.TipoDatoGeografico;

import java.util.ArrayList;
import java.util.List;

public class MisurazioneHandler {
    public static AreaGeografica area;
    public static boolean insertMisurazione(Misurazione misurazione) {
        misurazione.setArea(area);
        try{
            Misurazione mis = App.server.inserisciParametriClimatici(misurazione).get();
            System.out.println(misurazione);
        } catch(Exception e){
            System.out.println("NOOOOOO");
            return false;

        }

        return true;
    }

    public static Pane getMedia(){
        String s = "Nessuna misurazione relativa all'area : " + "["+area.getGeoID()+"]"+ area.getDenominazione();
        int[] tmpArrayValue = {0,0,0,0,0,0,0};


        List<String>[] arrayOfLists = new ArrayList[7];
        for (int i = 0; i < arrayOfLists.length; i++) {
            arrayOfLists[i] = new ArrayList<>();
        }

        try{
            ListaMisurazioni lm = App.server.getListaMisurazioni(-1,-1,-1, area.getGeoID(), null,null).get();
            for(Misurazione mis : lm){
                int i = 0;
                for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
                    tmpArrayValue[i] = tmpArrayValue[i] + mis.getDato(tipo);
                    arrayOfLists[i].add(mis.getNota(tipo));
                    i++;
                }

            }

            return visualizza(tmpArrayValue,arrayOfLists, lm.size());
        } catch(Exception e){
            System.out.println(e);
        }

        return null;
    }

    public static Pane visualizza(int[] values, List<String>[] note,int count) {
        // Creazione degli assi
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Categoria");
        yAxis.setLabel("Valore");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(area.getDenominazione());

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("PARAMETRI");
        int i = 0;
        for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
            System.out.println(count);
            dataSeries.getData().add(new XYChart.Data<>(tipo.name(), values[i]/count));
            i++;
        }

        barChart.getData().add(dataSeries);
        Pane pane = new Pane();
        pane.getChildren().add(barChart);
        return pane;

    }
}


