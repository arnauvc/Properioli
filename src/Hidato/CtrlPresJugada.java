package Hidato;

import java.util.*;
import java.io.*;

public class CtrlPresJugada {


    private void UsageJugada(){
        System.out.println("Usage: String {GUARDAR, AJUDA, NUMERO, SORTIR, RESET}");
    }
    Vector<String> v = new Vector<>();

    public void InteraccioJugada(Jugada j, Tauler t){
        Scanner input = new Scanner(System.in);
        String jugada;
        String numero = null;
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
            numero = input.nextLine();


            System.out.println("Insereix coordenades de fila: ");
            x = input.nextInt();

            System.out.println("Insereix coordenades de columna: ");
            y = input.nextInt();

            v.add(1, numero);
            v.add(2, Integer.toString(x));
            v.add(3, Integer.toString(y));

        }

        j.Parametres(v);

    }

    public void AvisaGestor(String[][] tauler){
        Gestor g = new Gestor();
        g.Interrupcio(v.get(0), tauler); //v.get(0) conte la jugada
    }



}
