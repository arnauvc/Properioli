package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuadrado extends JPanel {
    private final int ROWS;
    private final int COLUMNS;
    private JButton[][] cuabuton;
    private String [][] tauler;
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

    public Cuadrado(boolean cr, boolean ts) {
        crear = cr;
        tsol = ts;
        if (!crear) {
            if(!ts) tauler = Inici.cg.GetTauler();
            else Inici.cg.getTaulersol();
            ROWS = tauler.length;
            COLUMNS = tauler[0].length;
            System.out.println(ROWS);
            System.out.println(COLUMNS);
        }
        else{
            ROWS = Inici.cg.getFila();
            COLUMNS = Inici.cg.getColumna();
            tauler = new String[ROWS][COLUMNS];
            llenarmatriz();
        }
        setLayout(null);
        initGUI();
    }

    private void llenarmatriz(){
        for(int i = 0; i < ROWS;++i){
            for(int j = 0; j < COLUMNS;++j){
                tauler[i][j] = "?";
            }
        }
    }
    private void configurarelpanel() {
        offsetX = 0;
        offsetY = 0;
        setBackground(Color.white);
        cuabuton = new JButton[ROWS][COLUMNS];

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
                    String[] s = new String[0];
                    Menufinal.main(s);

                }
            });
        }

    }

    private void jugar() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int finalRow = row;
                int finalCol = col;
                if (!(tauler[row][col].equals("#"))) {
                    cuabuton[row][col] = new JButton();
                    if (tauler[row][col].equals("?")) {
                        cuabuton[row][col].setText(tauler[row][col]);
                        cuabuton[row][col].addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                s = num.getText();
                                if(!s.isEmpty()) {
                                    cuabuton[finalRow][finalCol].setText(s);
                                    tauler[finalRow][finalCol] = s;
                                    //HexagonButton clickedButton = (HexagonButton) e.getSource();
                                    //System.out.println("Button clicked: [" + clickedButton.getRow() + "][" + clickedButton.getCol() + "]");
                                }
                            }
                        });
                    }
                    else if (tauler[row][col].equals("*")) cuabuton[row][col].setText("NO");
                    else cuabuton[row][col].setText(tauler[row][col]);
                    cuabuton[row][col].setBounds(offsetX, offsetY, 100, 100);
                    add(cuabuton[row][col]);
                }
                offsetX += 100;
            }
            offsetY += 100;
        }
    }    

    private void crear() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            for (int col = 0; col < COLUMNS; col++) {
                cuabuton[row][col] = new JButton();
                int finalRow = row;
                int finalCol = col;
                cuabuton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            cuabuton[finalRow][finalCol].setText(s);
                            tauler[finalRow][finalCol] = s;
                            System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                        }
                    }
                });
                cuabuton[row][col].setBounds(offsetX, offsetY, 80, 80);
                add(cuabuton[row][col]);
                offsetX += 80;
            }
            offsetY += 80;
        }
    }
    private void initGUI(){
        configurarelpanel();
        accionbotones();
        if(!crear)  jugar();
        else if(crear)crear();

    }
}

