package Hidato;

import java.util.*;
import java.io.*;
import Hidato.Jugada;

public class CtrlPresJugada {


    private void UsageJugada(){
        System.out.println("Usage: String {GUARDAR, AJUDA, NUMERO}");
    }

    public void InteraccioJugada(Jugada j){
        Scanner input = new Scanner(System.in);
        String jugada;
        Integer numero = null;
        Integer x = null;
        Integer y = null;

        System.out.println("Tria la propera jugada: ");
        jugada = input.nextLine();

        while (!jugada.equals("GUARDAR") && !jugada.equals("AJUDA") && !jugada.equals("NUMERO")) {
            UsageJugada();
            jugada = input.nextLine();
        }
        if (jugada.equals("NUMERO")) {
            System.out.println("Insereix numero de casella: ");
            numero = input.nextInt();


            System.out.println("Insereix coordenades de fila: ");
            x = input.nextInt();

            System.out.println("Insereix coordenades de columna: ");
            y = input.nextInt();
        }

        j.Parametres(jugada, numero, x, y);
    }



}
