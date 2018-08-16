package Hidato;

import javafx.util.Pair;

import javax.swing.text.html.MinimalHTMLWriter;
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

    private Integer numero = 0;
    private Integer maxfila;
    private Integer maxcolumna;

    HashSet< Pair<Integer,Integer> > celes_ocupades = new HashSet<>() ;//(J,I)
    ArrayList<Cela> Cami_del_hidato = new ArrayList<>();

    private boolean CheckCela(Cela cel){

        if(celes_ocupades.contains(new Pair<>(cel.GetCoordJ(),cel.GetCoordI()))
            || cel.CoordJ < 0 ||cel.CoordI < 0 || cel.CoordJ > maxfila || cel.CoordI > maxcolumna){
            return false;
        }
        else {
            return true;
        }
    }


    private boolean GeneradorRecursiu(Cela cela_inicial){
        if(numero > numero_maxim_celes){return true;}
        if(!CheckCela(cela_inicial)) return false;
        else{

            cela_inicial.ModificarValor(Integer.toString(numero));
            Cami_del_hidato.add(cela_inicial);
            celes_ocupades.add(new Pair<>(cela_inicial.GetCoordJ(),cela_inicial.GetCoordI()));

            //UpdateMinMax(cela_inicial);
            ++numero;

            String direccio;
            if(aspect_ratio() > 1.0) direccio = "H";
            else if (aspect_ratio() == 1.0) direccio = "D";
            else direccio = "V";
            Integer MinIAntic = MinI;
            Integer MinJAntic = MinJ;
            Integer MaxIAntic = MaxI;
            Integer MaxJAntic = MaxJ;
            UpdateMinMax(cela_inicial);
            cela_inicial.Veins(direccio);

            Cela cela_nova = cela_inicial.NextCela();
            while(!GeneradorRecursiu(cela_nova)){
                if(!cela_inicial.UpdateProbabilitat()){//False quan ja no tens mes celes disponibles
                    --numero;
                    Cami_del_hidato.remove(Cami_del_hidato.size()-1);
                    celes_ocupades.remove(new Pair<>(cela_inicial.GetCoordJ(),cela_inicial.GetCoordI()));
                    MinI = MinIAntic;
                    MinJ = MinJAntic;
                    MaxI = MaxIAntic;
                    MaxJ = MaxJAntic;

                    return false;
                }
                cela_nova = cela_inicial.NextCela();
            }
        }
        return true;
    }

    public String[][] GenerarHidato(String Tipuscela, String Tipusadj, String Dificultat) {
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
                numero_maxim_celes = NumeroAleatori(9,30);
                ProbBlanc = 70;
                if(numero_maxim_celes < 17){
                    maxfila = 5;
                    maxcolumna = 6;
                }
                else{
                    maxfila =7;
                    maxcolumna = 7;
                }
                break;
            case "N":
                numero_maxim_celes = NumeroAleatori(31,60);
                if(numero_maxim_celes < 48){
                    maxfila = 8;
                    maxcolumna = 8;
                }
                else{
                    maxfila = 9;
                    maxcolumna = 9;
                }
                ProbBlanc = 70;
                break;
            case "D":
                numero_maxim_celes = NumeroAleatori(61,90);
                if(numero_maxim_celes < 75){
                    maxfila = 10;
                    maxcolumna = 10;
                }
                else{
                    maxfila = 11;
                    maxcolumna = 11;
                }

                ProbBlanc = 55;
                break;
            default:
                numero_maxim_celes = 25;
                maxfila =6;
                maxcolumna = 6;
                ProbBlanc = 50;
                break;
        }
        UpdateMinMax(cela_inicial);
        numero = 1;
        if(GeneradorRecursiu(cela_inicial)){

            //Inicialitza la matriu
            tauler = new String[GetNumFiles()][GetNumColumnes()];
            solucio = new String[GetNumFiles()][GetNumColumnes()];
            //Passar de graf a matriu

            for(Cela c: Cami_del_hidato){
                tauler[c.GetCoordJ()][c.GetCoordI()] = c.getValor();
                solucio[c.GetCoordJ()][c.GetCoordI()] = c.getValor();
            }

            //Buidar el tauler
            Random ran = new Random();
            for(int l = 0; l < GetNumFiles(); ++l){
                for(int k = 0; k < GetNumColumnes(); ++k){
                    if(tauler[l][k] == null){
                        tauler[l][k] = "#";
                        solucio[l][k] = "#";
                    }
                    if(!tauler[l][k].equals("#") && !tauler[l][k].equals("1") && !tauler[l][k].equals(Integer.toString(numero_maxim_celes))){
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
        else{
            return new String[0][0];
        }
    }

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

}
