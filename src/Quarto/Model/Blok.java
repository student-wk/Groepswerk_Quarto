package Quarto.Model;

import java.util.Objects;

/**
 * @author Willem Kuijpers
 * @version 1.0 27-2-2021 17:39
 */
public class Blok {

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

    private  Grootte grootte;
    private  Kleur kleur;
    private  Vorm vorm;
    private  Vulling vulling;


    public Blok(){
    }

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

    public void setGrootte(Grootte grootte) {
        this.grootte = grootte;
    }

    public void setKleur(Kleur kleur) {
        this.kleur = kleur;
    }

    public void setVorm(Vorm vorm) {
        this.vorm = vorm;
    }

    public void setVulling(Vulling vulling) {
        this.vulling = vulling;
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

/*
* De blokString methode is een combinatie van telkens de eerste letter van de enums, bv kzvV.
* De vulling (hol,vol) is met hoofdletter weergegeven om verwarring met vorm (rond, Vierkant) te voorkomen.
* De methode dient om de blokken duidelijk op het speelbord te kunnen weergeven.
* */

    public String blokString() {
        return grootte.toString().substring(0,1) + kleur.toString().substring(0,1)
                + vorm.toString().substring(0,1) + vulling.toString().substring(0,1).toUpperCase();
    }

    @Override
    public String toString() {
        return ("blok" + "|" + grootte + "|" + kleur + "|" + vorm + "|" + vulling);
    }
}
