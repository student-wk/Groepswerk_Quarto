package Quarto.Model;

import java.util.Objects;

/**
 * The Player class contains all the attributes to make a player object for each player.
 * It also contains the code to compare players to each other.
 * Players get compared to each other via their score.
 *
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */

public class Player implements Comparable<Player> {
    private String name;
    private int score;

    /**
     * The constructor makes a player object.
     *
     * @param name the name of the player.
     * @param score the score of the player.
     * */

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return this.name + '\t' + score;
    }

    @Override
    public int compareTo(Player player1) {
        return ((Integer)(player1.getScore())).compareTo(getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
