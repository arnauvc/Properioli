package Hidato;

import java.util.ArrayList;

public class Cela{
	private String tipus;
	private boolean valida;
	private boolean visible;

	private String valor;


	//Constructora

	public Cela(){};

	public Cela(String tip, String val, boolean vis, boolean vali){
		this.tipus = tip;
		this.visible = vis;
		this.valor = val;
		this.valida = vali;
	}

	//Consultora
	public String getValor() {
		return valor;
	}

	public boolean isValida() {
		return valida;
	}

	public boolean isVisible() {
		return visible;
	}


	//modificadores
	public void ModificarValor(String x){
		this.valor = x;
	}

}
