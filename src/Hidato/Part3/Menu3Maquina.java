package Hidato.Part3;

import Hidato.CtrlPresGestor;

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
    private CtrlPresGestor g;
    public String[][] tauler;
    public String tcelaM;
    public String tadjM;
    public String dif;
    private static JFrame frame;

    public Menu3Maquina() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tcelaM = textField1.getText();
                tadjM = textField2.getText();
                dif = textField3.getText();
                if (!tcelaM.isEmpty() && !tadjM.isEmpty() && !dif.isEmpty()) {
                    tauler = g.CtrlGenerarHidato(tcelaM, tadjM, dif);
                    g.SetTauler(tauler, tcelaM, tadjM);
                    Menu3Final m3f = new Menu3Final();
                    JFrame frameF = new JFrame("Menu3Final");
                    frameF.setResizable(false);
                    frameF.setContentPane(m3f.Finestra);
                    frameF.setLocationRelativeTo(null);
                    frameF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameF.pack();
                    frameF.setVisible(true);
                    m3f.SetG(g);
                    m3f.SetF(frameF);
                    Menu3Maquina.frame.dispose();
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

    public void SetG(CtrlPresGestor g) {
        this.g = g;
    }

    public void SetF(JFrame f) {
        this.frame = f;
    }

    public JFrame GetF() {
        return frame;
    }
}
