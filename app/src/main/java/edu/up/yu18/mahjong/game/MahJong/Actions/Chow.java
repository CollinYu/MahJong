package edu.up.yu18.mahjong.game.MahJong.Actions;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Chow extends Pong {

    private static final long serialVersionUID = 132306423153136954L;
    // Constructor
    public Chow(GamePlayer player, int pID, Tile t1, Tile t2, Tile t3){
        super(player, pID, t1,t2,t3);
    }

}
