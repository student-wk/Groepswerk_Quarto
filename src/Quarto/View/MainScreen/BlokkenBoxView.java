package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

// Willem: naam klasse veranderd naar BlokkenBoxView

public class BlokkenBoxView extends GridPane {
    public final Color BlUE_COLOR = Color.BLUE;
    public final Color RED_COLOR = Color.RED;
    public final Color EMPTY_COLOR_RED = Color.BROWN;
    public final Color EMPTY_COLOR_BLUE = Color.BLUEVIOLET;
    public final Color DEFAULT_COLOR = Color.GRAY;
    public final int BIG_SIZE = 30;
    public final int BIG_SIZE_EMPTY = 25;
    public final int STROKE_WIDTH_BIG = 10;
    public final int SMALL_SIZE = 20;
    public final int SMALL_SIZE_EMPTY = 16;
    public final int STROKE_WIDTH_SMALL = 8;

    public final int ROW_SIZE =4;
    public final int COL_SIZE =2;

    private Circle [][] circles;
    private Rectangle[][] rectangles;

    public BlokkenBoxView() {
        circles = new Circle[ROW_SIZE][COL_SIZE];
        rectangles = new Rectangle[ROW_SIZE][COL_SIZE];
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {

        /*
        * Maakt twee kolommen grijze cirkels en twee kolommen grijze vierkanten aan in de afgebeelde blokkenbox.
        * */

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                circles[i][j] = new Circle(BIG_SIZE, Color.GRAY);
                rectangles[i][j] = new Rectangle(2*BIG_SIZE, 2*BIG_SIZE,Color.GRAY);
            }

        }

        /*
        * In deze methodes worden de afbeeldingen van de cirkels en vierkanten aangemaakt en in de cirkel array of de
        * vierkant array geplaatst.
        * */

        for (Blok.Grootte grootte : Blok.Grootte.values()) {
            for (Blok.Kleur kleur : Blok.Kleur.values()) {
                for (Blok.Vorm vorm : Blok.Vorm.values()) {
                    for (Blok.Vulling vulling : Blok.Vulling.values()) {
                        Blok blok = new Blok(grootte,kleur,vorm,vulling); //Er wordt voor iedere blok in het model hier een blok aangemaakt.
                        if (blok.getVorm().equals(Blok.Vorm.ROND)){
                            if (blok.getKleur().equals(Blok.Kleur.WIT)){
                                int colIndex = 0; // Dit deel van de methode gaat alleen over de eerste kolom.
                                int rowIndex;
                                if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 0; // De volle, grote, witte cirkelafbeeldingen komen in de eerste rij terecht.
                                    circles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                    rowIndex = 1; // De volle, kleine, witte cirkelafbeeldingen komen in de tweede rij terecht.
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE);
                                    circles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 2; // De holle, grote, witte cirkelafbeeldingen komen in de derde rij terecht.
                                    circles[rowIndex][colIndex].setRadius(BIG_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    circles[rowIndex][colIndex].setFill(EMPTY_COLOR_RED);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3; // De holle, kleine, witte cirkelafbeeldingen komen in de vierde rij terecht.
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    circles[rowIndex][colIndex].setFill(EMPTY_COLOR_RED);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            } else {
                                int colIndex = 1;
                                int rowIndex;
                                if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 0;
                                    circles[rowIndex][colIndex].setFill(BlUE_COLOR);

                                } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                    rowIndex = 1;
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE);
                                    circles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 2;
                                    circles[rowIndex][colIndex].setRadius(BIG_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    circles[rowIndex][colIndex].setFill(EMPTY_COLOR_BLUE);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    circles[rowIndex][colIndex].setRadius(SMALL_SIZE_EMPTY);
                                    circles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    circles[rowIndex][colIndex].setFill(EMPTY_COLOR_BLUE);
                                    circles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            }
                        } else if (blok.getVorm().equals(Blok.Vorm.VIERKANT)){
                            if (blok.getKleur().equals(Blok.Kleur.WIT)){
                                int colIndex = 0;
                                int rowIndex;
                                if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 0;
                                    rectangles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                    rowIndex = 1;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setFill(RED_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 2;
                                    rectangles[rowIndex][colIndex].setWidth(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setHeight(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(EMPTY_COLOR_RED);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(RED_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(EMPTY_COLOR_RED);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_SMALL);
                                }
                            } else {
                                int colIndex = 1;
                                int rowIndex;
                                if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 0;
                                    rectangles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                    rowIndex = 1;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE*2);
                                    rectangles[rowIndex][colIndex].setFill(BlUE_COLOR);
                                } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                    rowIndex = 2;
                                    rectangles[rowIndex][colIndex].setWidth(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setHeight(BIG_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(EMPTY_COLOR_BLUE);
                                    rectangles[rowIndex][colIndex].setStrokeWidth(STROKE_WIDTH_BIG);
                                } else {
                                    rowIndex = 3;
                                    rectangles[rowIndex][colIndex].setHeight(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setWidth(SMALL_SIZE_EMPTY*2);
                                    rectangles[rowIndex][colIndex].setStroke(BlUE_COLOR);
                                    rectangles[rowIndex][colIndex].setFill(EMPTY_COLOR_BLUE);
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
        this.setGridLinesVisible(true);
        setHgap(10);
        this.setAlignment(Pos.CENTER);

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                this.add(circles[i][j], j, i);
            }
        }

        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                this.add(rectangles[i][j], j+2, i);
            }
        }

    }

    public Circle[][] getCircles() {
        return circles;
    }

    public Rectangle[][] getRectangles() {
        return rectangles;
    }
}