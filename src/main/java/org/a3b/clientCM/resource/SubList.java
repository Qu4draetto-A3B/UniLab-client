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

import javafx.scene.control.ListView;
import org.a3b.commons.magazzeno.AreaGeografica;
import org.a3b.commons.magazzeno.CentroMonitoraggio;
import org.a3b.commons.magazzeno.ListaAree;

public class SubList {
    private CentroMonitoraggio monitor;
    private ListaAree area;
    public SubList(CentroMonitoraggio cm) {
        monitor = cm;
    }

    private ListView<String> viewSubList() {
        ListView<String> subList = new ListView<>();
        ListaAree lista = monitor.getAree();
         for(AreaGeografica ag : lista){
             subList.getItems().add(ag.toString());
         }

         return subList;
    }

    public String toString(){
        return "Nome : "+monitor.getNome() +"\nIndirizzo : "+ monitor.getNomeVia() + monitor.getCivico()+
                "\nComune : "+monitor.getComune() + "["+monitor.getProvincia()+"]";
    }

    public CentroMonitoraggio getCentro(){ return monitor;}
}
