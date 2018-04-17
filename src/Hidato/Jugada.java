package Hidato;

import java.util.Scanner;

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
        System.out.printf("maxim: %d\n", max);
        nfil = t.getNumFiles();
        ncol = t.getNumColum();
        System.out.printf("nfil: %d\n, ncol: %d\n", nfil, ncol);
        if (numcasella <= min && numcasella > max) invalid = true;
        else{
            for (int i = 0; i < t.getNumFiles(); i++){
                for (int j = 0; j < t.getNumColum(); j++){
                    if (Integer.toString(numcasella).equals(t.consultarValCela(i,j))){
                        System.out.printf("Valor cela: %s\n: ", t.consultarValCela(i,j));
                        invalid = true;
                    }

                }
            }
        }

        if (coordx < 0 && coordx >= t.getNumFiles()) invalid = true;
        if (t.consultarValCela(coordx,coordy).equals("#")){
            //System.out.printf("Valor cela: %s\n: ", t.consultarValCela(i,j));
            invalid = true;
        }
        if (t.consultarValCela(coordx,coordy).equals("*")){
            //System.out.printf("Valor cela: %s\n: ", t.consultarValCela(i,j));
            invalid = true;
        }
        if (coordy < 0 && coordy >= t.getNumColum()) invalid = true;




    }

    public void Parametres(String jugada, Integer numero, Integer x, Integer y){
        SetJugada(jugada);
        SetNumero(numero);
        SetX(x);
        SetY(y);
    }

    public void SetJugada(String jugada){
        //Si jugada = AJUDA o GUARDAR, no cal cap mes Set
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
