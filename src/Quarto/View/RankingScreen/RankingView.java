package Quarto.View.RankingScreen;

import Quarto.View.UISettings;
import javafx.geometry.Insets;
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
    private MenuItem aboutMI;
    private MenuItem infoMI;

    private Button back;
    private Button resetRanking;
    private BarChart<String,Number> barChart;

    private XYChart.Series <String, Number> series;



    public RankingView() {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.settingsMI = new MenuItem("Main Menu");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");

        this.back = new Button("Back");
        this.resetRanking = new Button("Reset Ranking");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        this.barChart = new BarChart<>(xAxis,yAxis);
        xAxis.setLabel("Name");
        yAxis.setLabel("Score");

        this.series = new XYChart.Series();
        this.barChart.getData().addAll(series);

    }

    private void layoutNodes() {
        Menu menuFile = new Menu("File",null, settingsMI,new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);

        this.setBottom(back);
        this.setRight(resetRanking);
        RankingView.setMargin(this.resetRanking, new Insets(10));
        this.barChart.setTitle("Top 10 Ranking");
        this.setCenter(this.barChart);
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    public Button getBack() {
        return back;
    }

    public Button getResetRanking() {
        return resetRanking;
    }

    public XYChart.Series<String, Number> getSeries() {
        return series;
    }


}
