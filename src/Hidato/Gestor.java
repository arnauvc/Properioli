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

	//private Error e;
	private Ranking r;
    private Partida partidaactiva = new Partida();
    private PartidesGuardades pg = new PartidesGuardades();
    private HidatosSolucionats hs = new HidatosSolucionats();

    private Vector<String> parametres;

    public Integer VisualitzaHidatos() {
        //Aqui caldra invocar la capa de presentacio per fer display dels hidatos
        //I retornara el nombre total d'hidatos
        return 5;
    }
    public void JugarHidato(Vector<String> v , Integer idhidato) throws Exception {
        //Es selecciona de la biblioteca l'hidato amb id nhidato
        Tauler t = hs.CarregarHidato(idhidato);
        //Partida pa = new Partida();
        //pa.SetTauler(t);
        //partidaactiva = pa;

        //Caldra considerar si crea una nova partida o no

        partidaactiva.SetNom(v.get(0));
        partidaactiva.SetTauler(t);
        partidaactiva.PartidaBiblioteca();
    }
    public void Aleatori(Vector<String> v){
        Partida p = new Partida();
        p.SetCela(v.get(1));
        p.SetAdjacencia(v.get(2));
        p.SetDificultat(v.get(3));
        p.IniciaPartida();
        partidaactiva = p;
    }
    public void Parametres(Vector<String> p){
        //parametres = p; //{nomusuari,tipuscela, tipusadj, numfil, numcol}
        partidaactiva.SetNom(p.get(0));
        partidaactiva.SetCela(p.get(1));
        partidaactiva.SetAdjacencia(p.get(2));
        partidaactiva.SetFiles(Integer.parseInt(p.get(3)));
        partidaactiva.SetColumnes(Integer.parseInt(p.get(4)));
        //Aixo ho podriem posar amb una constructora tot junt estil
        //partidaactiva = new Partida(p.get(0),p.get(1),p.get(2),Integer.parseInt(p.get(3)),Integer.parseInt(p.get(4)) )
    }
    public void Generar(Vector<String> p, String[][] tauler){
        //partidaactiva.SetTauler(tauler); // Haura de ser String[][], es a dir SetTauler(tauler);
        Partida pa = new Partida();
        partidaactiva = pa;
        partidaactiva.SetNom(p.get(0));
        partidaactiva.SetCela(p.get(1));
        partidaactiva.SetAdjacencia(p.get(2));
        partidaactiva.SetFiles(Integer.parseInt(p.get(3)));
        partidaactiva.SetColumnes(Integer.parseInt(p.get(4)));
        //partidaactiva.SetDificultat(p.get(5));
        //partidaactiva.SetTauler(tauler); // Haura de ser String[][], es a dir SetTauler(tauler);
        partidaactiva.Generar();//Que s'hauria de dir, RESOLDRELamaquina
    }

    /**
     * Recupera la partida que l'ususari tingui guardada
     * @param v Conte el nom de l'usuari a la posicio 0
     * @throws Exception Si l'usuari no te cap partida guardada
     */
    public void Reprendre(Vector<String> v) throws Exception {
        partidaactiva = pg.Obtenirpartida(v.get(0),1);//NOMES CAL EL NOM DEL USUARI, PERQUE NOMES POT TENIR UNA PARTIDA EN MARXA
        partidaactiva.ReprendrePartida();
    }
    public void GuardarPartida(){

    }
    public void ActulitzarRanking(Integer id, Double temps){
        r.Actualitzar(id, temps);
    }
}
