package Quarto.View.MainScreen;

import Quarto.Model.Piece;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

// Willem: naam klasse veranderd naar BlokkenBoxView

public class BlokkenBoxView extends GridPane {
    public final javafx.scene.paint.Color BlUE_COLOR = javafx.scene.paint.Color.DODGERBLUE;
    public final javafx.scene.paint.Color RED_COLOR = javafx.scene.paint.Color.ORANGERED;
//    public final Color EMPTY_COLOR_RED = Color.TRANSPARENT;
//    public final Color EMPTY_COLOR_BLUE = Color.TRANSPARENT;
    public final javafx.scene.paint.Color DEFAULT_COLOR = javafx.scene.paint.Color.TRANSPARENT;
    public final int BIG_SIZE = 30;
    public final int BIG_SIZE_EMPTY = 25;
    public final int STROKE_WIDTH_BIG = 10;
    public final int SMALL_SIZE = 20;
    public final int SMALL_SIZE_EMPTY = 16;
    public final int STROKE_WIDTH_SMALL = 8;
    private final RowConstraints rowConstraints = new RowConstraints(60);
    private final ColumnConstraints columnConstraints = new ColumnConstraints(60);
    DropShadow ds;
    

    public final int ROW_SIZE =4;
    public final int COL_SIZE =2;

    private Circle [][] circles;
    private Rectangle[][] rectangles;

    public BlokkenBoxView() {
        circles = new Circle[ROW_SIZE][COL_SIZE];
        rectangles = new Rectangle[ROW_SIZE][COL_SIZE];
        initialiseNodes();
        layoutNodes();
        ds = new DropShadow();
        ds.setOffsetY(9.0f);
        ds.setOffsetX(9.0f);
        ds.setColor(javafx.scene.paint.Color.BLACK);
    }

    public void initialiseNodes() {
        /*
        * Maakt twee kolommen grijze cirkels en twee kolommen grijze vierkanten aan in de afgebeelde blokkenbox.
        * */
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                circles[i][j] = new Circle(BIG_SIZE, javafx.scene.paint.Color.GRAY);
                circles[i][j].setEffect(ds);

                rectangles[i][j] = new Rectangle(2*BIG_SIZE, 2*BIG_SIZE, javafx.scene.paint.Color.GRAY);
            }
        }
        /*
        * In deze methodes worden de afbeeldingen van de cirkels en vierkanten aangemaakt en in de cirkel array of de
        * vierkant array geplaatst.
        * */
        for (Piece.Size size : Piece.Size.values()) {
            for (Piece.Color color : Piece.Color.values()) {
                for (Piece.Shape shape : Piece.Shape.values()) {
                    for (Piece.Filling filling : Piece.Filling.values()) {
                        Piece piece = new Piece(size, color, shape, filling); //Er wordt voor iedere blok in het model hier een blok aangemaakt.
                        if (piece.getShape().equals(Piece.Shape.ROUND)){
                            if (piece.getColor().equals(Piece.Color.WHITE)){
                                int colIndex = 0; // Dit deel van de methode gaat alleen over de eerste kolom.
                                int rowIndex;
                                if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 0; // De volle, grote, witte cirkelafbeeldingen komen in de eerste rij terecht.
                                    circles[rowIndex][colIndex].setFill(RED_COLOR);

                                } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                    rowIndex = 1; // De volle, kleine, witte cirkelafbeeldingen komen in de tweede rij terecht.
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE);
                                    circles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 2; // De holle, grote, witte cirkelafbeeldingen komen in de derde rij terecht.
                                    circles[rowIndex][colIndex].setRadius(BIG_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    circles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3; // De holle, kleine, witte cirkelafbeeldingen komen in de vierde rij terecht.
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    circles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            } else {
                                int colIndex = 1;
                                int rowIndex;
                                if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 0;
                                    circles[rowIndex][colIndex].setFill(BlUE_COLOR);

                                } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                    rowIndex = 1;
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE);
                                    circles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 2;
                                    circles[rowIndex][colIndex].setRadius(BIG_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    circles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    circles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            }
                        } else if (piece.getShape().equals(Piece.Shape.SQUARE)){
                            if (piece.getColor().equals(Piece.Color.WHITE)){
                                int colIndex = 0;
                                int rowIndex;
                                if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 0;
                                    rectangles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                    rowIndex = 1;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 2;
                                    rectangles[rowIndex][colIndex].setWidth(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setHeight(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            } else {
                                int colIndex = 1;
                                int rowIndex;
                                if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 0;
                                    rectangles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                    rowIndex = 1;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                    rowIndex = 2;
                                    rectangles[rowIndex][colIndex].setWidth(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setHeight(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(DEFAULT_COLOR);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    * De afbeeldingen van de blokken worden op de juiste plaats van de gridpane geplaatst.
    * */
    public void layoutNodes() {
        this.setGridLinesVisible(false);
        setHgap(20);
        setVgap(20);
        this.setAlignment(Pos.CENTER);
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                this.add(circles[i][j], j, i);
                GridPane.setConstraints(circles[i][j], j, i, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);

            }
        }
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                this.add(rectangles[i][j], j+2, i);
                GridPane.setConstraints(rectangles[i][j], j+2, i, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            }
        }
        this.getRowConstraints().addAll(rowConstraints,rowConstraints,rowConstraints,rowConstraints);
        this.getColumnConstraints().addAll(columnConstraints,columnConstraints,columnConstraints,columnConstraints);
        this.setGridLinesVisible(true);
    }
    public Circle[][] getCircles() {
        return circles;
    }
    public Rectangle[][] getRectangles() {
        return rectangles;
    }
}