package Hidato;

import Hidato.Gestor;
import Hidato.Partida;
import java.util.ArrayList;
import java.util.HashMap;


public class PartidesGuardades {
    private HashMap<String, ArrayList<Partida>> Partides; //Implementar-ho amb un ArrayList<pair<Partida,Tauler>>

    public void GuardarPartida(String usuari, Partida p) {
        if (Partides.containsKey(ususari)) (Partides.get(usuari)).add(p);
        else {
            ArrayList<Partida> a = new ArrayList();
            a.add(p)
            Partides.put(usuari, a);
        }
    }

    public Partida Obtenirpartida(String usuari, Integer id) {
        if (Partides.containsKey(ususari)) {
            for (int i = 0; i < (Partides.get(usuari)).size(); i++) {
                if (((Partides.get(usuari)).get(i)) == id) {
                    return (Partides.get(usuari)).get(i);
                }
            }
        }
        else {
            Partida p = new Partida();
            //p.Setid = 0;
            return p;
        }
    }
}
