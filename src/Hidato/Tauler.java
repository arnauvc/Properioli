package Hidato;

public class Tauler {
    private enum TipusCela{
        TRIANGLE, QUADRAT, HEXAGON
    }
    private TipusCela tipuscela;
    private Integer numcelestotal;
    private Integer numcelesocupades;
    private Integer numcelesbuides;
    private Integer id;//Numero que identifica l'hidato inequivocament

	public Tauler(Integer numtauler){//Obliguem a que sempre que es crei una instancia de Tauler, se li proporcioni un id
		id = numtauler;
	}
	public Integer GetId(){
		return id;	
	}



}
