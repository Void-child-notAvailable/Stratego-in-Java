package Model;

import java.util.Objects;

public abstract class playerAbs {
    protected int rescuesLeft;
    protected boolean turn;
    protected player P2 = null;
    protected final String name;

    /**
     * Constructor:
     * Creates an instance of a player class,
     * Initialises the amount of rescues allowed and the turn is set to false
     * @param name Is the player's name, if null defaults to Player2
     * @param rescuesLeft is the allowed rescues for the player class
     */
    public playerAbs(int rescuesLeft,String name){
        this.rescuesLeft=rescuesLeft;
        turn=false;
        this.name = Objects.requireNonNullElse(name, "Player2");
    }

    /**
     * Accessor:
     * Returns the name of the player class
     * @return String that contains the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor:
     * Returns the rescues left by the player class
     * @return int that contains the player's rescues left
     */
    public int getRescues(){
        return rescuesLeft;
    }

    /**
     * Transformer:
     * Reduce the rescues left by the player class by 1
     * @pre rescues must be >0
     */
    public void rescueDone(){
        this.rescuesLeft=this.rescuesLeft-1;
    }

    /**
     * Accessor:
     * Returns a boolean value of the turn
     * @return True if it's the players turn, else false
     * */
    public boolean getTurn(){
        return turn;
    }

    /**
     * Transformer:
     * Sets the player's turn to true or false
     * @param turn a boolean value of the player's turn
     */
    public void setTurn(boolean turn){
        this.turn=turn;
    }


    /**
     * Transformer:
     * Initialises the other player
     * @param p2 an instance of a player class
     */
    public void setP2(player p2) {
        P2 = p2;
    }

    /**
     * Accessor:
     * Returns a player value of the other player
     * @return Player class copy of other player
     * */
    public player getP2() {
        return P2;
    }
}
