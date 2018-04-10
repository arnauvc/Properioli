package Hidato;

import java.util.*;
import java.io.*;
import Hidato.Partida;
import Hidato.Gestor;

public class PartidesGuardades {

    private BufferedWriter escriptor;
    private Scanner x;

    private boolean ObrirFitxerEscriptura (String nom) {//Nom ha de ser idHidato.txt
        File fitxer = new File(nom);
        if (!fitxer.exists()) {
            try {
                fitxer.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter filew = new FileWriter(fitxer);
            escriptor = new BufferedWriter(filew);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean TancarFitxerEscriptura() {
        try {
            escriptor.close();
        } catch (FileNotFoundException e) {
            System.out.println("No es pot tencar");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean ObrirFitxerLectura (String nom) {//Nom ha de ser idHidato.txt
        try {
            x = new Scanner(new File(nom));
        } catch (FileNotFoundException e) {
            System.out.println("No ho trobo");
            return false; //No existeix el fitxer
        }
        return true;
    }

    private boolean TancarFitxerLectura() {
        try {
            x.close();
        } catch (FileNotFoundException e) {
            System.out.println("No ho trobo");
            return false;
        }
        return true;
    }



    public void GuardarPartida(String nom_fitxer, Partida p, String[][] Tauler) {
        ObrirFitxerEscriptura(nom_fitxer);
        try {
            escriptor.write(p.GetCela());escriptor.newLine();
            escriptor.write(p.GetAdjacencia());escriptor.newLine();
            //escriptor.write(p.GetFiles);escriptor.newLine();
            //escriptor.write(p.GetColumnes);escriptor.newLine();
            escriptor.write(p.GetDificultat());escriptor.newLine();
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
    }

    public Partida Obtenirpartida(String nom_fitxer) {
        if (!ObrirFitxerLectura(nom_fitxer)) return null;
        Partida p = new Partida();
        String llegir;
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetCela(llegir);
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetAdjacencia(llegir);
        /*if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetFiles(String.valueOf(llegir.charAt(5)));
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetColumnes(String.valueOf(llegir.charAt(7)));
        if (x.hasNext()) {
            llegir = new String(x.next());
        } else return null;
        p.SetDificultat(String.valueOf(llegir.charAt(9)));*/
        int i = 0;
        //Integer f = Integer.parseInt(p.GetFiles);
        //Integer c = Integer.parseInt(p.GetColumnes);
        String [][] Tauler = new String[3][4];  //Ha de ser String[f][c]
        while (x.hasNext()) {
            llegir = x.next();
            for (int j = 0; j < llegir.length(); j++) {
                if (llegir.charAt(i) != ',') Tauler[i][j] = String.valueOf(llegir.charAt(i));
            }
        }
        //p.SetTauler(Tauler);
        return p;
    }
}
