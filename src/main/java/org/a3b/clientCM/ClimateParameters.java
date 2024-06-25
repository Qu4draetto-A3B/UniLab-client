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
