package Quarto.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;
    private AlleSpelers alleSpelers;
    private SpelerRanking spelerRanking;
    private boolean gameFinished;
    private boolean flipAction;

    public boolean isGameFinished() {
        return gameFinished;
    }

    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
    }

    public Quarto(Speler player1, Speler player2) throws QuartoException, IOException {
        this.setPlayers(player1.getNaam(), player2.getNaam());
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
        this.spelerRanking = new SpelerRanking();
    }

    public void setPlayers(String speler1, String speler2) throws QuartoException {
        if (speler1.isEmpty() || speler2.isEmpty()) {
            throw new QuartoException("Enter name for both players");
        } else {
            this.alleSpelers = new AlleSpelers(new Speler(speler1, 0), new Speler(speler2, 0));
        }
    }

    /*
     * Geeft telkens een specifieke blok aan gekozenBlok.
     * */

    public void kiesBlok(Blok blok) throws QuartoException {

        if (gekozenBlok != null) {
            throw new QuartoException("Er is al een blok gekozen.");
        } else {
            this.gekozenBlok = blok;
            blokkenBox.neemBlok(blok);
            alleSpelers.afwisselen();
            System.out.println("actieve speler: " + this.getAlleSpelers().getActieveSpeler());
            flipAction = true;
        }
    }

    /*
     * Plaatst blok op speelBord.
     * */

    public void plaatsBlok(Positie positie) throws QuartoException {
        if (this.gekozenBlok == null) {
            throw new QuartoException("Er is geen blok geselecteerd.");
        } else {
            speelbord.voegBlokToe(gekozenBlok, positie);
            flipAction = false;
            if (spelGedaan()) {
                gameFinished = true;
            }
//            this.gekozenBlok = null;
        }
    }

    public boolean isFlipAction() {
        return flipAction;
    }

    public boolean spelGedaan() {
        return (speelbord.isVol() || speelbord.heeftCombinatie());
    }

    public AlleSpelers getAlleSpelers() {
        return alleSpelers;
    }

    public BlokkenBox getBlokkenBox() {
        return blokkenBox;
    }

    public Speelbord getSpeelbord() {
        return speelbord;
    }

    public Blok getGekozenBlok() {
        if (gekozenBlok == null) {
            throw new IllegalStateException("Er is geen blok geselecteerd.");
        } else {
            return gekozenBlok;
        }
    }

    public void kieSpeler() {
        alleSpelers.kiesSpeler();
        System.out.println("actieve speler: " + this.getAlleSpelers().getActieveSpeler());

    }

    public void setGekozenBlok(Blok gekozenBlok) {
        this.gekozenBlok = gekozenBlok;
    }

    public SpelerRanking getSpelerRanking() {
        return spelerRanking;
    }
}

