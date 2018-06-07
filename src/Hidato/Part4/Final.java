package Hidato.Part4;

import Hidato.Part1.Inici;
import Hidato.Part2.Menu2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final {
    private JPanel fin;
    private JButton salir;
    private JButton menu;
    private JLabel tiempo;
    private JLabel felicidades;
    private static JFrame frame;

    public Final() {
        String s = "TU TIEMPO ES: ";
        double temps = Inici.cg.tiempo();
        System.out.println(temps);
        int t = (int) (temps*1);
        s += String.valueOf(t);
        s += " segundos";
        System.out.println(t);

        tiempo.setText(s);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Menu");
                String[] s = new String[0];
                Menu2.main(s);
                Final.frame.dispose();
            }
        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){
        frame = new JFrame("Puto amo");
        frame.setResizable(true);
        frame.setContentPane(new Final().fin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
