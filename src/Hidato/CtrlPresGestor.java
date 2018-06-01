package Hidato;

import javafx.util.Pair;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class CtrlPresGestor {

    private Gestor g = new Gestor();
    private String[][] taulerG;
    private String[][] taulerresol;
    private String tcelaG;
    private String tadjG;
    private String pathG;
    private String nomG;

    private String TPartida(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecciona si vols RESOLDRE, GENERAR o REPRENDRE un Hidato: ");
        String tipuspartida = input.nextLine();

        while (!tipuspartida.equals("RESOLDRE") && !tipuspartida.equals("GENERAR") && !tipuspartida.equals("REPRENDRE")) {
            System.out.println("Selecciona si vols RESOLDRE, GENERAR o REPRENDRE un Hidato: ");
            tipuspartida = input.nextLine();
        }
        return tipuspartida;
    }
    private String TCela(){
        Scanner input = new Scanner(System.in);
        System.out.println("Selecciona el tipus de cela: ");
        System.out.println("T(Triangle), Q(Quadrat), H(Hexagon)");
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
        System.out.println("C(Costat), CA(Costat+Angle)");
        tipusadj = input.nextLine();
        while (!tipusadj.equals("C") && !tipusadj.equals("CA")) {
            System.out.println("Selecciona el tipus de adjacencia: ");
            System.out.println("C(Costat), CA(Costat+Angle");
            tipusadj = input.nextLine();
        }
        return tipusadj;
    }
    private String TDificultat(){
        Scanner input = new Scanner(System.in);
        String tipusdificultat;
        System.out.println("Selecciona el nivell de dificultat: ");
        System.out.println("F(FACIL), N(NORMAL) O D(DIFICIL)");
        tipusdificultat = input.nextLine();
        while (!tipusdificultat.equals("F") && !tipusdificultat.equals("N") && !tipusdificultat.equals("D")) {
            System.out.println("Selecciona el nivell de dificultat: ");
            System.out.println("F(FACIL), N(NORMAL) O D(DIFICIL)");
            tipusdificultat = input.nextLine();
        }
        return tipusdificultat;
    }
    private Integer NHidato(Integer totalhidatos){
        Scanner input = new Scanner(System.in);
        Integer numhidato;
        System.out.println("Selecciona el numero del hidato: ");
        numhidato = Integer.parseInt(input.nextLine());
        /*while (numhidato < 0 || numhidato >= totalhidatos) {
            System.out.println("Selecciona el numero del hidato: ");
            numhidato = Integer.parseInt(input.nextLine());
        }*/
        return numhidato;
    }
    private Vector<String> NFilCol(){

        //S'ha de gestionar que si entres un Enter per teclat, peta

        Vector<String> vres = new Vector<String>();
        Scanner input = new Scanner(System.in);
        System.out.println("Introdueix numero files: ");
        Integer nfiles = Integer.parseInt(input.nextLine());
        System.out.println("Introdueix numero columnes: ");
        Integer ncolumnes = Integer.parseInt(input.nextLine());
        while (nfiles < 2|| ncolumnes < 2 ) {
            System.out.println("Introdueix numero files: ");
            nfiles = Integer.parseInt(input.nextLine());
            System.out.println("Introdueix numero columnes: ");
            ncolumnes = Integer.parseInt(input.nextLine());
        }
        vres.add(0, Integer.toString(nfiles));
        vres.add(1, Integer.toString(ncolumnes));
        return vres;
    }
    private Vector<String> Params(){
        //No funciona encara

        System.out.println("Introdueix configuracio: ");
        Vector<String> res = new Vector<String>();
        Scanner input = new Scanner(System.in);
        String valors = input.nextLine();
        StringCharacterIterator sct = new StringCharacterIterator(valors);
        for(Integer i = 0; i< 8; ++i) {
            if (valors.charAt(i) != ',') {
                res.add(String.valueOf(valors.charAt(i)));
            }
        }
        return res;
    }
    private String [][] LlegirTauler(Integer nfil, Integer ncol){
        //Ja funciona
        System.out.println("Genera el hidato:");
        String[][] Tauler = new String[nfil][ncol];
        Scanner input = new Scanner(System.in);


        for(int i = 0; i < nfil; i++) {
            String hidato[] = input.nextLine().split(",");

            for (int j = 0; j < ncol; j++) {
                Tauler[i][j] = hidato[j];
            }


        }
        return Tauler;


    }

    //identificar que esta jugando o creando.
    private boolean crear = false;
    //crear
    private int fila;
    private int columna;

    public void Interrupcio(String s){

    }

    public void Inicia() throws Exception {
        Vector<String> v = new Vector<String>();
        Scanner input = new Scanner(System.in);
        String tipuspartida;
        String tipuscela;
        String tipusadj;
        String nomusuari;
        String tipusdificultat;
        String path;
        boolean segur = false;

        System.out.println("Benvingut a Hidato!");
        System.out.println("Insereix el teu nom: ");
        nomusuari = input.nextLine();
        System.out.println("Hola " + nomusuari);
        v.add(0,nomusuari);

        while(!segur) {
            System.out.println("Tria la direccio desti per a guardar o carregar arxius:\nIMPORTANT! Ha de ser una direccio valida! ");
            path = input.nextLine();
            System.out.println("Estas segur que vols aquesta direccio desti? SI o NO");
            String siono = input.nextLine();
            while(!siono.equals("SI") && !siono.equals("NO")){
                System.out.println("Estas segur que vols aquesta direccio desti? SI o NO");
                siono = input.nextLine();
            }
            if (siono.equals("SI")) {
                v.add(1, path);
                segur = true;
            }

        }

        tipuspartida = TPartida();

        if (tipuspartida.equals("RESOLDRE")) {
            System.out.println("Vols carregar un hidato de la BIBLIOTECA o un ALEATORI? ");
            String tipushidato = input.nextLine();

            while(!tipushidato.equals("BIBLIOTECA") && !tipushidato.equals("ALEATORI")) {
                System.out.println("Vols carregar un hidato de la BIBLIOTECA o un ALEATORI? ");
                tipushidato = input.nextLine();
            }
            if(tipushidato.equals("BIBLIOTECA")){
                Integer totalhidatos = g.VisualitzaHidatos();//Li demana per poder fer display dels hidatos de la biblioteca
                //L'usuari ara veuria tots els hidatos i en triaria un
                Integer idhidato = NHidato(totalhidatos); //Tria l'hidato que vol
                try {
                    g.JugarHidato(v, idhidato);//Aqui ja l'envia a jugar
                }catch (Exception e){
                    System.out.println("No hi ha cap hidato amb aquest numero");
                }
            }
            else if (tipushidato.equals("ALEATORI")){
                tipuscela = TCela();
                if(tipuscela.equals("Q")){
                    tipusadj = TAdj();
                }
                else tipusadj = "C";
                tipusdificultat = TDificultat();
                v.add(2,tipuscela);
                v.add(3,tipusadj);
                v.add(4,tipusdificultat);
                //tcela, tadj, tdificultat
                g.Aleatori(v);
            }
        }
        else if (tipuspartida.equals("GENERAR")){

            //Tipus 1. Interactiu amb l'usuari

            tipuscela = TCela();
            if(tipuscela.equals("Q")){
                tipusadj = TAdj();
            }
            else tipusadj = "C";
            v.add(2,tipuscela);
            v.add(3,tipusadj);
            Vector<String> vres = NFilCol();
            v.add(4,vres.get(0));
            v.add(5,vres.get(1));
            String[][] tau = LlegirTauler(Integer.parseInt(vres.get(0)), Integer.parseInt(vres.get(1)));
            //g.Parametres(v);
            g.Generar(v,tau);//V{nomusuari, path, tipuscela, tipusadj, numfil, numcol, dificultat}

            /*
            //Tipus 2. No interactiu. Com el pdf de mostra

            Vector<String> parametres = Params();
            String[][] Tauler = LlegirTauler(Integer.parseInt(parametres.get(2)), Integer.parseInt(parametres.get(3)));
            //g.Parametres(parametres);
            g.Generar(parametres, Tauler);
            */
        }
        else if(tipuspartida.equals("REPRENDRE")){
            try {
                //nomusuari, path
                g.Reprendre(v);
            }
            catch (Exception e){
                System.out.println("No tens cap partida guardada");
            }
        }
    }




    //AFEGIT PER CONTROLAR PART3
    public void CtrlGenerarHidato(String tcela, String tadj, String dif) {
        tcelaG = tcela;
        tadjG = tadj;
        taulerG = g.GestorGenerarHidato(tcela, tadj, dif);
    }

    public String[][] CtrlResoldreHidato(String[][] tauler, String tcela, String tadj) {
        String[][] aux;
        aux = g.GestorResoldreHidato(tauler, tcela, tadj);
        return aux.clone();
    }

    public void CtrlGuardarHidato(String tcela, String tadj, String[][] tauler, ArrayList<Pair<Pair<Integer, Integer>, String>> solucio) {
        g.GestorGuardarHidato(tcela, tadj, tauler, solucio);
    }

    public void SetTipusTauler(String tcela, String tadj) {
        tcelaG = tcela;
        tadjG = tadj;

    }

    public void setcela(String cela){
        tcelaG = cela;
    }

    public void setTadjG(String adj){
        tadjG = adj;
    }

    public String[][] GetTauler() {
        return taulerG.clone();
    }

    public String GetTcela() {
        return tcelaG;
    }

    public String GetTadj() {
        return tadjG;
    }

    public void SetCrear(boolean crear){
        this.crear = crear;
    }

    public void setFilaColumna(int f, int c){
        this.fila = f;
        this.columna = c;
    }




    //AFEGIT PER CONTROLAR PART 1
    public void SetPath(String path){
        pathG = path;
    }
    public void SetNom(String nom){
        nomG = nom;
    }

    //para el numero 4
    public boolean isCrear(){
        return this.crear;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public void SetTauler(String[][] tau){
        this.taulerG = tau.clone();
        taulerresol = g.GestorResoldreHidato(taulerG, tcelaG, tadjG);
    }

}
