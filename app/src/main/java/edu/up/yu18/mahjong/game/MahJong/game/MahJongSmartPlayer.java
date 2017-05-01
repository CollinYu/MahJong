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
    int lastGameStageMoved = 1;

    public MahJongSmartPlayer(String name){super(name);}

    @Override
    /**
     *@Tristan_Boylan_&_Collin_Yu
     * The decision-making aspect of the computer. The method is called upon receiving a gamestate,
     * and the body consists of the logic of its response.
     */
    protected void receiveInfo(GameInfo info) {

        // make sure it really is a MahJongGameState
        if(info instanceof MahJongGameState){
            state = new MahJongGameState((MahJongGameState) info);
        }

        if(lastGameStageMoved != state.getGameStage()) {
            // Computer reevaluates its hand each time it receives info
            for (int i = 0; i < hand.length; i++) {
                hand[i] = 10;
            }
            // raises values of tiles that are part of combinations in its hand
            for (int i = 0; i < state.getPlayerClosedHandLength(playerNum) - 1; i++) {
                for (int j = i + 1; j < state.getPlayerClosedHandLength(playerNum); j++) {
                    if (state.getPlayerClosedHandTile(playerNum, i).isEqualTo(state.getPlayerClosedHandTile(playerNum, j))) {
                        this.hand[i] += 3;
                        this.hand[j] += 3;
                    }
                    if (state.getPlayerClosedHandTile(playerNum, i).isBelow(state.getPlayerClosedHandTile(playerNum, j))) {
                        this.hand[i] += 3;
                        this.hand[j] += 3;
                    }
                }
            }


            boolean allGucci = true;
            try {
                state.getCurrDiscard().isEqualTo(state.getPlayerClosedHandTile(this.playerNum, 1));
            } catch(NullPointerException npe){allGucci = false;}
            if(allGucci) {
                for (int i = 0; i < state.getPlayerClosedHandLength(this.playerNum); i++) {
                    if (state.getCurrDiscard().isEqualTo(state.getPlayerClosedHandTile(this.playerNum, i))) {
                        hand[i] -= 1;
                    }
                    if (state.getCurrDiscard().isAbove(state.getPlayerClosedHandTile(this.playerNum, i))) {
                        hand[i] -= 1;
                    }
                    if (state.getCurrDiscard().isBelow(state.getPlayerClosedHandTile(this.playerNum, i))) {
                        hand[i] -= 1;
                    }
                }
            }

            GameAction action = null;

            // if it is a post-discard phase, and the computer has not already made an action for this stage
            if (state.getGameStage() % 2 == 0) {

                // make the human feel less inadequate computationally by having the computer wait 1.5-2.5 seconds
                long waitTime = 1500 + (long) (Math.random() * 1000);
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException ie) {
                }

                // default action for this stage is to pass, will be overriden if better combination found
                action = new Pass(this, this.playerNum);

                // catch any weird between-stage handling
                if (state.getCurrDiscard() != null) {
                    for (int j = 0; j < state.getPlayerClosedHandLength(playerNum) - 1; j++) {
                        if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getCurrDiscard())) {
                            if ((state.getPlayerClosedHandTile(playerNum, j + 1)) != null) {
                                if (state.getPlayerClosedHandTile(playerNum, j).isEqualTo(state.getPlayerClosedHandTile(playerNum, j + 1))) {
                                    boolean tryPassed = true;
                                    try {
                                        state.getPlayerClosedHandTile(playerNum, j + 2);
                                    } catch (ArrayIndexOutOfBoundsException npe) {
                                        tryPassed = false;
                                    }
                                    if (tryPassed) {
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
                if (state.getGameStage() == (this.playerNum) * 2 || (this.playerNum == 0 && state.getGameStage() == 8)) {
                    for (int i = 0; i < myHand.length - 1; i++) {
                        if (state.deckGet(myHand[i]).isAbove(curPlay)) {
                            for (int j = i; j < myHand.length; j++) {
                                if (state.deckGet(myHand[j]).isAbove(state.deckGet(myHand[i]))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                    break;
                                }

                            }
                            for (int j = i; j > -1; j--) {
                                if (state.deckGet(myHand[j]).isBelow(state.deckGet(curPlay.getDeckPos()))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                    break;
                                }

                            }
                        } else if (state.deckGet(myHand[i]).isBelow(curPlay)) {
                            for (int j = 0; j < i; j++) {
                                if (state.deckGet(myHand[j]).isBelow(state.deckGet(myHand[i]))) {
                                    action = new Chow(this, this.playerNum, state.deckGet(myHand[i])
                                            , state.deckGet(myHand[j]), curPlay);
                                    break;
                                }

                            }
                        }
                    }
                }
            }

            // if it's this' turn, discards the worst card in its hand
            else if (state.getGameStage() == (this.playerNum + 1) * 2 - 1) {

                int lowest = 0;
                for (int q = 1; q < hand.length; q++) {
                    if (hand[q] < hand[lowest]) {
                        if (state.getPlayerClosedHandTile(this.playerNum, q).getDeckPos() == 136) {
                            continue;
                        }
                        lowest = q;
                    } else if (hand[q] == hand[lowest]) {
                        if (Math.random() < 0.5) {
                            lowest = q;
                        }
                    }
                }
                action = new Discard(this, this.playerNum, state.getPlayerClosedHandTile(this.playerNum, lowest));
            }
            if (state.hasMahJong(state.getWholeClosedHand(this.playerNum), true)) {
                action = new MahJong(this, this.playerNum);
            }

            if (action != null) {
                this.lastGameStageMoved = state.getGameStage();
            }
            game.sendAction(action);
        }
    } // end receiveInfo
} // end smartPlayer
