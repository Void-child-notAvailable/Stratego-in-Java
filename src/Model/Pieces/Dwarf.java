package Model.Pieces;
import Model.player;
import java.util.Objects;

public class Dwarf extends piece{

    /**
     * Constructor:
     * Creates an instance of a Dwarf piece class with power of 3 and name Dwarf
     * @param p is the player in control of this piece
     */
    public Dwarf(player p) {
        super( 3, p, "Dwarf");
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     *      The dwarf class has the special ability to disarm a trap
     * @param p Another instance of a piece
     * @return The result of a fight, w if the fight ends in a win of this piece, l for loss, d for draw and end for a flag capture
     * @pre p must not be null
     */
    public String combat(piece p){
        if (Objects.equals(p.getName(), "Flag")){
            return "end";
        }
        if (Objects.equals(p.getName(), "Trap")){
            return "w";
        }
        else if(p.power>this.power){
            return "l";
        }else if(p.power<this.power){
            return "w";
        }else{
            return "d";
        }
    }
}
