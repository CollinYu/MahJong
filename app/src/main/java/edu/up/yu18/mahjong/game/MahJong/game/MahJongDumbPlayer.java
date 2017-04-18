package edu.up.yu18.mahjong.game.MahJong.game;

import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;

/**
 * Created by CollinYu on 4/12/17.
 */

public class MahJongDumbPlayer extends GameComputerPlayer {

    private int myID;
    public MahJongDumbPlayer(String name){
        super(name);
    }
    @Override
    protected void receiveInfo(GameInfo info) {

    }
}
