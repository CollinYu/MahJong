package edu.up.yu18.mahjong.game.MahJong.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private ImageButton[] tiles;
    private boolean[] tilePressed;
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

        tiles[0] = (ImageButton) activity.findViewById(R.id.tile1);
        tiles[1] = (ImageButton) activity.findViewById(R.id.tile2);
        tiles[2] = (ImageButton) activity.findViewById(R.id.tile3);
        tiles[3] = (ImageButton) activity.findViewById(R.id.tile4);
        tiles[4] = (ImageButton) activity.findViewById(R.id.tile5);
        tiles[5] = (ImageButton) activity.findViewById(R.id.tile6);
        tiles[6] = (ImageButton) activity.findViewById(R.id.tile7);
        tiles[7] = (ImageButton) activity.findViewById(R.id.tile8);
        tiles[8] = (ImageButton) activity.findViewById(R.id.tile9);
        tiles[9] = (ImageButton) activity.findViewById(R.id.tile10);
        tiles[10] = (ImageButton) activity.findViewById(R.id.tile11);
        tiles[11] = (ImageButton) activity.findViewById(R.id.tile12);
        tiles[12] = (ImageButton) activity.findViewById(R.id.tile13);
        tiles[13] = (ImageButton) activity.findViewById(R.id.tile14);
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].setOnClickListener(this);
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
            int counter = 0;
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                    counter++;
                }
                if (counter != 3) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Chow(this, playerNum, tiles.get(0), tiles.get(1), tiles.get(2));
                }
            }
        }
        if (v == pongButton) {
            int counter = 0;
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                    counter++;
                }
                if (counter != 3) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Pong(this, playerNum, tiles.get(0), tiles.get(1), tiles.get(2));
                }
            }
        }
        if (v == kongButton) {
            int counter = 0;
            ArrayList<Tile> tiles = new ArrayList<>(0);
            for (int i = 0; i < tilePressed.length; i++) {
                if (tilePressed[i]) {
                    tiles.add(state.getPlayerOpenHandTile(playerNum, i));
                    counter++;
                }
                if (counter != 4) {
                    displayTextBox.setText("Invalid Move!");
                    return;
                } else {
                    action = new Kong(this, playerNum, tiles.get(0), tiles.get(1), tiles.get(2), tiles.get(3));
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
        for (int i = 0; i < tiles.length; i++) {
            if (v == tiles[i]) {
                if (!tilePressed[i]) {
                    tiles[i].setBackgroundColor(0xFF0000B2);
                    tilePressed[i] = true;
                } else {
                    tiles[i].setBackgroundColor(0xFF00B200);
                    tilePressed[i] = false;
                }
                return;
            }
        }
        game.sendAction(action);
    }


}
