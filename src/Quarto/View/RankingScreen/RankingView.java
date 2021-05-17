package Quarto.View.RankingScreen;

import Quarto.View.UISettings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

/**
 * @author Willem Kuijpers
 * @version 1.0 11-5-2021 11:56
 */
public class RankingView extends BorderPane {

    private UISettings uiSettings;

    private MenuItem exitMI;
    private MenuItem settingsMI;
    private MenuItem lastGameMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private Button terug;


    public RankingView() {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.settingsMI = new MenuItem("Main Menu");
        this.lastGameMI = new MenuItem("Last Game");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");

        this.terug = new Button("Back");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null, settingsMI,lastGameMI,new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);

        this.setBottom(terug);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    public Button getTerug() {
        return terug;
    }

    public MenuItem getLastGameItem() {
        return lastGameMI;
    }
}
