package Quarto.View.MainScreen;


import Quarto.View.UISettings;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class QuartoView extends BorderPane {

    private MenuItem exitMI;
    private MenuItem saveMI;
    private MenuItem loadMI;
    private MenuItem settingsMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;

    private SpeelBordView speelBordView;

    public QuartoView(UISettings uiSettings) {
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
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null,loadMI, saveMI, new SeparatorMenuItem(), settingsMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setCenter(speelBordView);



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

