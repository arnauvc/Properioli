package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Usuari;

import java.util.*;


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
	private Resolucio re = new Resolucio();
	//private Usuari u = new Usuari();
	private Integer idhidato;
	private String dif; //Dificultat
	private Integer torn;
	private Integer fil;
	private Integer col;
	private String[][] taulerU;
	private String[][] taulerguardat;
	private Integer maxim;
	private String nomusuari;


	public Partida(){}
	public Partida(String nomusuari, String tcela, String tadj, Integer nfil, Integer ncol){
		SetNom(nomusuari);
		SetCela(tcela);
		SetAdjacencia(tadj);
		fil = nfil;
		col = ncol;
	}



	public void PartidaBiblioteca() throws Exception{
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
        Random rand = new Random();
        idhidato = rand.nextInt(100) + 1;

		String[][] hidato_resolt = new String[t.getNumFiles()][t.getNumColum()];
		hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());
		ctj.MostrarResolucio(hidato_resolt, t);

	}
	
	public void IniciaPartida() throws Exception {
		//Quan l'usuari vol resoldre un hidato creat per la IA(Aleatori)


		taulerU = g.GenerarHidato(GetCela(), GetAdjacencia(), dif);
		maxim = g.GetValorMaxim();
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
		SetFiles(t.getNumFiles());
		SetColumnes(t.getNumColum());
        Random rand = new Random();
        idhidato = rand.nextInt(100) + 1;

		r.start(); //Inicia el rellotge
		finalitzat = false;
		completat = false;
		ajuda = false;
		guardat = false;
		reguardat = false;
		torn = 1;
		TranscursPartida();

	}
	public void ReprendrePartida() throws Exception {
		//Quan l'usuari carrega la partida que tenia guardada
		finalitzat = false;
		completat = false;
		guardat = true;
		reguardat = false;
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
        SetFiles(t.getNumFiles());
        SetColumnes(t.getNumColum());
		TranscursPartida();
	}

	public void TranscursPartida() throws Exception {
		Jugada j = new Jugada();
		Integer x, y;
		String num;
		boolean aux = false;
		String[][] hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());

		while (!finalitzat && !completat){

		    ctj.MostrarTauler(t);


			ctj.InteraccioJugada(j, t);
			j.SetInvalid(aux);
			j.GetJugada();
			if (j.GetJugada().equals("NUMERO")){
				num = j.GetNumero();
				x = j.GetX();
				y = j.GetY();
                j.ComprovaJugada(t, maxim);
                if (j.GetInvalid()) e.PrintError(1);
				else if (!j.GetInvalid()) {
                    ++torn;
				    t.ModificaCeldaV(num, x, y);
                }
                if (ComprovarPartidaFinalitzada(hidato_resolt)) {
					completat = true;
				}

			}
			else if (j.GetJugada().equals("GUARDAR")) {
				//Preparar el tauler[][] per a PartidesGuardades
                taulerguardat = new String[t.getNumFiles()][t.getNumColum()];

                for (int i = 0; i < t.getNumFiles(); ++i) {
                    for (int l = 0; l < t.getNumColum(); ++l) {
                        taulerguardat[i][l] = t.consultarValCela(i, l);
                    }
                }
                r.stop();
                guardat = true; //L'unic que fa es invalidar el temps
                reguardat = true; //Es per avisar al Ctrl que l'usuari ha guardat
                finalitzat = true;
                //exception per al gestor
                throw new Exception();
			}

			else if (j.GetJugada().equals("SORTIR")) finalitzat = true;
			else if (j.GetJugada().equals("RESET")){
				t = new Tauler();
				t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
				ajuda = true;
			}
			else if (j.GetJugada().equals("AJUDA")){
				r.stop();
				ajuda = true;
				String[][] hidato_ajuda;
				hidato_ajuda = a.GetAjuda(t, hidato_resolt);
				ctj.MostrarAjuda(hidato_ajuda, t);
			}

		}


		//Partida completada
		if (completat && !guardat && !ajuda){
			r.stop();
			temps = r.GetTime();
			ctj.MostrarPuntuacio(temps);

		}

		//Partida completada utilitzant ajuda o havent guardat
		if (completat && guardat || ajuda){
			temps = 0;
		}
	}


	public boolean ComprovarPartidaFinalitzada(String[][] matriu_solucio){
		String[][] matriu_tauler = new String[t.getNumFiles()][t.getNumColum()];
		for (int i = 0; i < t.getNumFiles(); i++){
			for (int j = 0; j < t.getNumColum(); j++){
				matriu_tauler[i][j] = t.consultarValCela(i, j);
				if (!matriu_tauler[i][j].equals("*") && !matriu_tauler[i][j].equals("#")) {
					if (!matriu_tauler[i][j].equals(matriu_solucio[i][j])) return false;
				}
			}
		}
		return true;
	}

	public void SetTauler(Tauler t){
		this.t = t;
	}
	public void SetTaulerU(String[][] taulerU){
		this.taulerU = taulerU;
	}
	public void SetTaulerG(String[][] taulerguardat){
	    this.taulerguardat = taulerguardat;
    }
	public String[][] GetTaulerU(){ return taulerU; }
	public String[][] GetTaulerG(){ return taulerguardat; }
	public void SetNom(String nom){
		Usuari u = new Usuari(nom);
		u.SetNom(nom);
		nomusuari = nom;
	}
	public String GetNom(){
	    return nomusuari;
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
	public Integer GetMaxim(){
		return maxim;
	}
	public void SetMaxim(Integer maxim){
		this.maxim = maxim;
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
	public void SetFinalitzat(boolean finalitzat){
		this.finalitzat = finalitzat;
	}
	public boolean GetAjuda(){
		return ajuda;
	}
}
