package Controller;

import Model.Pieces.piece;
import Model.board.*;
import Model.player;

import java.util.Objects;


public class Controller {
    private static player P1;
    private static player P2;
    private static board b;
    private static int gameMode;
    private static boolean neverSurrender;

    /**
     * Constructor:
     * Creates an instance of the controller class,
     * creates a 10*8 board and initialises the 2 players
     */
    public Controller(){
        P1=new player("P1");
        P2=new player("P2");
        P1.setP2(P2);
        P2.setP2(P1);
    }

    /**
     * Transformer:
     * Initialises the board depending on the gameMode chosen
     * @param red is a 3*10 array of square objects,
     */
    public void initBoard( square[][] red, square[][] blue){
        b= new board();
        for(int i=5;i<8;i++){
            for (int j=0;j<10;j++){
                b.placePiece(red[i-5][j].getPiece(),i,j);
            }
        }
        for(int i=0;i<3;i++){
            for (int j=0;j<10;j++){
                b.placePiece(blue[i][j].getPiece(),i,j);
            }
        }
    }

    /**
     * Accessor:
     * Returns the full board of the game
     * @return an instance of the board
     */
    public board getBoard() {
        return b;
    }

    /**
     * Transformer:
     * Set the first player's turn
     * @post Set the turn of either P1 or P2 to true
     */
    public void setTurnOne(String p){
        if (Objects.equals(p, "P1")){
            P1.setTurn(true);
        }else{
            P2.setTurn(true);
        }
    }

