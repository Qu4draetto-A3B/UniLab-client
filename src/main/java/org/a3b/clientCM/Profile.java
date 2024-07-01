package org.a3b.clientCM;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.a3b.clientCM.resource.CustomButton;
import org.a3b.clientCM.resource.SceneHandler;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Operatore;

public class Profile extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Operatore op = App.operatore;
        CentroMonitoraggio ct = App.centro;

        String view = "OPERATORE : " +op.getCognome()+" "+ op.getNome()+
                "\nUSER_ID : "+ op.getUid() + "\n\n" + "CENTRO MONITORAGGIO ASSOGIATO : " +
                ct.getNome() + "\nINDIRIZZO : " +  ct.getNomeVia() + " "+ ct.getCivico() +" "+ ct.getComune() +
                " ["+ct.getProvincia()+"]" + "\nLISTA AREE GEOGRAFICHE : \n";

        ListaAree list = App.centro.getAree();
        view += view + list;

        Label text = new Label(view);

        Button back = CustomButton.backButton(stage,new Operator());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(text,back);

        SceneHandler.sceneSetter(stage,vbox);


    }
}
