package Model.board;

import Model.Pieces.piece;

public class board{
    private final square[][] board;

    /**
     * Constructor:
     * Creates an instance of the board, class
     * A board is an 8*10 array of squares (see square class)
     * with 8 obstructed squares and 72 empty spaces for pieces
     */
    public board(){
        board=new square[8][10];
        for(int i=0;i<8;i++){
            for (int j=0;j<10;j++){
                board[i][j]= new square();
            }
        }
        board[3][2].setObstructed();
        board[3][3].setObstructed();
        board[4][2].setObstructed();
        board[4][3].setObstructed();

        board[3][6].setObstructed();
        board[3][7].setObstructed();
        board[4][6].setObstructed();
        board[4][7].setObstructed();
    }

    /**
     * Accessor:
     * Returns an instance of the entire 8*10 board
     * @return a square[][] that represents the current board state
     */
    public square[][] getBoard() {
        return board;
    }

    /**
     * Accessor:
     * Returns a specific square in the 8*10 board
     * @param x is the y coordinate of the square
     * @param y is the x coordinate of the square
     * @return an instance of a square class within the board
     * @pre x and y must not be out of bounds of the board
     */
    public square getSquare(int x, int y){
        return board[x][y];
    }

    /**
     * Transformer:
     * Place a piece in a square within the board
     * @param p is the piece that is to be added in the square
     * @param x is the y coordinate of the square
     * @param y is the x coordinate of the square
     * @pre x and y must not be out of bounds of the board
     */
    public void placePiece(piece p, int x, int y){
        board[x][y].setPiece(p);
    }

    /**
     * Transformer:
     * Remove a piece in a square within the board
     * @param x is the y coordinate of the square
     * @param y is the x coordinate of the square
     * @pre x and y must not be out of bounds of the board
     */
    public void removePiece(int x, int y){
        board[x][y].setPiece(null);
    }
}