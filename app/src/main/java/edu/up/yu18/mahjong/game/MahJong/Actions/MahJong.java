package edu.up.yu18.mahjong.game.MahJong.Actions;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;

/**
 * Created by CollinYu on 4/25/17.
 */

public class MahJong extends GameAction {
    private int pID;
    private static final long serialVersionUID = 1322128667123454L;

    public MahJong(GamePlayer gp, int pID){
        super(gp);
        this.pID = pID;
    }

    public int getPlayerID(){
        return this.pID;
    }
}
