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

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
/**
 * Questa classe rappresenta una cella per l'input di note, utilizzata per gestire testo in un TextField all'interno di un HBox.
 */
public class NoteCell {
    private String text = ""; // Testo attuale della cella
    private final TextField textField; // Campo di testo per l'input delle note
    private final Label invisibleLabel = new Label(); // Etichetta invisibile usata per la gestione del focus
    private HBox noteContainer; // Contenitore HBox che racchiude il campo di testo e l'etichetta invisibile

    /**
     * Costruisce una nuova istanza di NoteCell, inizializzando il TextField e impostando i gestori di eventi.
     */
    public NoteCell() {
        textField = new TextField(text);
        setHandler();
        setHB();
    }

    // Imposta i gestori di eventi per il TextField
    private void setHandler() {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Il TextField ha guadagnato il focus
            } else {
                // Il TextField ha perso il focus
                text = textField.getText();
                textField.setText(text);
            }
        });

        // Gestisce l'evento di pressione del tasto Invio nel TextField
        textField.setOnAction(event -> {
            invisibleLabel.requestFocus();
        });
    }

    // Configura il contenitore HBox che include il TextField e l'etichetta invisibile
    private void setHB() {
        noteContainer = new HBox();
        invisibleLabel.setPrefSize(0, 0); // Imposta le dimensioni dell'etichetta invisibile
        invisibleLabel.setStyle("-fx-background-color: transparent;"); // Stile per rendere l'etichetta invisibile
        noteContainer.getChildren().addAll(textField, invisibleLabel); // Aggiunge i nodi al contenitore HBox
        noteContainer.setAlignment(Pos.CENTER); // Allinea i nodi al centro del contenitore
    }

    /**
     * Restituisce il contenitore HBox che rappresenta la cella delle note.
     *
     * @return Il contenitore HBox che contiene il TextField e l'etichetta invisibile.
     */
    public HBox getNoteCell() {
        return noteContainer;
    }

    /**
     * Restituisce il testo attualmente presente nel TextField della cella delle note.
     *
     * @return Il testo attualmente presente nel TextField.
     */
    public String getText() {
        return text;
    }
}
