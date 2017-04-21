package edu.up.yu18.mahjong.game.MahJong.game;

import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;

/**
 * @Collin_Yu
 * Smart Computer Player that uses tile counting to determine the best discard
 * Creates a parallel int[] that it uses to value the tiles in its hand based on:
 *          - If it can be used with other cards in its hand, value increases
 *          - If there are inaccessible discarded cards that have potential combinations with it, value decreases
 * Always Pongs, Kongs and Chows
 */

public class MahJongSmartPlayer extends GameComputerPlayer {

    public MahJongSmartPlayer(String name){super(name);}

    @Override
    protected void receiveInfo(GameInfo info) {

    }
}
