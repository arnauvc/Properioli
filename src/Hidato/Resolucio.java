package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.io.BufferedWriter;
import java.util.*;

public class Resolucio{

    private String[][] solucio;
    private String adj = new String();
    private String tcela = new String();
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

    private boolean EsCorrecte(Integer valor, int f, int c) {

        if (f < solucio.length-1) {
            if (solucio[f+1][c].equals(String.valueOf(valor))) return true;
        }
        if (f > 0) {
            if (solucio[f-1][c].equals(String.valueOf(valor))) return true;
        }
        if (c > 0) {
            if (solucio[f][c-1].equals(String.valueOf(valor))) return true;
        }
        if (c < solucio[0].length-1) {
            if (solucio[f][c+1].equals(String.valueOf(valor))) return true;
        }
        if (f > 0 && c > 0) {
            if (solucio[f-1][c-1].equals(String.valueOf(valor))) return true;
        }
        if (f < solucio.length-1 && c < solucio[0].length-1) {
            if (solucio[f+1][c+1].equals(String.valueOf(valor))) return true;
        }
        if (f < solucio.length-1 && c > 0) {
            if (solucio[f+1][c-1].equals(String.valueOf(valor))) return true;
        }
        if (f > 0 && c < solucio[0].length-1) {
            if (solucio[f-1][c+1].equals(String.valueOf(valor))) return true;
        }
        return false;
    }

    private boolean EsCorrecteH(Integer valor, int f, int c) {
        if (f < solucio.length-1) {
            if (solucio[f+1][c].equals(String.valueOf(valor))) return true;
        }
        if (f > 0) {
            if (solucio[f-1][c].equals(String.valueOf(valor))) return true;
        }
        if (c > 0) {
            if (solucio[f][c-1].equals(String.valueOf(valor))) return true;
        }
        if (c < solucio[0].length-1) {
            if (solucio[f][c+1].equals(String.valueOf(valor))) return true;
        }
        if (f > 0 && c > 0) {
            if (solucio[f-1][c-1].equals(String.valueOf(valor))) return true;
        }
        if (f < solucio.length-1 && c > 0) {
            if (solucio[f+1][c-1].equals(String.valueOf(valor))) return true;
        }
        return false;
    }

    private boolean EsCorrecteT(Integer valor, int f, int c) {
        if (f%2 == 0) {
            if(c%2 == 0) {
                if (f < solucio.length-1) {
                    if (solucio[f+1][c].equals(String.valueOf(valor))) return true;
                }
            }
            else {
                if (f > 0) {
                    if (solucio[f-1][c].equals(String.valueOf(valor))) return true;
                }
            }
        }
        else {
            if(c%2 != 0) {
                if (f < solucio.length-1) {
                    if (solucio[f+1][c].equals(String.valueOf(valor))) return true;
                }
            }
            else {
                if (f > 0) {
                    if (solucio[f-1][c].equals(String.valueOf(valor))) return true;
                }
            }
        }
        if (c > 0) {
            if (solucio[f][c-1].equals(String.valueOf(valor))) return true;
        }
        if (c < solucio[0].length-1) {
            if (solucio[f][c+1].equals(String.valueOf(valor))) return true;
        }
        return false;
    }

