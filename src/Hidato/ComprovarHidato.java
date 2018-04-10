package Hidato;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class ComprovarHidato {

   /*private Scanner x;
   private String nom;*/
   private String adjacencies;
   private int files;
   private int columnes;
   /*private String[][] Tauler;
   private ArrayList<Pair<Integer , Integer>> Numeros;*/

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(String.valueOf(cadena));
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

   /*private boolean ObrirFitxer (String nom) {//Nom ha de ser idHidato.txt
       this.nom = nom;
       try {
           x = new Scanner(new File(nom));
       } catch (FileNotFoundException e) {
           System.out.println("No ho trobo");
           return false;
       }
       return true;
   }

   private boolean TancarFitxer(String nom) {
       if (nom != this.nom) {
           return false;
       } else {
           x.close();
           return true;
       }
   }

   private boolean TransformarHidato(String nom){
        Numeros = new ArrayList<Pair<Integer , Integer>>();
       if (!ObrirFitxer(nom)) return false; //error al obrir el ditxer;
       String line = null;
       char aux;
       line = x.next();
       if (line.length() > 8 || line.length() < 7) return false;
       else {
           Tcela = line.charAt(0);
           if (line.length() == 8) {
               adjacencies = line.substring(2, 3);
               files = Integer.parseInt(String.valueOf(line.charAt(5)));
               columnes = (2*(Integer.parseInt(String.valueOf(line.charAt(7)))))-1;
           }
           else {
               adjacencies = line.substring(2, 2);
               files = Integer.parseInt(String.valueOf(line.charAt(4)));
               columnes = (2*(Integer.parseInt(String.valueOf(line.charAt(6)))))-1;
           }
       }
       Tauler = new String[Integer.parseInt(String.valueOf(files))][Integer.parseInt(String.valueOf(columnes))];
       Pair<Integer, Integer> p;
       for (int i = 0; i < files; i++) {
           if (x.hasNext()) line = x.next();
           for (int j = 0; j < columnes; j++) {
               aux = line.charAt(j);
               if (aux != ',') {
                   Tauler[i][j] = String.valueOf(aux);
                   if (isNumeric(String.valueOf(aux))) {
                       p = new Pair<Integer, Integer>(i, j);
                       Numeros.add(p);
                   }
               }
           }
       }
       if (x.hasNext()) return false; //Error no te mateixes files i columnes;
       if (!TancarFitxer(nom)) return false;//error de tencament de fitxer
       return true;
   }*/

   private boolean ComprovarAdjacencia (Integer f, Integer c, Integer valor, String[][] Tauler) {
        Integer numero = -1;
       for (int i = f-1; i <= f+1; i++) {
           for (int j = c-1; j <= c+1; j++) {
               if (i >= 0 && j >= 0 && i <= (files-1) && j <= (columnes-1)) {
                   System.out.println("HOLAAAA");
                   if (isNumeric(Tauler[i][j]) && (i != f || j != c)) {
                       numero = Integer.parseInt(Tauler[i][j]);
                       System.out.format("%d", numero);
                       if ((valor % 2) == 0) {
                           if ((numero % 2) == 0) return false;
                       }
                       else {
                           if ((numero % 2) != 0) return false;
                       }
                   }
               }
           }
       }
       return true;
   }

   private boolean ComprovarIgualtat (int i, Integer valor, String[][] Tauler, ArrayList<Pair<Integer , Integer>> Numeros) {
        if (i >= Numeros.size()) return true;
        if (valor.equals(Integer.parseInt(Tauler[Numeros.get(i).getKey()][Numeros.get(i).getValue()]))) return false;
        ComprovarIgualtat(i+1, valor, Tauler, Numeros);
        return true;
   }

   public int Comprovar (String[][] Tauler, int f, int c, String adj , ArrayList<Pair<Integer , Integer>> Numeros) {
        files = f;
        columnes = c;
        adjacencies = adj;
        Integer valor = Integer.valueOf(Tauler[Numeros.get(0).getKey()][Numeros.get(0).getValue()]);
       if (!ComprovarIgualtat(1, valor, Tauler, Numeros)) return 21; // Numeros repetits en l'hidato
       if (adj.equals("C")) {
           for (int i = 0; i < Numeros.size(); i++) {
               valor = Integer.valueOf(Tauler[Numeros.get(i).getKey()][Numeros.get(i).getValue()]);
               if (!ComprovarAdjacencia(Numeros.get(i).getKey(), Numeros.get(i).getValue(), valor, Tauler)) return 22; //Adjacencies incorrectes
           }
       }
       return 0;
   }

}
