package Hidato;

import java.util.ArrayList;
import java.util.List;
import Hidato.Cela;

public class Tauler {
    /*private enum TipusCela{
        TRIANGLE, QUADRAT, HEXAGON
    }*/
    //private List<List<Cela>> Celas = new ArrayList<ArrayListCela>>();
    private String tipuscela;
    private Integer numcelestotal;
    private Integer numcelesocupades;
    private Integer numcelesbuides;
    private Integer id;//Numero que identifica l'hidato inequivocament
    private boolean adjacencia;// si adjacencia == true  es adjacencia tanto para lado como vertices
    private Cela cela;


    //constructora
	public Tauler(Integer numtauler){//Obliguem a que sempre que es crei una instancia de Tauler, se li proporcioni un id
		this.id = numtauler;
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
     /*public void ModificaCeldaV(int x, int posi, int posj){
	    cela = Celas.get(posi).get(posj);
	    cela.modificarValor(x);

    }*/


    /*public void ModificarCela(){ //celas adjacents
        list<Cela> adjacents = new ArrayList<Cela>();
        CelaAux
        if(tipuscela == "Q"){
            if(adjacencia == "C")
            for(int i = 0; i < Celas.size(); ++i){
                for(int j = 0; j < Celas.get(0).size(); ++j){
                    cela = Celas.get(i).get(j);
                    if(cela.isValida()){
                        if(i == 0) {
                            if (j == 0){
                                celaaux = Celas.get(i+1).get(j);
                                if(celaux.IsValida) adjacents.
                            }
                        }
                    }
                }
            }

        }

    }*/

    public void CrearTauler(String ticela, Integer celatotal, Integer celaoc, Integer celabuides, boolean adj)// es para cuando el usuario crea el hidato, y pasa todos los parametros para crear el tauler
    {
        this.tipuscela = ticela;
        this.numcelestotal = celatotal;
        this.numcelesocupades = celaoc;
        this.numcelesbuides = celabuides;
        this.adjacencia = adj;

    }





}
