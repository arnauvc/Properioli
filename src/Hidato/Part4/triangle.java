package Hidato.Part4;

import Hidato.Part2.Menu2;
import javafx.scene.shape.TriangleMesh;

import Hidato.Part1.Inici;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class triangle extends JPanel {

    private final int ROWS;
    private final int COLUMNS;
    private TriButton[][] triButton;
    private String [][] taulerG;
    private boolean crear;
    private boolean tsol;

    private JTextField num;
    private int offsetX;
    private int offsetY;
    private String s;

    private JButton Jcrear;
    private JButton Jmenu;
    private JButton Jguardar;
    private JButton Jayuda;
    private JButton Jsalir;


    public triangle(boolean cr, boolean ts) {
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

    private void configurarelpanel(){
        offsetX = 0;
        offsetY = 0;
        setBackground(Color.white);
        triButton = new TriButton[ROWS][COLUMNS];
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds( 900, 350,100,55);
        num.setFont(new Font("Calibri",1,20));
        add(num);
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

    private boolean comprabarBase(int row, int col){
        if (((row == 0 || row % 2 == 0) & (col == 0 || col % 2 == 0)) || ((row % 2 != 0 & col % 2 != 0))) {
            return true;
        }
        return false;
    }

    private void jugar(){
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                String cel = Inici.cg.Stringcela(row,col);
                if (!(cel.equals("#"))) {
                    triButton[row][col] = new TriButton(comprabarBase(row, col));
                    int finalRow = row;
                    int finalCol = col;
                    triButton[row][col].setText(cel);
                    if (!Inici.cg.isTso()) {
                        if (cel.equals("?") || cel.charAt(cel.length() - 1) == 'I') {
                            triButton[row][col].setText(cel);
                            triButton[row][col].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    s = num.getText();
                                    if (!s.isEmpty()) {
                                        Inici.cg.Transpartida(finalRow, finalCol, s, "NUMERO");
                                        if (!Inici.cg.comprobajugada()) triButton[finalRow][finalCol].setText(s);
                                        if (Inici.cg.isTso()) {
                                            String[] s = new String[0];
                                            Final.main(s);
                                            Menu4.frame.dispose();
                                        }
                                    }
                                }
                            });
                        } else if (cel.equals("*")) triButton[row][col].setText("NO");
                        else triButton[row][col].setText(cel);
                        add(triButton[row][col]);
                        triButton[row][col].setBounds(offsetX, offsetY, 71, 71);
                    }
                }
                offsetX += 35;
            }
            offsetX = 0 ;
            offsetY += 70;
        }
    }
    private void crear(){
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++){
                triButton[row][col] = new TriButton(comprabarBase(row,col));
                int finalRow = row;
                int finalCol = col;
                triButton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        triButton[finalRow][finalCol].setText(s);
                        taulerG[finalRow][finalRow] = s;
                        System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                    }
                });
                add(triButton[row][col]);
                triButton[row][col].setBounds(offsetX, offsetY, 71, 71);
                offsetX += 35;
            }
            offsetX = 0 ;
            offsetY += 70;
        }
    }




    public void initGUI() {

        configurarelpanel();
        accionbotones();
        if(!crear) jugar();
        else crear();


    }



    //Following class draws the Buttons
    class TriButton extends JButton {
        private static final int LENGTH = 71;
        private static final int WIDTH = 71;
        private boolean a;

        public TriButton( boolean a) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setFont(new Font("Calibri",1,20));
            this.a = a;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            if (a) {
                hex.addPoint(35, 0); //"la punta de arriba"
                hex.addPoint(0, 70); //abajo a la izquierda
                hex.addPoint(70, 70); //abajo a la derecha
            } else {
                hex.addPoint(0, 0); //"la punta de arriba"
                hex.addPoint(70, 0); //abajo a la izquierda
                hex.addPoint(35, 70); //abajo a la derecha
            }
            g.setColor(Color.red);
            g.drawPolygon(hex);

        }
    }
}