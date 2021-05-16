package Quarto.Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.logging.FileHandler;

/**
 * @author Willem Kuijpers
 * @version 1.0 14-5-2021 12:10
 */
public class SpelerRanking extends ArrayList {

    private ArrayList<Speler> highScoresRanking;

    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Path Highscore_File = Paths.get("resources"+FILE_SEPARATOR+"ranking"+FILE_SEPARATOR+"ranking.bin");
    private String HIGHSCORE_FILE = pathToString();


    public SpelerRanking() throws IOException {
        this.highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
    }


    public void addScoreWinningPlayer(Speler speler) throws IOException { //EXCEPTION NOG OPVANGEN!
        String naam = speler.getNaam();
        int score = speler.getScore();
        score += 1;

        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
        // de bestaande speler wordt eerst uit de lijst verwijderd, en daarna met die nieuwe score toegevoegd.
        if (highScoresRanking.contains(speler)) {
            highScoresRanking.remove(speler);
        }
        highScoresRanking.add(new Speler(naam, score));
        Collections.sort(highScoresRanking);
        SpelerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    public void addScoreLosingPlayer(Speler speler) throws IOException { //EXCEPTION NOG OPVANGEN!
        String naam = speler.getNaam();
        int score = speler.getScore();
        score -= 1;

        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
        // de bestaande speler wordt eerst uit de lijst verwijderd, en daarna met die nieuwe score toegevoegd.
        if (highScoresRanking.contains(speler)) {
            highScoresRanking.remove(speler);
        }
        highScoresRanking.add(new Speler(naam, score));
        Collections.sort(highScoresRanking);
        SpelerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    public String pathToString() {
        return Highscore_File.toString();
    }

/*    public void showRanking () throws IOException {
        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
    }*/

 /*   public ArrayList<Speler> getHighScoresRanking() throws IOException {
        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
        return highScoresRanking;
    }*/

    public ArrayList<Speler> getHighScoresRanking() throws IOException {
        highScoresRanking = SpelerFileHandler.binFile2List(HIGHSCORE_FILE);
        return highScoresRanking;
    }


}
