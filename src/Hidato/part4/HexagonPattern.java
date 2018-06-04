package Hidato.Part4;

import Hidato.Part1.Inici;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HexagonPattern extends JPanel {
    private final int ROWS;
    private final int COLUMNS;
    private HexagonButton[][] hexButton;
    private String [][] taulerG;
    private boolean crear;
    private boolean tsol;

    private JTextField num;
    private int offsetX;
    private int offsetY;
    private int offsetXaux;
    private int offsetYaux;
    private String s;
    private JButton Jcrear;
    private JButton Jmenu;
    private JButton Jguardar;
    private JButton Jayuda;
    private JButton Jsalir;

    public HexagonPattern(boolean cr, boolean ts) {
        crear = cr;
        ROWS = Inici.cg.getFila();
        COLUMNS = Inici.cg.getColumna();
        if (crear) {
            taulerG = new String[ROWS][COLUMNS];
            llenarmatriz();
        }
        setLayout(null);
        initGUI();
    }

    private void llenarmatriz(){
        for(int i = 0; i < ROWS;++i){
            for(int j = 0; j < COLUMNS;++j){
                taulerG[i][j] = "?";
            }
        }
    }

    private void configurarelpanel() {
        offsetXaux = offsetX = 0;
        offsetYaux = offsetY = 0;
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

        if(!crear) {
            Jguardar = new JButton();
            Jguardar.setText("Guardar");
            Jguardar.setBounds(50, 950, 100, 30);

            Jayuda = new JButton();
            Jayuda.setText("ayuda");
            Jayuda.setBounds(170, 950, 100, 30);
            add(Jayuda);
            add(Jguardar);
        }

        Jmenu = new JButton();
        Jmenu.setText("Menu");
        Jmenu.setBounds(290, 950, 100, 30);

        Jsalir = new JButton();
        Jsalir.setBounds(410,950,100,30);
        Jsalir.setText("Salir");

        //añadir
        add(num);
        add(Jmenu);
        add(Jsalir);
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

        if(!crear) {
            Jayuda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","AJUDA");
                    System.out.println("Ayuda");
                }
            });

            Jguardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","GUARDAR");
                    System.out.println("Guardar");
                }
            });
        }
        if(crear){
            Jcrear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("Crear");
                    Inici.cg.SetTauler(taulerG);
                    Inici.cg.CtrlGenerarHidato();
                    String[] s = new String[0];
                    Menufinal.main(s);
                    Menu4.frame.dispose();
                }
            });
        }

    }

    private void jugar() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            offsetYaux =  row * 87;
            for (int col = 0; col < COLUMNS; col++) {
                String cel = Inici.cg.Stringcela(row,col);
                int finalRow = row;
                int finalCol = col;
                if (col % 2 == 0) {
                    offsetY = 43 + offsetYaux ;
                } else {
                    offsetY =  offsetYaux;
                }
                if (!(cel.equals("#"))) {
                    hexButton[row][col] = new HexagonButton();
                    if (cel.equals("?")) {
                        hexButton[row][col].setText(cel);
                        hexButton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                s = num.getText();
                                if(!s.isEmpty()) {
                                    hexButton[finalRow][finalCol].setText(s);
                                    Inici.cg.Transpartida(finalRow,finalCol,s,"NUMERO");
                                }
                            }
                        });
                    }
                    else if (cel.equals("*")) hexButton[row][col].setText("NO");
                    else hexButton[row][col].setText(cel);
                    hexButton[row][col].setBounds(offsetX, offsetY, 105, 95);
                    add(hexButton[row][col]);
                }
                offsetX += 76;
            }
            offsetX  = 0;
            offsetY += 87;
        }
    }
    private void crear() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            offsetYaux =  row * 87;
            //System.out.println("fila: " + row);
            //System.out.println("offseYaux: " + offsetYaux);
            for (int col = 0; col < COLUMNS; col++) {
                //System.out.println("offsetx: " + offsetX + "offsety: " + offsetY);
                hexButton[row][col] = new HexagonButton();
                int finalRow = row;
                int finalCol = col;
                hexButton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            hexButton[finalRow][finalCol].setText(s);
                            //HexagonButton clickedButton = (HexagonButton) e.getSource();
                            taulerG[finalRow][finalCol] = s;
                            System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                        }
                    }
                });
                if (col % 2 == 0) {
                    offsetY = 43 + offsetYaux ;
                } else {
                    offsetY =  offsetYaux;
                }
                add(hexButton[row][col]);
                hexButton[row][col].setBounds(offsetX, offsetY, 105, 95);
                offsetX += 76;

            }
            offsetX  = 0;
            offsetY += 87;


        }
    }

    private void initGUI() {
        configurarelpanel();
        accionbotones();
        if(!crear) jugar();
        else if(crear)crear();
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
            setFont(new Font("Calibri",1,50));
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