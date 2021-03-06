package edu.up.yu18.mahjong.game.MahJong.game;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Objects.Deck;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameState;
import edu.up.yu18.mahjong.game.frameWork.base.util.GameTimer;

/**
 * Current functionality of game as a whole:
 * Can Discard and Pass turn,
 * Chow, Pong, and Kong should work, but the visual feedback of the discard pile and the card currently being
 * discarded does not, so this portion has yet to be tested
 * Highlighting of hand tiles does not work
 * Can run through all eight stages of the game, win condition recently implemented and not fully tested but should work
 * DumbPlayer goes through the game passing and discarding accordingly
 * Sort and associated button work, but game automatically sorts hand anyways, so it is currently redundant
 */


/**
 * @Collin_Yu
 * The MahJongGameState class holds all of the information one would need to recreate the game from scratch, its alteration
 * represents any change in the game
 */
public class MahJongGameState extends GameState {

    // our deck sets itself up as a referential source, it shuffles itself and like the Random() class, one
    // simply needs to look at the next object to get the sense of randomization
    private ArrayList<Tile> deck;
    private static final long serialVersionUID = 1323064333136954L;

    // the reference point for the next tile in the deck
    private int deckPosition;

    // passingPlayers represents those players that have passed in a given post-discard phase
    private boolean[] passingPlayers = {
            false,
            false,
            false,
            false
    };

    // represents the undrawn portions of the deck
    private int[] wall;

    // represents the previously discarded tiles
    private int[] discardPile;

    // player names...
    private String[] playerNames;

    // Note that the int arrays below are [playerID][tileID]
    private int[][] playerClosedHands;
    private int[][] playerOpenHands;

    // Represents the players progress towards a mahjong, boolean hasMahjong(int playerID) utilizes this
    private int[] playerMJProg = {0, 0, 0, 0};

    // The most recently discarded card in a given post-discard phase
    private Tile currDiscard;

    // whose turn it is will be expressed in stages, player 1's turn
    //will be stage 1, player 1's post-discard phase will be stage 2,
    // player 2's turn will be stage 3, her discard phase will be stage 4,
    //and so forth, resulting in the game being split up into stages 1 through 8
    private int gameStage;

    private boolean outOfCards = false;

    /**
     * @Collin_Yu Initializes game with default and constant values.
     * Only called once at the start of the game.
     */

    public MahJongGameState() {

        // instantiate variables
        Deck d = new Deck();
        deck = d.getDeckTiles();
        playerClosedHands = new int[4][14]; // 4 players, 14 cards max
        for (int g = 0; g < 14; g++) {
            playerClosedHands[0][g] = g;
        }
        for (int l = 1; l < 4; l++) {
            for (int q = 0; q < 13; q++) {
                playerClosedHands[l][q] = 1 + l * 13 + q;
            }
            playerClosedHands[l][13] = 136;
        }

        wall = new int[84]; // 136 - 52 Tiles initially (hands dealt before deck is made)
        for (int i = 0; i < 84; i++) {
            wall[i] = i + 52;
        }
        discardPile = new int[84]; // at least 52 Tiles in all 4 players hands, thus max size is 84
        for (int i = 0; i < discardPile.length; i++) {
            discardPile[i] = 136;
        }
        deckPosition = 52;
        playerNames = new String[4]; // 4 players
        playerOpenHands = new int[4][16]; // 4 players, 16 cards max (4 Kongs)
        for (int k = 0; k < 4; k++) {
            for (int j = 0; j < 16; j++) {
                playerOpenHands[k][j] = 136;
            }
        }
        gameStage = 1;
    }

    public Tile deckGet(int deckPos){return deck.get(deckPos);}
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

    public int[] getWholeClosedHand(int player) {
        int[] hand = new int[playerClosedHands[player].length];
        for (int i = 0; i < playerClosedHands[player].length; i++) {
            hand[i] = playerClosedHands[player][i];
        }
        return hand;
    }

    public void setPlayerName(String name, int player) {
        this.playerNames[player] = name;
    }

    public String getPlayerName(int player) {
        return this.playerNames[player];
    }

    public int[] getWholeOpenHand(int player) {
        return this.playerOpenHands[player];
    }

    public int getPlayerOpenHands(int player, int pos) {
        return (this.playerOpenHands[player][pos]);
    }

    public void setPlayerClosedHandTile(int player, int tile, int pos) {
        this.playerClosedHands[player][pos] = tile;
    }

