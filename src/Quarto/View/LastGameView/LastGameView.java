package Quarto.View.LastGameView;

import Quarto.View.UISettings;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * @author Willem Kuijpers
 * @version 1.0 12-5-2021 10:40
 */
public class LastGameView extends BorderPane {

    private UISettings uiSettings;

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem rankingMI;
    private MenuItem lastGameMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private Button terug;


    public LastGameView() {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Afsluiten");
        this.saveMI = new MenuItem("Opslaan");
        this.loadMI = new MenuItem("Laden");
        this.settingsMI = new MenuItem("Hoofdmenu");
        this.rankingMI = new MenuItem("Ranking");
        this.lastGameMI = new MenuItem("Laatste Spel");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");

        this.terug = new Button("Terug");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("Bestand",null,loadMI, saveMI, settingsMI,rankingMI,lastGameMI, new SeparatorMenuItem(),exitMI);        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);

        this.setBottom(terug);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    public Button getTerug() {
        return terug;
    }

    public MenuItem getRankingItem() {
        return rankingMI;
    }

    public MenuItem getLastGameItem() {
        return lastGameMI;
    }
}
