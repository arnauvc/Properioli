package Hidato;

import java.util.ArrayList;
import java.util.*;
import Hidato.Cela;
import Hidato.Tauler;

public class Ajuda {
    private Resolucio r;
    private String[][] matajuda;
    private String[][] mattauler;
    private String[][] matsolucio;

    public String[][] GetAjuda(Tauler t){
        List<List<Cela>> tauler = null;
        tauler = t.getCelas();
        mattauler = new String[t.getNumFiles()][t.getNumColum()]; //El tauler actual
        matajuda = new String[t.getNumFiles()][t.getNumColum()]; //El tauler amb celes incorrectes
        matsolucio = new String[t.getNumFiles()][t.getNumColum()]; //El tauler resolt

        //La idea es comparar el tauler actual amb la resolucio, i la diferencia es la matajuda
        //Esta a mitges

        //matsolucio = r.ResoldreHidato();

        for (int i = 0; i < t.getNumFiles(); ++i) {
            for (int j = 0; j < t.getNumColum(); ++j) {
                mattauler[i][j] = t.consultarValCela(i, j);
            }
        }


        matajuda = mattauler;





        return matajuda;
    }
}
