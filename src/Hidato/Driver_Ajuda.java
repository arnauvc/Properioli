package Hidato;

import java.util.*;

public class Driver_Ajuda {
    public void TestClass(){
        Scanner input = new Scanner(System.in);

        String[][] s = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s[i][j] = input.next();

            }
        }

        Ajuda a = new Ajuda();
        String[][] aj;
        Tauler t = new Tauler();
        t.CrearTauler("Q", "C", s);
        aj = a.GetAjuda(t);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(aj[i][j]);
            }
            System.out.println();
        }


    }
}
