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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Questa classe rappresenta una cella interattiva per la selezione del punteggio,
 * utilizzata per visualizzare e gestire una serie di cerchi numerati.
 */
public class ScoreCell {

    private static final String[] INDEX = {"1", "2", "3", "4", "5"};
    private int score = 0; // Punteggio corrente selezionato
    private final HBox circlesContainer; // Contenitore per i cerchi numerati

    /**
     * Costruttore della classe ScoreCell.
     * Inizializza una serie di cerchi numerati interattivi.
     */
    public ScoreCell() {
        circlesContainer = new HBox(10); // Spaziatura tra i cerchi
        circlesContainer.setAlignment(Pos.CENTER); // Allineamento al centro
        for (int i = 0; i < 5; i++) {
            circlesContainer.getChildren().add(createBall(INDEX[i], i));
        }
    }

    private StackPane createBall(String text, int index) {
        Circle ball = new Circle(10); // Creazione del cerchio
        ball.setFill(Color.web("#FDFFFE")); // Colore di riempimento
        ball.setStroke(Color.BLACK); // Colore del bordo
        ball.setStrokeWidth(2); // Spessore del bordo

        Label label = new Label(text); // Etichetta con il testo numerico
        label.setTextFill(Color.BLACK); // Colore del testo

        StackPane stack = new StackPane(); // Contenitore per cerchio e etichetta
        stack.getChildren().addAll(ball, label); // Aggiunta di cerchio ed etichetta al contenitore
        stack.setOnMouseClicked(event -> handleClick(stack, index)); // Gestione del clic sull'intera cella

        return stack;
    }

    private void handleClick(StackPane stack, int index) {
        // Cambia il colore del cerchio selezionato
        if (score != -1) {
            StackPane previousBall = (StackPane) circlesContainer.getChildren().get(score);
            Circle previousCircle = (Circle) previousBall.getChildren().get(0);
            previousCircle.setFill(Color.web("#FDFFFE")); // Ripristina il colore del cerchio precedente
        }

        Circle currentCircle = (Circle) stack.getChildren().get(0);
        currentCircle.setFill(Color.web("#01BFBF")); // Imposta il colore del cerchio corrente
        score = index; // Imposta il punteggio corrente
    }

    /**
     * Restituisce il punteggio corrente selezionato.
     *
     * @return Il punteggio corrente.
     */
    public int getScore() {
        return score;
    }

    /**
     * Restituisce il contenitore HBox che rappresenta la cella del punteggio.
     *
     * @return Il contenitore HBox con i cerchi numerati.
     */
    public HBox getScoreCell() {
        return circlesContainer;
    }

}
