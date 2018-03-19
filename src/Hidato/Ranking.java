package Hidato;

import java.awt.*;
import java.util.*;

import Hidato.Tauler;


public class Ranking {

    private HashMap<Integer, PriorityQueue<Double> > ranking;
    private Integer NUMMAXTEMPS;//Numero maxim de temps record per hidato

    public Ranking() {
        ranking = new HashMap<Integer,PriorityQueue<Double>>();
    }

    public void Inicialitzar(){
        ranking.clear();
    }

    public void Actualitzar(Integer idhidato, Double temps){
        PriorityQueue<Double> resultats = ranking.get(idhidato);
        if(resultats == null){
            PriorityQueue<Double> pq = new PriorityQueue<Double>();
            pq.add(temps);
            ranking.put(idhidato, pq);
        }

        else{
            PriorityQueue<Double> pqr = ranking.get(idhidato);
            pqr.add(temps);
			ranking.remove(idhidato);
			ranking.put(idhidato, pqr);
            //ranking.replace(idhidato, resultats, pqr); //Nomes funciona per Java8, i el compilador de la fib es javac versio 7
        }
    }

    public PriorityQueue<Double> GetValue(Integer idhidato){
        return ranking.get(idhidato);
    }


}
