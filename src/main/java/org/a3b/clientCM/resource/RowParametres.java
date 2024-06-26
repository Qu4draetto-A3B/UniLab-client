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


public class RowParametres {

    private final ScoreCell SC = new ScoreCell();
    private final NoteCell NC = new NoteCell();

    private String parametro;
    private HBox circleValues;
    private HBox note;

    public RowParametres(String str) {
        parametro = str;
        circleValues = SC.getScoreCell();
        note = NC.getNoteCell();
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String str) {
        parametro = str;
    }

    public HBox getCircleValues() {
        return circleValues;
    }

    public void setCircleValues(HBox hb) {
        circleValues = hb;
    }

    public HBox getNote() {
        return note;
    }

    public void setNote(HBox hb) {
        note = hb;
    }

    /*--------------------------------------
    ----------------------------------------
    ---METODO PROVVISORIO-------------------
    ---PER PROVARE OUTPUT TABELLA----------
     */
    public String toString() {
        return parametro + " " + SC.getScore() + " " + NC.getText();
    }
}