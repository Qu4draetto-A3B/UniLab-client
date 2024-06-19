package org.a3b.clientCM;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.InputStream;

public class ClimateParameters extends Application {

    @Override
    public void start(Stage stage) {

        Button backButton = CustomButton.backButton(stage,new Operator()); //bottone back
        Button homeButton = CustomButton.homeButton(stage); //bottone home









        // Crea il TableView
        TableView<Row> tableView = new TableView<>();
        tableView.setEditable(true); // Abilita la modalità di modifica
        Button buttonOK = new Button("CONFERMA");

        // Definisci le colonne e associa alle proprietà della classe Row
        TableColumn<Row, String> col1 = new TableColumn<>("PARAMETRO");
        col1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        col1.setMinWidth(200); // Imposta larghezza minima
        col1.setMaxWidth(200); // Imposta larghezza massima

        TableColumn<Row, String[]> col2 = new TableColumn<>("PUNTEGGIO");
        col2.setCellValueFactory(new PropertyValueFactory<>("circleValues"));
        col2.setCellFactory(new Callback<TableColumn<Row, String[]>, TableCell<Row, String[]>>() {
            @Override
            public TableCell<Row, String[]> call(TableColumn<Row, String[]> param) {
                return new CircleTableCell();
            }
        });
        col2.setMinWidth(300); // Imposta larghezza minima
        col2.setMaxWidth(300); // Imposta larghezza massima

        TableColumn<Row, String> col3 = new TableColumn<>("NOTE");
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        col3.setCellFactory(TextFieldTableCell.forTableColumn()); // permette di scrivere valore
        col3.setOnEditCommit(event -> event.getRowValue().setCol3(event.getNewValue())); // gestisce la modifica del valore
        col3.setMinWidth(200); // Imposta larghezza minima
        col3.setMaxWidth(200); // Imposta larghezza massima

        // Aggiungi le colonne al TableView
        tableView.getColumns().addAll(col1, col2, col3);

        // Crea 7 righe con i nomi dei parametri
        ObservableList<Row> data = FXCollections.observableArrayList(
                new Row("Vento"),
                new Row("Umidità"),
                new Row("Pressione"),
                new Row("Temperatura"),
                new Row("Precipitazioni"),
                new Row("Altitudine dei ghiacciai"),
                new Row("Massa dei ghiacciai")
        );

        // Imposta le righe nella tabella
        tableView.setItems(data);

        // Aggiungi il TableView al layout
        VBox vb = new VBox(10, tableView, buttonOK,backButton,homeButton);
        vb.setAlignment(Pos.CENTER);

        // Crea la scena e mostra la finestra
        Scene scene = new Scene(vb, 1000, 1000, Color.WHITE);
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene);
        stage.setTitle("Climate Monitoring");
        stage.show();
    }

    // Classe per rappresentare una riga
    public static class Row {
        private String col1;
        private String[] circleValues;
        private String col3;
        private int selectedIndex = -1; // Indice del cerchio selezionato

        public Row(String col1) {
            this.col1 = col1;
            this.circleValues = new String[]{"0", "1", "2", "3", "4", "5"}; // Valori iniziali per i cerchi
            this.col3 = "";
        }

        // Getter e Setter per tutte le colonne
        public String getCol1() {
            return col1;
        }

        public void setCol1(String col1) {
            this.col1 = col1;
        }

        public String[] getCircleValues() {
            return circleValues;
        }

        public void setCircleValues(String[] circleValues) {
            this.circleValues = circleValues;
        }

        public String getCol3() {
            return col3;
        }

        public void setCol3(String col3) {
            this.col3 = col3;
        }

        public int getSelectedIndex() {
            return selectedIndex;
        }

        public void setSelectedIndex(int selectedIndex) {
            this.selectedIndex = selectedIndex;
        }
    }

    // Classe per la cella con i cerchi cliccabili
    public class CircleTableCell extends TableCell<Row, String[]> {
        private HBox circlesContainer;

        public CircleTableCell() {
            circlesContainer = new HBox(10); // Spaziatura di 10 tra i cerchi
            circlesContainer.setAlignment(Pos.CENTER);
        }

        @Override
        protected void updateItem(String[] item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                circlesContainer.getChildren().clear(); // Rimuovi i cerchi esistenti

                Row rowData = getTableView().getItems().get(getIndex());

                for (int i = 0; i < item.length; i++) {
                    StackPane ball = createBall(item[i], i, rowData.getSelectedIndex() == i);
                    circlesContainer.getChildren().add(ball); // Aggiungi il cerchio al container
                }

                setGraphic(circlesContainer); // Imposta il contenitore come grafico della cella
            }
        }

        private StackPane createBall(String text, int index, boolean isSelected) {
            Circle ball = new Circle(10);
            ball.setFill(Color.web(isSelected ? "#01BFBF" : "#FDFFFE")); // Colore esadecimale
            ball.setStroke(Color.BLACK);
            ball.setStrokeWidth(2);

            Label label = new Label(text);
            label.setTextFill(Color.BLACK);

            StackPane stack = new StackPane();
            stack.getChildren().addAll(ball, label);
            stack.setOnMouseClicked(event -> handleClick(stack, index));

            return stack;
        }

        private void handleClick(StackPane stack, int index) {
            Row rowData = getTableView().getItems().get(getIndex());
            int previousIndex = rowData.getSelectedIndex();

            // Aggiorna l'indice del cerchio selezionato nella riga
            rowData.setSelectedIndex(index);

            // Cambia il colore del cerchio selezionato
            if (previousIndex != -1) {
                StackPane previousBall = (StackPane) circlesContainer.getChildren().get(previousIndex);
                Circle previousCircle = (Circle) previousBall.getChildren().get(0);
                previousCircle.setFill(Color.web("#FDFFFE"));
            }

            Circle currentCircle = (Circle) stack.getChildren().get(0);
            currentCircle.setFill(Color.web("#01BFBF"));
        }
    }

    private void changeInLogin(Stage stage) throws Exception {

    }
    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
}
