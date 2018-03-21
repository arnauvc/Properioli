package Hidato;

import java.util.ArrayList;
import java.util.List;
import Hidato.Cela;

public class Tauler {
    /*private enum TipusCela{
        TRIANGLE, QUADRAT, HEXAGON
    }*/
    private List<Cela> Celas = new ArrayList<Cela>();
    private String tipuscela;
    private Integer numcelestotal;
    private Integer numcelesocupades;
    private Integer numcelesbuides;
    private Integer id;//Numero que identifica l'hidato inequivocament
    private boolean adjacencia;  // si adjacencia == true  es adjacencia tanto para lado como vertices


    //constructora
	public Tauler(Integer numtauler){//Obliguem a que sempre que es crei una instancia de Tauler, se li proporcioni un id
		id = numtauler;
	}

	//Consultora
	public Integer GetId(){
		return id;	
	}
	public Integer GetNumCelasTotal(){return numcelestotal;}
	public Integer GetNumCelesOcupadas(){return numcelesocupades;}
	public Integer GetNumCelesBuides(){return numcelesbuides;}
    public boolean GetTiposAdj(){return adjacencia;}

    //Modificadoras
    void ModificaCelda(int x){
    }

    void CrearTauler(String ticela, Integer celatotal, Integer celaoc, Integer celabuides, boolean adj)// es para cuando el usuario crea el hidato, y pasa todos los parametros para crear el tauler
    {
        tipuscela = ticela;
        numcelestotal = celatotal;
        numcelesocupades = celaoc;
        numcelesbuides = celabuides;
        adjacencia = adj;

    }



}
