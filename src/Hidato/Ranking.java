package Hidato;

import java.util.*;


public class Ranking {

    private HashMap<Integer, TreeSet<Double>> ranking = new HashMap<>();
    //private Integer NUMMAXTEMPS;//Numero maxim de temps record per hidato
    //private PriorityQueue<Integer> cua = new PriorityQueue <Integer>(10, Comparator.reverseOrder());

    public void Inicialitzar(){
        ranking.clear();
    }

    public void Actualitzar(Integer idhidato, Double temps){

        if(! ranking.containsKey(idhidato)){
            TreeSet<Double> ss = new TreeSet<Double>(Comparator.reverseOrder());
            ss.add(temps);
            ranking.put(idhidato, ss);
        }

        else{
            TreeSet<Double> resultats = ranking.get(idhidato);
            resultats.add(temps);
			ranking.remove(idhidato);
			ranking.put(idhidato, resultats);
            //ranking.replace(idhidato, resultats, pqr); //Nomes funciona per Java8, i el compilador de la fib es javac versio 7
        }

    }

    public TreeSet<Double> GetValue(Integer idhidato){
        return ranking.get(idhidato);
    }

}
