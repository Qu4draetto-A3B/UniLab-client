package org.a3b.clientCM;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.NoteCell;
import org.a3b.clientCM.resource.RowParametres;
import org.a3b.clientCM.resource.ScoreCell;


public class ProvaTable extends Application {
    public void start(Stage stage) throws InterruptedException {
        ParametersTable pt = new ParametersTable();
       TableView<RowParametres> tv = pt.getTableView();
        Button b = new Button("Invio");
        VBox vb = new VBox(tv,b);
        b.setOnAction(event ->{
           String str = pt.getTableParameter();
           System.out.println(str);

        });
        Scene scene = new Scene(vb, 300, 250);

        stage.setScene(scene);
        stage.show();




    }
    public static void main(String[] args) {
        launch();
    }
}



