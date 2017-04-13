package edu.up.yu18.mahjong.game.MahJong.Actions;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;

/**
 * Created by CollinYu on 3/9/17.
 * This game action takes 3 tiles, and the player that played them,
 * other game actions are of this form, and inherit from it
 */
public class Pong extends GameAction {
    private int playerID;
    private Tile tile1;
    private Tile tile2;
    private Tile tile3;

    // Constructor
    public Pong(GamePlayer player, int pID, Tile t1, Tile t2, Tile t3){
        super(player);
        this.playerID = pID;
        this.tile1 = t1;
        this.tile2 = t2;
        this.tile3 = t3;
    }
    // Getter methods for the instance variables
    /// Does not require setters
    public int getPlayerID(){return this.playerID;}
    public Tile getTile1(){return this.tile1;}
    public Tile getTile2(){return this.tile2;}
    public Tile getTile3(){return this.tile3;}
}
