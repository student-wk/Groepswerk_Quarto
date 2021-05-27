package Quarto.Model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class creates the pieces and puts them in a collection from which you can take a single piece at a time.
 *
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */

public class Pieces {
    private final Set<Piece> pieceSet;

    public Pieces() {
        this.pieceSet = new LinkedHashSet<>();
        makePieces();
    }

    /**
     * This method uses the different attributes of the class Piece to create 16 unique pieces and puts them in pieceSet.
     * */

    public void makePieces() {
        for (Piece.Size size : Piece.Size.values()) {
            for (Piece.Color color : Piece.Color.values()) {
                for (Piece.Shape shape : Piece.Shape.values()) {
                    for (Piece.Filling filling : Piece.Filling.values()) {
                        Piece piece = new Piece(size, color, shape, filling);
                        pieceSet.add(piece);
                    }
                }
            }
        }
    }

/**
 * This method deletes a piece from pieceSet.
 *
 *
 * @param piece the specific piece you want to delete.
* */

    public void takePiece(Piece piece) throws QuartoException {
        if (!pieceSet.contains(piece)) {
            throw new QuartoException("Er is geen geldige blok geselecteerd.");
        } else {
            pieceSet.remove(piece);
        }
    }

    public Set<Piece> getPieceSet() {
        return Pieces.this.pieceSet;
    }

    @Override
    public String toString() {
        String result = "";
        int i = 1;
        for (Piece piece :
             this.pieceSet) {
            result = result + "\n" + (i++) + "\t" + piece;
        }
        return result;
    }
}
