package Quarto.Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

/**
 * @author Willem Kuijpers
 * @version 1.0 14-5-2021 12:10
 */
public class SpelerRanking {

    private ArrayList<Speler> highScoresRanking;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Path Highscore_File = Paths.get("resources"+FILE_SEPARATOR+"ranking"+FILE_SEPARATOR+"ranking.bin");
    private String HIGHSCORE_FILE = pathToString();


    public SpelerRanking() {
        highScoresRanking = new ArrayList<>();
    }

    public void scoreFile2List() throws QuartoException {
        ArrayList<Speler> highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
        this.highScoresRanking = highScoresRanking;
    }

    public void addScoreWinningPlayer(Speler speler) throws QuartoException { //EXCEPTION NOG OPVANGEN!
        String naam = speler.getNaam();
        int score;

        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);

        if (highScoresRanking.contains(speler)) {
            score = highScoresRanking.get(highScoresRanking.indexOf(speler)).getScore();
            score += 1;
            highScoresRanking.remove(speler);
            highScoresRanking.add(new Speler(naam, score));

        } else {
            score = speler.getScore();
            score += 1;
            highScoresRanking.add(new Speler(naam, score));
        }
        Collections.sort(highScoresRanking);
        SpelerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    public void addScoreLosingPlayer(Speler speler) throws QuartoException { //EXCEPTION NOG OPVANGEN!
        String naam = speler.getNaam();
        int score;

        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);

        if (highScoresRanking.contains(speler)) {
            score = highScoresRanking.get(highScoresRanking.indexOf(speler)).getScore();
            score -= 1;
            if (score<=0) {
                score = 0;
            }
            highScoresRanking.remove(speler);
            highScoresRanking.add(new Speler(naam, score));

        } else {
            score = 0;
            highScoresRanking.add(new Speler(naam, score));
        }
        Collections.sort(highScoresRanking);
        SpelerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    /*
    * reset de highscores.
    * */

    public void clearRankingFile() throws QuartoException {
        this.highScoresRanking.clear();
        SpelerFileHandler.list2BinFile(this.highScoresRanking,HIGHSCORE_FILE);
    }

    public String pathToString() {
        return Highscore_File.toString();
    }

    public ArrayList<Speler> getHighScoresRanking() {
        return highScoresRanking;
    }
}
