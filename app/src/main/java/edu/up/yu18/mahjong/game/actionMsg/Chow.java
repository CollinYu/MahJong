package edu.up.yu18.mahjong.game.actionMsg;
import edu.up.yu18.mahjong.game.GamePlayer;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Chow extends GameAction{
    int tile1;
    int tile2;
    int tile3;
    public Chow(GamePlayer player, int t1, int t2, int t3){
        super(player);
        this.tile1 = t1;
        this.tile2 = t2;
        this.tile3 = t3;
    }
}
