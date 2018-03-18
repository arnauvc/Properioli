package Hidato;

import java.awt.*;
import java.util.*;

import Hidato.Tauler;


public class Ranking {

    private HashMap ranking;
    private Integer NUMMAXTEMPS;//Numero maxim de temps record per hidato

    public Ranking() {
        ranking = new HashMap<Integer,PriorityQueue<Double>>();
    }

    public void Inicialitzar(){
        ranking.clear();
    }

    public void Actualitzar(Integer idhidato, Double temps){
        PriorityQueue<Double> resultats = (PriorityQueue<Double>) ranking.get(idhidato);
        if(resultats == null){
            PriorityQueue<Double> pq = new PriorityQueue<Double>();
            pq.add(temps);
            ranking.put(idhidato, pq);
        }

        else{
            PriorityQueue<Double> pqr = (PriorityQueue<Double>) ranking.get(idhidato);
            pqr.add(temps);
            ranking.replace(idhidato, resultats, pqr);
        }
    }

    public PriorityQueue<Double> GetValue(Integer idhidato){
        return (PriorityQueue<Double>) ranking.get(idhidato);
    }


}
