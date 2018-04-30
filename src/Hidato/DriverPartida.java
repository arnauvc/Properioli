package Hidato;

import java.util.Scanner;

public class DriverPartida {
    private Partida p = new Partida();
    private HidatosSolucionats hs = new HidatosSolucionats();
    private PartidesGuardades pg = new PartidesGuardades();
    private Tauler t = new Tauler();

    public void Test(){
        Scanner input = new Scanner(System.in);
        String lector = "";
        boolean seguir = true;
        boolean bug;
        System.out.println("Benvingut al Test de la classe Partida");
        while(seguir){
            System.out.println("Si vols resoldre un hidato nou prem 1");
            System.out.println("Si vols resoldre un hidato de la biblioteca prem 2");
            System.out.println("Si vols generar un hidato prem 3");
            System.out.println("Si vols reprendre una partida prem 4");
            System.out.println("Si vols sortir prem 0");
            lector = input.nextLine();
            switch (Integer.parseInt(lector)){
                case 0:
                    seguir = false;
                    break;
                case 1:
                    String nom, path, cela, adj, dif;
                    System.out.println("Insereix el teu nom:");
                    nom = input.nextLine();
                    System.out.println("Tria la direccio desti per a guardar o carregar arxuis:");
                    System.out.println("IMPORTANT! Ha de ser una direccio valida!");
                    path = input.nextLine();
                    System.out.println("Selecciona el tipus de cela [T | Q | H]:");
                    cela = input.nextLine();
                    System.out.println("Selecciona el tipus de adjacencia [C | CA]:");
                    adj = input.nextLine();
                    System.out.println("Selecciona el nivell de dificultat [F | N | D]:");
                    dif = input.nextLine();
                    p.SetNom(nom);
                    p.SetPath(path);
                    p.SetCela(cela);
                    p.SetAdjacencia(adj);
                    p.SetDificultat(dif);
                    try {
                        p.IniciaPartida();
                    } catch (Exception e) {
                        GuardarPartida(path);
                    }
                    break;
                case 2:
                    String resposta;
                    Integer numhidato;
                    bug = false;
                    System.out.println("Tens algun hidato a la biblioteca? [SI | NO]");
                    resposta = input.nextLine();
                    if (resposta.equals("SI")) {
                        System.out.println("Insereix el teu nom");
                        nom = input.nextLine();
                        System.out.println("Tria la direccio desti per a guardar o carregar arxius:");
                        System.out.println("IMPORTANT! Ha de ser una direccio valida!");
                        path = input.nextLine();
                        System.out.println("Selecciona el numero del hidato:");
                        numhidato = Integer.parseInt(input.nextLine());
                        p.SetNom(nom);
                        p.SetPath(path);
                        try {
                            t = hs.CarregarHidato(numhidato);
                            p.SetTauler(t);
                        } catch (Exception e) {
                            System.out.println("No tens cap hidato amb aquest numero de hidato");
                            bug = true;
                        }
                        if (!bug) {
                            try {
                                p.PartidaBiblioteca();
                            } catch (Exception e) {
                                GuardarPartida(path);
                            }
                        }
                    }
                    else System.out.println("Primer has de tenir un hidato a la biblioteca");
                    break;
                case 3:
                    Integer fil, col;
                    System.out.println("Insereix el teu nom:");
                    nom = input.nextLine();
                    System.out.println("Selecciona el tipus de cela [T | Q | H]:");
                    cela = input.nextLine();
                    System.out.println("Selecciona el tipus de adjacencia [C | CA]:");
                    adj = input.nextLine();
                    System.out.println("Insereix el numero de files:");
                    fil = Integer.parseInt(input.nextLine());
                    System.out.println("Insereix el numero de columnes:");
                    col = Integer.parseInt(input.nextLine());
                    System.out.println("Genera el hidato amb les caselles separades per comes");
                    String[][] Tauler = new String[fil][col];
                    for (int i = 0; i < fil; i++) {
                        String hidato[] = input.nextLine().split(",");
                        for (int j = 0; j < col; j++) {
                            Tauler[i][j] = hidato[j];
                        }
                    }
                    p.SetNom(nom);
                    p.SetCela(cela);
                    p.SetAdjacencia(adj);
                    p.SetFiles(fil);
                    p.SetColumnes(col);
                    p.SetTaulerU(Tauler);
                    p.Generar();
                    break;
                case 4:
                    bug = false;
                    System.out.println("Insereix el teu nom:");
                    nom = input.nextLine();
                    System.out.println("Tria la direccio desti per a guardar o carregar arxius:");
                    System.out.println("IMPORTANT! Ha de ser una direccio valida!");
                    path = input.nextLine();
                    pg.SetPath(path);
                    try {
                        p = pg.Obtenirpartida(nom);
                    } catch (Exception e) {
                        System.out.println("No hi ha cap partida guardada");
                        bug = true;
                    }
                    if (!bug) {
                        try {
                            p.ReprendrePartida();
                        } catch (Exception e) {
                            GuardarPartida(path);
                        }
                    }
                    break;
            }
        }
    }

    private void GuardarPartida(String path){
        pg.SetPath(path);
        try {
            System.out.println("Has guardat la partida");
            pg.GuardarPartida(p.GetNom(), p, p.GetTaulerG());
        } catch (Exception e) {
            System.out.print("Error al guardar la partida");
        }

    }
}
