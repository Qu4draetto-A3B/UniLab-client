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


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.a3b.clientCM.App;
import org.a3b.commons.magazzeno.Misurazione;
import org.a3b.commons.utils.TipoDatoGeografico;

import java.util.HashMap;

public class ParametersTable {
    private final static String[] PARAMETRI = {TipoDatoGeografico.AltitudineGhiacciai.name(), TipoDatoGeografico.MassaGhiacciai.name(),
            TipoDatoGeografico.Precipitazioni.name(), TipoDatoGeografico.Pressione.name(), TipoDatoGeografico.Temperatura.name(),
            TipoDatoGeografico.Umidita.name(), TipoDatoGeografico.Vento.name()};

    // Creazione della TableView
    TableView<RowParametres> tableView = new TableView<>();
    private ObservableList<RowParametres> data;

    public ParametersTable() {

        TableColumn<RowParametres, String> parametroCol = new TableColumn<>("PARAMETRO");
        TableColumn<RowParametres, HBox> circleValueCol = new TableColumn<>("VALORI");
        TableColumn<RowParametres, HBox> noteCol = new TableColumn<>("NOTE");


        parametroCol.setCellValueFactory(new PropertyValueFactory<>("parametro"));
        circleValueCol.setCellValueFactory(new PropertyValueFactory<>("circleValues"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));

        tableView.getColumns().add(parametroCol);
        tableView.getColumns().add(circleValueCol);
        tableView.getColumns().add(noteCol);

        tableView.setPrefWidth(800);
        tableView.setMaxHeight(300);
        tableView.setMinHeight(250);


        setTable();
    }

    private void setTable() {
        data = FXCollections.observableArrayList();
        for (String str : PARAMETRI) {
            data.add(new RowParametres(str));
        }

        tableView.setItems(data);
    }

    public TableView<RowParametres> getTableView() {
        return tableView;
    }

    public Misurazione getTableParameter() {
        String[] note = noteToArray();
        Byte[] valori = scoreToArray();

        HashMap<TipoDatoGeografico, Byte>  tmp1 = Misurazione.buildDati(valori[0],valori[1],valori[2],valori[3],valori[4],valori[5],valori[6]);
        HashMap<TipoDatoGeografico, String>  tmp2 = Misurazione.buildNote(note[0],note[1],note[2],note[3],note[4],note[5],note[6]);

        return new Misurazione(0, App.operatore, null,tmp1,tmp2);
    }

    private String[] noteToArray(){
        String[] tmpArray = new String[data.size()];
        int i = 0;
        for (RowParametres mis : data) {
            tmpArray[i++] = mis.getText();
        }

        return tmpArray;
    }

    private Byte[] scoreToArray(){
        Byte[] tmpArray = new Byte[data.size()];
        int i = 0;
        for (RowParametres mis : data) {
            tmpArray[i++] = mis.getScore();
        }

        return tmpArray;
    }


}
