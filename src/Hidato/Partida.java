package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Usuari;


public class Partida {

    private boolean ajuda; //Ha fet servir ajuda en algun moment de la partida
    private boolean guardat; //Ha guardat la partida en algun moment
	private boolean reguardat; //Guardat aux per si torna a guardar la partida
    private boolean finalitzat; //Ha parat la partida
	private boolean completat; //Ha acabat la partida
    private double  temps;
	private Rellotge r = new Rellotge();
	private Tauler t = new Tauler();
	private Generacio g = new Generacio();
	private Ajuda a = new Ajuda();
	private CtrlPresJugada ctj = new CtrlPresJugada();
	private Error e = new Error();
	private Usuari u;
	private Integer idhidato;
	private String dif; //Dificultat
	private Integer torn;
	private Integer fil;
	private Integer col;
	private String[][] taulerU;


	public Partida(){}
	public Partida(String nomusuari, String tcela, String tadj, Integer nfil, Integer ncol){
		SetNom(nomusuari);
		SetCela(tcela);
		SetAdjacencia(tadj);
		fil = nfil;
		col = ncol;
	}


	public void PartidaBiblioteca(){
		//Quan l'usuari tria un hidato que esta a la biblioteca i el vol resoldre
		r.start();
		finalitzat = false;
		completat = false;
		ajuda = false;
		guardat = false;
		reguardat = false;
		torn = 1;
		TranscursPartida();
	}

	public void Generar(){
		//Quan l'usuari ha generat un hidato i la IA l'ha de resoldre
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
	}
	
	public void IniciaPartida(){
		//Quan l'usuari vol resoldre un hidato creat per la IA(Aleatori)
		String[][] hidato;

		hidato = g.GenerarHidato(GetCela(), GetAdjacencia(), dif);
		t.CrearTauler(GetCela(), GetAdjacencia(), hidato);

		r.start(); //Inicia el rellotge
		finalitzat = false;
		completat = false;
		ajuda = false;
		guardat = false;
		reguardat = false;
		torn = 1;
		TranscursPartida();

	}
	public void ReprendrePartida(){
		//Quan l'usuari carrega la partida que tenia guardada
		finalitzat = false;
		completat = false;
		reguardat = false;
		TranscursPartida();
	}

	public void TranscursPartida(){
		Jugada j = new Jugada();
		Integer num, x, y;
		boolean aux = false;

		while (!finalitzat && !completat){
			ctj.InteraccioJugada(j);
			j.SetInvalid(aux);
			j.GetJugada();
			if (j.GetJugada().equals("NUMERO")){
				num = j.GetNumero();
				x = j.GetX();
				y = j.GetY();
                j.ComprovaJugada();
                if (j.GetInvalid()) e.PrintError(1);
				if (!j.GetInvalid()) {
                    ++torn;
				    t.ModificaCeldaV(Integer.toString(num), x, y);
                }

				//if (ja no hi han interrogants) completat = true;
			}
			else if (j.GetJugada().equals("GUARDAR")){
				r.stop();
				guardat = true; //L'unic que fa es invalidar el temps
				reguardat = true; //Es per avisar al Ctrl que l'usuari ha guardat
				finalitzat = true;
			}
			else if (j.GetJugada().equals("AJUDA")){
				r.stop();
				ajuda = true;
				String[][] hidato_ajuda;
				hidato_ajuda = a.GetAjuda(t);
			}

		}

		//Partida completada
		if (completat && !guardat && !ajuda){
			r.stop();
			temps = r.GetTime();
			System.out.printf("Has trigat: %f\n", temps);
			System.out.println("Has guanyat!");

		}

		//Partida completada utilitzant ajuda o havent guardat
		if (completat && guardat || ajuda){
			temps = 0;
		}
	}



	public void SetTauler(Tauler t){
		this.t = t;
	}
	public void SetTaulerU(String[][] taulerU){
		this.taulerU = taulerU;
	}
	public void SetNom(String nom){
		u.SetNom(nom);
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
	public void SetTorn(Integer torn){
		this.torn = torn;
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
	public String GetCela(){
		return t.getTipuscela();
	}
	public String GetAdjacencia(){
		return t.GetTiposAdj();
	}
	public void SetCela(String cela){
		t.SetTipuscela(cela);
	}
	public void SetAdjacencia(String adj){
		t.SetAdjacencia(adj);
	}
	public boolean GetReguardat(){
		return reguardat;
	}
	public boolean GetGuardat(){
		return guardat;
	}
	public void SetGuardat(boolean guardat){
		this.guardat = guardat;
	}
	public boolean GetCompletat(){
		return completat;
	}
	public boolean GetAjuda(){
		return ajuda;
	}
}
