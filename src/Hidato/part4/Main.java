package Hidato.part4;

import Hidato.Part3.Hexagon;
import Hidato.part4.HexagonPattern;
import Hidato.part4.triangle;

import javax.swing.*;

class Main {
    public static void main(String[] args) throws Exception {
        /*DriverPresentacio dp = new DriverPresentacio();
        dp.Inici();
        Panel pa = new Panel();
        */
        int n = 2;
        if(n == 1) {
            Hexagon hexPattern = new Hexagon(5,5);
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
            triangle tri = new triangle();
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
