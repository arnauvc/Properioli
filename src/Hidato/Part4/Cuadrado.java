package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuadrado extends JPanel{
    private final int ROWS;
    private final int COLUMNS;
    private cuadradoBoton[][] cuabuton;
    private String [][] taulerG;
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

    public Cuadrado(boolean cr, boolean ts) {
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
        offsetX = 0;
        offsetY = 0;
        setBackground(Color.white);
        cuabuton = new cuadradoBoton[ROWS][COLUMNS];

        //texto
        num = new JTextField();
        num.setSize(25,25);
        num.setBounds(950 , 800,100,55);
        num.setFont(new Font("Calibri",1,50));

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

    private void modificamatriu(){
        for(int i = 0; i < ROWS; ++i){
            for(int j = 0; j < COLUMNS; ++j){
                cuabuton[i][j].setText(Inici.cg.Stringcela(i,j));
            }
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

        if(!crear) {
            Jayuda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Inici.cg.Transpartida(-1,-1,"s","AJUDA");
                    System.out.println("Ayuda");
                    //modificamatriu();
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
            for (int col = 0; col < COLUMNS; col++) {
                String cel = Inici.cg.Stringcela(row,col);
                int finalRow = row;
                int finalCol = col;
                if (!cel.equals("#")) {
                    cuabuton[row][col] = new cuadradoBoton();
                    if(!Inici.cg.isTso()) {
                        if (cel.equals("?") || cel.charAt(cel.length()-1) == 'I') {
                            cuabuton[row][col].setText(cel);
                            cuabuton[row][col].addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    s = num.getText();
                                    if (!s.isEmpty()) {
                                        Inici.cg.Transpartida(finalRow, finalCol, s, "NUMERO");
                                        if(!Inici.cg.comprobajugada()) cuabuton[finalRow][finalCol].setText(s);
                                        if(Inici.cg.isTso()) {
                                            String[] s = new String[0];
                                            Final.main(s);
                                            Menu4.frame.dispose();
                                        }
                                    }
                                }
                            });
                        } else if (cel.equals("*")) cuabuton[row][col].setText("NO");
                        else cuabuton[row][col].setText(cel);
                    }
                    cuabuton[row][col].setBounds(offsetX, offsetY, 90, 90);
                    add(cuabuton[row][col]);
                }
                offsetX += 90;
            }
            offsetY += 90;
        }
    }

    private void generar() {
        for (int row = 0; row < ROWS; row++) {
            offsetX = 0;
            for (int col = 0; col < COLUMNS; col++) {
                cuabuton[row][col] = new cuadradoBoton();
                int finalRow = row;
                int finalCol = col;
                cuabuton[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        s = num.getText();
                        if (!s.isEmpty()) {
                            cuabuton[finalRow][finalCol].setText(s);
                            taulerG[finalRow][finalCol] = s;
                            System.out.println("Button clicked: [" + finalRow + "][" + finalCol + "]");
                        }
                    }
                });
                cuabuton[row][col].setBounds(offsetX, offsetY, 90, 90);
                add(cuabuton[row][col]);
                offsetX += 90;
            }
            offsetY += 90;
        }
    }
    private void initGUI(){
        configurarelpanel();
        accionbotones();
        if(!crear)  jugar();
        else generar();

    }

    class cuadradoBoton extends JButton {
        private static final int LENGTH = 100;
        private static final int WIDTH = 100;


        public cuadradoBoton() {
            setContentAreaFilled(false);
            setFocusPainted(true);
            setBorderPainted(false);
            setPreferredSize(new Dimension(WIDTH, LENGTH));
            setFont(new Font("Calibri",1,35));
            setBackground(Color.GREEN);
        }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.red);
                g.drawRect(0,0,89,89);

        }
    }
}

