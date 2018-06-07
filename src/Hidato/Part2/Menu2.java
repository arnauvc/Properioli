package Hidato.Part2;

import Hidato.Part1.Inici;
import Hidato.Part3.Menu3;
import Hidato.Part3.Menu3Jo;
import Hidato.Part4.Menu4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu2 {
    private JButton jugarButton;
    private JButton ReprendreButton;
    private JButton sortirButton;
    private JButton generarButton;
    private static JFrame frame;
    private JPanel Finestra2;


    public Menu2() {
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] s = new String[0];
                Menu21.main(s);
                Menu2.frame.dispose();
            }
        });
        ReprendreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Inici.cg.Rependre();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                String[] s= new String[0];
                Menu4.main(s);
            }
        });
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String[] s = new String[0];
                Menu2.frame.dispose();
                Menu3.main(s);*/
                Menu3Jo m3j= new Menu3Jo();
                m3j.main();
                Menu2.frame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        frame = new JFrame("Menu2");
        frame.setResizable(false);
        frame.setContentPane(new Menu2().Finestra2);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,300);
    }
}
