package Hidato;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Jugada {
    private String jugada;
    private Integer numcasella;
    private Integer coordx;
    private Integer coordy;
    private boolean invalid;
    private Generacio g;


    public void ComprovaJugada(Tauler t, Integer maxim){

        Integer max, min, nfil, ncol;
        min = 1;
        max = maxim; //g.GetValorMaxim();
        nfil = t.getNumFiles();
        ncol = t.getNumColum();
        if (numcasella <= min || numcasella > max) invalid = true;
        else{
            for (int i = 0; i < t.getNumFiles(); i++){
                for (int j = 0; j < t.getNumColum(); j++){
                    if (Integer.toString(numcasella).equals(t.consultarValCela(i,j))){
                        invalid = true;
                    }

                }
            }
        }

        if (coordx < 0 && coordx >= t.getNumFiles()) invalid = true;
        if (t.consultarValCela(coordx,coordy).equals("#")) invalid = true;
        if (t.consultarValCela(coordx,coordy).equals("*")) invalid = true;
        if (coordy < 0 && coordy >= t.getNumColum()) invalid = true;




    }

    public void Parametres(Vector<String> v){
        SetJugada(v.get(0));
        if (GetJugada().equals("NUMERO")){
            SetNumero(Integer.parseInt(v.get(1)));
            SetX(Integer.parseInt(v.get(2)));
            SetY(Integer.parseInt(v.get(3)));
        }



    }

    public void SetJugada(String jugada){
        this.jugada = jugada;
    }
    public String GetJugada(){
        return jugada;
    }
    public void SetNumero(Integer num){
        this.numcasella = num;
    }
    public Integer GetNumero(){
        return numcasella;
    }
    public void SetX(Integer x){
        this.coordx = x;
    }
    public Integer GetX(){
        return coordx;
    }
    public void SetY(Integer y){
        this.coordy = y;
    }
    public Integer GetY(){
        return coordy;
    }
    public boolean GetInvalid(){
        return invalid;
    }
    public void SetInvalid(boolean invalid){
        this.invalid = invalid;
    }

}
