package edu.up.yu18.mahjong.game.MahJong.game;

import android.app.Notification;

import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
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
        for(int i = 0; i < hand.length; i++){
            hand[i] = 10;
        }

        if(isFirstTurn){
            //sets the initial value to the AI of his current hand
            for(int i = 0;i < state.getPlayerClosedHandLength(playerNum)-1;i++){
                for(int j = i+1;j < state.getPlayerClosedHandLength(playerNum);j++){
                    if(state.getPlayerClosedHandTile(playerNum,i).isEqualto(state.getPlayerClosedHandTile(playerNum,j))){
                        hand[i]+= 2;
                        hand[j]+= 2;
                    }
                    if(state.getPlayerClosedHandTile(playerNum,i).isBelow(state.getPlayerClosedHandTile(playerNum,j))){
                        hand[i]+= 2;
                        hand[j]+= 2;
                    }
                }
            }
            isFirstTurn = false;
        }

        if(state.getGameStage()%2 == 0)
        {
            GameAction action = new Pass(this,playerNum);
            for (int i =0;i < state.getPlayerClosedHandLength(playerNum)-1;i++){
                if(state.getPlayerClosedHandTile(playerNum,i).isEqualto(state.getCurrDiscard())) {
                    if ((state.getPlayerClosedHandTile(playerNum, i + 1)) != null) {
                        if (state.getPlayerClosedHandTile(playerNum, i).isEqualto(state.getPlayerClosedHandTile(playerNum, i + 1))) {
                            if (state.getPlayerClosedHandTile(playerNum, i + 2) != null) {
                                if (state.getPlayerClosedHandTile(playerNum,i).isEqualto(state.getPlayerClosedHandTile(playerNum,i+2))){
                                    action = new Kong(this,playerNum,state.getPlayerClosedHandTile(playerNum,i),state.getPlayerClosedHandTile(playerNum,i+1),state.getPlayerClosedHandTile(playerNum,i+2),state.getCurrDiscard());
                                }
                            }
                            else {
                                action = new Pong(this, playerNum, state.getPlayerClosedHandTile(playerNum, i), state.getPlayerClosedHandTile(playerNum, i + 1), state.getCurrDiscard());
                            }
                        }

                    }
                }
            }
            if(action instanceof Pass) {
                for (int i = 0; i < state.getPlayerClosedHandLength(playerNum); i++) {
                    if (state.getCurrDiscard().isEqualto(state.getPlayerClosedHandTile(playerNum, i))) {
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
            //checks if the current discard lowers the value of any of the cards in its hand
            game.sendAction(action);
        }

        // if it's this' turn, discards the first card in its hand
        if(state.getGameStage() == (this.playerNum+1)*2-1){

            Discard disc = new Discard(this, playerNum, state.getPlayerClosedHandTile(playerNum,0));
            game.sendAction(disc);
        }

        // if it's a post-discard phase, just passes
        else if (state.getGameStage() % 2 == 0 && !state.hasPassed(this.playerNum)){
            Pass p = new Pass(this, this.playerNum);
            game.sendAction(p);
        }


    }
}
