package Quarto.View.LastGameView;

import Quarto.Model.Quarto;
import Quarto.View.MainScreen.MainScreenPresenter;
import Quarto.View.MainScreen.MainScreenView;
import Quarto.View.UISettings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class LastGamePresenter2 extends MainScreenPresenter {
    private LastGameView2 view2;

    private Timeline stopwatchTimeline;



    public LastGamePresenter2(Quarto model, LastGameView2 view, UISettings uiSettings) {
        super(model, view, uiSettings);
        this.view2 = view;
        addEventHandlers();
        setupTimeline();
        updateView();
    }

    public void updateView() {

    }

    private void setupTimeline() {
        stopwatchTimeline = new Timeline();
        stopwatchTimeline.setCycleCount(Animation.INDEFINITE);
//        updateClockSpeed();
    }


    // Basis-opstelling: constante snelheid

//    private void setupTimelineBasis() {
//        stopwatchTimeline = new Timeline(new KeyFrame(
//                Duration.millis(1000), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                model.tick();
//                updateView();
//            }
//        }));
//        stopwatchTimeline.setCycleCount(Animation.INDEFINITE);
//    }



//    private void updateClockSpeed() {
//        stopwatchTimeline.getKeyFrames().clear();
//        stopwatchTimeline.getKeyFrames().add(new KeyFrame(
//                Duration.millis(1000, new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                super.;
//                updateView();
//            }
//        }));
//    }

    public void addEventHandlers() {
        view2.getPlayAnimation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stopwatchTimeline.getStatus().equals(Animation.Status.PAUSED)) {
                    stopwatchTimeline.play();
                } else {
                    stopwatchTimeline.play();
                }
            }
        });
    }


}
