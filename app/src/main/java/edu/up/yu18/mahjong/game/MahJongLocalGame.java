package edu.up.yu18.mahjong.game;

import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;

/**
 * Created by CollinYu on 4/12/17.
 */

public class MahJongLocalGame extends LocalGame{
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerIdx) {
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        return false;
    }
}
