package Hidato;


import javafx.util.Pair;

import java.util.*;

public class Generacio {

        private ArrayList<Cela> solucio;
        private String[][] tauler;
        private ArrayList<Pair<Pair<Integer,Integer>, String>> hidato = new ArrayList<>(); //(x,y),Valor
        private HashMap<Pair<Integer,Integer>,String> Hidato = new HashMap<>();
        private Random r = new Random();

        private Integer nfiles;
        private Integer ncolumnes;
        private Double fillfactor = 0.9;

        private Integer maxceles;
        private String tipuscela;
        private String tipusadj;
        private Integer numadj; //Conte el numero de veins que pot tenir una cela en funcio de l'adjecencia
        private String dificultat;
        private Integer numcostats;//segons tipuscela i tipusadj

        private Integer ProbNumero;
        private Integer ProbBlanc;

        private Integer maxI;
        private Integer minI;
        private Integer maxJ;
        private Integer minJ;

        private Vector<Integer> probs;

    private void Check(Integer i, Integer j){
        if(i > maxI) maxI = i;
        if(i < minI) minI = i;
        if(j > maxJ) maxJ = j;
        if(j < minJ) minJ = j;
    }

    private boolean Check1(Integer i, Integer j){
        return (i < nfiles && i >= 0) && (j < ncolumnes && j >= 0) && (tauler[i][j].equals("#"));
    }

    public boolean Generar(Integer i, Integer j, Integer contador){
        //Pair<Integer,Integer>nextcela = NextPos(new Pair(x,y));
        if(contador.equals(maxceles)){return true;}
        else {
            //hidato.add(contador,new Pair(new Pair(i, j), Integer.toString(contador)));
            Hidato.put(new Pair<>(i,j), Integer.toString(contador));
            Check(i,j);
            Pair<Integer, Integer> nextcela = NextPos(new Pair<>(i, j));
            while(Hidato.containsKey((nextcela))){
                nextcela = NextPos(new Pair<>(i, j));
            }
            Generar(nextcela.getKey(), nextcela.getValue(), ++contador);
        }
        return true;
    }

