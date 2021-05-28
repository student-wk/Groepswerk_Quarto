package Quarto.View.LastGameScreen;

import Quarto.Model.Blok;
import Quarto.Model.Blok.*;
import Quarto.Model.Positie;
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
                    case "blok":
                        if (quatroTimeline.getCycleCount() == model.getAnimationFileHandler().getActions().size()-1){
                            gameStatus = GameStatus.INCOMPLETE;
                        }
                        try {
                            model.kiesBlok(actionToBlok(action));
                            updateBlokkenBoxView();
                            updateTurnView();
                        } catch (QuartoException | IOException exception) {
                            final Alert noBlokChosen = new Alert(Alert.AlertType.INFORMATION);
                            noBlokChosen.setTitle("Place a piece on the playbord.");
                            noBlokChosen.setContentText(exception.getMessage());
                            noBlokChosen.show();
                        }
                        break;
                    case "positie":
                        if (quatroTimeline.getCycleCount() == model.getAnimationFileHandler().getActions().size()-1){
                            gameStatus = GameStatus.INCOMPLETE;
                        }
                        int rowIndex = Integer.parseUnsignedInt(action[1]);
                        int colIndex = Integer.parseUnsignedInt(action[2]);
                        try {
                            model.plaatsBlok(new Positie(rowIndex, colIndex));
                            updateSpeelBordView(rowIndex, colIndex);
                            updateTurnView();
                            model.setGekozenBlok(null);
                            view.setNode(model.getGekozenBlok());
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
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getGekozenBlok());
    }

    @Override
    protected void showFinishedDialog() throws QuartoException, IOException {

        Alert gameFinished = new Alert(Alert.AlertType.CONFIRMATION);
        gameFinished.setTitle("Game Finished");
        gameFinished.setContentText("Press Replay Button to replay");
        if (gameStatus.equals(GameStatus.GAME_FINISHED)){
            if (model.getSpeelbord().heeftCombinatie()) {
                gameFinished.setHeaderText(model.getAlleSpelers().getActieveSpeler().getNaam() + " won");
            } else {
                gameFinished.setHeaderText("Playbord is full!");
            }
        } else if (gameStatus.equals(GameStatus.INCOMPLETE)){
            gameFinished.setHeaderText("Game not completed");
        }
        gameFinished.show();
        view.getPlayAnimation().setText("Replay?");
    }

    public Blok actionToBlok (String[] action) {
        Blok blok = new Blok();
        if (action[1].equals("groot")){
            blok.setGrootte(Grootte.GROOT);
        }else {
            blok.setGrootte(Grootte.KLEIN);
        }

        if (action[2].equals("wit")){
            blok.setKleur(Kleur.WIT);
        }else {
            blok.setKleur(Kleur.ZWART);
        }

        if (action[3].equals("rond")){
            blok.setVorm(Vorm.ROND);
        }else {
            blok.setVorm(Vorm.VIERKANT);
        }

        if (action[4].equals("hol")){
            blok.setVulling(Vulling.HOL);
        }else {
            blok.setVulling(Vulling.VOL);
        }
        return blok;
    }

    @Override
    protected void blokkenBoxEventHandlers() {
    }

    @Override
    protected void speelBordEventHandlers() {
    }


}
