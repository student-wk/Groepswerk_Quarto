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


    /*
    * Menuscherm met meerdere knoppen:
    *   Nieuw Spel
    *   Ranking
    *   Toon Laatste Spel
    *   Terug
    *   Afsluiten
    *
    * BorderPane maken en daarin VBox zetten met knoppen? Anders gwn Vbox die
    * herschaalt tot normaal scherm.
    * (zie wrs https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Region.html)
    * */

    private UISettings uiSettings;
    private Button nieuwSpel;
    private Button ranking;
    private Button toonLaatsteSpel;
    private Button afsluiten;

    public MenuScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.nieuwSpel = new Button("New Game");
        this.ranking = new Button("Ranking");
        this.toonLaatsteSpel = new Button("Last Game");
        this.afsluiten = new Button("Exit");
    }

    private void layoutNodes() {
        this.setSpacing(20);
        this.setPadding(new Insets(100));
        this.getChildren().addAll(nieuwSpel,ranking,toonLaatsteSpel,afsluiten);
        this.setAlignment(Pos.CENTER);
        this.nieuwSpel.setPrefSize(150, 20);
        this.ranking.setPrefSize(150, 20);
        this.toonLaatsteSpel.setPrefSize(150, 20);
        this.afsluiten.setPrefSize(150, 20);

    }

    public UISettings getUiSettings() {
        return uiSettings;
    }

    public Button getNieuwSpel() {
        return nieuwSpel;
    }

    public Button getRanking() {
        return ranking;
    }

    public Button getToonLaatsteSpel() {
        return toonLaatsteSpel;
    }

    public Button getAfsluiten() {
        return afsluiten;
    }
}
