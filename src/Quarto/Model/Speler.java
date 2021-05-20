package Quarto.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class Speler implements Comparable<Speler> {
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
        return this.naam + '\t' + score;
    }

    @Override
    public int compareTo(Speler speler1) {
        return ((Integer)(speler1.getScore())).compareTo(getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speler speler = (Speler) o;
        return naam.equals(speler.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    /*@Override
    public int compareTo(Speler anderespeler) {
        return this.score.compareTo(anderespeler.score);
    }*/
}
