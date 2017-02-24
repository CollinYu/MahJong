package edu.up.yu18.mahjong;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.media.Image;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;




public class MainActivity extends AppCompatActivity{
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
    private class mahJongGameState{
        private ArrayList<Tile> deck;
        private int[] wall;
        private int[] discardPile;
        private int[] players;
        private String[] playerNames;
        // Note that the int arrays below are [playerID][tileID]
        private int[][] playerClosedHands;
        private int[][] playerOpenHands;
        // whose turn it is will be expressed in stages, player 1's turn will be stage 1, player 1's post-discard phase will be stage 2,
        // player 2's turn will be stage 3, her discard phase will be stage 4, and so forth, resulting in the game being split up into stages 1 through 8
        private int gameStage;
        

        //Constructor
        private mahJongGameState(){


        }

        // Takes an existing mahJongGameState object as a parameter to
        // initialize a new object with the same attributes
        public mahJongGameState(mahJongGameState game){

            // Goes through each element of int[] wall and makes it individually
            // a copy of the equivalent element of the parameter
            wall = new int[game.wall.length];
            for (int i = 0; i < game.wall.length; i++){
                wall[i] = game.wall[i];
            }

            discardPile = new int[game.discardPile.length];
            for (int i = 0; i < game.discardPile.length; i++){
                discardPile[i] = game.discardPile[i];
            }

            players = new int[players.length];
            for (int i = 0; i < game.players.length; i++){
                players[i] = game.players[i];
            }

            playerNames = new String[game.playerNames.length];
            for (int i = 0; i < game.playerNames.length; i++){
                playerNames[i] = game.playerNames[i];
            }


            playerClosedHands = new int [game.playerClosedHands.length][]; // sets the "array of arrays" to the equivalent size - playerClosedHands[copies this part's length][]
            for(int i = 0; i < game.playerClosedHands.length; i++){ // goes through each array in the "array of arrays" - playerClosedHands[goes through this part][]
                playerClosedHands[i] = new int[game.playerClosedHands.length]; // sets array length to that of the equivalent array - playerClosedHands[][copies this part's length]

                for (int j = 0; j < game.playerClosedHands[i].length; j++){ // goes through each element of the array in question - playerClosedHands[][goes through his part]
                    playerClosedHands[i][j] = game.playerClosedHands[i][j]; // sets the element to be the same as the equivalent element - playerClosedHands[][copies this part]
                }
            }

            playerOpenHands = new int [game.playerOpenHands.length][];
            for(int i = 0; i < game.playerOpenHands.length; i++){
                playerOpenHands[i] = new int[game.playerOpenHands.length];

                for (int j = 0; j < game.playerOpenHands[i].length; j++){
                    playerOpenHands[i][j] = game.playerOpenHands[i][j];
                }
            }

            gameStage = game.gameStage; // pretty obvious how this one works

        }

        // Need getters and setter methods


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
                        tile1.setVisibility(View.GONE);
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
    }


