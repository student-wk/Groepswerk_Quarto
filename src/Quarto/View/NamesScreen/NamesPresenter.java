package Quarto.View.NamesScreen;

import Quarto.Model.Quarto;
import Quarto.Model.QuartoException;
import Quarto.Model.Player;
import Quarto.Model.PlayerRanking;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;

public class NamesPresenter {
    private Quarto model;
    private NamesView view;
    private UISettings uiSettings;
    private PlayerRanking ranking;


    public NamesPresenter(Quarto model, NamesView view, UISettings uiSettings, PlayerRanking ranking) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        this.addEventHandlers();
        this.ranking = ranking;
        this.updateView();
        addEventHandlers();
    }

    private void updateView() {
        updateListNames();
    }

    private void updateListNames() {
        try {
            this.ranking.scoreFile2List();
        } catch (IOException e) {
            final Alert enterPlayerNames = new Alert(Alert.AlertType.ERROR);
            enterPlayerNames.setTitle("Error using sourcefile");
            enterPlayerNames.setContentText(e.getMessage());
            enterPlayerNames.showAndWait();
        }
        for (Player player :
                ranking.getHighScoresRanking()) {
            view.getListNames().add(player.getName());
        }
        Collections.sort(view.getListNames());
    }

    private void addEventHandlers() {
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.setPlayers(view.getPlayer1ComboBox().getValue(), view.getPlayer2ComboBox().getValue());
                    model.choosePlayer();
                    MainScreenView mainScreenView = new MainScreenView(uiSettings);
                    MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model, mainScreenView, uiSettings);
                    view.getScene().setRoot(mainScreenView);
                try {
                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                mainScreenView.getScene().getWindow().sizeToScene();
                mainScreenView.getScene().getWindow().setX(uiSettings.getResX()/4);
                mainScreenView.getScene().getWindow().setY(uiSettings.getResY()/4);
                mainScreenView.getScene().getWindow().setHeight(6 * uiSettings.getResY() / 10);
                mainScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResY() / 10);
                mainScreenPresenter.windowsHandler();
                } catch (QuartoException | IOException exception) {
                    final Alert enterPlayerNames = new Alert(Alert.AlertType.ERROR);
                    enterPlayerNames.setTitle("Enter names");
                    enterPlayerNames.setContentText(exception.getMessage());
                    enterPlayerNames.showAndWait();
                    actionEvent.consume();
                }
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                handleCloseEvent(event);
            }
        });
    }

    public void handleCloseEvent(Event event) {
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You are about to close the application");
        stopWindow.setContentText("Are you sure, unsaved progress will be lost");
        stopWindow.setTitle("Warning!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("NO");
        ButtonType yesButton = new ButtonType("YES");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }
}
