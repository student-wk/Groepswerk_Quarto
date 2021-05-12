package Quarto.View.MenuScreen;

import Quarto.Model.Quarto;
import Quarto.Model.Speler;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;

public class NamesPresenter {
    private Quarto model;
    private NamesView view;
    private UISettings uiSettings;


    public NamesPresenter(Quarto model, NamesView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void updateView() {

        // Koppelt event handlers (anon . inner klassen)
        // aan de controls uit de view.
        // Event handlers: roepen methodes aan uit het
        // model en zorgen voor een update van de view.
    }

    private void addEventHandlers() {
        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setPlayers( view.getPlayer1TextField().getText(),  view.getPlayer2TextField().getText());
                model.kieSpeler();

                MainScreenView mainScreenView = new MainScreenView(uiSettings);
                MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model,mainScreenView,uiSettings);
                view.getScene().setRoot(mainScreenView);
                mainScreenView.getScene().getWindow().sizeToScene();
//                try {
//                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
//                } catch (MalformedURLException ex) {
//                    // // do nothing, if toURL-conversion fails, program can continue
//                }
//                mainScreenView.getScene().getWindow().sizeToScene();
//                mainScreenView.getScene().getWindow().setX(uiSettings.getResX()/20);
//                mainScreenView.getScene().getWindow().setY(uiSettings.getResY()/20);
//                mainScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
//                mainScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
//                mainScreenPresenter.windowsHandler();

            }
        });
        // Vult de view met data uit model
    }

    public void addWindowEventHandlers() {
        //Window event handlers (non.innerklassen)
    }


}
