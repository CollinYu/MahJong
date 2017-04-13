package edu.up.yu18.mahjong.game.MahJong.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import edu.up.yu18.mahjong.R;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameHumanPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameMainActivity;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;


public class MahJongHumanPlayer extends GameHumanPlayer implements View.OnClickListener{
    private ImageButton tile1;
    private ImageButton tile2;
    private ImageButton tile3;
    private ImageButton tile4;
    private boolean tile1IsPressed = false;
    private boolean tile2IsPressed;
    private boolean tile3IsPressed;
    private boolean tile4IsPressed;
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

    public MahJongHumanPlayer(String name){
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

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


        tile1 = (ImageButton) activity.findViewById(R.id.tile1);
        tile1.setOnClickListener(this);
        tile2 = (ImageButton) activity.findViewById(R.id.tile2);
        tile2.setOnClickListener(this);
        tile3 = (ImageButton) activity.findViewById(R.id.tile3);
        tile3.setOnClickListener(this);
        tile4 = (ImageButton) activity.findViewById(R.id.tile4);
        tile4.setOnClickListener(this);
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

        if (v == chowButton){displayTextBox.setText("Invalid Move: Failed Chow");}
        if (v == pongButton){displayTextBox.setText("Invalid Move: Failed Pong");}
        if (v == kongButton){displayTextBox.setText("Invalid Move: Failed Kong");}
        if (v == emoteButton){
            String text = emoteSpinner.getSelectedItem().toString();
            displayTextBox.setText(text);
        }
        if (v == discardButton){
            if(tile1IsPressed == true)
           {
               tile1.setVisibility(View.INVISIBLE);
               tile1IsPressed = false;
          }
        else
        {
            displayTextBox.setText("Invalid Move: Failed Discard");
        }}
        if (v == tile1) {
            if (!tile1IsPressed) {
                tile1.setBackgroundColor(0xFF0000B2);
                tile1IsPressed = true;
            } else {
                tile1.setBackgroundColor(0xFF00B200);
                tile1IsPressed = false;
            }
        }
        if (v == tile2) {
            if (!tile2IsPressed) {
                tile2.setBackgroundColor(0xFF0000B2);
                tile2IsPressed = true;
            } else {
                tile2.setBackgroundColor(0xFF00B200);
                tile2IsPressed = false;
            }
        }
        if (v == tile3) {
            if (!tile3IsPressed) {
                tile3.setBackgroundColor(0xFF0000B2);
                tile3IsPressed = true;
            } else {
                tile3.setBackgroundColor(0xFF00B200);
                tile3IsPressed = false;
            }
        }
        if (v == tile4) {
            if (!tile4IsPressed) {
                tile4.setBackgroundColor(0xFF0000B2);
                tile4IsPressed = true;
            } else {
                tile4.setBackgroundColor(0xFF00B200);
                tile4IsPressed = false;
            }
        }



        }



    public void setGameOver(boolean b){}
}
