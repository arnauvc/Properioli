package Hidato.Part3;

import Hidato.CtrlPresGestor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3 {
    private JPanel Finestra1;
    private JButton Màquina;
    private JButton Jo;
    public static CtrlPresGestor g;
    private static JFrame frame;

    public Menu3() {
        Màquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu3Maquina m3m = new Menu3Maquina();
                JFrame frameM3M = new JFrame("Menu3Maquina");
                frameM3M.setResizable(false);
                frameM3M.setContentPane(m3m.Finestra);
                frameM3M.setLocationRelativeTo(null);
                frameM3M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameM3M.pack();
                frameM3M.setVisible(true);
                m3m.SetG(g);
                m3m.SetF(frameM3M);
                Menu3.frame.dispose();
            }
        });
        Jo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu3Jo m3j= new Menu3Jo();
                JFrame frameM3J = new JFrame("Menu3Jo");
                frameM3J.setResizable(false);
                frameM3J.setContentPane(m3j.Finestra);
                frameM3J.setLocationRelativeTo(null);
                frameM3J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameM3J.pack();
                frameM3J.setVisible(true);
                m3j.SetF(frameM3J);
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
        g = new CtrlPresGestor();
    }
}
