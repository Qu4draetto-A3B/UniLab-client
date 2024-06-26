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


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.a3b.clientCM.resource.RowParametres;

public class ParametersTable {
    private final static String[] PARAMETRI = {"Vento", "Umidità", "Pressione", "Temperatura", "Precipitazioni", "Altitudine dei ghiacciai", "Massa dei ghiacciai"};

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

    public String getTableParameter() {
        String str = "";
        for (RowParametres mis : data) {
            str = str + mis.toString() + "\n";
        }

        return str;
    }


    // Creazione delle colonne (con i nomi delle proprietà da visualizzare)


}
