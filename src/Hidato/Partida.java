package Hidato;

import Hidato.Rellotge;
import Hidato.Tauler;
import Hidato.Jugada;
import Hidato.Ajuda;
import Hidato.Usuari;
import javafx.util.Pair;

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
	private HidatosSolucionats hs = new HidatosSolucionats();
	private Integer idhidato;
	private String dif; //Dificultat
	private Integer torn;
	private Integer fil;
	private Integer col;
	private String[][] taulerU;
	private String[][] taulerguardat;
	private String[][] hidato_resolt;
	private Integer maxim;
	private String nomusuari;
	private String path;
	private String[][] hidato_ajuda;
	private boolean cj;


	public Partida(){}
	public Partida(String nomusuari, String tcela, String tadj, Integer nfil, Integer ncol){
		SetNom(nomusuari);
		SetCela(tcela);
		SetAdjacencia(tadj);
		fil = nfil;
		col = ncol;
	}

	private boolean isNumeric(String s){
		try{
			Integer d = Integer.parseInt(s);
		}
		catch (NumberFormatException nfe){
			return  false;
		}
		return  true;
	}



	public void PartidaBiblioteca() throws Exception{
		//Quan l'usuari tria un hidato que esta a la biblioteca i el vol resoldre
		r.start();
		finalitzat = false;
		completat = false;
		ajuda = false;
		guardat = false;
		reguardat = false;
		dif = "F"; //He de ficar alguna cosa sino peta
		fil = t.getNumFiles();
		col = t.getNumColum();
		torn = 1;
		maxim = 0;

		//Per calcular el maxim per despres comprovarJugada
		for (int i = 0; i < t.getNumFiles(); i++){
			for (int j = 0; j < t.getNumColum(); ++j){
				if (isNumeric(t.consultarValCela(i,j))) {
					if (maxim < Integer.parseInt(t.consultarValCela(i, j))) {
						maxim = Integer.parseInt(t.consultarValCela(i, j));
					}
				}
			}
		}

		//Per passar el Tauler t a la matriu String[][] taulerU
		taulerU = new String[t.getNumFiles()][t.getNumColum()];
		for (int i = 0; i < t.getNumFiles(); i++){
			for (int j = 0; j < t.getNumColum(); ++j){
				taulerU[i][j] = t.consultarValCela(i, j);
			}
		}
		hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());
		//TranscursPartida();
	}

	public void Generar(){
		//Quan l'usuari ha generat un hidato i la IA l'ha de resoldre
		System.out.println("Generar en partida");
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
        Random rand = new Random();
        idhidato = rand.nextInt(50) + 1;

		hidato_resolt = new String[t.getNumFiles()][t.getNumColum()];
		hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());
		//ts = hidato_resolt.clone();
		ctj.MostrarResolucio(hidato_resolt, t);
	}

	public void IniciaPartida() throws Exception {
		//Quan l'usuari vol resoldre un hidato creat per la IA(Aleatori)


		taulerU = g.GenerarHidato(GetCela(), GetAdjacencia(), dif);
		for(int i = 0; i <taulerU.length;++i){
			for(int j = 0; j < taulerU[i].length;++j){
				System.out.print(taulerU[i][j]);
				if(j < taulerU[0].length-1) System.out.print(",");
			}
			System.out.println();
		}
		maxim = g.GetValorMaxim();
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
		SetFiles(t.getNumFiles());
		SetColumnes(t.getNumColum());
        Random rand = new Random();
        idhidato = rand.nextInt(50) + 1;
		hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());

		//Passar String[][] hidato_resolt -> ArrayList<>solucio
		if (hidato_resolt != null) {
			ArrayList<Pair<Pair<Integer, Integer>, String>> solucio = new ArrayList<>();
			for (int i = 0; i < hidato_resolt.length; i++) {
				for (int j = 0; j < hidato_resolt[i].length; j++) {
					Pair<Integer, Integer> p = new Pair<>(i, j);
					String hr = hidato_resolt[i][j];
					solucio.add(new Pair<>(p, hr));
				}
			}
			//hs.SetPath(path);
			//hs.GuardarHidato(idhidato, t, solucio);

			r.start(); //Inicia el rellotge
			finalitzat = false;
			completat = false;
			ajuda = false;
			guardat = false;
			reguardat = false;
			torn = 1;
			//TranscursPartida();
		}
		else e.PrintError(2	);

	}
	public void ReprendrePartida() {
		//Quan l'usuari carrega la partida que tenia guardada
		finalitzat = false;
		completat = false;
		guardat = true;
		reguardat = false;
		t.CrearTauler(GetCela(), GetAdjacencia(), taulerU);
        SetFiles(t.getNumFiles());
        SetColumnes(t.getNumColum());
		hidato_resolt = re.ResoltreHidato(taulerU, GetCela(), GetAdjacencia());
		//TranscursPartida();
	}

	public void TranscursPartida(Integer fila, Integer columna, String elem, String accion) {
		Jugada j = new Jugada();
		Integer x, y;
		String num;
		boolean aux = false;

		//while (!finalitzat && !completat){

		    //ctj.MostrarTauler(t);


            ctj.InteraccioJugada(j, t,fila,columna,elem,accion);
			j.SetInvalid(aux);
			j.GetJugada();
			if (j.GetJugada().equals("NUMERO")){
				num = j.GetNumero();
				x = j.GetX();
				y = j.GetY();
                j.ComprovaJugada(t, maxim);
                cj = j.GetInvalid();
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
                        if(t.consultarValCela(i,l).equals(hidato_resolt[i][l])) taulerguardat[i][l] = t.consultarValCela(i, l);
                        else  taulerguardat[i][l] = taulerU[i][l];
                    }
                }

				for (int i = 0; i < t.getNumFiles(); ++i) {
					for (int l = 0; l < t.getNumColum(); ++l) {
						System.out.print(taulerguardat[i][l]);
					}
					System.out.println();
				}

                r.stop();
                guardat = true; //L'unic que fa es invalidar el temps
                reguardat = true; //Es per avisar al Ctrl que l'usuari ha guardat
                finalitzat = true;
                //exception per al gestor
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
				hidato_ajuda = a.GetAjuda(t, hidato_resolt);
				//ctj.MostrarAjuda(hidato_ajuda, t);
			}
		//}


		//Partida completada
		if (completat && !guardat && !ajuda){
			r.stop();
			temps = r.GetTime();
			ctj.MostrarPuntuacio(temps);
			ctj.MostrarTauler(t);
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
	public void SetPath(String path){
		this.path = path;
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
	public String[][] GetTsolucio(){return hidato_resolt.clone();}

	public boolean isFinalitzat(){return finalitzat;}

	//mis funciones para el menu4
    public String Celataulersol(Integer f, Integer c){
	    //System.out.println(hidato_resolt[f][c]);
	    return hidato_resolt[f][c];
    }
    public String Celatauler(Integer f, Integer c){
        //System.out.println(hidato_resolt[f][c]);
        return t.consultarValCela(f,c);
    }

    public String celayuda(Integer f, Integer c){
		return hidato_ajuda[f][c];
	}

	public boolean comprabajugada() {
		return cj;
	}
}
