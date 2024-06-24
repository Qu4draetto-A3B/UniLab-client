package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.RowParametres;

public class ClimateParameters extends Application {

    @Override
    public void start(Stage stage) {

        Button backButton = CustomButton.backButton(stage, new Operator()); // Bottone back
        Button homeButton = CustomButton.homeButton(stage); // Bottone home

        // Crea il TableView
        ParametersTable pt = new ParametersTable();
        TableView<RowParametres> tv = pt.getTableView();
        Button b = new Button("Invio");
        VBox vb = new VBox(tv, b, backButton, homeButton);
        b.setOnAction(event -> {
            String str = pt.getTableParameter();
            System.out.println(str);

        });

        Handler.sceneSetter(stage, vb);


    }
}