    /**
     * Observer:
     * Checks the board for valid moves for a piece
     * @param x is the y coordinate on the board
     * @param y is they x coordinate on the board
     * @return An int array of allowed moves in the following way:
     *      [0]=1 meaning that there is 1 allowed move towards north
     *      [1]=0 meaning that there are no allowed moves south
     *      [2]=3 meaning that there are 3 allowed moves west
     *      [3]=0 meaning that there are no allowed moves east
     *      [4] is the total amount of allowed moves, used in checkValidMoves()
     */
    public int[] checkValidMoves(int x, int y){
        int[] valid= new int[5];
        valid[0]=0;
        valid[1]=0;
        valid[2]=0;
        valid[3]=0;
        if (b.getSquare(x,y).getPiece().allowedMovement()==1){
            if (x>0&&(!neverSurrender||b.getSquare(x,y).getPiece().getPl()!=P2||b.getSquare(x-1,y).getPiece()!=null)){
                if(!b.getSquare(x-1,y).isObstructed()&&(b.getSquare(x-1,y).getPiece()==null ||b.getSquare(x-1,y).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                    valid[0]=1;
                }
            }
            if (x<7&&(!neverSurrender||b.getSquare(x,y).getPiece().getPl()!=P1||b.getSquare(x+1,y).getPiece()!=null)){
                if(!b.getSquare(x+1,y).isObstructed()&&(b.getSquare(x+1,y).getPiece()==null ||b.getSquare(x+1,y).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                    valid[1]=1;
                }
            }
            if (y>0){
                if(!b.getSquare(x,y-1).isObstructed()&&(b.getSquare(x,y-1).getPiece()==null ||b.getSquare(x,y-1).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                    valid[2]=1;
                }}
            if (y<9){
                if (!b.getSquare(x,y+1).isObstructed()&&(b.getSquare(x,y+1).getPiece()==null ||b.getSquare(x,y+1).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                    valid[3]=1;
                }}
        }else if(b.getSquare(x,y).getPiece().allowedMovement()>1) {
            if (!neverSurrender || b.getSquare(x, y).getPiece().getPl() != P2) {
                for (int i = x - 1; i >= 0; i--) {
                    if (!b.getSquare(i, y).isObstructed()) {
                        if (b.getSquare(i, y).getPiece() == null) {
                            valid[0] += 1;
                        } else if (b.getSquare(i, y).getPiece().getPl() != b.getSquare(x, y).getPiece().getPl()) {
                            valid[0] += 1;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                if (x>0&&(b.getSquare(x,y).getPiece().getPl()!=P2||b.getSquare(x-1,y).getPiece()!=null)){
                    if(!b.getSquare(x-1,y).isObstructed()&&(b.getSquare(x-1,y).getPiece()==null ||b.getSquare(x-1,y).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                        valid[0]=1;
                    }
                }
            }

            if (!neverSurrender||b.getSquare(x,y).getPiece().getPl()!=P1) {
                for (int i = x + 1; i < 8 && (!neverSurrender || b.getSquare(x, y).getPiece().getPl() != P1); i++) {
                    if (!b.getSquare(i, y).isObstructed()) {
                        if (b.getSquare(i, y).getPiece() == null) {
                            valid[1] += 1;
                        } else if (b.getSquare(i, y).getPiece().getPl() != b.getSquare(x, y).getPiece().getPl()) {
                            valid[1] += 1;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }else{
                if (x<7&&(b.getSquare(x,y).getPiece().getPl()!=P1||b.getSquare(x+1,y).getPiece()!=null)){
                    if(!b.getSquare(x+1,y).isObstructed()&&(b.getSquare(x+1,y).getPiece()==null ||b.getSquare(x+1,y).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl())){
                        valid[1]=1;
                    }
                }
            }

            for (int i=y-1;i>=0;i--){
                if (!b.getSquare(x,i).isObstructed()){
                    if (b.getSquare(x,i).getPiece()==null){
                        valid[2]+=1;
                    }else if(b.getSquare(x,i).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl()){
                        valid[2]+=1;
                        break;
                    }else{break;}
                }else{break;}}

            for (int i=y+1;i<10;i++){
                if (!b.getSquare(x,i).isObstructed()){
                    if (b.getSquare(x,i).getPiece()==null){
                        valid[3]+=1;
                    }else if(b.getSquare(x,i).getPiece().getPl()!=b.getSquare(x,y).getPiece().getPl()){
                        valid[3]+=1;
                        break;
                    }else{break;}
                }else{break;}}
        }
        valid[4]=valid[0]+valid[1]+valid[2]+valid[3];
        return valid;
    }

    /**
     * Observer:
     * Checks the board for valid moves for the player whose turn it is
     * @return A boolean value where true means that for the player selected there is at least 1 valid move
     */
    public boolean checkValidMoves(){
        for (int i=0; i<8;i++){
            for (int j=0;j<10;j++){
                if (b.getSquare(i,j).getPiece()!=null&&b.getSquare(i,j).getPiece().getPl().getTurn()){
                    if(checkValidMoves(i,j)[4]>0){
                        return true;
                    }}}}
        return false;
    }

    /**
     * Transformer:
     * Given a piece and its coordinates ,place it back into the board
     * @param p Piece that is to be rescued
     * @param s coordinates for given piece to be placed, s is a 2-digit number where the leftmost digit is equal to the x(j) coordinate and the leftmost is the x(i)
     */
    public void rescue(piece p,int s){
        b.getSquare(s/10,s%10).setPiece(p);
    }

    /**
     * Transformer:
     * Change the turn based on whose turn it was
     */
    public void changeTurn(){
        if (P1.getTurn()){
            P1.setTurn(false);
            P2.setTurn(true);
        }else{
            P1.setTurn(true);
            P2.setTurn(false);
        }
    }

    /**
     * Transformer:
     * Attempt a movement of a piece in the board,
     *      if the square is empty and the distance is within the allowed movement of the piece it completes the movement
     *      if the square is occupied by an enemy piece and within the allowed movement, combat ensues
     * @pre x and y must contain a piece in the board
     * @param x Is the y coordinate of the piece
     * @param y Is the x coordinate of the piece
     * @param x2 Is the y coordinate of the desired movement location
     * @param y2 Is the x coordinate of the desired movement location
     * @return An int code based of the success or failure of movement,0 translates to movement not allowed, 1 is successful movement, -1 is unsuccessful movement/destruction of moving piece, 2 is a draw and 3 is flag has been captured
     */
    public static int movement(int x, int y, int x2, int y2,board b){
        piece attacker=b.getSquare(x,y).getPiece();

        if((x!=x2 && y!=y2 )||(x==x2 && y==y2)){
            //System.out.println("Diagonal movement");
            return 0;
        }

        if (x==x2&&Math.abs(y2-y)>attacker.allowedMovement()  ||  y==y2&&Math.abs(x2-x)> attacker.allowedMovement()){
            //System.out.println("Movement beyond allowed range");
            return 0;
        }

        if (attacker.allowedMovement()>1){
            if(x==x2){
                for (int i=y+(y2-y)/Math.abs(y2-y);i!=y2;i+=(y2-y)/Math.abs(y2-y)){
                    if (b.getSquare(x,i).isObstructed() || b.getSquare(x,i).getPiece()!=null){
                        //System.out.println("Movement through obstruction");
                        return 0;
                    }
                }
            }else{
                for (int i=x+(x2-x)/Math.abs(x2-x);i!=x2;i+=(x2-x)/Math.abs(x2-x)) {
                    if (b.getSquare(i, y).isObstructed() || b.getSquare(i, y).getPiece()!=null) {
                        //System.out.println("Movement through obstruction");
                        return 0;
                    }
                }
            }
        }

        if (b.getSquare(x2,y2).isObstructed()){
            //System.out.println("Movement through obstruction");
            return 0;
        }

        if(neverSurrender){
            if(x<x2&&attacker.getPl()==P1&&(b.getSquare(x2,y).getPiece()!=null&&b.getSquare(x2,y).getPiece().getPl()!=P2||b.getSquare(x2,y).getPiece()==null)){
                //System.out.println("No surender!");
                return 0;
            }else if(x>x2&&attacker.getPl()==P2&&(b.getSquare(x2,y).getPiece()!=null&&b.getSquare(x2,y).getPiece().getPl()!=P1||b.getSquare(x2,y).getPiece()==null)){
                //System.out.println("No surender!");
                return 0;
            }
        }

        if(b.getSquare(x2,y2).getPiece()==null){
            //System.out.println("Movement through Empty square");
            b.placePiece(attacker,x2,y2);
            b.removePiece(x,y);
            return 1;
        }


        piece defender=b.getSquare(x2,y2).getPiece();
        if(Objects.equals(attacker.getPl().getName(),defender.getPl().getName())){
            //System.out.println("Attack a piece that you own");
            return 0;
        }

        String result=attacker.combat(defender);
        switch (result) {
            case "w" -> {
                if(!Objects.equals(defender.getName(), "Trap")){defender.getPl().defeatedPiece(defender);}
                attacker.getPl().addAttacks();
                attacker.getPl().addAttacksWon();
                b.placePiece(attacker,x2,y2);
                b.removePiece(x,y);
                return 1;
            }
            case "l" ->{
                if(!Objects.equals(attacker.getName(), "Trap")){attacker.getPl().defeatedPiece(attacker);}
                attacker.getPl().addAttacks();
                b.removePiece(x,y);
                return -1;
            }
            case "d" ->{
                attacker.getPl().addAttacks();
                attacker.getPl().addAttacksWon();
                attacker.getPl().defeatedPiece(attacker);
                defender.getPl().defeatedPiece(defender);
                b.removePiece(x,y);
                b.removePiece(x2,y2);
                return 2;
            }
            case "end" ->{
                System.out.println("End");
                return 3;
            }
        }
        return 0;
    }

    /**
     * Accessor:
     * Get the player 2/blue player
     * @return player value of P2
     */
    public static player getP2() {
        return P2;
    }

    /**
     * Accessor:
     * Get the player 1/red player
     * @return player value of P1
     */
    public static player getP1() {
        return P1;
    }

    /**
     * Transformer:
     * Set the inital gamemode as normal/0 or reduced/1
     * @param gameMode int value of gamemode, 0 for normal and 1 for reduced
     */
    public void setGameMode(int gameMode){
        Controller.gameMode=gameMode;
    }

    /**
     * Acessor:
     * Returns the gamemode in int form
     * @return int value of gamode with 0 for normal and 1 for reduced
     */
    public int getGameMode() {
        return gameMode;
    }

    /**
     * Transformer:
     * Sets the gamemode as "Never surrender"
     * @param neverSurrender int value of gamemode, 0 for normal, 1 for never surrender
     */
    public void setNeverSurrender(int neverSurrender) {
        Controller.neverSurrender = neverSurrender==1;
    }

    /**
     * Acessor:
     * Returns true if gamemode is never surrender
     * @return boolean value of gamemode being never surrender
     */
    public static boolean isNeverSurrender() {
        return neverSurrender;
    }
}


