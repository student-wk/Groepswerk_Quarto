package Quarto.View.LastGameScreen;

import Quarto.Model.Piece.*;
import Quarto.Model.Piece;
import Quarto.Model.Position;
import Quarto.Model.Quarto;
import Quarto.Model.QuartoException;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.UISettings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.util.Duration;
// for commit


import java.io.IOException;

public class LastGamePresenter extends MainScreenPresenter {
    private LastGameView view;
    private GameStatus gameStatus;
//    private ChoiceDialog<String> again;

    private Timeline quatroTimeline;

    public enum GameStatus{
        GAME_FINISHED, INCOMPLETE, NOT_SET
    }

    public LastGamePresenter(Quarto model, LastGameView view, UISettings uiSettings) {
        super(model, view, uiSettings);
        this.view = view;
        this.gameStatus = GameStatus.NOT_SET;
        addEventHandlers();
        setupTimeline();
    }

    private void setupTimeline() {
        quatroTimeline = new Timeline();
        quatroTimeline.setCycleCount(model.getAnimationFileHandler().actions.size()-1);
        updateAnimation();
    }

    private void updateAnimation() {
        quatroTimeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String actiontoprint = model.getAnimationFileHandler().getAction();
                String[] action = actiontoprint.split("\\|");
                System.out.println(model.getAnimationFileHandler().getCOUNT());
                System.out.println(quatroTimeline.getCycleCount());
                switch (action[0]) {
                    case "piece":
                        if (quatroTimeline.getCycleCount() == model.getAnimationFileHandler().getActions().size()-1){
                            gameStatus = GameStatus.INCOMPLETE;
                        }
                        try {
                            model.choosePiece(actionToPiece(action));
                            updateBlokkenBoxView();
                            updateTurnView();
                        } catch (QuartoException | IOException exception) {
                            exception.printStackTrace();
//                            final Alert noBlokChosen = new Alert(Alert.AlertType.INFORMATION);
//                            noBlokChosen.setTitle("Place a piece on the playbord.");
//                            noBlokChosen.setContentText(exception.getMessage());
//                            noBlokChosen.show();
                        }
                        break;
                    case "position":
                        if (quatroTimeline.getCycleCount() == model.getAnimationFileHandler().getActions().size()-1){
                            gameStatus = GameStatus.INCOMPLETE;
                        }
                        int rowIndex = Integer.parseUnsignedInt(action[1]);
                        int colIndex = Integer.parseUnsignedInt(action[2]);
                        try {
                            model.placePiece(new Position(rowIndex, colIndex));
                            System.out.println(model.getBoard().toString());
                            updateSpeelBordView(rowIndex, colIndex);
                            updateTurnView();
                            model.setChosenPiece(null);
                            view.setNode(model.getChosenPiece());
                        } catch (QuartoException | IOException e) {
                            final Alert noBlokChosen = new Alert(Alert.AlertType.ERROR);
                            noBlokChosen.setTitle("You cannot close the application yet.");
                            noBlokChosen.setContentText(e.getMessage());
                            noBlokChosen.show();
                        }
                        break;
                    case "gamefinished":
                        gameStatus = GameStatus.GAME_FINISHED;
                        break;
                }
                System.out.println(actiontoprint);
                System.out.println(gameStatus);
            }
        }));
        quatroTimeline.setOnFinished(finished -> {
            try {
                showFinishedDialog();
                quatroTimeline.stop();
            } catch (QuartoException exception) {
                exception.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }


    public void addEventHandlers() {
        view.getPlayAnimation().setOnAction(actionEvent -> {
            System.out.println(quatroTimeline.getStatus());
            if (quatroTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                if (gameStatus.equals(GameStatus.GAME_FINISHED) || gameStatus.equals(GameStatus.INCOMPLETE)){
                    System.out.println("entered resetPlay");
                    resetPlaz();
                    quatroTimeline.play();
                } else {
                    quatroTimeline.play();
                }
            } else if (quatroTimeline.getStatus().equals(Animation.Status.PAUSED)) {
                quatroTimeline.play();
            } else {
                quatroTimeline.pause();
            }
        });
    }

    private void resetPlaz() {
        System.out.println("start replay");
        this.gameStatus = GameStatus.NOT_SET;
        model.reset();
        view.initialiseNodes();
        view.setPlayAnimation("Replay");
        view.layoutNodes();
        addEventHandlers();
    }



    @Override
    protected void updateSpeelBordView(int rowIndex, int colIndex) throws QuartoException, IOException {
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getChosenPiece());
    }

    //
    @Override
    protected void showFinishedDialog() throws QuartoException, IOException {

        Alert gameFinished = new Alert(Alert.AlertType.CONFIRMATION);
        gameFinished.setTitle("Game Finished");
        gameFinished.setContentText("Press Replay Button to replay");
        if (gameStatus.equals(GameStatus.GAME_FINISHED)){
            if (model.getBoard().hasCombination()) {
                gameFinished.setHeaderText(model.getAllPlayers().getActivePlayer().getName() + " won");
            } else {
                gameFinished.setHeaderText("Playbord is full!");
            }
        } else if (gameStatus.equals(GameStatus.INCOMPLETE)){
            gameFinished.setHeaderText("Game not completed");
        }
        gameFinished.show();
        view.getPlayAnimation().setText("Replay?");
    }

    public Piece actionToPiece (String[] action) {
        Piece piece = new Piece();
        if (action[1].equals("big")){
            piece.setSize(Size.BIG);
        }else {
            piece.setSize(Size.SMALL);
        }

        if (action[2].equals("white")){
            piece.setColor(Color.WHITE);
        }else {
            piece.setColor(Color.BLACK);
        }

        if (action[3].equals("round")){
            piece.setShape(Shape.ROUND);
        }else {
            piece.setShape(Shape.SQUARE);
        }

        if (action[4].equals("empty")){
            piece.setFilling(Filling.EMPTY);
        }else {
            piece.setFilling(Filling.FULL);
        }
        return piece;
    }


    @Override
    protected void blokkenBoxEventHandlers() {
    }

    @Override
    protected void speelBordEventHandlers() {
    }


}
