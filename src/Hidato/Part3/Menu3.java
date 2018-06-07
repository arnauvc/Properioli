package Hidato.Part3;

import Hidato.CtrlPresGestor;
import Hidato.Part1.Inici;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3 {
    private JPanel Finestra1;
    private JButton Jo;
    private static JFrame frame;

    public Menu3() {
        /*Màquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu3Maquina m3m = new Menu3Maquina();
                m3m.main();
                m3m.SetG(Inici.cg);
                Menu3.frame.dispose();
            }
        });*/
        Jo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu3Jo m3j= new Menu3Jo();
                m3j.main();
                Menu3.frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Menu3");
        frame.setResizable(false);
        frame.setContentPane(new Menu3().Finestra1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //g = new CtrlPresGestor();
    }
}
