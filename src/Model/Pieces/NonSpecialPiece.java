package Model.Pieces;

import Model.player;

import java.util.Objects;

public class NonSpecialPiece extends piece{

    /**
     * Constructor:
     * Creates an instance of a piece class
     * @param power is the power of the piece, higher power makes a stronger piece
     * @param p is the player in control of this piece
     * @param name is the name of the piece
     */
    public NonSpecialPiece(int power, player p,String name) {
        super(power,p,name);
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     * @param p Another instance of a piece
     * @return The result of a fight, w if the fight ends in a win of this piece, l for loss, d for draw and end for a flag capture
     * @pre p must not be null
     */
    @Override
    public String combat(piece p) {
        if (Objects.equals(p.getName(), "Flag")){
            return "end";
        }
        if(p.power>this.power){
            return "l";
        }else if(p.power<this.power){
            return "w";
        }else{
            return "d";
        }
    }


}
