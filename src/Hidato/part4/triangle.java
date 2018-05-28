package Hidato.part4;

import javafx.scene.shape.TriangleMesh;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class triangle extends JPanel {
    private final long serialVersionUID = 1L;
    private final int ROWS = 10;
    private final int COLUMNS = 10;
    private TriButton[][] triButton = new TriButton[ROWS][COLUMNS];
    private JTextField num;

    public triangle() {
        setLayout(null);
        initGUI();
    }


    public void initGUI() {

        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(ROWS * 80 , COLUMNS * 80,100,25);
        add(num);
        int offsetX = 0;
        int offsetY = 0;

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++){
                if(((row == 0 || row%2 == 0) & (col == 0 || col%2 == 0)) ||((row%2 != 0 & col%2 != 0))){
                    triButton[row][col] = new TriButton(col, row,true);
                }
                else triButton[row][col] = new TriButton(col, row,false);
                int finalRow = row;
                int finalCol = col;
                triButton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        triButton[finalRow][finalCol].setText(num.getText());
                        TriButton clickedButton = (TriButton) e.getSource();
                        System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
                    }
                });
                add(triButton[row][col]);
                triButton[row][col].setBounds(offsetY, offsetX, 105, 95);
                offsetX += 90;
            }
            offsetX = 0 ;
            offsetY += 55;
        }
    }



    //Following class draws the Buttons
    class TriButton extends JButton {
        private static final long serialVersionUID = 1L;
        //private static final int SIDES = 6;
        //private static final int SIDE_LENGTH = 50;
        public static final int LENGTH = 95;
        public static final int WIDTH = 105;
        private int row = 0;
        private int col = 0;
        private boolean a;

        public TriButton(int row, int col, boolean a) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            this.row = row;
            this.col = col;
            this.a = a;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            if(a) {
                hex.addPoint(55, 0); //"la punta de arriba"
                hex.addPoint(0, 90); //abajo a la izquierda
                hex.addPoint(105, 90); //abajo a la derecha
            }
            else {
                hex.addPoint(0, 0); //"la punta de arriba"
                hex.addPoint(105, 0); //abajo a la izquierda
                hex.addPoint(55, 90); //abajo a la derecha
            }
            g.drawPolygon(hex);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}