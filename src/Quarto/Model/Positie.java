package Quarto.Model;

/**
 * @author Willem Kuijpers
 * @version 1.0 23-4-2021 10:26
 */
public class Positie {
    private final int rij;
    private final int kollom;

    public Positie(int rij, int kollom) throws QuartoException{
        if ((rij<Speelbord.GROOTTE) && (rij>=0) && (kollom<Speelbord.GROOTTE) && (kollom>=0)) {
            this.rij = rij;
            this.kollom = kollom;
        } else {
            throw new QuartoException("Positie ligt buiten het bereik van het speelbord."); //normaal illigalargumentexception maar dat lukt niet
        }
    }

    public int getRij() {
        return rij;
    }

    public int getKollom() {
        return kollom;
    }
}
