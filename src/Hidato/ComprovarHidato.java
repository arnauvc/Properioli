package Hidato;

import javafx.scene.transform.MatrixType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ComprovarHidato {

   private Scanner x;
   private String nom;
   private String Tcela;
   private String adjacencies;
   private String files;
   private String columnes;
   private Vector<Vector<String>> Tauler;

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

   private int ObrirFitxer (String nom) {//Nom ha de ser idHidato.txt
       this.nom = nom;
       try {
           x = new Scanner(new File(nom));
       } catch (FileNotFoundException e) {
           return 1; //error al obrir el ditxer;
       }
       return 0;
   }

   private int TencarFitxer(String nom) {
       if (nom != this.nom) {
           return 2;//error de tencament de fitxer
       }
       else {
           x.close();
           return 0;
       }
   }

   private int TransformarHidato(String nom){
       if (ObrirFitxer(nom) != 0) return ObrirFitxer(nom);
       Tauler = new Vector<Vector<String>>();
       Tcela = x.next();x.next();
       adjacencies = x.next();x.next();
       files = x.next();x.next();
       columnes = x.next();
       String aux;
       for (int i = 0; i < Integer.parseInt(files); i++) {
           for (int j = 0; j < Integer.parseInt(columnes); j++) {
               if (x.hasNext()) {
                   aux = x.next();
                   if (aux != ",") {
                       Tauler.get(i).set(j, aux);
                       if (1)
                   }
               }
           }
       }
       if (x.hasNext()) return 3; //Error no te mateixes files i columnes;
       if (TencarFitxer(nom) != 0) return TencarFitxer(nom);
       return 0;
   }

   private int ComprovarAdjacencia (Vector<Vector<String>> matriu, int f, int c) {

   }

   public int ComprovarHidato (String nom) {
       if (TransformarHidato(nom) != 0) return TransformarHidato(nom);

       return 0;
   }

}
