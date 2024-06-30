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

import javafx.scene.layout.HBox;

/**
 * Questa classe rappresenta una riga dei parametri climatici nella tabella dei parametri.
 * Ogni riga contiene un parametro climatico, il valore del parametro e le note associate.
 */
public class RowParametres {

    private final ScoreCell SC = new ScoreCell(); // Cella per il valore del parametro
    private final NoteCell NC = new NoteCell();   // Cella per le note associate

    private String parametro;  // Nome del parametro climatico
    private HBox circleValues; // Contenitore per il valore del parametro
    private HBox note;         // Contenitore per le note associate

    /**
     * Costruttore della classe RowParametres.
     *
     * @param str Il nome del parametro climatico per questa riga.
     */
    public RowParametres(String str) {
        parametro = str;
        circleValues = SC.getScoreCell();
        note = NC.getNoteCell();
    }

    /**
     * Restituisce il nome del parametro climatico di questa riga.
     *
     * @return Il nome del parametro climatico.
     */
    public String getParametro() {
        return parametro;
    }

    /**
     * Imposta il nome del parametro climatico di questa riga.
     *
     * @param str Il nome del parametro climatico da impostare.
     */
    public void setParametro(String str) {
        parametro = str;
    }

    /**
     * Restituisce il contenitore per il valore del parametro climatico.
     *
     * @return Il contenitore HBox per il valore del parametro.
     */
    public HBox getCircleValues() {
        return circleValues;
    }

    /**
     * Imposta il contenitore per il valore del parametro climatico.
     *
     * @param hb Il contenitore HBox da impostare.
     */
    public void setCircleValues(HBox hb) {
        circleValues = hb;
    }

    /**
     * Restituisce il contenitore per le note associate al parametro climatico.
     *
     * @return Il contenitore HBox per le note associate.
     */
    public HBox getNote() {
        return note;
    }

    /**
     * Imposta il contenitore per le note associate al parametro climatico.
     *
     * @param hb Il contenitore HBox da impostare.
     */
    public void setNote(HBox hb) {
        note = hb;
    }

    /**
     * Restituisce il testo delle note associate al parametro climatico.
     *
     * @return Il testo delle note associate.
     */
    public String getText() {
        return NC.getText();
    }

    /**
     * Restituisce il punteggio del parametro climatico come byte.
     *
     * @return Il punteggio del parametro climatico.
     */
    public Byte getScore() {
        return (byte) SC.getScore();
    }

    /**
     * Metodo provvisorio per ottenere una rappresentazione testuale della riga dei parametri.
     * Questo metodo è utilizzato per il debug o per il testing dell'output della tabella.
     *
     * @return Una stringa che rappresenta il nome del parametro, il suo valore e le note associate.
     */
    public String toString() {
        return parametro + " " + SC.getScore() + " " + NC.getText();
    }
}
