package Quarto.View.LastGameScreen;

import Quarto.Model.Piece;
import Quarto.Model.Piece.*;
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


import java.io.IOException;

public class LastGamePresenter extends MainScreenPresenter {
    private LastGameView view;
    private Quarto model;

    private Timeline quatroTimeline;



    public LastGamePresenter(Quarto model, LastGameView view, UISettings uiSettings) {
        super(model, view, uiSettings);
        this.view = view;
        this.model = model;
        addEventHandlers();
        setupTimeline();
        updateView();
    }


    public void updateView() {

    }

    private void setupTimeline() {
        quatroTimeline = new Timeline();
        quatroTimeline.setCycleCount(model.getAnimationFileHandler().actions.size()-1);
        updateAnimation();
    }


    private void updateAnimation() {
        quatroTimeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String actiontoprint = model.getAnimationFileHandler().getAction();
                String[] action = actiontoprint.split("\\|");
                if (action[0].equals("blok")){
                    try {
                        model.kiesBlok(actionToBlok(action));
                    } catch (QuartoException | IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        LastGamePresenter.super.updateBlokkenBoxView();
                        LastGamePresenter.super.updateTurnView();
                    }
                } else if (action[0].equals("position")){
                    int rowIndex = Integer.parseUnsignedInt(action[1]);
                    int colIndex = Integer.parseUnsignedInt(action[2]);
                    try {

                        model.plaatsBlok(new Position(rowIndex,colIndex));
                        updateSpeelBordView(rowIndex, colIndex);
                        updateTurnView();
                        model.setGekozenBlok(null);
                        view.setNode(model.getGekozenBlok());

                    } catch (QuartoException | IOException e) {
                        final Alert noBlokChosen = new Alert(Alert.AlertType.ERROR);
                        noBlokChosen.setTitle("You cannot close the application yet.");
                        noBlokChosen.setContentText(e.getMessage());
                        noBlokChosen.showAndWait();

                    }

                } else {
                    if (model.isGameFinished()){
                        System.out.println("now show dialog");
                        try {
                            showFinishedDialog();
                        } catch (QuartoException exception) {
                            exception.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                }

                System.out.println(actiontoprint);

            }
        }));
    }

    @Override
    protected void updateSpeelBordView(int rowIndex, int colIndex) throws QuartoException, IOException {
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getGekozenBlok());


    }

    @Override
    protected void showFinishedDialog() throws QuartoException, IOException {
//        Log.debug("showing finished");
        if (!model.isGameFinished()) return;
        ChoiceDialog<String> again = new ChoiceDialog<String>("Ok", "Ok", "Nope");
        if (model.getSpeelbord().hasCombination()) {
            again.setTitle(model.getAlleSpelers().getActivePlayer().getName() + " has won!");
            again.setHeaderText(model.getAlleSpelers().getActivePlayer().getName() + " has won");
//            CombinationView combinationView = new CombinationView();
//            new CombinationPresenter(model.getRiddle(), combinationView);
//            again.setGraphic(combinationView);
        } else {
            again.setTitle("Playbord is full!");
            again.setHeaderText("Playbord is full!");
//            again.setGraphic(new ImageView("images/duim.png"));
//            again.setHeaderText("You found it in " + model.getNumberOfGuessesDone() + " moves...");
        }
        again.setContentText("You wanna replay again?");
        again.show();

//        String result = again.getResult();
//        if (result == null || result.equals("Nope")) {
//            Platform.exit();
//        } else {
//            model = new Quarto(Boolean.FALSE);
//            try {
//                model.setPlayerForAnimation();
//            } catch (IOException ioException) {
//                System.out.println("something went wrong with setting player for animation");
//            } catch (QuartoException exception) {
//                exception.printStackTrace();
//            }
////            MainScreenView newView = new MainScreenView(uiSettings);
////            view.getScene().setRoot(newView);
////            new MainScreenPresenter(model, newView, uiSettings);
//        }
    }

    public Piece actionToBlok (String[] action) {
        Piece piece = new Piece();
        if (action[1].equals("groot")){
            piece.setSize(Size.BIG);
        }else {
            piece.setSize(Size.SMALL);
        }

        if (action[2].equals("wit")){
            piece.setColor(Color.WHITE);
        }else {
            piece.setColor(Color.BLACK);
        }

        if (action[3].equals("rond")){
            piece.setShape(Shape.ROUND);
        }else {
            piece.setShape(Shape.SQUARE);
        }

        if (action[4].equals("hol")){
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

    public void addEventHandlers() {
        view.getPlayAnimation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(quatroTimeline.getStatus());
                if (quatroTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                    quatroTimeline.play();
                } else if (quatroTimeline.getStatus().equals(Animation.Status.PAUSED)){
                    quatroTimeline.play();
                } else {
                    quatroTimeline.pause();

                }
//                if (quatroTimeline.getStatus().equals(Animation.Status.PAUSED)) {
//                    quatroTimeline.play();
//                    updateAnimation();
//                } else {
//                    quatroTimeline.pause();
//                }
            }
        });
    }
}
