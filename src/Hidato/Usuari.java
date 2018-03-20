package Hidato;

import Hidato.Gestor;
import java.util.ArrayList;

public class Usuari {

    private String nom;
    private ArrayList<Integer> Taulers = new ArrayList<Integer>();


    public String GetNom(){
        return nom;
    }
    public void SetNom(String nom){
        this.nom = nom;
    }
    public void AfegirTaulers (Integer idTauler){
        Taulers.add(idTauler);
    }
}
