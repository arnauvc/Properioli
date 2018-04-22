package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.util.*;

public class Resolucio{

    private String[][] solucio;
    private String adj = new String();
    private ArrayList<Pair<Pair<Integer, Integer>, Integer>> sl;
    private ArrayList<ArrayList<ArrayList<Pair<Integer, Integer>>>> multicamins; //per tots els camins entre tots els cumeros, multicamins<camins>
    private ArrayList<ArrayList<Pair<Integer, Integer>>> camins; //per tots els camins entre x i y, camins<cami>
    private int espais;
    private Iterator<ArrayList<ArrayList<Pair<Integer, Integer>>>> itrm;
    private Iterator<Pair<Pair<Integer, Integer>, Integer>> itrsl;
    private ArrayList<Pair<Integer, Integer>> cami; //per cada cami entre x i y, cami<Pair<Integer,Integer>>

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(String.valueOf(cadena));
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    /*private boolean TrobarSolucio(Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        //System.out.print(distancia);System.out.print(" ");System.out.print(valor1);System.out.print(" ");System.out.print(valor2);
        //System.out.println("");
        if (distancia == 1) {
            if (valor1 == valor2-1) {
                k++;
                System.out.println(espais);
                System.out.println((""));
                if (espais == 0) {
                    System.out.println("HOLAAAA");
                    return true;
                }
                int distancia_aux = sl.get(k+1).getValue() - sl.get(k).getValue();
                System.out.println(distancia_aux);
                System.out.println((""));
                if (!TrobarSolucio(sl.get(k).getKey().getKey(), sl.get(k).getKey().getValue(), sl.get(k).getValue(), sl.get(k+1).getKey().getKey(), sl.get(k+1).getKey().getValue(), sl.get(k+1).getValue(), distancia_aux)) return false;
                else return true;
                for (int f = 0; f < camins.size(); f++) {
                    for (int l = 0; l < camins.get(f).size(); l++) {
                        System.out.print(camins.get(f).get(l).getKey()); System.out.print(",");
                        System.out.print(camins.get(f).get(l).getValue());System.out.print(" ");
                    }
                    System.out.println("");
                }
                System.out.println("--------------------------");
                camins.add(cami);
            }
            return false;
        }
        else {
            if (i2 <= i) {
                //System.out.println(i);
                if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                    solucio[i - 1][j] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                    distancia -= 1;
                    espais--;
                    if (TrobarSolucio(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    espais++;
                    solucio[i - 1][j] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                }
                if (j2 < j) {
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j - 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                    }
                    if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j + 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i - 1][j - 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j < solucio[0].length-1) && solucio[i - 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i - 1][j + 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                        }
                    }
                }
                else {
                    if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j + 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>(i, j+1));
                    }
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j - 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j < solucio[0].length-1) && solucio[i - 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i - 1][j + 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i - 1][j - 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                        }
                    }
                }
                if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                    solucio[i + 1][j] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                    distancia -= 1;
                    espais--;
                    if (TrobarSolucio(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    espais++;
                    solucio[i + 1][j] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                }
            }
            if (i2 >= i) {
                if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                    solucio[i + 1][j] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                    distancia -= 1;
                    espais--;
                    if (TrobarSolucio(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    espais++;
                    solucio[i + 1][j] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                }
                if (j2 < j) {
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j - 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                    }
                    if ( j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j + 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i + 1][j - 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j < solucio[0].length-1 ) && solucio[i + 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i + 1][j + 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                        }
                    }
                }
                else {
                    if ( j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                        solucio[i][j + 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j + 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                    }
                    if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                        solucio[i][j - 1] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                        distancia -= 1;
                        espais--;
                        if (TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                        distancia += 1;
                        espais++;
                        solucio[i][j - 1] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j < solucio[0].length-1 ) && solucio[i + 1][j + 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i + 1][j + 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                        }
                    }
                    if (adj.equals("CA")) {
                        if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                            solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                            cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                            distancia -= 1;
                            espais--;
                            if (TrobarSolucio(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return true;
                            distancia += 1;
                            espais++;
                            solucio[i + 1][j - 1] = String.valueOf(0);
                            cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                        }
                    }
                }
                if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                    solucio[i - 1][j] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                    distancia -= 1;
                    espais--;
                    if (TrobarSolucio(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return true;
                    distancia += 1;
                    espais++;
                    solucio[i - 1][j] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                }
            }
        }
        return true;
    }*/

