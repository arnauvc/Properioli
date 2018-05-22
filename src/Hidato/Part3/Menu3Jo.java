package Hidato.Part3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public String tcelaJ;
    public String tadjJ;
    public String files;
    public String columnes;
    private static JFrame frame;

    public Menu3Jo() {
        Generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tcelaJ = textField1.getText();
                tadjJ = textField2.getText();
                files = textField3.getText();
                columnes = textField4.getText();
                if (!tcelaJ.isEmpty() && !tadjJ.isEmpty() && !files.isEmpty() && !columnes.isEmpty()) {
                    Menu3Final m3f = new Menu3Final();
                    JFrame frameF = new JFrame("Menu3Final");
                    frameF.setResizable(false);
                    frameF.setContentPane(m3f.Finestra);
                    frameF.setLocationRelativeTo(null);
                    frameF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameF.pack();
                    frameF.setVisible(true);
                    Menu3Jo.frame.dispose();
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

    public void SetF(JFrame f) {
        this.frame = f;
    }

    public JFrame GetF() {
        return frame;
    }
}
