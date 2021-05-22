package Quarto.View.NamesScreen;

import Quarto.Model.Quarto;
import Quarto.Model.QuartoException;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

public class NamesPresenter {
    private Quarto model;
    private NamesView view;
    private UISettings uiSettings;


    public NamesPresenter(Quarto model, NamesView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
        addEventHandlers();
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
                try {
                    model.setPlayers( view.getPlayer1TextField().getText(),  view.getPlayer2TextField().getText());
                    model.kieSpeler();

                    MainScreenView mainScreenView = new MainScreenView(uiSettings);
                    MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(model,mainScreenView,uiSettings);
                    view.getScene().setRoot(mainScreenView);
//                try {
//                    mainScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
//                } catch (MalformedURLException ex) {
//                    // // do nothing, if toURL-conversion fails, program can continue
//                }
                    mainScreenView.getScene().getWindow().sizeToScene();
//                mainScreenView.getScene().getWindow().setX(uiSettings.getResX()/20);
//                mainScreenView.getScene().getWindow().setY(uiSettings.getResY()/20);
                    mainScreenView.getScene().getWindow().setHeight(view.getHeight()*2);
                    mainScreenView.getScene().getWindow().setWidth(view.getWidth()*3);
                    mainScreenPresenter.windowsHandler();
                } catch (QuartoException exception) {
                    final Alert enterPlayerNames = new Alert(Alert.AlertType.ERROR);
                    enterPlayerNames.setTitle("Enter names");
                    enterPlayerNames.setContentText(exception.getMessage());
                    enterPlayerNames.showAndWait();
                    actionEvent.consume();
                }


            }
        });
        // Vult de view met data uit model
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }

    public void handleCloseEvent(Event event){
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
        }
        else {
            view.getScene().getWindow().hide();
        }
    }

//    public void addWindowEventHandlers() {
//        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setHeaderText("Hierdoor stopt het spel!");
//                alert.setContentText("Ben je zeker?");
//                alert.setTitle("Opgelet!");
//                alert.getButtonTypes().clear();
//                ButtonType neen = new ButtonType("Neen");
//                ButtonType ja = new ButtonType("Ja");
//                alert.getButtonTypes().addAll(neen, ja);
//                alert.showAndWait();
//                if (alert.getResult() == null || alert.getResult().equals(neen)) {
//                    event.consume();
//                }
//            }
//        });
//    }

//    public void addWindowEventHandlers() {
//        //Window event handlers (non.innerklassen)
//    }


}
