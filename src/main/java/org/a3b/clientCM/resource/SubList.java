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
/**
 * Questa classe rappresenta una sottolista di aree geografiche associate a un centro di monitoraggio.
 * Fornisce metodi per visualizzare le aree geografiche e ottenere informazioni sul centro di monitoraggio stesso.
 */
public class SubList {
    private CentroMonitoraggio monitor; // Centro di monitoraggio associato alla sottolista
    private ListaAree area; // Lista delle aree geografiche associate al centro

    /**
     * Costruttore della classe SubList.
     *
     * @param cm Il centro di monitoraggio per il quale creare la sottolista di aree.
     */
    public SubList(CentroMonitoraggio cm) {
        monitor = cm;
    }

    /**
     * Restituisce una ListView contenente la sottolista delle aree geografiche associate al centro di monitoraggio.
     *
     * @return Una ListView di stringhe rappresentante le aree geografiche.
     */
    private ListView<String> viewSubList() {
        ListView<String> subList = new ListView<>();
        ListaAree lista = monitor.getAree();
        for (AreaGeografica ag : lista) {
            subList.getItems().add(ag.toString());
        }
        return subList;
    }

    /**
     * Restituisce una stringa che rappresenta le informazioni principali del centro di monitoraggio.
     *
     * @return Una stringa con il nome, l'indirizzo, il comune e la provincia del centro di monitoraggio.
     */
    public String toString() {
        return "Nome : " + monitor.getNome() + "\nIndirizzo : " + monitor.getNomeVia() + monitor.getCivico() +
                "\nComune : " + monitor.getComune() + "[" + monitor.getProvincia() + "]";
    }

    /**
     * Restituisce l'oggetto CentroMonitoraggio associato alla sottolista.
     *
     * @return L'oggetto CentroMonitoraggio associato alla sottolista.
     */
    public CentroMonitoraggio getCentro() {
        return monitor;
    }
}
