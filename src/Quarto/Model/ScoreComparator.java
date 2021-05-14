package Quarto.Model;

import java.util.Comparator;

/**
 * @author Willem Kuijpers
 * @version 1.0 14-5-2021 12:07
 */
public class ScoreComparator implements Comparator<Speler> {

    public int compare(Speler speler1,Speler speler2) {
        int score1 = speler1.getScore();
        int score2 = speler2.getScore();

        if (score1 > score2) {
            return -1;
        } else if (score1 < score2) {
            return +1;
        } else {
            return 0;
        }
    }


}
