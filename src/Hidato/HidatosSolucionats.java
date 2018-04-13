package Hidato;

import Hidato.Tauler;
import Hidato.Cela;
import Hidato.LlegirEscriure;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class HidatosSolucionats { // Guarda a disc un idhidato, un Tauler, i les solucions

    private BufferedWriter escriptor;
    private Scanner x;
    private LlegirEscriure LE = new LlegirEscriure();
    private String path = new String("C:\\Users\\nilmc\\Desktop\\");

    public void GuardarHidato(Integer idhidato, Tauler t, String[][] solucio) throws Exception {//Te un tauler Tau com a parametre
        String s = new String(path);
        s += String.valueOf(idhidato);
        s += ".txt";
        List<List<Cela>> tauler = null;
        //Afegir el string s al string global que seria el path
        escriptor = LE.ObrirFitxerEscriptura(s, true);
        escriptor.write(String.valueOf(idhidato));
        escriptor.newLine();
        escriptor.write(t.getTipuscela());
        escriptor.newLine();
        escriptor.write(t.GetTiposAdj());
        escriptor.newLine();
        escriptor.write(t.getNumFiles());
        escriptor.newLine();
        escriptor.write(t.getNumColum());
        escriptor.newLine();
        tauler = t.getCelas();
        for (int i = 0; i < t.getNumFiles(); i++) {
            for (int j = 0; j < t.getNumColum(); j++) {
                escriptor.write((tauler.get(i).get(j)).getValor());
                if (j != t.getNumColum()) escriptor.write(",");
                else escriptor.newLine();
            }
        }
        escriptor.write("-");escriptor.newLine();
        for (int i = 0; i < solucio.length; i++) {
            for (int j = 0; j < solucio.length; j++) {
                escriptor.write(solucio[i][j]);
                if (j != (solucio[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
        escriptor.write("-");escriptor.newLine();
        LE.TancarFitxerEscriptura(escriptor);
    }

    public void GuardarSolucioHidato(Integer idhidato, String[][] solucio) throws Exception {
        String s = new String(path);
        s += String.valueOf(idhidato);
        s += ".txt";
        //Afegir el string s al string global que seria el path
        escriptor = LE.ObrirFitxerEscriptura(s, false);
        for (int i = 0; i < solucio.length; i++) {
            for (int j = 0; j < solucio.length; j++) {
                escriptor.write(solucio[i][j]);
                if (j != (solucio[i].length-1)) escriptor.write(",");
                else escriptor.newLine();
            }
        }
        escriptor.write("-");escriptor.newLine();
        LE.TancarFitxerEscriptura(escriptor);
    }

    public Tauler CarregarHidato(Integer idhidato, int num_solucio) throws Exception {
        String s = new String(path);
        String tcela = new String();
        String adj = new String();
        Integer f = null;
        Integer c = null;
        s += String.valueOf(idhidato);
        s += ".txt";
        x = LE.ObrirFitxerLectura(s);
        boolean trobat = false;
        if (x.hasNext()) s = x.nextLine();
        if (x.hasNext()) {
            s = x.nextLine();
            tcela = s;
        }
        if (x.hasNext()) {
            s = x.nextLine();
            adj = s;
        }
        if (x.hasNext()) {
            s = x.nextLine();
            f = new Integer(String.valueOf(s));
        }
        if (x.hasNext()) {
            s = x.nextLine();
            c = new Integer(String.valueOf(s));
        }
        int i = 0;
        while (!trobat) {
            if (s.equals("-")) i++;
            if (i == num_solucio) trobat = true;
            if (x.hasNext()) s = x.nextLine();
        }
        String[][] solucio = new String[f][c];
        i = 0;
        int k = 0;
        while (!s.equals("-")) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != ',') {
                    solucio[i][k] = String.valueOf(s.charAt(j));
                    k++;
                }
            }
            i++;
            if (x.hasNext()) s = x.nextLine();
            System.out.print(s);
            System.out.print(" ");
        }
        Tauler t = new Tauler();
        t.CrearTauler(tcela, adj, solucio);
        LE.TancarFitxerLectura(x);
        return t;
    }
}
