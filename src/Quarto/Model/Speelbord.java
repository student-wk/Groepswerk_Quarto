package Quarto.Model;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:38
 */
public class Speelbord {
    private Blok[][] blokkenOpBord;
    public static final int GROOTTE = 4;

    //mss nog toString methode maken van Speelbord? om uit te testen fzo?

    public Speelbord() {
        this.blokkenOpBord = new Blok[GROOTTE][GROOTTE];
    }

    /*
    * Constructor met blokkenOpBord als parameter. (om Speelbord te updaten denk ik.)
    * */

    public Speelbord(Blok[][] blokkenOpBord) {this.blokkenOpBord = blokkenOpBord; }

    /*
    * Checkt of de positie op het bord leeg is (true).
    * */

    public boolean isLeeg(Positie positie) {
        return(blokkenOpBord[positie.getRij()][positie.getKollom()]) == null;
    }

    /*
    * Voegt blok aan speelbord toe.
    * */

    public void voegBlokToe(Blok blok, Positie positie) throws QuartoException {
        if (isLeeg(positie) == false) {
            throw new QuartoException("Positie is niet vrij.");
        } else {
            blokkenOpBord[positie.getRij()][positie.getKollom()] = blok;
        }
    }

    /*
    * Als er een positie op het bord leeg is, geeft deze methode "false" terug.
    * heeftCombinatie() komt samen met isVol() in spelGedaan() in de Quarto klasse.
    * */

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

/*
* De combo methodes worden hier samengevoegd en op alle mogelijke manieren diagonaal overlopen. Als er een combo van 4
* is (grootte-1) dan is de boolean true. RijIncrement en KolomIncrement is eigenlijk telkens rij +1 en kolom +1.
* diagonaal() en rijKolom() komen samen in heeftCombinatie().
* */

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

/*
* Gaat alle rijen en kolomen af op zoek naar een winnende combo.
* diagonaal() en rijKolom() komen samen in heeftCombinatie().
* */

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

    /*
    * Onderdeel van Combo methodes. Gebruikt isLeeg methode om te controleren of er blokken aanwezig zijn om een combo
    * te vormen.
    * */

    public boolean leeg(int rij, int kollom, int rijIncrement, int kolomIncrement) throws QuartoException /*weet ni of exception werkt/nodig is*/ {
        return ((!isLeeg(new Positie(rij,kollom))))
                && ((!isLeeg(new Positie(rij + rijIncrement, kollom + kolomIncrement))));

    }

    /*
    * De combo methodes zijn een onderdeel van de diagonaal en rijKolom methode. De methode zoekt naar een combinatie
    * en checkt daarbij of de loop binnen het bord blijft, of er een positie leeg is en of de opeenvolgende blokken
    * dezelfde eigenschap hebben (bv dezelfde kleur).
    * De methode geeft een int met de hoogste combo terug.
    * */

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

/*
* heeftCombinatie() komt samen met isVol() in spelGedaan() in de Quarto klasse.
* */

    public boolean heeftCombinatie() throws QuartoException { return (rijKolom() || diagonaal());}

    public Blok[][] getBlokkenOpBord() {
        return blokkenOpBord;
    }
}
