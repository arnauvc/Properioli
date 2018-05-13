package Hidato.Part3;

import javax.swing.*;

public class Menu3Jo {
    private JLabel Tcela;
    private JLabel Tadj;
    private JLabel Files;
    private JLabel Columnes;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton Generar;
    public JPanel Finestra;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu3Jo");
        frame.setContentPane(new Menu3Jo().Finestra);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
