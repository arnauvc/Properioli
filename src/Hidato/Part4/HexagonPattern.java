package Hidato.Part4;

import Hidato.Part1.Inici;
import com.sun.crypto.provider.JceKeyStore;
import sun.awt.Graphics2Delegate;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonPattern extends JPanel {
    private final int ROWS;
    private final int COLUMNS;
    private HexagonButton[][] hexButton;
    private String [][] tauler;
    private boolean crear;

    private JTextField num;
    private int offsetX;
    private int offsetY;
    private String s;
    private JButton Jcrear;
    private JButton Jmenu;
    private JButton Jguardar;
    private JButton Jayuda;
    private JButton Jsalir;

    public HexagonPattern(boolean cr) {
        crear = cr;
        if (!crear) {
            tauler = Inici.cg.GetTauler();
            ROWS = tauler.length;
            COLUMNS = tauler[0].length;
            System.out.println(ROWS);
            System.out.println(COLUMNS);
        }
        else{
            ROWS = Inici.cg.getFila();
            COLUMNS = Inici.cg.getColumna();
            tauler = new String[ROWS][COLUMNS];
        }
        setLayout(null);
        initGUI();
    }

    private void configurarelpanel() {
        offsetX = 43;
        offsetY = 0;
        setBackground(Color.white);
        hexButton = new HexagonButton[ROWS][COLUMNS];
        //texto
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(800 , 800,100,30);

        //botones
        configurarboronoes();
    }

    private void configurarboronoes(){
        if(crear) {
            Jcrear = new JButton();
            Jcrear.setText("Crear");
            Jcrear.setBounds(530, 950, 100, 30);
            add(Jcrear);
        }
        Jguardar = new JButton();
        Jguardar.setText("Guardar");
        Jguardar.setBounds(50,950,100,30);

        Jayuda = new JButton();
        Jayuda.setText("ayuda");
        Jayuda.setBounds(170,950,100,30);

        Jmenu = new JButton();
        Jmenu.setText("Menu");
        Jmenu.setBounds(290, 950, 100, 30);

        Jsalir = new JButton();
        Jsalir.setBounds(410,950,100,30);
        Jsalir.setText("Salir");
        //añadir
        add(num);
        add(Jguardar);
        add(Jmenu);
        add(Jayuda);
        add(Jsalir);
    }
    private void jugar() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int finalRow = row;
                int finalCol = col;
                if (!(tauler[row][col].equals("#"))) {
                    hexButton[row][col] = new HexagonButton();
                    if (tauler[row][col].equals("?")) {
                        hexButton[row][col].setText(tauler[row][col]);
                        hexButton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                s = num.getText();
                                if(!s.isEmpty()) {
                                    hexButton[finalRow][finalCol].setText(s);
                                    tauler[finalRow][finalCol] = s;
                                    //HexagonButton clickedButton = (HexagonButton) e.getSource();
                                    //System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
                                }
                            }
                        });
                    }
                    else if (tauler[row][col].equals("*")) hexButton[row][col].setText("NO");
                    else hexButton[row][col].setText(tauler[row][col]);
                    add(hexButton[row][col]);
                    hexButton[row][col].setBounds(offsetY, offsetX, 105, 95);
                }
                offsetX += 87;
            }
            if (row % 2 == 0) {
                offsetX = 0;
            } else {
                offsetX = 43;
            }
            offsetY += 76;
        }
    }
    private void crear() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                hexButton[row][col] = new HexagonButton();
                int finalRow = row;
                int finalCol = col;
                hexButton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            hexButton[finalRow][finalCol].setText(s);
                            //HexagonButton clickedButton = (HexagonButton) e.getSource();
                            tauler[finalRow][finalCol] = s;
                            //System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
                        }
                    }
                });
                add(hexButton[row][col]);
                hexButton[row][col].setBounds(offsetY, offsetX, 105, 95);
                offsetX += 87;
            }
            if (row % 2 == 0) {
                offsetX = 0;
            } else {
                offsetX = 43;
            }
            offsetY += 76;
        }
    }

    private void accionbotones(){
        Jmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Menu");
            }
        });

        Jsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Salir");
            }
        });

        Jayuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Ayuda");
            }
        });

        Jguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Guardar");
            }
        });

        if(crear){
            Jcrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Crear");
                    Inici.cg.SetTauler(tauler);

                }
            });
        }

    }


    private void initGUI() {
        configurarelpanel();
        accionbotones();
        if(!crear)  jugar();
        else if(crear)crear();

    }




    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    //Following class draws the Buttons
    class HexagonButton extends JButton {
        private static final int SIDES = 6;
        private static final int SIDE_LENGTH = 50;
        public static final int LENGTH = 95;
        public static final int WIDTH = 105;

        public HexagonButton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
            }
            g.setColor(Color.red);
            g.drawPolygon(hex);
        }

    }
}