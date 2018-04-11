package Hidato;

import Hidato.Usuari;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver_Usuari {
    public void Prova () {
        Scanner input = new Scanner(System.in);
        String lector = "";
        Usuari u = null;
        boolean t = true;
        while (t) {
            System.out.println("Benvingut a la prova de la classe Usuari");
            System.out.println("Si vols crear un Usuari prem 1");
            System.out.println("Si vols obtenir el nom de l'usuari creat prem 2");
            System.out.println("Si vols modificar el nom de l'usuari creat prem 3");
            System.out.println("Si vols afegir l'id d'un hidato als hidatos jugats per l'usuari creat prem 4");
            System.out.println("Si vols saber si l'usuari creat ha jugat un hidato en concret prem 5");
            System.out.println("Si vols obtenir les id dels hidatos que ha jugat l'usuari creat prem 6");
            System.out.println("Si vols sortir prem 0");
            lector = input.nextLine();
            switch (Integer.parseInt(lector)) {
                case 0:
                    t = false;
                    break;
                case 1:
                    System.out.println("Introgueix el nom de l'usuari: ");
                    lector = input.nextLine();
                    u = new Usuari(lector);
                    break;
                case 2:
                    if (u != null) System.out.println(u.GetNom());
                    else System.out.println("Has de crear un usuari primer!");
                    break;
                case 3:
                    if (u != null) {
                        System.out.println("Introgueix el nou nom de l'usuari: ");
                        lector = input.nextLine();
                        u.SetNom(lector);
                    }
                    else System.out.println("Has de crear un usuari primer!");
                    break;
                case 4:
                    if (u != null) {
                        System.out.println("Introgueix l'id de l'hidato: ");
                        lector = input.nextLine();
                        u.AfegirTaulers(Integer.parseInt(lector));
                    }
                    else System.out.println("Has de crear un usuari primer!");
                    break;
                case 5:
                    if (u != null) {
                        System.out.println("Introgueix l'id de l'hidato: ");
                        lector = input.nextLine();
                        if (u.HidatoFet(Integer.parseInt(lector))) System.out.println("Sí");
                        else System.out.println("No");
                    }
                    else System.out.println("Has de crear un usuari primer!");
                    break;
                case 6:
                    if (u != null) {
                        ArrayList<Integer> al = new ArrayList<Integer>();
                        al = u.HidatosJugats();
                        if (al.isEmpty()) System.out.println("No ha jugat a res");
                        else {
                            for (int i = 0; i < al.size(); i++) {
                                System.out.println(al.get(i));
                            }
                        }
                    }
                    else System.out.println("Has de crear un usuari primer!");
                    break;
            }
        }
    }
}
