package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.util.*;
import Hidato.Resolucio;
import Hidato.Generacio;

public class Driver_Resolucio {

    private Resolucio r = new Resolucio();
    private Generacio g = new Generacio();

    public void Prova() {
        String[][] tauler = g.GenerarHidato("Q", "C", "FACIL");
        /*Integer k = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0 && i == 0) s[i][j] = String.valueOf(1);
                else if (j == 0 && i == 4) s[i][j] = String.valueOf(25);
                else s[i][j] = "?";
            }
        }*/
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                System.out.print(tauler[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_");
        String[][] s = r.ResoltreHidato(tauler, "C");
        if (s != null) {
            System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_");
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s[i].length; j++) {
                    System.out.print(s[i][j]);
                    System.out.print(" ");
                }
                System.out.println("");
            }
        }
    }
}
