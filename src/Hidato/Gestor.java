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

    private Vector<String> parametres;


    public Integer VisualitzaHidatos(){
        return 1;
    }
    public void EscullHidato(Integer nhidato){} //Retorna numero total de hidatos
    public void Aleatori(Vector<String> v){}

    public void Parametres(Vector<String> p){
        parametres = p; //{nomusuari,tipuscela, tipusadj, numfil, numcol}
        partidaactiva.SetNom(p.get(0));
        partidaactiva.SetCela(p.get(1));
        partidaactiva.SetAdjacencia(p.get(2));
        partidaactiva.SetFiles(Integer.parseInt(p.get(3)));
        partidaactiva.SetColumnes(Integer.parseInt(p.get(4)));
        //Aixo ho podriem posar amb una constructora tot junt estil
        //partidaactiva = new Partida(p.get(0),p.get(1),p.get(2),Integer.parseInt(p.get(3)),Integer.parseInt(p.get(4)) )
    }
    public void Generar(String[][] tauler){
        //partidaactiva.SetTauler(); // Haura de ser String[][], es a dir SetTauler(tauler);
        partidaactiva.Generar();
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
