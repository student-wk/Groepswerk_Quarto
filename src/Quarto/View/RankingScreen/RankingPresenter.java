package Quarto.View.RankingScreen;

import Quarto.Model.Quarto;
import Quarto.Model.Speler;
import Quarto.View.AboutScreen.AboutScreenPresenter;
import Quarto.View.AboutScreen.AboutScreenView;
import Quarto.View.InfoScreen.InfoScreenPresenter;
import Quarto.View.InfoScreen.InfoScreenView;
import Quarto.View.LastGameView.LastGamePresenter;
import Quarto.View.LastGameView.LastGameView;
import Quarto.View.MenuScreen.MenuScreenPresenter;
import Quarto.View.MenuScreen.MenuScreenView;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * @author Willem Kuijpers
 * @version 1.0 11-5-2021 11:56
 */
public class RankingPresenter {

    private Quarto model;
    private RankingView rankingView;
    private UISettings uiSettings;
    private ArrayList<Speler> rankingList;
    private Speler speler;

    public RankingPresenter(Quarto model,RankingView rankingView, UISettings uiSettings) {
        this.model = model;
        this.rankingView = rankingView;
        this.uiSettings = uiSettings;
        updateView();
        eventHandlers();
    }



    private void updateView() {
        // de gegevens uit het bestand halen en in een arraylist plaatsen
        // de gegevens aan barchart toevoegen
//        this.rankingList = model.getSpelerRanking().getHighScoresRanking();

        XYChart.Series <String, Number> series1 = new XYChart.Series();

        for (int i = 0; i < 10; i++) {
            Speler speler = rankingList.get(i);
            series1.getData().add(new XYChart.Data(speler.getNaam(),speler.getScore()));
        }
    }

    private void eventHandlers() {
        addMenuEventHandlers();
        terugHandler();
    }

    private void terugHandler() {
        rankingView.getTerug().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MenuScreenView menuScreenView = new MenuScreenView(uiSettings);
                MenuScreenPresenter menuScreenPresenter = new MenuScreenPresenter(model, menuScreenView, uiSettings);
                rankingView.getScene().setRoot(menuScreenView);
                try {
                    menuScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                menuScreenView.getScene().getWindow().sizeToScene();
                menuScreenView.getScene().getWindow().setX(uiSettings.getResX() / 20);
                menuScreenView.getScene().getWindow().setY(uiSettings.getResY() / 20);
                menuScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 10);
                menuScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 10);
//                menuScreenPresenter.a();
            }
        });
    }

    public void addMenuEventHandlers(){
        rankingView.getSettingsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuScreenView menuScreenView = new MenuScreenView(uiSettings);
                MenuScreenPresenter menuScreenPresenter = new MenuScreenPresenter(model, menuScreenView, uiSettings);
                rankingView.getScene().setRoot(menuScreenView);
                try {
                    menuScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                menuScreenView.getScene().getWindow().sizeToScene();
                menuScreenView.getScene().getWindow().setX(uiSettings.getResX() / 20);
                menuScreenView.getScene().getWindow().setY(uiSettings.getResY() / 20);
                menuScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 10);
                menuScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 10);
//                menuScreenPresenter.windowsHandler();
            }
        });
        rankingView.getLastGameItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LastGameView lastGameView = new LastGameView();
                LastGamePresenter lastGamePresenter = new LastGamePresenter(model,lastGameView,uiSettings);
                rankingView.getScene().setRoot(lastGameView);
                lastGameView.getScene().getWindow().sizeToScene();
                try {
                    lastGameView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                lastGameView.getScene().getWindow().sizeToScene();
                lastGameView.getScene().getWindow().setX(uiSettings.getResX()/20);
                lastGameView.getScene().getWindow().setY(uiSettings.getResY()/20);
                lastGameView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
                lastGameView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
                lastGamePresenter.windowsHandler();
            }
        });
//        rankingView.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                handleCloseEvent(event);
//            }
//        });
        rankingView.getAboutItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(model, aboutScreenView, uiSettings);
                Stage aboutScreenStage = new Stage();
                aboutScreenStage.initOwner(rankingView.getScene().getWindow());
                aboutScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(aboutScreenView);
                aboutScreenStage.setScene(scene);
                aboutScreenStage.setTitle(uiSettings.getApplicationName() + " - About");
                aboutScreenStage.setX(rankingView.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                aboutScreenStage.setY(rankingView.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        aboutScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                aboutScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 2);
                aboutScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 2);
                if (uiSettings.styleSheetAvailable()) {
                    aboutScreenView.getScene().getStylesheets().removeAll();
                    try {
                        aboutScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                aboutScreenStage.showAndWait();
            }});
        rankingView.getInfoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InfoScreenView infoScreenView = new InfoScreenView(uiSettings);
                InfoScreenPresenter infoScreenPresenter = new InfoScreenPresenter(model, infoScreenView, uiSettings);
                Stage infoScreenStage = new Stage();
                infoScreenStage.initOwner(rankingView.getScene().getWindow());
                infoScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(infoScreenView);
                infoScreenStage.setScene(scene);
                infoScreenStage.setTitle(uiSettings.getApplicationName()+ " - Info");
                infoScreenStage.setX(rankingView.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                infoScreenStage.setY(rankingView.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        infoScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                infoScreenView.getScene().getWindow().setHeight(uiSettings.getResY()/2);
                infoScreenView.getScene().getWindow().setWidth(uiSettings.getResX()/2);
                if (uiSettings.styleSheetAvailable()){
                    infoScreenView.getScene().getStylesheets().removeAll();
                    try {
                        infoScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                infoScreenStage.showAndWait();
            }});
    }

    private void afsluitenHandler() {
        rankingView.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rankingView.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Hierdoor stopt het spel!");
                        alert.setContentText("Ben je zeker?");
                        alert.setTitle("Opgelet!");
                        alert.getButtonTypes().clear();
                        ButtonType neen = new ButtonType("Neen");
                        ButtonType ja = new ButtonType("Ja");
                        alert.getButtonTypes().addAll(neen, ja);
                        alert.showAndWait();
                        if (alert.getResult() == null || alert.getResult().equals(neen)) {
                            event.consume();
                        }
                    }
                });
            }
        });
    }

    public void addWindowEventHandlers() {
        rankingView.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Hierdoor stopt het spel!");
                alert.setContentText("Ben je zeker?");
                alert.setTitle("Opgelet!");
                alert.getButtonTypes().clear();
                ButtonType neen = new ButtonType("Neen");
                ButtonType ja = new ButtonType("Ja");
                alert.getButtonTypes().addAll(neen, ja);
                alert.showAndWait();
                if (alert.getResult() == null || alert.getResult().equals(neen)) {
                    event.consume();
                }
            }
        });
    }
}
