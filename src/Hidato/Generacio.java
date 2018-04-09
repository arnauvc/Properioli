package Hidato;

import java.util.Scanner;
import java.util.Random;


public class Generacio {

    public char[][] GenerarHidatoUsuari(Integer nfil, Integer ncol){
        char[][] hidato = new char[nfil][ncol];
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < nfil; ++i){
            for (int j = 0; j < ncol; ++j){
                hidato[i][j] = input.next().charAt(0);
            }
        }
        for (int i = 0; i < nfil; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncol; ++j){
                if (j > 0) System.out.printf(",%c", hidato[i][j]);
                else System.out.print(hidato[i][j]);
            }
        }

        return hidato;
    }

    public char[][] GenerarHidatoAlgorisme(String tipuscela, String tipusadj, String dif){
        int nfil = 0, ncol = 0;
        if (dif.equals("FACIL")){
            nfil = NumeroAleatori(3, 6);
            ncol = NumeroAleatori(3, 6);

        }
        else if (dif.equals("NORMAL")){
            nfil = NumeroAleatori(6, 8);
            ncol = NumeroAleatori(6, 8);

        }
        else if (dif.equals("DIFICIL")){
            nfil = NumeroAleatori(8, 10);
            ncol = NumeroAleatori(8, 10);

        }
        /*
        Els dos bucles seguents son per comprovar, el segon es pot esborrar, el primer no sino
        peta perque la matriu estari buida i faig un return de la matriu
        */
        System.out.printf("nfil: %d, ncol: %d\n", nfil, ncol);
        char[][] hidato = new char[nfil][ncol];
        for (int i = 0; i < nfil; ++i){
            for (int j = 0; j < ncol; ++j){
                hidato[i][j] = '?';
            }
        }
        for (int i = 0; i < nfil; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncol; ++j){
                if (j > 0) System.out.printf(",%c", hidato[i][j]);
                else System.out.print(hidato[i][j]);
            }
        }
        System.out.println();

        return hidato;
    }

    private Integer NumeroAleatori(Integer min, Integer max){
        Random r = new Random();
        return r.nextInt(max-min) + min;
    }


}
