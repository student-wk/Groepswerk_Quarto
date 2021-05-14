package Quarto.Model;

import java.util.*;
import java.io.*;

/**
 * @author Willem Kuijpers
 * @version 1.0 14-5-2021 12:10
 */
public class HighScoreManager {

    private ArrayList<Speler> highScoresRanking;
    private static final String HIGHSCORE_FILE = "highScoresRanking.dat";

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighScoreManager() {
        highScoresRanking = new ArrayList<Speler>();
    }

    public ArrayList<Speler> getHighScores() {
        loadScoreFile();
        sort();
        return highScoresRanking;
    }

    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(highScoresRanking, comparator);
    }

    /*
    * Bij het aanmaken van het spel, wordt er spelers gekozen, als een speler nog niet bestaat,
    * wordt die aangemaakt, en krijgt die speler de score 0 mee.
    *
    * Ik moet denk een methode aanmaken voor als er een speler wint, en 1 voor de speler die verliest.
    * */

    public void addNewPlayer(Speler speler) {
        highScoresRanking.add(speler);
    }

    public void addScoreWinningPlayer(Speler speler) {
        // de bestaande speler wordt eerst uit de lijst verwijderd, en daarna met die nieuwe score toegevoegd.
        if(highScoresRanking.contains(speler)) {
            highScoresRanking.remove(speler);
        }
        int score = speler.getScore();
        score += 1;
        loadScoreFile();
        highScoresRanking.add(new Speler(speler.getNaam(), score));
        updateScoreFile();
    }

    public void addScoreLosingPlayer(Speler speler) {
        // de bestaande speler wordt eerst uit de lijst verwijderd, en daarna met die nieuwe score toegevoegd.
        if(highScoresRanking.contains(speler)) {
            highScoresRanking.remove(speler);
        }
        int score = speler.getScore();
        score -= 1;
        if (score < 0) {
            score = 0;
        }
        loadScoreFile();
        highScoresRanking.add(new Speler(speler.getNaam(), score));
        updateScoreFile();
    }


    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            highScoresRanking = (ArrayList<Speler>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }

    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(highScoresRanking);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    public String getHighscoreString() {
        String highscoreString = "";
        final int max = 10;

        ArrayList<Speler> highScoresRanking;
        highScoresRanking = getHighScores();

        int i = 0;
        int x = highScoresRanking.size();
        // dit stuk beperkt de ranking tot een top 10:
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + highScoresRanking.get(i).getNaam()
                    + "\t\t" + highScoresRanking.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }

    public void clearList() {
        highScoresRanking.clear();
    }
}
