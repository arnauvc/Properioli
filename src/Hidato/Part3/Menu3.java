package Hidato.Part3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3 {
    private JPanel Finestra1;
    private JButton Màquina;
    private JButton Jo;

    public Menu3() {
        Màquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameM3M = new JFrame("Menu3Maquina");
                frameM3M.setResizable(false);
                frameM3M.setContentPane(new Menu3Maquina().Finestra);
                frameM3M.setLocationRelativeTo(null);
                frameM3M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameM3M.pack();
                frameM3M.setVisible(true);
            }
        });
        Jo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameM3J = new JFrame("Menu3Jo");
                frameM3J.setResizable(false);
                frameM3J.setContentPane(new Menu3Jo().Finestra);
                frameM3J.setLocationRelativeTo(null);
                frameM3J.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameM3J.pack();
                frameM3J.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu3");
        frame.setResizable(false);
        frame.setContentPane(new Menu3().Finestra1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
