package Quarto.Model;

/**
 * This class can determines a position on the board.
 *
 * @author Willem Kuijpers
 * @version 1.0 23-4-2021 10:26
 */

public class Position {
    private final int row;
    private final int column;

    /**
     * The Position constructor makes an object that contains the coordinates of a place on the board.
     *
     * @param row The number of a row on the Board.
     * @param column The number of a column on the Board.
     * */

    public Position(int row, int column) {
        if ((row < Board.SIZE) && (row >=0) && (column < Board.SIZE) && (column >=0)) {
            this.row = row;
            this.column = column;
        } else {
            throw new IllegalArgumentException("The position is outside of the range of the board.");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "position" + "|" + row + "|"+ column;
    }
}
