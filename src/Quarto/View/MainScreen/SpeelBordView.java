package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SpeelBordView extends GridPane {
    public final Color BlUE_COLOR = Color.DODGERBLUE;
    public final Color RED_COLOR = Color.ORANGERED;
//    public final Color EMPTY_COLOR_RED = Color.TRANSPARENT;
//    public final Color EMPTY_COLOR_BLUE = Color.TRANSPARENT;
    public final Color DEFAULT_COLOR = Color.TRANSPARENT;
    public final int BIG_SIZE = 30;
    public final int BIG_SIZE_EMPTY = 25;
    public final int STROKE_WIDTH_BIG = 10;
    public final int SMALL_SIZE = 20;
    public final int SMALL_SIZE_EMPTY = 16;
    public final int STROKE_WIDTH_SMALL = 8;



    public SpeelBordView() {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
    }

    public Node getNodeByRowColumnIndex (final int row, final int column) {
        Node result = null;
        ObservableList<Node> childrens = this.getChildren();
        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    public void removeNodeByRowColumnIndex(final int row,final int column) {

        ObservableList<Node> childrens = this.getChildren();
        for(Node node : childrens) {
            if(node instanceof Circle && this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
                this.getChildren().remove(node);
                break;
            }
        }
    }

    public void layoutNodes() {
        layoutSpeelBord();


        this.setGridLinesVisible(true);
        setVgap(20);
        setHgap(20);
        this.setAlignment(Pos.CENTER);

    }


    public void layoutSpeelBord() {
        for (int row = 0;row < 4 ; row++) {
            for (int col = 0; col < 4; col++) {
                Circle greyCicle = new Circle(BIG_SIZE, DEFAULT_COLOR);
                this.add(greyCicle, col,row);
                GridPane.setConstraints(greyCicle, col, row, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            }
        }
    }

    public void voegBlokToe(int rowIndex, int colIndex, Blok gekozenBlok) {
        this.removeNodeByRowColumnIndex(rowIndex, colIndex);
        if (gekozenBlok.getVorm().equals(Blok.Vorm.ROND)){
            Circle circle = new Circle();
            if (gekozenBlok.getKleur().equals(Blok.Kleur.WIT)){
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(BIG_SIZE);
                    circle.setFill(RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                   circle.setRadius(SMALL_SIZE);
                    circle.setFill(RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(BIG_SIZE_EMPTY);
                    circle.setStroke(RED_COLOR);
                    circle.setFill(DEFAULT_COLOR);
                    circle.setStrokeWidth(STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(SMALL_SIZE_EMPTY);
                    circle.setStroke(RED_COLOR);
                    circle.setFill(DEFAULT_COLOR);
                    circle.setStrokeWidth(STROKE_WIDTH_SMALL);
                }
            } else {
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(BIG_SIZE);
                    circle.setFill(BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    circle.setRadius(SMALL_SIZE);
                    circle.setFill(BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(BIG_SIZE_EMPTY);
                    circle.setStroke(BlUE_COLOR);
                    circle.setFill(DEFAULT_COLOR);
                    circle.setStrokeWidth(STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(SMALL_SIZE_EMPTY);
                    circle.setStroke(BlUE_COLOR);
                    circle.setFill(DEFAULT_COLOR);
                    circle.setStrokeWidth(STROKE_WIDTH_SMALL);
                }
            }
            this.add(circle,colIndex,rowIndex);
            GridPane.setConstraints(circle, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
        } else if (gekozenBlok.getVorm().equals(Blok.Vorm.VIERKANT)){
            Rectangle rectangle = new Rectangle();
            if (gekozenBlok.getKleur().equals(Blok.Kleur.WIT)){
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                   rectangle.setFill(RED_COLOR);
                   rectangle.setWidth(BIG_SIZE*2);
                   rectangle.setHeight(BIG_SIZE*2);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    rectangle.setHeight(SMALL_SIZE*2);
                    rectangle.setWidth(SMALL_SIZE*2);
                    rectangle.setFill(RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setWidth(BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(RED_COLOR);
                    rectangle.setFill(DEFAULT_COLOR);
                    rectangle.setStrokeWidth(STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(RED_COLOR);
                    rectangle.setFill(DEFAULT_COLOR);
                    rectangle.setStrokeWidth(STROKE_WIDTH_SMALL);
                }
            } else {
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setFill(BlUE_COLOR);
                    rectangle.setWidth(BIG_SIZE*2);
                    rectangle.setHeight(BIG_SIZE*2);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    rectangle.setHeight(SMALL_SIZE*2);
                    rectangle.setWidth(SMALL_SIZE*2);
                    rectangle.setFill(BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setWidth(BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(BlUE_COLOR);
                    rectangle.setFill(DEFAULT_COLOR);
                    rectangle.setStrokeWidth(STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(BlUE_COLOR);
                    rectangle.setFill(DEFAULT_COLOR);
                    rectangle.setStrokeWidth(STROKE_WIDTH_SMALL);
                }
            }
            this.add(rectangle, colIndex, rowIndex);
            GridPane.setConstraints(rectangle, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
        }
    }
}