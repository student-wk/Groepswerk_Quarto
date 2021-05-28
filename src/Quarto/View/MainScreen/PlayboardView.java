package Quarto.View.MainScreen;

import Quarto.Model.Piece;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PlayboardView extends GridPane {


    public final javafx.scene.paint.Color BlUE_COLOR = javafx.scene.paint.Color.DODGERBLUE;
    public final javafx.scene.paint.Color RED_COLOR = javafx.scene.paint.Color.ORANGERED;
    public final javafx.scene.paint.Color DEFAULT_COLOR = javafx.scene.paint.Color.TRANSPARENT;
    public final int BIG_SIZE = 30;
    public final int BIG_SIZE_EMPTY = 25;
    public final int STROKE_WIDTH_BIG = 10;
    public final int SMALL_SIZE = 20;
    public final int SMALL_SIZE_EMPTY = 16;
    public final int STROKE_WIDTH_SMALL = 8;
    private final RowConstraints rowConstraints = new RowConstraints(60);
    private final ColumnConstraints columnConstraints = new ColumnConstraints(60);



    public PlayboardView() {
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
        layoutPlayboard();
        this.setGridLinesVisible(true);
        setVgap(20);
        setHgap(20);

        this.setAlignment(Pos.CENTER);
        this.setBorder(new Border(new BorderImage(new Image("/images/Melamine-wood-005.png"),BorderWidths.DEFAULT,new Insets(2),BorderWidths.DEFAULT, true,BorderRepeat.REPEAT, BorderRepeat.REPEAT)));
        this.getRowConstraints().addAll(rowConstraints,rowConstraints,rowConstraints,rowConstraints);
        this.getColumnConstraints().addAll(columnConstraints,columnConstraints,columnConstraints,columnConstraints);

    }


    public void layoutPlayboard() {
        for (int row = 0;row < 4 ; row++) {
            for (int col = 0; col < 4; col++) {
                Circle greyCicle = new Circle(BIG_SIZE, DEFAULT_COLOR);
                this.add(greyCicle, col,row);
                GridPane.setConstraints(greyCicle, col, row, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER);
            }
        }
    }

    public void addPiece(int rowIndex, int colIndex, Piece chosenPiece) {
        this.removeNodeByRowColumnIndex(rowIndex, colIndex);
        if (chosenPiece.getShape().equals(Piece.Shape.ROUND)){
            Circle circle = new Circle();
            if (chosenPiece.getColor().equals(Piece.Color.WHITE)){
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(BIG_SIZE);
                    circle.setFill(RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                   circle.setRadius(SMALL_SIZE);
                    circle.setFill(RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
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
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(BIG_SIZE);
                    circle.setFill(BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    circle.setRadius(SMALL_SIZE);
                    circle.setFill(BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
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
        } else if (chosenPiece.getShape().equals(Piece.Shape.SQUARE)){
            Rectangle rectangle = new Rectangle();
            if (chosenPiece.getColor().equals(Piece.Color.WHITE)){
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                   rectangle.setFill(RED_COLOR);
                   rectangle.setWidth(BIG_SIZE*2);
                   rectangle.setHeight(BIG_SIZE*2);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    rectangle.setHeight(SMALL_SIZE*2);
                    rectangle.setWidth(SMALL_SIZE*2);
                    rectangle.setFill(RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
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
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    rectangle.setFill(BlUE_COLOR);
                    rectangle.setWidth(BIG_SIZE*2);
                    rectangle.setHeight(BIG_SIZE*2);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    rectangle.setHeight(SMALL_SIZE*2);
                    rectangle.setWidth(SMALL_SIZE*2);
                    rectangle.setFill(BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
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