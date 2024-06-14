package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewMonitoringCenter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //BOTTONI E TEXTFIELD
        TextField centerID = new TextField();
        TextField name = new TextField();
        TextField street = new TextField();
        TextField civicnumber = new TextField();
        TextField zipcode = new TextField();
        TextField town = new TextField();
        TextField province = new TextField();
        Button home = new Button();
        Button back = new Button();

        //SET TEXTFIELD
        centerID.setPromptText("INSERISCI CENTERID");
        town.setPromptText("INSERISCI CITTA");
        province.setPromptText("INSERISCI PROVINCIA");
        street.setPromptText("INSERISCI STRADA");
        civicnumber.setPromptText("INSERISCI NUMEROCIVICO");
        zipcode.setPromptText("INSERISCI ZIPCODE");

        //bottone back
        back.setOnAction(event -> {
            try {
                changeInOperatorReg(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //bottone home
        home.setOnAction(event -> {
            try {
                changeInHome(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //VBOX
        VBox vb = new VBox();
        vb.getChildren().addAll(centerID,town,province,street,civicnumber,zipcode,back,home);

        //SCENA
        Scene scene = new Scene(vb, 800, 400); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena


    }
    private void changeInHome(Stage stage) throws Exception {
        new Home().start(stage);
    }
    private void changeInOperatorReg(Stage stage) throws Exception {
        new OperatorReg().start(stage);
    }
}
