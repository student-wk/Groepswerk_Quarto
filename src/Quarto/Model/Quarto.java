package Quarto.Model;

import java.util.Scanner;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;
    private AlleSpelers alleSpelers;
//    private Speler speler1;
//    private Speler speler2;

//    Speler speler1 = new Speler("Delawar", 0);
//    Speler speler2 = new Speler("Willem", 0);
    private static int count = 0;


    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;

//        this.alleSpelers.kiesSpeler();
//        System.out.println();
//        System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());

    }

    public  void setPlayers(String speler1, String speler2) {
        this.alleSpelers = new AlleSpelers(new Speler(speler1, 0), new Speler(speler2,0));
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

                System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());

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

//    public void setSpeler1(Speler speler1) {
//        this.speler1 = speler1;
//    }
//
//    public void setSpeler2(Speler speler2) {
//        this.speler2 = speler2;
//    }

    public void kieSpeler(){
        alleSpelers.kiesSpeler();
        System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());

    }
}
