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
    public String tcelaM;
    public String tadjM;
    public String dif;

    public Menu3Maquina() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = new Generacio();
                tcelaM = textField1.getText();
                tadjM = textField2.getText();
                dif = textField3.getText();
                if (!tcelaM.isEmpty() && !tadjM.isEmpty() && !dif.isEmpty()) {
                    tauler = g.GenerarHidato(tcelaM, tadjM, dif);
                    JFrame frameF = new JFrame("Menu3Final");
                    frameF.setResizable(false);
                    frameF.setContentPane(new Menu3Final().Finestra);
                    frameF.setLocationRelativeTo(null);
                    frameF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameF.pack();
                    frameF.setVisible(true);
                }
                else {
                    JFrame frame = new JFrame("Error_dades");
                    frame.setResizable(false);
                    frame.setContentPane(new Error_dades().panel1);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
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
