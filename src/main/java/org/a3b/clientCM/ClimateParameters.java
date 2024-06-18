package org.a3b.clientCM;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class ClimateParameters extends Application {

    // Classe per rappresentare una riga vuota
    public static class Row {
        private String col1;
        private String col2;
        private String col3;

        public Row(String col1) {
            this.col1 = col1;
            this.col2 = "";
            this.col3 = "";
        }

        // Getter e Setter per tutte le colonne
        public String getCol1() {
            return col1;
        }

        public void setCol1(String col1) {
            this.col1 = col1;
        }

        public String getCol2() {
            return col2;
        }

        public void setCol2(String col2) {
            this.col2 = col2;
        }

        public String getCol3() {
            return col3;
        }

        public void setCol3(String col3) {
            this.col3 = col3;
        }



    }

        @Override
        public void start(Stage stage) {
            // Crea il TableView
            TableView<Row> tableView = new TableView<>();

            // Definisci le colonne e associa alle proprietà della classe Row
            TableColumn<Row, String> col1 = new TableColumn<>("PARAMETRO");
            col1.setCellValueFactory(new PropertyValueFactory<>("col1"));

            TableColumn<Row, String> col2 = new TableColumn<>("PUNTEGGIO");
            col2.setCellValueFactory(new PropertyValueFactory<>("col2"));

            TableColumn<Row, String> col3 = new TableColumn<>("NOTE");
            col3.setCellValueFactory(new PropertyValueFactory<>("col3"));



            // Aggiungi le colonne al TableView
            tableView.getColumns().addAll(col1, col2, col3);

            // Crea 4 righe vuote
            ObservableList<Row> emptyData = FXCollections.observableArrayList(
                    new Row("Vento"), new Row("Umidità"), new Row("Pressione"),
                    new Row("Temperatura"), new Row("Precipitazioni"), new Row("Altitudine dei ghiacciai"),
                    new Row("Massa dei ghiacciai")
            );

            // Imposta le righe vuote nella tabella
            tableView.setItems(emptyData);

            // Aggiungi il TableView al layout
            VBox vb = new VBox(tableView);

            // Crea la scena e mostra la finestra
            //SCENA
            Scene scene = new Scene(vb, 1000, 1000, Color.WHITE); //scena
            scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
            stage.setScene(scene); //setta scena
            stage.setTitle("Climate Monitoring");
        }

    }




