package Quarto.Model;

public class Quarto {
    private final BlokkenBox blokkenBox;
    private final Speelbord speelbord;
    private Blok gekozenBlok;

    public Quarto() {
        this.blokkenBox = new BlokkenBox();
        this.speelbord = new Speelbord();
        this.gekozenBlok = null;
    }

/*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void kiesBlok(Blok blok) {
        try {
            if (gekozenBlok != null) {
                throw new IllegalStateException("Er is al een blok als gekozenBlok geselecteerd.");
            } else {
                this.gekozenBlok = blokkenBox.neemBlok(blok);
            }

        } catch (QuartoException ex) {
            System.out.println("error: " + ex.getMessage());
        }
    }

/*
* Plaatst blok op speelBord.
* */

    public void plaatsBlok(Positie positie) throws QuartoException {
        if (this.gekozenBlok == null) {
            throw new IllegalStateException("Er is geen gekozenBlok geselecteerd.");
        } else {
            speelbord.voegBlokToe(gekozenBlok, positie);
            this.gekozenBlok = null;
        }
    }

    public boolean spelGedaan() throws QuartoException {
        return (speelbord.isVol() || speelbord.heeftCombinatie());
    }

    public BlokkenBox getBlokkenBox() {
        return blokkenBox;
    }

    public Speelbord getSpeelbord() {
        return speelbord;
    }

    public Blok getGekozenBlok() {
        if (gekozenBlok == null) {
            throw new IllegalStateException("Er is geen gekozenBlok geselecteerd.");
        } else {
            return gekozenBlok;
        }
    }
}
