package Hidato;

import java.util.ArrayList;
import java.util.*;
import Hidato.Cela;
import Hidato.Tauler;

public class Ajuda {
    private String[][] matajuda;
    private String[][] mattauler;
    private String[][] matsolucio;

    public String[][] GetAjuda(Tauler t, String[][] hidato){
        List<List<Cela>> tauler = null;
        tauler = t.getCelas();
        mattauler = new String[t.getNumFiles()][t.getNumColum()]; //El tauler actual
        matajuda = new String[t.getNumFiles()][t.getNumColum()]; //El tauler amb celes incorrectes
        matsolucio = hidato; //El tauler resolt

        
        matajuda = mattauler;
        for (int i = 0; i < t.getNumFiles(); i++){
            for (int j = 0; j < t.getNumColum(); j++){
                mattauler[i][j] = t.consultarValCela(i, j);
                if (!mattauler[i][j].equals("*") && !mattauler[i][j].equals("#") && !mattauler[i][j].equals("?")) {
                    if (!mattauler[i][j].equals(matsolucio[i][j])) matajuda[i][j] += "I";
                }
            }
        }

        return matajuda;
    }
}
