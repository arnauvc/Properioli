package Hidato.Part2;

import Hidato.Part1.Inici;
import Hidato.Part4.Menu4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu22 {
    private JTextField nivel;
    private JTextField tipoc;
    private JTextField tipoad;
    private JButton comenzar;
    private JPanel R;
    private JLabel Tnivel;
    private static JFrame frame;

    private String tc;
    private String tn;
    private String ta;

    public Menu22(){

        comenzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tc = tipoc.getText();
                tn = nivel.getText();
                ta = tipoad.getText();
                Inici.cg.CtrlGenerarHidato(tc,ta,tn);
                String[] s = new String[0];
                Menu4.main(s);
                //frame.setVisible(false);
                //Menu22.frame.dispose();
            }
        });

    }

    public static void main(String[] args){
        frame = new JFrame("Menu3Jo");
        frame.setResizable(false);
        frame.setContentPane(new Menu22().R);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
