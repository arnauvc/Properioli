package Hidato;

import java.util.ArrayList;
import java.util.*;
import Hidato.Cela;
import Hidato.Tauler;

public class Ajuda {
    private Resolucio r;
    private String[][] matajuda;
    private String[][] mattauler;

    public String[][] GetAjuda(Tauler t){
        List<List<Cela>> tauler;
        tauler = t.getCelas();
        /*for (int i = 0; i < t.getNumFiles(); ++i) {
            for (int j = 0; j < t.getNumColum(); ++j) {

                System.out.printf("Element: %s\n", (tauler.get(i).get(j)).getValor());
                mattauler[i][j] = (tauler.get(i).get(j)).getValor();
                System.out.printf("Element: %s\n", mattauler[i][j]);
            }
        }*/
        t.MostrarTauler();
        matajuda = mattauler;


        return matajuda;
    }
}
