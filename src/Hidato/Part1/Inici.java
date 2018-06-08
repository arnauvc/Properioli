package Hidato.Part1;

import Hidato.CtrlPresGestor;
import Hidato.Part2.Menu2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Inici{
    private JButton examinarButton;
    private JPanel Window;
    private JLabel Titol;
    private JLabel Image;
    private JTextField username;
    private JButton seguentButton;
    private JRadioButton windowsRadioButton;
    private JRadioButton linuxRadioButton;
    private String path;
    private String nom;
    private boolean sistema = false;
    private static JFrame frame;
    public static CtrlPresGestor cg;
    JFileChooser chooser;

    public Inici() {
        cg = new CtrlPresGestor();
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
                System.out.printf("El path es: %s\n", path);
            }
        });
        seguentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sistema) path += "\\";
                else path += "/";
                System.out.printf("El path es: %s\n", path);
                cg.SetNom(username.getText());
                cg.SetPath(path);
                String[] s = new String[0];
                cg.Inicia();
                Menu2.main(s);
                frame.setVisible(false);
            }
        });
        windowsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema = true;
            }

        });
        linuxRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sistema = false;
            }
        });
    }


    public static void main(String[] args) {
        frame = new JFrame("Inici");
        frame.setContentPane(new Inici().Window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        //frame.setSize(300,300);
    }

    public String GetPath(){
        return path;
    }
    public String GetNom(){
        return nom;
    }
}
