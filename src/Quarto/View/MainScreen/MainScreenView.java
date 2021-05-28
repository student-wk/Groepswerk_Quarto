package Quarto.View.MainScreen;

import Quarto.Model.Piece;
import Quarto.View.UISettings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem settingsMI;
    private MenuItem rankingMI;
    private MenuItem lastGameMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;
    private Node chosenPiece;

    private PlayboardView playboardView;
    private PiecesView piecesView;
    private Label turnLabel;
    private Label pieceChosenLabel;

    private GridPane infoGridpane;
    private final int infoGridpaneColIndex = 1;



    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    protected void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.settingsMI = new MenuItem("Main Menu");
        this.rankingMI = new MenuItem("Ranking");
        this.lastGameMI = new MenuItem("Last Game");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.playboardView = new PlayboardView();
        this.piecesView = new PiecesView();

        this.turnLabel = new Label();
        this.turnLabel.setMaxWidth(200);
        this.turnLabel.setMaxHeight(30);
        this.turnLabel.setStyle("-fx-background-color: orange");
        this.turnLabel.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) ));

        this.pieceChosenLabel = new Label("Chosen Piece");
        this.pieceChosenLabel.setMaxWidth(200);
        this.pieceChosenLabel.setMaxHeight(30);
        this.pieceChosenLabel.setStyle("-fx-background-color: orange");
        this.pieceChosenLabel.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) ));

        this.infoGridpane = new GridPane();
        this.infoGridpane.setHgap(20);
        this.infoGridpane.setAlignment(Pos.CENTER_LEFT);
        this.infoGridpane.getRowConstraints().addAll(new RowConstraints(60), new RowConstraints(60));
        this.infoGridpane.getColumnConstraints().addAll(new ColumnConstraints(200), new ColumnConstraints(200));

        this.chosenPiece = new Circle(playboardView.BIG_SIZE, playboardView.DEFAULT_COLOR);
    }


    public void setNode(Piece chosenPiece) {
        infoGridpane.getChildren().remove(this.chosenPiece);
        if (chosenPiece == null) {
            this.chosenPiece = new Circle(playboardView.BIG_SIZE, playboardView.DEFAULT_COLOR);
        } else if (chosenPiece.getShape().equals(Piece.Shape.ROUND)){
            Circle circle = new Circle();
            if (chosenPiece.getColor().equals(Piece.Color.WHITE)){
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(playboardView.BIG_SIZE);
                    circle.setFill(playboardView.RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    circle.setRadius(playboardView.SMALL_SIZE);
                    circle.setFill(playboardView.RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(playboardView.BIG_SIZE_EMPTY);
                    circle.setStroke(playboardView.RED_COLOR);
                    circle.setFill(playboardView.DEFAULT_COLOR);
                    circle.setStrokeWidth(playboardView.STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(playboardView.SMALL_SIZE_EMPTY);
                    circle.setStroke(playboardView.RED_COLOR);
                    circle.setFill(playboardView.DEFAULT_COLOR);
                    circle.setStrokeWidth(playboardView.STROKE_WIDTH_SMALL);
                }
            } else {
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(playboardView.BIG_SIZE);
                    circle.setFill(playboardView.BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    circle.setRadius(playboardView.SMALL_SIZE);
                    circle.setFill(playboardView.BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    circle.setRadius(playboardView.BIG_SIZE_EMPTY);
                    circle.setStroke(playboardView.BlUE_COLOR);
                    circle.setFill(playboardView.DEFAULT_COLOR);
                    circle.setStrokeWidth(playboardView.STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(playboardView.SMALL_SIZE_EMPTY);
                    circle.setStroke(playboardView.BlUE_COLOR);
                    circle.setFill(playboardView.DEFAULT_COLOR);
                    circle.setStrokeWidth(playboardView.STROKE_WIDTH_SMALL);
                }
            }
            this.chosenPiece = circle;
        } else {
            Rectangle rectangle = new Rectangle();
            if (chosenPiece.getColor().equals(Piece.Color.WHITE)){
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    rectangle.setFill(playboardView.RED_COLOR);
                    rectangle.setWidth(playboardView.BIG_SIZE*2);
                    rectangle.setHeight(playboardView.BIG_SIZE*2);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    rectangle.setHeight(playboardView.SMALL_SIZE*2);
                    rectangle.setWidth(playboardView.SMALL_SIZE*2);
                    rectangle.setFill(playboardView.RED_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    rectangle.setWidth(playboardView.BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(playboardView.BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(playboardView.RED_COLOR);
                    rectangle.setFill(playboardView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(playboardView.STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(playboardView.SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(playboardView.SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(playboardView.RED_COLOR);
                    rectangle.setFill(playboardView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(playboardView.STROKE_WIDTH_SMALL);
                }
            } else {
                if (chosenPiece.getFilling().equals(Piece.Filling.FULL)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    rectangle.setFill(playboardView.BlUE_COLOR);
                    rectangle.setWidth(playboardView.BIG_SIZE*2);
                    rectangle.setHeight(playboardView.BIG_SIZE*2);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.FULL) && chosenPiece.getSize().equals(Piece.Size.SMALL)){
                    rectangle.setHeight(playboardView.SMALL_SIZE*2);
                    rectangle.setWidth(playboardView.SMALL_SIZE*2);
                    rectangle.setFill(playboardView.BlUE_COLOR);
                } else if (chosenPiece.getFilling().equals(Piece.Filling.EMPTY)&& chosenPiece.getSize().equals(Piece.Size.BIG)){
                    rectangle.setWidth(playboardView.BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(playboardView.BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(playboardView.BlUE_COLOR);
                    rectangle.setFill(playboardView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(playboardView.STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(playboardView.SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(playboardView.SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(playboardView.BlUE_COLOR);
                    rectangle.setFill(playboardView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(playboardView.STROKE_WIDTH_SMALL);
                }
            }
            this.chosenPiece = rectangle;
        }
        infoGridpane.add(this.chosenPiece, infoGridpaneColIndex,1);
        GridPane.setConstraints(this.chosenPiece, infoGridpaneColIndex, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

    }


    protected void layoutNodes() {
        Menu menuFile = new Menu("File",null, settingsMI,rankingMI,lastGameMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setRight(playboardView);
        setLeft(piecesView);
        setMargin(piecesView, new Insets(10));

        setBottom(infoGridpane);

        infoGridpane.add(turnLabel, 0,0);
        GridPane.setConstraints(turnLabel, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        infoGridpane.add(pieceChosenLabel, infoGridpaneColIndex,0);
        GridPane.setConstraints(pieceChosenLabel, infoGridpaneColIndex, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        infoGridpane.add(this.chosenPiece, infoGridpaneColIndex,1);
        GridPane.setConstraints(this.chosenPiece, infoGridpaneColIndex, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        infoGridpane.setGridLinesVisible(true);
        infoGridpane.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
    }

    public Label getTurnLabel() {
        return turnLabel;
    }

    public PiecesView getPiecesView() {
        return piecesView;
    }

    public PlayboardView getPlayboardView() {
        return playboardView;
    }

    MenuItem getExitItem() {return exitMI;}

    MenuItem getSettingsItem() {return settingsMI;}

    MenuItem getAboutItem() {return aboutMI;}

    MenuItem getInfoItem() {return infoMI;}

    public MenuItem getRankingItem() {
        return rankingMI;
    }

    public MenuItem getLastGameItem() {
        return lastGameMI;
    }

    public GridPane getInfoGridpane() {
        return infoGridpane;
    }
}
