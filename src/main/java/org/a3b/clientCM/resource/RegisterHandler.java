package org.a3b.clientCM.resource;

import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.Operatore;

public class RegisterHandler {
    public static Operatore tmpOperatore = new Operatore();
    public static CentroMonitoraggio tmpCentro = new CentroMonitoraggio();
    public static String tmpPassword = "";

    public static void setTmpOperator(String[] attributi){

        tmpOperatore.setNome(attributi[0]);
        tmpOperatore.setCognome(attributi[1]);
        tmpOperatore.setCf(attributi[2]);
        tmpOperatore.setEmail(attributi[3]);

        tmpPassword = attributi[4];

    }

    public static void setTmpCentro(String[] attributi){
        tmpCentro.setNome(attributi[0]);
        tmpCentro.setNomeVia(attributi[1]);
        tmpCentro.setCivico(Integer.parseInt(attributi[2]));
        tmpCentro.setCap(Integer.parseInt(attributi[3]));
        tmpCentro.setComune(attributi[4]);
        tmpCentro.setProvincia(attributi[5]);
    }

    public static long getGeoIDFromString(String input){
        int spaceIndex = input.indexOf(' ');

        return  Long.parseLong(input.substring(1, spaceIndex-1));
    }

}
