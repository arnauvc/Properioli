package Hidato.Part4;

import Hidato.Part4.Hexagon;
import Hidato.Part4.Triangle;

import javax.swing.*;

class Main {
    public static void main(String[] args) throws Exception {
        /*DriverPresentacio dp = new DriverPresentacio();
        dp.Inici();
        Panel pa = new Panel();
        */
        int n = 1;
        if(n == 1) {
            Hexagon hexPattern = new Hexagon();
            JFrame frame = new JFrame();
            frame.setTitle("Juego");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLocation(new Point(700, 300));
            frame.add(hexPattern);
            frame.setSize(1000, 1000);
            frame.setResizable(false);
            frame.setVisible(true);
        }
        else {
            Triangle tri = new Triangle();
            JFrame frame = new JFrame();
            frame.setTitle("Juego");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLocation(new Point(700, 300));
            frame.add(tri);
            frame.setSize(1000, 1000);
            frame.setResizable(false);
            frame.setVisible(true);
        }
    }

}