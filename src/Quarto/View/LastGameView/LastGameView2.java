package Quarto.View.LastGameView;

import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.UISettings;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class LastGameView2 extends MainScreenView {
    private Button playAnimation;

    public LastGameView2(UISettings uiSettings) {
        super(uiSettings);
        intialiseNodes();
        layoutNodes();
    }

    public void intialiseNodes(){
        playAnimation = new Button("Play Animation");
    }

    public void layoutNodes(){
        super.getInfoGridpane().add(this.playAnimation, 2,0);
        GridPane.setConstraints(this.playAnimation, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
    }

    public Button getPlayAnimation() {
        return playAnimation;
    }
}
