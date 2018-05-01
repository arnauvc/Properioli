package Hidato;


import javafx.scene.Camera;
import javafx.util.Pair;

import java.util.*;
import java.lang.Exception;

public class Generacio {

    private Integer MinI=0;
    private Integer MinJ=0;
    private Integer MaxI=0;
    private Integer MaxJ=0;
    private Integer numero_maxim_celes=0;

    private String[][] tauler;
    private String[][] solucio;

    private Integer numero;

    HashSet< Pair<Integer,Integer> > celes_ocupades = new HashSet<>() ;//(J,I)
    ArrayList<Cela> Cami_del_hidato = new ArrayList<>();


    private boolean GeneradorRecursiu(Cela cela_inicial){
        if(numero > numero_maxim_celes){return true;}
        if(celes_ocupades.contains(new Pair<>(cela_inicial.GetCoordJ(),cela_inicial.GetCoordI())) || cela_inicial.CoordJ < 0 ||cela_inicial.CoordI < 0){return false; }
        else{

            cela_inicial.ModificarValor(Integer.toString(numero));
            Cami_del_hidato.add(cela_inicial);
            celes_ocupades.add(new Pair<>(cela_inicial.GetCoordJ(),cela_inicial.GetCoordI()));
            UpdateMinMax(cela_inicial);
            //Calcula qui son els veins de la cela (i,j), en funcio del tipus de cela i adjacencia
            //Escull la seguent cela a analitzar en funcio d'una probabilitat
            ++numero;
            cela_inicial.Veins();
            Cela cela_nova = cela_inicial.NextCela();
            while(!GeneradorRecursiu(cela_nova)){
                if(!cela_inicial.UpdateProbabilitat(numero)){//False quan ja no tens mes celes disponibles
                    --numero;
                    Cami_del_hidato.remove(Cami_del_hidato.size()-1);
                    return false;
                }
                cela_nova = cela_inicial.NextCela();
            }
        }
        return true;
    }

    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dificultat){
        Integer ProbBlanc;
        Cela cela_inicial;
        switch (Tipuscela){
            case "Q":
                cela_inicial = new Quadrada(0,0,Tipusadj);
                break;
            case "T":
                cela_inicial = new Triangular(0,0, Tipusadj);
                break;
            case "H":
                cela_inicial = new Hexagonal(0,0, Tipusadj);
                break;
            default:
                cela_inicial = new Quadrada(0,0, Tipusadj);
                break;
        }

        switch (Dificultat){
            case "F":
                numero_maxim_celes = NumeroAleatori(3,5)*NumeroAleatori(3,5);
                ProbBlanc = 70;
                break;
            case "N":
                numero_maxim_celes = NumeroAleatori(6,7)*NumeroAleatori(6,7);
                ProbBlanc = 70;
                break;
            case "D":
                numero_maxim_celes = NumeroAleatori(8,9)*NumeroAleatori(8,9);
                ProbBlanc = 70;
                break;
            default:
                numero_maxim_celes = 20;
                ProbBlanc = 50;
                break;
        }
        UpdateMinMax(cela_inicial);
        numero = 1;
        if(GeneradorRecursiu(cela_inicial)){
            System.out.println("TOT BE");
            System.out.println();

            //Inicialitzar tauler i taulersolucio (matrius)
            /*
            Integer MinJParell = MinJ;
            Integer MinIParell = MinI;
            Integer NumFilesNou = GetNumFiles();
            Integer NumColumnesNou = GetNumColumnes();
            if(GetNumFiles()%2 != 0 && GetNumColumnes()%2 == 0){
                MinJParell = MinJ-1;
                NumFilesNou += 1;
            }
            else if(GetNumFiles()%2 == 0 && GetNumColumnes()%2 != 0){
                MinIParell = MinI-1;
                NumColumnesNou += 1;
            }

            System.out.printf("GetNumFiles: %s",GetNumFiles());
            System.out.println();
            System.out.printf("GetNumColumnes: %s",GetNumColumnes());
            System.out.println();
            System.out.printf("NumFilesNou: %s",NumFilesNou);
            System.out.println();
            System.out.printf("NumColumnesNou: %s",NumColumnesNou);
            System.out.println();
            */
            //Inicialitza la matriu
            tauler = new String[GetNumFiles()][GetNumColumnes()];
            solucio = new String[GetNumFiles()][GetNumColumnes()];


            //Posa tot a #
            for(int l = 0; l < GetNumFiles(); ++l){
                for(int k = 0; k < GetNumColumnes(); ++k) {
                    tauler[l][k] = "#";
                    solucio[l][k] = "#";
                }
            }
            //Passar de graf a matriu

            for(Cela c: Cami_del_hidato){
                tauler[c.GetCoordJ()][c.GetCoordI()] = c.getValor();
                solucio[c.GetCoordJ()][c.GetCoordI()] = c.getValor();

                //tauler[c.GetCoordJ()-MinJ][c.GetCoordI()-MinI] = c.getValor();
                //solucio[c.GetCoordJ()-MinJ][c.GetCoordI()-MinI] = c.getValor();
            }

            //Buidar el tauler
            Random ran = new Random();
            for(int l = 0; l < GetNumFiles(); ++l){
                for(int k = 0; k < GetNumColumnes(); ++k){
                    if(!tauler[l][k].equals("#") && !tauler[l][k].equals("1") && !tauler[l][k].equals(Integer.toString(numero_maxim_celes-1))){
                        Integer ra = ran.nextInt(100);
                        if(ra < ProbBlanc){
                            tauler[l][k]="?";
                        }
                    }
                }
            }

            System.out.printf("TIPUS: %s  ADJACENCIA: %s DIFICULTAT: %s", Tipuscela, Tipusadj, Dificultat);
            System.out.println();
            //Aqui va la funcio de posar * pels buits
            return tauler;
        }
        else{
            System.out.println("CACA DURA");
            System.out.println();
            return new String[0][0];
        }
    }


     /*
            ArrayList<Cela> veins_meus = cela_inicial.Veins();
            Integer posi = rat.nextInt(veins_meus.size());
            Integer con = 0;
            while(con < veins_meus.size()){
                if(!celes_ocupades.contains(new Pair<>(veins_meus.get(posi).GetCoordI(),veins_meus.get(posi).GetCoordJ())) ){
                    cela_inicial = veins_meus.get(posi);
                    break;
                }
                posi = rat.nextInt(veins_meus.size());
                ++con;
            }
            */


    /*
    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dificultat){
        Integer ProbBlanc;
        Cela cela_inicial;
        HashSet< Pair<Integer,Integer> > celes_ocupades = new HashSet<>() ;
        ArrayList<Cela> Cami_del_hidato = new ArrayList<>();
        switch (Tipuscela){
            case "Q":
                cela_inicial = new Quadrada(50,50,Tipusadj);
                break;
            case "T":
                cela_inicial = new Triangular(50,50, Tipusadj);
                break;
            case "H":
                cela_inicial = new Hexagonal(50,50, Tipusadj);
                break;
            default:
                cela_inicial = new Quadrada(50,50, Tipusadj);
                break;
        }

        switch (Dificultat){
            case "F":
                numero_maxim_celes = NumeroAleatori(3,5)*NumeroAleatori(3,5);
                ProbBlanc = 70;
                break;
            case "N":
                numero_maxim_celes = NumeroAleatori(6,7)*NumeroAleatori(6,7);
                ProbBlanc = 70;
                break;
            case "D":
                numero_maxim_celes = NumeroAleatori(8,9)*NumeroAleatori(8,9);
                ProbBlanc = 70;
                break;
            default:
                numero_maxim_celes = 20;
                ProbBlanc = 50;
                break;
        }
        cela_inicial.SetCoordI(50);
        cela_inicial.SetCoordJ(50);

        cela_inicial.ModificarValor(Integer.toString(1));
        Cami_del_hidato.add(cela_inicial);
        celes_ocupades.add(new Pair<>(cela_inicial.GetCoordI(), cela_inicial.GetCoordJ()));
        UpdateMinMax(cela_inicial);
        Random rat = new Random();
        for(int numero = 2; numero < numero_maxim_celes; ++numero){


            String direccio; // Vertical o Horitzontal
            if(aspect_ratio() >= 1) direccio = "H";
            else if (aspect_ratio() == 1){
                if(rat.nextInt(2) < 1) direccio = "H";
                else direccio = "V";
            }
            else direccio = "V";
            ArrayList<Cela> veins_meus = cela_inicial.Veins(direccio);



            Integer tamany = 0;
            while(tamany < veins_meus.size()){
                if(!celes_ocupades.contains(new Pair<>(veins_meus.get(tamany).GetCoordI(),veins_meus.get(tamany).GetCoordJ())) ){
                    cela_inicial = veins_meus.get(tamany);
                    break;
                }
                ++tamany;
            }

            cela_inicial.ModificarValor(Integer.toString(numero));
            celes_ocupades.add(new Pair<>(cela_inicial.GetCoordI(),cela_inicial.GetCoordJ()));
            UpdateMinMax(cela_inicial);
            Cami_del_hidato.add(cela_inicial);
        }

        tauler = new String[GetNumFiles()][GetNumColumnes()];
        solucio = new String[GetNumFiles()][GetNumColumnes()];

        //Inicialitzar tauler i taulersolucio (matrius)
        for(int l = 0; l < GetNumFiles(); ++l){
            for(int k = 0; k < GetNumColumnes(); ++k) {
                tauler[l][k] = "#";
                solucio[l][k] = "#";
            }
        }
        //Passar de graf a matriu
        for(Cela c: Cami_del_hidato){
            tauler[c.GetCoordJ()-MinJ][c.GetCoordI()-MinI] = c.getValor();
            solucio[c.GetCoordJ()-MinJ][c.GetCoordI()-MinI] = c.getValor();
        }

        //Buidar el tauler
        Random ran = new Random();
        for(int l = 0; l < GetNumFiles(); ++l){
            for(int k = 0; k < GetNumColumnes(); ++k){
                if(!tauler[l][k].equals("#") && !tauler[l][k].equals("1") && !tauler[l][k].equals(Integer.toString(numero_maxim_celes-1))){
                    Integer ra = ran.nextInt(100);
                    if(ra < ProbBlanc){
                        tauler[l][k]="?";
                    }
                }
            }
        }
        //Aqui va la funcio de posar * pels buits
        return tauler;
    }
    */


    private void UpdateMinMax(Cela celaini){
        if(celaini.GetCoordI() > MaxI) MaxI = celaini.GetCoordI();
        if(celaini.GetCoordI() < MinI) MinI = celaini.GetCoordI();
        if(celaini.GetCoordJ() > MaxJ) MaxJ = celaini.GetCoordJ();
        if(celaini.GetCoordJ() < MinJ) MinJ = celaini.GetCoordJ();
    }

    private Integer NumeroAleatori(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }

    private Double aspect_ratio(){ return (double) (GetNumFiles() / GetNumColumnes()); }

    private Integer GetNumFiles(){ return (MaxJ-MinJ)+1; }

    private Integer GetNumColumnes(){ return (MaxI-MinI)+1; }

    public String[][]GetSolucio(){ return solucio; }

    public Integer GetValorMaxim(){
        return numero_maxim_celes-1;
    }

    /*

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


    private boolean Check(Integer i, Integer j){
        return (i < nfiles && i >= 0) && (j < ncolumnes && j >= 0) && (tauler[i][j].equals("#"));
    }


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


    */



}
