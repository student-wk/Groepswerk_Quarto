package Quarto.View.MainScreen;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BlokkenBoxView extends GridPane {
    public static final Color BlUE_COLOR = Color.BLUE;
    public static final Color RED_COLOR = Color.RED;
    public static final Color EMPTY_COLOR_RED = Color.BROWN;
    public static final Color EMPTY_COLOR_BLUE = Color.BLUEVIOLET;
    public static final int BIG_SIZE = 30;
    public static final int BIG_SIZE_EMPTY = 25;
    public static final int STROKE_WIDTH_BIG = 10;
    public static final int SMALL_SIZE = 20;
    public static final int SMALL_SIZE_EMPTY = 16;
    public static final int STROKE_WIDTH_SMALL = 8;
    private Circle CircleRedFullBig;
    private Circle CircleRedFullSmall;
    private Circle CircleRedEmptyBig;
    private Circle CircleRedEmptySmall;
    private Circle CircleBlueFullBig;
    private Circle CircleBlueFullSmall;
    private Circle CircleBlueEmptyBig;
    private Circle CircleBlueEmptySmall;

    private Rectangle RectRedFullBig;
    private Rectangle RectRedFullSmall;
    private Rectangle RectRedEmptyBig;
    private Rectangle RectRedEmptySmall;
    private Rectangle RectBlueFullBig;
    private Rectangle RectBlueFullSmall;
    private Rectangle RectBlueEmptyBig;
    private Rectangle RectBlueEmptySmall;

    public BlokkenBoxView() {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        inialiseBlokkkenBox();


    }

    public void layoutNodes() {
        layoutBlokkenBox();
        this.setGridLinesVisible(true);
        setHgap(10);
        this.setAlignment(Pos.CENTER);
    }

    public void inialiseBlokkkenBox() {
        CircleRedFullBig = new Circle(BIG_SIZE, RED_COLOR);
        CircleRedFullSmall = new Circle(SMALL_SIZE, RED_COLOR);
        CircleBlueFullBig = new Circle(BIG_SIZE, BlUE_COLOR);
        CircleBlueFullSmall = new Circle(SMALL_SIZE, BlUE_COLOR);

        CircleRedEmptyBig = new Circle(BIG_SIZE_EMPTY, EMPTY_COLOR_RED);
        CircleRedEmptySmall = new Circle(SMALL_SIZE_EMPTY, EMPTY_COLOR_RED);
        CircleRedEmptyBig.setStroke(RED_COLOR);
        CircleRedEmptySmall.setStroke(RED_COLOR);
        CircleRedEmptyBig.setStrokeWidth(STROKE_WIDTH_SMALL);
        CircleRedEmptySmall.setStrokeWidth(STROKE_WIDTH_SMALL);

        CircleBlueEmptyBig = new Circle(BIG_SIZE_EMPTY, EMPTY_COLOR_BLUE);
        CircleBlueEmptySmall = new Circle(SMALL_SIZE_EMPTY, EMPTY_COLOR_BLUE);
        CircleBlueEmptyBig.setStroke(BlUE_COLOR);
        CircleBlueEmptySmall.setStroke(BlUE_COLOR);
        CircleBlueEmptyBig.setStrokeWidth(STROKE_WIDTH_BIG);
        CircleBlueEmptySmall.setStrokeWidth(STROKE_WIDTH_SMALL);


        RectRedFullBig = new Rectangle(2*BIG_SIZE, 2*BIG_SIZE, RED_COLOR);
        RectRedFullSmall = new Rectangle(2*SMALL_SIZE, 2*SMALL_SIZE, RED_COLOR);
        RectBlueFullBig = new Rectangle(2*BIG_SIZE, 2*BIG_SIZE, BlUE_COLOR);
        RectBlueFullSmall = new Rectangle(2*SMALL_SIZE, 2*SMALL_SIZE, BlUE_COLOR);

        RectRedEmptyBig = new Rectangle(2*BIG_SIZE_EMPTY, 2*BIG_SIZE_EMPTY, EMPTY_COLOR_RED);
        RectRedEmptyBig.setStroke(RED_COLOR);
        RectRedEmptyBig.setStrokeWidth(STROKE_WIDTH_BIG);

        RectRedEmptySmall = new Rectangle(2*SMALL_SIZE_EMPTY, 2*SMALL_SIZE_EMPTY, EMPTY_COLOR_RED);
        RectRedEmptySmall.setStroke(RED_COLOR);
        RectRedEmptySmall.setStrokeWidth(STROKE_WIDTH_BIG);

        RectBlueEmptyBig = new Rectangle(2*BIG_SIZE_EMPTY, 2*BIG_SIZE_EMPTY, EMPTY_COLOR_BLUE);
        RectBlueEmptyBig.setStroke(BlUE_COLOR);
        RectBlueEmptyBig.setStrokeWidth(STROKE_WIDTH_BIG);

        RectBlueEmptySmall = new Rectangle(2*SMALL_SIZE_EMPTY, 2*SMALL_SIZE_EMPTY, EMPTY_COLOR_BLUE);
        RectBlueEmptySmall.setStroke(BlUE_COLOR);
        RectBlueEmptySmall.setStrokeWidth(STROKE_WIDTH_BIG);
    }

    public Circle getCircleRedFullBig() {
        return CircleRedFullBig;
    }

    public Circle getCircleRedFullSmall() {
        return CircleRedFullSmall;
    }

    public Circle getCircleRedEmptyBig() {
        return CircleRedEmptyBig;
    }

    public Circle getCircleRedEmptySmall() {
        return CircleRedEmptySmall;
    }

    public Circle getCircleBlueFullBig() {
        return CircleBlueFullBig;
    }

    public Circle getCircleBlueFullSmall() {
        return CircleBlueFullSmall;
    }

    public Circle getCircleBlueEmptyBig() {
        return CircleBlueEmptyBig;
    }

    public Circle getCircleBlueEmptySmall() {
        return CircleBlueEmptySmall;
    }

    public Rectangle getRectRedFullBig() {
        return RectRedFullBig;
    }

    public Rectangle getRectRedFullSmall() {
        return RectRedFullSmall;
    }

    public Rectangle getRectRedEmptyBig() {
        return RectRedEmptyBig;
    }

    public Rectangle getRectRedEmptySmall() {
        return RectRedEmptySmall;
    }

    public Rectangle getRectBlueFullBig() {
        return RectBlueFullBig;
    }

    public Rectangle getRectBlueFullSmall() {
        return RectBlueFullSmall;
    }

    public Rectangle getRectBlueEmptyBig() {
        return RectBlueEmptyBig;
    }

    public Rectangle getRectBlueEmptySmall() {
        return RectBlueEmptySmall;
    }

    public void layoutBlokkenBox() {
        this.add(CircleRedFullBig, 0, 0);
        this.add(CircleRedFullSmall, 0, 1);
        this.add(CircleRedEmptyBig, 0, 2);
        this.add(CircleRedEmptySmall, 0, 3);
        this.add(CircleBlueFullBig, 0, 4);
        this.add(CircleBlueFullSmall, 0, 5);
        this.add(CircleBlueEmptyBig, 2, 4);
        this.add(CircleBlueEmptySmall, 2, 5);


        this.add(RectRedFullBig, 1, 0);
        this.add(RectRedFullSmall, 1, 1);
        this.add(RectRedEmptyBig, 1, 2);
        this.add(RectRedEmptySmall, 1, 3);
        this.add(RectBlueFullBig, 1, 4);
        this.add(RectBlueFullSmall, 1, 5);
        this.add(RectBlueEmptyBig, 3, 4);
        this.add(RectBlueEmptySmall, 3, 5);


        GridPane.setConstraints(CircleRedFullBig, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleRedFullSmall, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleRedEmptyBig, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleRedEmptySmall, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleBlueFullBig, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleBlueFullSmall, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleBlueEmptyBig, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(CircleBlueEmptySmall, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);


        GridPane.setConstraints(RectRedFullBig, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectRedFullSmall, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectRedEmptyBig, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectRedEmptySmall, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectBlueFullBig, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectBlueFullSmall, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectBlueEmptyBig, 3, 4, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        GridPane.setConstraints(RectBlueEmptySmall, 3, 5, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);



        for (int row = 0;row < 4 ; row++) {
            for (int col = 2; col < 6; col++) {
                Circle greyCicle = new Circle(BIG_SIZE, Color.GRAY);
                this.add(greyCicle, col,row);
                GridPane.setConstraints(greyCicle, col, row, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            }

        }



    }

}