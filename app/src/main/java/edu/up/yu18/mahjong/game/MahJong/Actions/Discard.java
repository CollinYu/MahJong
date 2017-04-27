package edu.up.yu18.mahjong.game.MahJong.Actions;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;

/**
 * Created by CollinYu on 3/9/17.
 */


public class Discard extends GameAction {


    private static final long serialVersionUID = 132127523136954L;
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
