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
    private String path = new String("/home/marc/FIB/");

    public void SetPath(String path) {
        this.path = path;
    }

    public void GuardarPartida(String nom_usuari, Partida p, String[][] Tauler) throws Exception {
        String s = new String(path);
        s += nom_usuari;
        escriptor = LE.ObrirFitxerEscriptura(s, true);
        try {
            escriptor.write((p.GetNom()));escriptor.newLine();
            escriptor.write(p.GetCela());escriptor.newLine();
            escriptor.write(p.GetAdjacencia());escriptor.newLine();
            escriptor.write(Integer.toString(p.GetFiles()));escriptor.newLine();
            escriptor.write(Integer.toString(p.GetColumnes()));escriptor.newLine();
            escriptor.write(p.GetDificultat());escriptor.newLine();
            escriptor.write(Integer.toString(p.GetTorn()));escriptor.newLine();
            escriptor.write(Integer.toString(p.GetMaxim()));escriptor.newLine();
            for (int i = 0; i < Tauler.length; i++) {

                for (int j = 0; j < Tauler[i].length; j++) {
                    escriptor.write(Tauler[i][j]);
                    if (j != (Tauler[i].length-1)) escriptor.write(",");
                }
                escriptor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LE.TancarFitxerEscriptura(escriptor);
    }

    public Partida Obtenirpartida(String nom_usuari) {
        String s = new String(path);
        s += nom_usuari;
        x = LE.ObrirFitxerLectura(s);
        Partida p = new Partida();
        String llegir;
        if (x.hasNext()) {
            llegir = new String(x.nextLine());
        } else return null;
        p.SetNom(llegir);
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
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetMaxim(Integer.parseInt(llegir));

        //int i = 0;
        Integer f = p.GetFiles();
        Integer c = p.GetColumnes();
        String [][] Tauler = new String[f][c];  //Ha de ser String[f][c]
        int l = 0;
        x.nextLine();
        for(int i = 0; i < f; i++) {
            String hidato[] = x.nextLine().split(",");

            for (int j = 0; j < c; j++) {
                Tauler[i][j] = hidato[j];
            }

        }
        /* AIXO NO VA! EL DE DALT SI

        while (x.hasNext()) {
            llegir = x.next();
            System.out.println("QUEEEEE");
            l = 0;
            for (int j = 0; j < llegir.length(); j++) {
                Tauler[i][j] = llegir;
                System.out.println(Tauler[i][j]);
                if (llegir.charAt(j) == ',') {

                    Tauler[i][j] = llegir.substring(l, j-1);
                    l = j+1;
                }
            }
            i++;
        }*/

        p.SetTaulerU(Tauler);
        LE.TancarFitxerLectura(x);
        return p;
    }
}