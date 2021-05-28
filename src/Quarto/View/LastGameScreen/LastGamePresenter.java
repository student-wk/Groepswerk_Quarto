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
import javafx.util.Duration;


/**
 * This presenter is a subclass of MainScreenPresenter to control the animation view which is a subclass of MainScreenView
 * Makes use of timeline to pause actions (placing of pieces on a playboard or choosing pieces to place)
 *
 * @author Delawar Jalat
 * @version 1.0 24-4-2021 14:26
 */

import java.io.IOException;

public class LastGamePresenter extends MainScreenPresenter {
    private LastGameView view;
    private GameStatus gameStatus;

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

    @Override
    protected void updatePlayboardView(int rowIndex, int colIndex)  {
        view.getPlayboardView().addPiece(rowIndex, colIndex, model.getChosenPiece());
    }


    private void updateAnimation() {
        quatroTimeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String actiontoprintrint = model.getAnimationFileHandler().getAction();
                String[] action = actiontoprintrint.split("\\|");
                switch (action[0]) {
                    case "piece":
                        if (quatroTimeline.getCycleCount() == model.getAnimationFileHandler().getActions().size()-1){
                            gameStatus = GameStatus.INCOMPLETE;
                        }
                        try {
                            model.choosePiece(actionToPiece(action));
                            updatePiecesView();
                            updateTurnStatusView();
                        } catch (QuartoException | IOException exception) {
                            //DO nothing, this is an animation
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
                            updatePlayboardView(rowIndex, colIndex);
                            updateTurnStatusView();
                            model.setChosenPiece(null);
                            view.setNode(model.getChosenPiece());
                        } catch (QuartoException | IOException e) {
                            //Do nothing, this is an animation
                        }
                        break;
                    case "gamefinished":
                        gameStatus = GameStatus.GAME_FINISHED;
                        break;
                    default:
                        gameStatus = GameStatus.INCOMPLETE;
                }

            }
        }));
        quatroTimeline.setOnFinished(finished -> {
                showFinishedDialog();
                quatroTimeline.stop();
        });

    }

    @Override
    protected void showFinishedDialog()  {
        Alert gameFinished = new Alert(Alert.AlertType.INFORMATION);
        gameFinished.setTitle("Game Finished");
        gameFinished.setContentText("Press \"Replay Button\" to replay");
        if (gameStatus.equals(GameStatus.GAME_FINISHED)){
            if (model.getBoard().hasCombination()) {
                gameFinished.setHeaderText(model.getAllPlayers().getActivePlayer().getName() + " won");
            } else {
                gameFinished.setHeaderText("Playboard is full!");
            }
        } else if (gameStatus.equals(GameStatus.INCOMPLETE)){
            gameFinished.setHeaderText("Game not completed");
        }
        gameFinished.show();
        view.getPlayAnimation().setText("Replay?");
    }


    public void addEventHandlers() {
        view.getPlayAnimation().setOnAction(actionEvent -> {
            if (quatroTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                if (gameStatus.equals(GameStatus.GAME_FINISHED) || gameStatus.equals(GameStatus.INCOMPLETE)){
                    resetPlay();
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

    private void resetPlay() {
        this.gameStatus = GameStatus.NOT_SET;
        model.reset();
        view.initialiseNodes();
        view.setPlayAnimation("Replay?");
        view.layoutNodes();
        addEventHandlers();
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
    protected void piecesEventHandlers() {
    }

    @Override
    protected void playboardEventHandlers() {
    }


}