    public Tile getPlayerClosedHandTile(int player, int pos) {
        return deck.get(this.playerClosedHands[player][pos]);
    }

    public void setPlayerOpenHandTile(int player, int tile, int pos) {
        this.playerOpenHands[player][pos] = tile;
    }

    public Tile getPlayerOpenHandTile(int player, int pos) {
        return deck.get(this.playerOpenHands[player][pos]);
    }

    public Tile getDiscardPileTile(int player, int pos) {
        return deck.get(this.playerOpenHands[player][pos]);
    }

    public int getPlayerOpenHandLength(int player) {
        return playerOpenHands[player].length;
    }

    public int getPlayerClosedHandLength(int player) {
        return playerClosedHands[player].length;
    }

    public Tile getCurrDiscard() {
        return this.currDiscard;
    }

    public void setGameStage(int stage) {
        this.gameStage = stage;
    }

    public int getGameStage() {
        return this.gameStage;
    }

    public boolean hasPassed(int pID) {
        return passingPlayers[pID];
    }

    public boolean isOutOfCards() {
        return this.outOfCards;
    }

    /**
     * makeSubHand
     * <p>
     * removes 3 tiles from a given hand and returns the result. given
     * hand is unmodified
     */
    public int[] makeSubHand(int[] hand, int x, int y, int z) {
        int[] newHand = new int[hand.length - 3];
        int index = 0;
        for (int i = 0; i < hand.length; ++i) {
            if ((i != x) && (i != y) && (i != z)) {
                newHand[index] = hand[i];
                index++;
            }
        }

        return newHand;
    }

