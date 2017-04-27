package edu.up.yu18.mahjong.game.MahJong.Actions;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;

/**
 * Created by CollinYu on 4/18/17.
 */

public class Pass extends GameAction {
    int id;
    private static final long serialVersionUID = 8791286423454L;

    public Pass(GamePlayer gp, int pID){
        super(gp);
        id = pID;
    }

    public int getId(){return this.id;}
}
