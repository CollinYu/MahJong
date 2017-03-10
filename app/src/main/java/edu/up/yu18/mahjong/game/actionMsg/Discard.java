package edu.up.yu18.mahjong.game.actionMsg;

import edu.up.yu18.mahjong.game.GamePlayer;
import edu.up.yu18.mahjong.game.Tile;

/**
 * Created by CollinYu on 3/9/17.
 */


public class Discard extends GameAction{
    // Instance Variables
    private Tile tile;
    private int playerID;

    // Constructor
    public Discard(GamePlayer player, int pID, Tile t){
        super(player);
        this.tile = t;
        this.playerID = pID;
    }

    // Getter methods
    public int getPlayerID(){return this.playerID;}
    public Tile getTile(){return this.tile;}

}
