package Hidato.Part3;

import Hidato.CtrlPresGestor;
import Hidato.Part2.Menu2;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu3Final {
    private JButton jugarButton;
    private JButton menúButton;
    public JPanel Finestra;
    private CtrlPresGestor g;
    private static JFrame frame;

    public Menu3Final() {
        menúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] aux;
                ArrayList<Pair<Pair<Integer, Integer>, String>> solucio = new ArrayList<Pair<Pair<Integer, Integer>, String>>();
                String[][] tauler = g.GetTauler();
                String tcela = g.GetTcela();
                String tadj = g.GetTadj();
                aux = g.CtrlResoldreHidato(tauler, tcela, tadj);
                for (int i = 0; i < aux.length; i++) {
                    for (int j = 0; j < aux[i].length; j++) {
                        if (!aux[i][j].equals(tauler[i][j])) {
                            Pair p = new Pair(i,j);
                            solucio.add(new Pair<>(p, aux[i][j]));
                        }
                    }
                }
                g.CtrlGuardarHidato(tcela, tadj, tauler, solucio);
                Menu2 m2 = new Menu2();
                String[] s = new String[0];
                m2.main(s);
                Menu3Final.frame.dispose();
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
        JFrame frame = new JFrame("Menu3Final");
        frame.setResizable(false);
        frame.setContentPane(new Menu3Final().Finestra);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.SetF(frame);
    }
}
