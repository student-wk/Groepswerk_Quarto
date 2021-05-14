package Quarto.Model;

import java.io.Serializable;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class Speler implements Serializable {
    private String naam;
    private int score;

    public Speler(String naam) {
        this.naam = naam;
    }

    public Speler(String naam, int score) {
        this.naam = naam;
        this.score = score;
    }

    public String getNaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.naam.toString();
    }
}
