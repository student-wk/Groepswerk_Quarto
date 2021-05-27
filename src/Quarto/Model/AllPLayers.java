package Quarto.Model;

import java.util.Random;

/**
 * The class AllPlayers is used to determine which player can make a move in the game.
 *
 * @author Willem Kuijpers
 * @version 1.0 24-4-2021 14:26
 */
public class AllPLayers {

    private Player player1;
    private Player player2;
    private Player activePlayer;
    private final int random = new Random().nextInt(2) + 1;

    /**
    *
    * */
    public AllPLayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        activePlayer = null;
    }

/**
 *
 * * This method picks an int 1 or 2
 *
 * @return int index
* */

    public int choosePlayerIndex() {
        int index;
        if (random == 1) {
            activePlayer = player1;
            index = 1;
        } else {
            activePlayer = player2;
            index =2;
        }
        return index;
    }

/**
 * This method switches the active player.
 *
 * @return Player activePlayer
 * */

    public Player alternate() {

        if (activePlayer == player1) {
            activePlayer = player2;
        } else if (activePlayer == player2){
            activePlayer = player1;
        }
        return activePlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getNonActivePlayer(){
        if(activePlayer == player1) {
            return player2;
        } else return player1;
    }

    public void setActivePlayer (int activePlayer) {
        if (activePlayer == 1) {
            this.activePlayer = player1;
        } else  {
            this.activePlayer = player2;
        }
    }
}
