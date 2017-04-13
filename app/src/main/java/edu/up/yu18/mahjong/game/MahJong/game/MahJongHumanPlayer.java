package edu.up.yu18.mahjong.game.MahJong.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import edu.up.yu18.mahjong.R;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameHumanPlayer;


public class MahJongHumanPlayer extends GameHumanPlayer{
    private ImageButton tile1;
    private ImageButton tile2;
    private ImageButton tile3;
    private ImageButton tile4;
    private boolean tile1IsPressed;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tile1IsPressed = false;
        tile1 = (ImageButton)findViewById(R.id.tile1);
        tile1.setOnClickListener(new yourTileListener());
        tile2 = (ImageButton)findViewById(R.id.tile2);
        tile2.setOnClickListener(new yourTileListener());
        tile3 = (ImageButton)findViewById(R.id.tile3);
        tile3.setOnClickListener(new yourTileListener());
        tile4 = (ImageButton)findViewById(R.id.tile4);
        tile4.setOnClickListener(new yourTileListener());
        displayTextBox =(TextView)findViewById(R.id.DisplayTextBox);
        chowButton = (Button)findViewById(R.id.chowButton);
        chowButton.setOnClickListener(new chowButtonListener());
        pongButton = (Button)findViewById(R.id.pongButton);
        pongButton.setOnClickListener(new pongButtonListener());
        kongButton = (Button)findViewById(R.id.kongButton);
        kongButton.setOnClickListener(new kongButtonListener());
        discardButton = (Button)findViewById(R.id.discardButton);
        discardButton.setOnClickListener(new discardButtonListener());
        emoteButton = (Button)findViewById(R.id.emoteButton);
        emoteButton.setOnClickListener(new emoteButtonListener());
        emoteSpinner = (Spinner)findViewById(R.id.EmoteSpinner);
        scoreSpinner = (Spinner)findViewById(R.id.ScoreSpinner);
        String[] emoteSpinner = getResources().getStringArray(R.array.emote_choices);
        String[] scoreSpinner = getResources().getStringArray(R.array.emote_choices);


    }

    private class yourTileListener implements View.OnClickListener {
        public void onClick(View v) {
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
    }

    private class chowButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            displayTextBox.setText("Invalid Move: Failed Chow");
        }
    }
    private class pongButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            displayTextBox.setText("Invalid Move: Failed Pong");
        }
    }
    private class kongButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            displayTextBox.setText("Invalid Move: Failed Kong");
        }
    }
    private class discardButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            if(tile1IsPressed == true)
            {
                tile1.setVisibility(View.INVISIBLE);
                tile1IsPressed = false;
            }
            else
            {
                displayTextBox.setText("Invalid Move: Failed Discard");
            }
        }
    }
    private class emoteButtonListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            String text = emoteSpinner.getSelectedItem().toString();
            displayTextBox.setText(text);
        }
    }
    public void setGameOver(boolean b){}
}