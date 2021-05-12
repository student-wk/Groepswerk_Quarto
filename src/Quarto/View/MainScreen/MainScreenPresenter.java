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
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MainScreenPresenter {

    private Quarto model;
    private MainScreenView view;
    private UISettings uiSettings;

    public MainScreenPresenter(Quarto model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updateView();
        EventHandlers();
    }

    private void updateView() {
        updateBlokkenBoxView();
    }

    /*
     * Deze methode zorgt ervoor dat de afgebeelde blokkenbox telkens geupdated wordt naar de huidige toestand van de
     * BlokkenBox in het model.
     * */

    private void updateBlokkenBoxView() {
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

    private void updateSpeelBordView(int rowIndex, int colIndex) {
//        view.getSpeelBordView().removeNodeByRowColumnIndex(rowIndex, colIndex);
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getGekozenBlok());
    }

    private void EventHandlers() {
        addMenuEventHandlers();
        blokkenBoxEventHandlers();
        speelBordEventHandlers();
    }

    private void blokkenBoxEventHandlers() {
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
                        if (circle.toString().length()>70){
                            blok.setVulling(Blok.Vulling.HOL);
                            blok.setGrootte((circle.getRadius() == view.getBlokkenBoxView().BIG_SIZE_EMPTY? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                            blok.setKleur(circle.getFill() == view.getBlokkenBoxView().EMPTY_COLOR_BLUE? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                        } else {
                            blok.setVulling(Blok.Vulling.VOL);
                            blok.setGrootte((circle.getRadius() == view.getBlokkenBoxView().BIG_SIZE? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                            blok.setKleur(circle.getFill() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                        }
                        model.kiesBlok(blok);
                        updateView();
                    }
                });
                view.getBlokkenBoxView().getRectangles()[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Blok blok = new Blok();
                        Rectangle rectangle = view.getBlokkenBoxView().getRectangles()[row][col];
                        blok.setVorm(Blok.Vorm.VIERKANT);
                        if (rectangle.toString().length()>70){
                            blok.setVulling(Blok.Vulling.HOL);
                            blok.setGrootte((rectangle.getWidth() == view.getBlokkenBoxView().BIG_SIZE_EMPTY*2? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                            blok.setKleur(rectangle.getFill() == view.getBlokkenBoxView().EMPTY_COLOR_BLUE? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                        } else {
                            blok.setVulling(Blok.Vulling.VOL);
                            blok.setGrootte((rectangle.getWidth() == view.getBlokkenBoxView().BIG_SIZE*2? Blok.Grootte.GROOT : Blok.Grootte.KLEIN));
                            blok.setKleur(rectangle.getFill() == view.getBlokkenBoxView().BlUE_COLOR? Blok.Kleur.ZWART: Blok.Kleur.WIT);
                        }
                        model.kiesBlok(blok);
                        updateView();
                    }
                });
            }
        }
    }

    private void speelBordEventHandlers() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.getSpeelBordView().getNodeByRowColumnIndex(i,j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        int rowIndex = GridPane.getRowIndex((Node) mouseEvent.getSource());
                        int colIndex = GridPane.getColumnIndex((Node) mouseEvent.getSource());
                        updateSpeelBordView(rowIndex, colIndex);
                        try {
                            model.plaatsBlok(new Positie(rowIndex,colIndex));

                            if (model.getSpeelbord().heeftCombinatie()){
                                System.out.println("Een speler heeft gewonnen");
                            } else if (model.getSpeelbord().isVol()){
                                System.out.println("speelbord is vol");
                            }
                        } catch (QuartoException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }


    public void addMenuEventHandlers(){
        view.getSettingsItem().setOnAction(new EventHandler<ActionEvent>() {
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
                menuScreenView.getScene().getWindow().setX(uiSettings.getResX() / 20);
                menuScreenView.getScene().getWindow().setY(uiSettings.getResY() / 20);
                menuScreenView.getScene().getWindow().setHeight(9 * uiSettings.getResY() / 10);
                menuScreenView.getScene().getWindow().setWidth(9 * uiSettings.getResX() / 10);
                menuScreenPresenter.windowsHandler();
            }
        });

        view.getLoadItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Laad data bestand");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showOpenDialog(view.getScene().getWindow());
                if ((selectedFile != null) ){ //^ (Files.isReadable(Paths.get(selectedFile.toURI())))) {
                    try {
                        List<String> input = Files.readAllLines(Paths.get(selectedFile.toURI()));
                        // begin implementeren ingelezen gegevens doorgeven aan model
                        for (int i = 0; i < input.size(); i++) {
                            String inputline = input.get(i);
                            String[] elementen = inputline.split(" ");
                        }
                        // einde implementeren ingelezen gegevens doorgeven aan model
                    } catch (IOException e) {
                        //
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Probleem met het geselecteerde input bestand:");
                    errorWindow.setContentText("Bestand is niet leesbaar");
                    errorWindow.showAndWait();
                }
            }
        });
        view.getSaveItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Sla data bestand op");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showSaveDialog(view.getScene().getWindow());
                if ((selectedFile != null) ^ (Files.isWritable(Paths.get(selectedFile.toURI())))) {
                    try {
                        Files.deleteIfExists(Paths.get(selectedFile.toURI()));
                    } catch (IOException e) {
                        //
                    }
                    try (Formatter output = new Formatter(selectedFile)) {
                        // Begin implementeren wegschrijven model-gegevens
                        output.format("%s%n", "Hier komt de data!");
                        output.format("%s%n", "Eerste opslag");
                        output.format("%s%n", "...");
                        output.format("%s%n", "Laatste opslag");
                        // Einde implementeren wegschrijven model-gegevens
                    } catch (IOException e) {
                        //
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with the selected output file:");
                    errorWindow.setContentText("File is not writable");
                    errorWindow.showAndWait();
                }
            }
        });
        view.getRankingItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RankingView rankingView = new RankingView();
                RankingPresenter rankingPresenter = new RankingPresenter(model,rankingView,uiSettings);
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
                rankingPresenter.windowsHandler();
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