    private boolean TrobarSolucioT (Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        /*for (int f = 0; f < solucio.length; f++) {
            for (int l = 0; l < solucio[f].length; l++) {
                System.out.print(solucio[f][l]);System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------");*/
        if (distancia == 1) {
            if (valor1 == valor2-1) {
                if (EsCorrecteT(valor2, cami.get(cami.size()-1).getKey(), cami.get(cami.size()-1).getValue())) {
                    camins.add((ArrayList<Pair<Integer, Integer>>) cami.clone());
                    /*System.out.println("CAMI:");
                    for (int f = 0; f < cami.size(); f++) {
                        System.out.print(cami.get(f).getKey() + "," + cami.get(f).getValue() + " ");
                    }
                    System.out.println();
                    System.out.println("CAMINS QUE PORTEM:");
                    for (int f = 0; f < camins.size(); f++) {
                        for (int l = 0; l < camins.get(f).size(); l++) {
                            System.out.print(camins.get(f).get(l).getKey() + "," + camins.get(f).get(l).getValue() + " ");
                        }
                        System.out.println();
                    }*/
                }
                //System.out.println();
                return true;
            }
            return false;
        }
        else {
            if (i%2 == 0) {
                if (j%2 == 0) {
                    if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                        solucio[i + 1][j] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                        distancia -= 1;
                        if (!TrobarSolucioT(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                        distancia += 1;
                        solucio[i + 1][j] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                    }
                }
                else {
                    if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                        solucio[i - 1][j] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                        distancia -= 1;
                        if (!TrobarSolucioT(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                        distancia += 1;
                        solucio[i - 1][j] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                    }
                }
            }
            else {
                if (j%2 != 0) {
                    if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                        solucio[i + 1][j] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                        distancia -= 1;
                        if (!TrobarSolucioT(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                        distancia += 1;
                        solucio[i + 1][j] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                    }
                }
                else {
                    if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                        solucio[i - 1][j] = String.valueOf(valor1 + 1);
                        cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                        distancia -= 1;
                        if (!TrobarSolucioT(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                        distancia += 1;
                        solucio[i - 1][j] = String.valueOf(0);
                        cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                    }
                }
            }

            if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                solucio[i][j + 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                distancia -= 1;
                if (!TrobarSolucioT(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i][j + 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
            }
            if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                solucio[i][j - 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                distancia -= 1;
                if (!TrobarSolucioT(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i][j - 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
            }
        }
        return true;
    }

    private boolean TrobarSolucioH (Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        /*for (int f = 0; f < solucio.length; f++) {
            for (int l = 0; l < solucio[f].length; l++) {
                System.out.print(solucio[f][l]);System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------");*/
        if (distancia == 1) {
            if (valor1 == valor2-1) {
                if (EsCorrecteH(valor2, cami.get(cami.size()-1).getKey(), cami.get(cami.size()-1).getValue())) {
                    camins.add((ArrayList<Pair<Integer, Integer>>) cami.clone());
                    /*System.out.println("CAMI:");
                    for (int f = 0; f < cami.size(); f++) {
                        System.out.print(cami.get(f).getKey() + "," + cami.get(f).getValue() + " ");
                    }
                    System.out.println();
                    System.out.println("CAMINS QUE PORTEM:");
                    for (int f = 0; f < camins.size(); f++) {
                        for (int l = 0; l < camins.get(f).size(); l++) {
                            System.out.print(camins.get(f).get(l).getKey() + "," + camins.get(f).get(l).getValue() + " ");
                        }
                        System.out.println();
                    }*/
                }
                //System.out.println();
                return true;
            }
            return false;
        }
        else {
            if (i > 0 && solucio[i - 1][j].equals(String.valueOf(0))) {
                solucio[i - 1][j] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
                distancia -= 1;
                if (!TrobarSolucioH(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i - 1][j] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j));
            }
            if (j < solucio[0].length-1 && solucio[i][j + 1].equals(String.valueOf(0))) {
                solucio[i][j + 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
                distancia -= 1;
                if (!TrobarSolucioH(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i][j + 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j+1));
            }
            if (j > 0 && solucio[i][j - 1].equals(String.valueOf(0))) {
                solucio[i][j - 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
                distancia -= 1;
                if (!TrobarSolucioH(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i][j - 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i, (Integer)j-1));
            }
            if ((i < solucio.length-1) && (solucio[i + 1][j].equals(String.valueOf(0)))) {
                solucio[i + 1][j] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
                distancia -= 1;
                if (!TrobarSolucioH(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i + 1][j] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j));
            }
            if((i < solucio.length-1 && j > 0 ) && solucio[i + 1][j - 1].equals(String.valueOf(0))) {
                solucio[i + 1][j - 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
                distancia -= 1;
                if (!TrobarSolucioH(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i + 1][j - 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i+1, (Integer)j-1));
            }
            if((i > 0 && j > 0) && solucio[i - 1][j - 1].equals(String.valueOf(0))) {
                solucio[i - 1][j - 1] = String.valueOf(valor1 + 1);
                cami.add(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
                distancia -= 1;
                if (!TrobarSolucioH(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
                distancia += 1;
                solucio[i - 1][j - 1] = String.valueOf(0);
                cami.remove(new Pair<Integer, Integer>((Integer)i-1, (Integer)j-1));
            }
        }
        return true;
    }

    private boolean TrobarSolucioQ (Integer i, Integer j, Integer valor1, Integer i2, Integer j2, Integer valor2, int distancia) {
        /*for (int f = 0; f < solucio.length; f++) {
            for (int l = 0; l < solucio[f].length; l++) {
                System.out.print(solucio[f][l]);System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------");*/
        if (distancia == 1) {
            if (valor1 == valor2-1) {
                if (EsCorrecte(valor2, cami.get(cami.size()-1).getKey(), cami.get(cami.size()-1).getValue())) {
                    camins.add((ArrayList<Pair<Integer, Integer>>) cami.clone());
                    /*System.out.println("CAMI:");
                    for (int f = 0; f < cami.size(); f++) {
                        System.out.print(cami.get(f).getKey() + "," + cami.get(f).getValue() + " ");
                    }
                    System.out.println();
                    System.out.println("CAMINS QUE PORTEM:");
                    for (int f = 0; f < camins.size(); f++) {
                        for (int l = 0; l < camins.get(f).size(); l++) {
                            System.out.print(camins.get(f).get(l).getKey() + "," + camins.get(f).get(l).getValue() + " ");
                        }
                        System.out.println();
                    }*/
                }
                //System.out.println();
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
                if (!TrobarSolucioQ(i - 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                if (!TrobarSolucioQ(i, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                if (!TrobarSolucioQ(i, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                if (!TrobarSolucioQ(i + 1, j, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                    if (!TrobarSolucioQ(i + 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                    if (!TrobarSolucioQ(i + 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                    if (!TrobarSolucioQ(i - 1, j - 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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
                    if (!TrobarSolucioQ(i - 1, j + 1, valor1 + 1, i2, j2, valor2, distancia)) return false;
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

    private Pair<Boolean,ArrayList<Pair<Integer, Integer>>> Emplenar(ArrayList<Pair<Integer, Integer>> posicions, Integer valor) {
        //System.out.println("ELS NUMEROS SON.... ");
        ArrayList<Pair<Integer, Integer>> aux = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 1; i < posicions.size(); i++) {
            //System.out.print("(");System.out.print(posicions.get(i).getKey());System.out.print(",");System.out.print(posicions.get(i).getValue());System.out.print(")");System.out.print(solucio[posicions.get(i).getKey()][posicions.get(i).getValue()]); System.out.print(" ");
            if (solucio[posicions.get(i).getKey()][posicions.get(i).getValue()].equals(String.valueOf(0))) {
                aux.add(new Pair<>(posicions.get(i).getKey(), posicions.get(i).getValue()));
                solucio[posicions.get(i).getKey()][posicions.get(i).getValue()] = String.valueOf(valor);
                //System.out.print("(");System.out.print(posicions.get(i).getKey());System.out.print(",");System.out.print(posicions.get(i).getValue());System.out.print(")");System.out.print(solucio[posicions.get(i).getKey()][posicions.get(i).getValue()]); System.out.print(" ");
                valor++;
            }
            else return new Pair<Boolean, ArrayList<Pair<Integer, Integer>>>(false, (ArrayList<Pair<Integer, Integer>>) aux.clone());
        }
        return new Pair<Boolean, ArrayList<Pair<Integer, Integer>>>(true, (ArrayList<Pair<Integer, Integer>>) aux.clone());
    }

    private void Buidar(ArrayList<Pair<Integer, Integer>> posicions) {
        for (int i = 0; i < posicions.size(); i++) {
            solucio[posicions.get(i).getKey()][posicions.get(i).getValue()] = String.valueOf(0);
        }
    }

    private boolean LaBona(int j) {
        if (j == multicamins.size()) return true;
        ArrayList<ArrayList<Pair<Integer, Integer>>> llista = (ArrayList<ArrayList<Pair<Integer, Integer>>>) multicamins.get(j).clone();
        //System.out.println(llista.size());
        //System.out.println("....................");
        Integer valor = Integer.valueOf(solucio[llista.get(0).get(0).getKey()][llista.get(0).get(0).getValue()]);
        ArrayList<Pair<Integer, Integer>> posicions_ocupades;
        Pair<Boolean, ArrayList<Pair<Integer, Integer>>> aux;
        for (int i = 0; i < llista.size(); i++) {
            aux = Emplenar(llista.get(i), valor+1);
            posicions_ocupades = aux.getValue();
            if (aux.getKey()) {
                /*System.out.println("TAMANY POSICIONS OCUPADES: " + posicions_ocupades.size());
                System.out.println("HE EMPLENAT EL CAMI....");
                for (int k = 0; k < posicions_ocupades.size(); k++) {
                        System.out.print(posicions_ocupades.get(k).getKey());
                        System.out.print(",");
                        System.out.print(posicions_ocupades.get(k).getValue());
                }*/
                j=j+1;
                if (!LaBona(j)) {
                    //System.out.println("AIXO NO VA");
                    j=j-1;
                    /*for (int k = 0; k < posicions_ocupades.size(); k++) {
                        System.out.print(posicions_ocupades.get(k).getKey());
                        System.out.print(",");
                        System.out.print(posicions_ocupades.get(k).getValue());
                        System.out.print(" ");
                    }*/
                    Buidar(posicions_ocupades);
                }
                else return true;
            }
            else {
                /*System.out.println("AIXO VA BUID");
                for (int k = 0; k < posicions_ocupades.size(); k++) {
                    System.out.print(posicions_ocupades.get(k).getKey());
                    System.out.print(",");
                    System.out.print(posicions_ocupades.get(k).getValue());
                    System.out.print(" ");
                }*/
                Buidar(posicions_ocupades);
            }
        }
        return false;
    }

    public String[][] ResoltreHidato (String[][] t, String tcela, String adjacencia) {
        adj = adjacencia;
        this.tcela = tcela;
        System.out.println(t.length);
        solucio = new String[t.length][t[0].length];
        System.out.println(solucio.length);
        ObtenirNumeros(t);
        int distancia = 0;
        multicamins = new ArrayList<ArrayList<ArrayList<Pair<Integer, Integer>>>>();
        camins = new ArrayList<ArrayList<Pair<Integer, Integer>>>();
        cami = new ArrayList<Pair<Integer, Integer>>();
        if (tcela.equals("Q")) {
            for (int i = 0; i < sl.size() - 1; i++) {
                distancia = sl.get(i + 1).getValue() - sl.get(i).getValue();
                cami.clear();
                camins.clear();
                if (distancia != 1) {
                    cami.add(new Pair<>(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue()));
                    TrobarSolucioQ(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i + 1).getKey().getKey(), sl.get(i + 1).getKey().getValue(), sl.get(i + 1).getValue(), distancia);
                    if (camins.isEmpty()) {
                        solucio = null;
                        //System.out.println("NO HE TROBAT SOLUCIO");
                        //System.out.println("");
                        break;
                    } else {
                        multicamins.add((ArrayList<ArrayList<Pair<Integer, Integer>>>) camins.clone());
                    }
                }
            }
        }
        else if (tcela.equals("H")) {
            for (int i = 0; i < sl.size() - 1; i++) {
                distancia = sl.get(i + 1).getValue() - sl.get(i).getValue();
                cami.clear();
                camins.clear();
                if (distancia != 1) {
                    cami.add(new Pair<>(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue()));
                    TrobarSolucioH(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i + 1).getKey().getKey(), sl.get(i + 1).getKey().getValue(), sl.get(i + 1).getValue(), distancia);
                    if (camins.isEmpty()) {
                        solucio = null;
                        //System.out.println("NO HE TROBAT SOLUCIO");
                        //System.out.println("");
                        break;
                    } else {
                        multicamins.add((ArrayList<ArrayList<Pair<Integer, Integer>>>) camins.clone());
                    }
                }
            }
        }
        else if (tcela.equals("T")) {
            for (int i = 0; i < sl.size() - 1; i++) {
                distancia = sl.get(i + 1).getValue() - sl.get(i).getValue();
                cami.clear();
                camins.clear();
                if (distancia != 1) {
                    cami.add(new Pair<>(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue()));
                    TrobarSolucioT(sl.get(i).getKey().getKey(), sl.get(i).getKey().getValue(), sl.get(i).getValue(), sl.get(i + 1).getKey().getKey(), sl.get(i + 1).getKey().getValue(), sl.get(i + 1).getValue(), distancia);
                    if (camins.isEmpty()) {
                        solucio = null;
                        //System.out.println("NO HE TROBAT SOLUCIO");
                        //System.out.println("");
                        break;
                    } else {
                        multicamins.add((ArrayList<ArrayList<Pair<Integer, Integer>>>) camins.clone());
                    }
                }
            }
        }
        if (solucio != null) {
            if (!LaBona(0)) System.out.println("NO TROBO SOL");
        }
        return solucio;
    }

    public String consultarsolucio(Integer f, Integer c){
       return solucio[f][c];
    }

}