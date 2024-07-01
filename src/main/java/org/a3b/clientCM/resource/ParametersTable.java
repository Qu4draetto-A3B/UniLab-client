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
/**
 * Questa classe rappresenta una tabella dei parametri climatici, utilizzata per visualizzare e gestire i dati di vari parametri climatici.
 */
public class ParametersTable {
    // Array statico contenente i nomi dei parametri climatici
    private final static String[] PARAMETRI = {
            TipoDatoGeografico.AltitudineGhiacciai.name(), TipoDatoGeografico.MassaGhiacciai.name(),
            TipoDatoGeografico.Precipitazioni.name(), TipoDatoGeografico.Pressione.name(),
            TipoDatoGeografico.Temperatura.name(), TipoDatoGeografico.Umidita.name(),
            TipoDatoGeografico.Vento.name()
    };

    // TableView per visualizzare i dati dei parametri
    private TableView<RowParametres> tableView = new TableView<>();
    private ObservableList<RowParametres> data;

    /**
     * Costruttore della classe ParametersTable. Configura la struttura della tabella e inizializza i dati dei parametri.
     */
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

    // Metodo privato per inizializzare i dati dei parametri nella tabella
    private void setTable() {
        data = FXCollections.observableArrayList();
        for (String str : PARAMETRI) {
            data.add(new RowParametres(str));
        }
        tableView.setItems(data);
    }

    /**
     * Restituisce la TableView che rappresenta la tabella dei parametri climatici.
     *
     * @return La TableView contenente i dati dei parametri climatici.
     */
    public TableView<RowParametres> getTableView() {
        return tableView;
    }

    /**
     * Ottiene i parametri climatici dalla tabella e li restituisce come oggetto Misurazione.
     *
     * @return Un'istanza di Misurazione contenente i parametri climatici e le note inserite nella tabella.
     */
    public Misurazione getTableParameter() {
        String[] note = noteToArray();
        Byte[] valori = scoreToArray();

        // Costruisce i dati e le note come HashMap per l'oggetto Misurazione
        HashMap<TipoDatoGeografico, Byte> tmp1 = Misurazione.buildDati(valori[0], valori[1], valori[2], valori[3], valori[4], valori[5], valori[6]);
        HashMap<TipoDatoGeografico, String> tmp2 = Misurazione.buildNote(note[0], note[1], note[2], note[3], note[4], note[5], note[6]);

        return new Misurazione(0, App.operatore, null, tmp1, tmp2);
    }

    // Metodo privato per convertire le note dei parametri in un array di stringhe
    private String[] noteToArray() {
        String[] tmpArray = new String[data.size()];
        int i = 0;
        for (RowParametres mis : data) {
            tmpArray[i++] = mis.getText();
        }
        return tmpArray;
    }

    // Metodo privato per convertire i valori dei parametri in un array di byte
    private Byte[] scoreToArray() {
        Byte[] tmpArray = new Byte[data.size()];
        int i = 0;
        for (RowParametres mis : data) {
            tmpArray[i++] = mis.getScore();
        }
        return tmpArray;
    }
}