    private boolean Generar1(Integer i, Integer j, Integer contador){
        //Pair<Integer,Integer>nextcela = NextPos(new Pair(x,y));
        if(contador >= maxceles*fillfactor){return true;}
        if(!Check1(i,j)){//retorna fals si la casella te un numero o esta fora dels limits
            System.out.println(contador);
            return false;
        }
        else{
            hidato.add(new Pair<>(new Pair<>(i, j), Integer.toString(contador)));
            tauler[i][j]=Integer.toString(contador);
            //Hidato.put(new Pair<>(i,j), Integer.toString(contador));
            //Check(i,j);
            HashSet<Pair<Integer,Integer>> visitades = new HashSet<>();
            Pair<Integer, Integer> nextcela = NextPos(new Pair<>(i, j));
            visitades.add(nextcela);
            Integer cont = ++contador;
            while(!Generar1(nextcela.getKey(),nextcela.getValue(), cont)){
                if(visitades.size() >= numadj) return false;
                nextcela = NextPos(new Pair<>(i, j));
                visitades.add(nextcela);
            }
            return true;
        }
    }

    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dif){
        if (Dif.equals("FACIL")){
            maxceles = NumeroAleatori(4, 12);
            nfiles = NumeroAleatori(3, 4);
            ncolumnes = NumeroAleatori(3,4);
            ProbNumero = 98;
        }
        else if (Dif.equals("NORMAL")){
            maxceles = NumeroAleatori(16, 25);
            nfiles = NumeroAleatori(5, 6);
            ncolumnes = NumeroAleatori(5,6);
            ProbNumero = 94;
        }
        else {
            maxceles = NumeroAleatori(25,49);
            nfiles = NumeroAleatori(7, 8);
            ncolumnes = NumeroAleatori(7,8);
            ProbNumero = 90;
        }

        tipuscela = Tipuscela;
        tipusadj = Tipusadj;
        numadj = 4;//si tipusadj es C
        dificultat = Dif;
        ProbBlanc = 100 - ProbNumero;
        numcostats = 4;

        maxI = 0;
        minI = 0;
        maxJ = 0;
        minJ = 0;

        probs= new Vector<Integer>(4);
        probs.add(25);
        probs.add(25);
        probs.add(25);
        probs.add(25);

        tauler = new String[nfiles][ncolumnes];
        Integer Iini = NumeroAleatori(0, nfiles-1);
        Integer Jini = NumeroAleatori(0, ncolumnes-1);
        tauler[Iini][Jini] = Integer.toString(1);

        hidato.add(new Pair<>(new Pair<>(Iini,Jini),"1"));
        maxceles = nfiles*ncolumnes;

        for (int i = 0; i < nfiles; ++i){
            for (int j = 0; j < ncolumnes; ++j){
                tauler[i][j] = "#";
            }
        }
        System.out.println(nfiles);
        System.out.println(ncolumnes);
        System.out.println("Cela ini");
        System.out.println(Iini);
        System.out.println(Jini);
        if(Generar1(Iini,Jini, 1)){
            for (int i = 0; i < nfiles; ++i){
                if (i > 0) System.out.println();
                for (int j = 0; j < ncolumnes; ++j){
                    if (j > 0) System.out.printf(",%s", tauler[i][j]);
                    else System.out.print(tauler[i][j]);
                }
            }
            System.out.println();
        }
        else  System.out.println("CACA");
        /*
        Hidato.put(new Pair<>(0,0), "1");
        Generar(0,-1,2);
        System.out.println(Hidato.values());

        Integer nfil = (maxI-minI)+1;
        Integer ncol = (maxJ-minJ)+1;

        tauler = new String[nfil][ncol];

        System.out.println(nfil);
        System.out.println(ncol);

        for (int i = 0; i < nfil; ++i){
            for (int j = 0; j < ncol; ++j){
                tauler[i][j] = "#";
            }
        }



        for (Map.Entry<Pair<Integer,Integer>, String> entry : Hidato.entrySet()) {
            Pair<Integer,Integer> key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key);
            System.out.println(value);
            System.out.println();
            tauler[key.getKey()+nfil-1][key.getValue()+ncol-1] = value;
        }




        for (int i = 0; i < nfil; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncol; ++j){
                if (j > 0) System.out.printf(",%s", tauler[i][j]);
                else System.out.print(tauler[i][j]);
            }
        }
        System.out.println();
        */
        //Convertiat llista hidato en tauler[][]
        return tauler;
    }

    private Double aspectratio(Integer i, Integer j){//aquesta funcio s'ha de millorar en funcionalitat
        return Double.valueOf(i/j);
    }

    private Pair<Integer,Integer> NextPos(Pair<Integer,Integer> posactual){

        //Assumint adjacencia costat, celes quadrades
        Pair<Integer, Integer> p;

        Integer in = r.nextInt(100);
        if(in < probs.get(0)){ p = new Pair<>(posactual.getKey()-1,posactual.getValue());}//TOP
        else if (in < probs.get(0) + probs.get(1)){p = new Pair<>(posactual.getKey(),posactual.getValue()+1);}//RIGHT
        else if (in < probs.get(0) + probs.get(1) + probs.get(2)){p = new Pair<>(posactual.getKey()+1,posactual.getValue());}//BOT
        else {p = new Pair<>(posactual.getKey(),posactual.getValue()-1);}//LEFT
        return p;
    }

    private boolean NextType(){ //True es un numero, false es Cela en blanc
        Random rand = new Random();
        Integer ra = rand.nextInt(100);
        if(ra < ProbNumero) return true;
        else return false;
    }








    public String[][] GenerarHidatoUsuari(Integer nfil, Integer ncol){
        String[][] hidato = new String[nfil][ncol];
        Scanner input = new Scanner(System.in);
        System.out.printf("Genera el nou hidato amb numero de files: %d i numero de columnes: %d\n", nfil, ncol);

        for (int i = 0; i < nfil; ++i){
            for (int j = 0; j < ncol; ++j){
                hidato[i][j] = input.next();
            }
        }
        for (int i = 0; i < nfil; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncol; ++j){
                if (j > 0) System.out.printf(",%s", hidato[i][j]);
                else System.out.print(hidato[i][j]);
            }
        }
        System.out.println();

        return hidato;
    }


    public String[][] GenerarHidatoAlgorisme(String tipuscela, String tipusadj, String dif){
        int nfil = 0, ncol = 0;
        if (dif.equals("FACIL")){
            nfil = NumeroAleatori(3, 6);
            ncol = NumeroAleatori(3, 6);

        }
        else if (dif.equals("NORMAL")){
            nfil = NumeroAleatori(6, 8);
            ncol = NumeroAleatori(6, 8);

        }
        else if (dif.equals("DIFICIL")){
            nfil = NumeroAleatori(8, 10);
            ncol = NumeroAleatori(8, 10);

        }
        Partida p = new Partida();
        p.SetFiles(nfil);
        p.SetColumnes(ncol);

        /*
        Els dos bucles seguents son per comprovar, el segon es pot esborrar, el primer no sino
        peta perque la matriu estari buida i faig un return de la matriu
        */
        System.out.printf("nfil: %d, ncol: %d\n", nfil, ncol);
        String[][] hidato = new String[nfil][ncol];
        for (int i = 0; i < nfil; ++i){
            for (int j = 0; j < ncol; ++j){
                hidato[i][j] = "?";
            }
        }
        for (int i = 0; i < nfil; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncol; ++j){
                if (j > 0) System.out.printf(",%s", hidato[i][j]);
                else System.out.print(hidato[i][j]);
            }
        }
        System.out.println();

        return hidato;
    }



    private Integer NumeroAleatori(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }


}
