package Quarto.View.MainScreen;

import Quarto.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

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
    private Label turnLabel;


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
        this.turnLabel = new Label();
        this.turnLabel.setStyle("-fx-background-color: orange");
        this.turnLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) ));
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("Bestand",null, settingsMI,rankingMI,lastGameMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setCenter(speelBordView);
        setLeft(blokkenBoxView);
        setBottom(turnLabel);


        this.setMargin(blokkenBoxView, new Insets(30));
    }

    public Label getTurnLabel() {
        return turnLabel;
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
