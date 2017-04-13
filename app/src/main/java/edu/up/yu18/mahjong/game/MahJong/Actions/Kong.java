package edu.up.yu18.mahjong.game.MahJong.Actions;
import edu.up.yu18.mahjong.game.MahJong.Objects.Tile;

import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Kong extends Pong {
    private Tile tile4;

    public Kong(GamePlayer player, int pID, Tile t1, Tile t2, Tile t3, Tile t4){
        super(player, pID, t1,t2,t3);
        tile4 = t4;
    }

    public Tile getTile4(){return this.tile4;}
}
