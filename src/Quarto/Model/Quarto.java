package Quarto.Model;

import java.io.IOException;
import java.util.Scanner;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;
    private AlleSpelers alleSpelers;
    private boolean gameFinished;
    private boolean flipAction;

    private SpelerRanking spelerRanking;
    private AnimationFileHandler animationFileHandler;


    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
        this.spelerRanking = new SpelerRanking();
        animationFileHandler = new AnimationFileHandler();
    }

    public Quarto(Speler player1, Speler player2) throws QuartoException, IOException {
        this.setPlayers(player1.getNaam(), player2.getNaam());
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
        this.spelerRanking = new SpelerRanking();
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public  void setPlayers(String speler1, String speler2) throws QuartoException, IOException {
        if (speler1.isEmpty() || speler2.isEmpty()) {
            throw new QuartoException("Enter name for both players");
        } else {
        this.alleSpelers = new AlleSpelers(new Speler(speler1, 0), new Speler(speler2,0));}
    }


    /*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void kiesBlok(Blok blok) throws QuartoException, IOException {

        if (gekozenBlok != null) {
            throw new QuartoException("Er is al een blok gekozen.");
        } else {
            this.gekozenBlok = blok;
            blokkenBox.neemBlok(blok);
            alleSpelers.afwisselen();
            this.animationFileHandler.addAction(blok.toString());
            System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());
            flipAction = true;
        }
    }

    /*
* Plaatst blok op speelBord.
* */

    public void plaatsBlok(Positie positie) throws QuartoException, IOException {
        if (this.gekozenBlok == null) {
            throw new QuartoException("Er is geen blok geselecteerd.");
        } else {
            speelbord.voegBlokToe(gekozenBlok, positie);
            this.animationFileHandler.addAction(positie.toString());

            flipAction = false;
             if (spelGedaan()) {
                 gameFinished = true;
                 if (speelbord.isVol()) {
                     this.animationFileHandler.addAction("gamefinished"+"|"+"vol");
                 } else {
                     this.animationFileHandler.addAction("gamefinished"+"|"+"won");
                 }

                 animationFileHandler.printout();

                 updateRanking();
             }
//            this.gekozenBlok = null;
        }
    }
    public void updateRanking() throws QuartoException {
        if (this.speelbord.heeftCombinatie()) {

            this.spelerRanking.addScoreWinningPlayer(this.alleSpelers.getActieveSpeler());
            this.spelerRanking.addScoreLosingPlayer(this.alleSpelers.getNietActieveSpeler());
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
//        if (gekozenBlok == null) {
//            throw new IllegalStateException("Er is geen blok geselecteerd.");
//        } else {
//            return gekozenBlok;
//        }
        return gekozenBlok;
    }

    public SpelerRanking getSpelerRanking() {
        return spelerRanking;
    }

    public void kieSpeler() throws IOException {
        int indexChosenPlayer = alleSpelers.kiesSpeler();

        this.animationFileHandler.initiateFile("oneVone"+"|"+ alleSpelers.getSpeler1().getNaam() + "|"+ alleSpelers.getSpeler2().getNaam() + "|" + indexChosenPlayer );

        System.out.println("actieve speler: "+ this.getAlleSpelers().getActieveSpeler());

    }

    public void setGekozenBlok(Blok gekozenBlok) {
        this.gekozenBlok = gekozenBlok;
    }

    public void setSpelerRanking(SpelerRanking spelerRanking) {
        this.spelerRanking = spelerRanking;
    }

    public void setPlayerForAnimation() throws IOException, QuartoException {
        System.out.println(animationFileHandler.getAction());
        String player1FromAnimation = animationFileHandler.getAction().split("\\|")[1];
        String player2FromAnimation = animationFileHandler.getAction().split("\\|")[2];
        this.setPlayers(player1FromAnimation,player2FromAnimation);
        this.alleSpelers.setActieveSpeler(Integer.parseInt(animationFileHandler.getAction().split("\\|")[3]));
    }
}