    /**
     * getIndexOfAbove
     * <p>
     * finds the first tile above a given time starting at a given
     * index in a
     * given hand
     */
    public int getIndexOfAbove(int[] hand, int belowTileIndex) {
        for (int i = belowTileIndex + 1; i < hand.length; ++i) {
            if (deck.get(hand[i]).isAbove(deck.get(hand[belowTileIndex]))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @Collin_Yu Takes an existing mahJongGameState object as a parameter to
     * initialize a new object with the same attributes that does not point to the parameter
     * AKA deep copier
     */
    public MahJongGameState(MahJongGameState game) {

        // Goes through each element of int[] wall and makes it individually
        // a copy of the equivalent element of the parameter
        this.currDiscard = game.getCurrDiscard();
        for (int h = 0; h < 4; h++) {
            passingPlayers[h] = game.passingPlayers[h];
        }
        deck = game.deck;
        wall = new int[game.wall.length];
        for (int a = 0; a < game.wall.length; a++) {
            wall[a] = game.wall[a];
        }

        discardPile = new int[game.discardPile.length];
        for (int b = 0; b < game.discardPile.length; b++) {
            discardPile[b] = game.discardPile[b];
        }

        playerNames = new String[game.playerNames.length];
        for (int c = 0; c < game.playerNames.length; c++) {
            if (playerNames[c] != null) {
                playerNames[c] = game.playerNames[c];
            }
        }


        playerClosedHands = new int[4][14]; // sets the "array of arrays" to the equivalent size - playerClosedHands[copies this part's length][]
        for (int d = 0; d < 4; d++) { // goes through each array in the "array of arrays" - playerClosedHands[goes through this part][]
            for (int j = 0; j < 14; j++) {// sets array length to that of the equivalent array - playerClosedHands[][copies this part's length]
                playerClosedHands[d][j] = game.playerClosedHands[d][j];
            }
        }

        playerOpenHands = new int[4][16];
        for (int e = 0; e < 4; e++) {
            for (int j = 0; j < 16; j++) {
                playerOpenHands[e][j] = game.playerOpenHands[e][j];
            }
        }

        for (int g = 0; g < 4; g++) {
            sort(g);
        }
        deckPosition = game.deckPosition;
        gameStage = game.gameStage; // pretty obvious how this one works

    }


    /**
     * @param c the chow given
     *          changes this in response to the chow action previously checked and handled by MahJongLocalGame
     * @Collin_Yu
     */
    public void Chow(Chow c) {

        // Check legality
        if ((c.getTile1().isAbove(c.getTile2()) && c.getTile2().isAbove(c.getTile3())) ||
                (c.getTile2().isAbove(c.getTile1()) && c.getTile1().isAbove(c.getTile3())) ||
                (c.getTile1().isAbove(c.getTile3()) && c.getTile3().isAbove(c.getTile2())) ||
                (c.getTile2().isAbove(c.getTile3()) && c.getTile3().isAbove(c.getTile1())) ||
                (c.getTile3().isAbove(c.getTile1()) && c.getTile1().isAbove(c.getTile2())) ||
                (c.getTile3().isAbove(c.getTile2()) && c.getTile2().isAbove(c.getTile1()))) {

            // Set next open openhand slot to Tile 1
            for (int i = 0; i < playerOpenHands[c.getPlayerID()].length; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile1().getDeckPos(), i);
                    break;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < playerClosedHands[c.getPlayerID()].length; i++) {
                if (this.playerClosedHands[c.getPlayerID()][i] == c.getTile1().getDeckPos()) {
                    setPlayerClosedHandTile(c.getPlayerID(), 136, i);
                    break;
                }
            }


            // Set next open openhand slot to Tile 2
            for (int i = 0; i < playerOpenHands[c.getPlayerID()].length; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile2().getDeckPos(), i);
                    break;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < playerClosedHands[c.getPlayerID()].length; i++) {
                if (this.playerClosedHands[c.getPlayerID()][i] == c.getTile2().getDeckPos()) {
                    setPlayerClosedHandTile(c.getPlayerID(), 136, i);
                    break;
                }
            }


            // Set next open openhand slot to Tile 3
            for (int i = 0; i < playerOpenHands[c.getPlayerID()].length; i++) {
                if (this.playerOpenHands[c.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(c.getPlayerID(), c.getTile3().getDeckPos(), i);
                    break;
                }
            }

            // remove currDiscard
            currDiscard = null;

            // increment MahJongProgress accordingly
            playerMJProg[c.getPlayerID()]++;

            // it is now your turn to discard
            if (gameStage == 8) {
                gameStage = 1;
            } else {
                gameStage++;
            }

            // reset passingPlayers for the post-discard phase
            passingPlayers[0] = false;
            passingPlayers[1] = false;
            passingPlayers[2] = false;
            passingPlayers[3] = false;
        }
    }

    /**
     * @param p the Pong given
     *          changes this in response to the Pong action previously checked and handled by MahJongLocalGame
     * @Collin_Yu
     */
    public void Pong(Pong p) {
        if (p.getTile1().isEqualTo(p.getTile2()) && p.getTile2().isEqualTo(p.getTile3())) {
            // Set next open openhand slot to Tile 1
            for (int i = 0; i < playerOpenHands[p.getPlayerID()].length; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(p.getPlayerID(), p.getTile1().getDeckPos(), i);
                    break;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < playerClosedHands[p.getPlayerID()].length; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile1().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), 136, i);
                    break;
                }
            }


            // Set next open openhand slot to Tile 2
            for (int i = 0; i < playerOpenHands[p.getPlayerID()].length; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(p.getPlayerID(), p.getTile2().getDeckPos(), i);
                    break;
                }
            }

            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < playerClosedHands[p.getPlayerID()].length; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile2().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), 136, i);
                    break;
                }
            }


            // Set next open openhand slot to Tile 3
            for (int i = 0; i < playerOpenHands[p.getPlayerID()].length; i++) {
                if (this.playerOpenHands[p.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(p.getPlayerID(), p.getTile3().getDeckPos(), i);
                    break;
                }
            }


            // Find the tile in your closedhand, and remove it
            for (int i = 0; i < playerClosedHands[p.getPlayerID()].length; i++) {
                if (this.playerClosedHands[p.getPlayerID()][i] == p.getTile3().getDeckPos()) {
                    setPlayerClosedHandTile(p.getPlayerID(), 136, i);
                    break;
                }
            }
            // remove currDiscard
            currDiscard = null;

            playerMJProg[p.getPlayerID()]++;
            gameStage = (p.getPlayerID() + 1) * 2 - 1;
            passingPlayers[0] = false;
            passingPlayers[1] = false;
            passingPlayers[2] = false;
            passingPlayers[3] = false;
        }
    }

    /**
     * @param k the Kong given
     *          changes this in response to chow action action previously checked and handled by MahJongLocalGame
     * @Collin_Yu
     */
    public void Kong(Kong k) {
        // Check Legality
        if (k.getTile1().isEqualTo(k.getTile2()) && k.getTile2().isEqualTo(k.getTile3()) && k.getTile3().isEqualTo(k.getTile4())) {


            // Set 1st open slot to Tile 1
            for (int i = 0; i < playerOpenHands[k.getPlayerID()].length; i++) {
                if (this.playerOpenHands[k.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(k.getPlayerID(), k.getTile1().getDeckPos(), i);
                    break;
                }
            }
            // Find the tile in your hand, and remove it
            for (int i = 0; i < playerClosedHands[k.getPlayerID()].length; i++) {
                if (this.playerClosedHands[k.getPlayerID()][i] == k.getTile1().getDeckPos()) {
                    setPlayerClosedHandTile(k.getPlayerID(), 136, i);
                    break;
                }
            }
            // Set next open slot to Tile 2
            for (int i = 0; i < playerOpenHands[k.getPlayerID()].length; i++) {
                if (this.playerOpenHands[k.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(k.getPlayerID(), k.getTile2().getDeckPos(), i);
                    break;
                }
            }
            // Find the tile in your hand, and remove it
            for (int i = 0; i < playerClosedHands[k.getPlayerID()].length; i++) {
                if (this.playerClosedHands[k.getPlayerID()][i] == k.getTile2().getDeckPos()) {
                    break;
                }
            }

            // Set next open openhand slot to Tile 3
            for (int i = 0; i < playerOpenHands[k.getPlayerID()].length; i++) {
                if (this.playerOpenHands[k.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(k.getPlayerID(), k.getTile3().getDeckPos(), i);
                    break;
                }
            }
            // Find the tile in your hand, and remove it
            for (int i = 0; i < playerClosedHands[k.getPlayerID()].length; i++) {
                if (this.playerClosedHands[k.getPlayerID()][i] == k.getTile3().getDeckPos()) {
                    setPlayerClosedHandTile(k.getPlayerID(), 136, i);
                    break;
                }
            }
            // Set next open openhand slot to Tile 4
            for (int i = 0; i < playerOpenHands[k.getPlayerID()].length; i++) {
                if (this.playerOpenHands[k.getPlayerID()][i] == 136) {
                    setPlayerOpenHandTile(k.getPlayerID(), k.getTile4().getDeckPos(), i);
                    break;
                }
            }
            // Find the tile in your hand, and remove it
            for (int i = 0; i < playerClosedHands[k.getPlayerID()].length; i++) {
                if (this.playerClosedHands[k.getPlayerID()][i] == k.getTile4().getDeckPos()) {
                    setPlayerClosedHandTile(k.getPlayerID(), 136, i);
                    break;
                }
            }

            // remove currDiscard

            currDiscard = null;

            // increment MahJong progress accordingly
            playerMJProg[k.getPlayerID()]++;

            // draw as one is supposed to after a Kong
            draw(k.getPlayerID());

            // set gameStage to the discard phase of the player who Konged
            gameStage = (k.getPlayerID() + 1) * 2 - 1;

            // reset passingPlayers
            passingPlayers[0] = false;
            passingPlayers[1] = false;
            passingPlayers[2] = false;
            passingPlayers[3] = false;
        }
    }

    /**
     * @param d the Discard action given
     *          Removes the appropriate card from the hand and place it in the corresponding location: discardPile
     * @Collin_Yu
     */

    public void Discard(Discard d) {
        // Remove it from closedhand
        for (int i = 0; i < 14; i++) {
            if (this.playerClosedHands[d.getPlayerID()][i] == d.getTile().getDeckPos()) {
                this.setPlayerClosedHandTile(d.getPlayerID(), 136, i);

                break;
            }
        }

        // Set up the currDiscard for the post-discard phase
        currDiscard = new Tile(d.getTile());

        // increment gameStage accordingly
        if (gameStage < 8) {
            gameStage++;
        } else if (gameStage == 8) {
            gameStage = 1;
        }
    }

    /**
     * @param p the pass given
     *          changes this in response to chow action action previously checked and handled by MahJongLocalGame
     * @Collin_Yu
     */
    public void pass(Pass p) {

        // sets that the player who sent the Pass action has passed
        passingPlayers[p.getId()] = true;

        // if all have passed
        if (passingPlayers[0] && passingPlayers[1] && passingPlayers[2] && passingPlayers[3]) {
            // make sure currDiscard is not null
            // note that this should never happen, but catching it just in case
            if (currDiscard != null) {

                // loop through to find the first blank tile
                for (int i = 0; i < discardPile.length; i++) {
                    // upon encountering the first blank tile
                    if (discardPile[i] == 136) {

                        // replace it with the current discard
                        discardPile[i] = currDiscard.getDeckPos();

                        // reset currDiscard to null
                        currDiscard = null;

                        // if end of round, cycle over
                        if (gameStage == 8) {
                            draw(0);
                            gameStage = 1;
                        }

                        // otherwise continue normally
                        else {
                            draw(gameStage / 2);
                            gameStage++;
                        }

                        // reset passingPlayers
                        passingPlayers[0] = false;
                        passingPlayers[1] = false;
                        passingPlayers[2] = false;
                        passingPlayers[3] = false;

                        // break search loop
                        break;
                    }
                }
            }
        }

    }


    /**
     * @param pID the player whose hand needs to be sorted
     *            Sorts hand of the given player in the following manner
     *            - dots 1-9
     *            - bamboo 1-9
     *            - characters 1-9
     *            - dragon tiles
     *            - wind tiles
     * @Collin_Yu
     */
    public void sort(int pID) {
        int temp;

        // ID of the tiles was made for easy sorting
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

    /**
     * @param pID the player who will draw
     *            Replaces first blank tile in hand with the next spot in the deck reference, and increments accordingly
     * @Collin_Yu
     */
    public void draw(int pID) {
        // search hand
        if (deckPosition > 135) {
            outOfCards = true;
            return;
        }
        for (int i = 0; i < playerClosedHands[pID].length; i++) {

            if (deckPosition > 135) {
                outOfCards = true;
            }
            // if blank tile
            if (playerClosedHands[pID][i] == 136) {

                // set blank tile to next card in the deck
                playerClosedHands[pID][i] = this.deckPosition;

                // increment deckPosition accordingly
                this.deckPosition++;
                if (deckPosition > 135) {
                    outOfCards = true;
                }

                // automatically sort their hand
                sort(pID);

                // break search loop
                break;
            }
        }
    }

    public int[] removeNull(int[] initHand) {
        int[] hand = null;
        int counter = 0;
        boolean[] keep = new boolean[initHand.length];
        for (int m = 0; m < initHand.length; m++) {
            if (initHand[m] == 136) {
                keep[m] = false;
            } else {
                keep[m] = true;
                counter++;
            }
        }
        hand = new int[counter];
        int counter2 = 0;
        for (int n = 0; n < initHand.length; n++) {
            if (keep[n]) {
                hand[counter2] = initHand[n];
                counter2++;
            }
        }
        return hand;
    }

    public int[] addCurrDiscard(int[] initHand){
        int[] hand = initHand;
        for(int i = 0; i < hand.length; i++){
            if(hand[i] == 136){
                hand[i] = this.currDiscard.getDeckPos();
                break;
            }
        }
        return hand;
    }
    public boolean hasMahJong(int[] initHand, boolean firstTime)
    {
        /**
         * first time the method is called
         */

        int[] hand = initHand;
        if(firstTime) {
            if (gameStage % 2 == 0) {
                if(currDiscard == null){return false;}
                hand = addCurrDiscard(hand);
            }
            hand = removeNull(hand);
        }        int temp;

        // ID of the tiles was made for easy sorting
        for (int i = 0; i < hand.length - 1; i++) {
            for (int j = i + 1; j < hand.length; j++) {
                if (deck.get(hand[i]).getID() > deck.get(hand[j]).getID()) {
                    temp = hand[j];
                    hand[j] = hand[i];
                    hand[i] = temp;
                }
            }
        }
            //base case, two tiles must be a pair
            if (hand.length == 2) {
                return (deck.get(hand[0]).isEqualTo(deck.get(hand[1])));
            }


        //check for pongs
        for(int i = 0; i < hand.length-2; ++i)
        {
            if ( (deck.get(hand[i]).isEqualTo(deck.get(hand[i+1])))
                    && (deck.get(hand[i]).isEqualTo(deck.get(hand[i+2]))) )
            {
                int[] newHand = makeSubHand(hand, i, i+1, i+2);
                if (hasMahJong(newHand, false)) return true;
            }
        }

        //check for chows
        for(int i = 0; i < hand.length-2; ++i)
        {

            int second = getIndexOfAbove(hand, i);
            if (second != -1)
            {
                int third = getIndexOfAbove(hand, second);
                if (third != -1)
                {
                    int[] newHand = makeSubHand(hand, i, second, third);
                    if (hasMahJong(newHand, false)) return true;
                }
            }
        }

        return false;
    }//isMahjong

}

