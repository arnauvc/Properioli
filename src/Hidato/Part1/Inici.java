package Hidato.Part1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Inici{
    private JButton examinarButton;
    private JPanel Window;
    private JLabel Titol;
    private JLabel Image;
    private JTextField username;
    private JButton seguentButton;
    JFileChooser chooser;

    public Inici() {
        examinarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                chooser.setAcceptAllFileFilterUsed(false);
                chooser.showSaveDialog(null);
                System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());

            }


        });
        seguentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inici");
        frame.setContentPane(new Inici().Window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
