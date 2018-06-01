package Hidato.Part3;

import javax.swing.*;

public class Error_dades {
    public JPanel panel1;

    public static void main() {
        JFrame frame = new JFrame("Error_dades");
        frame.setResizable(false);
        frame.setContentPane(new Error_dades().panel1);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
