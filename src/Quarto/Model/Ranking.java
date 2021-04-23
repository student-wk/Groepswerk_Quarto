package Quarto.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:40
 */
public class Ranking {
    private List<Speler> ranglijst = new ArrayList<>();

// NOG DOEN!!

    public void updateRanking (Speler speler) {
        // checken of speler al in ranglijst zit (alleen via naam)
        ranglijst.add(speler);
        // als speler al in ranglijst zat (alleen via naam checken), de score updaten (score van ranking + score laatste spel)
    }

    public void orderRanking() {

    }

    @Override
    public String toString() {
     return ranglijst.toString();
    }
}
