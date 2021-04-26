package Quarto.Model;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class Speler {
    private String naam;
    private int score;

    //NOG DOEN!!

    public Speler(String naam, int score) {
        this.naam = naam;
        this.score = score;
    }

    public void vraagNaam() {
        //generische naam "speler1", "speler2" overschrijven
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
