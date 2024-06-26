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

public class NoteCell {
    private String text = "";
    private final TextField textField;
    private final Label invisibleLabel = new Label();
    private HBox noteContainer;


    public NoteCell() {
        textField = new TextField(text);
        setHandler();
        setHB();
    }

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

        textField.setOnAction(event -> {
            invisibleLabel.requestFocus();
        });
    }

    private void setHB() {
        noteContainer = new HBox();
        invisibleLabel.setPrefSize(0, 0);
        invisibleLabel.setStyle("-fx-background-color: transparent;");
        noteContainer.getChildren().addAll(textField, invisibleLabel);
        noteContainer.setAlignment(Pos.CENTER);
    }

    public HBox getNoteCell() {
        return noteContainer;
    }

    public String getText() {
        return text;
    }


}
