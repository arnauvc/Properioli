package Hidato;

import Hidato.Tauler;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
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

    private boolean TrobarSolucio(Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        //System.out.println(distancia);
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                System.out.print(solucio[k][l]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("--------------------------");
        if (distancia == 1) {
            if (valor1 == valor2-1) return true;
            return false;
        }
        else {
            if (i2 <= i) {
                //System.out.println(i);
                if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                    solucio[i - 1][j] = String.valueOf(valor1 + 1);
                    distancia -= 1;
                    if (TrobarSolucio(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    solucio[i - 1][j] = String.valueOf(0);
                }
                if (j2 < j) {
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j - 1] = String.valueOf(0);
                    }
                    if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j + 1] = String.valueOf(0);
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i - 1][j - 1] = String.valueOf(0);
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j < solucio[0].length-1) && solucio[i - 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i - 1][j + 1] = String.valueOf(0);
                        }
                    }
                }
                else {
                    if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j + 1] = String.valueOf(0);
                    }
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j - 1] = String.valueOf(0);
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j < solucio[0].length-1) && solucio[i - 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i - 1][j + 1] = String.valueOf(0);
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i - 1][j - 1] = String.valueOf(0);
                        }
                    }
                }
            }
            else {
                if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                    solucio[i + 1][j] = String.valueOf(valor1 + 1);
                    distancia -= 1;
                    if (TrobarSolucio(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    solucio[i + 1][j] = String.valueOf(0);
                }
                if (j2 < j) {
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j - 1] = String.valueOf(0);
                    }
                    if ( j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j + 1] = String.valueOf(0);
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i + 1][j - 1] = String.valueOf(0);
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j < solucio[0].length-1 ) && solucio[i + 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i + 1][j + 1] = String.valueOf(0);
                        }
                    }
                }
                else {
                    if ( j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j + 1] = String.valueOf(0);
                    }
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        distancia -= 1;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        solucio[i][j - 1] = String.valueOf(0);
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j < solucio[0].length-1 ) && solucio[i + 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i + 1][j + 1] = String.valueOf(0);
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                            distancia -= 1;
                            if (TrobarSolucio(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            solucio[i + 1][j - 1] = String.valueOf(0);
                        }
                    }
                }
            }
            /*if (i2 <= i) {
                solucio[i - 1][j] = String.valueOf(valor1 + 1);
                if (!TrobarSolucio(i - 1, j, valor1 + 1, i2, j2, valor2, distancia--)) {
                    solucio[i - 1][j] = String.valueOf(0);
                    if (j2 < j) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        if (!TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                            solucio[i][j - 1] = String.valueOf(0);
                            if (adj.equals("CA")) {
                                solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                                if (!TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                                    solucio[i - 1][j - 1] = String.valueOf(0);
                                }
                            }
                        }
                    }
                    else if (j2 > j) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        if (!TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                            solucio[i][j + 1] = String.valueOf(0);
                            if (adj.equals("CA")) {
                                solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                                if (!TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                                    solucio[i - 1][j + 1] = String.valueOf(0);
                                }
                            }
                        }
                    }
                }
            }
            else {
                solucio[i + 1][j] = String.valueOf(valor1 + 1);
                if (!TrobarSolucio(i + 1, j, valor1 + 1, i2, j2, valor2, distancia--)) {
                    solucio[i + 1][j] = String.valueOf(0);
                    if (j2 < j) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        if (!TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                            solucio[i][j - 1] = String.valueOf(0);
                            if (adj.equals("CA")) {
                                solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                                if (!TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                                    solucio[i - 1][j - 1] = String.valueOf(0);
                                }
                            }
                        }
                    }
                    else if (j2 > j) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        if (!TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                            solucio[i][j + 1] = String.valueOf(0);
                            if (adj.equals("CA")) {
                                solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                                if (!TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia--)) {
                                    solucio[i + 1][j + 1] = String.valueOf(0);
                                }
                            }
                        }
                    }
                }
            }*/
        }
        return false;
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
                else if (t[i][j].equals(("#"))) solucio[i][j] = String.valueOf("-1");
                else if (t[i][j].equals(("*"))) solucio[i][j] = String.valueOf("-2");
            }
        }
        Collections.sort(sl, c);
    }

    public String[][] ResoltreHidato (String[][] t, String adjacencia) {
        adj = adjacencia;
        solucio = new String[t.length][t[0].length];
        ObtenirNumeros(t);
        int distancia;
        for (int i = 0; i < sl.size()-1; i+=2) {
            distancia = sl.get(i+1).getValue() - sl.get(i).getValue();
            if (!TrobarSolucio(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i+1).getKey().getKey(), sl.get(i+1).getKey().getValue(), sl.get(i+1).getValue(), distancia)) {
                solucio = null;
                break;
            }
        }
        /*for (int i = 0; i < sl.size(); i++) {
            System.out.print(String.valueOf(sl.get(i).getKey().getKey()));
            System.out.print(" ");
            System.out.print(String.valueOf(sl.get(i).getKey().getValue()));
            System.out.print(" ");
            System.out.print(String.valueOf(sl.get(i).getValue()));
            System.out.print(" ");
        }*/
        return solucio;
    }
}