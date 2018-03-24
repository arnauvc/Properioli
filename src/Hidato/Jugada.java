package Hidato;

import java.util.Scanner;



/*
    AVIS!!!:
    Nomes es un esquelet de prova mentre no tenim la creacio del hidato
    implementada, i mentre penso com fotre tot aixo mes eficient
*/

public class Jugada {
    private String jugada;
    private Integer numcasella;
    private Integer coordx;
    private Integer coordy;

    private void UsageJugada(){
        System.out.println("Usage: String {GUARDAR, AJUDA, NUMERO}");
    }


    public void DoJugada(){
        Scanner input = new Scanner(System.in);
        System.out.println("Tria la propera jugada: ");
        SetJugada(input.nextLine());

        while (!jugada.equals("GUARDAR") && !jugada.equals("AJUDA") && !jugada.equals("NUMERO")) {
            UsageJugada();
            SetJugada(input.nextLine());
        }
        if (jugada.equals("NUMERO")) {
            System.out.println("Insereix numero de casella: ");
            SetNumero(input.nextInt());
            //Aqui es comprova si el numero es correcte, pero em fa pal fer-ho ara

            System.out.println("Insereix coordenades de fila: ");
            SetX(input.nextInt());
            //Comprovar bla bla bla ...
            System.out.println("Insereix coordenades de columna: ");
            SetY(input.nextInt());
            //Comprovar bla bla bla ...
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
    
}

