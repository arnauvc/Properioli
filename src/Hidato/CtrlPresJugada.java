package Hidato;

import java.util.*;
import java.io.*;

public class CtrlPresJugada {


    private void UsageJugada(){
        System.out.println("Usage: String {GUARDAR, AJUDA, NUMERO, SORTIR, RESET}");
    }

    public void InteraccioJugada(Jugada j){
        Scanner input = new Scanner(System.in);
        Vector<String> v = new Vector<String>();
        String jugada;
        Integer numero = null;
        Integer x = null;
        Integer y = null;

        System.out.println("Tria la propera jugada: ");
        jugada = input.nextLine();

        while (!jugada.equals("GUARDAR") && !jugada.equals("AJUDA") && !jugada.equals("NUMERO")
                && !jugada.equals("SORTIR") && !jugada.equals("RESET")) {
            UsageJugada();
            jugada = input.nextLine();

        }
        v.add(0, jugada);
        if (jugada.equals("NUMERO")) {
            System.out.println("Insereix numero de casella: ");
            numero = input.nextInt();


            System.out.println("Insereix coordenades de fila: ");
            x = input.nextInt();

            System.out.println("Insereix coordenades de columna: ");
            y = input.nextInt();

            v.add(1, Integer.toString(numero));
            v.add(2, Integer.toString(x));
            v.add(3, Integer.toString(y));

        }

        else if (jugada.equals("GUARDAR") || jugada.equals("SORTIR")){
            Gestor g = new Gestor();
            //g.Interrupcio(v.get(0)); //v.get(0) conte la jugada
        }

        j.Parametres(v);

    }



}
