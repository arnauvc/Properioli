package Hidato.Part2;

import Hidato.Part1.Inici;
import Hidato.Part4.Menu4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu21 {
    public static JFrame frame;
    private JPanel Finestra;
    private JButton aleatori;
    private JButton biblioteca;
    private JTextField numhidato;

    public Menu21() {
        aleatori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] s = new String[0];
                Menu22.main(s);
                Menu21.frame.dispose();
            }
        });
        biblioteca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String n = numhidato.getText();
                if(!n.isEmpty()) Inici.cg.hidatobiblio(Integer.parseInt(n));
                String[] s = new String[0];
                Menu4.main(s);
                Menu21.frame.dispose();
            }
        });
    }
    public static void main(String[] args) {
        frame = new JFrame("Menu21");
        frame.setResizable(false);
        frame.setContentPane(new Menu21().Finestra);
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,300);
    }
}

