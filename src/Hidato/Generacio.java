package Hidato;


import javafx.util.Pair;

import java.util.*;
import java.lang.Exception;

public class Generacio {

    private String[][] solucio;
    private String[][] tauler;
    private ArrayList<Pair<Pair<Integer,Integer>, String>> hidato = new ArrayList<>(); //(x,y),Valor

    private Random r = new Random();

    private Integer nfiles;
    private Integer ncolumnes;
    private Double fillfactor = 0.9;

    private Integer maxceles;
    private Integer ValorMaxim = 0;
    private String tipuscela;
    private String tipusadj;
    private Integer numadj; //Conte el numero de veins que pot tenir una cela en funcio de l'adjecencia
    //private String dificultat;
    private Integer numcostats;//segons tipuscela i tipusadj

    private Integer ProbNumero;
    private Integer ProbBlanc;

    private boolean Check1(Integer i, Integer j){
        return (i < nfiles && i >= 0) && (j < ncolumnes && j >= 0) && (tauler[i][j].equals("#"));
    }

    private boolean Generar1(Integer i, Integer j, Integer contador){
        if(contador > maxceles*fillfactor){
            ValorMaxim = contador-1;
            return true;
        }
        if(!Check1(i,j)) return false;
        else{
            hidato.add(new Pair<>(new Pair<>(i, j), Integer.toString(contador)));
            tauler[i][j]=Integer.toString(contador);

            HashSet<Pair<Integer,Integer>> visitades = new HashSet<>();
            Vector<Pair<Pair<Integer, Integer>, Integer>> prob = Veins(new Pair<>(i,j));

            Pair<Integer, Integer> nextcela = NextPos(new Pair<>(i, j), prob);
            visitades.add(nextcela);
            Integer cont = ++contador;
            while(!Generar1(nextcela.getKey(),nextcela.getValue(), cont)){
                if(visitades.size() >= numadj){
                    tauler[i][j]="#";
                    return false;
                }
                Recalcular(nextcela,prob);
                for(int t = 0; t < 4; ++t){
                    prob.get(t).getKey();
                }
                nextcela = NextPos(new Pair<>(i, j), prob);
                visitades.add(nextcela);
            }
            return true;
        }
    }

