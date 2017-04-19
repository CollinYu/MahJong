package edu.up.yu18.mahjong.game.MahJong.game;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.yu18.mahjong.R;
import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameHumanPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameMainActivity;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;


public class MahJongHumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    private ImageButton[] myTiles;
    private boolean[] tilePressed;
    private ImageView[][] playerClosedHand;
    private ImageButton[][] playerOpenHand;
    private TextView displayTextBox;
    private Button chowButton;
    private Button pongButton;
    private Button kongButton;
    private Button discardButton;
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
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 14; i++) {
                if (state.getPlayerClosedHandTile(playerNum, i) != null) {
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
        for (int i = 0; i < 14; i++){
            playerClosedHand[0][i].setBackgroundResource(R.mipmap.mahjongtile_back2);
        }
        for (int i = 0; i < 14; i++){
            playerClosedHand[1][i].setBackgroundResource(R.mipmap.mahjongtile_back2);
        }
        for (int i = 0; i < 14; i++){
            playerClosedHand[2][i].setBackgroundResource(R.mipmap.mahjongtile_back);
        }

       /* for (int k = 0; k < 4; k++) {
            for (int i = 0; i < 14; i++) {
                if (state.getPlayerClosedHandTile(playerNum, i) != null) {
                    //if suit is dots suit is 0
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 0) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots1);
                                } else if (j == 1) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots2);
                                } else if (j == 2) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots3);
                                } else if (j == 3) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots4);
                                } else if (j == 4) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots5);
                                } else if (j == 5) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots6);
                                } else if (j == 6) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots7);
                                } else if (j == 7) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots8);
                                } else if (j == 8) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledots9);
                                }
                            }
                        }
                    }
                    //if suit is bamboo suit is 1
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 1) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo1);
                                } else if (j == 1) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo2);
                                } else if (j == 2) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo3);
                                } else if (j == 3) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo4);
                                } else if (j == 4) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo5);
                                } else if (j == 5) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo6);
                                } else if (j == 6) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo7);
                                } else if (j == 7) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo8);
                                } else if (j == 8) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilebamboo9);
                                }
                            }
                        }
                    }
                    //if suit is character suit is 2
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 2) {
                        for (int j = 0; j < 9; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter1);
                                } else if (j == 1) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter2);
                                } else if (j == 2) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter3);
                                } else if (j == 3) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter4);
                                } else if (j == 4) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter5);
                                } else if (j == 5) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter6);
                                } else if (j == 6) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter7);
                                } else if (j == 7) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter8);
                                } else if (j == 8) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtilecharacter9);
                                }
                            }
                        }
                    }
                    //if suit is dragon suit is 3
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 3) {
                        for (int j = 0; j < 3; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledragongreen);
                                } else if (j == 1) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledragonred);
                                } else if (j == 2) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledragonwhite);
                                }
                            }
                        }

                    }
                    //if suit is wind suit is 4
                    if (state.getPlayerClosedHandTile(playerNum, i).getSuit() == 4) {
                        for (int j = 0; j < 4; j++) {
                            if (state.getPlayerClosedHandTile(playerNum, i).getVal() == j) {
                                if (j == 0) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledirectioneast);
                                } else if (j == 1) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledirectionnorth);
                                } else if (j == 2) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledirectionsouth);
                                } else if (j == 3) {
                                    playerClosedHand[k][i].setBackgroundResource(R.drawable.mahjongtiledirectionwest);
                                }
                            }
                        }

                    }
                }

            }

        }*/
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
        for (int i = 0; i < myTiles.length; i++) {
            myTiles[i].setOnClickListener(this);
        }
        for (int i = 0; i < tilePressed.length; i++) {
            tilePressed[i] = false;
        }
        displayTextBox = (TextView) activity.findViewById(R.id.DisplayTextBox);
        chowButton = (Button) activity.findViewById(R.id.chowButton);
        chowButton.setOnClickListener(this);
        pongButton = (Button) activity.findViewById(R.id.pongButton);
        pongButton.setOnClickListener(this);
        kongButton = (Button) activity.findViewById(R.id.kongButton);
        kongButton.setOnClickListener(this);
        discardButton = (Button) activity.findViewById(R.id.discardButton);
        discardButton.setOnClickListener(this);
        emoteButton = (Button) activity.findViewById(R.id.emoteButton);
        emoteButton.setOnClickListener(this);
        emoteSpinner = (Spinner) activity.findViewById(R.id.EmoteSpinner);
        scoreSpinner = (Spinner) activity.findViewById(R.id.ScoreSpinner);
        String[] emoteSpinner = activity.getResources().getStringArray(R.array.emote_choices);
        String[] scoreSpinner = activity.getResources().getStringArray(R.array.emote_choices);

    }

    @Override
    public void onClick(View v) {

        // if we are not yet connected to a game, ignore
        if (game == null) return;

        // Construct the action and send it to the game
        GameAction action = null;

        if (v == chowButton) {
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                }
                if (tiles.size() != 2) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Chow(this, playerNum, tiles.get(0), tiles.get(1), state.getCurrDiscard());
                }
            }
        }
        if (v == pongButton) {
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                }
                if (tiles.size() != 2) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Pong(this, playerNum, tiles.get(0), tiles.get(1), state.getCurrDiscard());
                }
            }
        }
        if (v == kongButton) {
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                }
                if (tiles.size() != 3) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Kong(this, playerNum, tiles.get(0), tiles.get(1), tiles.get(2), state.getCurrDiscard());
                }
            }
        }
        /**
         if (v == emoteButton){
         String text = emoteSpinner.getSelectedItem().toString();
         displayTextBox.setText(text);
         }
         */
        if (v == discardButton) {
            int counter = 0;
            Tile tile = null;
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tile = state.getPlayerOpenHandTile(playerNum, i);
                    counter++;
                }
                if (counter != 1) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Discard(this, playerNum, tile);
                }
            }
        }
        for (int i = 0; i < myTiles.length; i++) {
            if (v == myTiles[i]) {
                if (!tilePressed[i]) {
                    myTiles[i].setBackgroundColor(0xFF0000B2);
                    tilePressed[i] = true;
                } else {
                    myTiles[i].setBackgroundColor(0xFF00B200);
                    tilePressed[i] = false;
                }
                return;
            }
        }
        game.sendAction(action);
    }


}
