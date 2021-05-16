package Quarto.View.StartScreen;

import Quarto.Model.*;
import Quarto.View.*;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.MenuScreen.MenuScreenPresenter;
import Quarto.View.MenuScreen.MenuScreenView;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import java.net.MalformedURLException;

public class StartScreenPresenter {

    private Quarto model;
    private StartScreenView view;
    private UISettings uiSettings;

    public StartScreenPresenter(Quarto model, StartScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuScreenView menuScreenView = new MenuScreenView(uiSettings);
                MenuScreenPresenter menuScreenPresenter = new MenuScreenPresenter(model, menuScreenView, uiSettings);
                view.getScene().setRoot(menuScreenView);
                try {
                    menuScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                menuScreenView.getScene().getWindow().sizeToScene();
//                menuScreenView.getScene().getWindow().setX(view.getLayoutX());
//                menuScreenView.getScene().getWindow().setY(view.getLayoutY());
                menuScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 25);
                menuScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 50);
                menuScreenPresenter.windowsHandler();
            }
        });
    }


        // OUDE OVERGANG NAAR MAINSCREEN:

/*        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainScreenView msView = new MainScreenView(uiSettings);
                MainScreenPresenter msPresenter = new MainScreenPresenter(model, msView, uiSettings);
                view.getScene().setRoot(msView);
                try {
                    msView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                msView.getScene().getWindow().sizeToScene();
                msView.getScene().getWindow().setX(uiSettings.getResX()/20);
                msView.getScene().getWindow().setY(uiSettings.getResY()/20);
                msView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
                msView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
                msPresenter.windowsHandler();
            }
        });*/

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
             @Override
             public void handle(WindowEvent event) {
                 Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                 stopWindow.setHeaderText("Je kan de applicatie nog niet afsluiten.");
                 stopWindow.setContentText("Probeer opnieuw als het programma is opgestart.");
                 stopWindow.showAndWait();
                 event.consume();
             }
        });
    }
}
