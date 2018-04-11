package Hidato;


import Hidato.Error;
import Hidato.Ranking;
//import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;
import Hidato.Generacio;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;


import Hidato.ComprovarHidato;


public class Gestor {

	private Error e;
	private Ranking r;

    //Podem eliminar aquest usuari actiu potser
    //private Usuari usuariactiu;//Conte l'objecte usuari del usuari actiu en el sistema
    private Partida partidaactiva = new Partida();
    //private Tauler tauler;
    private PartidesGuardades pg = new PartidesGuardades();


    public Integer VisualitzaHidatos(){
        return 1;
    }
    public void EscullHidato(Integer nhidato){} //Retorna numero total de hidatos
    public void Aleatori(Vector<String> v){}
    public void Generar(Vector<String> v){
        partidaactiva.SetNom(v.get(0));
        //partidaactiva.Generar();
    }
    public void Reprendre(Vector<String> v){
        /*
        partidaactiva = pg.Obtenirpartida(nomusuari);//NOMES CAL EL NOM DEL USUARI, PERQUE NOMES POT TENIR UNA PARTIDA EN MARXA
        partidaactiva.TranscursPartida();
        */
    }
    public void GuardarPartida(){

    }
    public void ActulitzarRanking(Integer id, Double temps){
        r.Actualitzar(id, temps);
    }

    public void Jugar(){}



}
