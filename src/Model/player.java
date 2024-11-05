package Model;


import Model.Pieces.piece;
import java.util.ArrayList;

public class player extends playerAbs{
    private final ArrayList<piece> defeatedPieces;
    private int totalAttacks;
    private int attacksWon;

    /**
     * Constructor:
     * Creates an instance of a player class,
     * Initialises the amount of rescues allowed and the turn is set to false
     * @param name Is the player's name, if null defaults to Player2
     */
    public player(String name){
        super(2,name);
        this.totalAttacks=0;
        this.attacksWon=0;
        this.defeatedPieces= new ArrayList<>();
    }

    /**
     * Transformer:
     * Adds defeated pieces to an array for rescue
     * @param p Piece variable to be added to the array
     */
    public void defeatedPiece(piece p){
        defeatedPieces.add(p);
    }

    /**
     * Observer:
     * Returns all defeated pieces, if no pieces have perished, returns null
     * @return An arrayList of pieces, or null
     */
    public ArrayList<piece> getDefeatedPieces() {
        return defeatedPieces.size()>0?defeatedPieces:null;
    }

    public void addAttacksWon() {
        this.attacksWon = attacksWon+1;
    }

    public void addAttacks() {
        this.totalAttacks = totalAttacks+1;
    }

    public int getWinPercentage(){
        if(totalAttacks==0){return 100;}
        else{
            return (attacksWon*100/totalAttacks);
        }
    }
}