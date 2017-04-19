package edu.up.yu18.mahjong.game.MahJong.game;

import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameComputerPlayer;
import edu.up.yu18.mahjong.game.frameWork.base.infoMsg.GameInfo;
import java.lang.Math;
import java.util.Random;

/**
 * Created by CollinYu on 4/12/17.
 */

public class MahJongDumbPlayer extends GameComputerPlayer {

    private MahJongGameState state;

    public MahJongDumbPlayer(String name){
        super(name);
    }
    @Override
    protected void receiveInfo(GameInfo info) {
        if(info instanceof MahJongGameState){
            state = new MahJongGameState((MahJongGameState) info);
        }
        if(state.getGameStage() == (this.playerNum+1)*2-1){
            Discard disc = new Discard(this, playerNum, state.getPlayerOpenHandTile(playerNum,0));
            game.sendAction(disc);
        }
        else if (state.getGameStage() % 2 == 0){
            Pass p = new Pass(this, this.playerNum);
            game.sendAction(p);
        }
    }
}
