package Hidato;

import Hidato.Gestor;

import java.util.Scanner;
import java.io.ObjectInputStream;
import java.util.Vector;

public class CtrlPresGestor {

    private Gestor g = new Gestor();

    private String TPartida(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecciona si vols RESOLDRE, GENERAR o REPRENDRE un Hidato: ");
        String tipuspartida = input.nextLine();

        while (!tipuspartida.equals("RESOLDRE") && !tipuspartida.equals("GENERAR") && !tipuspartida.equals("REPRENDRE")) {
            System.out.println("Selecciona si vols RESOLDRE,GENERAR o REPRENDRE un Hidato: ");
            tipuspartida = input.nextLine();
        }
        return tipuspartida;
    }
    private String TCela(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecciona el tipus de cela: ");
        String tipuscela = input.nextLine();
        while (!tipuscela.equals("T") && !tipuscela.equals("Q") && !tipuscela.equals("H")) {
            System.out.println("Selecciona el tipus de cela: ");
            tipuscela = input.nextLine();
        }

        return tipuscela;
    }
    private String TAdj(){
        Scanner input = new Scanner(System.in);
        String tipusadj;
        System.out.println("Selecciona el tipus de adjacencia: ");
        tipusadj = input.nextLine();
        while (!tipusadj.equals("C") && !tipusadj.equals("CA")) {
            System.out.println("Selecciona el tipus de adjacencia: ");
            tipusadj = input.nextLine();
        }
        return tipusadj;
    }

    private Integer NHidato(Integer totalhidatos){
        Scanner input = new Scanner(System.in);
        Integer numhidato;
        System.out.println("Selecciona el numero del hidato: ");
        numhidato = Integer.parseInt(input.nextLine());
        while (numhidato < 0 || numhidato >= totalhidatos) {
            System.out.println("Selecciona el numero del hidato: ");
            numhidato = Integer.parseInt(input.nextLine());
        }
        return numhidato;
    }

    public void Inicia(){
        Vector<String> v = new Vector<String>();
        Scanner input = new Scanner(System.in);
        String tipuspartida;
        String tipuscela;
        String tipusadj;
        String nomusuari;

        System.out.println("Benvingut a Hidato!");
        System.out.println("Insereix el teu nom: ");
        nomusuari = input.nextLine();
        v.add(0,nomusuari);

        tipuspartida = TPartida();

        if (tipuspartida.equals("RESOLDRE")) {
            System.out.println("Vols carregar un hidato de la BIBLIOTECA o un ALEATORI? ");
            String tipushidato = input.nextLine();

            while(!tipushidato.equals("BIBLIOTECA") && !tipushidato.equals("ALEATORI")) {
                tipushidato = input.nextLine();
            }
            if(tipushidato.equals("BIBLIOTECA")){
                Integer totalhidatos = g.VisualitzaHidatos();//Li demana al gestor que faci display dels hidatos de la biblioteca
                Integer numhidato = NHidato(totalhidatos);
                g.EscullHidato(numhidato);//Aqui ja l'envia a jugar
            }
            else if (tipushidato.equals("ALEATORI")){
                g.Aleatori(v);
            }
        }
        else if (tipuspartida.equals("GENERAR")){
            tipuscela = TCela();
            tipusadj = TAdj();
            v.add(1,tipuscela);
            v.add(2,tipusadj);
            //Faltara fer el read del hidato com a tal. El tauler vaja. I passarem una copia o referencia de la matriu
            //en la possicio 5 del vector v
            g.Generar(v);//V{nomusuari,tipuscela, tipusadj, numfil, numcol, matriu}
        }
        else if(tipuspartida.equals("REPRENDRE")){
            g.Reprendre(v);

        }
    }
}
