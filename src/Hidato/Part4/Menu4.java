package Hidato.Part4;

import Hidato.CtrlPresGestor;
import Hidato.Part1.Inici;
import Hidato.Part4.HexagonPattern;
import Hidato.Part4.triangle;

import javax.swing.*;
import java.awt.*;

public class Menu4 {


    private static String tcela;
    private static boolean crear;
    private static JFrame frame;


    private static void configurar() {
        tcela = Inici.cg.GetTcela();
        crear = Inici.cg.isCrear();
        frame = new JFrame();
        frame.setTitle("Juego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 11000);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setBackground(Color.WHITE );
    }
    private static void tablero() {
        if(tcela.equals("H")) {
            HexagonPattern hexPattern = new HexagonPattern(crear);
            //frame.setLocation(new Point(700, 300));
            frame.add(hexPattern);

        }
        else if (tcela.equals("T")){
            triangle tri = new triangle(crear);
            //frame.setLocation(new Point(700, 300));
            frame.add(tri);
        }
        else if(tcela.equals("Q")){

        }
    }

    public static void main(String[] args){
        configurar();
        tablero();
    }




}