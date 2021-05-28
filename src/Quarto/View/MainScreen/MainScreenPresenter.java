package Quarto.View.MainScreen;

import Quarto.Model.*;
import Quarto.View.AboutScreen.*;
import Quarto.View.InfoScreen.*;
import Quarto.View.LastGameScreen.LastGamePresenter;
import Quarto.View.LastGameScreen.LastGameView;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

public class MainScreenPresenter {

    protected Quarto model;
    protected MainScreenView view;
    protected UISettings uiSettings;

    public UISettings getUiSettings() {
        return uiSettings;
    }

    public MainScreenPresenter(Quarto model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        updatePiecesView();
        addMenuEventHandlers();
        piecesEventHandlers();
        playboardEventHandlers();
        updateTurnStatusView();
    }

//    private void updateView() {
//        updatePiecesView();
//    }

    protected void updateTurnStatusView(){
        String action = (model.isFlipAction()?"Place a piece!":"Pick a piece!");
        view.getTurnLabel().setText(model.getAllPlayers().getActivePlayer().getName() + ": " + action );
    }

    /*
     * Deze methode zorgt ervoor dat de afgebeelde blokkenbox telkens geupdated wordt naar de huidige toestand van de
     * BlokkenBox in het model.
     * */

    protected void updatePiecesView() {
        view.setNode(model.getChosenPiece());
        for (Piece.Size size : Piece.Size.values()) {
            for (Piece.Color color : Piece.Color.values()) {
                for (Piece.Shape shape : Piece.Shape.values()) {
                    for (Piece.Filling filling : Piece.Filling.values()) {
                        Piece piece = new Piece(size, color, shape, filling);
                        if (!model.getPieces().getPieceSet().contains(piece)) {
                            if (piece.getShape().equals(Piece.Shape.ROUND)){
                                if (piece.getColor().equals(Piece.Color.WHITE)){
                                    int colIndex = 0;
                                    int rowIndex;
                                    if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 0;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                        rowIndex = 1;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 2;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                } else {
                                    int colIndex = 1;
                                    int rowIndex;
                                    if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 0;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                        rowIndex = 1;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 2;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setRadius(view.getPiecesView().BIG_SIZE);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getCircles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                }
                            } else if (piece.getShape().equals(Piece.Shape.SQUARE)){
                                if (piece.getColor().equals(Piece.Color.WHITE)){
                                    int colIndex = 0;
                                    int rowIndex;
                                    if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 0;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                        rowIndex = 1;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 2;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                } else {
                                    int colIndex = 1;
                                    int rowIndex;
                                    if (piece.getFilling().equals(Piece.Filling.FULL)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 0;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.FULL) && piece.getSize().equals(Piece.Size.SMALL)){
                                        rowIndex = 1;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                    } else if (piece.getFilling().equals(Piece.Filling.EMPTY)&& piece.getSize().equals(Piece.Size.BIG)){
                                        rowIndex = 2;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    } else {
                                        rowIndex = 3;
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setHeight(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setWidth(view.getPiecesView().BIG_SIZE*2);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStroke(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setFill(view.getPiecesView().DEFAULT_COLOR);
                                        view.getPiecesView().getRectangles()[rowIndex][colIndex].setStrokeWidth(0);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    protected void updatePlayboardView(int rowIndex, int colIndex) throws QuartoException, IOException {
        view.getPlayboardView().addPiece(rowIndex, colIndex, model.getChosenPiece());
        if (model.isGameFinished()){showFinishedDialog(); }
    }

    protected void showFinishedDialog() throws QuartoException, IOException {
        if (!model.isGameFinished()) return;
        ChoiceDialog<String> again = new ChoiceDialog<String>("Ok", "Yes", "No");
        again.setTitle("Game finished!");
        if (model.getBoard().hasCombination()) {
            again.setHeaderText(model.getAllPlayers().getActivePlayer().getName() + " has won!");
        } else {
            again.setHeaderText("The board is full!");
        }
        again.setContentText("Do you want to play again?");
        again.showAndWait();
        String result = again.getResult();
        if (result == null || result.equals("No")) {
            Platform.exit();
        } else {
            this.model = new Quarto(model.getAllPlayers().getPlayer1(), model.getAllPlayers().getPlayer2());
            model.choosePlayer();

            MainScreenView newView = new MainScreenView(uiSettings);
            view.getScene().setRoot(newView);
            new MainScreenPresenter(model, newView, uiSettings);
        }
    }

    protected void piecesEventHandlers() {
        for (int i = 0; i < view.getPiecesView().ROW_SIZE; i++) {
            for (int j = 0; j < view.getPiecesView().COL_SIZE; j++) {
                final int row = i;
                final int col = j;
                view.getPiecesView().getCircles()[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Piece piece = new Piece();
                        Circle circle = view.getPiecesView().getCircles()[row][col];
                        piece.setShape(Piece.Shape.ROUND);
                        if (circle.getFill() == view.getPiecesView().DEFAULT_COLOR  && circle.toString().length() < 70) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();
                        } else if (circle.getStroke() == view.getPiecesView().DEFAULT_COLOR  && circle.toString().length() > 70) {mouseEvent.consume(); }else {
                            if (circle.toString().length()>70){
                                piece.setFilling(Piece.Filling.EMPTY);
                                piece.setSize((circle.getRadius() == view.getPiecesView().BIG_SIZE_EMPTY? Piece.Size.BIG : Piece.Size.SMALL));
                                piece.setColor(circle.getStroke() == view.getPiecesView().BlUE_COLOR? Piece.Color.BLACK : Piece.Color.WHITE);
                            } else {
                                piece.setFilling(Piece.Filling.FULL);
                                piece.setSize((circle.getRadius() == view.getPiecesView().BIG_SIZE? Piece.Size.BIG : Piece.Size.SMALL));
                                piece.setColor(circle.getFill() == view.getPiecesView().BlUE_COLOR? Piece.Color.BLACK : Piece.Color.WHITE);
                            }
                            try {
                                model.choosePiece(piece);
                                updatePiecesView();
                                updateTurnStatusView();

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
                view.getPiecesView().getRectangles()[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Piece piece = new Piece();
                        Rectangle rectangle = view.getPiecesView().getRectangles()[row][col];
                        piece.setShape(Piece.Shape.SQUARE);
                        if (rectangle.getFill() == view.getPiecesView().DEFAULT_COLOR && rectangle.toString().length() < 70 ) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();
                        } else if (rectangle.getStroke() == view.getPiecesView().DEFAULT_COLOR && rectangle.toString().length() > 70 ) {
                            // consume event when clicked on preselected pieces
                            mouseEvent.consume();

                        } else {
                            if (rectangle.toString().length()>70){
                                piece.setFilling(Piece.Filling.EMPTY);
                                piece.setSize((rectangle.getWidth() == view.getPiecesView().BIG_SIZE_EMPTY*2? Piece.Size.BIG : Piece.Size.SMALL));
                                piece.setColor(rectangle.getStroke() == view.getPiecesView().BlUE_COLOR? Piece.Color.BLACK : Piece.Color.WHITE);
                            } else {
                                piece.setFilling(Piece.Filling.FULL);
                                piece.setSize((rectangle.getWidth() == view.getPiecesView().BIG_SIZE*2? Piece.Size.BIG : Piece.Size.SMALL));
                                piece.setColor(rectangle.getFill() == view.getPiecesView().BlUE_COLOR? Piece.Color.BLACK : Piece.Color.WHITE);
                            }
                            try {
                                model.choosePiece(piece);
                                updatePiecesView();
                                updateTurnStatusView();

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

    protected void playboardEventHandlers() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.getPlayboardView().getNodeByRowColumnIndex(i,j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        int rowIndex = GridPane.getRowIndex((Node) mouseEvent.getSource());
                        int colIndex = GridPane.getColumnIndex((Node) mouseEvent.getSource());
                        try {
                            model.placePiece(new Position(rowIndex,colIndex));
                            updatePlayboardView(rowIndex, colIndex);
                            updateTurnStatusView();
                            model.setChosenPiece(null);
                            view.setNode(model.getChosenPiece());

                        } catch (QuartoException e) {
                            final Alert pieceException = new Alert(Alert.AlertType.ERROR);
                            pieceException.setTitle("Error while using piece");
                            pieceException.setContentText(e.getMessage());
                            pieceException.showAndWait();
                            mouseEvent.consume();
                        } catch (IOException e) {
                            final Alert animationException = new Alert(Alert.AlertType.ERROR);
                            animationException.setTitle("Error with source file");
                            animationException.setContentText(e.getMessage());
                            animationException.showAndWait();
                            mouseEvent.consume();
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
                Alert returnToMainMenu = new Alert(Alert.AlertType.CONFIRMATION);
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
        view.getRankingItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RankingView rankingView = new RankingView();
                PlayerRanking playerRanking = new PlayerRanking();
                RankingPresenter rankingPresenter = new RankingPresenter(model,rankingView,uiSettings, playerRanking);
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
                LastGameView lastGameView = new LastGameView(uiSettings);
                model = new Quarto(false);
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
        stopWindow.setTitle("WARNING!");
        stopWindow.setHeaderText("You are about to close the application");
        stopWindow.setContentText("Are you sure, unsaved progress will be lost");
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

    public MainScreenView getView() {
        return view;
    }

    public Quarto getModel() {
        return model;
    }
}