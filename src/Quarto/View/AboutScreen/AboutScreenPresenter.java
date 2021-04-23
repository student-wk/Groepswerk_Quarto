package Quarto.View.AboutScreen;

import Quarto.Model.*;
import Quarto.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AboutScreenPresenter {

    private Quarto model;
    private AboutScreenView view;
    private UISettings uiSettings;

    public AboutScreenPresenter(Quarto model, AboutScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getBtnOk().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getScene().getWindow().hide();
            }
        });
    }
}
