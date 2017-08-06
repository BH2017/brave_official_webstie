package com.braver.org.test;

/* ....Show License.... */

//package ensemble.samples.graphics2d.puzzle;

 

import java.util.ArrayList;

import java.util.List;

import javafx.animation.KeyFrame;

import javafx.animation.KeyValue;

import javafx.animation.Timeline;

import javafx.application.Application;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.effect.DropShadow;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;

import javafx.scene.shape.*;

import javafx.stage.Stage;

import javafx.util.Duration;

 

/**

 * A sample in which an image is broken into pieces to create a jigsaw puzzle.

 */

public class PuzzlePiecesApp extends Application {

 

    private Timeline timeline;

 

    public Parent createContent() {

        // load puzzle image

        Image image = new Image(PuzzlePiecesApp.class.getResourceAsStream(
//
        		"/lyf_0.jpg"));
//                "/ensemble/samples/shared-resources/PuzzlePieces-picture.jpg"));
//        Image image = new Image(null);
        
        int numOfColumns = (int) (image.getWidth() / Piece.SIZE);

        int numOfRows = (int) (image.getHeight() / Piece.SIZE);

        // create desk

        final Desk desk = new Desk(numOfColumns, numOfRows);

        // create puzzle pieces

        final List<Piece> pieces = new ArrayList<Piece>();

        for (int col = 0; col < numOfColumns; col++) {

            for (int row = 0; row < numOfRows; row++) {

                int x = col * Piece.SIZE;

                int y = row * Piece.SIZE;

                final Piece piece = new Piece(image, x, y, row > 0, col > 0,

                        row < numOfRows - 1, col < numOfColumns - 1,

                        desk.getWidth(), desk.getHeight());

                pieces.add(piece);

            }

        }

        desk.getChildren().addAll(pieces);

        // create button box

        Button shuffleButton = new Button("Shuffle");

        shuffleButton.setStyle("-fx-font-size: 2em;");

        shuffleButton.setOnAction((ActionEvent actionEvent) -> {

            if (timeline != null) {

                timeline.stop();

            }

            timeline = new Timeline();

            for (final Piece piece : pieces) {

                piece.setActive();

                double shuffleX = Math.random()

                        * (desk.getWidth() - Piece.SIZE + 48f)

                        - 24f - piece.getCorrectX();

                double shuffleY = Math.random()

                        * (desk.getHeight() - Piece.SIZE + 30f)

                        - 15f - piece.getCorrectY();

                timeline.getKeyFrames().add(

                        new KeyFrame(Duration.seconds(1),

                                new KeyValue(piece.translateXProperty(), shuffleX),

                                new KeyValue(piece.translateYProperty(), shuffleY)));

            }

            timeline.playFromStart();

        });

        Button solveButton = new Button("Solve");

        solveButton.setStyle("-fx-font-size: 2em;");

        solveButton.setOnAction((ActionEvent actionEvent) -> {

            if (timeline != null) {

                timeline.stop();

            }

            timeline = new Timeline();

            for (final Piece piece : pieces) {

                piece.setInactive();

                timeline.getKeyFrames().add(

                        new KeyFrame(Duration.seconds(1),

                                new KeyValue(piece.translateXProperty(), 0),

                                new KeyValue(piece.translateYProperty(), 0)));

            }

            timeline.playFromStart();

        });

        HBox buttonBox = new HBox(8);

        buttonBox.getChildren().addAll(shuffleButton, solveButton);

        // create vbox for desk and buttons

        VBox vb = new VBox(10);

        vb.getChildren().addAll(desk, buttonBox);

        vb.setPadding(new Insets(15, 24, 15, 24));

        vb.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);

        vb.setMinSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);

        return vb;

    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.show();

    }

 

    /**

     * Java main for when running without JavaFX launcher

     */

    public static void main(String[] args) {

        launch(args);

    }

}