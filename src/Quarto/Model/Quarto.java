package Quarto.Model;

public class Quarto {
    private Blok[] gegevenBlok;

    private Speler speler1;
    private Speler speler2;
    private Speelbord speelbord;
    private BlokkenBox blokkenBox;

// DEZE KLASSE NOG UITWERKEN

    public Quarto() {

        speler1 = new Speler("speler1",0);
        speler2 = new Speler("speler2",0);
        speelbord = new Speelbord();
        blokkenBox = new BlokkenBox();
        gegevenBlok = new Blok[1];

        // constructor van het spel, bevat objecten van de andere klassen
    }

    public void Start() {
        // eerst namen spelers vragen
        // eerste speler loten
        // spelverloop hierin plaatsen
        // met while loop doorgaan zolang niemand gewonnen is en er nog zetten mogelijk zijn (dus nog geen gelijkspel)
        // na spelverloop de score aan spelers toewijzen resultaten wegschrijven in de ranking
    }


}
