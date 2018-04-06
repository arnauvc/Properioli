package Hidato;

import java.util.ArrayList;

public class Cela{
	private boolean valida;
	private boolean visible;
	private Integer valor;
	List<Cela> adjacents = new Arraylist<Cela>();


	//Constructora
	public Cela(val,vis){
		this.valor = val;
		this.visible = vis;
	}

	//Consultora
	public Integer getValor() {
		return valor;
	}

	public boolean isValida() {
		return valida;
	}

	public boolean isVisible() {
		return visible;
	}

	public ArrayList getAdjacents() {
		return adjacents;
	}

	//modificadores
	public void ModificarValor(int x){
		this.valor = x;
	}

}
