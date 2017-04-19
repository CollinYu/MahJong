package edu.up.yu18.mahjong.game.MahJong.game;

import edu.up.yu18.mahjong.game.MahJong.Actions.Chow;
import edu.up.yu18.mahjong.game.MahJong.Actions.Discard;
import edu.up.yu18.mahjong.game.MahJong.Actions.Kong;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pass;
import edu.up.yu18.mahjong.game.MahJong.Actions.Pong;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.GameAction;
import edu.up.yu18.mahjong.game.frameWork.base.actionMessage.TimerAction;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.LocalGame;
import edu.up.yu18.mahjong.game.frameWork.base.util.GameTimer;

/**
 * Created by CollinYu on 4/12/17.
 */

public class MahJongLocalGame extends LocalGame {

    private MahJongGameState state;
    private GameTimer timer;

    public MahJongLocalGame(){
        this.state = new MahJongGameState();
        this.timer = new GameTimer(this);
        this.timer.setInterval(1000);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new MahJongGameState(state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if (state.getGameStage() % 2 == 0){return true;}
        if (state.getGameStage() == (playerIdx+1)*2 -1){return true;}
        else{return false;}
    }

    @Override
    protected String checkIfGameOver() {
        for(int i = 0; i < 4; i++){
            if(state.hasMahJong(i)){
                String victoryMsg = "Player " + i+1 + " has won the game!";
                return victoryMsg;
            }
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action){
        if (action instanceof Chow){
            Chow c = (Chow) action;
            state.Chow(c);
            return true;
        }
        if (action instanceof Pong){
            Pong p = (Pong) action;
            state.Pong(p);
            return true;
        }
        if (action instanceof Kong){
            Kong k = (Kong) action;
            state.Kong(k);
            return true;
        }
        if (action instanceof Discard){
            Discard d = (Discard) action;
            state.Discard(d);
            return true;
        }
        if (action instanceof Pass){
            Pass p = (Pass) action;
            state.pass(p);
            return true;
        }

        return false;
    }
}
