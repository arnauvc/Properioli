package Hidato.part4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonPattern extends JPanel {
    private final long serialVersionUID = 1L;
    private final int ROWS = 10;
    private final int COLUMNS = 10;
    private HexagonButton[][] hexButton = new HexagonButton[ROWS][COLUMNS];
    private JTextField num;

    public HexagonPattern() {
        setLayout(null);
        initGUI();
    }


    public void initGUI() {

        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(ROWS * 80 , COLUMNS * 80,100,25);
        add(num);
        int offsetX = 43;
        int offsetY = 0;

        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++){
                hexButton[row][col] = new HexagonButton(col, row);
                int finalRow = row;
                int finalCol = col;
                hexButton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        hexButton[finalRow][finalCol].setText(num.getText());
                        HexagonButton clickedButton = (HexagonButton) e.getSource();
                        System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
                    }
                });
                add(hexButton[row][col]);
                hexButton[row][col].setBounds(offsetY, offsetX, 105, 95);
                offsetX += 87;
            }
            if(row%2 == 0) {
                offsetX = 0 ;
            }
            else {
                offsetX = 43;
            }
            offsetY += 76;
        }
    }



    //Following class draws the Buttons
    class HexagonButton extends JButton {
        private static final long serialVersionUID = 1L;
        private static final int SIDES = 6;
        private static final int SIDE_LENGTH = 50;
        public static final int LENGTH = 95;
        public static final int WIDTH = 105;
        private int row = 0;
        private int col = 0;

        public HexagonButton(int row, int col) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            this.row = row;
            this.col = col;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
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