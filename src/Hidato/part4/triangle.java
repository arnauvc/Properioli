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

    private void configurarelpanel(){
        setBackground(Color.white);
        triButton = new TriButton[ROWS][COLUMNS];
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(ROWS * 85 , COLUMNS * 85,100,25);
        add(num);
        offsetX = 0;
        offsetY = 0;
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
                if (!(tauler[row][col].equals("#"))) {
                    triButton[row][col] = new TriButton(comprabarBase(row,col));
                    int finalRow = row;
                    int finalCol = col;
                    if(tauler[row][col].equals("?")){
                        triButton[row][col].setText(tauler[row][col]);
                        triButton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                 s = num.getText();
                                 if(!s.isEmpty()) {
                                     triButton[finalRow][finalCol].setText(s);
                                     tauler[finalRow][finalRow] = s;
                                     //TriButton clickedButton = (TriButton) e.getSource();
                                     //System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                                 }
                            }
                        });
                    }
                    else if(tauler[row][col].equals("*")) triButton[row][col].setText("NO");
                    else triButton[row][col].setText(tauler[row][col]);
                    add(triButton[row][col]);
                    triButton[row][col].setBounds(offsetY, offsetX, 105, 95);
                }
                offsetX += 90;
            }
            offsetX = 0 ;
            offsetY += 53;
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
                        //TriButton clickedButton = (TriButton) e.getSource();
                        tauler[finalRow][finalRow] = s;
                        //System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
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

    public triangle(boolean cr) {
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