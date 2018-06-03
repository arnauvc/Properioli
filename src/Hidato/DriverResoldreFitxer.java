package Hidato;

import java.util.Scanner;

public class DriverResoldreFitxer {

    private Resolucio r = new Resolucio();
    private HidatosSolucionats hs = new HidatosSolucionats();


    public void ResolHidatoFitxer(){
        Scanner input = new Scanner(System.in);
        String lector = "";
        String solucio[][] = null;
        String hidato[][] = null;
        String path, nomfitxer, tcela, tadj;
        System.out.println("Benvingut al Driver de Resoldre un Hidato de un fitxer");

        System.out.println("Tria la direccio desti on tinguis el fitxer que vulguis provar:");
        System.out.println("IMPORTANT! Ha de ser una direccio valida!");
        path = input.nextLine();
        System.out.println("Insereix el nom del fitxer (sense el .txt):");
        nomfitxer = input.nextLine();
        hs.SetPath(path);
        try {
            hidato = hs.LlegirHidato(nomfitxer);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("No tens cap fitxer amb aquest nom");
        }

        tcela = hs.Getcela();
        tadj = hs.Getadj();

        System.out.println("Hidato llegit:");
        System.out.println();

        for (int l = 0; l < hidato.length; l++){
            for (int j = 0; j < hidato[0].length; j++){
                if (j > 0) System.out.print(",");
                System.out.print(hidato[l][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(tcela);
        System.out.println(tadj);
        solucio = r.ResoltreHidato(hidato, tcela, tadj);

        System.out.println("Solucio:");
        System.out.println();

        for (int i = 0; i < solucio.length; i++){
            for (int j = 0; j < solucio[0].length; j++){
                if (j > 0) System.out.print(",");
                System.out.print(solucio[i][j]);
            }
            System.out.println();
        }
    }
}
