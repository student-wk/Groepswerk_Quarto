package Quarto.View.MainScreen;

import Quarto.View.UISettings;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem settingsMI;
    private MenuItem rankingMI;
    private MenuItem lastGameMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;

    private SpeelBordView speelBordView;
    private BlokkenBoxView blokkenBoxView;


    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Afsluiten");
        this.settingsMI = new MenuItem("Hoofdmenu");
        this.rankingMI = new MenuItem("Ranking");
        this.lastGameMI = new MenuItem("Laatste Spel");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.speelBordView = new SpeelBordView();
        this.blokkenBoxView = new BlokkenBoxView();
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("Bestand",null, settingsMI,rankingMI,lastGameMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setCenter(speelBordView);
        setLeft(blokkenBoxView);
    }

    public BlokkenBoxView getBlokkenBoxView() {
        return blokkenBoxView;
    }

    public SpeelBordView getSpeelBordView() {
        return speelBordView;
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    public MenuItem getRankingItem() {
        return rankingMI;
    }

    public MenuItem getLastGameItem() {
        return lastGameMI;
    }
}
