package edu.up.yu18.mahjong.game.MahJong.Actions;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;

/**
 * Created by CollinYu on 4/19/17.
 */

public class Sort extends GameAction {
    int id;
    private static final long serialVersionUID = 20968112323454L;

    public Sort(GamePlayer gp, int pID){
        super(gp);
        id = pID;
    }
    public int getId(){return this.id;}
}
