package Quarto.View.MainScreen;

import javafx.scene.layout.GridPane;

/**
 * @author Willem Kuijpers
 * @version 1.0 6-5-2021 15:22
 */
public class BlokkenBoxPane extends GridPane {

    public BlokkenBoxPane() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        BlokkenBoxShapes blokkenShapes = new BlokkenBoxShapes();
    }

    private void layoutNodes() {


        this.setGridLinesVisible(true);
    }
}
