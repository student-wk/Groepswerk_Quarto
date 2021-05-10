package Quarto.Model;

import java.util.Scanner;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;
    private AlleSpelers alleSpelers;
    Speler speler1 = new Speler("Delawar", 0);
    Speler speler2 = new Speler("Willem", 0);
    private static int count = 0;


    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
        this.alleSpelers = new AlleSpelers(speler1, speler2);
        this.alleSpelers.kiesSpeler();
        System.out.println();
        System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());

    }

/*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void kiesBlok(Blok blok) {
        try {
            if (gekozenBlok != null) {
                throw new IllegalStateException("Er is al een blok als gekozenBlok geselecteerd.");
            } else {
                this.gekozenBlok = blok;
                blokkenBox.neemBlok(blok);
                alleSpelers.afwisselen(count++);
            }

        } catch (QuartoException ex) {
            System.out.println("error: " + ex.getMessage());
        }
    }

/*
* Plaatst blok op speelBord.
* */

    public void plaatsBlok(Positie positie) throws QuartoException {
        if (this.gekozenBlok == null) {
            throw new IllegalStateException("Er is geen gekozenBlok geselecteerd.");
        } else {
            speelbord.voegBlokToe(gekozenBlok, positie);
            this.gekozenBlok = null;
        }
    }

    public boolean spelGedaan() {
        return (speelbord.isVol() || speelbord.heeftCombinatie());
    }

    //            alleSpelers.afwisselen(count++);
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
            throw new IllegalStateException("Er is geen gekozenBlok geselecteerd.");
        } else {
            return gekozenBlok;
        }
    }

}
