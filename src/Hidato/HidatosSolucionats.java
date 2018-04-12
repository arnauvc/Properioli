package Hidato;

import Hidato.Tauler;
import Hidato.LlegirEscriure;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class HidatosSolucionats { // Guarda a disc un idhidato, un Tauler, i les solucions

    private BufferedWriter escriptor;
    private Scanner x;
    private LlegirEscriure LE = new LlegirEscriure();

    public void GuardarHidato(Integer idhidato, String[][] t, String[][] solucio) throws Exception {//Te un tauler Tau com a parametre
        String s = new String(String.valueOf(idhidato));
        s.concat(".txt");
        //Afegir el string s al string global que seria el path
        LE.ObrirFitxerEscriptura(s, escriptor, true);
        escriptor.write(String.valueOf(idhidato));escriptor.newLine();
        for (int i = 0; i < t.length; i++) {
            escriptor.newLine();
            for (int j = 0; j < t.length; j++) {
                escriptor.write(t[i][j]);
                if (j != (t[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
        escriptor.newLine();
        for (int i = 0; i < solucio.length; i++) {
            escriptor.newLine();
            for (int j = 0; j < solucio.length; j++) {
                escriptor.write(solucio[i][j]);
                if (j != (solucio[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
    }

    public void GuardarSolucioHidato(Integer idhidato, String[][] solucio) throws Exception {
        String s = new String(String.valueOf(idhidato));
        s.concat(".txt");
        //Afegir el string s al string global que seria el path
        LE.ObrirFitxerEscriptura(s, escriptor, false);
        escriptor.newLine();
        for (int i = 0; i < solucio.length; i++) {
            escriptor.newLine();
            for (int j = 0; j < solucio.length; j++) {
                escriptor.write(solucio[i][j]);
                if (j != (solucio[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
    }

    public String[][] CarregarHidato(Integer id){
        String[][] s = new String[0][];
        return s;
    }
}
