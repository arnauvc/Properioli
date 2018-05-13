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
                frameM3M.setContentPane(new Menu3Maquina().Finestra);
            }
        });
        Jo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu3");
        frame.setContentPane(new Menu3().Finestra1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
