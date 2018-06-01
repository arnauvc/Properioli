package Hidato.Part3;

import Hidato.Part1.Inici;
import Hidato.Part4.Menu4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu3Jo {
    private JLabel Tcela;
    private JLabel Tadj;
    private JLabel Files;
    private JLabel Columnes;
    private JTextField tipoc;
    private JTextField tipoadj;
    private JTextField nfiles;
    private JTextField ncolum;
    private JButton Generar;
    public JPanel Finestra;
    private JButton Endarrere;
    public String tcelaJ;
    public String tadjJ;
    public String files;
    public String columnes;
    private static JFrame frame;

    public Menu3Jo() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tcelaJ = tipoc.getText();
                tadjJ = tipoadj.getText();
                files = nfiles.getText();
                columnes = ncolum.getText();
                if (!tcelaJ.isEmpty() && !tadjJ.isEmpty() && !files.isEmpty() && !columnes.isEmpty()) {
                    //Inici.cg.setcela(tcelaJ);
                    //Inici.cg.setTadjG(tadjJ);
                    Inici.cg.SetTipusTauler(tcelaJ,tadjJ);
                    Inici.cg.setFilaColumna(Integer.valueOf(files),Integer.valueOf(columnes));
                    Inici.cg.SetCrear(true);
                    System.out.println(tcelaJ+ " " + tadjJ);
                    Menu3Jo.frame.dispose();
                    String[] s = new String[0];
                    Menu4.main(s);
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
                Menu3Jo.frame.dispose();
            }
        });
    }

    public void SetF(JFrame f) {
        this.frame = f;
    }

    public JFrame GetF() {
        return frame;
    }

    public void main() {
        JFrame frame = new JFrame("Menu3Jo");
        frame.setResizable(false);
        frame.setContentPane(new Menu3Jo().Finestra);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.SetF(frame);
    }
}
