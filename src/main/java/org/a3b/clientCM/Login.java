package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.commons.magazzeno.Operatore;

import java.rmi.RemoteException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI LOGIN DOVE INSERIRE USERID E PASSWORD e accedere ai vari centri di monitoraggi o crearne di nuovi
        //creazione dei bottoni e TextField
        //bottoni con immagini
        //bottone con immagine back

        Button backButton = CustomButton.backButton(stage, new Home());


        Button login = new Button("LOGIN");
        TextField userID = new TextField();
        TextField password = new TextField();
        Label userLabel = new Label();


        //bottone back


        //bottone login
        login.setOnAction(event -> {
            try {
                if(isLong(userID.getText())) {
                    long userIdLong = Long.parseLong(userID.getText());
                    if (isOperatore(userIdLong,password.getText())) {
                        changeInOperator(stage);
                    }
                }

                userLabel.setText("Invalid username or password");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        //SETTAGGIO bottoni e TextField
        userID.setPromptText("INSERISCI USERID");
        password.setPromptText("INSERISCI PASSWORD");


        VBox vb = new VBox();
        vb.getChildren().addAll(userID, password, userLabel, login, backButton);
        vb.setAlignment(Pos.CENTER);

        //SCENA
        Handler.sceneSetter(stage, vb);
        login.requestFocus();
    }

    private void changeInOperator(Stage stage) throws Exception {
        new Operator().start(stage);
    }

    public static boolean isLong(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperatore(Long userId,String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        try {
            App.server.login(userId,password).get();
            return true;
        } catch (RemoteException e) {
            return false;
        }
    }

}
