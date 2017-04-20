package edu.up.yu18.mahjong.game.MahJong.Objects;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by boylan19 on 3/8/2017.
 */
public class Deck {

    private ArrayList<Tile> deckTiles;

    public Deck(){
        deckTiles = new ArrayList<Tile>();

        deckTiles.add(new Tile(0,0,0,0));
        deckTiles.add(new Tile(0,0,1,0));
        deckTiles.add(new Tile(0,0,2,0));
        deckTiles.add(new Tile(0,0,3,0));
        deckTiles.add(new Tile(0,1,4,0));
        deckTiles.add(new Tile(0,1,5,0));
        deckTiles.add(new Tile(0,1,6,0));
        deckTiles.add(new Tile(0,1,7,0));
        deckTiles.add(new Tile(0,2,8,0));
        deckTiles.add(new Tile(0,2,9,0));
        deckTiles.add(new Tile(0,2,10,0));
        deckTiles.add(new Tile(0,2,11,0));
        deckTiles.add(new Tile(0,3,12,0));
        deckTiles.add(new Tile(0,3,13,0));
        deckTiles.add(new Tile(0,3,14,0));
        deckTiles.add(new Tile(0,3,15,0));
        deckTiles.add(new Tile(0,4,16,0));
        deckTiles.add(new Tile(0,4,17,0));
        deckTiles.add(new Tile(0,4,18,0));
        deckTiles.add(new Tile(0,4,19,0));
        deckTiles.add(new Tile(0,5,20,0));
        deckTiles.add(new Tile(0,5,21,0));
        deckTiles.add(new Tile(0,5,22,0));
        deckTiles.add(new Tile(0,5,23,0));
        deckTiles.add(new Tile(0,6,24,0));
        deckTiles.add(new Tile(0,6,25,0));
        deckTiles.add(new Tile(0,6,26,0));
        deckTiles.add(new Tile(0,6,27,0));
        deckTiles.add(new Tile(0,7,28,0));
        deckTiles.add(new Tile(0,7,29,0));
        deckTiles.add(new Tile(0,7,30,0));
        deckTiles.add(new Tile(0,7,31,0));
        deckTiles.add(new Tile(0,8,32,0));
        deckTiles.add(new Tile(0,8,33,0));
        deckTiles.add(new Tile(0,8,34,0));
        deckTiles.add(new Tile(0,8,35,0));
        deckTiles.add(new Tile(1,0,36,0));
        deckTiles.add(new Tile(1,0,37,0));
        deckTiles.add(new Tile(1,0,38,0));
        deckTiles.add(new Tile(1,0,39,0));
        deckTiles.add(new Tile(1,1,40,0));
        deckTiles.add(new Tile(1,1,41,0));
        deckTiles.add(new Tile(1,1,42,0));
        deckTiles.add(new Tile(1,1,43,0));
        deckTiles.add(new Tile(1,2,44,0));
        deckTiles.add(new Tile(1,2,45,0));
        deckTiles.add(new Tile(1,2,46,0));
        deckTiles.add(new Tile(1,2,47,0));
        deckTiles.add(new Tile(1,3,48,0));
        deckTiles.add(new Tile(1,3,49,0));
        deckTiles.add(new Tile(1,3,50,0));
        deckTiles.add(new Tile(1,3,51,0));
        deckTiles.add(new Tile(1,4,52,0));
        deckTiles.add(new Tile(1,4,53,0));
        deckTiles.add(new Tile(1,4,54,0));
        deckTiles.add(new Tile(1,4,55,0));
        deckTiles.add(new Tile(1,5,56,0));
        deckTiles.add(new Tile(1,5,57,0));
        deckTiles.add(new Tile(1,5,58,0));
        deckTiles.add(new Tile(1,5,59,0));
        deckTiles.add(new Tile(1,6,60,0));
        deckTiles.add(new Tile(1,6,61,0));
        deckTiles.add(new Tile(1,6,62,0));
        deckTiles.add(new Tile(1,6,63,0));
        deckTiles.add(new Tile(1,7,64,0));
        deckTiles.add(new Tile(1,7,65,0));
        deckTiles.add(new Tile(1,7,66,0));
        deckTiles.add(new Tile(1,7,67,0));
        deckTiles.add(new Tile(1,8,68,0));
        deckTiles.add(new Tile(1,8,69,0));
        deckTiles.add(new Tile(1,8,70,0));
        deckTiles.add(new Tile(1,8,71,0));
        deckTiles.add(new Tile(2,0,72,0));
        deckTiles.add(new Tile(2,0,73,0));
        deckTiles.add(new Tile(2,0,74,0));
        deckTiles.add(new Tile(2,0,75,0));
        deckTiles.add(new Tile(2,1,76,0));
        deckTiles.add(new Tile(2,1,77,0));
        deckTiles.add(new Tile(2,1,78,0));
        deckTiles.add(new Tile(2,1,79,0));
        deckTiles.add(new Tile(2,2,80,0));
        deckTiles.add(new Tile(2,2,81,0));
        deckTiles.add(new Tile(2,2,82,0));
        deckTiles.add(new Tile(2,2,83,0));
        deckTiles.add(new Tile(2,3,84,0));
        deckTiles.add(new Tile(2,3,85,0));
        deckTiles.add(new Tile(2,3,86,0));
        deckTiles.add(new Tile(2,3,87,0));
        deckTiles.add(new Tile(2,4,88,0));
        deckTiles.add(new Tile(2,4,89,0));
        deckTiles.add(new Tile(2,4,90,0));
        deckTiles.add(new Tile(2,4,91,0));
        deckTiles.add(new Tile(2,5,92,0));
        deckTiles.add(new Tile(2,5,93,0));
        deckTiles.add(new Tile(2,5,94,0));
        deckTiles.add(new Tile(2,5,95,0));
        deckTiles.add(new Tile(2,6,96,0));
        deckTiles.add(new Tile(2,6,97,0));
        deckTiles.add(new Tile(2,6,98,0));
        deckTiles.add(new Tile(2,6,99,0));
        deckTiles.add(new Tile(2,7,100,0));
        deckTiles.add(new Tile(2,7,101,0));
        deckTiles.add(new Tile(2,7,102,0));
        deckTiles.add(new Tile(2,7,103,0));
        deckTiles.add(new Tile(2,8,104,0));
        deckTiles.add(new Tile(2,8,105,0));
        deckTiles.add(new Tile(2,8,106,0));
        deckTiles.add(new Tile(2,8,107,0));
        deckTiles.add(new Tile(3,0,108,0));
        deckTiles.add(new Tile(3,0,109,0));
        deckTiles.add(new Tile(3,0,110,0));
        deckTiles.add(new Tile(3,0,111,0));
        deckTiles.add(new Tile(3,1,112,0));
        deckTiles.add(new Tile(3,1,113,0));
        deckTiles.add(new Tile(3,1,114,0));
        deckTiles.add(new Tile(3,1,115,0));
        deckTiles.add(new Tile(3,2,116,0));
        deckTiles.add(new Tile(3,2,117,0));
        deckTiles.add(new Tile(3,2,118,0));
        deckTiles.add(new Tile(3,2,119,0));
        deckTiles.add(new Tile(4,0,120,0));
        deckTiles.add(new Tile(4,0,121,0));
        deckTiles.add(new Tile(4,0,122,0));
        deckTiles.add(new Tile(4,0,123,0));
        deckTiles.add(new Tile(4,1,124,0));
        deckTiles.add(new Tile(4,1,125,0));
        deckTiles.add(new Tile(4,1,126,0));
        deckTiles.add(new Tile(4,1,127,0));
        deckTiles.add(new Tile(4,2,128,0));
        deckTiles.add(new Tile(4,2,129,0));
        deckTiles.add(new Tile(4,2,130,0));
        deckTiles.add(new Tile(4,2,131,0));
        deckTiles.add(new Tile(4,3,132,0));
        deckTiles.add(new Tile(4,3,133,0));
        deckTiles.add(new Tile(4,3,134,0));
        deckTiles.add(new Tile(4,3,135,0));

        
        
        Collections.shuffle(deckTiles);
        deckTiles.add(new Tile(-1,-1,-1,-1));
        for(int p = 0; p < deckTiles.size(); p++){
            deckTiles.get(p).setDeckPos(p);
        }

    }
    public ArrayList<Tile> getDeckTiles(){
        return this.deckTiles;
    }
}
