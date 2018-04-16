package Hidato;

import java.io.*;
import java.util.Scanner;

public class LlegirEscriure {
    public BufferedWriter ObrirFitxerEscriptura (String nom, boolean sobreescriure) throws Exception {//Nom ha de ser idHidato.txt
        File fitxer = new File(nom);
        FileWriter filew;
        BufferedWriter escriptor = null;
        if (!fitxer.exists()) {
            try {
                fitxer.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            if (sobreescriure) filew = new FileWriter(fitxer);
            else filew = new FileWriter(fitxer, true);
            escriptor = new BufferedWriter(filew);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return escriptor;
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

    public Scanner ObrirFitxerLectura (String nom) throws Exception {//Nom ha de ser idHidato.txt
        Scanner x = null;
        try {
            x = new Scanner(new File(nom));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return x;
    }

    public boolean TancarFitxerLectura(Scanner x) {
        x.close();
        return true;
    }
}
