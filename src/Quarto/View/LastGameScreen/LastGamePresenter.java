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
                Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String actiontoprint = model.getAnimationFileHandler().getAction();
                String[] action = actiontoprint.split("\\|");
                if (action[0].equals("blok")){
                    try {
                        model.choosePiece(actionToBlok(action));
                    } catch (QuartoException | IOException exception) {
                        exception.printStackTrace();
                    } finally {
                        LastGamePresenter.super.updateBlokkenBoxView();
                        LastGamePresenter.super.updateTurnView();
                    }
                } else if (action[0].equals("positie")){
                    int rowIndex = Integer.parseUnsignedInt(action[1]);
                    int colIndex = Integer.parseUnsignedInt(action[2]);
                    try {

                        model.placePiece(new Position(rowIndex,colIndex));
                        updateSpeelBordView(rowIndex, colIndex);
                        updateTurnView();
                        model.setChosenPiece(null);
                        view.setNode(model.getChosenPiece());

                    } catch (QuartoException | IOException e) {
                        final Alert noBlokChosen = new Alert(Alert.AlertType.ERROR);
                        noBlokChosen.setTitle("You cannot close the application yet.");
                        noBlokChosen.setContentText(e.getMessage());
                        noBlokChosen.showAndWait();

                    }

                } else if (action[0].equals("gamefinished")){
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

                    quatroTimeline.stop();
                }

                System.out.println(actiontoprint);

            }
        }));

    }

    @Override
    protected void updateSpeelBordView(int rowIndex, int colIndex) throws QuartoException, IOException {
        view.getSpeelBordView().voegBlokToe(rowIndex, colIndex, model.getChosenPiece());
    }

    @Override
    protected void showFinishedDialog() throws QuartoException, IOException {
//        Log.debug("showing finished");
        if (!model.isGameFinished()) return;
        Alert gameFinished = new Alert(Alert.AlertType.INFORMATION);

//        ChoiceDialog<String> again = new ChoiceDialog<String>("Ok", "Ok", "Nope");
        if (model.getBoard().hasCombination()) {
            gameFinished.setTitle( "Game finished!");
            gameFinished.setContentText(model.getAllPlayers().getActivePlayer().getName() + " won");
        } else {
            gameFinished.setTitle("Game finished!");
            gameFinished.setContentText("Playbord is full!");
        }
        gameFinished.show();


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
        view.getPlayAnimation().setOnAction(actionEvent -> {
            System.out.println(quatroTimeline.getStatus());
            if (quatroTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                quatroTimeline.play();
            } else if (quatroTimeline.getStatus().equals(Animation.Status.PAUSED)) {
                quatroTimeline.play();
            } else {
                quatroTimeline.pause();

            }
            System.out.println(quatroTimeline.getStatus().name());
        });
    }
}
