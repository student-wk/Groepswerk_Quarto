package Quarto.Model;

import java.io.IOException;

public class Quarto {
    private final Pieces pieces;
    private final Board board;
    private Piece chosenPiece;
    private AllPLayers allPLayers;
    private boolean gameFinished;
    private boolean flipAction;
    private Boolean animation = true;
    private PlayerRanking playerRanking;
    private AnimationFileHandler animationFileHandler;


    public Quarto() {
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        animationFileHandler = new AnimationFileHandler();
    }

    public Quarto(Boolean doNotAnimate) {
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        this.animation = doNotAnimate;
        animationFileHandler = new AnimationFileHandler();
    }

    public Quarto(Player player1, Player player2) throws QuartoException, IOException {
        this.setPlayers(player1.getName(), player2.getName());
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        animationFileHandler = new AnimationFileHandler();
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public  void setPlayers(String speler1, String speler2) throws QuartoException, IOException {
        if (speler1.isEmpty() || speler2.isEmpty()) {
            throw new QuartoException("Enter name for both players");
        } else if (speler1.equals(speler2)) {
            throw new QuartoException ("Please pick two different names");
        } else {
            this.allPLayers = new AllPLayers(new Player(speler1, 0), new Player(speler2,0));
        }
    }


    /*
* Geeft telkens een specifieke blok aan gekozenBlok.
* */

    public void choosePiece(Piece piece) throws QuartoException, IOException {

        if (chosenPiece != null) {
            throw new QuartoException("A piece has already been chosen.");
        } else {
            this.chosenPiece = piece;
            pieces.takePiece(piece);
            allPLayers.alternate();
            if (animation){
                this.animationFileHandler.addAction(piece.toString());
            }
            System.out.println("active player: "+ this.getAllPlayers().getActivePlayer());
            flipAction = true;
        }
    }

    /*
* Plaatst blok op speelBord.
* */

    public void placePiece(Position position) throws QuartoException, IOException {
        if (this.chosenPiece == null) {
            throw new QuartoException("There has been no block selected.");
        } else {
            board.addPiece(chosenPiece, position);
            if (animation){
                this.animationFileHandler.addAction(position.toString());
            }

            flipAction = false;
             if (gameFinished()) {
                 gameFinished = true;
                 if (animation) {
                     if (board.isFull()) {
                     this.animationFileHandler.addAction("gamefinished"+"|"+"vol");
                     } else {
                     this.animationFileHandler.addAction("gamefinished"+"|"+"won");
                     }
                     animationFileHandler.printout();
                 }
                 updateRanking();
             }
        }
    }
    public void updateRanking() throws QuartoException {
        if (this.board.hasCombination()) {
            this.playerRanking.addScoreWinningPlayer(this.allPLayers.getActivePlayer());
            this.playerRanking.addScoreLosingPlayer(this.allPLayers.getNonActivePlayer());
        }
    }

    public boolean gameFinished() {
        return (board.isFull() || board.hasCombination());
    }

    public boolean isFlipAction() {
        return flipAction;
    }

    public AllPLayers getAllPlayers() {
        return allPLayers;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getChosenPiece() {
//        if (gekozenBlok == null) {
//            throw new IllegalStateException("Er is geen blok geselecteerd.");
//        } else {
//            return gekozenBlok;
//        }
        return chosenPiece;
    }

    public PlayerRanking getPlayerRanking() {
        return playerRanking;
    }

    public void choosePlayer() throws IOException {
        int indexChosenPlayer = allPLayers.choosePlayerIndex();
        if (animation) {
            this.animationFileHandler.initiateFile("oneVone" + "|" + allPLayers.getPlayer1().getName()
                    + "|" + allPLayers.getPlayer2().getName() + "|" + indexChosenPlayer);
        }
        System.out.println("Active Player: "+ this.getAllPlayers().getActivePlayer());
    }

    public void setChosenPiece(Piece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public void setPlayerForAnimation() throws IOException, QuartoException {
        animationFileHandler.cteateActions();
//        System.out.println(animationFileHandler.getAction().length());
        String action = animationFileHandler.getAction();
        String player1FromAnimation = action.split("\\|")[1];
        String player2FromAnimation = action.split("\\|")[2];
        this.setPlayers(player1FromAnimation,player2FromAnimation);
        this.allPLayers.setActivePlayer(Integer.parseInt(action.split("\\|")[3]));
    }

    public AnimationFileHandler getAnimationFileHandler() {
        return animationFileHandler;
    }
}
