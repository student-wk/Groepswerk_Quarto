package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 * @author Willem Kuijpers
 * @version 1.0 6-5-2021 13:22
 */
public class BlokkenBoxShape extends Shape {

    private static final int GROOT = 30;
    private static final int KLEIN = 15;

    private static final Color DONKER = Color.BLACK;
    private static final Color LICHT = Color.MOCCASIN;
    private static final Color TRANSPARANT = Color.TRANSPARENT;

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
