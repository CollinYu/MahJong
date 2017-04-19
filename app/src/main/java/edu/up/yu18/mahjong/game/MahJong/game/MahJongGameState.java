package edu.up.yu18.mahjong.game.MahJong.game;
import java.util.ArrayList;
import java.util.Arrays;

import edu.up.yu18.mahjong.game.MahJong.Objects.Deck;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameState;
import edu.up.yu18.mahjong.game.frameWork.base.util.GameTimer;


public class MahJongGameState extends GameState {
    private ArrayList<Tile> deck;
    private int deckPosition;
    private int passingPlayers;
    private int[] wall;
    private int[] discardPile;
    private String[] playerNames;
    // Note that the int arrays below are [playerID][tileID]
    private int[][] playerClosedHands;
    private int[][] playerOpenHands;
    private int[] playerMJProg = {0,0,0,0};
    private Tile currDiscard;
    // whose turn it is will be expressed in stages, player 1's turn
    //will be stage 1, player 1's post-discard phase will be stage 2,
    // player 2's turn will be stage 3, her discard phase will be stage 4,
    //and so forth, resulting in the game being split up into stages 1 through 8
    private int gameStage;


    //Constructor
    public MahJongGameState() {
        passingPlayers = 0;
        Deck d = new Deck();
        deck = d.getDeckTiles();
        playerClosedHands = new int[4][14]; // 4 players, 14 cards max
        for(int i = 0; i < 14; i++) {
            playerClosedHands[0][i] = i;
        }
        for(int i = 1; i < 4; i++){
            for( int j = 0; j < 13; j++){
                playerClosedHands[i][j] = 1 + i*13 + j;
            }
            playerClosedHands[i][13] = -1;
        }
        
        wall = new int[84]; // 136 - 52 Tiles initially (hands dealt before deck is made)
        for(int i = 0; i < 84; i++){wall[i] = i+52;}
        discardPile = new int[84]; // at least 52 Tiles in all 4 players hands, thus max size is 84
        for(int i = 0; i < discardPile.length; i++){discardPile[i] = -1;}
        deckPosition = 52;         
        playerNames = new String[4]; // 4 players
        playerOpenHands = new int[4][16]; // 4 players, 16 cards max (4 Kongs)
        gameStage = 1;
    }

    // Getters and Setters for Instance Variables
    public void setWall(int tile, int pos) {
        this.wall[pos] = tile;
    }
    public int getWall(int pos) {
        return wall[pos];
    }
    public void setDiscardPile(int tile, int pos) {
        this.discardPile[pos] = tile;
    }
    public int getDiscardPile(int pos) {
        return this.discardPile[pos];
    }
    public void setPlayerName(String name, int player) {
        this.playerNames[player] = name;
    }
    public String getPlayerName(int player) {
        return this.playerNames[player];
    }
    public void setPlayerClosedHandTile(int player, int tile, int pos) {this.playerClosedHands[player][pos] = tile;}
    public Tile getPlayerClosedHandTile(int player, int pos) {return deck.get(this.playerClosedHands[player][pos]);}
    public void setPlayerOpenHandTile(int player, int tile, int pos) {this.playerOpenHands[player][pos] = tile;}
    public Tile getPlayerOpenHandTile(int player, int pos) {return deck.get(this.playerOpenHands[player][pos]);}
    public int getPlayerOpenHandLength(int player){return playerOpenHands[player].length;}
    public Tile getCurrDiscard(){return this.currDiscard;}
    public void setGameStage(int stage) {
        this.gameStage = stage;
    }
    public int getGameStage() {return this.gameStage;}

