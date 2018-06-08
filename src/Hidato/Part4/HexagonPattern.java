package Hidato.Part4;

import Hidato.Part1.Inici;
import Hidato.Part2.Menu2;

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
        num.setBounds(900,350,100,55);
        num.setFont(new Font("Calibri",1,50));

        //botones
        configurarboronoes();
    }

    private void configurarboronoes(){
        if(crear) {
            Jcrear = new JButton();
            Jcrear.setText("Generar");
            Jcrear.setBounds(900, 50, 100, 30);
            add(Jcrear);
        }

        if(!crear) {
            Jguardar = new JButton();
            Jguardar.setText("Guardar");
            Jguardar.setBounds(900, 100, 100, 30);

            Jayuda = new JButton();
            Jayuda.setText("Ajuda");
            Jayuda.setBounds(900, 150, 100, 30);
            add(Jayuda);
            add(Jguardar);
        }

        Jmenu = new JButton();
        Jmenu.setText("Menú");
        Jmenu.setBounds(900, 200, 100, 30);

        Jsalir = new JButton();
        Jsalir.setBounds(900,250,100,30);
        Jsalir.setText("Sortir");
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
                String[] s = new String[0];
                Menu2.main(s);
                Menu4.frame.dispose();
                System.out.println("Menu");
            }
        });

        Jsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
                System.out.println("Salir");
            }
        });

        if(!crear) {
            Jayuda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","AJUDA");
                    System.out.println("Ayuda");
                    Menu4.frame.dispose();
                    String[] s = new String[0];
                    Menu4.main(s);
                    Inici.cg.setTayuda(false);
                }
            });

            Jguardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","GUARDAR");
                    System.out.println("Guardar");
                    String[] s = new String[0];
                    try {
                        Ayuda.main(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Menu4.frame.dispose();
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
            offsetYaux =  row * 70;
            for (int col = 0; col < COLUMNS; col++) {
                String cel = Inici.cg.Stringcela(row,col);
                int finalRow = row;
                int finalCol = col;
                if (col % 2 == 0) {
                    offsetY = 35 + offsetYaux ;
                } else {
                    offsetY =  offsetYaux;
                }
                if (!(cel.equals("#"))) {
                    hexButton[row][col] = new HexagonButton();
                    hexButton[row][col].setText(cel);
                    if (!Inici.cg.isTso()) {
                        if (cel.equals("?") || cel.charAt(cel.length() - 1) == 'I') {
                            hexButton[row][col].setText(cel);
                            hexButton[row][col].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    s = num.getText();
                                    if (!s.isEmpty()) {
                                        Inici.cg.Transpartida(finalRow, finalCol, s, "NUMERO");
                                        if (!Inici.cg.comprobajugada()) hexButton[finalRow][finalCol].setText(s);
                                        if (Inici.cg.isTso()) {
                                            String[] s = new String[0];
                                            Final.main(s);
                                            Menu4.frame.dispose();
                                        }
                                    }
                                }
                            });
                        } else if (cel.equals("*")) hexButton[row][col].setText("NO");
                        else hexButton[row][col].setText(cel);
                        hexButton[row][col].setBounds(offsetX, offsetY, 80, 71);
                        add(hexButton[row][col]);
                    }
                }
                offsetX += 60;
            }
            offsetX  = 0;
            offsetY += 70;
        }
    }
    private void crear()  {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            offsetYaux =  row * 70;
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
                    offsetY = 35 + offsetYaux ;
                } else {
                    offsetY =  offsetYaux;
                }
                add(hexButton[row][col]);
                hexButton[row][col].setBounds(offsetX, offsetY, 81, 71);
                offsetX += 60;

            }
            offsetX  = 0;
            offsetY += 70;
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
        public static final int LENGTH = 71;
        public static final int WIDTH = 80;

        public HexagonButton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setFont(new Font("Calibri",1,30));
            setPreferredSize(new Dimension(WIDTH, LENGTH));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            /*for (int i = 0; i < SIDES; i++) {
                hex.addPoint((int) (50 + SIDE_LENGTH * Math.cos(i * 2 * Math.PI / SIDES)), //calculation for side
                        (int) (50 + SIDE_LENGTH * Math.sin(i * 2 * Math.PI / SIDES)));   //calculation for side
            }*/
            //PUNTOS DEL HEXAGONO;
            hex.addPoint(0,35); //punto izquierda
            hex.addPoint(20,0); //punto arriba izquierda
            hex.addPoint(60,0); //punto arriba derecha
            hex.addPoint(80,35); //putno derecha
            hex.addPoint(60,70); //punto abajo derecha
            hex.addPoint(20,70);;
            ;
            g.setColor(Color.red);
            g.drawPolygon(hex);
        }

    }
}