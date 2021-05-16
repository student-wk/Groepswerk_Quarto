package Quarto.Model;

import java.io.IOException;

/**
 * @author Willem Kuijpers
 * @version 1.0 24-4-2021 14:43
 */
public class TestKlasse {

    public static void main(String[] args) {

/*        Quarto testQuarto = new Quarto();
        Speler speler1 = new Speler("Jos", 0);
        Speler speler2 = new Speler("Freya", 0);
        AlleSpelers testSpelers = new AlleSpelers(speler1, speler2);
//        testQuarto.kiesBlok(8);

        System.out.println(testQuarto.getBlokkenBox() + "\n");
//        testQuarto.kiesBlok(10);

//        while (testQuarto.spelGedaan() == false) {
//            testQuarto.spelRonde();
//            if (testQuarto.spelGedaan() == true) {
//                System.out.println("Het spel is gedaan.");
//                System.out.println(testQuarto.getBlokkenBox() + "\n");
//                if (testQuarto.getSpeelbord().isVol() == true) {
//                    System.out.println("Het is een gelijkspel.");
//                } else if (testQuarto.getSpeelbord().heeftCombinatie() == true) {
//                    System.out.println("Een speler heeft gewonnen.");
//                } else {
//                    System.out.println("Er klopt iets niet.");
//                }
//            }
//        }

        boolean exist = testQuarto.getBlokkenBox().getBlokkenSet().contains(new Blok(Blok.Grootte.KLEIN, Blok.Kleur.WIT, Blok.Vorm.VIERKANT, Blok.Vulling.VOL));

        System.out.println(exist);*/

        Speler joske = new Speler("Joske");
        Speler jefke = new Speler("Jefke",5);

        SpelerRanking ranking = new SpelerRanking();

        try {
            ranking.addScoreWinningPlayer(joske);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(jefke);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ranking);

        Speler jo = new Speler("Joske",8);
        Speler je = new Speler("Jefke",5);

        SpelerRanking ranking2 = new SpelerRanking();

        try {
            ranking2.addScoreWinningPlayer(joske);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ranking2.addScoreLosingPlayer(jefke);
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println(ranking);


    }
}
