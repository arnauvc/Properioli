package Hidato.Part4;

import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.*;

public class Ayuda {
    private JPanel Principal;
    private JLabel Ayuda;
    private static JFrame frame;

    public Ayuda(boolean a){
        String s1;
        if(a)  s1 = "S'ha gaurdat correctament";
        else s1 = "No s'ha gaurdat la partida";
        Ayuda.setFont(new Font("Calibri",1,50));
        Ayuda.setText(s1);
        //else Ayuda.setText(s2);

    }


    public static void main(String[] args) throws InterruptedException {
        frame = new JFrame("Ayuda");
        frame.setContentPane(new Ayuda(Inici.cg.getA()).Principal);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Thread.sleep(10);
        //System.exit(0);
    }
}