    // Takes an existing mahJongGameState object as a parameter to
    // initialize a new object with the same attributes
    public MahJongGameState(MahJongGameState game) {

        // Goes through each element of int[] wall and makes it individually
        // a copy of the equivalent element of the parameter
        deck = game.deck;
        wall = new int[game.wall.length];
        for (int i = 0; i < game.wall.length; i++) {
            wall[i] = game.wall[i];
        }

        discardPile = new int[game.discardPile.length];
        for (int i = 0; i < game.discardPile.length; i++) {
            discardPile[i] = game.discardPile[i];
        }

        playerNames = new String[game.playerNames.length];
        for (int i = 0; i < game.playerNames.length; i++) {
            if (playerNames[i] != null) {
                playerNames[i] = game.playerNames[i];
            }
        }


        playerClosedHands = new int[game.playerClosedHands.length][]; // sets the "array of arrays" to the equivalent size - playerClosedHands[copies this part's length][]
        for (int i = 0; i < game.playerClosedHands.length; i++) { // goes through each array in the "array of arrays" - playerClosedHands[goes through this part][]
            playerClosedHands[i] = Arrays.copyOf(game.playerClosedHands[i], game.playerClosedHands[i].length); // sets array length to that of the equivalent array - playerClosedHands[][copies this part's length]
        }

        playerOpenHands = new int[game.playerOpenHands.length][];
        for (int i = 0; i < game.playerOpenHands.length; i++) {
            playerOpenHands[i] = Arrays.copyOf(game.playerOpenHands[i], game.playerOpenHands.length);
        }

        for(int i = 0; i < 4; i++){
            sort(i);
        }
        gameStage = game.gameStage; // pretty obvious how this one works

    }

