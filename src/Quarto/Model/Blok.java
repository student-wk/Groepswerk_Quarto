package Quarto.Model;

import java.util.Objects;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */

//githu bdsfdfd
public class Blok {

    public void testGithub(){

    }

    public enum Grootte {
        KLEIN,GROOT;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Kleur {
        ZWART,WIT;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Vorm {
        ROND,VIERKANT;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    public enum Vulling {
        HOL,VOL;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private final Grootte grootte;
    private final Kleur kleur;
    private final Vorm vorm;
    private final Vulling vulling;


    public Blok(Grootte grootte, Kleur kleur, Vorm vorm, Vulling vulling) {
        if ((grootte != null) && (kleur != null) && (vorm != null) && (vulling != null)) {
            this.grootte = grootte;
            this.kleur = kleur;
            this.vorm = vorm;
            this.vulling = vulling;
        } else {
            throw new IllegalArgumentException("Blok is incorrect aangemaakt");
        }
    }

    public Grootte getGrootte() {
        return grootte;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public Vorm getVorm() {
        return vorm;
    }

    public Vulling getVulling() {
        return vulling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blok blok = (Blok) o;
        return grootte == blok.grootte && kleur == blok.kleur && vorm == blok.vorm && vulling == blok.vulling;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grootte, kleur, vorm, vulling);
    }

    @Override
    public String toString() {
        return grootte + " " + kleur + " " + vorm  + " " + vulling;
    }
}
