package Quarto.Model;

import java.util.Scanner;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;

    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
    }

/*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void kiesBlok(Blok blok) {
        System.out.println("dsdsdsa");
        try {
            if (gekozenBlok != null) {
                throw new IllegalStateException("Er is al een blok als gekozenBlok geselecteerd.");
            } else {
                this.gekozenBlok = blok;
                blokkenBox.neemBlok(blok);
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

    /*
    * Methode die 1 spelronde volledig afwerkt. Moet nog aangepast worden als de view en presenter worden uitgewerkt.
    * Ik weet nog niet zeker of hier al bepaald moet worden welke speler telkens een actie uitvoert, dat is mss iets
    * voor in de presenter te doen?
    * Er wordt hier geen winnaar/gelijkspel gecheckt en er worden ook geen spelers aangemaakt.
    * */

//    public void spelRonde() {
//// speelbord afprinten
//        System.out.println(getSpeelbord() + "\n");
//// blokkenbox afprinten
//        System.out.println(getBlokkenBox() + "\n");
//// actieve speler het blok laten kiezen
//        System.out.println("Kies een blok"  + "\n");
//        Scanner keyboard = new Scanner(System.in);
//        int i = keyboard.nextInt();
//        this.kiesBlok(i);
//// gekozen blok afprinten
//        System.out.println("Gekozen blok is: " + this.getGekozenBlok() + "\n");
//// niet actieve speler het blok op het bord laten plaatsen
//        System.out.println("Kies een positie om het blok te plaatsen."  + "\n");
//        System.out.println("Kies eerst een rij:"  + "\n");
//        int rij = keyboard.nextInt();
//        System.out.println("Kies nu een kolom:"  + "\n");
//        int kolom = keyboard.nextInt();
//        Positie positie = new Positie(rij,kolom);
//        try { this.plaatsBlok(positie);
//        } catch (QuartoException e) {
//            e.printStackTrace();
//        }
//    }

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
