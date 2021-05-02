package Quarto.Model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class BlokkenBox {
    private final Set<Blok> blokkenSet;

    public BlokkenBox() {
        this.blokkenSet = new LinkedHashSet<>();
        maakBlokken();
    }

    public void maakBlokken() {
        for (Blok.Grootte grootte : Blok.Grootte.values()) {
            for (Blok.Kleur kleur : Blok.Kleur.values()) {
                for (Blok.Vorm vorm : Blok.Vorm.values()) {
                    for (Blok.Vulling vulling : Blok.Vulling.values()) {
                        Blok blok = new Blok(grootte,kleur,vorm,vulling);
                        blokkenSet.add(blok);
                    }
                }
            }
        }
    }

/*
* Verwijdert een gekozen blok uit de blokkenSet en geeft die als return.
* Wordt gebruikt in Quarto.kiesBlok.
* */

    public Blok neemBlok(int index) throws QuartoException {
        if ((index-1 >= blokkenSet.size()) || (index < 1)) {
            throw new QuartoException("Er is geen geldige blok geselecteerd.");
        } else {
            Blok[] blokArray = new Blok[blokkenSet.size()];
            blokArray = blokkenSet.toArray(blokArray);
            blokkenSet.remove(blokArray[index-1]);
            return blokArray[index-1];
        }
    }

    public int aantalBlokken(BlokkenBox blokkenBox) {return (blokkenBox.blokkenSet.size());}

    public boolean isLeeg() {return blokkenSet.isEmpty();}

    public Set<Blok> getBlokkenSet() {
        return BlokkenBox.this.blokkenSet;
    }

    @Override
    public String toString() {
        String resultaat = "";
        int i = 1;
        for (Blok blok:
             this.blokkenSet) {
            resultaat = resultaat + "\n" + (i++) + "\t" + blok;
        }
        return resultaat;
    }
}
