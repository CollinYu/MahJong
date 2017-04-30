package edu.up.yu18.mahjong.game.MahJong.game;

import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;
import java.lang.Math;
import java.util.Random;

/**
 * @Collin_Yu
 * Computer AI (if intelligence is really an applicable word here) that really really sucks at MahJong
 * Performs the bare minimum for a MahJong player:
 * - Discards on its turn
 * - Passes when it needs to
 */

public class MahJongDumbPlayer extends GameComputerPlayer {

    private MahJongGameState state;

    private static final long serialVersionUID = 130987464333136954L;

    public MahJongDumbPlayer(String name){
        super(name);
    }
    @Override

    // Only receives MahJongGameState type GameInfos
    protected void receiveInfo(GameInfo info) {

        // make sure it really is a MahJongGameState
        if(info instanceof MahJongGameState){
            state = new MahJongGameState((MahJongGameState) info);
        }

        // if it's this' turn, discards the first card in its hand
        if(state.getGameStage() == (this.playerNum+1)*2-1){
            long waitTime = 1500+ (long)(Math.random()*1000);
            try{Thread.sleep(waitTime);}catch(InterruptedException ie){}
            double d = (double) state.getPlayerClosedHandLength(this.playerNum) - 1;
            double r = Math.random();
            double q = r*d;
            int h = (int) q;

            Discard disc = new Discard(this, playerNum, state.getPlayerClosedHandTile(playerNum,h));
            game.sendAction(disc);
        }

        // if it's a post-discard phase, just passes
        else if (state.getGameStage() % 2 == 0 && !state.hasPassed(this.playerNum)){
            Pass p = new Pass(this, this.playerNum);
            game.sendAction(p);
        }
    }
}
