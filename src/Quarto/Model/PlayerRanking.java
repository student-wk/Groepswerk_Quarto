package Quarto.Model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class creates a PlayerRanking object which can be used to create, edit and clear a ranking of Players.
 *
 * @author Willem Kuijpers
 * @version 1.0 14-5-2021 12:10
 */
public class PlayerRanking {

    private ArrayList<Player> highScoresRanking;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Path Highscore_File = Paths.get("resources"+FILE_SEPARATOR+"ranking"+FILE_SEPARATOR+"ranking.bin");
    private String HIGHSCORE_FILE = Highscore_File.toString();


    /**
     * The constructor contains an arraylist of Players.
     * */

    public PlayerRanking() {
        highScoresRanking = new ArrayList<>();
    }

    /**
     * This method adds the player who won to the arraylist and then the source file.
     * It does so for a new player or a player who already exists in the source file.
     * A winning player gets +1 to their original score.
     * The method also sorts the list by score (high to low).
     *
     * @param player the player who won the game.
     * @throws QuartoException of methods binFile2List and list2BinFile.
     * */

    public void addScoreWinningPlayer(Player player) throws QuartoException {
        String naam = player.getName();
        int score;

        highScoresRanking = PlayerFileHandler.binFile2List(HIGHSCORE_FILE);

        if (highScoresRanking.contains(player)) {
            score = highScoresRanking.get(highScoresRanking.indexOf(player)).getScore();
            score += 1;
            highScoresRanking.remove(player);
            highScoresRanking.add(new Player(naam, score));

        } else {
            score = player.getScore();
            score += 1;
            highScoresRanking.add(new Player(naam, score));
        }
        Collections.sort(highScoresRanking);
        PlayerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    /**
     * This method adds the player who lost to the arraylist and then the source file.
     * It does so for a new player or a player who already exists in the source file.
     * A winning player gets -1 to their original score.
     * The score can't go below 0.
     * The method also sorts the list by score (high to low).
     *
     * @param player the player who won the game.
     * @throws QuartoException of methods binFile2List and list2BinFile.
     * */

    public void addScoreLosingPlayer(Player player) throws QuartoException {
        String naam = player.getName();
        int score;

        highScoresRanking = PlayerFileHandler.binFile2List(HIGHSCORE_FILE);

        if (highScoresRanking.contains(player)) {
            score = highScoresRanking.get(highScoresRanking.indexOf(player)).getScore();
            score -= 1;
            if (score<=0) {
                score = 0;
            }
            highScoresRanking.remove(player);
            highScoresRanking.add(new Player(naam, score));

        } else {
            score = 0;
            highScoresRanking.add(new Player(naam, score));
        }
        Collections.sort(highScoresRanking);
        PlayerFileHandler.list2BinFile(highScoresRanking,HIGHSCORE_FILE);
    }

    /**
     * This method adds the data of the source file to the PlayerRanking object.
     *
     * @throws QuartoException of the method binFile2List.
     * */

    public void scoreFile2List() throws QuartoException {
        ArrayList<Player> highScoresRanking = PlayerFileHandler.binFile2List(HIGHSCORE_FILE);
        this.highScoresRanking = highScoresRanking;
    }

    /**
     * This method deletes all the highscores.
     *
     * @throws QuartoException of the method list2BinFile.
     * */

    public void clearRankingFile() throws QuartoException {
        this.highScoresRanking.clear();
        PlayerFileHandler.list2BinFile(this.highScoresRanking,HIGHSCORE_FILE);
    }

    public ArrayList<Player> getHighScoresRanking() {
        return highScoresRanking;
    }
}
