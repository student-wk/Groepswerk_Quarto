package Quarto.Model;

import java.util.Random;

/**
 * @author Willem Kuijpers
 * @version 1.0 24-4-2021 14:26
 */
public class AlleSpelers {

    private Speler speler1;
    private Speler speler2;
    private Speler actieveSpeler;
    private final int random = new Random().nextInt(2) + 1;

    public AlleSpelers(Speler speler1, Speler speler2) {
        this.speler1 = speler1;
        this.speler2 = speler2;
        actieveSpeler = null;
    }

/*
* Kiest in het begin van het spel een random speler die mag beginnen.
* */

    public Speler kiesSpeler() {
        if (random == 1) {
            actieveSpeler = speler1;
        } else {
            actieveSpeler = speler2;
        }
        return actieveSpeler;
    }

/*
* i gaat beginnen met random en gaat na iedere ronde omhoog in spelverloop met i++, een oneven getal is telkens
* actieveSpeler = speler1, even is speler2.
* */

    public Speler afwisselen() {

        if (actieveSpeler == speler1) {
            actieveSpeler = speler2;
        } else if (actieveSpeler == speler2){
            actieveSpeler = speler1;
        }
        return actieveSpeler;
    }

    public Speler getSpeler1() {
        return speler1;
    }

    public Speler getSpeler2() {
        return speler2;
    }

    public Speler getActieveSpeler() {
        return actieveSpeler;
    }

    public Speler getNietActieveSpeler() {
        if (actieveSpeler == speler1) {
        return speler2;
        } else return speler1;
    }

    public int getRandom() {
        return random;
    }

    public void setSpeler1(Speler speler1) {
        this.speler1 = speler1;
    }

    public void setSpeler2(Speler speler2) {
        this.speler2 = speler2;
    }
}
