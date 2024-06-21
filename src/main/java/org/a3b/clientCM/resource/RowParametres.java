package org.a3b.clientCM.resource;

import javafx.scene.layout.HBox;


public class RowParametres{

    private final ScoreCell SC = new ScoreCell();
    private final NoteCell NC = new NoteCell();

    private String parametro;
    private HBox circleValues;
    private HBox note;

    public RowParametres(String str){
        parametro = str;
        circleValues = SC.getScoreCell();
        note = NC.getNoteCell();
    }

    public String getParametro(){ return parametro;}
    public HBox getCircleValues(){ return circleValues;}
    public HBox getNote(){ return note;}

    public void setParametro(String str){parametro = str;}
    public void setCircleValues(HBox hb){circleValues = hb;}
    public void setNote(HBox hb){note = hb;}

    /*--------------------------------------
    ----------------------------------------
    ---METODO PROVVISORIO-------------------
    ---PER PROVARE OUTPUT TABELLA----------
     */
    public  String toString(){
        return parametro +" "+ SC.getScore()+" "+NC.getText();
    }
}