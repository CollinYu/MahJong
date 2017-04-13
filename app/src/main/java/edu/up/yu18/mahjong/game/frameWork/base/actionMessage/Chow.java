package edu.up.yu18.mahjong.game.frameWork.base.actionMessage;
import edu.up.yu18.mahjong.game.GamePlayer;
import edu.up.yu18.mahjong.game.Tile;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Chow extends Pong{
    // Constructor
    public Chow(GamePlayer player, int pID, Tile t1, Tile t2, Tile t3){
        super(player, pID, t1,t2,t3);
    }

}
