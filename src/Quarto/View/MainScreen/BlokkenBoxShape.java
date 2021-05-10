package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * @author Willem Kuijpers
 * @version 1.0 10-5-2021 11:09
 */
public class BlokkenBoxShape extends Shape {

    /*
    * Heb deze klasse vorige week aangemaakt, kunnen we misschien als we nog tijd over zouden hebben, wat aanpassen en
    * gebruiken om de code wat eenvoudiger en compacter te maken. Anders mag hij later dan gewoon verwijderd worden.
    *
    * Deze klasse kan, als je een blok uit het model als parameter meegeeft, met 1 methode (de constructor) de
    * afbeelding van de blok tekenen. Dit kan je dan eventueel gebruiken om in BlokkenBoxView, SpeelBordView en
    * MainScreenPresenter de code wat eenvoudiger te maken, omdat dan in die klassen zelf geen code meer moet staan
    * om de blokken te tekenen.
    */


    private static final int GROOT = 30;
    private static final int KLEIN = 15;

    private static final Color DONKER = Color.BLACK;
    private static final Color LICHT = Color.MOCCASIN; //soort kleur beige
    private static final Color TRANSPARANT = Color.TRANSPARENT; //doorzichtig --> handig voor holle blokken!


    public BlokkenBoxShape(Blok blok) {
        maakBlokShape(blok);
    }

    public Shape maakBlokShape(Blok blok) {
        Shape shape;
        if (blok.getVorm().toString() == "rond") {
            shape = maakCirkel(blok);
        } else shape = maakVierkant(blok);
        return shape;
    }

    public Circle maakCirkel(Blok blok) {
        Circle cirkel = new Circle(setGrootte(blok), setVulling(blok));
        cirkel.setStrokeType(StrokeType.CENTERED);
        cirkel.setStroke(setKleur(blok));
        // dit nog testen op kleur van de rand
        return cirkel;
    }

    public Rectangle maakVierkant(Blok blok) {
        Rectangle vierkant = new Rectangle(setGrootte(blok), setGrootte(blok), setVulling(blok));
        vierkant.setStrokeType(StrokeType.CENTERED);
        vierkant.setStroke(setKleur(blok));
        // dit nog testen op kleur van de rand
        return vierkant;
    }

    public Color setKleur(Blok blok) {
        if (blok.getKleur().toString() == "zwart") {
            return DONKER;
        } else {
            return LICHT;
        }
    }

    public int setGrootte(Blok blok) {
        if (blok.getGrootte().toString() == "groot") {
            return GROOT;
        } else {
            return KLEIN;
        }
    }

    public Color setVulling(Blok blok) {
        if (blok.getVulling().toString() == "hol") {
            return TRANSPARANT;
        } else {
            return setKleur(blok);
        }
    }
}