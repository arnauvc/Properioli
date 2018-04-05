package Hidato;

import Hidato.Rellotge;
import Hidato.Error;
import Hidato.Ranking;
import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;
import java.util.Iterator;
import java.util.TreeSet;



/////////
import Hidato.ComprovarHidato;


public class Gestor {

	private Error e;
	private Ranking ranking;

    //Podem eliminar aquest usuari actiu potser
    private Usuari usuariactiu;//Conte l'objecte usuari del usuari actiu en el sistema
    private Partida partidaactiva;
    private Tauler tauler;
    private PartidesGuardades pg;

	public void IniciaJoc(){
	    partidaactiva.IniciaPartida();

	}
    public void CarregaPartida(){

    }
    public void GuardarPartida(){

    }

    public void ActulitzarRanking(){

    }
	public void Test(){



        ComprovarHidato ch = new ComprovarHidato();
        if (!ch.Comprovar("C:\\Users\\Nil\\IdeaProjects\\PROP\\src\\com\\company\\Hidato\\prova.txt")) {
            System.out.println("Aixo no funciona Hulio");
        }
        else System.out.println("Aixo funciona Hulio");

		//Fi test
	}


}
