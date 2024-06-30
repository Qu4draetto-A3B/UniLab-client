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
package org.a3b.clientCM.resource;

import javafx.stage.Stage;
import org.a3b.clientCM.App;
import org.a3b.clientCM.NewMonitoringCenter;
import org.a3b.clientCM.Operator;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;
import org.a3b.commons.magazzeno.Operatore;

import java.rmi.RemoteException;

public class RegisterHandler {
    public static Operatore tmpOperatore = new Operatore();
    public static CentroMonitoraggio tmpCentro = new CentroMonitoraggio();
    public static ListaAree tmpLista= new ListaAree();
    public static String tmpPassword = "";


    public static void setTmpOperator(String[] attributi){

        tmpOperatore.setNome(attributi[0]);
        tmpOperatore.setCognome(attributi[1]);
        tmpOperatore.setCf(attributi[2]);
        tmpOperatore.setEmail(attributi[3]);

        tmpPassword = attributi[4];

    }
    public static void newRegister(){
        tmpLista = new ListaAree();
        tmpOperatore = new Operatore();
        tmpCentro = new CentroMonitoraggio();
        tmpPassword = "";
    }
    public static boolean newCenter(){
        try{
            if(!tmpLista.isEmpty()) {
                tmpCentro.setAree(tmpLista);
                tmpCentro = App.server.registraCentroAree(tmpCentro).get();
                App.server.alterListaAree(tmpCentro, tmpLista).get();
                tmpLista = new ListaAree();
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }
    public static boolean newOperator(){
        try{
                if(newCenter()) {
                    if (App.operatore == null) {
                        RegisterHandler.tmpOperatore.setCentro(tmpCentro);
                        App.operatore = App.server.registrazione(tmpOperatore, tmpPassword).get();
                        App.centro = tmpCentro;
                        System.out.println(App.operatore.getUid());
                    }
                } else {
                    return false;
                }
        } catch(Exception e){
            return false;
        }
        newRegister();
        return true;
    }

    public static boolean newOperatorExist(){
        try{

            RegisterHandler.tmpOperatore.setCentro(tmpCentro);
            App.operatore = App.server.registrazione(tmpOperatore, tmpPassword).get();
            App.centro = tmpCentro;
            System.out.println(App.operatore.getUid());

        } catch(Exception e){
            return false;
        }
        newRegister();
        return true;
    }

    public static boolean setTmpCentro(String[] attributi){
        try{
            if(Controller.validCenter(attributi)) {
                tmpCentro.setNome(attributi[0]);
                tmpCentro.setNomeVia(attributi[1]);
                tmpCentro.setCivico(Integer.parseInt(attributi[2]));
                tmpCentro.setCap(Integer.parseInt(attributi[3]));
                tmpCentro.setComune(attributi[4]);
                tmpCentro.setProvincia(attributi[5]);
            } else { return false;}
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public static long getGeoIDFromString(String input){
        int spaceIndex = input.indexOf(' ');

        return  Long.parseLong(input.substring(1, spaceIndex-1));
    }

}
