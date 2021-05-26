package Quarto.View.NamesScreen;

import Quarto.Model.Quarto;
import Quarto.Model.QuartoException;
import Quarto.Model.Speler;
import Quarto.Model.SpelerRanking;
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

public class NamesPresenter {
    private Quarto model;
    private NamesView view;
    private UISettings uiSettings;

    private SpelerRanking ranking;


    public NamesPresenter(Quarto model, NamesView view, UISettings uiSettings, SpelerRanking ranking) {
        this.model = model;
        this.view = view;
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
        } catch (QuartoException e) { // exception nog doen!
            e.printStackTrace();
        }
        for (Speler speler :
                ranking.getHighScoresRanking()) {
            view.getListNames().add(speler.getNaam());
        }
        /*        view.getListNames().sort();*/
        // nog sorteren!
    }

    private void addEventHandlers() {
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    model.setPlayers(view.getPlayer1ComboBox().getValue(), view.getPlayer2ComboBox().getValue());
                    model.kieSpeler();

                    MainScreenView mainScreenView = new MainScreenView(uiSettings);
                    MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model, mainScreenView, uiSettings);
                    view.getScene().setRoot(mainScreenView);
//                try {
//                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
//                } catch (MalformedURLException ex) {
//                    // // do nothing, if toURL-conversion fails, program can continue
//                }
                    mainScreenView.getScene().getWindow().sizeToScene();
//                mainScreenView.getScene().getWindow().setX(uiSettings.getResX()/20);
//                mainScreenView.getScene().getWindow().setY(uiSettings.getResY()/20);
                    mainScreenView.getScene().getWindow().setHeight(view.getHeight() * 2);
                    mainScreenView.getScene().getWindow().setWidth(view.getWidth() * 3);
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
        stopWindow.setContentText("Are you sure, unsaved prgress will be lost");
        stopWindow.setTitle("WAARSCHUWING!");
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
