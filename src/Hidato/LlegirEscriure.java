package Hidato;

import java.io.*;
import java.util.Scanner;

public class LlegirEscriure {
    public boolean ObrirFitxerEscriptura (String nom, BufferedWriter escriptor, boolean sobreescriure) throws Exception {//Nom ha de ser idHidato.txt
        File fitxer = new File(nom);
        FileWriter filew;
        if (!fitxer.exists()) {
            try {
                fitxer.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            if (sobreescriure) filew = new FileWriter(fitxer);
            else filew = new FileWriter(fitxer, true);
            escriptor = new BufferedWriter(filew);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean TancarFitxerEscriptura(BufferedWriter escriptor) throws Exception{
        try {
            escriptor.close();
        } catch (FileNotFoundException e) {
            System.out.println("No es pot tencar");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean ObrirFitxerLectura (String nom, Scanner x) throws Exception{//Nom ha de ser idHidato.txt
        try {
            x = new Scanner(new File(nom));
        } catch (FileNotFoundException e) {
            System.out.println("No ho trobo");
            return false; //No existeix el fitxer
        }
        return true;
    }

    public boolean TancarFitxerLectura(Scanner x) {
        x.close();
        return true;
    }
}
