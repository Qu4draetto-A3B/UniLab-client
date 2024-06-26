package org.a3b.clientCM;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.commons.magazzeno.Operatore;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //SCHERMATA DI REGISTRAZIONE CON TUTTI GLI ATTRIBUTI
        //creazione dei bottoni e TextField
        TextField name = new TextField();
        TextField surname = new TextField();
        TextField CF = new TextField();
        TextField email = new TextField();
        TextField password = new TextField();

        //bottone reg
        Button backButton = CustomButton.backButton(stage, new Home());
        Label userLabel = new Label();
        userLabel.setPrefSize(0,0);
        Button reg = new Button("AVANTI");
        reg.setOnAction(event -> {
            String[] str = {name.getText(), surname.getText(),CF.getText(), email.getText(), password.getText()};
            Handler.setTmpOperator(str);
            System.out.println(Handler.tmpOperatore.toString());
            Handler.sceneChanger(stage,new CenterReg());
        });




        //oggetto operatore di supporto alla registrazione
        Operatore op = new Operatore();






        //SETTAGGIO bottoni e TextField
        name.setPromptText("INSERISCI NOME");
        surname.setPromptText("INSERISCI COGNOME");
        CF.setPromptText("INSERISCI CODICE FISCALE");
        email.setPromptText("INSERISCI EMAIL");
        password.setPromptText("INSERISCI PASSWORD");

        VBox vb = new VBox();
        vb.getChildren().addAll(name, surname, CF, email, password, userLabel, reg, backButton);
        vb.setAlignment(Pos.CENTER);


        //SCENA
        Handler.sceneSetter(stage, vb);
        userLabel.requestFocus();

    }



}
