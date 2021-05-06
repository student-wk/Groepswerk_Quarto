package Quarto.View.MainScreen;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class BlokkenBoxView2 extends GridPane {
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

    private final HashSet<Circle> circles =  new LinkedHashSet<>();;
    private Rectangle[][] rectangles;

    public BlokkenBoxView2() {
        initialiseNodes();
        layoutNodes();
    }



    public void initialiseNodes(){
        circles.add(new Circle(20, Color.BROWN));
        circles.add(new Circle(20, Color.BLUE));
        circles.add(new Circle(20, Color.BLACK));
        circles.add(new Circle(20, Color.BEIGE));
    }

    public void layoutNodes() {
        for (Iterator<Circle> iterator = circles.iterator(); iterator.hasNext(); ) {
            Circle next =  iterator.next();
            this.getChildren().addAll(next);

        }

    }
}
