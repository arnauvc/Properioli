package Hidato;

import Hidato.Tauler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import java.util.*;
import Hidato.Resolucio;
import Hidato.Generacio;

public class Driver_Resolucio {

    private Resolucio r = new Resolucio();
    private Generacio g = new Generacio();

    public void Prova() {
        Scanner input = new Scanner(System.in);
        String lector = "";
        boolean t = true;
        String[][] tauler = null;
        String tcela = new String();
        String tadj = new String();
        String dif = new String();
        String[][] s = null;
        System.out.println("Benvingut a la prova de la classe Resolucio");
        while(t) {
            System.out.println("Si vols mostrar el menu prem 1");
            lector = input.nextLine();
            switch (Integer.parseInt(lector)) {
                case 0:
                    t = false;
                    break;
                case 1:
                    System.out.println("Si vols generar un tauler prem 2");
                    System.out.println("Si vols veure el tauler generat prem 3");
                    System.out.println("Si vols resoldre el tauler prem 4");
                    System.out.println("Si vols veure el tauler resolt prem 5");
                    System.out.println("Si vols sortir prem 0");
                    break;
                        case 2:
                            System.out.println("Selecciona tipus de Cela [Q | T | H]");
                            tcela = input.nextLine();
                            System.out.println("Selecciona tipus de d'Adjacencia [C | CA]");
                            tadj = input.nextLine();
                            System.out.println("Selecciona la dificultat [FACIL | NORMAL | DIFICIL]");
                            dif = input.nextLine();
                            tauler = g.GenerarHidato(tcela, tadj, dif);
                            break;
                        case 3:
                            if (tauler == null) System.out.println("Primer has de generar un tauler!");
                            else {
                                for (int i = 0; i < tauler.length; i++) {
                                    for (int j = 0; j < tauler[i].length; j++) {
                                        System.out.print(tauler[i][j]);
                                        System.out.print(" ");
                                    }
                                    System.out.println("");
                                }
                            }
                            break;
                        case 4:
                            if (tauler == null) System.out.println("Primer has de generar un tauler!");
                            else {
                                s = r.ResoltreHidato(tauler, tadj);
                            }
                            break;
                        case 5:
                            if (s == null) System.out.println("Primer has de resoldre el tauler generat!");
                            else {
                                for (int i = 0; i < s.length; i++) {
                                    for (int j = 0; j < s[i].length; j++) {
                                        System.out.print(s[i][j]);
                                        System.out.print(" ");
                                    }
                                    System.out.println("");
                                }
                            }
                            break;
            }
        }
    }
}
