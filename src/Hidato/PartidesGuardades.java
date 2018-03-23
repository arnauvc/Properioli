package Hidato;

import java.util.ArrayList;
import java.util.HashMap;
import Hidato.Partida;
import Hidato.Gestor



public class PartidesGuardades {
    private HashMap<String, ArrayList<Partida>> Partides; //Implementar-ho amb un ArrayList<pair<Partida,Tauler>>

    public void GuardarPartida(String usuari, Partida p) {
        if (Partides.containsKey(usuari)) (Partides.get(usuari)).add(p);
        else {
            ArrayList<Partida> a = new ArrayList();
            a.add(p);
            Partides.put(usuari, a);
        }
    }

    public Partida Obtenirpartida(String usuari, Integer id) {
        if (Partides.containsKey(usuari)) {
            for (int i = 0; i < (Partides.get(usuari)).size(); i++) {
                if (((Partides.get(usuari)).get(i).GetIdHidato()) == id) {
                    return (Partides.get(usuari)).get(i);
                }
            }
        }
        return null;
    }
}
