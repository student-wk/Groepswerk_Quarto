package Quarto.Model;

/**
 * This class creates a board to place pieces on.
 * It also checks if the board is full or if there is a winning combination.
 *
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:38
 */
public class Board {
    private Piece[][] boardArray;
    public static final int SIZE = 4;

    /**
     * The constructor contains an array of pieces and empty places called boardArray.
     * */

    public Board() {
        this.boardArray = new Piece[SIZE][SIZE];
    }

    /**
     * This method checks if a position on the board is empty or not.
     *
     * @param position The specific position you want to check.
     * @return Returns a boolean which tells if a position on the board is empty or not.
     * */

    public boolean isEmpty(Position position) {
        return(boardArray[position.getRow()][position.getColumn()]) == null;
    }

    /**
    * This method adds a piece to the board.
     *
     * @param piece The piece you want to place on the board.
     * @param position The position you want to put the piece on.
    * */

    public void addPiece(Piece piece, Position position) throws QuartoException {
        if (isEmpty(position) == false) {
            throw new QuartoException("The position is not empty");
        } else {
            boardArray[position.getRow()][position.getColumn()] = piece;
        }
    }

    /**
     * This method checks if the board is full or not.
     *
     * @return True if the board is full, false if the board is not.
     **/

    public boolean isFull() {
        boolean isFull = true;
        int row = 0;
        int column;
        while ((row< boardArray.length) && (isFull)) {
            column = 0;
            while ((column< boardArray.length) && (isFull)) {
                if (boardArray[row][column] == null) {
                    isFull = false;
                }
                column++;
            }
            row++;
        }
        return isFull;
    }

    /**
     * This method checks if there is a winning combination of pieces on the board.
     *
     * @return A boolean true if there is a combination, false if not.
     * */

    public boolean hasCombination() { return (rowColumn() || diagonal());}

    /**
     * This method checks if there is a diagonal winning combination.
     * It is part of the method hasCombination().
     *
     * @return true if there is a diagonal combination of 4 pieces with the same property.
     * */

    public boolean diagonal() {
        int size = boardArray.length;
        return ((colorCombo(0,0,1,1) == size-1)
                || ((shapeCombo(0,0,1,1) == size-1)
                || (sizeCombo(0,0,1,1) == size-1)
                || (fillingCombo(0,0,1,1) == size-1)
                || (colorCombo(0,size-1,1,-1) == size-1)
                || (shapeCombo(0,size-1,1,-1) == size-1)
                || (sizeCombo(0,size-1,1,-1) == size-1)
                || (fillingCombo(0,size-1,1,-1) == size-1)));

    }

    /**
     * This method checks if there is a row or column with a winning combination.
     * It is part of the method hasCombination().
     *
     * @return true if there is a combination of 4 pieces with the same property.
     * */

    public boolean rowColumn() {
        int i = 0;
        boolean hasWon = false;
        while ((i < boardArray.length) && (!hasWon)) {
            if ((colorCombo(i, 0, 0, 1) == boardArray.length -1)
                || (shapeCombo(i, 0, 0, 1) == boardArray.length -1)
                || (sizeCombo(i, 0, 0, 1) == boardArray.length -1)
                || (fillingCombo(i, 0, 0, 1) == boardArray.length -1)
                || (colorCombo(0, i, 1, 0) == boardArray.length -1)
                || (shapeCombo(0, i, 1, 0) == boardArray.length -1)
                || (sizeCombo(0, i, 1, 0) == boardArray.length -1)
                || (fillingCombo(0, i, 1, 0) == boardArray.length -1)) {
                hasWon = true;
            }
            i++;
        }
        return hasWon;
    }

    /**
     * This method returns a combination of pieces of the same color.
     * The method checks if the combination stays within the length of the board, if a position is empty and if subsequent pieces have the same color.
     * This method is part of the rowColumn() and diagonal() methods.
     *
     * @param row the starting row of a possible combination.
     * @param column the starting column of a possible combination.
     * @param rowIncrement the next row of a possible combination.
     * @param columnIncrement the next column of a possible combination.
     * @return an int which is the combination of consecutive pieces of the same color.
     **/

