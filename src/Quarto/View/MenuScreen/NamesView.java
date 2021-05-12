package Quarto.View.MenuScreen;

import Quarto.View.UISettings;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NamesView extends GridPane {
    private VBox namesVBox;
    private TextField player1TextField;
    private TextField player2TextField;
    private Label player1Label;
    private Label player2Label;
    private Button oneVoneButton;
    private Button startButton;
    private UISettings uiSettings;


//    private CheckBox checkBox;
//    private ImageView imageView;
//    private ComboBox<String> comboBox;
//    private MenuItem menuItem;

    public NamesView(UISettings uiSettings) {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        player1TextField = new TextField("Enter Name");
        player2TextField = new TextField("Enter Name");

        player1Label = new Label("Name player 1");
        player2Label = new Label("Name player 2");

        oneVoneButton = new Button("One V One");
        startButton = new Button("Start");


    }

    public void layoutNodes() {

//        this.add(oneVoneButton, 0, 0);
        this.add(player1Label, 0, 2);
        this.add(player2Label,0, 3);
        this.add(player1TextField, 1, 2);
        this.add(player2TextField, 1, 3);
        this.add(startButton, 0, 4, 1, 2);

    }

    public Button getStartButton() {
        return startButton;
    }

    public TextField getPlayer1TextField() {
        return player1TextField;
    }

    public TextField getPlayer2TextField() {
        return player2TextField;
    }
}