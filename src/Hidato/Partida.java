package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import java.util.Scanner;


/*
1. Seleccionar tipus cela
2. Seleccionar tipus Adj
3. Seleccionar dificultat
4. getHidato -> Generar Hidato (Classe Generacio)
-------------------------------------------------------------------------------------
5. IniciaPartida() (i rellotge)
6. getJugada
7. Comprovar jugada (ha utilitzat ajuda?, ha guardat?, es invalida?, es incorrecte?)
7.1. Si ajuda -> getAjuda (classe Ajuda)
7.2. Si guardat -> Partida en pausa, i es guarda la partida
7.3. Si invalida -> mostrar error(?) i anular jugada
7.4. Si incorrecte -> mostrar missatge de casella incorrecte
8. Comprovar finalitzada
9. Si finalitzada == false, GO TO 6
-------------------------------------------------------------------------------------
10. finalitzada == true -> stop_rellotge
11. setNomUsuari
12. getPuntuacio si (ajuda && guardat) == false
13. passar info al Gestor o algo semblant...
 */

public class Partida {
    private enum Dificultat{
        EASY, MEDIUM, HARD
    }
    private Dificultat d;
    //Si els dos booleans seguents estan a fals, el temps servira pel ranking, sino no.
    private boolean ajuda;//Ha fet servir ajuda en algun moment de la partida
    private boolean guardat;//Ha guardat la partida en algun moment
    private boolean finalitzat;
    private double  temps;
	private Rellotge r = new Rellotge();
	private Integer idhidato;
	private String tipuscela;
	private String tipusadj;
	
	public void IniciaPartida(){
		//Aqui fa coses
		Scanner input = new Scanner(System.in);
		System.out.println("Selecciona el tipus de cela: ");
		tipuscela = input.nextLine();
		if (tipuscela.equals("QUADRAT") || tipuscela.equals("TRIANGLE")){
			System.out.println("Selecciona si vols adjacencia per VERTEX o COSTAT: ");
			tipusadj = input.nextLine();
		}
		else tipusadj = "COSTAT";

		System.out.println("Selecciona la dificultat: ");
		d = Dificultat.valueOf(input.nextLine());

	}



	public void TranscursPartida(){
		r.start();//Inicia el rellotge
	}

	public void AcabaPartida(){
		r.stop();
		temps = r.GetTime();
		System.out.println("Has guanyat!");
		//Aqui fa coses tambe
	}
	public double GetTemps(){
		return temps;
	}

	public Integer GetIdHidato(){
		return idhidato;
	}

	public String GetCela(){
		return tipuscela;
	}

	public String GetAdjacencia(){
		return tipusadj;
	}

	public Dificultat GetDificultat(){
		return d;
	}

}
