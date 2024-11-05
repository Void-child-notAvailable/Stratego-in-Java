package Model.Pieces;

import Model.player;

import javax.swing.*;

public abstract class piece{
    protected final int power;
    private final player p;
    private final String name;
    private boolean rescue=false;
    private ImageIcon i;

    /**
     * Constructor:
     * Creates an instance of a piece class
     * @param power is the power of the piece, higher power makes a stronger piece
     * @param p is the player in control of this piece
     * @param name is the name of the piece
     */
    public piece(int power,player p,String name) {
        this.power=power;
        this.p=p;
        this.name=name;
    }

    /**
     * Accessor:
     * Returns the visible power of the piece
     * @return An int, indicative of the power of a piece
     */
    public int getPower() {
        return power;
    }

    /**
     * Accessor:
     * Returns the player in control of the piece
     * @return An instance of a player class
     */
    public player getPl() {
        return p;
    }

    /**
     * Accessor:
     * Returns the name of the piece
     * @return A string containing the name of the piece
     */
    public String getName() {
        return name;
    }

    /**
     * Observer:
     * Returns a String based on the result of combat between this piece, and a given other piece
     * @param p Another instance of a piece
     * @return The result of a fight, w if the fight ends in a win of this piece, l for loss, d for draw and end for a flag capture
     * @pre p must not be null
     */
    public abstract String combat(piece p);

    /**
     * Accessor:
     * Returns the allowed movement of this piece, default value is 1
     * @return An int of the allowed movement amount
     */
    public int allowedMovement(){return 1;}

    /**
     * Transformer:
     * After a rescue, a piece cannot rescue another, so the rescue parameter is set to true
     */
    public void setRescue() {
        this.rescue = true;
    }

    /**
     * Accessor:
     * Return a boolean value of If the piece has already rescued another
     * @return True, if this piece has already done a rescue, else false
     */
    public boolean getRescue() {
        return rescue;
    }

    /**
     * Accessor:
     * Return the icon associated with the piece
     * @return An ImageIcon according to the piece
     * @pre ImageIcon MUST be set before calling this
     */
    public ImageIcon getI() {
        return i;
    }

    /**
     * Transformer:
     * Initialise the icon according to piece name
     * @param i ImageIcon variable of the piece
     */
    public void setI(ImageIcon i) {
        this.i = i;
    }


    @Override
    public String toString() {
        return name;
    }
}