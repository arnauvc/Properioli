package Hidato;

import java.util.*;

public class Driver_Ajuda {
    public void TestClass(){
        Scanner input = new Scanner(System.in);
        String[][] hidato=null;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                hidato[i][j] = input.next();
            }
        }

        Tauler t = new Tauler();
        t.CrearTauler("Q", "C", hidato);
        Ajuda a = new Ajuda();
        String[][] ajuda;
        ajuda = a.GetAjuda();

        for (int i = 0; i < 3; i++){
            System.out.print(",");
            for (int j = 0; j < 3; j++){
                if (j != 0) System.out.print(",");
                System.out.print(ajuda[i][j]);

            }
            System.out.println();
        }
    }
}