    public void Chow(Chow c) {
        // Check legality
        if (c.getTile1().isAbove(c.getTile2()) && c.getTile2().isAbove(c.getTile3()) ||
                c.getTile2().isAbove(c.getTile1()) && c.getTile1().isAbove(c.getTile3()) ||
                c.getTile1().isAbove(c.getTile3()) && c.getTile3().isAbove(c.getTile2()) ||
                c.getTile2().isAbove(c.getTile3()) && c.getTile3().isAbove(c.getTile1()) ||
                c.getTile3().isAbove(c.getTile1()) && c.getTile1().isAbove(c.getTile2()) ||
                c.getTile3().isAbove(c.getTile2()) && c.getTile2().isAbove(c.getTile1())) {

            // Set next open openhand slot to Tile 1
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == -1) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile1().getDeckPos(), i);
                    return;
                }
            }


            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[c.getPlayerID()][i] == c.getTile1().getDeckPos()) {
                    setPlayerClosedHandTile(c.getPlayerID(), -1, i);
                    return;
                }
            }


            // Set next open openhand slot to Tile 2
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == -1) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile2().getDeckPos(), i);
                    return;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[c.getPlayerID()][i] == c.getTile2().getDeckPos()) {
                    setPlayerClosedHandTile(c.getPlayerID(), -1, i);
                    return;
                }
            }


            // Set next open openhand slot to Tile 3
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == -1) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile3().getDeckPos(), i);
                    return;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[c.getPlayerID()][i] == c.getTile3().getDeckPos()) {
                    setPlayerClosedHandTile(c.getPlayerID(), -1, i);
                    return;
                }
            }
        }
        playerMJProg[c.getPlayerID()]++;
        draw(gameStage/2 + 1);
        passingPlayers = 0;
        gameStage++;
    }

    public void Pong(Pong p) {
        if (p.getTile1().isEqualto(p.getTile2()) && p.getTile2().isEqualto(p.getTile3())) {
            // Set next open openhand slot to Tile 1
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                    setPlayerOpenHandTile(p.getPlayerID(), p.getTile1().getDeckPos(), i);
                    return;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile1().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                    return;
                }
            }


            // Set next open openhand slot to Tile 2
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                    setPlayerClosedHandTile(p.getPlayerID(), p.getTile2().getDeckPos(), i);
                    return;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile2().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                    return;
                }
            }


            // Set next open openhand slot to Tile 3
            for (int i = 0; i < 16; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile3().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                    return;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < 16; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                    setPlayerOpenHandTile(p.getPlayerID(), p.getTile3().getDeckPos(), i);
                    return;
                }
            }
        }

        playerMJProg[p.getPlayerID()]++;
        draw(gameStage/2 + 1);
        passingPlayers = 0;
        gameStage++;
    }

    public void Kong(Kong p) {
        // Check Legality
        if (p.getTile1().isEqualto(p.getTile2()) && p.getTile2().isEqualto(p.getTile3()) && p.getTile3().isEqualto(p.getTile4())) {

        }
        // Set 1st open slot to Tile 1
        for (int i = 0; i < 16; i++) {
            if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                setPlayerOpenHandTile(p.getPlayerID(), p.getTile1().getDeckPos(), i);
                return;
            }
        }
        // Find the tile in your hand, and remove it
        for (int i = 0; i < 16; i++) {
            if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile1().getDeckPos()) {
                setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                return;
            }
        }
         // Set next open slot to Tile 2
        for (int i = 0; i < 16; i++) {
            if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                setPlayerOpenHandTile(p.getPlayerID(), p.getTile2().getDeckPos(), i);
                return;
            }
        }
        // Find the tile in your hand, and remove it
        for (int i = 0; i < 16; i++) {
            if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile2().getDeckPos()) {
                setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                return;
            }
        }

        // Set next open openhand slot to Tile 3
        for (int i = 0; i < 16; i++) {
            if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                setPlayerOpenHandTile(p.getPlayerID(), p.getTile3().getDeckPos(), i);
            }
        }
        // Find the tile in your hand, and remove it
        for (int i = 0; i < 16; i++) {
            if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile3().getDeckPos()) {
                setPlayerClosedHandTile(p.getPlayerID(), -1, i);
                return;
            }
        }
        // Set next open openhand slot to Tile 4
        for (int i = 0; i < 16; i++) {
            if (this.playerOpenHands[p.getPlayerID()][i] == -1) {
                setPlayerOpenHandTile(p.getPlayerID(), p.getTile4().getDeckPos(), i);
                return;
            }
        }
        // Find the tile in your hand, and remove it
        for (int i = 0; i < 16; i++) {
            if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile4().getDeckPos()) {
                setPlayerOpenHandTile(p.getPlayerID(), p.getTile3().getDeckPos(), i);
                return;
            }

        }
        playerMJProg[p.getPlayerID()]++;
        draw(gameStage/2 + 1);
        passingPlayers = 0;
        gameStage++;
    }

    public void Discard(Discard d) {
        // Remove it from closedhand
        for (int i = 0; i < 14; i++) {
            if (this.playerClosedHands[d.getPlayerID()][i] == d.getTile().getDeckPos()) {
                setPlayerClosedHandTile(d.getPlayerID(), -1, i);
                return;
            }
        }
        currDiscard = d.getTile();
        gameStage++;
    }
    public void pass(){
        passingPlayers++;
        if(passingPlayers == 4) {
            if (currDiscard != null) {
                for (int i = 0; i < discardPile.length; i++) {
                    if (discardPile[i] == -1) {
                        discardPile[i] = currDiscard.getDeckPos();
                        currDiscard = null;
                        draw(gameStage / 2 + 1);
                        gameStage++;
                        passingPlayers = 0;
                        break;
                    }
                }
            }
        }

    }
    public void sort(int pID){
        int temp = 0;
            for (int i = 0; i < playerClosedHands[pID].length - 1; i++) {
                for (int j = i + 1; j < playerClosedHands[pID].length; j++) {
                    if (deck.get(playerClosedHands[pID][i]).getID() > deck.get(playerClosedHands[pID][j]).getID()) {
                        temp = playerClosedHands[pID][j];
                        playerClosedHands[pID][j] = playerClosedHands[pID][i];
                        playerClosedHands[pID][i] = temp;
                    }
                }
            }

    }
    
    public void draw(int pID){
        for(int i= 0; i < playerClosedHands[pID][i]; i++){
            if(playerClosedHands[pID][i] != -1){
                playerClosedHands[pID][i] = deckPosition;
                deckPosition++;
                sort(pID);
                break;
            }
        }
    }

        public boolean hasMahJong(int pID){
            for(int i = 0; i < playerOpenHands[pID].length; i+=3){
                try {
                    if (deck.get(playerOpenHands[pID][i]).isEqualto(deck.get(playerOpenHands[pID][i + 1])) &&
                            deck.get(playerOpenHands[pID][i + 1]).isEqualto(deck.get(playerOpenHands[pID][i + 2])))
                    {
                        playerMJProg[pID]++;
                    }
                } catch (NullPointerException n){}

                try {
                    if (deck.get(playerOpenHands[pID][i]).isBelow(deck.get(playerOpenHands[pID][i + 1])) &&
                            deck.get(playerOpenHands[pID][i + 1]).isBelow(deck.get(playerOpenHands[pID][i + 2]))) {
                        playerMJProg[pID]++;
                    }
                } catch (NullPointerException n){}
                try {
                    if (i + 3 > playerOpenHands[pID].length) {
                        if (deck.get(playerOpenHands[pID][i + 4]).isBelow(deck.get(playerOpenHands[pID][i + 5]))) {
                            playerMJProg[pID]++;
                        }

                    }
                } catch (NullPointerException n){}
            }
            if (playerMJProg[pID] == 5){return true;}
            else {return  false;}
        }

    }

