package Hidato;


import Hidato.Error;
import Hidato.Ranking;
//import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;
import Hidato.Generacio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;


import Hidato.ComprovarHidato;
import javafx.util.Pair;


public class Gestor {

	//private Error e;
	private Ranking r;
    private Partida partidaactiva;/*new Partida()*/;
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
        hs.SetPath(v.get(1));
        Tauler t = hs.CarregarHidato(idhidato);
        //Partida pa = new Partida();
        //pa.SetTauler(t);
        //partidaactiva = pa;

        //Caldra considerar si crea una nova partida o no

        partidaactiva.SetNom(v.get(0));
        partidaactiva.SetTauler(t);
        try{
            partidaactiva.PartidaBiblioteca();
        } catch (Exception e){
            GuardarPartida(v.get(1));
        }

    }
    public void Aleatori(Vector<String> v){
        Partida pa =  new Partida();
        partidaactiva = pa;
        System.out.println(v.get(0));
        partidaactiva.SetNom(v.get(0));
        partidaactiva.SetPath(v.get(1));
        partidaactiva.SetCela(v.get(2));
        partidaactiva.SetAdjacencia(v.get(3));
        partidaactiva.SetDificultat(v.get(4));
        try{
            partidaactiva.IniciaPartida();
        } catch (Exception e){
            GuardarPartida(v.get(1));
        }

    }
    public void Parametres(Vector<String> p){
        //parametres = p; //{nomusuari,tipuscela, tipusadj, numfil, numcol}
        partidaactiva.SetNom(p.get(0));
        partidaactiva.SetCela(p.get(2));
        partidaactiva.SetAdjacencia(p.get(3));
        partidaactiva.SetFiles(Integer.parseInt(p.get(4)));
        partidaactiva.SetColumnes(Integer.parseInt(p.get(5)));
        //Aixo ho podriem posar amb una constructora tot junt estil
        //partidaactiva = new Partida(p.get(0),p.get(1),p.get(2),Integer.parseInt(p.get(3)),Integer.parseInt(p.get(4)) )
    }
    public void Generar(Vector<String> p, String[][] tauler){
        //partidaactiva.SetTauler(tauler); // Haura de ser String[][], es a dir SetTauler(tauler);
        System.out.println("Generar en gestor");
        Partida pa = new Partida();
        partidaactiva = pa;
        partidaactiva.SetNom(p.get(0));
        partidaactiva.SetCela(p.get(2));
        partidaactiva.SetAdjacencia(p.get(3));
        partidaactiva.SetFiles(Integer.parseInt(p.get(4)));
        partidaactiva.SetColumnes(Integer.parseInt(p.get(5)));
        //partidaactiva.SetDificultat(p.get(5));
        partidaactiva.SetTaulerU(tauler); // Haura de ser String[][], es a dir SetTauler(tauler);
        partidaactiva.Generar();//Que s'hauria de dir, RESOLDRELamaquina
        //System.out.println(tsolucion.length);
    }

    public void Reprendre(Vector<String> v) throws Exception {
        pg.SetPath(v.get(1));

        partidaactiva = pg.Obtenirpartida(v.get(0));//NOMES CAL EL NOM DEL USUARI, PERQUE NOMES POT TENIR UNA PARTIDA EN MARXA
        try{
            partidaactiva.ReprendrePartida();
        } catch (Exception e){
            GuardarPartida(v.get(1));
        }
    }
    public void GuardarPartida(String path){
        pg.SetPath(path);
        try {
            System.out.println("Partida guardada");
            pg.GuardarPartida(partidaactiva.GetNom(), partidaactiva, partidaactiva.GetTaulerG());
        } catch (Exception e) {
            System.out.print("Error al guardar la partida");

        }

    }

    public void ActulitzarRanking(Integer id, Double temps){
        r.Actualitzar(id, temps);
    }

    //generar
    public String celasol(Integer f, Integer c){
        return partidaactiva.Celataulersol(f,c);
    }

    public String getcelat(Integer f, Integer c){
        return partidaactiva.Celatauler(f,c);
    }
    //jugar
    public Integer getfila(){
        //System.out.println("estamos en gestor");
        //System.out.println("fila: " + partidaactiva.GetFiles());
        return partidaactiva.GetFiles();
    }

    public Integer getcolumna(){
        //System.out.println("estamos en gestor");
        //System.out.println("columna: " + partidaactiva.GetColumnes());
        return partidaactiva.GetColumnes();
    }

    public void jugar(Integer fila, Integer columna, String elem, String accion) {
            partidaactiva.TranscursPartida(fila,columna,elem,accion);
    }

    public boolean partidafinalitzada(){
        return partidaactiva.GetCompletat();
    }

    //AFEGIT EXTRA PER CONTROLAR PART3
    public void GestorGenerarHidato(String tcela, String tadj, String dif, String Nomu, String Path) {
        Generacio g = new Generacio();
        String[][] tauler;
        tauler = g.GenerarHidato(tcela, tadj, dif);
        //return tauler.clone();
    }

    public String[][] GestorResoldreHidato(String[][] tauler, String tcela, String tadj) {
        Resolucio r = new Resolucio();
        String[][] aux;
        aux = r.ResoltreHidato(tauler, tcela, tadj);
        return aux.clone();
    }

    public void GestorGuardarHidato(String tcela, String tadj, String[][] tauler, ArrayList<Pair<Pair<Integer, Integer>, String>> solucio) {
        Tauler t = new Tauler();
        t.CrearTauler(tcela, tadj, tauler);
        HidatosSolucionats hs = new HidatosSolucionats();
        try {
            hs.GuardarHidato(1,t, solucio); //Canviar l'1 per l'id que toqui
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


}


