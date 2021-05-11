package Quarto.View.MenuScreen;

import Quarto.Model.Quarto;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.StartScreen.StartScreenView;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;

/**
 * @author Willem Kuijpers
 * @version 1.0 10-5-2021 15:46
 */
public class MenuScreenPresenter {

    private Quarto model;
    private MenuScreenView view;
    private UISettings uiSettings;


    public MenuScreenPresenter(Quarto model, MenuScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {

    }

    private void EventHandlers() {
        nieuwSpelHandler();
        afsluitenHandler();
    }

    private void nieuwSpelHandler() {
        view.getNieuwSpel().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainScreenView mainScreenView = new MainScreenView(uiSettings);
                MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model,mainScreenView,uiSettings);
                view.getScene().setRoot(mainScreenView);
                mainScreenView.getScene().getWindow().sizeToScene();
                try {
                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                mainScreenView.getScene().getWindow().sizeToScene();
                mainScreenView.getScene().getWindow().setX(uiSettings.getResX()/20);
                mainScreenView.getScene().getWindow().setY(uiSettings.getResY()/20);
                mainScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
                mainScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
                mainScreenPresenter.windowsHandler();
            }
        });
    }

    private void afsluitenHandler() {
        view.getAfsluiten().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCloseEvent(actionEvent);
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }

    private void handleCloseEvent(Event event){
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
            view.getScene().getWindow().hide();
        }
    }
}

