package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Error;

import java.io.ObjectInputStream;
import java.util.Scanner;

/*
Necessito:
Per a iniciaPartida():
	- Generacio d'Hidato automaticament
	- Generacio d'Hidato manualment

Per a transcursPartida():
	- Crida al Tauler de l'Hidato per a cada jugada
	- Crida per a guardar la partida (passar la partida a PartidesGuardades)
	- Resolucio de l'Hidato (algorisme de resolucio)
 */


public class Partida {

    private boolean ajuda; //Ha fet servir ajuda en algun moment de la partida
    private boolean guardat; //Ha guardat la partida en algun moment
    private boolean finalitzat; //Ha acabat la partida
    private double  temps;
    private Integer puntuacio;
	private Rellotge r = new Rellotge();
	private Error e = new Error();
	private Integer idhidato;
	private String tipuscela;
	private String tipusadj;
	private String dif; //Dificultat
	private String nom; //Nom jugador
	private String tipuspartida;


	private void UsageCela(){
		System.out.println("Usage: String {T(Triangle), Q(Quadrat), H(Hexagon)}");
	}
	private void UsageAdjacencia(){
		System.out.println("Usage: String {C(Costat), CA(Costat+Angle)}");
	}
	private void UsageDificultat(){
		System.out.println("Usage: String {FACIL, NORMAL, DIFICIL}");
	}
	private void UsageTipusPartida(){
		System.out.println("Usage: String {GENERAR, RESOLDRE}");
	}

	
	public void IniciaPartida(){
		//Configuracio de Partida
		Scanner input = new Scanner(System.in);

		System.out.println("Benvingut a Hidato!");
		System.out.println("Selecciona si vols RESOLDRE o GENERAR un Hidato: ");
		SetTipusPartida(input.nextLine());

		while (!tipuspartida.equals("RESOLDRE") && !tipuspartida.equals("GENERAR")) {
			UsageTipusPartida();
			System.out.println("Selecciona si vols RESOLDRE o GENERAR un Hidato: ");
			SetTipusPartida(input.nextLine());
		}

		if (GetTipusPartida().equals("RESOLDRE")){
			System.out.println("Insereix el teu nom: ");
			SetNom(input.nextLine());

			System.out.println("Selecciona el tipus de cela: ");
			SetCela(input.nextLine());

			while (!tipuscela.equals("T") && !tipuscela.equals("Q") && !tipuscela.equals("H")) {
				UsageCela();
				System.out.println("Selecciona el tipus de cela: ");
				SetCela(input.nextLine());
			}

			if (tipuscela.equals("Q")){
				System.out.println("Selecciona el tipus de adjacencia: ");
				SetAdjacencia(input.nextLine());
				while (!tipusadj.equals("C") && !tipusadj.equals("CA")) {
					UsageAdjacencia();
					System.out.println("Selecciona el tipus de adjacencia: ");
					SetAdjacencia(input.nextLine());
				}

			}
			else SetAdjacencia("C");

			System.out.println("Selecciona la dificultat: ");
			SetDificultat(input.nextLine());

			while (!dif.equals("FACIL") && !dif.equals("NORMAL") && !dif.equals("DIFICIL")) {
				UsageDificultat();
				System.out.println("Selecciona la dificultat: ");
				SetDificultat(input.nextLine());
			}

			/*
			Aqui faltaria un Hidato h = new Hidato(tipuscela, tipusadj, dif)
			I segons dif, el tamany de l'Hidato sera mes gran o mes petit
			*/

			r.start(); //Inicia el rellotge
			finalitzat = false;
			ajuda = false;
			puntuacio = 0;
		}

		else if (GetTipusPartida().equals("GENERAR")){

			System.out.println("Selecciona el tipus de cela: ");
			SetCela(input.nextLine());

			while (!tipuscela.equals("T") && !tipuscela.equals("Q") && !tipuscela.equals("H")) {
				UsageCela();
				System.out.println("Selecciona el tipus de cela: ");
				SetCela(input.nextLine());
			}

			if (tipuscela.equals("Q")){
				System.out.println("Selecciona el tipus de adjacencia: ");
				SetAdjacencia(input.nextLine());
				while (!tipusadj.equals("C") && !tipusadj.equals("CA")) {
					UsageAdjacencia();
					System.out.println("Selecciona el tipus de adjacencia: ");
					SetAdjacencia(input.nextLine());
				}

			}
			else SetAdjacencia("C");

			/*
			SetTauler(tipuscela, tipusadj, nfil, ncol) o algo semblant de manera manual
			seguint les pautes de l'enunciat
			*/
			System.out.println("Hidato Generat!");

		}

	}

	public void TranscursPartida(){
		if (GetTipusPartida().equals("RESOLDRE")){
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

		else if (GetTipusPartida().equals("GENERAR")){
			System.out.println("Hola soc la IA i resolc Hidatos");
			//GetResolucio(); I aixo ja dira si es pot resoldre o no
		}




	}

	public void AcabaPartida(){
		if (GetTipusPartida().equals("RESOLDRE")){
			r.stop();
			temps = r.GetTime();
			System.out.printf("Has trigat: %f\n", temps);
			if (ajuda || guardat) puntuacio = 0;

			System.out.printf("Has guanyat! Puntuacio: %d\n", puntuacio);
		}

		else if (GetTipusPartida().equals("GENERAR")){
			System.out.println("Hidato resolt!");
			//El missatge variara segons si es pot resoldre o no
			//En aquest cas no cal guardar puntuacio ni res

		}

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
	public void SetTipusPartida(String tipuspartida){
		this.tipuspartida = tipuspartida;
	}
	public String GetTipusPartida(){
		return tipuspartida;
	}
}
