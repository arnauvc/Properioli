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
    private String path = new String("C:\\Users\\Nil\\Desktop\\");

    public void GuardarHidato(Integer idhidato, String[][] t, String[][] solucio) throws Exception {//Te un tauler Tau com a parametre
        String s = new String(String.valueOf(idhidato));
        s += ".txt";
        path += s;
        System.out.println(path);
        //Afegir el string s al string global que seria el path
        LE.ObrirFitxerEscriptura(path, escriptor, true);
        escriptor.write(String.valueOf(idhidato));escriptor.newLine();
        for (int i = 0; i < t.length; i++) {
            escriptor.newLine();
            for (int j = 0; j < t.length; j++) {
                escriptor.write(t[i][j]);
                if (j != (t[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
        escriptor.write("-"); escriptor.newLine();
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
        s += ".txt";
        path += s;
        //Afegir el string s al string global que seria el path
        LE.ObrirFitxerEscriptura(path, escriptor, false);
        escriptor.write("-");escriptor.newLine();
        for (int i = 0; i < solucio.length; i++) {
            escriptor.newLine();
            for (int j = 0; j < solucio.length; j++) {
                escriptor.write(solucio[i][j]);
                if (j != (solucio[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
    }

    public String[][] CarregarHidato(Integer idhidato, int num_solucio) throws Exception {
        int i = 0;
        String s = new String(String.valueOf(idhidato));
        s += ".txt";
        path += s;
        LE.ObrirFitxerLectura(path, x);
        boolean trobat = false;
        if (x.hasNext()) s = x.nextLine();
        while (!trobat) {
            if (s == "-") i++;
            if (i == num_solucio) trobat = true;
            if (x.hasNext()) s = x.nextLine();
        }
        String[][] solucio = new String[0][];
        i = 0;
        int k = 0;
        while (s != "-") {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != ',') {
                    solucio[i][k] = String.valueOf(s.charAt(j));
                    k++;
                }
            }
            if (x.hasNext()) s = x.nextLine();
        }
        return solucio;
    }
}
