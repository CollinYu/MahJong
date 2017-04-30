package edu.up.yu18.mahjong.game.MahJong.game;


import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.MahJong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;

/**
 * @Tristan_Boylan & Collin_Yu
 * Smart Computer Player that uses tile counting to determine the best discard
 * Creates a parallel int[] that it uses to value the tiles in its hand based on:
 *          - If it can be used with other cards in its hand, value increases
 *          - If there are inaccessible discarded cards that have potential combinations with it, value decreases
 * Always Pongs, Kongs and Chows
 */

public class MahJongSmartPlayer extends GameComputerPlayer {

    private static final long serialVersionUID = 132306479891054L;

    private MahJongGameState state;
    private int[] hand = new int[14];
    private boolean isFirstTurn = true;
    int lastGameStageMoved = 1;

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
        GameAction action = null;
        if(state.getGameStage()%2 == 0 && lastGameStageMoved != state.getGameStage()) {
            long waitTime = 1500+ (long)(Math.random()*1000);
            try{Thread.sleep(waitTime);}catch(InterruptedException ie){}
            action = new Pass(this, this.playerNum);
            if(state.getCurrDiscard() != null) {
                for (int j = 0; j < state.getPlayerClosedHandLength(playerNum) - 1; j++) {
                    if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getCurrDiscard())) {
                        if ((state.getPlayerClosedHandTile(playerNum, j + 1)) != null) {
                            if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getPlayerClosedHandTile(playerNum, j + 1))) {
                                boolean tryPassed = true;
                                try{state.getPlayerClosedHandTile(playerNum, j + 2);}
                                catch(NullPointerException npe){tryPassed = false;}
                                    if(tryPassed){
                                    if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getPlayerClosedHandTile(playerNum, j + 2))) {
                                        action = new Kong(this, this.playerNum, state.getPlayerClosedHandTile(playerNum, j), state.getPlayerClosedHandTile(playerNum, j + 1), state.getPlayerClosedHandTile(playerNum, j + 2), state.getCurrDiscard());
                                    }
                                } else {
                                    action = new Pong(this, this.playerNum, state.getPlayerClosedHandTile(playerNum, j), state.getPlayerClosedHandTile(playerNum, j + 1), state.getCurrDiscard());
                                }
                            }

                        }
                    }
                }

                int[] myHand;
                Tile curPlay = state.getCurrDiscard();
                myHand = state.getWholeClosedHand(this.playerNum);
                if (state.getGameStage() % 2 == 0) {
                    myHand = state.addCurrDiscard(myHand);
                }
                int temp;

                myHand = state.removeNull(myHand);
                // ID of the tiles was made for easy sorting
                for (int i = 0; i < myHand.length - 1; i++) {
                    for (int j = i + 1; j < myHand.length; j++) {
                        if (state.deckGet(myHand[i]).getID() > state.deckGet(myHand[j]).getID()) {
                            temp = myHand[j];
                            myHand[j] = myHand[i];
                            myHand[i] = temp;
                        }
                    }
                }
                //CHOWWWWW
                if(state.getGameStage() == (this.playerNum)*2 || (this.playerNum == 0 && state.getGameStage() == 8)) {
                    for (int i = 0; i < myHand.length - 1; i++) {
                        if (state.deckGet(myHand[i]).isAbove(curPlay)) {
                            for (int j = i; j < myHand.length; j++) {
                                if (state.deckGet(myHand[j]).isAbove(state.deckGet(myHand[i]))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                }

                            }
                            for (int j = i; j > -1; j--) {
                                if (state.deckGet(myHand[j]).isBelow(state.deckGet(myHand[i]))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                }

                            }
                        } else if (state.deckGet(myHand[i]).isBelow(curPlay)) {
                            for (int j = 0; j < i; j++) {
                                if (state.deckGet(myHand[j]).isBelow(state.deckGet(myHand[i]))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                }

                            }
                        }
                    }
                }


                if (action instanceof Pass) {
                    for (int i = 0; i < state.getPlayerClosedHandLength(this.playerNum); i++) {
                        if (state.getCurrDiscard().isEqualTo(state.getPlayerClosedHandTile(this.playerNum, i))) {
                            hand[i] -= 2;
                        }
                        if (state.getCurrDiscard().isAbove(state.getPlayerClosedHandTile(this.playerNum, i))) {
                            hand[i] -= 2;
                        }
                        if (state.getCurrDiscard().isBelow(state.getPlayerClosedHandTile(this.playerNum, i))) {
                            hand[i] -= 2;
                        }
                    }
                }
            }
        }

        // if it's this' turn, discards the worst card in its hand
        else if(state.getGameStage() == (this.playerNum+1)*2-1){

            int lowest = 0;
            for (int q = 1; q < hand.length; q++){
                if (hand[q] < hand[lowest]){
                    lowest = q;
                }
                else if(hand[q] == hand[lowest]){
                    if (Math.random() < 0.5){
                        lowest = q;
                    }
                }
            }
            action = new Discard(this, this.playerNum, state.getPlayerClosedHandTile(this.playerNum,lowest));
        }
        if(state.hasMahJong(state.getWholeClosedHand(this.playerNum), true)){
            action = new MahJong(this, this.playerNum);
        }

        if (action != null){lastGameStageMoved = state.getGameStage();}
        game.sendAction(action);


    } // end receiveInfo
} // end smartPlayer
