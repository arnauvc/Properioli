package Hidato.Part3;

import Hidato.CtrlPresGestor;
import Hidato.part4.HexagonPattern;

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
    private JButton Endarrere;
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
                    m3f.main();
                    m3f.SetG(g);
                    Menu3Maquina.frame.dispose();
                }
                else {
                    Error_dades er = new Error_dades();
                    er.main();
                }
            }
        });
        Endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu3 m3 = new Menu3();
                String[] h = new String[0];
                m3.main(h);
                Menu3Maquina.frame.dispose();
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

    public void main() {
        JFrame frame = new JFrame("Menu3Maquina");
        frame.setResizable(false);
        frame.setContentPane(new Menu3Maquina().Finestra);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.SetF(frame);
    }
}
