package edu.up.yu18.mahjong.game.MahJong.game;

import android.app.Notification;

import java.util.Arrays;

import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;

/**
 * @Collin_Yu
 * Smart Computer Player that uses tile counting to determine the best discard
 * Creates a parallel int[] that it uses to value the tiles in its hand based on:
 *          - If it can be used with other cards in its hand, value increases
 *          - If there are inaccessible discarded cards that have potential combinations with it, value decreases
 * Always Pongs, Kongs and Chows
 */

public class MahJongSmartPlayer extends GameComputerPlayer {

    private MahJongGameState state;
    private int[] hand = new int[14];
    private boolean isFirstTurn = true;

    public MahJongSmartPlayer(String name){super(name);}

    @Override
    protected void receiveInfo(GameInfo info) {


        // make sure it really is a MahJongGameState
        if(info instanceof MahJongGameState){
            state = new MahJongGameState((MahJongGameState) info);
        }

        if(isFirstTurn){
            for(int i = 0; i < hand.length; i++){
                hand[i] = 10;
            }
            //sets the initial value to the AI of his current hand
            for(int i = 0;i < state.getPlayerClosedHandLength(playerNum)-1;i++){
                for(int j = i+1;j < state.getPlayerClosedHandLength(playerNum);j++){
                    if(state.getPlayerClosedHandTile(playerNum,i).isEqualTo(state.getPlayerClosedHandTile(playerNum,j))){
                        this.hand[i]+= 2;
                        this.hand[j]+= 2;
                    }
                    if(state.getPlayerClosedHandTile(playerNum,i).isBelow(state.getPlayerClosedHandTile(playerNum,j))){
                        this.hand[i]+= 2;
                        this.hand[j]+= 2;
                    }
                }
            }
            isFirstTurn = false;
        }
        if(state.getGameStage()%2 == 0 && !state.hasPassed(this.playerNum)) {
            GameAction action = new Pass(this, playerNum);
            if(state.getCurrDiscard() != null) {
                for (int j = 0; j < state.getPlayerClosedHandLength(playerNum) - 1; j++) {
                    if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getCurrDiscard())) {
                        if ((state.getPlayerClosedHandTile(playerNum, j + 1)) != null) {
                            if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getPlayerClosedHandTile(playerNum, j + 1))) {
                                if (state.getPlayerClosedHandTile(playerNum, j + 2) != null) {
                                    if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getPlayerClosedHandTile(playerNum, j + 2))) {
                                        action = new Kong(this, playerNum, state.getPlayerClosedHandTile(playerNum, j), state.getPlayerClosedHandTile(playerNum, j + 1), state.getPlayerClosedHandTile(playerNum, j + 2), state.getCurrDiscard());
                                    }
                                } else {
                                    action = new Pong(this, playerNum, state.getPlayerClosedHandTile(playerNum, j), state.getPlayerClosedHandTile(playerNum, j + 1), state.getCurrDiscard());
                                }
                            }

                        }
                    }
                }

                int[] hand;
                Tile curPlay = state.getCurrDiscard();
                hand = state.getWholeClosedHand(this.playerNum);
                if (state.getGameStage() % 2 == 0) {
                    hand[hand.length - 1] = curPlay.getDeckPos();
                }
                Arrays.sort(hand);
                //CHOWWWWW
                if(state.getGameStage() == (this.playerNum)*2 || (this.playerNum == 0 && state.getGameStage() == 8)){
                    for (int i = 0; i < hand.length - 2; i++) {
                        if (state.getPlayerClosedHandTile(this.playerNum, i).isEqualTo(curPlay)) {
                            if (state.getPlayerClosedHandTile(this.playerNum, i + 1).isAbove(curPlay)
                                    && state.getPlayerClosedHandTile(this.playerNum, i + 2).isAbove(state.getPlayerClosedHandTile(this.playerNum, i + 1))) {
                                action = new Chow(this, playerNum, state.getPlayerClosedHandTile(this.playerNum, i + 1)
                                        , state.getPlayerClosedHandTile(this.playerNum, i + 2), curPlay);
                            }
                            if (i > 0) {
                                if (state.getPlayerClosedHandTile(this.playerNum, i + 1).isAbove(curPlay)
                                        && state.getPlayerClosedHandTile(this.playerNum, i - 1).isBelow(curPlay)) {
                                    action = new Chow(this, playerNum, state.getPlayerClosedHandTile(this.playerNum, i + 1)
                                            , state.getPlayerClosedHandTile(this.playerNum, i - 1), curPlay);

                                }
                            }
                            if (i > 1) {
                                if (state.getPlayerClosedHandTile(this.playerNum, i - 1).isBelow(curPlay)
                                        && state.getPlayerClosedHandTile(this.playerNum, i - 2).isBelow(curPlay)) {
                                    action = new Chow(this, playerNum, state.getPlayerClosedHandTile(this.playerNum, i - 1)
                                            , state.getPlayerClosedHandTile(this.playerNum, i - 2), curPlay);
                                }
                            }

                            break;
                        }
                    }
                }


                if (action instanceof Pass) {
                    for (int i = 0; i < state.getPlayerClosedHandLength(playerNum); i++) {
                        if (state.getCurrDiscard().isEqualTo(state.getPlayerClosedHandTile(playerNum, i))) {
                            hand[i] -= 2;
                        }
                        if (state.getCurrDiscard().isAbove(state.getPlayerClosedHandTile(playerNum, i))) {
                            hand[i] -= 2;
                        }
                        if (state.getCurrDiscard().isBelow(state.getPlayerClosedHandTile(playerNum, i))) {
                            hand[i] -= 2;
                        }
                    }
                }
            }
                //checks if the current discard lowers the value of any of the cards in its hand
                game.sendAction(action);

        }

        // if it's this' turn, discards the first card in its hand
        if(state.getGameStage() == (this.playerNum+1)*2-1){

            int lowest = 0;
            for (int q = 1; q < hand.length; q++){
                if (hand[q] < hand[lowest]){lowest = q;}
            }
            Discard disc = new Discard(this, playerNum, state.getPlayerClosedHandTile(playerNum,lowest));
            game.sendAction(disc);
        }

        // if it's a post-discard phase, just passes
        else if (state.getGameStage() % 2 == 0 && !state.hasPassed(this.playerNum)){
            Pass p = new Pass(this, this.playerNum);
            game.sendAction(p);
        }


    }
}
