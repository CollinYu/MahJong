package edu.up.yu18.mahjong.game.MahJong.game;

import java.io.Serializable;

import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.MahJong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Sort;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.LocalGame;
import edu.up.yu18.mahjong.game.frameWork.base.util.GameTimer;

/**
 * @Collin_Yu
 * Basically the one ring to rule them all
 * Sets everything up, and all actions pass through this
 */

public class MahJongLocalGame extends LocalGame implements  Serializable{

    private static final long serialVersionUID = 132306433313234514L;

    private boolean gameOverPrompt = false;
    private MahJongGameState state;
    private GameTimer timer;
    /**
     * @Collin_Yu
     * basic constructor
     */
    public MahJongLocalGame(){
        this.state = new MahJongGameState();
        timer = new GameTimer(this);
        timer.setInterval(100);
    }

    /**
     * @Collin_Yu
     * @param p the player to send the new state to
     *          sends gameState to player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new MahJongGameState(state));
    }

    /**
     * @Collin_Yu
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return whether the player can move or not
     * Checks legality of move based on stage of the game
     */
    @Override
    protected boolean canMove(int playerIdx) {
        // if it is a post discard phase not directly after this players discard return true
        if (state.getGameStage() % 2 == 0){return true;}

        // if it is this players discard phase return true
        if (state.getGameStage() == (playerIdx+1)*2 -1){return true;}


        else{return false;}
    }

    /**
     * @Collin_Yu
     * @return Victory Message if the game is Over
     * Checks if Game is Over
     */
    @Override
    protected String checkIfGameOver() {
        // will be changed
        if(state.isOutOfCards()){
            String victoryMsg = "The Deck has run out of cards! Everyone wins! Press Quit to leave.";
            return victoryMsg;
        }
        if(gameOverPrompt) {
            for (int i = 0; i < 4; i++) {
                if(state.hasMahJong(state.getWholeClosedHand(i), true)){
                    String victoryMsg = "Player " + i + 1 + " has won the game!";
                    return victoryMsg;
                }
            }
        }
        return null;
    }

    /**
     * @Collin_Yu
     * @param action
     * 			The move that the player has sent to the game
     * @return whether the move was made
     * after an action is checked and handled, this method calls the corresponding gameState method
     */
    @Override
    protected boolean makeMove(GameAction action){

        // if it's Chow, Chow
        if (action instanceof Chow){
            Chow c = (Chow) action;
            state.Chow(c);
            return true;
        }

        if (action instanceof MahJong){
            MahJong mj = (MahJong) action;
            if(state.hasMahJong(state.getWholeClosedHand(mj.getPlayerID()), true)){
                this.gameOverPrompt = true;
                this.checkIfGameOver();
            }
            return true;
        }

        // if it's a Pong, Pong
        if (action instanceof Pong){
            Pong p = (Pong) action;
            state.Pong(p);
            return true;
        }

        // if it's a Kong, Kong
        if (action instanceof Kong){
            Kong k = (Kong) action;
            state.Kong(k);
            return true;
        }

        // if it's a Discard, Discard
        if (action instanceof Discard){
            Discard d = (Discard) action;
            state.Discard(d);
            return true;
        }

        // if it's a Pass, Pass
        if (action instanceof Pass){
            Pass p = (Pass) action;
            state.pass(p);
            return true;
        }

        // if it's a Sort, Sort
        if (action instanceof Sort){
            Sort s = (Sort) action;
            state.sort(s.getId());
            return true;
        }

        // if it is none of these (this should never occur), return false
        return false;
    }
}
