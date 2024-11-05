package Model.Pieces;

import Model.player;

public class Flag extends piece{

    /**
     * Constructor:
     * Creates an instance of a Flag piece class with power of 0 and name Flag
     * @param p is the player in control of this piece
     */
    public Flag(player p) {
        super(0, p, "Flag");
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     * A flag cannot enter combat so this is not used
     */
    @Override
    public String combat(piece p) {
        return null;
    }

    /**
     * Accessor:
     * Returns the allowed movement of this piece, default value is 1
     * @return An int of the allowed movement amount
     */
    @Override
    public int allowedMovement(){return 0;}
}
