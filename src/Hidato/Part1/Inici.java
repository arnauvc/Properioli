package Hidato.Part1;

import Hidato.CtrlPresGestor;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class Inici{
    private JButton examinarButton;
    private JPanel Window;
    private JLabel Titol;
    private JLabel Image;
    private JTextField username;
    private JButton seguentButton;
    private String path;
    private String nom;
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
                chooser.getCurrentDirectory();
                File newLoc = chooser.getSelectedFile();
                path = newLoc.getAbsolutePath();
                path += "/";
                System.out.printf("El path es: %s\n", path);
            }


        });
        seguentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom = username.getText();
                System.out.printf("El nom es: %s\n",nom);
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        CtrlPresGestor cg = new CtrlPresGestor();
        JFrame frame = new JFrame("Inici");
        frame.setContentPane(new Inici().Window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public String GetPath(){
        return path;
    }
    public String GetNom(){
        return nom;
    }
}
