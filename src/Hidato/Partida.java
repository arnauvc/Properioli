package Hidato;

import Hidato.Rellotge;

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
	private Rellotge r;
	
	public void IniciaPartida(){
		//Aqui fa coses
		r.start();//Inicia el rellotge
	}

	public void AcabaPartida(){
		r.stop();
		temps = r.GetTime();
		//Aqui fa coses tambe
	}
	public double GetTemps(){
		return temps;
	}


}
