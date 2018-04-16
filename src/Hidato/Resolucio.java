package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.util.*;

public class Resolucio{

    private String[][] solucio;
    private String adj = new String();
    ArrayList<Pair<Pair<Integer, Integer>, Integer>> sl;

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(String.valueOf(cadena));
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    private boolean TrobarSolucio(Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2) {
        int distancia = valor2-valor1;
        return true;
    }

    private void ObtenirNumeros(String[][] t) {
        ComparatorPropi c = new ComparatorPropi();
        sl = new ArrayList<Pair<Pair<Integer, Integer>, Integer>> ();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (isNumeric(t[i][j])) {
                    Pair<Integer, Integer> p = new Pair<Integer, Integer> ((Integer) i, (Integer) j);
                    sl.add(new Pair<Pair<Integer, Integer>, Integer>(p, Integer.parseInt(t[i][j])));
                    solucio[i][j] = t[i][j];
                }
                else if (t[i][j].equals("?")) solucio[i][j] = String.valueOf(0);
            }
        }
        Collections.sort(sl, c);
    }

    public String[][] ResoltreHidato (String[][] t, String adjecencia) {
        adj = adjecencia;
        solucio = new String[t.length][t[0].length];
        ObtenirNumeros(t);
        for (int i = 0; i < sl.size()-1; i+=2) {
            if (!TrobarSolucio(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i+1).getKey().getKey(), sl.get(i+1).getKey().getValue(), sl.get(i+1).getValue())) {
                solucio = null;
                break;
            }
        }
        return solucio.clone();
    }
}