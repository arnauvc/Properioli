package Hidato;


import javafx.util.Pair;

import java.util.*;
import java.lang.Exception;

public class Generacio {

    private Random r = new Random();

    private String[][] solucio;//Matriu plena. Resolta
    private String[][] tauler; //Matriu apunt per jugar
    //private ArrayList<Pair<Pair<Integer,Integer>, String>> hidato = new ArrayList<>(); //(x,y),Valor

    private Integer nfiles;
    private Integer ncolumnes;
    private Double  fillfactor = 0.9;
    private Integer maxceles; // Numero maxim de celes teoric
    private Integer ValorMaxim = 0; // Numero mes alt (real) del tauler
    private Integer numadj; //Conta el numero de veins que pot tenir una cela en funcio de l'adjecencia
    private String  tipuscela;
    private String  tipusadj;
    private Integer ProbNumero;
    private Integer ProbBlanc;

    /** Comprova la validesa de la nova cela
     *
     * @param i Coordenada i del tauler
     * @param j Coordenada j del tauler
     * @return Cert si la cela[i][j] esta dins del tauler, i no pren per valor "#"
     */
    private boolean Check(Integer i, Integer j){
        return (i < nfiles && i >= 0) && (j < ncolumnes && j >= 0) && (tauler[i][j].equals("#"));
    }

    /** Genera un hidato resolt.
     *
     * @param i Coordenada i del tauler
     * @param j Coordenada j del tauler
     * @param contador Valor que ha de prendre la cela[i][j]
     * @return Cert si s'ha generat correctament un hidato
     */
    private boolean Generar1(Integer i, Integer j, Integer contador){
        if(contador > maxceles*fillfactor){
            ValorMaxim = contador-1;
            return true;
        }
        if(!Check(i,j)) return false;
        else{
            System.out.printf("El contador es: %d de %f" , contador, maxceles*fillfactor);
            System.out.println();

            //Assigna el numero correcte a la cela correcte del tauler
            tauler[i][j]=Integer.toString(contador);

            HashSet<Pair<Integer,Integer>> visitades = new HashSet<>();

            //Calcula qui son els veins de la cela (i,j), en funcio del tipus de cela i adjacencia
            Vector<Pair<Pair<Integer, Integer>, Integer>> prob = Veins(new Pair<>(i,j));

            //Escull la seguent cela a analitzar en funcio d'una probabilitat
            Pair<Integer, Integer> nextcela = NextPos(new Pair<>(i, j), prob);

            //Marca la cela com a visitada
            visitades.add(nextcela);

            //Augmenta el contador, es a dir, el valor del numero a afegir
            Integer cont = ++contador;

            //Mentre no hagi acabat, fa el bucle
            while(!Generar1(nextcela.getKey(),nextcela.getValue(), cont)){

                //Si el numero de celes visitades es superior o igual al numero de veins
                //Es a dir, has provat tots els veins possibles i cap funciona, reseteges la cela
                //posant-la a # i retornes false
                if(visitades.size() >= numadj){
                    tauler[i][j]="#";
                    for (int in = 0; in < nfiles; ++in){
                        if (in > 0) System.out.println();
                        for (int jn = 0; jn < ncolumnes; ++jn){
                            if (jn > 0) System.out.printf(",%s", tauler[in][jn]);
                            else System.out.print(tauler[in][jn]);
                        }
                    }
                    System.out.println();
                    System.out.println();
                    return false;
                }
                System.out.printf("El contador es: %d de %f" , contador, maxceles*fillfactor);
                System.out.println();
                //Recalcula el vector de probabilitats
                Recalcular(nextcela,prob);

                //No recordo que fotia aixo
                for(int t = 0; t < 4; ++t){
                    prob.get(t).getKey();
                }

                //Calculem la nova cela
                nextcela = NextPos(new Pair<>(i, j), prob);

                //Marca la nova cela com a visitada
                visitades.add(nextcela);
                System.out.println(visitades.size());
            }
            return true;

        }
    }

    /**
     * Buida el tauler en funcio de ProbBlanc per tal que l'usuari pugui jugar l'hidato
     */
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
        //Double dd = r.nextDouble(100);
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

    private Integer NumeroAleatori(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dif) {
        if (Dif.equals("F")){
            nfiles = NumeroAleatori(3, 6);
            ncolumnes = NumeroAleatori(3, 6);
            ProbNumero = 45;
        }
        else if (Dif.equals("N")){
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
        maxceles = nfiles * ncolumnes;

        if(nfiles*ncolumnes >= 49) fillfactor = 0.8;
        else fillfactor = 0.9;

        tauler = new String[nfiles][ncolumnes];

        //Podem canviar la cela inicial per algo diferent
        Integer Iini = NumeroAleatori(0, 1);
        Integer Jini = NumeroAleatori(0, 1);
        tauler[Iini][Jini] = Integer.toString(1);

        //hidato.add(new Pair<>(new Pair<>(Iini,Jini),"1"));
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






    private Integer amplada;
    private Integer altura;
    private Double ratio;

    private Integer numero_maxim_celes = 10;

    /*
    ////////////////////*
    UNA CELA:
    -Integer CoordI
    -Integer CoordJ
    -String Valor
    -String Adjecencia

    ////////////////////
    */


    ArrayList<Cela> Cami_del_hidato = new ArrayList<Cela>();


    public void Generador(){
        for(int i = 0; i < numero_maxim_celes; ++i) {
            //
        }
    }


    public void aspect_ratio(){
        ratio = Double.valueOf(amplada/altura);
    }



}
