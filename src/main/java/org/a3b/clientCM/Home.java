package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class Home extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();
        //IMMAGINE:
        InputStream in = getClass().getResourceAsStream("/img/logoclimatemonitoring.png");
        Image logo = new Image(in); // Assicurati che il percorso sia corretto
        ImageView imageViewLogo = new ImageView(logo);
        double side = 200;
        imageViewLogo.setFitWidth(side); //larghezza
        imageViewLogo.setFitHeight(side); //altezza


        //CREAZIONE GRIDPANEL
        GridPane gp = new GridPane();

        //creazione dei bottoni e TextField
        TextField textField = new TextField();
        Button search=new Button("CERCA");
        Button reg = new Button("REGISTRAZIONE");
        Button login = new Button("LOGIN");

        //SETTAGGIO bottoni e TextField
        textField.setPromptText("INSERISCI L'AREA DI INTERESSE");
        search.setOnAction(event -> System.out.println("CERCA : "+ textField.getText()) );
        reg.setOnAction(event -> {
            try {
                changeInOperator(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone reg evento");
            }
        });

        login.setOnAction(event -> {
            try {
                changeInLogin(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone login evento");
            }
        });


        //GP
        gp.add(textField, 0,0);
        gp.add(search, 1,0);
        gp.add(reg, 0,1);
        gp.add(login, 1,1);
        gp.setHalignment(textField, HPos.CENTER);
        gp.setHalignment(search, HPos.CENTER);
        gp.setHalignment(reg, HPos.CENTER);
        gp.setHalignment(login, HPos.CENTER);
        gp.setAlignment(Pos.CENTER);

        // STACKPANE per Image
        VBox vb = new VBox();
        vb.getChildren().add(imageViewLogo);
        vb.getChildren().add(gp);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Scene scene = new Scene(vb, 800, 400, Color.WHITE); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.show(); //mostra scena
    }

    private void changeInLogin(Stage stage) throws Exception{
        new Login().start(stage);
    }

    private void changeInOperator(Stage stage) throws Exception {
        new Operator().start(stage);
    }
}
