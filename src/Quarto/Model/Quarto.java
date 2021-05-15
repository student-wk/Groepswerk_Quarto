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

    public  void setPlayers(String speler1, String speler2)  throws QuartoException {
        if (speler1.isEmpty() || speler2.isEmpty()) {
            throw new QuartoException("Enter name for both players");
        } else {
        this.alleSpelers = new AlleSpelers(new Speler(speler1, 0), new Speler(speler2,0));}
    }

/*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void kiesBlok(Blok blok) throws QuartoException{

        if (gekozenBlok != null) {
            throw new QuartoException("Er is al een blok gekozen.");
        } else {
            this.gekozenBlok = blok;
            blokkenBox.neemBlok(blok);
            alleSpelers.afwisselen();
            System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());
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
//            this.gekozenBlok = null;
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
            throw new IllegalStateException("Er is geen blok geselecteerd.");
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

    public void setGekozenBlok(Blok gekozenBlok) {
        this.gekozenBlok = gekozenBlok;
    }
}
