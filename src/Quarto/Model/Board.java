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
            throw new QuartoException("Positie is niet vrij.");
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

/*
* De combo methodes worden hier samengevoegd en op alle mogelijke manieren diagonaal overlopen. Als er een combo van 4
* is (grootte-1) dan is de boolean true. RijIncrement en KolomIncrement is eigenlijk telkens rij +1 en kolom +1.
* diagonaal() en rijKolom() komen samen in heeftCombinatie().
* */

    /**
    * This method checks if there is a diagonal winning combination.
     *
     * @return true if there is a diagonal combination.
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

/*
* Gaat alle rijen en kolomen af op zoek naar een winnende combo.
* diagonaal() en rijKolom() komen samen in heeftCombinatie().
* */

    /**
     *
     *
     *
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

    /*
    * De combo methodes zijn een onderdeel van de diagonaal en rijKolom methode. De methode zoekt naar een combinatie
    * en checkt daarbij of de loop binnen het bord blijft, of er een position leeg is en of de opeenvolgende blokken
    * dezelfde eigenschap hebben (bv dezelfde kleur).
    * De methode geeft een int met de hoogste combo terug.
    * */


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

    public int shapeCombo(int row, int column, int rowIncrement, int ColumnIncrement) {
        int combo = 0;
        int a = rowIncrement;
        int b = ColumnIncrement;
        while ((rowIncrement < boardArray.length) && (ColumnIncrement < boardArray.length)
            && (empty(row, column, rowIncrement, ColumnIncrement)) && (boardArray[row][column].getShape())
            == boardArray[row + rowIncrement][column + ColumnIncrement].getShape()) {
            combo++;
            rowIncrement = rowIncrement + a;
            ColumnIncrement = ColumnIncrement +b;
        }
        return combo;
    }

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
     * This metod checks if
     *
     * @return
     *
     * Onderdeel van Combo methodes. Gebruikt isLeeg methode om te controleren of er blokken aanwezig zijn om een combo
     * te vormen.
     * */

    public boolean empty(int row, int column, int rowIncrement, int columnIncrement) {
        return ((!isEmpty(new Position(row,column))))
                && ((!isEmpty(new Position(row + rowIncrement, column + columnIncrement))));

    }
}
