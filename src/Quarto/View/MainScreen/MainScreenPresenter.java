package Quarto.View.MainScreen;

import Quarto.Model.*;
import Quarto.View.AboutScreen.*;
import Quarto.View.InfoScreen.*;
import Quarto.View.LastGameView.LastGamePresenter;
import Quarto.View.LastGameView.LastGameView;
import Quarto.View.MenuScreen.MenuScreenPresenter;
import Quarto.View.MenuScreen.MenuScreenView;
import Quarto.View.RankingScreen.RankingPresenter;
import Quarto.View.RankingScreen.RankingView;
import Quarto.View.UISettings;
import javafx.application.Platform;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MainScreenPresenter {

    public Quarto model;
    private MainScreenView view;
    private UISettings uiSettings;

    public MainScreenPresenter(Quarto model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateBlokkenBoxView();
        addMenuEventHandlers();
        blokkenBoxEventHandlers();
        speelBordEventHandlers();
        updateTurnView();
    }

//    private void updateView() {
//        updateBlokkenBoxView();
//    }

    protected void updateTurnView(){
        String action = (model.isFlipAction()?"Place a piece!":"Pick a piece!");
        view.getTurnLabel().setText(model.getAlleSpelers().getActieveSpeler().getNaam() + ": " + action );
    }
    /*
     * Deze methode zorgt ervoor dat de afgebeelde blokkenbox telkens geupdated wordt naar de huidige toestand van de
     * BlokkenBox in het model.
     * */

    protected void updateBlokkenBoxView() {
        view.setNode(model.getGekozenBlok());
        for (Blok.Grootte grootte : Blok.Grootte.values()) {
            for (Blok.Kleur kleur : Blok.Kleur.values()) {
                for (Blok.Vorm vorm : Blok.Vorm.values()) {
                    for (Blok.Vulling vulling : Blok.Vulling.values()) {
                        Blok blok = new Blok(grootte,kleur,vorm,vulling);
                        if (!model.getBlokkenBox().getBlokkenSet().contains(blok)) {
                            if (blok.getVorm().equals(Blok.Vorm.ROND)){
                                if (blok.getKleur().equals(Blok.Kleur.WIT)){
                                    int colIndex = 0;
                                    int rowIndex;
                                    if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 0;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                        rowIndex = 1;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 2;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                } else {
                                    int colIndex = 1;
                                    int rowIndex;
                                    if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 0;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                        rowIndex = 1;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 2;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setRadius(view.getBlokkenBoxView().BIG_SIZE);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                }
                            } else if (blok.getVorm().equals(Blok.Vorm.VIERKANT)){
                                if (blok.getKleur().equals(Blok.Kleur.WIT)){
                                    int colIndex = 0;
                                    int rowIndex;
                                    if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 0;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                        rowIndex = 1;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 2;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                } else {
                                    int colIndex = 1;
                                    int rowIndex;
                                    if (blok.getVulling().equals(Blok.Vulling.VOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 0;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.VOL) && blok.getGrootte().equals(Blok.Grootte.KLEIN)){
                                        rowIndex = 1;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                    } else if (blok.getVulling().equals(Blok.Vulling.HOL)&& blok.getGrootte().equals(Blok.Grootte.GROOT)){
                                        rowIndex = 2;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setHeight(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setWidth(view.getBlokkenBoxView().BIG_SIZE*2);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStroke(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setFill(view.getBlokkenBoxView().DEFAULT_COLOR);
                                        view.getBlokkenBoxView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    protected void updateSpeelBordView(int rowIndex, int colIndex) throws QuartoException, IOException {
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getGekozenBlok());
        if (model.isGameFinished()){showFinishedDialog(); }
    }

    protected void showFinishedDialog() throws QuartoException, IOException {
//        Log.debug("showing finished");
        if (!model.isGameFinished()) return;
        ChoiceDialog<String> again = new ChoiceDialog<String>("Ok", "Ok", "Nope");
        if (model.getSpeelbord().heeftCombinatie()) {
            again.setTitle(model.getAlleSpelers().getActieveSpeler().getNaam() + " has won!");
            again.setHeaderText(model.getAlleSpelers().getActieveSpeler().getNaam() + " has won");
//            CombinationView combinationView = new CombinationView();
//            new CombinationPresenter(model.getRiddle(), combinationView);
//            again.setGraphic(combinationView);
        } else {
            again.setTitle("Playbord is full!");
            again.setHeaderText("Playbord is full!");
//            again.setGraphic(new ImageView("images/duim.png"));
//            again.setHeaderText("You found it in " + model.getNumberOfGuessesDone() + " moves...");
        }
        again.setContentText("You wanna play again?");
        again.showAndWait();
        String result = again.getResult();
        if (result == null || result.equals("Nope")) {
            Platform.exit();
        } else {
            this.model = new Quarto(model.getAlleSpelers().getSpeler1(), model.getAlleSpelers().getSpeler2());
            model.kieSpeler();

            MainScreenView newView = new MainScreenView(uiSettings);
            view.getScene().setRoot(newView);
            new MainScreenPresenter(model, newView, uiSettings);
        }
    }

    protected void blokkenBoxEventHandlers() {
        for (int i = 0; i < view.getBlokkenBoxView().ROW_SIZE; i++) {
            for (int j = 0; j < view.getBlokkenBoxView().COL_SIZE; j++) {
                final int row = i;
                final int col = j;
                view.getBlokkenBoxView().getCircles()[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Blok blok = new Blok();
                        Circle circle = view.getBlokkenBoxView().getCircles()[row][col];
                        blok.setVorm(Blok.Vorm.ROND);
                        if (circle.getFill() == view.getBlokkenBoxView().DEFAULT_COLOR  && circle.toString().length() < 70) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();
                        } else if (circle.getStroke() == view.getBlokkenBoxView().DEFAULT_COLOR  && circle.toString().length() > 70) {mouseEvent.consume(); }else {
                            if (circle.toString().length()>70){
                                blok.setVulling(Blok.Vulling.HOL);
                                blok.setGrootte((circle.getRadius() == view.getBlokkenBoxView().BIG_SIZE_EMPTY? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                                blok.setKleur(circle.getStroke() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                            } else {
                                blok.setVulling(Blok.Vulling.VOL);
                                blok.setGrootte((circle.getRadius() == view.getBlokkenBoxView().BIG_SIZE? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                                blok.setKleur(circle.getFill() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                            }

                            try {
                                model.kiesBlok(blok);
                                updateBlokkenBoxView();
                                updateTurnView();

                            } catch (QuartoException | IOException exception){
                                final Alert noBlokChosen = new Alert(Alert.AlertType.INFORMATION);
                                noBlokChosen.setTitle("Place a piece on the playbord.");
                                noBlokChosen.setContentText(exception.getMessage());
                                noBlokChosen.showAndWait();
                                mouseEvent.consume();
                            }
                        }


                    }
                });
                view.getBlokkenBoxView().getRectangles()[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Blok blok = new Blok();
                        Rectangle rectangle = view.getBlokkenBoxView().getRectangles()[row][col];
                        blok.setVorm(Blok.Vorm.VIERKANT);
                        if (rectangle.getFill() == view.getBlokkenBoxView().DEFAULT_COLOR && rectangle.toString().length() < 70 ) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();
                        } else if (rectangle.getStroke() == view.getBlokkenBoxView().DEFAULT_COLOR && rectangle.toString().length() > 70 ) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();

                        } else {
                            if (rectangle.toString().length()>70){
                                blok.setVulling(Blok.Vulling.HOL);
                                blok.setGrootte((rectangle.getWidth() == view.getBlokkenBoxView().BIG_SIZE_EMPTY*2? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                                blok.setKleur(rectangle.getStroke() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                            } else {
                                blok.setVulling(Blok.Vulling.VOL);
                                blok.setGrootte((rectangle.getWidth() == view.getBlokkenBoxView().BIG_SIZE*2? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                                blok.setKleur(rectangle.getFill() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                            }

                            try {
                                model.kiesBlok(blok);
                                updateBlokkenBoxView();
                                updateTurnView();

                            } catch (QuartoException | IOException exception){
                                final Alert noBlokChosen = new Alert(Alert.AlertType.INFORMATION);
                                noBlokChosen.setTitle("Place a piece on the playbord.");
                                noBlokChosen.setContentText(exception.getMessage());
                                noBlokChosen.showAndWait();
                                mouseEvent.consume();
                            }
                        }





                    }
                });
            }
        }
    }

    protected void speelBordEventHandlers() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.getSpeelBordView().getNodeByRowColumnIndex(i,j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        int rowIndex = GridPane.getRowIndex((Node) mouseEvent.getSource());
                        int colIndex = GridPane.getColumnIndex((Node) mouseEvent.getSource());
                        try {

                            model.plaatsBlok(new Positie(rowIndex,colIndex));
                            updateSpeelBordView(rowIndex, colIndex);
                            updateTurnView();
                            model.setGekozenBlok(null);
                            view.setNode(model.getGekozenBlok());

                        } catch (QuartoException | IOException e) {
                            final Alert noBlokChosen = new Alert(Alert.AlertType.ERROR);
                            noBlokChosen.setTitle("You cannot close the application yet.");
                            noBlokChosen.setContentText(e.getMessage());
                            noBlokChosen.showAndWait();
                            mouseEvent.consume();

                        }
                    }
                });
            }
        }
    }

    public MainScreenView getView() {
        return view;
    }

    public void addMenuEventHandlers(){
        view.getSettingsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuScreenView menuScreenView = new MenuScreenView(uiSettings);
                MenuScreenPresenter menuScreenPresenter = new MenuScreenPresenter(model, menuScreenView, uiSettings);
                view.getScene().setRoot(menuScreenView);
//                try {
//                    menuScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
//                } catch (MalformedURLException ex) {
//                    // // do nothing, if toURL-conversion fails, program can continue
//                }
                menuScreenView.getScene().getWindow().sizeToScene();
//                menuScreenView.getScene().getWindow().setX(uiSettings.getResX() / 20);
//                menuScreenView.getScene().getWindow().setY(uiSettings.getResY() / 20);
//                menuScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 10);
//                menuScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 10);
//                menuScreenPresenter.windowsHandler();
            }
        });
        view.getRankingItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RankingView rankingView = new RankingView();
                SpelerRanking spelerRanking = new SpelerRanking();
                RankingPresenter rankingPresenter = new RankingPresenter(model,rankingView,uiSettings,spelerRanking);
                view.getScene().setRoot(rankingView);
                rankingView.getScene().getWindow().sizeToScene();
                try {
                    rankingView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                } catch (MalformedURLException ex) {
                    // // do nothing, if toURL-conversion fails, program can continue
                }
                rankingView.getScene().getWindow().sizeToScene();
                rankingView.getScene().getWindow().setX(uiSettings.getResX()/20);
                rankingView.getScene().getWindow().setY(uiSettings.getResY()/20);
                rankingView.getScene().getWindow().setHeight(9 * uiSettings.getResY()/10);
                rankingView.getScene().getWindow().setWidth(9 * uiSettings.getResX()/10);
//                rankingPresenter.windowsHandler();
            }
        });
        view.getLastGameItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LastGameView lastGameView = new LastGameView();
                LastGamePresenter lastGamePresenter = new LastGamePresenter(model,lastGameView,uiSettings);
                view.getScene().setRoot(lastGameView);
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
        view.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCloseEvent(event);
            }
        });
        view.getAboutItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(model, aboutScreenView, uiSettings);
                Stage aboutScreenStage = new Stage();
                aboutScreenStage.initOwner(view.getScene().getWindow());
                aboutScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(aboutScreenView);
                aboutScreenStage.setScene(scene);
                aboutScreenStage.setTitle(uiSettings.getApplicationName() + " - About");
                aboutScreenStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                aboutScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
//                if (Files.exists(uiSettings.getApplicationIconPath())) {
//                    try {
//                        aboutScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
//                    } catch (MalformedURLException ex) {
//                        // do nothing, if toURL-conversion fails, program can continue
//                    }
//                } else { // do nothing, if ApplicationIconImage is not available, program can continue
//                }
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
        view.getInfoItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InfoScreenView infoScreenView = new InfoScreenView(uiSettings);
                InfoScreenPresenter infoScreenPresenter = new InfoScreenPresenter(model, infoScreenView, uiSettings);
                Stage infoScreenStage = new Stage();
                infoScreenStage.initOwner(view.getScene().getWindow());
                infoScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(infoScreenView);
                infoScreenStage.setScene(scene);
                infoScreenStage.setTitle(uiSettings.getApplicationName()+ " - Info");
                infoScreenStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                infoScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
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

    public Quarto getModel() {
        return model;
    }
}
