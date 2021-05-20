package Quarto.Model;

import java.io.IOException;

/**
 * @author Willem Kuijpers
 * @version 1.0 24-4-2021 14:43
 */
public class TestKlasse {

    /*    public static void main(String[] args) throws IOException {

            Quarto testQuarto = new Quarto();
            Speler speler1 = new Speler("Jos", 0);
            Speler speler2 = new Speler("Freya", 0);
            AlleSpelers testSpelers = new AlleSpelers(speler1, speler2);
            testQuarto.kiesBlok(8);

            System.out.println(testQuarto.getBlokkenBox() + "\n");
            testQuarto.kiesBlok(10);

            while (testQuarto.spelGedaan() == false) {
                testQuarto.spelRonde();
                if (testQuarto.spelGedaan() == true) {
                    System.out.println("Het spel is gedaan.");
                    System.out.println(testQuarto.getBlokkenBox() + "\n");
                    if (testQuarto.getSpeelbord().isVol() == true) {
                        System.out.println("Het is een gelijkspel.");
                    } else if (testQuarto.getSpeelbord().heeftCombinatie() == true) {
                        System.out.println("Een speler heeft gewonnen.");
                    } else {
                        System.out.println("Er klopt iets niet.");
                    }
                }
            }*/
    public static void main(String[] args) {


        Speler joske = new Speler("Joske");
        Speler jefke = new Speler("Jefke");

        SpelerRanking ranking = new SpelerRanking();

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking()); //niks


        try {
            ranking.addScoreWinningPlayer(joske);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 1 jefke 0

        Speler jo = new Speler("Joske");
        Speler je = new Speler("Jefke");

        try {
            ranking.addScoreWinningPlayer(jo);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(je);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 2 jefke 0

        Speler j = new Speler("Jefke");
        Speler frans = new Speler("Frans",10);

        try {
            ranking.addScoreWinningPlayer(j);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 2 jefke 1 frans 0

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//frans 4 jefke 3 joske 2

        try {
            ranking.clearRankingFile();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//niks


    }
}
