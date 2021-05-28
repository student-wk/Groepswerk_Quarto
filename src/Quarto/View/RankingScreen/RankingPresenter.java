package Quarto.View.RankingScreen;

import Quarto.Model.Quarto;
import Quarto.Model.QuartoException;
import Quarto.Model.Player;
import Quarto.Model.PlayerRanking;
import Quarto.View.AboutScreen.AboutScreenPresenter;
import Quarto.View.AboutScreen.AboutScreenView;
import Quarto.View.InfoScreen.InfoScreenPresenter;
import Quarto.View.InfoScreen.InfoScreenView;
import Quarto.View.LastGameScreen.LastGamePresenter;
import Quarto.View.LastGameScreen.LastGameView;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;
import java.nio.file.Files;

/**
 * @author Willem Kuijpers
 * @version 1.0 11-5-2021 11:56
 */
public class RankingPresenter {

    private Quarto model;
    private RankingView rankingView;
    private UISettings uiSettings;

    private PlayerRanking playerRanking;

    public RankingPresenter(Quarto model,RankingView rankingView, UISettings uiSettings, PlayerRanking playerRanking) {
        this.model = model;
        this.rankingView = rankingView;
        this.uiSettings = uiSettings;
        this.playerRanking = playerRanking;
        updateView();
        eventHandlers();
    }

    /*
    * Update de grafiek met de gegevens uit het bestand.
    * */

    private void updateView() {
        try {
            this.playerRanking.scoreFile2List();
        } catch (QuartoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to show ranking");
            alert.setContentText(e.getMessage());
            alert.showAndWait();        }
        try {
            int lengte = playerRanking.getHighScoresRanking().size();
            if (lengte>10) {
                lengte = 10;
            }
            for (int i = 0; i < lengte; i++) {
                Player player = playerRanking.getHighScoresRanking().get(i);
                this.rankingView.getSeries().getData().add(new XYChart.Data(player.getName(), player.getScore()));
            }
        } catch (IndexOutOfBoundsException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unable to show ranking");
        alert.setContentText("There are no rankings logged yet, please play a game first");
        alert.showAndWait();


        // nog zorgen dat hij in dit geval trg naar het menu gaat. DEZE EXCEPTION GAAT OOK AF WNR DE RANKING WEL WERKT

        }
    }

    private void eventHandlers() {
        addMenuEventHandlers();
        terugHandler();
        resetRankingHandler();
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
                menuScreenPresenter.windowsHandler();
            }
        });
    }

    private void resetRankingHandler() {
        rankingView.getResetRanking().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.getPlayerRanking().clearRankingFile();
                    updateView();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reset Ranking");
                    alert.setContentText("Open ranking again to view the updated ranking.");
                    alert.showAndWait();

                } catch (QuartoException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Unable to clear ranking in source file");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
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
                menuScreenPresenter.windowsHandler();
            }
        });
        rankingView.getLastGameItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LastGameView lastGameView = new LastGameView(uiSettings);
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
        rankingView.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCloseEvent(actionEvent);
            }
        });
    }

    public void handleCloseEvent(Event event) {
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("Je gaat de applicatie afsluiten.");
        stopWindow.setContentText("Ben je zeker? Onopgeslaagde data kan verloren gaan.");
        stopWindow.setTitle("WAARSCHUWING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("Nee");
        ButtonType yesButton = new ButtonType("Ja");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            rankingView.getScene().getWindow().hide();
        }
    }

    public void windowsHandler() {
        rankingView.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                handleCloseEvent(event);
            }
        });
    }
}
