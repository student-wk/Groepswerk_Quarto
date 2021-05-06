package Quarto.View.MainScreen;

import Quarto.View.UISettings;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;

    private SpeelBordView speelBordView;
//    private BlokkenBoxView2 blokkenBoxView2;
    private BlokkenBoxGridPane blokkenBoxGridPane;


    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.saveMI = new MenuItem("Save");
        this.loadMI = new MenuItem("Load");
        this.settingsMI = new MenuItem("Settings");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.speelBordView = new SpeelBordView();
//        this.blokkenBoxView2 = new BlokkenBoxView2();
        this.blokkenBoxGridPane = new BlokkenBoxGridPane();

    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setCenter(speelBordView);
        setLeft(blokkenBoxGridPane);
//        setRight(blokkenBoxView2);
    }

    public BlokkenBoxGridPane getBlokkenBoxGridPane() {
        return blokkenBoxGridPane;
    }

    public SpeelBordView getSpeelBordView() {
        return speelBordView;
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSaveItem() {return saveMI;}

    MenuItem getLoadItem() {return loadMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

}
