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
	private Tauler t;
	private Generacio g = new Generacio();
	private Gestor ge;
	private Integer idhidato;
	private String dif; //Dificultat
	private Integer torn;



	private void UsageDificultat(){
		System.out.println("Usage: String {FACIL, NORMAL, DIFICIL}");
	}

	public void Generar(){
		String tcela, tadj;
		int fil, col;

		String hidato[][];

		//tcela = t.GetTipusCela();
		//tadj = t.GetTiposAdj();
		//fil = t.GetNombreFiles();
		//col = t.GetNombreColumnes();

		//hidato = g.GenerarHidatoUsuari(fil, col);

		System.out.println("Hidato Generat!");

		//passar a tauler i tauler a solucio

	}


	public void ReprendrePartida(){
		ge.CarregaPartida();
		finalitzat = false;
		TranscursPartida();

	}
	
	public void IniciaPartida(){
		Scanner input = new Scanner(System.in);
		String tcela, tadj;

		//tcela = t.GetTipusCela();
		//tadj = t.GetTiposAdj();


		/*System.out.println("Selecciona el tipus de cela: ");
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
			*/
		System.out.println("Selecciona la dificultat: ");
		SetDificultat(input.nextLine());

		while (!dif.equals("FACIL") && !dif.equals("NORMAL") && !dif.equals("DIFICIL")) {
			UsageDificultat();
			System.out.println("Selecciona la dificultat: ");
			SetDificultat(input.nextLine());
		}
		tcela = "Q";
		tadj = "C";


		g.GenerarHidatoAlgorisme(tcela, tadj, dif);

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
}
