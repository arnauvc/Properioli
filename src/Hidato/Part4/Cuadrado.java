package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.*;

public class Cuadrado extends JPanel {
    private final int ROWS;
    private final int COLUMNS;
    private JButton[][] cuabuton;
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

    public Cuadrado(boolean cr) {
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
        //initGUI();
    }
    private void configurarelpanel() {
        offsetX = 43;
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
}