    private boolean TrobarSolucio (Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        for (int f = 0; f < solucio.length; f++) {
            for (int l = 0; l < solucio[f].length; l++) {
                System.out.print(solucio[f][l]);System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------");
        if (distancia == 1) {
            System.out.println("HOLA JODER");
            if (valor1 == valor2-1) {
                camins.add(cami);
                return true;
            }
            return false;
        }
        else {
            if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                solucio[i - 1][j] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                distancia -= 1;
                espais--;
                if (!TrobarSolucio(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                espais++;
                solucio[i - 1][j] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
            }
            if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                solucio[i][j + 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                distancia -= 1;
                espais--;
                if (!TrobarSolucio(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                espais++;
                solucio[i][j + 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
            }
            if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                solucio[i][j - 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                distancia -= 1;
                espais--;
                if (!TrobarSolucio(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                espais++;
                solucio[i][j - 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
            }
            if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                solucio[i + 1][j] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                distancia -= 1;
                espais--;
                if (!TrobarSolucio(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                espais++;
                solucio[i + 1][j] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
            }
            if (adj.equals("CA")) {
                if((i < solucio.length-1 && j < solucio[0].length-1 ) && solucio[i + 1][j + 1].equals(String.valueOf(0))) {
                    solucio[i + 1][j + 1] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                    distancia -= 1;
                    espais--;
                    if (!TrobarSolucio(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                    distancia += 1;
                    espais++;
                    solucio[i + 1][j + 1] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j+1));
                }
                if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                    solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                    distancia -= 1;
                    espais--;
                    if (!TrobarSolucio(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                    distancia += 1;
                    espais++;
                    solucio[i + 1][j - 1] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                }
                if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                    solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                    distancia -= 1;
                    espais--;
                    if (!TrobarSolucio(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                    distancia += 1;
                    espais++;
                    solucio[i - 1][j - 1] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                }
                if((i > 0 && j < solucio[0].length-1) && solucio[i - 1][j + 1].equals(String.valueOf(0))) {
                    solucio[i - 1][j + 1] = String.valueOf(valor1 + 1);
                    cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                    distancia -= 1;
                    espais--;
                    if (!TrobarSolucio(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                    distancia += 1;
                    espais++;
                    solucio[i - 1][j + 1] = String.valueOf(0);
                    cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j+1));
                }
            }
        }
        return true;
    }

    private void ObtenirNumeros(String[][] t) {
        ComparatorPropi c = new ComparatorPropi();
        sl = new ArrayList<Pair<Pair<Integer, Integer>, Integer>> ();
        espais = 0;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (isNumeric(t[i][j])) {
                    Pair<Integer, Integer> p = new Pair<Integer, Integer> ((Integer) i, (Integer) j);
                    sl.add(new Pair<Pair<Integer, Integer>, Integer>(p, Integer.parseInt(t[i][j])));
                    solucio[i][j] = t[i][j];
                }
                else if (t[i][j].equals("?")) {
                    solucio[i][j] = String.valueOf(0);
                    espais++;
                }
                else if (t[i][j].equals(("#"))) solucio[i][j] = String.valueOf("-1");
                else if (t[i][j].equals(("*"))) solucio[i][j] = String.valueOf("-2");
            }
        }
        Collections.sort(sl, c);
    }

    private boolean Emplenar(ArrayList<Pair<Integer, Integer>> posicions, Integer valor) {
        for (int i = 0; i < posicions.size(); i++) {
            if (solucio[posicions.get(i).getKey()][posicions.get(i).getValue()] == String.valueOf(0)) {
                solucio[posicions.get(i).getKey()][posicions.get(i).getValue()] = String.valueOf(valor);
                valor++;
            }
            else {
                return false;
            }
        }
        return true;
    }

    private void Buidar(ArrayList<Pair<Integer, Integer>> posicions) {
        for (int i = 0; i < posicions.size(); i++) {
            solucio[posicions.get(i).getKey()][posicions.get(i).getValue()] = String.valueOf(0);
        }
    }

    private void LaBona(Integer valor, Iterator<ArrayList<ArrayList<Pair<Integer, Integer>>>> it, Iterator<Pair<Pair<Integer, Integer>, Integer>> itsl) {
        if (it.hasNext() && itsl.hasNext()) {
            Integer valor_aux = itsl.next().getValue();
            ArrayList<ArrayList<Pair<Integer, Integer>>> llista = it.next();
            System.out.println(llista.size());
            System.out.println("....................");
            for (int i = 0; i < llista.size(); i++) {
                if (Emplenar(llista.get(i), valor)) {
                    LaBona(valor_aux, it, itsl);
                }
                else Buidar(llista.get(i));
            }
        }
        else return;
    }

    public String[][] ResoltreHidato (String[][] t, String adjacencia) {
        adj = adjacencia;
        solucio = new String[t.length][t[0].length];
        ObtenirNumeros(t);
        int distancia = 0;
        multicamins = new ArrayList<ArrayList<ArrayList<Pair<Integer, Integer>>>>();
        camins = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
        cami = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < sl.size()-1; i++) {
            distancia = sl.get(i+1).getValue() - sl.get(i).getValue();
            cami = new ArrayList<Pair<Integer, Integer>>();
            camins = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
            if (distancia != 1) {
                if (!TrobarSolucio(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i + 1).getKey().getKey(), sl.get(i + 1).getKey().getValue(), sl.get(i + 1).getValue(), distancia)) {
                    solucio = null;
                    System.out.println("NO HE TROBAT SOLUCIO");
                    System.out.println("");
                    break;
                }
                else {
                    /*for (int f = 0; f < camins.size(); f++) { //No imprimeix res pero comprovo el tamany de camins i es el correcte
                        for (int l = 0; l < camins.get(f).size(); l++) {
                            System.out.print(camins.get(f).get(l).getKey());
                            System.out.print(",");
                            System.out.print(camins.get(f).get(l).getValue());
                            System.out.println("");
                        }
                        System.out.println("CANVI DE LINIA");
                    }*/
                    System.out.println(camins.size());
                    System.out.println("'''''''''''''''''''''''''''''''''''");
                    multicamins.add(camins);
                }
            }
        }
        System.out.println(multicamins.size());
        System.out.println("???????????????????????????????");
        if (solucio != null) {
            itrm = multicamins.iterator();
            itrsl = sl.iterator();
            if (itrm.hasNext() && itrsl.hasNext()) {
                LaBona((itrsl.next().getValue()) + 1, itrm, itrsl);
            }
        }
        /*for (int i = 0; i < multicamins.size(); i++) {
            for (int j = 0; j < multicamins.get(i).size(); j++) {
                for (int k = 0; k < multicamins.get(i).get(j).size(); k++) {
                    System.out.print(multicamins.get(i).get(j).get(k).getKey());
                    System.out.print(",");
                    System.out.print(multicamins.get(i).get(j).get(k).getValue());
                    System.out.println("");
                }
            }
            System.out.println("----");
        }*/
        return solucio;
    }
}