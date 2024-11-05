package Model.Pieces;
import Model.player;

public class Trap extends piece{

    /**
     * Constructor:
     * Creates an instance of a Trap piece class with power of 0 and name Trap
     * @param p is the player in control of this piece
     */
    public Trap(player p) {
        super( 300, p, "Trap");
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     * A trap cannot enter combat so this is not used
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
    public int allowedMovement(){
        return 0;
    }

    /**
     * Accessor:
     * Returns the visible power of the piece
     * @return An int, indicative of the power of a piece
     */
    @Override
    public int getPower(){
        return 0;
    }
}
