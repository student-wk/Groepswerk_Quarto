package Quarto.Model;

import java.io.IOException;

/**
 * This class is the main class of the code in the model and it brings the code of the other classes together.
 *
 * It also contains code to write information to the animation source file.
 * */

public class Quarto {
    private Pieces pieces;
    private Board board;
    private Piece chosenPiece;
    private AllPLayers allPLayers;
    private boolean gameFinished;
    private boolean flipAction;
    private Boolean animation = true;
    private PlayerRanking playerRanking;
    private AnimationFileHandler animationFileHandler;


    /**
     * This constructor is used at the start of the program.
     * */

    public Quarto() {
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        animationFileHandler = new AnimationFileHandler();
    }

    public void reset(){
        this.pieces = new Pieces();
        this.board = new Board();
        this.setChosenPiece(null);
        AnimationFileHandler.setCount(1);
    }

    /**
     * This constructor is used when playing the replay of the last game.
     *
     * @param doNotAnimate
     * */

    public Quarto(Boolean doNotAnimate) {
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        this.animation = doNotAnimate;
        animationFileHandler = new AnimationFileHandler();
    }

    /**
     * This constructor is used when pressing new game in the main menu.
     * */

    public Quarto(Player player1, Player player2) throws QuartoException, IOException {
        this.setPlayers(player1.getName(), player2.getName());
        this.pieces = new Pieces();
        this.board = new Board();
        this.chosenPiece = null;
        this.playerRanking = new PlayerRanking();
        animationFileHandler = new AnimationFileHandler();
    }

    /**
     * This method initializes the two players for the game.
     * */

    public  void setPlayers(String speler1, String speler2) throws QuartoException {
        if (speler1.isEmpty() || speler2.isEmpty()) {
            throw new QuartoException("Enter name for both players");
        } else if (speler1.equals(speler2)) {
            throw new QuartoException ("Please pick two different names");
        } else {
            this.allPLayers = new AllPLayers(new Player(speler1, 0), new Player(speler2,0));
        }
    }

    /**
     * This method chooses the player who can do the first move.
     * */

    public void choosePlayer() throws IOException {
        int indexChosenPlayer = allPLayers.choosePlayerIndex();
        if (animation) {

            this.animationFileHandler.initiateFile("oneVone" + "|" + allPLayers.getPlayer1().getName()
                    + "|" + allPLayers.getPlayer2().getName() + "|" + indexChosenPlayer);
        }
    }

    /**
     * This method is used to pick a piece from pieces.
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
            flipAction = true;
        }
    }

    /**
     * This method places a piece on the board.
     * */

    public void placePiece(Position position) throws QuartoException, IOException {
        if (this.chosenPiece == null) {
            throw new QuartoException("Select piece first.");
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
                     this.animationFileHandler.addAction("gamefinished"+"|"+"full");
                     } else {
                     this.animationFileHandler.addAction("gamefinished"+"|"+"won");
                     }
                 }
                 updateRanking();
             }
        }
    }

    /**
     * Checks if the game is finished.
     * */

    public boolean gameFinished() {
        return (board.isFull() || board.hasCombination());
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * This method updates the ranking.
     * */

    public void updateRanking() throws IOException {
        if (this.board.hasCombination()) {
            this.playerRanking.addScoreWinningPlayer(this.allPLayers.getActivePlayer());
            this.playerRanking.addScoreLosingPlayer(this.allPLayers.getNonActivePlayer());
        }
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
        return chosenPiece;
    }

    public PlayerRanking getPlayerRanking() {
        return playerRanking;
    }

    public AnimationFileHandler getAnimationFileHandler() {
        return animationFileHandler;
    }

    public void setChosenPiece(Piece chosenPiece) {
        this.chosenPiece = chosenPiece;
    }

    public void setPlayerForAnimation() throws QuartoException {
        try {
            animationFileHandler.cteateActions();
        } catch (IOException ioException) {
            throw new QuartoException(ioException);
        }
        String action = animationFileHandler.getAction();
        String player1FromAnimation = action.split("\\|")[1];
        String player2FromAnimation = action.split("\\|")[2];
        this.setPlayers(player1FromAnimation,player2FromAnimation);
        this.allPLayers.setActivePlayer(Integer.parseInt(action.split("\\|")[3]));
    }
}
