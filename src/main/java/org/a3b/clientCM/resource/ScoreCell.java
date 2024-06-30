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

public class ScoreCell {

    private static final String[] INDEX = {"0", "1", "2", "3", "4", "5"};
    private int score = 0;
    private final HBox circlesContainer;

    public ScoreCell() {
        circlesContainer = new HBox(10); // Valore della spaziatura tra i cerchi
        // Valore della spaziatura tra gli elementi dle HBox
        circlesContainer.setAlignment(Pos.CENTER);
        for (int i = 0; i < 6; i++) {
            circlesContainer.getChildren().add(createBall(INDEX[i], i));
        }
    }

    private StackPane createBall(String text, int index) {
        Circle ball = new Circle(10);
        ball.setFill(Color.web("#FDFFFE"));
        ball.setStroke(Color.BLACK);
        ball.setStrokeWidth(2);

        Label label = new Label(text);
        label.setTextFill(Color.BLACK);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(ball, label);
        stack.setOnMouseClicked(event -> handleClick(stack, index));

        return stack;
    }

    private void handleClick(StackPane stack, int index) {


        // Cambia il colore del cerchio selezionato
        if (score != -1) {
            StackPane previousBall = (StackPane) circlesContainer.getChildren().get(score);
            Circle previousCircle = (Circle) previousBall.getChildren().get(0);
            previousCircle.setFill(Color.web("#FDFFFE"));
        }

        Circle currentCircle = (Circle) stack.getChildren().get(0);
        currentCircle.setFill(Color.web("#01BFBF"));
        score = index;
        System.out.println(index);
    }

    public int getScore() {
        return score;
    }

    public HBox getScoreCell() {
        return circlesContainer;
    }


}