package Quarto.Model;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:38
 */
public class Speelbord {
    private Blok[][] blokkenOpBord;
    public static final int GROOTTE = 4;

    public Speelbord() {
        this.blokkenOpBord = new Blok[GROOTTE][GROOTTE];
    }

    /*
    * Constructor met blokkenOpBord als parameter. (om Speelbord te updaten.)
    * */

    public Speelbord(Blok[][] blokkenOpBord) {this.blokkenOpBord = blokkenOpBord; }

    public boolean isLeeg(Positie positie) {
        return(blokkenOpBord[positie.getRij()][positie.getKollom()]) == null;
    }

    public void voegBlokToe(Blok blok, Positie positie) throws QuartoException {
        if (isLeeg(positie) == false) {
            throw new QuartoException("Positie is niet vrij.");
        } else {
            blokkenOpBord[positie.getRij()][positie.getKollom()] = blok;
        }
    }

//mss nog toString methode maken van Speelbord? om uit te testen fzo?

    public boolean isVol() {
        boolean isVol = true;
        int rij = 0;
        int kollom;
        while ((rij< blokkenOpBord.length) && (isVol)) {
            kollom = 0;
            while ((kollom< blokkenOpBord.length) && (isVol)) {
                if (blokkenOpBord[rij][kollom] == null) {
                    isVol = false;
                }
                kollom++;
            }
            rij++;
        }
        return isVol;
    }

    //snap ik nog ni helemaal
    public boolean diagonaal() throws QuartoException {
        int grootte = blokkenOpBord.length;
        return ((kleurenCombo(0,0,1,1) == grootte-1)
                || ((vormCombo(0,0,1,1) == grootte-1)
                || (grootteCombo(0,0,1,1) == grootte-1)
                || (vullingCombo(0,0,1,1) == grootte-1)
                || (kleurenCombo(0,grootte-1,1,-1) == grootte-1)
                || (vormCombo(0,grootte-1,1,-1) == grootte-1)
                || (grootteCombo(0,grootte-1,1,-1) == grootte-1)
                || (vullingCombo(0,grootte-1,1,-1) == grootte-1)));

    }

    //snap ik nog ni helemaal
    public boolean rijKolom() throws QuartoException {
        int i = 0;
        boolean gewonnen = false;
        while ((i < blokkenOpBord.length) && (!gewonnen)) {
            if ((kleurenCombo(i, 0, 0, 1) == blokkenOpBord.length -1)
                || (vormCombo(i, 0, 0, 1) == blokkenOpBord.length -1)
                || (grootteCombo(i, 0, 0, 1) == blokkenOpBord.length -1)
                || (vullingCombo(i, 0, 0, 1) == blokkenOpBord.length -1)
                || (kleurenCombo(0, i, 1, 0) == blokkenOpBord.length -1)
                || (vormCombo(0, i, 1, 0) == blokkenOpBord.length -1)
                || (grootteCombo(0, i, 1, 0) == blokkenOpBord.length -1)
                || (vullingCombo(0, i, 1, 0) == blokkenOpBord.length -1)) {
                gewonnen = true;
            }
            i++;
        }
        return gewonnen;
    }

    //snap ik nog ni helemaal
    public boolean leeg(int rij, int kollom, int rijIncrement, int kolomIncrement) throws QuartoException /*weet ni of exception werkt/nodig is*/ {
        return ((!isLeeg(new Positie(rij,kollom))))
                && ((!isLeeg(new Positie(rij + rijIncrement, kollom + kolomIncrement))));

    }

    //snap ik nog ni helemaal
    public int kleurenCombo(int rij, int kollom, int rijIncrement, int kolomIncrement) throws QuartoException {
        int combo = 0;
        int a = rijIncrement;
        int b = kolomIncrement;
        while ((rijIncrement < blokkenOpBord.length) && (kolomIncrement < blokkenOpBord.length)
            && (leeg(rij, kollom, rijIncrement, kolomIncrement)) && (blokkenOpBord[rij][kollom].getKleur())
            == blokkenOpBord[rij + rijIncrement][kollom + kolomIncrement].getKleur()) {
            combo++;
            rijIncrement = rijIncrement + a;
            kolomIncrement = kolomIncrement +b;
        }
        return combo;
    }

    public int vormCombo(int rij, int kollom, int richtingRij, int kolomIncrement) throws QuartoException {
        int combo = 0;
        int a = richtingRij;
        int b = kolomIncrement;
        while ((richtingRij < blokkenOpBord.length) && (kolomIncrement < blokkenOpBord.length)
            && (leeg(rij, kollom, richtingRij, kolomIncrement)) && (blokkenOpBord[rij][kollom].getVorm())
            == blokkenOpBord[rij + richtingRij][kollom + kolomIncrement].getVorm()) {
            combo++;
            richtingRij = richtingRij + a;
            kolomIncrement = kolomIncrement +b;
        }
        return combo;
    }

    public int grootteCombo(int rij, int kollom, int rijIncrement, int kolomIncrement) throws QuartoException {
        int combo = 0;
        int a = rijIncrement;
        int b = kolomIncrement;
        while ((rijIncrement < blokkenOpBord.length) && (kolomIncrement < blokkenOpBord.length)
            && (leeg(rij, kollom, rijIncrement, kolomIncrement)) && (blokkenOpBord[rij][kollom].getGrootte())
            == blokkenOpBord[rij + rijIncrement][kollom + kolomIncrement].getGrootte()) {
            combo++;
            rijIncrement = rijIncrement + a;
            kolomIncrement = kolomIncrement +b;
        }
        return combo;
    }

    public int vullingCombo(int rij, int kollom, int rijIncrement, int kolomIncrement) throws QuartoException {
        int combo = 0;
        int a = rijIncrement;
        int b = kolomIncrement;
        while ((rijIncrement < blokkenOpBord.length) && (kolomIncrement < blokkenOpBord.length)
            && (leeg(rij, kollom, rijIncrement, kolomIncrement)) && (blokkenOpBord[rij][kollom].getVulling())
            == blokkenOpBord[rij + rijIncrement][kollom + kolomIncrement].getVulling()) {
            combo++;
            rijIncrement = rijIncrement + a;
            kolomIncrement = kolomIncrement +b;
        }
        return combo;
    }

    public boolean heeftCombinatie() throws QuartoException { return (rijKolom() || diagonaal());}

    public Blok[][] getBlokkenOpBord() {
        return blokkenOpBord;
    }
}
