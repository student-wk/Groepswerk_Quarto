package Quarto.View.NamesScreen;

import Quarto.View.UISettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class NamesView extends BorderPane {
    private GridPane gridPane;
    private Label player1Label;
    private Label player2Label;
    private Button oneVoneButton;
    private Button startButton;
    private UISettings uiSettings;

    private ObservableList<String> listNames;
    private ComboBox<String> player1ComboBox;
    private ComboBox<String> player2ComboBox;

    public NamesView(UISettings uiSettings) {
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes() {
        this.gridPane = new GridPane();

        this.player1Label = new Label("Name player 1");
        this.player2Label = new Label("Name player 2");

        this.oneVoneButton = new Button("One VS One");
        this.startButton = new Button("Start");

        this.player1ComboBox = new ComboBox<>();
        this.player2ComboBox = new ComboBox<>();
        this.listNames = FXCollections.observableArrayList();
        this.player1ComboBox.setItems(listNames);
        this.player2ComboBox.setItems(listNames);
        this.player1ComboBox.setEditable(true);
        this.player2ComboBox.setEditable(true);
        this.player1ComboBox.setPromptText("choose/create a name");
        this.player2ComboBox.setPromptText("choose/create a name");
        this.player1ComboBox.setVisibleRowCount(5);
        this.player2ComboBox.setVisibleRowCount(5);
    }

    public void layoutNodes() {
        player2Label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        player1Label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        player1Label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
        player2Label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));

        this.setPadding(new Insets(20));
        this.startButton.setPrefSize(100,20);
        this.player1Label.setPrefSize(110,20);
        this.player2Label.setPrefSize(110,20);
        this.player1ComboBox.setPrefSize(200,20);
        this.player2ComboBox.setPrefSize(200,20);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(player1Label, 0, 2);
        gridPane.add(player2Label,0, 3);

        gridPane.add(player1ComboBox,1,2);
        gridPane.add(player2ComboBox,1,3);

        gridPane.add(new Label(""), 0,4 );
        gridPane.add(startButton, 1, 5, 1, 1);
        gridPane.setGridLinesVisible(false);

        this.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);


    }

    public Button getStartButton() {
        return startButton;
    }

    public ComboBox<String> getPlayer1ComboBox() {
        return player1ComboBox;
    }

    public ComboBox<String> getPlayer2ComboBox() {
        return player2ComboBox;
    }

    public ObservableList<String> getListNames() {
        return listNames;
    }
}