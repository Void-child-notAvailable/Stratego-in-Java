package View;

import Model.Pieces.*;
import Model.board.square;
import Model.player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitialBoard extends JFrame{
    private boolean iconSelected;
    private JButton selectedButton;
    private int selectedButtoni,selectedButtonj;
    String Rb;
    private final square[][] boardR= new square[3][10];

    public square[][] getBoardR() {
        return boardR;
    }

    public InitialBoard(player p) {
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowAdapter exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                whatamidoing();
            }
        };
        this.addWindowListener(exitListener);
        CardListener cl = new CardListener();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 10));
        panel.setSize(new Dimension(700,210));

        for(int i=0;i<6;i++){
            boardR[0][i]=new square();
            boardR[0][i].setPiece(new Trap(p));
        }
        boardR[0][6]=new square();
        boardR[0][6].setPiece(new NonSpecialPiece(10,p,"Dragon"));
        boardR[0][7]=new square();
        boardR[0][7].setPiece(new NonSpecialPiece(9,p,"Mage"));
        boardR[0][8]=new square();
        boardR[0][8].setPiece(new NonSpecialPiece(8,p,"Knight"));
        boardR[0][9]=new square();
        boardR[0][9].setPiece(new NonSpecialPiece(8,p,"Knight"));
        boardR[1][0]=new square();
        boardR[1][0].setPiece(new NonSpecialPiece(7,p,"BeastRider"));
        boardR[1][1]=new square();
        boardR[1][1].setPiece(new NonSpecialPiece(7,p,"BeastRider"));
        boardR[1][2]=new square();
        boardR[1][2].setPiece(new NonSpecialPiece(7,p,"BeastRider"));
        boardR[1][3]=new square();
        boardR[1][3].setPiece(new NonSpecialPiece(6,p,"Sorceress"));
        boardR[1][4]=new square();
        boardR[1][4].setPiece(new NonSpecialPiece(6,p,"Sorceress"));
        boardR[2][8]=new square();
        boardR[2][8].setPiece(new NonSpecialPiece(4,p,"Elf"));
        boardR[2][9]=new square();
        boardR[2][9].setPiece(new NonSpecialPiece(4,p,"Elf"));
        boardR[1][7]=new square();
        boardR[1][7].setPiece(new Dwarf(p));
        boardR[1][8]=new square();
        boardR[1][8].setPiece(new Dwarf(p));
        boardR[1][9]=new square();
        boardR[1][9].setPiece(new Dwarf(p));
        boardR[2][0]=new square();
        boardR[2][0].setPiece(new Dwarf(p));
        boardR[2][1]=new square();
        boardR[2][1].setPiece(new Dwarf(p));
        boardR[2][2]=new square();
        boardR[2][2].setPiece(new Scout(p));
        boardR[2][3]=new square();
        boardR[2][3].setPiece(new Scout(p));
        boardR[2][4]=new square();
        boardR[2][4].setPiece(new Scout(p));
        boardR[2][5]=new square();
        boardR[2][5].setPiece(new Scout(p));
        boardR[2][6]=new square();
        boardR[2][6].setPiece(new Flag(p));
        boardR[2][7]=new square();
        boardR[2][7].setPiece(new Slayer(p));

        if(Objects.equals(p.getName(), "P1")){
            JOptionPane.showMessageDialog(new JFrame(),"Welcome to stratego, the strategy board game\nPlayer 1 can make his board by clicking on 2 separate tiles and they will be switched, after that Player 1 should press the X at the top right so Player 2 can also set up their board");
            this.setTitle("Red board: SAVE & EXIT BY CLOSING THE WINDOW");
            Rb="R";
            boardR[1][5]=new square();
            boardR[1][5].setPiece(new NonSpecialPiece(5,p,"LavaBeast"));
            boardR[1][6]=new square();
            boardR[1][6].setPiece(new NonSpecialPiece(5,p,"LavaBeast"));

            for (int i=0; i<3;i++){
                for (int j=0;j<10;j++){
                    JButton b= new JButton();
                    b.setPreferredSize(new Dimension(70,70));
                    b.setMinimumSize(new Dimension(70,70));
                    b.setMargin(new Insets(0, 0, 0, 0));
                    b.setIcon(getImageCard(boardR[i][j].getPiece().getName()));
                    boardR[i][j].getPiece().setI(getImageCard(boardR[i][j].getPiece().getName()));
                    b.setName(String.valueOf(i*10+j));
                    b.addMouseListener(cl);
                    panel.add(b);
                }
            }
        }else{
            this.setTitle("Blue board: SAVE & EXIT BY CLOSING THE WINDOW");
            Rb="B";
            boardR[1][5]=new square();
            boardR[1][5].setPiece(new NonSpecialPiece(5,p,"Yeti"));
            boardR[1][6]=new square();
            boardR[1][6].setPiece(new NonSpecialPiece(5,p,"Yeti"));

            for (int i=0; i<3;i++){
                for (int j=0;j<10;j++){
                    JButton b= new JButton();
                    b.setPreferredSize(new Dimension(70,70));
                    b.setMinimumSize(new Dimension(70,70));
                    b.setMargin(new Insets(0, 0, 0, 0));
                    b.setIcon(getImageCard(boardR[i][j].getPiece().getName()));
                    boardR[i][j].getPiece().setI(getImageCard(boardR[i][j].getPiece().getName()));
                    b.setName(String.valueOf(i*10+j));
                    b.addMouseListener(cl);
                    panel.add(b);
                }
            }
        }




        this.add(panel,BorderLayout.CENTER);
        iconSelected = false;

        this.setPreferredSize(new Dimension(1000,800));
        this.setMinimumSize(new Dimension(1000,800));
    }


    private void whatamidoing(){
        this.setVisible(false);
    }


    private ImageIcon getImageCard(String name) {
        try {
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("./images/pieces"+Rb+"/"+name+Rb+".png")))
                    .getScaledInstance(100, 80, Image.SCALE_SMOOTH)); // image
        } catch (IOException ex) {
            Logger.getLogger(View.MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private class CardListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton but1 = ((JButton) e.getSource());
            if (iconSelected && !but1.equals(selectedButton)) {
                Icon i= but1.getIcon();
                square tmp= boardR[selectedButtoni][selectedButtonj];
                boardR[selectedButtoni][selectedButtonj]=boardR[Integer.parseInt(but1.getName())/10][Integer.parseInt(but1.getName())%10];
                boardR[Integer.parseInt(but1.getName())/10][Integer.parseInt(but1.getName())%10]=tmp;
                but1.setIcon(selectedButton.getIcon());
                selectedButton.setIcon(i);
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.black));
                selectedButton = but1;
                iconSelected = false;
            } else if (!iconSelected && but1.getName() != null) {
                iconSelected = true;
                selectedButton = but1;
                selectedButtoni= Integer.parseInt(but1.getName())/10;
                selectedButtonj= Integer.parseInt(but1.getName())%10;
                but1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            } else {
                if (iconSelected) {
                    System.out.println("Already Selected");
                } else {
                    System.out.println("Not selected");
                }
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
}
