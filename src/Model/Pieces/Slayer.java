package Model.Pieces;
import Model.player;
import java.util.Objects;

public class Slayer extends piece{

    /**
     * Constructor:
     * Creates an instance of a slayer piece class with power of 1 and name Slayer
     * @param p is the player in control of this piece
     */
    public Slayer(player p) {
        super( 1, p, "Slayer");
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     *      The slayer class has the special ability to destroy a dragon, provided it is the one attacking
     * @param p Another instance of a piece
     * @return The result of a fight, w if the fight ends in a win of this piece, l for loss, d for draw and end for a flag capture
     * @pre p must not be null
     */
    public String combat(piece p){
        if (Objects.equals(p.getName(), "Flag")){
            return "end";
        }
        if (Objects.equals(p.getName(), "Dragon")){
            return "w";
        }else if(p.power>this.power){
            return "l";
        }else{
            return "d";
        }
    }
}
