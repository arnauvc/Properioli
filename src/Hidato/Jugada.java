package Hidato;

import java.util.Scanner;



public class Jugada {
    private String jugada;
    private Integer numcasella;
    private Integer coordx;
    private Integer coordy;

    private void UsageJugada(){
        System.out.println("Usage: String {GUARDAR, AJUDA, NUMERO}");
    }
    private void ComprovaJugada(){

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
            /*
            Comprovar que num sigui valid
            - que estigui dins de la sequencia de numeros a omplir
            - que no estigui ja ficat al tauler
             */

            System.out.println("Insereix coordenades de fila: ");
            SetX(input.nextInt());
            //Comprovar que X estigui entre [nfilmin, nfilmax]

            System.out.println("Insereix coordenades de columna: ");
            SetY(input.nextInt());
            //Comprovar que Y estigui entre [ncolmin,ncolmax]
        }


    }

    public void ComprovarJugada(){

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

