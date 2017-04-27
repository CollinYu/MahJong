package edu.up.yu18.mahjong.game.MahJong.game;

import java.util.ArrayList;

import edu.up.yu18.mahjong.game.frameWork.base.config.GameConfig;
import edu.up.yu18.mahjong.game.frameWork.base.config.GamePlayerType;
import edu.up.yu18.mahjong.game.frameWork.base.game.GameMainActivity;
import edu.up.yu18.mahjong.game.frameWork.base.game.GamePlayer;
import edu.up.yu18.mahjong.game.frameWork.base.game.LocalGame;

/**
 * @Steve_Vegdahl
 * @Andrew_Nuxoll
 * @Collin_Yu
 */

public class MahJongMainActivity extends GameMainActivity{

        // the port number that this game will use when playing over the network
        private static final int PORT_NUMBER = 2234;

        /**
         * Create the default configuration for this game:
         * - one human player vs. 3 computer players
         * - minimum of 4 player, maximum of 4
         * - one kind of computer player and one kind of human player available
         *
         * @return
         * 		the new configuration object, representing the default configuration
         */
        @Override
        public GameConfig createDefaultConfig() {

            // Define the allowed player types
            ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

            // a human player player type (player type 0)
            playerTypes.add(new GamePlayerType("Local Human Player") {
                public GamePlayer createPlayer(String name) {
                    return new MahJongHumanPlayer(name);
                }});

            // a computer player type (player type 1)
            playerTypes.add(new GamePlayerType("Computer Player (Dumb)") {
                public GamePlayer createPlayer(String name) {
                    return new MahJongDumbPlayer(name);
                }});

            // a computer player type (player type 2)
            playerTypes.add(new GamePlayerType("Computer Player (Smart)") {
                public GamePlayer createPlayer(String name) {
                    return new MahJongSmartPlayer(name);
                }});

            // Create a game configuration class for MahJong:
            // - player types as given above
            // - from 1 to 2 players
            // - name of game is "MahJong Game"
            // - port number as defined above
            GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "MahJong Game",
                    PORT_NUMBER);

            // Add the default players to the configuration
            defaultConfig.addPlayer("Human", 0); // player 1: a human player
            defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
            defaultConfig.addPlayer("Computer", 1); // player 3: a computer player
            defaultConfig.addPlayer("Computer", 1); // player 4: a computer player

            // Set the default remote-player setup:
            // - player name: "Remote Player"
            // - IP code: (empty string)
            // - default player type: human player
            defaultConfig.setRemoteData("Remote Player", "", 0);

            // return the configuration
            return defaultConfig;
        }//createDefaultConfig

        /**
         * create a local game
         *
         * @return
         * 		the local game, a MahJong game
         */
        @Override
        public LocalGame createLocalGame() {
            return new MahJongLocalGame();
        }

        public boolean setGameOver(){return false;}
    }




