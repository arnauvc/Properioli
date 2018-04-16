package Hidato;

import java.util.List;

public class Ajuda {
    private Resolucio r;
    private Tauler t;
    private String[][] matajuda;
    private String[][] mattauler;

    public String[][] GetAjuda(){
        PassarAMatriu();


        return matajuda;
    }

    private void PassarAMatriu() {
        List<List<Cela>> tauler = null;
        tauler = t.getCelas();
        for (int i = 0; i < t.getNumFiles(); i++) {
            for (int j = 0; j < t.getNumColum(); j++) {
                mattauler[i][j] = (tauler.get(i).get(j)).getValor();
            }
        }
    }
}
