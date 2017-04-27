package edu.up.yu18.mahjong.game.MahJong.game;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.yu18.mahjong.R;
import edu.up.yu18.mahjong.game.MahJong.Actions.MahJong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Sort;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameHumanPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameMainActivity;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;

/**
 * @Collin_Yu
 * the MahJongHumanPlayer Class translates both ways between a GUI and the game
 *
 * TODO: make discard work doh
 */

public class MahJongHumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    private Button passButton;
    private Button mahJongButton;
    private Button quitButton;
    private ImageButton[] myTiles = new ImageButton[14];
    private boolean[] tilePressed = new boolean[14];
    private ImageView[][] playerClosedHand = new ImageView[3][14];
    private ImageView[][] playerOpenHand = new ImageView[1][14];
    private ImageButton[] discard = new ImageButton[88];
    private TextView displayTextBox;
    private TextView turnIndicator;
    private Button chowButton;
    private Button pongButton;
    private Button kongButton;
    private Button discardButton;
    private Button sortButton;
    private Button emoteButton;
    private Spinner emoteSpinner;
    private Spinner scoreSpinner;
    private GameMainActivity myActivity;
    private MahJongGameState state;

    public MahJongHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }


    /**
     * This is where we manipulate the gui objects in response to the gamestate having changed
     */
    public void updateDisplay() {
        if (state.getGameStage() % 2 ==0){
            turnIndicator.setText("Player " + state.getGameStage()/2 + "'s Post-Discard Phase!");
        }
        else {
            turnIndicator.setText("It is Player " + (state.getGameStage() + 1) / 2  + "'s Turn!");
        }
        for(int q = 0; q < 14; q++) {
            if (tilePressed[q]){
                myTiles[q].setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
            }
            else {

                myTiles[q].setBackgroundTintMode(PorterDuff.Mode.ADD);
            }
        }
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 14; i++) {
                if (state.getPlayerClosedHandTile(playerNum, i) != null) {
                    if(state.getPlayerClosedHandTile(playerNum,i).getSuit() == -1){
                        myTiles[i].setBackgroundColor(0xFF1e771e);
                    }

                    //if suit is dots suit is 0
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 0) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots1);
                                } else if (j == 1) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots2);
                                } else if (j == 2) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots3);
                                } else if (j == 3) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots4);
                                } else if (j == 4) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots5);
                                } else if (j == 5) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots6);
                                } else if (j == 6) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots7);
                                } else if (j == 7) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots8);
                                } else if (j == 8) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledots9);
                                }
                            }
                        }
                    }
                    //if suit is bamboo suit is 1
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 1) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo1);
                                } else if (j == 1) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo2);
                                } else if (j == 2) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo3);
                                } else if (j == 3) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo4);
                                } else if (j == 4) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo5);
                                } else if (j == 5) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo6);
                                } else if (j == 6) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo7);
                                } else if (j == 7) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo8);
                                } else if (j == 8) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilebamboo9);
                                }
                            }
                        }
                    }
                    //if suit is character suit is 2
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 2) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter1);
                                } else if (j == 1) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter2);
                                } else if (j == 2) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter3);
                                } else if (j == 3) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter4);
                                } else if (j == 4) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter5);
                                } else if (j == 5) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter6);
                                } else if (j == 6) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter7);
                                } else if (j == 7) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter8);
                                } else if (j == 8) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtilecharacter9);
                                }
                            }
                        }
                    }
                    //if suit is dragon suit is 3
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 3) {
                        for (int j = 0; j < 3; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledragongreen);
                                } else if (j == 1) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledragonred);
                                } else if (j == 2) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledragonwhite);
                                }
                            }
                        }

                    }
                    //if suit is wind suit is 4
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 4) {
                        for (int j = 0; j < 4; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledirectioneast);
                                } else if (j == 1) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledirectionnorth);
                                } else if (j == 2) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledirectionsouth);
                                } else if (j == 3) {
                                    myTiles[i].setBackgroundResource(R.drawable.mahjongtiledirectionwest);
                                }
                            }
                        }

                    }

                }

            }
        }

        for(int q = 0; q < 14; q++) {
            if (tilePressed[q]){
                myTiles[q].setBackgroundColor(Color.BLUE);
            }
        }
        for (int i = 0; i < 14; i++) {
            if (state.getPlayerClosedHandTile(playerNum, i) != null) {
                playerClosedHand[0][i].setBackgroundResource(R.mipmap.mahjongtile_back2);
            }
        }
        for (int i = 0; i < 14; i++) {
            if (state.getPlayerClosedHandTile(playerNum, i) != null) {
                playerClosedHand[1][i].setBackgroundResource(R.mipmap.mahjongtile_back2);
            }
        }
        for (int i = 0; i < 14; i++) {
            if (state.getPlayerClosedHandTile(playerNum, i) != null) {
                playerClosedHand[2][i].setBackgroundResource(R.mipmap.mahjongtile_back);
            }
        }

        for (int k = 0; k < 1; k++) {
            for (int n = 0; n < 14; n++) {
                if ((state.getPlayerOpenHandTile(playerNum, n) != null) && (state.getPlayerOpenHands(playerNum,n) != 136)) {
                    //if suit is dots suit is 0
                    if (state.getPlayerOpenHandTile(playerNum, n).getSuit() == 0) {
                        if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 0) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots1);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 1) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots2);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 2) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots3);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 3) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots4);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 4) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots5);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 5) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots6);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 6) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots7);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 7) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots8);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 8) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledots9);
                        }

                    }
                    //if suit is bamboo suit is 1
                    if (state.getPlayerOpenHandTile(playerNum, n).getSuit() == 1) {
                        if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 0) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo1);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 1) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo2);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 2) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo3);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 3) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo4);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 4) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo5);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 5) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo6);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 6) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo7);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 7) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo8);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 8) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilebamboo9);
                        }


                    }
                    //if suit is character suit is 2
                    if (state.getPlayerOpenHandTile(playerNum, n).getSuit() == 2) {
                        if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 0) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter1);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 1) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter2);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 2) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter3);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 3) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter4);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 4) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter5);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 5) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter6);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 6) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter7);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 7) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter8);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 8) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtilecharacter9);
                        }
                    }
                    //if suit is dragon suit is 3
                    if (state.getPlayerOpenHandTile(playerNum, n).getSuit() == 3) {
                        if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 0) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledragongreen);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 1) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledragonred);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 2) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledragonwhite);
                        }


                    }
                    //if suit is wind suit is 4
                    if (state.getPlayerOpenHandTile(playerNum, n).getSuit() == 4) {
                        if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 0) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledirectioneast);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 1) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledirectionnorth);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 2) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledirectionsouth);
                        } else if (state.getPlayerOpenHandTile(playerNum, n).getVal() == 3) {
                            playerOpenHand[k][n].setImageResource(R.drawable.mahjongtiledirectionwest);
                        }


                    }

                }
                else   {
                    playerOpenHand[k][n].setBackgroundColor(0xFF1e771e);
                }
            }
        }
        /**
         * TODO make the wall update GUI
         * */

        for(int i = 0; i < 84; i++){
            if(state.getDiscardPile(i) == 136) {

                discard[i].setBackgroundColor(0xFF1e771e);
            }
        }
        for(int i = 0; i < 84; i++){
            if(state.getCurrDiscard() != null) {
                /*
                if (state.getDiscardPile(i) != 136){
                    if (state.getCurrDiscard().getSuit() == 0) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots9);
                                }
                            }
                        }
                    }
                    //if suit is bamboo suit is 1
                    if (state.getCurrDiscard().getSuit() == 1) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo9);
                                }
                            }
                        }
                    }
                    //if suit is character suit is 2
                    if (state.getCurrDiscard().getSuit() == 2) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter9);
                                }
                            }
                        }
                    }
                    //if suit is dragon suit is 3
                    if (state.getCurrDiscard().getSuit() == 3) {
                        for (int j = 0; j < 3; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragongreen);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragonred);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragonwhite);
                                }
                            }
                        }

                    }
                    //if suit is wind suit is 4
                    if (state.getCurrDiscard().getSuit() == 4) {
                        for (int j = 0; j < 4; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectioneast);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionnorth);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionsouth);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionwest);
                                }
                            }
                        }

                    }
                }
                else */if (state.getDiscardPile(i) == 136) {
                    if (state.getCurrDiscard().getSuit() == 0) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledots9);
                                }
                            }
                        }
                    }
                    //if suit is bamboo suit is 1
                    if (state.getCurrDiscard().getSuit() == 1) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilebamboo9);
                                }
                            }
                        }
                    }
                    //if suit is character suit is 2
                    if (state.getCurrDiscard().getSuit() == 2) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter1);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter2);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter3);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter4);
                                } else if (j == 4) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter5);
                                } else if (j == 5) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter6);
                                } else if (j == 6) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter7);
                                } else if (j == 7) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter8);
                                } else if (j == 8) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtilecharacter9);
                                }
                            }
                        }
                    }
                    //if suit is dragon suit is 3
                    if (state.getCurrDiscard().getSuit() == 3) {
                        for (int j = 0; j < 3; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragongreen);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragonred);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledragonwhite);
                                }
                            }
                        }

                    }
                    //if suit is wind suit is 4
                    if (state.getCurrDiscard().getSuit() == 4) {
                        for (int j = 0; j < 4; j++) {
                            if (state.getCurrDiscard().getVal() == j) {
                                if (j == 0) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectioneast);
                                } else if (j == 1) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionnorth);
                                } else if (j == 2) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionsouth);
                                } else if (j == 3) {
                                    discard[i].setBackgroundResource(R.drawable.mahjongtiledirectionwest);
                                }
                            }
                        }

                    }
                    return;
                }
            }
        }

}

    /**
     * We only receive new gamestates, basically just calls updateDisplay()
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof MahJongGameState)) return;

        // update our state; then update the display
        state = (MahJongGameState) info;
        updateDisplay();
    }


    /**
     * @Collin_Yu
     * initializes this players GUI
     * @param activity
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.mahjong_human_player);


        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }

        playerClosedHand[0][0] = (ImageView) activity.findViewById(R.id.player2Tile1);
        playerClosedHand[0][1] = (ImageView) activity.findViewById(R.id.player2Tile2);
        playerClosedHand[0][2] = (ImageView) activity.findViewById(R.id.player2Tile3);
        playerClosedHand[0][3] = (ImageView) activity.findViewById(R.id.player2Tile4);
        playerClosedHand[0][4] = (ImageView) activity.findViewById(R.id.player2Tile5);
        playerClosedHand[0][5] = (ImageView) activity.findViewById(R.id.player2Tile6);
        playerClosedHand[0][6] = (ImageView) activity.findViewById(R.id.player2Tile7);
        playerClosedHand[0][7] = (ImageView) activity.findViewById(R.id.player2Tile8);
        playerClosedHand[0][8] = (ImageView) activity.findViewById(R.id.player2Tile9);
        playerClosedHand[0][9] = (ImageView) activity.findViewById(R.id.player2Tile10);
        playerClosedHand[0][10] = (ImageView) activity.findViewById(R.id.player2Tile11);
        playerClosedHand[0][11] = (ImageView) activity.findViewById(R.id.player2Tile12);
        playerClosedHand[0][12] = (ImageView) activity.findViewById(R.id.player2Tile13);
        playerClosedHand[0][13] = (ImageView) activity.findViewById(R.id.player2Tile14);


        playerClosedHand[1][0] = (ImageView) activity.findViewById(R.id.player3Tile1);
        playerClosedHand[1][1] = (ImageView) activity.findViewById(R.id.player3Tile2);
        playerClosedHand[1][2] = (ImageView) activity.findViewById(R.id.player3Tile3);
        playerClosedHand[1][3] = (ImageView) activity.findViewById(R.id.player3Tile4);
        playerClosedHand[1][4] = (ImageView) activity.findViewById(R.id.player3Tile5);
        playerClosedHand[1][5] = (ImageView) activity.findViewById(R.id.player3Tile6);
        playerClosedHand[1][6] = (ImageView) activity.findViewById(R.id.player3Tile7);
        playerClosedHand[1][7] = (ImageView) activity.findViewById(R.id.player3Tile8);
        playerClosedHand[1][8] = (ImageView) activity.findViewById(R.id.player3Tile9);
        playerClosedHand[1][9] = (ImageView) activity.findViewById(R.id.player3Tile10);
        playerClosedHand[1][10] = (ImageView) activity.findViewById(R.id.player3Tile11);
        playerClosedHand[1][11] = (ImageView) activity.findViewById(R.id.player3Tile12);
        playerClosedHand[1][12] = (ImageView) activity.findViewById(R.id.player3Tile13);
        playerClosedHand[1][13] = (ImageView) activity.findViewById(R.id.player3Tile14);

        playerClosedHand[2][0] = (ImageView) activity.findViewById(R.id.player4Tile1);
        playerClosedHand[2][1] = (ImageView) activity.findViewById(R.id.player4Tile2);
        playerClosedHand[2][2] = (ImageView) activity.findViewById(R.id.player4Tile3);
        playerClosedHand[2][3] = (ImageView) activity.findViewById(R.id.player4Tile4);
        playerClosedHand[2][4] = (ImageView) activity.findViewById(R.id.player4Tile5);
        playerClosedHand[2][5] = (ImageView) activity.findViewById(R.id.player4Tile6);
        playerClosedHand[2][6] = (ImageView) activity.findViewById(R.id.player4Tile7);
        playerClosedHand[2][7] = (ImageView) activity.findViewById(R.id.player4Tile8);
        playerClosedHand[2][8] = (ImageView) activity.findViewById(R.id.player4Tile9);
        playerClosedHand[2][9] = (ImageView) activity.findViewById(R.id.player4Tile10);
        playerClosedHand[2][10] = (ImageView) activity.findViewById(R.id.player4Tile11);
        playerClosedHand[2][11] = (ImageView) activity.findViewById(R.id.player4Tile12);
        playerClosedHand[2][12] = (ImageView) activity.findViewById(R.id.player4Tile13);
        playerClosedHand[2][13] = (ImageView) activity.findViewById(R.id.player4Tile14);

        playerOpenHand[0][0] = (ImageView) activity.findViewById(R.id.openTile1);
        playerOpenHand[0][1] = (ImageView) activity.findViewById(R.id.openTile2);
        playerOpenHand[0][2] = (ImageView) activity.findViewById(R.id.openTile3);
        playerOpenHand[0][3] = (ImageView) activity.findViewById(R.id.openTile4);
        playerOpenHand[0][4] = (ImageView) activity.findViewById(R.id.openTile5);
        playerOpenHand[0][5] = (ImageView) activity.findViewById(R.id.openTile6);
        playerOpenHand[0][6] = (ImageView) activity.findViewById(R.id.openTile7);
        playerOpenHand[0][7] = (ImageView) activity.findViewById(R.id.openTile8);
        playerOpenHand[0][8] = (ImageView) activity.findViewById(R.id.openTile9);
        playerOpenHand[0][9] = (ImageView) activity.findViewById(R.id.openTile10);
        playerOpenHand[0][10] = (ImageView) activity.findViewById(R.id.openTile11);
        playerOpenHand[0][11] = (ImageView) activity.findViewById(R.id.openTile12);
        playerOpenHand[0][12] = (ImageView) activity.findViewById(R.id.openTile13);
        playerOpenHand[0][13]= (ImageView) activity.findViewById(R.id.openTile14);

        /**
        playerOpenHand[1][0] = (ImageView) activity.findViewById(R.id.player2Tile1);
        playerOpenHand[1][1] = (ImageView) activity.findViewById(R.id.player2Tile2);
        playerOpenHand[1][2] = (ImageView) activity.findViewById(R.id.player2Tile3);
        playerOpenHand[1][3] = (ImageView) activity.findViewById(R.id.player2Tile4);
        playerOpenHand[1][4] = (ImageView) activity.findViewById(R.id.player2Tile5);
        playerOpenHand[1][5] = (ImageView) activity.findViewById(R.id.player2Tile6);
        playerOpenHand[1][6] = (ImageView) activity.findViewById(R.id.player2Tile7);
        playerOpenHand[1][7] = (ImageView) activity.findViewById(R.id.player2Tile8);
        playerOpenHand[1][8] = (ImageView) activity.findViewById(R.id.player2Tile9);
        playerOpenHand[1][9] = (ImageView) activity.findViewById(R.id.player2Tile10);
        playerOpenHand[1][10] = (ImageView) activity.findViewById(R.id.player2Tile11);
        playerOpenHand[1][11] = (ImageView) activity.findViewById(R.id.player2Tile12);
        playerOpenHand[1][12] = (ImageView) activity.findViewById(R.id.player2Tile13);
        playerOpenHand[1][13] = (ImageView) activity.findViewById(R.id.player2Tile14);


        playerOpenHand[2][0] = (ImageView) activity.findViewById(R.id.player3Tile1);
        playerOpenHand[2][1] = (ImageView) activity.findViewById(R.id.player3Tile2);
        playerOpenHand[2][2] = (ImageView) activity.findViewById(R.id.player3Tile3);
        playerOpenHand[2][3] = (ImageView) activity.findViewById(R.id.player3Tile4);
        playerOpenHand[2][4] = (ImageView) activity.findViewById(R.id.player3Tile5);
        playerOpenHand[2][5] = (ImageView) activity.findViewById(R.id.player3Tile6);
        playerOpenHand[2][6] = (ImageView) activity.findViewById(R.id.player3Tile7);
        playerOpenHand[2][7] = (ImageView) activity.findViewById(R.id.player3Tile8);
        playerOpenHand[2][8] = (ImageView) activity.findViewById(R.id.player3Tile9);
        playerOpenHand[2][9] = (ImageView) activity.findViewById(R.id.player3Tile10);
        playerOpenHand[2][10] = (ImageView) activity.findViewById(R.id.player3Tile11);
        playerOpenHand[2][11] = (ImageView) activity.findViewById(R.id.player3Tile12);
        playerOpenHand[2][12] = (ImageView) activity.findViewById(R.id.player3Tile13);
        playerOpenHand[2][13] = (ImageView) activity.findViewById(R.id.player3Tile14);

        playerOpenHand[3][0] = (ImageView) activity.findViewById(R.id.player4Tile1);
        playerOpenHand[3][1] = (ImageView) activity.findViewById(R.id.player4Tile2);
        playerOpenHand[3][2] = (ImageView) activity.findViewById(R.id.player4Tile3);
        playerOpenHand[3][3] = (ImageView) activity.findViewById(R.id.player4Tile4);
        playerOpenHand[3][4] = (ImageView) activity.findViewById(R.id.player4Tile5);
        playerOpenHand[3][5] = (ImageView) activity.findViewById(R.id.player4Tile6);
        playerOpenHand[3][6] = (ImageView) activity.findViewById(R.id.player4Tile7);
        playerOpenHand[3][7] = (ImageView) activity.findViewById(R.id.player4Tile8);
        playerOpenHand[3][8] = (ImageView) activity.findViewById(R.id.player4Tile9);
        playerOpenHand[3][9] = (ImageView) activity.findViewById(R.id.player4Tile10);
        playerOpenHand[3][10] = (ImageView) activity.findViewById(R.id.player4Tile11);
        playerOpenHand[3][11] = (ImageView) activity.findViewById(R.id.player4Tile12);
        playerOpenHand[3][12] = (ImageView) activity.findViewById(R.id.player4Tile13);
        playerOpenHand[3][13] = (ImageView) activity.findViewById(R.id.player4Tile14);
        */


        myTiles[0] = (ImageButton) activity.findViewById(R.id.tile1);
        myTiles[1] = (ImageButton) activity.findViewById(R.id.tile2);
        myTiles[2] = (ImageButton) activity.findViewById(R.id.tile3);
        myTiles[3] = (ImageButton) activity.findViewById(R.id.tile4);
        myTiles[4] = (ImageButton) activity.findViewById(R.id.tile5);
        myTiles[5] = (ImageButton) activity.findViewById(R.id.tile6);
        myTiles[6] = (ImageButton) activity.findViewById(R.id.tile7);
        myTiles[7] = (ImageButton) activity.findViewById(R.id.tile8);
        myTiles[8] = (ImageButton) activity.findViewById(R.id.tile9);
        myTiles[9] = (ImageButton) activity.findViewById(R.id.tile10);
        myTiles[10] = (ImageButton) activity.findViewById(R.id.tile11);
        myTiles[11] = (ImageButton) activity.findViewById(R.id.tile12);
        myTiles[12] = (ImageButton) activity.findViewById(R.id.tile13);
        myTiles[13] = (ImageButton) activity.findViewById(R.id.tile14);

        discard[0] = (ImageButton) activity.findViewById(R.id.Discard1Tile1);
        discard[1] = (ImageButton) activity.findViewById(R.id.Discard1Tile2);
        discard[2] = (ImageButton) activity.findViewById(R.id.Discard1Tile3);
        discard[3] = (ImageButton) activity.findViewById(R.id.Discard1Tile4);
        discard[4] = (ImageButton) activity.findViewById(R.id.Discard1Tile5);
        discard[5] = (ImageButton) activity.findViewById(R.id.Discard1Tile6);
        discard[6] = (ImageButton) activity.findViewById(R.id.Discard1Tile7);
        discard[7] = (ImageButton) activity.findViewById(R.id.Discard1Tile8);
        discard[8] = (ImageButton) activity.findViewById(R.id.Discard1Tile9);
        discard[9] = (ImageButton) activity.findViewById(R.id.Discard1Tile10);
        discard[10] = (ImageButton) activity.findViewById(R.id.Discard1Tile11);
        discard[11] = (ImageButton) activity.findViewById(R.id.Discard1Tile12);
        discard[12] = (ImageButton) activity.findViewById(R.id.Discard1Tile13);
        discard[13] = (ImageButton) activity.findViewById(R.id.Discard1Tile14);
        discard[14] = (ImageButton) activity.findViewById(R.id.Discard1Tile15);
        discard[15] = (ImageButton) activity.findViewById(R.id.Discard1Tile16);
        discard[16] = (ImageButton) activity.findViewById(R.id.Discard1Tile17);
        discard[17] = (ImageButton) activity.findViewById(R.id.Discard1Tile18);
        discard[18] = (ImageButton) activity.findViewById(R.id.Discard1Tile19);
        discard[19] = (ImageButton) activity.findViewById(R.id.Discard1Tile20);
        discard[20] = (ImageButton) activity.findViewById(R.id.Discard1Tile21);
        discard[21] = (ImageButton) activity.findViewById(R.id.Discard1Tile22);
        discard[22] = (ImageButton) activity.findViewById(R.id.Discard1Tile23);
        discard[23] = (ImageButton) activity.findViewById(R.id.Discard1Tile24);
        discard[24] = (ImageButton) activity.findViewById(R.id.Discard1Tile25);
        discard[25] = (ImageButton) activity.findViewById(R.id.Discard1Tile26);
        discard[26] = (ImageButton) activity.findViewById(R.id.Discard1Tile27);
        discard[27] = (ImageButton) activity.findViewById(R.id.Discard1Tile28);
        discard[28] = (ImageButton) activity.findViewById(R.id.Discard1Tile29);
        discard[29] = (ImageButton) activity.findViewById(R.id.Discard1Tile30);
        discard[30] = (ImageButton) activity.findViewById(R.id.Discard1Tile31);
        discard[31] = (ImageButton) activity.findViewById(R.id.Discard1Tile32);
        discard[32] = (ImageButton) activity.findViewById(R.id.Discard1Tile33);
        discard[33] = (ImageButton) activity.findViewById(R.id.Discard1Tile34);
        discard[34] = (ImageButton) activity.findViewById(R.id.Discard1Tile35);
        discard[35] = (ImageButton) activity.findViewById(R.id.Discard1Tile36);
        discard[36] = (ImageButton) activity.findViewById(R.id.Discard1Tile37);
        discard[37] = (ImageButton) activity.findViewById(R.id.Discard1Tile38);
        discard[38] = (ImageButton) activity.findViewById(R.id.Discard1Tile39);
        discard[39] = (ImageButton) activity.findViewById(R.id.Discard1Tile40);
        discard[40] = (ImageButton) activity.findViewById(R.id.Discard1Tile41);
        discard[41] = (ImageButton) activity.findViewById(R.id.Discard1Tile42);
        discard[42] = (ImageButton) activity.findViewById(R.id.Discard1Tile43);
        discard[43] = (ImageButton) activity.findViewById(R.id.Discard1Tile44);
        discard[44] = (ImageButton) activity.findViewById(R.id.Discard1Tile45);
        discard[45] = (ImageButton) activity.findViewById(R.id.Discard1Tile46);
        discard[46] = (ImageButton) activity.findViewById(R.id.Discard1Tile47);
        discard[47] = (ImageButton) activity.findViewById(R.id.Discard1Tile48);
        discard[48] = (ImageButton) activity.findViewById(R.id.Discard1Tile49);
        discard[49] = (ImageButton) activity.findViewById(R.id.Discard1Tile50);
        discard[50] = (ImageButton) activity.findViewById(R.id.Discard1Tile51);
        discard[51] = (ImageButton) activity.findViewById(R.id.Discard1Tile52);
        discard[52] = (ImageButton) activity.findViewById(R.id.Discard1Tile53);
        discard[53] = (ImageButton) activity.findViewById(R.id.Discard1Tile54);
        discard[54] = (ImageButton) activity.findViewById(R.id.Discard1Tile55);
        discard[55] = (ImageButton) activity.findViewById(R.id.Discard1Tile56);
        discard[56] = (ImageButton) activity.findViewById(R.id.Discard1Tile57);
        discard[57] = (ImageButton) activity.findViewById(R.id.Discard1Tile58);
        discard[58] = (ImageButton) activity.findViewById(R.id.Discard1Tile59);
        discard[59] = (ImageButton) activity.findViewById(R.id.Discard1Tile60);
        discard[60] = (ImageButton) activity.findViewById(R.id.Discard1Tile61);
        discard[61] = (ImageButton) activity.findViewById(R.id.Discard1Tile62);
        discard[62] = (ImageButton) activity.findViewById(R.id.Discard1Tile63);
        discard[63] = (ImageButton) activity.findViewById(R.id.Discard1Tile64);
        discard[64] = (ImageButton) activity.findViewById(R.id.Discard1Tile65);
        discard[65] = (ImageButton) activity.findViewById(R.id.Discard1Tile66);
        discard[66] = (ImageButton) activity.findViewById(R.id.Discard1Tile67);
        discard[67] = (ImageButton) activity.findViewById(R.id.Discard1Tile68);
        discard[68] = (ImageButton) activity.findViewById(R.id.Discard1Tile69);
        discard[69] = (ImageButton) activity.findViewById(R.id.Discard1Tile70);
        discard[70] = (ImageButton) activity.findViewById(R.id.Discard1Tile71);
        discard[71] = (ImageButton) activity.findViewById(R.id.Discard1Tile72);
        discard[72] = (ImageButton) activity.findViewById(R.id.Discard1Tile73);
        discard[73] = (ImageButton) activity.findViewById(R.id.Discard1Tile74);
        discard[74] = (ImageButton) activity.findViewById(R.id.Discard1Tile75);
        discard[75] = (ImageButton) activity.findViewById(R.id.Discard1Tile76);
        discard[76] = (ImageButton) activity.findViewById(R.id.Discard1Tile77);
        discard[77] = (ImageButton) activity.findViewById(R.id.Discard1Tile78);
        discard[78] = (ImageButton) activity.findViewById(R.id.Discard1Tile79);
        discard[79] = (ImageButton) activity.findViewById(R.id.Discard1Tile80);
        discard[80] = (ImageButton) activity.findViewById(R.id.Discard1Tile81);
        discard[81] = (ImageButton) activity.findViewById(R.id.Discard1Tile82);
        discard[82] = (ImageButton) activity.findViewById(R.id.Discard1Tile83);
        discard[83] = (ImageButton) activity.findViewById(R.id.Discard1Tile84);
        discard[84] = (ImageButton) activity.findViewById(R.id.Discard1Tile85);
        discard[85] = (ImageButton) activity.findViewById(R.id.Discard1Tile86);
        discard[86] = (ImageButton) activity.findViewById(R.id.Discard1Tile87);
        discard[87] = (ImageButton) activity.findViewById(R.id.Discard1Tile88);





        for (int i = 0; i < myTiles.length; i++) {
            myTiles[i].setOnClickListener(this);
        }
        for (int i = 0; i < tilePressed.length; i++) {
            tilePressed[i] = false;
        }
        displayTextBox = (TextView) activity.findViewById(R.id.DisplayTextBox);
        //
        //sortButton = (Button) activity.findViewById(R.id.sortButton);
        //sortButton.setOnClickListener(this);
        chowButton = (Button) activity.findViewById(R.id.chowButton);
        chowButton.setOnClickListener(this);
        turnIndicator = (TextView) activity.findViewById(R.id.TurnIndicator);
        turnIndicator.setOnClickListener(this);
        quitButton = (Button) activity.findViewById(R.id.quitButton);
        quitButton.setOnClickListener(this);
        pongButton = (Button) activity.findViewById(R.id.pongButton);
        pongButton.setOnClickListener(this);
        kongButton = (Button) activity.findViewById(R.id.kongButton);
        kongButton.setOnClickListener(this);
        discardButton = (Button) activity.findViewById(R.id.discardButton);
        discardButton.setOnClickListener(this);
        passButton = (Button) activity.findViewById((R.id.PassButton));
        passButton.setOnClickListener(this);
        mahJongButton = (Button) activity.findViewById((R.id.mahjongButton));
        mahJongButton.setOnClickListener(this);
        /**
         *
        emoteButton = (Button) activity.findViewById(R.id.emoteButton);
        emoteButton.setOnClickListener(this);
        emoteSpinner = (Spinner) activity.findViewById(R.id.EmoteSpinner);
         */
        /*scoreSpinner = (Spinner) activity.findViewById(R.id.ScoreSpinner);
        String[] emoteSpinner = activity.getResources().getStringArray(R.array.emote_choices);
        String[] scoreSpinner = activity.getResources().getStringArray(R.array.emote_choices);*/
        if (state != null) {
            receiveInfo(state);
        }
    }

    /**
     * @Collin_Yu
     * @param v, the view that received the click action
     * governs:
     *           - Tile Selection
     *           - GUIPlayer Actions
     */
    @Override
    public void onClick(View v) {

        // if we are not yet connected to a game, ignore
        if (game == null) return;

        // Construct the action and send it to the game
        GameAction action = null;

        // for chowButton
        if (v == chowButton) {

            // check for valid gameStage
            if (this.playerNum == 0 && state.getGameStage() == 8 ||
                    state.getGameStage() == this.playerNum*2) {

                // create new ArrayList of Tile objects for referential purposes
                ArrayList<Tile> tiles = new ArrayList<>(0);

                // for each Tile highlighted by the player, add that tile to the new array
                for (int i = 0; i < tilePressed.length; i++) {
                    if (tilePressed[i]) {
                        tiles.add(state.getPlayerClosedHandTile(playerNum, i));
                    }

                }

                // catches null pointer for next legality check
                if (tiles.size() != 2) {
                    displayTextBox.setText("Invalid Chow!"); // if invalid, let the player know
                    return;

                }
                // legality check
                else if ((tiles.get(0).isAbove(tiles.get(1)) && tiles.get(1).isAbove(state.getCurrDiscard()) ||
                        (tiles.get(0).isAbove(state.getCurrDiscard()) && state.getCurrDiscard().isAbove(tiles.get(1)))  ||
                        (tiles.get(1).isAbove(tiles.get(0)) && tiles.get(0).isAbove(state.getCurrDiscard()))  ||
                        (tiles.get(1).isAbove(state.getCurrDiscard()) && state.getCurrDiscard().isAbove(tiles.get(0)))  ||
                        (state.getCurrDiscard().isAbove(tiles.get(1)) && tiles.get(1).isAbove(tiles.get(0))) ||
                        (state.getCurrDiscard().isAbove(tiles.get(0)) && tiles.get(0).isAbove(tiles.get(1))))) {

                    displayTextBox.setText("Nice Chow!"); // congratulate them on performing a simple game action

                    action = new Chow(this, playerNum, tiles.get(0), tiles.get(1), state.getCurrDiscard()); // confirm Chow action
                    for (int i = 0; i < tilePressed.length; i++){tilePressed[i] = false;} // reset hand highlighting
                } // end legality check
            } // gameStage check
            else {displayTextBox.setText("You can't Chow right now!");} // if invalid gameStage, let player know
        } // end chowButton

        // for pong button
        if (v == pongButton) {
            // gameStage check
            if (state.getGameStage() != (this.playerNum+1)*2 &&
                    state.getGameStage() % 2 == 0) {
                ArrayList<Tile> tiles = new ArrayList<>(0);  // initialize referential ArrayList

                // add all highlighted tiles to referential array
                for (int i = 0; i < tilePressed.length; i++) {
                    if (tilePressed[i]) {
                        tiles.add(state.getPlayerClosedHandTile(playerNum, i));
                    }
                }

                // catches null pointer for next legality check
                if (tiles.size() != 2) {
                    displayTextBox.setText("Invalid Pong!");
                    return;
                }
                 // legality check
                else if (tiles.get(0).isEqualTo(tiles.get(1)) &&
                        tiles.get(1).isEqualTo(state.getCurrDiscard()))
                {
                    // all checks passed, create pong action
                    action = new Pong(this, playerNum, tiles.get(0), tiles.get(1), state.getCurrDiscard());

                    displayTextBox.setText("Nice Pong!"); // congratulate user for simple game action

                    for (int i = 0; i < tilePressed.length; i++){tilePressed[i] = false;} // reset highlighting
                } // end legality check
                   
                else { // if illegal move
                    displayTextBox.setText("Invalid Pong!");
                    return;
                }
            } // end gameStage check
            else { // if gameStage check failed
                displayTextBox.setText("You can't Pong right now!");
            }
        } // end pong button

        // for kongButton calls
        if (v == kongButton) {

            // check for valid gameStage
            if (state.getGameStage() != (this.playerNum + 1) * 2 &&
                    state.getGameStage() % 2 == 0) {

                // new ArrayList of Tiles to handle kong action
                ArrayList<Tile> tiles = new ArrayList<>(0);

                // add all highlighted tiles in hand to referential ArrayList
                for (int i = 0; i < tilePressed.length; i++) {
                    if (tilePressed[i]) {
                        tiles.add(state.getPlayerClosedHandTile(playerNum, i));
                    }
                }

                // catch null pointer for next legality check
                if (tiles.size() != 3) {
                    displayTextBox.setText("Invalid Kong!");
                    return;
                }
                // legality check
                else if (tiles.get(0).isEqualTo(tiles.get(1)) &&
                        tiles.get(1).isEqualTo(state.getCurrDiscard()) &&
                        state.getCurrDiscard().isEqualTo(tiles.get(2)))
                {
                    // confirm Kong action
                    action = new Kong(this, playerNum, tiles.get(0), tiles.get(1), tiles.get(2), state.getCurrDiscard());

                    // congratulate the user for completing a simple game action
                    displayTextBox.setText("Nice Kong!");

                    // reset hand highlighting
                    for (int i = 0; i < tilePressed.length; i++){tilePressed[i] = false;}

                } // end legality check
                else { // if move didn't complete legality check
                    displayTextBox.setText("Invalid Kong!");
                    return;
                }
            } // end gameStage check

            else{ // if move didn't pass gameStage test
                displayTextBox.setText("You can't Kong right now!");
            }
        }

        // for pass button press
        if (v == passButton){
            // gamestage check
            if(state.getGameStage() % 2 == 0) {
                action = new Pass(this, this.playerNum);
                displayTextBox.setText("Passed");
            } // end gameStage check
            else{displayTextBox.setText("Invalid Move!");}
        }

        if (v == sortButton){
            Sort s = new Sort(this, this.playerNum);
            action = s;
        }
        /**
         if (v == emoteButton){
         String text = emoteSpinner.getSelectedItem().toString();
         displayTextBox.setText(text);
         }
         */
        if (v == discardButton) {
            if (!(state.getGameStage() == (this.playerNum+1)*2 -1)) {displayTextBox.setText("You can't Discard right now!");}
            else{
                int counter = 0;
                Tile tile = null;
                int pos = 0;
                for (int i = 0; i < tilePressed.length; i++) {
                    if (tilePressed[i]) {
                        tile = state.getPlayerClosedHandTile(playerNum, i);
                        pos = i;
                        counter++;
                    }
                }
                if (counter != 1) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    tilePressed[pos] = !tilePressed[pos];
                    action = new Discard(this, playerNum, tile);
                    displayTextBox.setText("Tile Discarded");
                }
            }

        }

        if (v == mahJongButton){
            if ((state.getGameStage() % 2) == 0 || (state.getGameStage()) == (this.playerNum*2 - 1)){
                int[] hand;
                hand = state.getWholeOpenHand(this.playerNum);
                if(state.getGameStage() % 2 == 0){hand[hand.length-1] = state.getCurrDiscard().getDeckPos();}
                if(state.hasMahJong(hand)){
                        action = new MahJong(this, playerNum);
                        displayTextBox.setText("You Win!");
                }

            }
            else
            {
                displayTextBox.setText("You don't have a MahJong!");
            }
        }

        if (v == quitButton){
            System.exit(0);
        }
        // for selecting a Tile in hand
        for (int i = 0; i < myTiles.length; i++) {
            if (v == myTiles[i]) {
                if (!tilePressed[i]) {
                    tilePressed[i] = true;
                    updateDisplay();
                } else {
                    tilePressed[i] = false;
                    updateDisplay();
                }
                return;
            }
        }
            game.sendAction(action);
            updateDisplay();
    }


}
