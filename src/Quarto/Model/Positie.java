package Quarto.Model;

import java.util.StringJoiner;

/**
 * @author Willem Kuijpers
 * @version 1.0 23-4-2021 10:26
 */

/*
* Klasse om de positie van blokken op het speelbord te bepalen.
* */

public class Positie {
    private final int rij;
    private final int kollom;

    public Positie(int rij, int kollom) {
        if ((rij<Speelbord.GROOTTE) && (rij>=0) && (kollom<Speelbord.GROOTTE) && (kollom>=0)) {
            this.rij = rij;
            this.kollom = kollom;
        } else {
            throw new IllegalArgumentException("Positie ligt buiten het bereik van het speelbord.");
        }
    }

    public int getRij() {
        return rij;
    }

    public int getKollom() {
        return kollom;
    }

    @Override
    public String toString() {
        return "positie" + "|" + rij + "|"+ kollom;
    }
}
