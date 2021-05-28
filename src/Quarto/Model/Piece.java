package Quarto.Model;

import java.util.Objects;

/**
 * This class contains all the methods to create and compare a Piece object.
 *
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class Piece {

    public enum Size {
        SMALL, BIG;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Color {
        BLACK, WHITE;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Shape {
        ROUND, SQUARE;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Filling {
        EMPTY, FULL;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private Size size;
    private Color color;
    private Shape shape;
    private Filling filling;


    public Piece(){
    }

    public Piece(Size size, Color color, Shape shape, Filling filling) {
        if ((size != null) && (color != null) && (shape != null) && (filling != null)) {
            this.size = size;
            this.color = color;
            this.shape = shape;
            this.filling = filling;
        } else {
            throw new IllegalArgumentException("The piece is created incorrectly");
        }
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public Filling getFilling() {
        return filling;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setFilling(Filling filling) {
        this.filling = filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return size == piece.size && color == piece.color && shape == piece.shape && filling == piece.filling;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color, shape, filling);
    }

    @Override
    public String toString() {
        return ("piece" + "|" + size + "|" + color + "|" + shape + "|" + filling);
    }
}
