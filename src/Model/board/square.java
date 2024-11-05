package Model.board;
import Model.Pieces.piece;

public class square{

    private boolean obstructed;
    private piece piece;

    /**
     * Constructor:
     * Creates an instance of a square class
     * A new instance is empty, not obstructed and has no piece attached
     */
    public square(){
        obstructed=false;
        piece=null;
    }

    /**
     * Accessor:
     * Returns true if the piece is obstructed and cannot be accessed further
     * @return a boolean value of the obstructed variable
     */
    public boolean isObstructed() {
        return obstructed;
    }


    /**
     * Transformer:
     * Sets the piece as obstructed, meaning movement on or through it is not allowed
     */
    public void setObstructed() {
        this.obstructed = true;
    }

    /**
     * Accessor:
     * Returns an instance of the piece contained in the square
     * @return A piece class or null if the square does not contain a piece
     */
    public piece getPiece() {
        return this.piece;
    }

    /**
     * Transformer:
     * Sets the piece contained in the square
     * @param p is the piece that will be contained in the square,null if a piece is removed
     */
    public void setPiece(piece p) {
        this.piece = p;
    }

}