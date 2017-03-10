package edu.up.yu18.mahjong.game;
import java.util.ArrayList;

import edu.up.yu18.mahjong.game.infoMsg.GameState;


public class mahJongGameState extends GameState{
    private ArrayList<Tile> deck;
    private int[] wall;
    private int[] discardPile;
    private int[] players;
    private String[] playerNames;
    // Note that the int arrays below are [playerID][tileID]
    private int[][] playerClosedHands;
    private int[][] playerOpenHands;
    // whose turn it is will be expressed in stages, player 1's turn
    //will be stage 1, player 1's post-discard phase will be stage 2,
    // player 2's turn will be stage 3, her discard phase will be stage 4,
    //and so forth, resulting in the game being split up into stages 1 through 8
    private int gameStage;


    //Constructor
    private mahJongGameState(){
        wall = new int[84]; // 136 - 52 Tiles initially (hands dealt before deck is made)
        discardPile = new int[84]; // at least 52 Tiles in all 4 players hands, thus max size is 84
        players = new int[4]; // 4 players
        playerNames = new String[4]; // 4 players
        playerClosedHands = new int [4][16]; // 4 players, 16 cards max (4 Kongs)
        playerOpenHands = new int [4][14]; // 4 players, 14 cards max
    }

    public void setWall(int tile, int pos) {this.wall[pos]=tile;}
    public int getWall(int pos) {return wall[pos];} // pos stands for position

    public void setDiscardPile(int tile, int pos) {this.discardPile[pos]=tile;}
    public int getDiscardPile(int pos) {return this.discardPile[pos];}


    public void setPlayerName(String name, int player) {this.playerNames[player] = name;}
    public String getPlayerName(int player) {return this.playerNames[player];}

    public void setPlayerClosedHandTile(int player, int pos, int tile){this.playerClosedHands[player][pos]=tile;}
    public int getPlayerClosedHandTile(int player, int pos){return this.playerClosedHands[player][pos];}

    public void setPlayerOpemHandTile(int player, int pos, int tile){this.playerOpenHands[player][pos]=tile;}
    public int getPlayerOpenHandTile(int player, int pos){return this.playerOpenHands[player][pos];}

    public void setGameStage(int stage){this.gameStage = stage;}
    public int getGameStage(int stage){return this.gameStage;}

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

    public void chow(int player, int tile1, int tile2, int tile3)
    {
            for(int i = 0; i < 16; i++) {
                if (this.playerClosedHands[i] != null)
                    setPlayerClosedHandTile(player, i, tile1);
                return;
            }

            for(int i = 0; i < 16; i++){
                    if(this.playerClosedHands[i]!=null)
                        setPlayerClosedHandTile(player, i, tile1);
            }
            for(int i = 0; i < 16; i++){
              if(this.playerClosedHands[i]!=null)
                   setPlayerClosedHandTile(player, i, tile1);
        }
    }


}