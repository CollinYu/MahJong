package edu.up.yu18.mahjong.game.MahJong.Objects;

/**
 * Created by CollinYu on 3/9/17.
 */

public class Tile {
    private int suit;
    private int val;
    private int ID;
    private int deckPos;
    // Constructor
    public Tile(int s, int v, int ego, int deckPos){
        this.suit = s;
        this.val = v;
        this.ID = ego;
        this.deckPos = deckPos;
    }

    // Checks if this tile is "above" another tile
    public boolean isAbove(Tile t){
        if(this.suit < 3) {
            if (this.suit == t.suit) {
                if (this.val - t.val == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if this tile is "below" another tile
    public boolean isBelow(Tile t){
        if(this.suit < 3) {
            if (this.suit == t.suit) {
                if (this.val - t.val == -1) {
                    return true;
                }
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

    // Tile Deep Copy

    public Tile (Tile t){
        this.suit = t.suit;
        this.val = t.val;
        this.ID = t.ID;
        this.deckPos = t.deckPos;
    }
    // Gets tileID
    public int getID(){return this.ID;}
    public int getSuit(){return this.suit;}
    public int getVal(){return this.val;}
    public int getDeckPos(){return this.deckPos;}

    public void setDeckPos(int dP){this.deckPos = dP;}
    public void setID(int id){this.ID = id;}

}
