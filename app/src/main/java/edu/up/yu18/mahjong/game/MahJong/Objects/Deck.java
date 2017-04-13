package edu.up.yu18.mahjong.game.MahJong.Objects;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by boylan19 on 3/8/2017.
 */
public class Deck {

    private ArrayList<Tile> deckTiles;

    private Deck(){
        deckTiles = new ArrayList<Tile>();
        int i = 0;
        for(int j = 0; j < 4; j++){
            for(int k = 0; k < 3; k++){
                for(int l = 0; l < 9; l++) {
                    deckTiles.add(new Tile(k,l,i));
                    i++;
                }//l
            }//k
        }//j
        for (int j = 0; j < 4; j++) {
            deckTiles.add(new Tile(3, j, i));
            i++;//4 is dragon
        }
        for (int j = 0; j < 4; j++) {
            deckTiles.add(new Tile(4, 1, i));
            i++;//5 is wind
        }

        Collections.shuffle(deckTiles);

    }
}
