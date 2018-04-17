package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.util.*;
import Hidato.Resolucio;

public class Driver_Resolucio {

    private Resolucio r = new Resolucio();

    public void Prova() {
        String[][] s = new String[4][4];
        Integer k = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j%2 != 0) s[i][j] = String.valueOf(k);
                else s[i][j] = "?";
                k++;
            }
        }
        s = r.ResoltreHidato(s, "C");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(s[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
