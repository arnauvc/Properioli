package Hidato;

import java.util.*;
import java.io.*;
import Hidato.Partida;
import Hidato.Gestor;
import Hidato.LlegirEscriure;

public class PartidesGuardades {

    private BufferedWriter escriptor;
    private Scanner x;
    private LlegirEscriure LE = new LlegirEscriure();
    private String path = new String("C:\\Users\\nilmc\\Desktop\\");


    public void GuardarPartida(String nom_usuari, Partida p, String[][] Tauler) throws Exception {
        String s = new String(path);
        s += nom_usuari;
        s += "_";
        s += String.valueOf(p.GetIdHidato());
        escriptor = LE.ObrirFitxerEscriptura(s, true);
        try {
            escriptor.write(p.GetCela());escriptor.newLine();
            escriptor.write(p.GetAdjacencia());escriptor.newLine();
            escriptor.write(p.GetFiles());escriptor.newLine();
            escriptor.write(p.GetColumnes());escriptor.newLine();
            escriptor.write(p.GetDificultat());escriptor.newLine();
            escriptor.write(p.GetTorn());escriptor.newLine();
            //escriptor.write(p.GetMaxim());escriptor.newLine();
            for (int i = 0; i < Tauler.length; i++) {
                escriptor.newLine();
                for (int j = 0; j < Tauler[i].length; j++) {
                    escriptor.write(Tauler[i][j]);
                    if (j != (Tauler[i].length-1)) escriptor.write(",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LE.TancarFitxerEscriptura(escriptor);
    }

    public Partida Obtenirpartida(String nom_usuari, Integer idhidato) throws Exception {
        String s = new String(path);
        s += nom_usuari;
        s += "_";
        s += String.valueOf(idhidato);
        x = LE.ObrirFitxerLectura(s);
        Partida p = new Partida();
        String llegir;
        if (x.hasNext()) {
            llegir = new String(x.nextLine());
        } else return null;
        p.SetCela(llegir);
        if (x.hasNext()) {
            llegir = new String(x.nextLine());
        } else return null;
        p.SetAdjacencia(llegir);
        if (x.hasNext()) {
            llegir = new String(x.nextLine());
        } else return null;
        p.SetFiles(Integer.parseInt(llegir));
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetColumnes(Integer.parseInt(llegir));
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetDificultat(llegir);
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetTorn(Integer.parseInt(llegir));
        /*if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetMaxim(Integer.parseInt(llegir));*/
        int i = 0;
        Integer f = p.GetFiles();
        Integer c = p.GetColumnes();
        String [][] Tauler = new String[f][c];  //Ha de ser String[f][c]
        int l = 0;
        while (x.hasNext()) {
            llegir = x.next();
            l = 0;
            for (int j = 0; j < llegir.length(); j++) {
                if (llegir.charAt(j) == ',') {
                    Tauler[i][j] = llegir.substring(l, j-1);
                    l = j+1;
                }
            }
            i++;
        }
        p.SetTaulerU(Tauler);
        LE.TancarFitxerLectura(x);
        return p;
    }
}
