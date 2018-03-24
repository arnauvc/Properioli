package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Error;

import java.io.ObjectInputStream;
import java.util.Scanner;


/*
1. setNomUsuari
2. Seleccionar tipus cela
3. Seleccionar tipus Adj
4. Seleccionar dificultat
5. getHidato -> Generar Hidato (Classe Generacio)
6. IniciaPartida() (i rellotge)
-------------------------------------------------------------------------------------
7. getJugada
8. Comprovar jugada (ha utilitzat ajuda?, ha guardat?, es invalida?, es incorrecte?)
8.1. Si ajuda -> getAjuda (classe Ajuda, stop rellotge
8.2. Si guardat -> Partida en pausa, i es guarda la partida, stop rellotge
8.3. Si invalida -> mostrar error(?) i anular jugada
8.4. Si incorrecte -> mostrar missatge de casella incorrecte
9. Comprovar finalitzada
10. Si finalitzada == false, GO TO 6
-------------------------------------------------------------------------------------
11. finalitzada == true -> stop_rellotge
12. getPuntuacio si (ajuda && guardat) == false
13. passar info al Gestor o algo semblant...
 */

public class Partida {
    //Si els dos booleans seguents estan a fals, el temps servira pel ranking, sino no.
    private boolean ajuda;//Ha fet servir ajuda en algun moment de la partida
    private boolean guardat;//Ha guardat la partida en algun moment
    private boolean finalitzat;
    private double  temps;
    private Integer puntuacio;
	private Rellotge r = new Rellotge();
	private Error e = new Error();
	private Integer idhidato;
	private String tipuscela;
	private String tipusadj;
	private String dif;
	private String nom;

	private void UsageCela(){
		System.out.println("Usage: String {TRIANGLE, QUADRAT, HEXAGON}");
	}
	private void UsageAdjacencia(){
		System.out.println("Usage: String {VERTEX, COSTAT}");
	}
	private void UsageDificultat(){
		System.out.println("Usage: String {FACIL, NORMAL, DIFICIL}");
	}

	
	public void IniciaPartida(){
		//Configuracio de Partida
		Scanner input = new Scanner(System.in);

		System.out.println("Insereix el teu nom: ");
		SetNom(input.nextLine());

		System.out.println("Selecciona el tipus de cela: ");
		SetCela(input.nextLine());

		while (!tipuscela.equals("TRIANGLE") && !tipuscela.equals("QUADRAT") && !tipuscela.equals("HEXAGON")) {
			UsageCela();
			System.out.println("Selecciona el tipus de cela: ");
			SetCela(input.nextLine());
		}

		if (tipuscela.equals("QUADRAT")){
			System.out.println("Selecciona el tipus de adjacencia: ");
			SetAdjacencia(input.nextLine());
			while (!tipusadj.equals("COSTAT") && !tipusadj.equals("VERTEX")) {
				UsageAdjacencia();
				System.out.println("Selecciona el tipus de adjacencia: ");
				SetAdjacencia(input.nextLine());
			}

		}
		else SetAdjacencia("COSTAT");

		System.out.println("Selecciona la dificultat: ");
		SetDificultat(input.nextLine());

		while (!dif.equals("FACIL") && !dif.equals("NORMAL") && !dif.equals("DIFICIL")) {
			UsageDificultat();
			System.out.println("Selecciona la dificultat: ");
			SetDificultat(input.nextLine());
		}

		//Aqui faltaria un Hidato h = new Hidato(tipuscela, tipusadj, dif) o algo semblant
		r.start();//Inicia el rellotge
		finalitzat = false;
		ajuda = false;
		puntuacio = 0;

	}



	public void TranscursPartida(){
		Jugada j = new Jugada();
		int cont = 5; //Contador temporal de jugades mentre no tenim hidato programat

		while (!finalitzat){
			j.DoJugada();
			j.GetJugada();
			if (j.GetJugada().equals("NUMERO")){
				j.GetNumero();
				j.GetX();
				j.GetY();
				//Alguna cosa com pasarli les dades a tauler o algo, ja se m'acudira
				//En algun punt de la partida ja no quedaran mes numeros per colocar,
				//llavors finalitzat = true, ara faig una xapusa per testejar tot
				--cont;
				if (GetDificultat().equals("FACIL")) puntuacio += 5;
				else if (GetDificultat().equals("NORMAL")) puntuacio += 10;
				else puntuacio += 20;
				if (cont == 0) finalitzat = true;
			}
			else if (j.GetJugada().equals("GUARDAR")){
				r.stop();
				guardat = true;
				//Passar partida a partides guardades (?), de moment farem que acabi
				finalitzat = true;
				System.out.println("Has guardat la partida.");
			}
			else if (j.GetJugada().equals("AJUDA") && !ajuda){
				r.stop();
				ajuda = true;
				//GetAjuda
				System.out.println("Ajuda? A pensar una mica home.");
			}
			else if (j.GetJugada().equals("AJUDA") && ajuda) {
				System.out.println("Ja no pots demanar mes ajudes.");
			}

		}

	}

	public void AcabaPartida(){
		r.stop();
		temps = r.GetTime();
		if (ajuda || guardat) puntuacio = 0;

		System.out.printf("Has guanyat! Puntuacio: %d\n", puntuacio);

	}
	public double GetTemps(){
		return temps;
	}

	public Integer GetIdHidato(){
		return idhidato;
	}

	public void SetCela(String tipuscela){
		this.tipuscela = tipuscela;
	}

	public String GetCela(){
		return tipuscela;
	}

	public void SetAdjacencia(String tipusadj){
		this.tipusadj = tipusadj;
	}

	public String GetAdjacencia(){
		return tipusadj;
	}

	public void SetDificultat(String dif){
		this.dif = dif;
	}

	public String GetDificultat(){
		return dif;
	}

	public void SetNom(String nom) {
		this.nom = nom;
	}

	public String GetNom(){
		return nom;
	}

	public Integer GetPuntuacio(){
		return puntuacio;
	}
}
