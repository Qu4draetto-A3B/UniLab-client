package org.a3b.clientCM.resource;

import org.a3b.clientCM.App;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.Misurazione;

public class MisurazioneHandler {
    public static AreaGeografica area;
    public static boolean insertMisurazione(Misurazione misurazione) {
        misurazione.setArea(area);
        try{
            Misurazione mis = App.server.inserisciParametriClimatici(misurazione).get();
            System.out.println(misurazione);
        } catch(Exception e){
            System.out.println("NOOOOOO");
            return false;

        }

        return true;
    }

    public static String getMedia(){
        String s = "Nessuna misurazione relativa all'area : " + "["+area.getGeoID()+"]"+ area.getDenominazione();
        Misurazione mis = null;
        try{
            mis = App.server.visualizzaAreaGeografica(area.getGeoID()).get();
            return mis.toString();
        } catch(Exception e){
            s = "QUALCOSA E' ANDATO STORTO\n" + s;
        }

        return s;
    }
}


