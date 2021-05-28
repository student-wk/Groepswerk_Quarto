package Quarto.View.MenuScreen;

import Quarto.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * @author Willem Kuijpers
 * @version 1.0 10-5-2021 15:46
 */
public class MenuScreenView extends VBox {

    private UISettings uiSettings;
    private Button newGame;
    private Button ranking;
    private Button showLastGame;
    private Button close;

    public MenuScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.newGame = new Button("New game");
        this.ranking = new Button("Ranking");
        this.showLastGame = new Button("Show last game");
        this.close = new Button("Exit");
    }

    private void layoutNodes() {
        this.setSpacing(20);
        this.setPadding(new Insets(100));
        this.getChildren().addAll(newGame,ranking, showLastGame, close);
        this.setAlignment(Pos.CENTER);
        this.newGame.setPrefSize(150, 20);
        this.ranking.setPrefSize(150, 20);
        this.showLastGame.setPrefSize(150, 20);
        this.close.setPrefSize(150, 20);

    }

    public UISettings getUiSettings() {
        return uiSettings;
    }

    public Button getNewGame() {
        return newGame;
    }

    public Button getRanking() {
        return ranking;
    }

    public Button getShowLastGame() {
        return showLastGame;
    }

    public Button getClose() {
        return close;
    }
}
