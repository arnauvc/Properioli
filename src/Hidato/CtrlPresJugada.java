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


    public void MostrarPuntuacio(Double temps){
        System.out.printf("Has trigat: %f\n", temps);
        System.out.println("Has guanyat!");
    }

    public void MostrarResolucio(String[][] hidato_resolt, Tauler t){
        for (int i = 0; i < t.getNumFiles(); ++i) {
            for (int l = 0; l < t.getNumColum(); ++l) {
                if (l > 0) System.out.print(",");
                System.out.print(t.consultarValCela(i, l));
            }
            System.out.println();
        }
    }

    public void MostrarTauler(Tauler t){
        for (int i = 0; i < t.getNumFiles(); ++i) {
            for (int l = 0; l < t.getNumColum(); ++l) {
                if (l > 0) System.out.print(",");
                System.out.print(t.consultarValCela(i, l));
            }
            System.out.println();
        }
    }

    public void MostrarAjuda(String[][] hidato_ajuda, Tauler t){
        System.out.println("Ajuda:");
        for (int i = 0; i < t.getNumFiles(); i++){
            for (int k = 0; k < t.getNumColum(); k++){
                if (k > 0) System.out.print(",");
                System.out.print(hidato_ajuda[i][k]);
            }
            System.out.println();
        }
        System.out.println();
    }



}
