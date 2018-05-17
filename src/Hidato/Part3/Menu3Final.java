package Hidato.Part3;

import Hidato.CtrlPresGestor;
import Hidato.HidatosSolucionats;
import Hidato.Resolucio;
import Hidato.Part3.Menu3Maquina;
import Hidato.Part3.Menu3Jo;
import Hidato.Tauler;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu3Final {
    private JButton jugarButton;
    private JButton menúButton;
    public JPanel Finestra;

    public Menu3Final() {
        menúButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CtrlPresGestor g = new CtrlPresGestor();
                Menu3Maquina m3m = new Menu3Maquina();
                String[][] aux;
                ArrayList<Pair<Pair<Integer, Integer>, String>> solucio = new ArrayList<Pair<Pair<Integer, Integer>, String>>();
                aux = g.CtrlResoldreHidato(m3m.tauler, m3m.tcelaM, m3m.tadjM);
                for (int i = 0; i < aux.length; i++) {
                    for (int j = 0; j < aux[i].length; j++) {
                        if (!aux[i][j].equals(m3m.tauler[i][j])) {
                            Pair p = new Pair(i,j);
                            solucio.add(new Pair<>(p, aux[i][j]));
                        }
                    }
                }
                g.CtrlGuardarHidato(m3m.tcelaM, m3m.tadjM, m3m.tauler, solucio);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu3Final");
        frame.setContentPane(new Menu3Final().Finestra);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
