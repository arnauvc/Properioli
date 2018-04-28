package Hidato;

import Hidato.Tauler;
import Hidato.Cela;
import Hidato.LlegirEscriure;
import javafx.util.Pair;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class HidatosSolucionats { // Guarda a disc un idhidato, un Tauler, i les solucions

    private BufferedWriter escriptor;
    private Scanner x;
    private LlegirEscriure LE = new LlegirEscriure();
    private String path = new String("C:\\Users\\nilmc\\Desktop\\");

    public void SetPath(String path) {
        this.path = path;
    }

    public void GuardarHidato(Integer idhidato, Tauler t, ArrayList<Pair<Pair<Integer, Integer>, String>> solucio) throws Exception {//Te un tauler Tau com a parametre
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
                escriptor.write(",");
                if (j == t.getNumColum()-1) escriptor.newLine();
            }
        }
        escriptor.write("-");escriptor.newLine();
        for (int i = 0; i < solucio.size(); i++) {
            escriptor.write(String.valueOf(solucio.get(i).getKey().getKey())); escriptor.write(",");   //escribim la i
            escriptor.write(String.valueOf(solucio.get(i).getKey().getValue())); escriptor.write(",");   //escribim la j
            escriptor.write(solucio.get(i).getValue()); //escribim el valor
            escriptor.newLine();
        }
        escriptor.write("-");escriptor.newLine();
        LE.TancarFitxerEscriptura(escriptor);
    }

    public void GuardarSolucioHidato(Integer idhidato, ArrayList<Pair<Pair<Integer, Integer>, String>> solucio) throws Exception {
        String s = new String(path);
        s += String.valueOf(idhidato);
        s += ".txt";
        //Afegir el string s al string global que seria el path
        escriptor = LE.ObrirFitxerEscriptura(s, false);
        for (int i = 0; i < solucio.size(); i++) {
            escriptor.write(String.valueOf(solucio.get(i).getKey().getKey())); escriptor.write(",");   //escribim la i
            escriptor.write(String.valueOf(solucio.get(i).getKey().getValue())); escriptor.write(",");   //escribim la j
            escriptor.write(solucio.get(i).getValue()); //escribim el valor
            escriptor.newLine();
        }
        escriptor.write("-");escriptor.newLine();
        LE.TancarFitxerEscriptura(escriptor);
    }

    public Tauler CarregarHidato(Integer idhidato) throws Exception {
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
        String[][] solucio = new String[f][c];
        int k = 0;
        int l = 0;
        if (x.hasNext()) s = x.nextLine();
        while (!s.equals("-")) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == ',') {
                    solucio[i][k] = s.substring(l, j);
                    k++;
                    l = j+1;
                }
            }
            l = 0;
            k = 0;
            i++;
            if (x.hasNext()) s = x.nextLine();
        }
        Tauler t = new Tauler();
        t.CrearTauler(tcela, adj, solucio);
        LE.TancarFitxerLectura(x);
        return t;
    }

    public ArrayList<Pair<Pair<Integer, Integer>, String>> CarregarSolucio (Integer idhidato, int num_solucio) throws Exception {
        String s = new String(path);
        s += String.valueOf(idhidato);
        s += ".txt";
        x = LE.ObrirFitxerLectura(s);
        boolean trobat = false;
        int i = 0;
        if (x.hasNext()) s = x.nextLine();
        while (!trobat) {
            if (s.equals("-")) i++;
            if (i == num_solucio) trobat = true;
            if (x.hasNext()) s = x.nextLine();
        }
        ArrayList<Pair<Pair<Integer, Integer>, String>> solucio = new ArrayList<Pair<Pair<Integer, Integer>, String>>();
        Pair<Integer, Integer> p = null;
        String valor = null;
        Integer pi = null;
        Integer pj = null;
        int l = 0;
        i = 0;
        while (!s.equals("-")) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == ',') {
                    i++;
                    if (i == 1) {
                        System.out.println("HE TROBAT UNA COMA");
                        pi = Integer.parseInt(s.substring(l, j));
                        System.out.println(pi);
                        l = j+1;
                    }
                    else if (i == 2) {
                        System.out.println("HE TROBAT UNA COMA");
                        pj = Integer.parseInt(s.substring(l, j));
                        System.out.println(pj);
                        l = j+1;
                        valor = new String(s.substring(l));
                        System.out.println(valor);
                    }
                }
            }
            p = new Pair<Integer, Integer>(pi, pj);
            solucio.add(new Pair<Pair<Integer, Integer>, String>(p, valor));
            if (x.hasNext()) s = x.nextLine();
        }
        return solucio;
    }
}
