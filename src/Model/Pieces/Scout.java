package Model.Pieces;

import Model.player;

import java.util.Objects;

public class Scout extends piece{

    /**
     * Constructor:
     * Creates an instance of a Scout piece class with power of 2 and name Scout
     * @param p is the player in control of this piece
     */
    public Scout(player p) {
        super(2, p, "Scout");
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

    /**
     * Accessor:
     * Returns the allowed movement of this piece, default value is 1
     *      The scout has infinite movement in the 4 cardinal directions, it cannot pass through obstructed or nonempty squares
     * @return An int of the allowed movement amount
     */
    @Override
    public int allowedMovement(){
        return 100;
    }

    @Override
    public boolean getRescue(){
        return true;
    }
}
