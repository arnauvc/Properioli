package Hidato;

import Hidato.Tauler;

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

   public int ObrirFitxer (String nom) {//Nom ha de ser idHidato.txt
       this.nom = nom;
       try {
           x = new Scanner(new File(nom));
       } catch (FileNotFoundException e) {
           return 1; //error al obrir el ditxer;
       }
       return 0;
   }

   public int TencarFitxer(String nom) {
       if (nom != this.nom) {
           return 2;//error de tencament de fitxer
       }
       else {
           x.close();
           return 0;
       }
   }

   /*public int ComprovarHidato (){
        for (int i = 0; i < 4; i++) {

       }
   }
   */

}