    private void Buidar(){
        for(int l = 0; l < nfiles; ++l){
            for(int k = 0; k < ncolumnes; ++k){
                if(!tauler[l][k].equals("#") && !tauler[l][k].equals("1") && !tauler[l][k].equals(Integer.toString(ValorMaxim))){
                    Integer ra = r.nextInt(100);
                    if(ra < ProbBlanc){
                        tauler[l][k]="?";
                    }
                }
            }
        }
    }

    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dif) {
        if (Dif.equals("FACIL")){
            nfiles = NumeroAleatori(3, 6);
            ncolumnes = NumeroAleatori(3, 6);
            ProbNumero = 45;
        }
        else if (Dif.equals("NORMAL")){
            nfiles = NumeroAleatori(7, 8);
            ncolumnes = NumeroAleatori(7,8);
            ProbNumero = 25;
        }
        else {
            nfiles = NumeroAleatori(9, 11);
            ncolumnes = NumeroAleatori(9,11);
            ProbNumero = 30;
        }

        tipuscela = Tipuscela;
        tipusadj = Tipusadj;
        numadj = 4;//si tipusadj es C
        ProbBlanc = 100 - ProbNumero;
        numcostats = 4;
        maxceles = nfiles * ncolumnes;

        if(nfiles*ncolumnes >= 49) fillfactor = 0.8;
        else fillfactor = 0.9;

        tauler = new String[nfiles][ncolumnes];

        //Podem canviar la cela inicial per algo diferent
        Integer Iini = NumeroAleatori(0, 1);
        Integer Jini = NumeroAleatori(0, 1);
        tauler[Iini][Jini] = Integer.toString(1);

        hidato.add(new Pair<>(new Pair<>(Iini,Jini),"1"));
        maxceles = nfiles*ncolumnes;

        //Inicialitzar la matriu a #
        for (int i = 0; i < nfiles; ++i){
            for (int j = 0; j < ncolumnes; ++j){
                tauler[i][j] = "#";
            }
        }
        /*
        System.out.println("NFiles i NColumnes");
        System.out.printf("%d , %d", nfiles, ncolumnes);
        System.out.println();
        System.out.printf("Fillfactor: %f", fillfactor);
        System.out.println();
        System.out.println("Cela ini");
        System.out.printf("%d , %d", Iini, Jini);
        System.out.println();
        */
        if(Generar1(Iini,Jini, 1)){
            //Genera una copia del tauler
            solucio = new String[nfiles][ncolumnes];
            for(int i = 0; i < nfiles; ++i){
                for(int j = 0; j < ncolumnes; ++j){
                    solucio[i][j] = tauler[i][j];
                }
            }
            Buidar();
        }
        else{
            //throw new Exception();
        }

        return tauler;
    }

    private Vector<Pair<Pair<Integer, Integer>, Integer>> Veins(Pair<Integer,Integer> posactual){
        Vector<Pair<Pair<Integer, Integer>, Integer>> pro = new Vector<>(numadj);

        pro.add(new Pair<Pair<Integer,Integer>,Integer>(new Pair<>(posactual.getKey()-1,posactual.getValue()),25));
        pro.add(new Pair<Pair<Integer,Integer>,Integer>(new Pair<>(posactual.getKey(),posactual.getValue()+1),25));
        pro.add(new Pair<Pair<Integer,Integer>,Integer>(new Pair<>(posactual.getKey()+1,posactual.getValue()),25));
        pro.add(new Pair<Pair<Integer,Integer>,Integer>(new Pair<>(posactual.getKey(),posactual.getValue()-1),25));
        return pro;
    }

    private Pair<Integer,Integer> NextPos(Pair<Integer,Integer> posactual, Vector<Pair<Pair<Integer, Integer>, Integer>> prob){
        //Assumint adjacencia costat, celes quadrades
        Pair<Integer, Integer> p;
        prob.get(0).getKey();
        Integer in = r.nextInt(100);
        if(in < prob.get(0).getValue()) p = prob.get(0).getKey();//TOP
        else if (in < prob.get(0).getValue() + prob.get(1).getValue())p = prob.get(1).getKey();//RIGHT
        else if (in < prob.get(0).getValue() + prob.get(1).getValue() + prob.get(2).getValue())p = prob.get(2).getKey();//BOT
        else p = prob.get(3).getKey();//LEFT
        return p;
    }

    private void Recalcular(Pair<Integer,Integer> pos, Vector<Pair<Pair<Integer, Integer>, Integer>> prob ){
        Integer valor = 0;
        Integer num0 = 0;
        for(int t = 0; t < prob.size(); ++t){
            if(prob.get(t).getKey() == pos){
                valor = prob.get(t).getValue();
                prob.set(t, new Pair<>(prob.get(t).getKey(), 0));
            }
            if(prob.get(t).getValue() == 0) ++num0;
        }
        for(int t = 0; t < prob.size(); ++t){
            if(prob.get(t).getValue() != 0){
                prob.set(t, new Pair<>(prob.get(t).getKey(), prob.get(t).getValue()+valor/(prob.size()-num0)));
            }
        }
    }
/*
    private boolean NextType(){ //True es un numero, false es Cela en blanc
        Random rand = new Random();
        Integer ra = rand.nextInt(100);
        if(ra < ProbNumero) return true;
        else return false;
    }
*/
    private Integer NumeroAleatori(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }
    public String[][]GetSolucio(){
        return solucio;
    }

    public Integer GetValorMaxim(){
        return ValorMaxim;
    }

    public Integer GetNumFiles(){
        return nfiles;
    }

    public Integer GetNumColumnes(){
        return ncolumnes;
    }
}
