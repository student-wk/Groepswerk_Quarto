package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import javafx.scene.paint.Color;

public class Piece extends javafx.scene.shape.Shape{
    public enum Size{ BIG, SMALL}
    public enum Shape{ ROUND, SQUARE}
    public enum Color{ RED, BLUE}
    public enum Fill{ FULL, EMPTY}

    private Size size;
    private Shape shape;
    private Color color;
    private Fill fill;




    public Piece(Size size, Shape shape, Color color, Fill fill) {
        this.size = size;
        this.shape = shape;
        this.color = color;
        this.fill = fill;

        if (shape.equals(Shape.ROUND)){

        }
    }
}


//    public final Color BlUE_COLOR = Color.BLUE;
//    public final Color RED_COLOR = Color.RED;
//    public final Color EMPTY_COLOR_RED = Color.BROWN;
//    public final Color EMPTY_COLOR_BLUE = Color.BLUEVIOLET;
//    public final Color DEFAULT_COLOR = Color.GRAY;
//    public final int BIG_SIZE = 30;
//    public final int BIG_SIZE_EMPTY = 25;
//    public final int STROKE_WIDTH_BIG = 10;
//    public final int SMALL_SIZE = 20;
//    public final int SMALL_SIZE_EMPTY = 16;
//    public final int STROKE_WIDTH_SMALL = 8;
//    view.getBlokkenBoxGridPane().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxGridPane().BIG_SIZE*2);
//    view.getBlokkenBoxGridPane().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxGridPane().BIG_SIZE*2);
//    view.getBlokkenBoxGridPane().getRectangles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxGridPane().DEFAULT_COLOR);
//    view.getBlokkenBoxGridPane().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxGridPane().DEFAULT_COLOR)
//            view.getBlokkenBoxGridPane().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);