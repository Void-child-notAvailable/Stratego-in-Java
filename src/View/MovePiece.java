package View;

import Controller.Controller;
import Model.Pieces.Slayer;
import Model.Pieces.piece;
import Model.board.board;
import Model.board.square;
import Model.player;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MovePiece extends JFrame {
    private boolean iconSelected;
    private JButton selectedButton;
    private int selectedButtonI,selectedButtonJ;
    static board board;
    static Controller controller;
    static JButton[] bArray;
    int[] highlightedMoves;

    JLabel Turn;
    JLabel winPercent;
    JLabel rescues;
    JLabel turn;
    int turnNo=1;
    JLabel totalCaptures;

    JLabel[] pp;


    public MovePiece() {

        this.setTitle("Stratego Game");
        this.setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardListener cl = new CardListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 10));
        bArray= new JButton[80];
        for (int i=0; i<8;i++){
            for (int j=0;j<10;j++){
                JButton b= new JButton();
                b.setPreferredSize(new Dimension(100,100));
                b.setMinimumSize(new Dimension(100,100));
                if (board.getSquare(i,j).isObstructed()) {
                    b.setIcon(getImageObs());
                }else if (board.getSquare(i,j).getPiece()==null){
                    b.setIcon(getImageBack());
                }else{
                    if (board.getSquare(i,j).getPiece().getPl().getTurn()){
                        b.setIcon(getImageCard(board.getSquare(i,j).getPiece().getName(), Objects.equals(board.getSquare(i, j).getPiece().getPl().getName(), "P1") ?"R":"B"));
                    }else{
                        b.setIcon(getImageHidden(Objects.equals(board.getSquare(i, j).getPiece().getPl().getName(), "P1") ?"R":"B"));
                    }
                }
                b.setName(String.valueOf(i*10+j));
                b.addMouseListener(cl);
                bArray[i*10+j]=b;
                panel.add(b);
            }
        }
        this.add(panel);
        iconSelected = false;

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        JPanel temp= new JPanel();
        temp.setLayout(new FlowLayout());
        JLabel Rules= new JLabel("<html>Eνεργοι κανονες<br/><html/>",SwingConstants.CENTER);
        Rules.setFont(new Font("Verdana",Font.PLAIN,18));
        temp.add(Rules);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        String tmp=(controller.getGameMode()==1)?"☑":"☐";
        String tmp2=(Controller.isNeverSurrender())?"☑":"☐";
        String rules="<html>" +
                "Μειωμενος στρατος&nbsp;&nbsp;"+tmp+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>" +
                "Καμια υποχωριση!&nbsp;&nbsp;&nbsp;&nbsp;"+tmp2+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><br/></html>";
        Rules = new JLabel(rules,SwingConstants.CENTER);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        temp.add(Rules);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        JLabel Stats = new JLabel("<html><br/><br/>Στατιστικα</html>",SwingConstants.CENTER);
        Stats.setFont(new Font("Verdana",Font.PLAIN,18));
        temp.add(Stats);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        Turn = new JLabel("<html>"+"P1's turn"+"<br/><br/></html>",SwingConstants.CENTER);
        Turn.setFont(new Font("Verdana",Font.PLAIN,12));
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        temp.add(Turn);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        winPercent = new JLabel("<html>Ποσοστο επιτ. επιθεσης 100<span>&#37;</span></html>",SwingConstants.CENTER);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        temp.add(winPercent);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        rescues = new JLabel("<html>Rescues Left:2&nbsp&nbsp&nbsp</html>",SwingConstants.CENTER);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        temp.add(rescues);

        turn = new JLabel("<html>Turn 1</html>",SwingConstants.CENTER);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        temp.add(turn);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new FlowLayout());
        JLabel Captures = new JLabel("<html><br/><br/>Αιχμαλωτισεις</html>",SwingConstants.CENTER);
        Captures.setFont(new Font("Verdana",Font.PLAIN,18));
        temp.add(Captures);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new GridLayout(3,3));
        pp=new JLabel[11];
        pp[0]= new JLabel("0",getImageCard("Slayer","B"), SwingConstants.RIGHT);
        pp[1]= new JLabel("0",getImageCard("Scout","B"), SwingConstants.RIGHT);
        pp[2]= new JLabel("0",getImageCard("Dwarf","B"), SwingConstants.RIGHT);
        pp[3]= new JLabel("0",getImageCard("Elf","B"), SwingConstants.RIGHT);
        pp[4]= new JLabel("0",getImageCard("Yeti","B"), SwingConstants.RIGHT);
        pp[5]= new JLabel("0",getImageCard("Sorceress","B"), SwingConstants.RIGHT);
        pp[6]= new JLabel("0",getImageCard("Knight","B"), SwingConstants.RIGHT);
        pp[7]= new JLabel("0",getImageCard("Mage","B"), SwingConstants.RIGHT);
        pp[8]= new JLabel("0",getImageCard("Dragon","B"), SwingConstants.RIGHT);
        pp[9]= new JLabel("0",getImageCard("Beastrider","B"), SwingConstants.RIGHT);
        pp[10]=new JLabel("0",getImageCard("trap","B"), SwingConstants.RIGHT);
        pp[0].setName("Slayer");
        pp[1].setName("Scout");
        pp[2].setName("Dwarf");
        pp[3].setName("Elf");
        pp[4].setName("Yeti");
        pp[5].setName("Sorceress");
        pp[6].setName("Knight");
        pp[7].setName("Mage");
        pp[8].setName("Dragon");
        pp[9].setName("Beastrider");
        pp[10].setName("trap");
        temp.add(pp[0]);
        temp.add(pp[1]);
        temp.add(pp[2]);
        temp.add(pp[3]);
        temp.add(pp[4]);
        temp.add(pp[5]);
        temp.add(pp[6]);
        temp.add(pp[7]);
        temp.add(pp[8]);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new GridLayout(1,3));
        temp.add(pp[9]);
        temp.add(pp[10]);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        panel2.add(temp);

        temp= new JPanel();
        temp.setLayout(new GridLayout(1,3));
        totalCaptures= new JLabel("<html>Συνολικες αιχμαλωτησεις 0</html>",SwingConstants.CENTER);
        temp.add(totalCaptures);
        temp.setOpaque(true);
        temp.setBackground(Color.gray);
        panel2.add(temp);



        this.add(panel2);
        this.setPreferredSize(new Dimension(1400,840));
        this.setMinimumSize(new Dimension(1400,840));
    }

    private void changeTurn(){
        controller.changeTurn();
        player tmp=Controller.getP1().getTurn()?Controller.getP1():Controller.getP2();
        if(Objects.equals(tmp.getName(), "P1")){
            turnNo+=1;
            pp[0].setIcon(getImageCard("Slayer","B"));
            pp[1].setIcon(getImageCard("Scout","B"));
            pp[2].setIcon(getImageCard("Dwarf","B"));
            pp[3].setIcon (getImageCard("Elf","B"));
            pp[4].setIcon(getImageCard("Yeti","B") );
            pp[5].setIcon(getImageCard("Sorceress","B") );
            pp[6].setIcon(getImageCard("Knight","B") );
            pp[7].setIcon(getImageCard("Mage","B"));
            pp[8].setIcon(getImageCard("Dragon","B"));
            pp[9].setIcon(getImageCard("BeastRider","B"));
            pp[10].setIcon(getImageCard("trap","B"));
            if(tmp.getP2().getDefeatedPieces()!=null){
                for (int j=0;j<11;j++){
                    pp[j].setText("0");}
                for (int i=0;i<tmp.getP2().getDefeatedPieces().size();i++){
                    for (int j=0;j<11;j++){
                        if(tmp.getP2().getDefeatedPieces().get(i).getName().toLowerCase(Locale.ROOT).equals(pp[j].getName().toLowerCase(Locale.ROOT))){
                            pp[j].setText(String.valueOf(Integer.parseInt(pp[j].getText())+1));
                        }
                    }
                }
            }
        }else{
            pp[0].setIcon(getImageCard("Slayer","R"));
            pp[1].setIcon(getImageCard("Scout","R") );
            pp[2].setIcon(getImageCard("Dwarf","R") );
            pp[3].setIcon(getImageCard("Elf","R"));
            pp[4].setIcon(getImageCard("lavabeast","R") );
            pp[5].setIcon(getImageCard("Sorceress","R") );
            pp[6].setIcon(getImageCard("Knight","R") );
            pp[7].setIcon(getImageCard("Mage","R") );
            pp[8].setIcon(getImageCard("Dragon","R"));
            pp[9].setIcon(getImageCard("BeastRider","R"));
            pp[10].setIcon(getImageCard("trap","R"));
            if(tmp.getP2().getDefeatedPieces()!=null){
                for (int j=0;j<11;j++){
                    pp[j].setText("0");}
                for (int i=0;i<tmp.getP2().getDefeatedPieces().size();i++){
                    for (int j=0;j<11;j++){
                        if(tmp.getP2().getDefeatedPieces().get(i).getName().toLowerCase(Locale.ROOT).equals(pp[j].getName().toLowerCase(Locale.ROOT))){
                            pp[j].setText(String.valueOf(Integer.parseInt(pp[j].getText())+1));
                        }
                    }
                }
            }
        }
        Turn.setText("<html>"+tmp.getName()+"'s turn"+"<br/><br/></html>");
        winPercent.setText("<html>Ποσοστο επιτ. επιθεσης "+tmp.getWinPercentage()+"<span>&#37;</span></html>");
        rescues.setText("<html>Rescues Left:"+tmp.getRescues()+"&nbsp&nbsp&nbsp</html>");
        turn.setText("<html>Turn "+turnNo+"</html>");
        if(tmp.getP2().getDefeatedPieces()==null){
            totalCaptures.setText("<html>Συνολικες αιχμαλωτησεις 0</html>");
        }else{
            totalCaptures.setText("<html>Συνολικες αιχμαλωτισεις "+tmp.getP2().getDefeatedPieces().size()+"</html>");
        }

        for (int i=0; i<8;i++){
            for (int j=0;j<10;j++){
                if (board.getSquare(i,j).getPiece()!=null){
                    if(board.getSquare(i,j).getPiece().getPl().getTurn()){
                        bArray[i*10+j].setIcon(board.getSquare(i,j).getPiece().getI());
                    }else{
                        bArray[i*10+j].setIcon(getImageHidden(Objects.equals(board.getSquare(i, j).getPiece().getPl().getName(), "P1") ?"R":"B"));
                    }}}}
    }

    private void checkValidMoves(){
        if(!controller.checkValidMoves()){
            JOptionPane.showMessageDialog(new JFrame(),"No valid moves, "+( !Controller.getP1().getTurn() ? "Red":"Blue")+" player wins!");
            System.exit(0);
        }
    }

    private void highlightMoves(){
        highlightedMoves=controller.checkValidMoves(selectedButtonI,selectedButtonJ);
        for (int i=0;i<highlightedMoves[0];i++){
            bArray[(selectedButtonI-1-i)*10+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.GREEN,7));
        }
        for (int i=0;i<highlightedMoves[1];i++){
            bArray[(selectedButtonI+1+i)*10+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.GREEN,7));
        }
        for (int i=0;i<highlightedMoves[2];i++){
            bArray[(selectedButtonI)*10-1-i+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.GREEN,7));
        }
        for (int i=0;i<highlightedMoves[3];i++){
            bArray[(selectedButtonI)*10+1+i+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.GREEN,7));
        }
    }

    private void restoreGreenHighlight(){
        for (int i=0;i<highlightedMoves[0];i++){
            bArray[(selectedButtonI-1-i)*10+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        for (int i=0;i<highlightedMoves[1];i++){
            bArray[(selectedButtonI+1+i)*10+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        for (int i=0;i<highlightedMoves[2];i++){
            bArray[(selectedButtonI)*10-1-i+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        for (int i=0;i<highlightedMoves[3];i++){
            bArray[(selectedButtonI)*10+1+i+selectedButtonJ].setBorder(BorderFactory.createLineBorder(Color.gray));
        }
    }

    private static boolean tryRescue(){
        ArrayList<Integer>aSpaces;
        if(Controller.getP1().getTurn()&&Controller.getP1().getRescues()>0 && Controller.getP1().getDefeatedPieces()!=null){
            aSpaces= new ArrayList<>();
            for (int i = 5; i < 8; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board.getSquare(i,j).getPiece()==null){
                        aSpaces.add(i*10+j);
                    }
                }}

            if (aSpaces.size()==0){
                return false;
            }

            Object[] r= Rescue(Controller.getP1(),aSpaces);
            if(r==null){return false;}
            Controller.getP1().rescueDone();
            controller.rescue((piece) r[0], (Integer) r[1]);
            bArray[(int) r[1]].setIcon(((piece) r[0]).getI());
            bArray[(int) r[1]].repaint();
            return true;
        }else if(Controller.getP2().getTurn()&&Controller.getP2().getRescues()>0&&Controller.getP2().getDefeatedPieces()!=null){
            aSpaces= new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++) {
                    if (board.getSquare(i,j).getPiece()==null){
                        aSpaces.add(i*10+j);
                    }
                }}
            if (aSpaces.size()==0){
                return false;
            }
            Object[] r=Rescue(Controller.getP2(),aSpaces);
            if(r==null){return false;}
            Controller.getP2().rescueDone();
            controller.rescue((piece) r[0], (Integer) r[1]);
            bArray[(int) r[1]].setIcon(((piece) r[0]).getI());
            bArray[(int) r[1]].repaint();
            return true;
        }
        return false;
    }

    public static Object[] Rescue(player p,ArrayList aSpaces){
        Object[] Rb=new Object[2];
        Object[] pieces=new Object[p.getDefeatedPieces().size()+1];
        pieces[0]="None";
        for (int k=1;k<p.getDefeatedPieces().size()+1;k++){
            pieces[k]=p.getDefeatedPieces().get(k-1);
        }
        Rb[0]=JOptionPane.showOptionDialog(null, "Which piece would you like to rescue", "Defeated pieces",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null,pieces,pieces[0]);
        if ((int)Rb[0]==0){
            return null;
        }
        Rb[0]=p.getDefeatedPieces().get((int)Rb[0]-1);
        String[] options= new String[aSpaces.size()];
        for (int i=0;i< options.length;i++){
            options[i]="Row "+((int)aSpaces.get(i)/10+1)+" and column "+((int)aSpaces.get(i)%10+1);
        }
        Object temp=JOptionPane.showInputDialog(null,"Where would you like to place the rescued piece","Rescue!",
                JOptionPane.PLAIN_MESSAGE,null,
                options,options[0]);
        Rb[1]=aSpaces.get(findIndex(options, (String) temp));
        p.getDefeatedPieces().remove(Rb[0]);
        return Rb;
    }

    public static int findIndex(String[] arr, String t)
    {
        int len = arr.length;
        return IntStream.range(0, len)
                .filter(i -> Objects.equals(t, arr[i]))
                .findFirst() // first occurrence
                .orElse(-1); // No element found
    }

    private ImageIcon getImageBack() {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("./images/Bg.png")))); // image
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageIcon getImageObs() {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("./images/Obs.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageIcon getImageHidden(String Rb){
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("./images/pieces"+Rb+"/hidden"+Rb+".png")))
                    .getScaledInstance(100, 80, Image.SCALE_SMOOTH)); // image
        } catch (IOException ex) {
            Logger.getLogger(View.MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ImageIcon getImageCard(String name,String Rb) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("./images/pieces"+Rb+"/"+name+Rb+".png")))
                    .getScaledInstance(100, 80, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            Logger.getLogger(View.MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private class CardListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton but = ((JButton) e.getSource());
            if (iconSelected && !but.equals(selectedButton)) {
                restoreGreenHighlight();
                int result=Controller.movement(selectedButtonI,selectedButtonJ,Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10,board);
                if(result==1){
                    but.setIcon(selectedButton.getIcon());
                    selectedButton.setIcon(getImageBack());
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                    selectedButton = but;
                    iconSelected = false;
                    if(Integer.parseInt(but.getName())/10==0&&board.getSquare(Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10).getPiece().getPl()==Controller.getP1()&&!board.getSquare(Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10).getPiece().getRescue() ||
                            Integer.parseInt(but.getName())/10==7&&board.getSquare(Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10).getPiece().getPl()==Controller.getP2()&&!board.getSquare(Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10).getPiece().getRescue()){
                        if(tryRescue()){
                            board.getSquare(Integer.parseInt(but.getName())/10,Integer.parseInt(but.getName())%10).getPiece().setRescue();
                        }
                    }
                    changeTurn();
                    checkValidMoves();
                }else if(result==-1){
                    selectedButton.setIcon(getImageBack());
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                    iconSelected = false;
                    changeTurn();
                    checkValidMoves();
                }else if (result==2){
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                    selectedButton.setIcon(getImageBack());
                    iconSelected=false;
                    but.setIcon(getImageBack());
                    changeTurn();
                    checkValidMoves();
                }else if (result==0){
                    selectedButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                    iconSelected = false;
                }else{
                    JOptionPane.showMessageDialog(new JFrame(),"Congratulations, you have captured your opponents flag!");
                    System.exit(0);
                }
            } else if (!iconSelected && but.getName() != null ) {
                selectedButtonI= Integer.parseInt(but.getName())/10;
                selectedButtonJ= Integer.parseInt(but.getName())%10;
                if(board.getSquare(selectedButtonI,selectedButtonJ).getPiece()!=null && board.getSquare(selectedButtonI,selectedButtonJ).getPiece().getPl().getTurn()){
                    iconSelected = true;
                    selectedButton = but;
                    but.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                    highlightMoves();
                }
            } else{
                iconSelected=false;
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.gray));
                restoreGreenHighlight();
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(){
        controller= new Controller();
        Object[] options = { "Normal", "Reduced" };
        controller.setGameMode(JOptionPane.showOptionDialog(null, "What gamemode would you like to play?", "Gamemode",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null,options, options[0]));
        options = new Object[]{ "Normal", "No surrender!" };
        controller.setNeverSurrender(JOptionPane.showOptionDialog(null, "Would you like to activate no surrender mode?", "Gamemode",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null,options, options[0]));

        if (controller.getGameMode() == 0) {
            InitialBoard initB= new InitialBoard(Controller.getP1());
            initB.setVisible(true);
            while(initB.isVisible()){System.out.print("");}
            square[][] temp= initB.getBoardR();

            initB= new InitialBoard(Controller.getP2());
            initB.setVisible(true);
            while(initB.isVisible()){System.out.print("");}

            controller.initBoard(temp,initB.getBoardR());
            controller.setTurnOne("P1");
            board=controller.getBoard();
            MovePiece view = new MovePiece();
            view.setVisible(true);
        }else{
            InitialBoardReduced initB= new InitialBoardReduced(Controller.getP1());
            initB.setVisible(true);
            while(initB.isVisible()){System.out.print("");}
            square[][] temp= initB.getBoardR();


            initB= new InitialBoardReduced(Controller.getP2());
            initB.setVisible(true);
            while(initB.isVisible()){System.out.print("");}

            controller.initBoard(temp,initB.getBoardR());
            controller.setTurnOne("P1");
            board=controller.getBoard();
            MovePiece view = new MovePiece();
            view.setVisible(true);
        }
    }
}