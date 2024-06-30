package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.MisurazioneHandler;
import org.a3b.clientCM.resource.SceneHandler;
import org.a3b.commons.magazzeno.AreaGeografica;


public class VisualArea extends Application {


    public void start(Stage stage){

        Pane bar = MisurazioneHandler.getMedia();
        Button back = CustomButton.backButton(stage,new SearchArea());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(bar,back);
        SceneHandler.sceneSetter(stage,vbox);
    }


}
