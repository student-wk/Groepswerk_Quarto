package Quarto.View.MainScreen;

import Quarto.Model.Blok;
import Quarto.View.UISettings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MainScreenView extends BorderPane  {

    private MenuItem exitMI;
    private MenuItem settingsMI;
    private MenuItem rankingMI;
    private MenuItem lastGameMI;
    private MenuItem aboutMI;
    private MenuItem infoMI;
    private UISettings uiSettings;
    private Node gekozenBlok;

    private SpeelBordView speelBordView;
    private BlokkenBoxView blokkenBoxView;
    private Label turnLabel;
    private Label pieceChosenLabel;

    private GridPane infoGridpane;
    private final int infoGridpaneColIndex = 1;



    public MainScreenView(UISettings uiSettings) {
        this.uiSettings = uiSettings;
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.exitMI = new MenuItem("Exit");
        this.settingsMI = new MenuItem("Main Menu");
        this.rankingMI = new MenuItem("Ranking");
        this.lastGameMI = new MenuItem("Last Game");
        this.aboutMI = new MenuItem("About");
        this.infoMI = new MenuItem("Info");
        this.speelBordView = new SpeelBordView();
        this.blokkenBoxView = new BlokkenBoxView();

        this.turnLabel = new Label();
        this.turnLabel.setMaxWidth(200);
        this.turnLabel.setMaxHeight(30);
        this.turnLabel.setStyle("-fx-background-color: orange");
        this.turnLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) ));

        this.pieceChosenLabel = new Label("Chosen Piece");
        this.pieceChosenLabel.setMaxWidth(200);
        this.pieceChosenLabel.setMaxHeight(30);
        this.pieceChosenLabel.setStyle("-fx-background-color: orange");
        this.pieceChosenLabel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT) ));

        this.infoGridpane = new GridPane();
        this.infoGridpane.setHgap(20);
        this.infoGridpane.setAlignment(Pos.CENTER_LEFT);
        this.infoGridpane.getRowConstraints().addAll(new RowConstraints(60), new RowConstraints(60));
        this.infoGridpane.getColumnConstraints().addAll(new ColumnConstraints(200), new ColumnConstraints(200));

        this.gekozenBlok = new Circle(speelBordView.BIG_SIZE,speelBordView.DEFAULT_COLOR);
    }


    public void setNode(Blok gekozenBlok) {
        infoGridpane.getChildren().remove(this.gekozenBlok);
        if (gekozenBlok == null) {
            this.gekozenBlok = new Circle(speelBordView.BIG_SIZE,speelBordView.DEFAULT_COLOR);
        } else if (gekozenBlok.getVorm().equals(Blok.Vorm.ROND)){
            Circle circle = new Circle();
            if (gekozenBlok.getKleur().equals(Blok.Kleur.WIT)){
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(speelBordView.BIG_SIZE);
                    circle.setFill(speelBordView.RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    circle.setRadius(speelBordView.SMALL_SIZE);
                    circle.setFill(speelBordView.RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(speelBordView.BIG_SIZE_EMPTY);
                    circle.setStroke(speelBordView.RED_COLOR);
                    circle.setFill(speelBordView.DEFAULT_COLOR);
                    circle.setStrokeWidth(speelBordView.STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(speelBordView.SMALL_SIZE_EMPTY);
                    circle.setStroke(speelBordView.RED_COLOR);
                    circle.setFill(speelBordView.DEFAULT_COLOR);
                    circle.setStrokeWidth(speelBordView.STROKE_WIDTH_SMALL);
                }
            } else {
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(speelBordView.BIG_SIZE);
                    circle.setFill(speelBordView.BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    circle.setRadius(speelBordView.SMALL_SIZE);
                    circle.setFill(speelBordView.BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    circle.setRadius(speelBordView.BIG_SIZE_EMPTY);
                    circle.setStroke(speelBordView.BlUE_COLOR);
                    circle.setFill(speelBordView.DEFAULT_COLOR);
                    circle.setStrokeWidth(speelBordView.STROKE_WIDTH_BIG);
                } else {
                    circle.setRadius(speelBordView.SMALL_SIZE_EMPTY);
                    circle.setStroke(speelBordView.BlUE_COLOR);
                    circle.setFill(speelBordView.DEFAULT_COLOR);
                    circle.setStrokeWidth(speelBordView.STROKE_WIDTH_SMALL);
                }
            }
            this.gekozenBlok= circle;
        } else {
            Rectangle rectangle = new Rectangle();
            if (gekozenBlok.getKleur().equals(Blok.Kleur.WIT)){
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setFill(speelBordView.RED_COLOR);
                    rectangle.setWidth(speelBordView.BIG_SIZE*2);
                    rectangle.setHeight(speelBordView.BIG_SIZE*2);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    rectangle.setHeight(speelBordView.SMALL_SIZE*2);
                    rectangle.setWidth(speelBordView.SMALL_SIZE*2);
                    rectangle.setFill(speelBordView.RED_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setWidth(speelBordView.BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(speelBordView.BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(speelBordView.RED_COLOR);
                    rectangle.setFill(speelBordView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(speelBordView.STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(speelBordView.SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(speelBordView.SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(speelBordView.RED_COLOR);
                    rectangle.setFill(speelBordView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(speelBordView.STROKE_WIDTH_SMALL);
                }
            } else {
                if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setFill(speelBordView.BlUE_COLOR);
                    rectangle.setWidth(speelBordView.BIG_SIZE*2);
                    rectangle.setHeight(speelBordView.BIG_SIZE*2);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.VOL) && gekozenBlok.getGrootte().equals(Blok.Grootte.KLEIN)){
                    rectangle.setHeight(speelBordView.SMALL_SIZE*2);
                    rectangle.setWidth(speelBordView.SMALL_SIZE*2);
                    rectangle.setFill(speelBordView.BlUE_COLOR);
                } else if (gekozenBlok.getVulling().equals(Blok.Vulling.HOL)&& gekozenBlok.getGrootte().equals(Blok.Grootte.GROOT)){
                    rectangle.setWidth(speelBordView.BIG_SIZE_EMPTY*2);
                    rectangle.setHeight(speelBordView.BIG_SIZE_EMPTY*2);
                    rectangle.setStroke(speelBordView.BlUE_COLOR);
                    rectangle.setFill(speelBordView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(speelBordView.STROKE_WIDTH_BIG);
                } else {
                    rectangle.setWidth(speelBordView.SMALL_SIZE_EMPTY*2);
                    rectangle.setHeight(speelBordView.SMALL_SIZE_EMPTY*2);
                    rectangle.setStroke(speelBordView.BlUE_COLOR);
                    rectangle.setFill(speelBordView.DEFAULT_COLOR);
                    rectangle.setStrokeWidth(speelBordView.STROKE_WIDTH_SMALL);
                }
            }
            this.gekozenBlok= rectangle;
        }
        infoGridpane.add(this.gekozenBlok, infoGridpaneColIndex,1);
        GridPane.setConstraints(this.gekozenBlok, infoGridpaneColIndex, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

    }


    private void layoutNodes() {
        Menu menuFile = new Menu("File",null, settingsMI,rankingMI,lastGameMI, new SeparatorMenuItem(),exitMI);
        Menu menuHelp = new Menu("Help",null, aboutMI, infoMI);
        MenuBar menuBar = new MenuBar(menuFile,menuHelp);
        setTop(menuBar);
        setRight(speelBordView);
        setLeft(blokkenBoxView);
        setMargin(blokkenBoxView, new Insets(10));

        setBottom(infoGridpane);

        infoGridpane.add(turnLabel, 0,0);
        GridPane.setConstraints(turnLabel, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        infoGridpane.add(pieceChosenLabel, infoGridpaneColIndex,0);
        GridPane.setConstraints(pieceChosenLabel, infoGridpaneColIndex, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
        infoGridpane.add(this.gekozenBlok, infoGridpaneColIndex,1);
        GridPane.setConstraints(this.gekozenBlok, infoGridpaneColIndex, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);

        infoGridpane.setGridLinesVisible(true);
        infoGridpane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
    }

    public Label getTurnLabel() {
        return turnLabel;
    }

    public BlokkenBoxView getBlokkenBoxView() {
        return blokkenBoxView;
    }

    public SpeelBordView getSpeelBordView() {
        return speelBordView;
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
