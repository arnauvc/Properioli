package Hidato;

import Hidato.Rellotge;
import Hidato.Error;
import Hidato.Ranking;
//import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;



/////////
import Hidato.ComprovarHidato;


public class Gestor {

	private Error e;
	private Ranking ranking;

    //Podem eliminar aquest usuari actiu potser
    private Usuari usuariactiu;//Conte l'objecte usuari del usuari actiu en el sistema
    private Partida partidaactiva = new Partida();
    //private Tauler tauler;
    private PartidesGuardades pg = new PartidesGuardades();

    //Test
    //Test del master al branch

    public void JugarPartida(){

        Scanner input = new Scanner(System.in);

        System.out.println("Benvingut a Hidato!");
        System.out.println("Insereix el teu nom: ");
        String nomusuari = input.nextLine();

        System.out.println("Selecciona si vols RESOLDRE, GENERAR o REPRENDRE un Hidato: ");
        String tipuspartida = input.nextLine();

        while (!tipuspartida.equals("RESOLDRE") && !tipuspartida.equals("GENERAR") && !tipuspartida.equals("REPRENDRE")) {
            System.out.println("Selecciona si vols RESOLDRE,GENERAR o REPRENDRE un Hidato: ");
            tipuspartida = input.nextLine();
        }

        if (tipuspartida.equals("RESOLDRE")) {
            System.out.println("Vols carregar un hidato de la BIBLIOTECA o un ALEATORI? ");
            String tipushidato = input.nextLine();
            while(!tipushidato.equals("BIBLIOTECA") && !tipushidato.equals("ALEATORI")) {
                tipushidato = input.nextLine();
            }
            if(tipushidato.equals("BIBLIOTECA")){
                //HidatosSolucionats
            }
            else if (tipushidato.equals("ALEATORI")){
                partidaactiva.IniciaPartida();
            }

        }
	    else if (tipuspartida.equals("GENERAR")){
            //partidaactiva.Generar();
	    }
        else if(tipuspartida.equals("REPRENDRE")){
            pg.Obtenirpartida(nomusuari, 1);//Nomes cal el nom, no li caldria la resta
        }



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
