package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Error;
import Hidato.Gestor;

import java.util.Scanner;


public class Partida {

    private boolean ajuda; //Ha fet servir ajuda en algun moment de la partida
    private boolean guardat; //Ha guardat la partida en algun moment
    private boolean finalitzat; //Ha parat la partida
	private boolean completat; //Ha acabat la partida
    private double  temps;
	private Rellotge r = new Rellotge();
	private Error e = new Error();
	private Tauler t = new Tauler();
	private Generacio g = new Generacio();
	private Gestor ge;
	private Integer idhidato;
	private String dif; //Dificultat
	private Integer torn;
	private Integer fil;
	private Integer col;



	private void UsageDificultat(){
		System.out.println("Usage: String {FACIL, NORMAL, DIFICIL}");
	}
	private void UsageCela(){
		System.out.println("Usage: String {T(Triangle), Q(Quadrat), H(Hexagon)}");
	}
	private void UsageAdjacencia(){
		System.out.println("Usage: String {C(Costat), CA(Costat+Angle)}");
	}

	public void Generar(){
		Scanner input = new Scanner(System.in);
		String tcela, tadj;

		//Falta interactuar con el usuario para tcela tadj

		String hidato[][];

		System.out.println("Selecciona el tipus de cela: ");
		tcela = (input.nextLine());

		while (!tcela.equals("T") && !tcela.equals("Q") && !tcela.equals("H")) {
			UsageCela();
			System.out.println("Selecciona el tipus de cela: ");
			tcela = (input.nextLine());
		}

		if (tcela.equals("Q")){
			System.out.println("Selecciona el tipus de adjacencia: ");
			tadj = (input.nextLine());
			while (!tadj.equals("C") && !tadj.equals("CA")) {
				UsageAdjacencia();
				System.out.println("Selecciona el tipus de adjacencia: ");
				tadj = (input.nextLine());
			}

		}
		else tadj = "C";

		System.out.println("Indica el numero de files del hidato: ");
		fil = input.nextInt();
		System.out.println("Indica el numero de columnes del hidato: ");
		col = input.nextInt();


		hidato = g.GenerarHidatoUsuari(fil, col);

		System.out.println("Hidato Generat!");

		t.CrearTauler(tcela, tadj, hidato);
	}


	public void ReprendrePartida(){
		ge.CarregaPartida();
		finalitzat = false;
		TranscursPartida();

	}
	
	public void IniciaPartida(){
		Scanner input = new Scanner(System.in);
		String tcela, tadj;
		String[][] hidato;


		System.out.println("Selecciona el tipus de cela: ");
		tcela = (input.nextLine());

		while (!tcela.equals("T") && !tcela.equals("Q") && !tcela.equals("H")) {
			UsageCela();
			System.out.println("Selecciona el tipus de cela: ");
			tcela = (input.nextLine());
		}

			if (tcela.equals("Q")){
				System.out.println("Selecciona el tipus de adjacencia: ");
				tadj = (input.nextLine());
				while (!tadj.equals("C") && !tadj.equals("CA")) {
					UsageAdjacencia();
					System.out.println("Selecciona el tipus de adjacencia: ");
					tadj = (input.nextLine());
				}

			}
			else tadj = "C";

		System.out.println("Selecciona la dificultat: ");
		SetDificultat(input.nextLine());

		while (!dif.equals("FACIL") && !dif.equals("NORMAL") && !dif.equals("DIFICIL")) {
			UsageDificultat();
			System.out.println("Selecciona la dificultat: ");
			SetDificultat(input.nextLine());
		}


		hidato = g.GenerarHidatoAlgorisme(tcela, tadj, dif);
		t.CrearTauler(tcela, tadj, hidato);

		r.start(); //Inicia el rellotge
		finalitzat = false;
		ajuda = false;
		torn = 1;
		TranscursPartida();

	}

	public void TranscursPartida(){
		Jugada j = new Jugada();

		while (!finalitzat && !completat){
			j.DoJugada();
			j.GetJugada();
			if (j.GetJugada().equals("NUMERO")){
				j.GetNumero();
				j.GetX();
				j.GetY();
				++torn;

				//if (ja no hi han interrogants) completat = true;
			}
			else if (j.GetJugada().equals("GUARDAR")){
				r.stop();
				guardat = true;
				finalitzat = true;
				System.out.println("Has guardat la partida.");
				//ge.GuardarPartida();
			}
			else if (j.GetJugada().equals("AJUDA") && !ajuda){
				r.stop();
				ajuda = true;
				//GetAjuda: Mostra en vermell les celes incorrectes
				System.out.println("Ajuda");
			}

		}

		//Partida completada
		if (completat){

			r.stop();
			temps = r.GetTime();
			System.out.printf("Has trigat: %f\n", temps);

			System.out.println("Has guanyat!");
		}
	}



	public void SetTauler(Tauler t){
		this.t = t;
	}
	public double GetTemps(){
		return temps;
	}
	public Integer GetIdHidato(){
		return idhidato;
	}
	public void SetDificultat(String dif){
		this.dif = dif;
	}
	public String GetDificultat(){
		return dif;
	}
	public Integer GetTorn(){
		return torn;
	}
	public Integer GetFiles(){
		return fil;
	}
	public Integer GetColumnes(){
		return col;
	}
	public void SetFiles(Integer fil){
		this.fil = fil;
	}
	public void SetColumnes(Integer col){
		this.col = col;
	}
}
