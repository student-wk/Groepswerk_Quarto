package Quarto.View.RankingScreen;

import Quarto.View.UISettings;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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

    private BarChart<String,Number> barChart;


    public RankingView() {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Afsluiten");
        this.settingsMI = new MenuItem("Hoofdmenu");
        this.lastGameMI = new MenuItem("Laatste Spel");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        this.barChart = new BarChart<>(xAxis,yAxis);
        xAxis.setLabel("Name");
        yAxis.setLabel("Score");

        this.terug = new Button("Terug");
    }

    private void layoutNodes() {
        Menu menuFile = new Menu("Bestand",null, settingsMI,lastGameMI,new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);

        this.barChart.setTitle("Top 10 Ranking");
        this.setCenter(this.barChart);
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

BarChart<String, Number> getBarChart() {
        return barChart;
    }
}
