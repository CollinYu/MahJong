package edu.up.yu18.mahjong.game;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Tile {
    private int suit;
    private int val;
    private int ID;

    // Constructor
    public Tile(char s, int v, int ego){
        this.suit = s;
        this.val = v;
        this.ID = ego;
    }

    // Checks if this tile is "above" another tile
    public boolean isAbove(Tile t){
        if (this.suit == t.suit){
            if(this.val - t.val == 1){
                return true;
            }
        }
        return false;
    }

    // Checks if this tile is "below" another tile
    public boolean isBelow(Tile t){
        if (this.suit == t.suit){
            if(this.val - t.val == -1){
                return true;
            }
        }
        return false;
    }

    // Checks if this tile is the same as another tile
    public boolean isEqualto(Tile t){
        if (this.suit == t.suit){
            if(this.val == t.val){
                return true;
            }
        }
        return false;
    }
    // Gets tileID
    public int getID(){return this.ID;}
}