    public int colorCombo(int row, int column, int rowIncrement, int columnIncrement) {
        int combo = 0;
        int a = rowIncrement;
        int b = columnIncrement;
        while ((rowIncrement < boardArray.length) && (columnIncrement < boardArray.length)
            && (empty(row, column, rowIncrement, columnIncrement)) && (boardArray[row][column].getColor())
            == boardArray[row + rowIncrement][column + columnIncrement].getColor()) {
            combo++;
            rowIncrement = rowIncrement + a;
            columnIncrement = columnIncrement +b;
        }
        return combo;
    }

    /**
     * This method returns a combination of pieces of the same shape.
     * The method checks if the combination stays within the length of the board, if a position is empty and if subsequent pieces have the same shape.
     * This method is part of the rowColumn() and diagonal() methods.
     *
     * @param row the starting row of a possible combination.
     * @param column the starting column of a possible combination.
     * @param rowIncrement the next row of a possible combination.
     * @param columnIncrement the next column of a possible combination.
     * @return an int which is the combination of consecutive pieces of the same shape.
     **/

    public int shapeCombo(int row, int column, int rowIncrement, int columnIncrement) {
        int combo = 0;
        int a = rowIncrement;
        int b = columnIncrement;
        while ((rowIncrement < boardArray.length) && (columnIncrement < boardArray.length)
            && (empty(row, column, rowIncrement, columnIncrement)) && (boardArray[row][column].getShape())
            == boardArray[row + rowIncrement][column + columnIncrement].getShape()) {
            combo++;
            rowIncrement = rowIncrement + a;
            columnIncrement = columnIncrement +b;
        }
        return combo;
    }

    /**
     * This method returns a combination of pieces of the same size.
     * The method checks if the combination stays within the length of the board, if a position is empty and if subsequent pieces have the same size.
     * This method is part of the rowColumn() and diagonal() methods.
     *
     * @param row the starting row of a possible combination.
     * @param column the starting column of a possible combination.
     * @param rowIncrement the next row of a possible combination.
     * @param columnIncrement the next column of a possible combination.
     * @return an int which is the combination of consecutive pieces of the same size.
     **/

    public int sizeCombo(int row, int column, int rowIncrement, int columnIncrement) {
        int combo = 0;
        int a = rowIncrement;
        int b = columnIncrement;
        while ((rowIncrement < boardArray.length) && (columnIncrement < boardArray.length)
            && (empty(row, column, rowIncrement, columnIncrement)) && (boardArray[row][column].getSize())
            == boardArray[row + rowIncrement][column + columnIncrement].getSize()) {
            combo++;
            rowIncrement = rowIncrement + a;
            columnIncrement = columnIncrement +b;
        }
        return combo;
    }

    /**
     * This method returns a combination of pieces of the same filling.
     * The method checks if the combination stays within the length of the board, if a position is empty and if subsequent pieces have the same filling.
     * This method is part of the rowColumn() and diagonal() methods.
     *
     * @param row the starting row of a possible combination.
     * @param column the starting column of a possible combination.
     * @param rowIncrement the next row of a possible combination.
     * @param columnIncrement the next column of a possible combination.
     * @return an int which is the combination of consecutive pieces of the same filling.
     **/

    public int fillingCombo(int row, int column, int rowIncrement, int columnIncrement) {
        int combo = 0;
        int a = rowIncrement;
        int b = columnIncrement;
        while ((rowIncrement < boardArray.length) && (columnIncrement < boardArray.length)
            && (empty(row, column, rowIncrement, columnIncrement)) && (boardArray[row][column].getFilling())
            == boardArray[row + rowIncrement][column + columnIncrement].getFilling()) {
            combo++;
            rowIncrement = rowIncrement + a;
            columnIncrement = columnIncrement +b;
        }
        return combo;
    }

    /**
     * This method is part of the colorCombo, shapeCombo, sizeCombo and fillingCombo methods.
     * This metod checks if the starting position of a possible winning combo and each increment of that position is empty or not.
     *
     * @param row the starting row of a possible combination.
     * @param column the starting column of a possible combination.
     * @param rowIncrement the next row of a possible combination.
     * @param columnIncrement the next column of a possible combination.
     * @return true if not empty.
     * */

    public boolean empty(int row, int column, int rowIncrement, int columnIncrement) {
        return ((!isEmpty(new Position(row,column))))
                && ((!isEmpty(new Position(row + rowIncrement, column + columnIncrement))));

    }
}
