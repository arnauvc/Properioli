package Hidato;

import java.util.*;
import java.io.*;

public class CtrlPresJugada {


    private void UsageJugada(){
        System.out.println("Usage: String {NUMERO, AJUDA, GUARDAR, RESET, SORTIR}");
    }
    private  void UsageNumero(){
        System.out.println("Usage: Pot ser un numero o un interrogant per deixar la casella buida");
    }
    private  void UsageFila(){
        System.out.println("Usage: Valor numeric per indicar la fila [0..n] n=nfiles-1");
    }
    private  void UsageColumna(){
        System.out.println("Usage: Valor numeric per indicar la columna [0..n] n=ncols-1");
    }

    private boolean isNumeric(String s){
        try{
            Integer d = Integer.parseInt(s);
        }
        catch (NumberFormatException nfe){
            return  false;
        }
        return  true;
    }


    Vector<String> v = new Vector<>();

    public void InteraccioJugada(Jugada j, Tauler t){
        Scanner input = new Scanner(System.in);
        String jugada;
        String numero = null;
        String x = null;
        String y = null;

        System.out.println("Tria la propera jugada: ");
        UsageJugada();
        jugada = input.nextLine();
        while (!jugada.equals("GUARDAR") && !jugada.equals("AJUDA") && !jugada.equals("NUMERO")
                && !jugada.equals("SORTIR") && !jugada.equals("RESET")) {
            System.out.println("Tria la propera jugada: ");
            UsageJugada();
            jugada = input.nextLine();

        }
        v.add(0, jugada);
        if (jugada.equals("NUMERO")) {
            System.out.println("Insereix numero de casella: ");
            UsageNumero();
            numero = input.nextLine();
            while (!isNumeric(numero) && !numero.equals("?")){
                System.out.println("Insereix numero de casella: ");
                UsageNumero();
                numero = input.nextLine();
            }


            System.out.println("Insereix coordenades de fila: ");
            UsageFila();
            x = input.nextLine();
            while (!isNumeric(x)){
                System.out.println("Insereix coordenades de fila: ");
                UsageFila();
                x = input.nextLine();
            }

            System.out.println("Insereix coordenades de columna: ");
            UsageColumna();
            y = input.nextLine();
            while (!isNumeric(y)){
                System.out.println("Insereix coordenades de columna: ");
                UsageColumna();
                y = input.nextLine();
            }

            v.add(1, numero);
            v.add(2, x);
            v.add(3, y);

        }

        j.Parametres(v);
    }


    public void MostrarPuntuacio(Double temps){
        System.out.printf("Has trigat: %f\n", temps);
        System.out.println("Has guanyat!");
    }

    public void MostrarResolucio(String[][] hidato_resolt, Tauler t){
        boolean zeros = false;
        if (hidato_resolt != null) {
            for (int i = 0; i < t.getNumFiles(); ++i) {
                for (int l = 0; l < t.getNumColum(); ++l) {
                    if (hidato_resolt[i][l].equals("0")) zeros = true;
                }
            }
            if (!zeros){
                for (int i = 0; i < t.getNumFiles(); ++i) {
                    for (int l = 0; l < t.getNumColum(); ++l) {
                        if (l > 0) System.out.print(",");
                        System.out.print(hidato_resolt[i][l]);
                    }
                    System.out.println();
                }
            }
            else System.out.println("No hi ha solucio");
        }
        else System.out.println("No hi ha solucio");
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
