package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;

public class Home extends Application {
    //SCHERMATA INIZIALE CON LOGO + REGISTRAZIONE + LOGIN
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

        Button search=new Button("VISUALIZZA AREA");
        Button reg = new Button("REGISTRAZIONE");
        Button login = new Button("LOGIN");

        //SETTAGGIO bottoni e TextField

        search.setOnAction(event -> {
            try {
                changeInSearch(stage);
            } catch (Exception e) {
                System.out.println("errore: bottone login evento");
            }
        });
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





        //VBOX
        VBox vb = new VBox();
        vb.getChildren().add(imageViewLogo);
        vb.getChildren().addAll(search,reg,login);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Scene scene = new Scene(vb, 800, 400, Color.WHITE); //scena
        scene.getRoot().setStyle("-fx-background-color: #FDFFFE");
        stage.setScene(scene); //setta scena
        stage.setTitle("Climate Monitoring");

        stage.show(); //mostra scena
    }

    private void changeInLogin(Stage stage) throws Exception{
        new Login().start(stage);
    }

    private void changeInOperator(Stage stage) throws  Exception {
        new Register().start(stage);
    }

    private void changeInSearch(Stage stage) throws  Exception{
        new SearchArea().start(stage);
    }
}
