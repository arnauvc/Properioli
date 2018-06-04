package Hidato.Part4;

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
        num.setBounds(ROWS * 85 , COLUMNS * 85,100,25);
        add(num);
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
                    triButton[row][col] = new TriButton(comprabarBase(row,col));
                    int finalRow = row;
                    int finalCol = col;
                    if(cel.equals("?")){
                        triButton[row][col].setText(cel);
                        triButton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                 s = num.getText();
                                 if(!s.isEmpty()) {
                                     triButton[finalRow][finalCol].setText(s);
                                     Inici.cg.Transpartida(finalRow,finalCol,s,"NUMERO");
                                 }
                            }
                        });
                    }
                    else if(cel.equals("*")) triButton[row][col].setText("NO");
                    else triButton[row][col].setText(cel);
                    add(triButton[row][col]);
                    triButton[row][col].setBounds(offsetX, offsetY, 105, 95);
                }
                offsetX += 53;
            }
            offsetX = 0 ;
            offsetY += 90;
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
                triButton[row][col].setBounds(offsetX, offsetY, 105, 95);
                offsetX += 53;
            }
            offsetX = 0 ;
            offsetY += 90;
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
        private static final int LENGTH = 95;
        private static final int WIDTH = 105;
        private boolean a;

        public TriButton( boolean a) {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setFont(new Font("Calibri",1,35));
            this.a = a;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Polygon hex = new Polygon();
            if (a) {
                hex.addPoint(53, 0); //"la punta de arriba"
                hex.addPoint(0, 90); //abajo a la izquierda
                hex.addPoint(105, 90); //abajo a la derecha
            } else {
                hex.addPoint(0, 0); //"la punta de arriba"
                hex.addPoint(105, 0); //abajo a la izquierda
                hex.addPoint(52, 90); //abajo a la derecha
            }
            g.setColor(Color.red);
            g.drawPolygon(hex);

        }
    }
}