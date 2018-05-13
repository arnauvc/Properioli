package Hidato.Part3;

import Hidato.Generacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3Maquina {
    private JLabel Tcela;
    private JLabel Tadj;
    private JLabel Dif;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton Generar;
    public JPanel Finestra;
    private Generacio g;
    public String[][] tauler;

    public Menu3Maquina() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = new Generacio();
                String tcela = textField1.getText();
                String tadj = textField2.getText();
                String dif = textField3.getText();
                tauler = g.GenerarHidato(tcela, tadj, dif);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu3Maquina");
        frame.setContentPane(new Menu3Maquina().Finestra);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
