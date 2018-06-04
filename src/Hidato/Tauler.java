package Hidato;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tauler {
    private Integer id;
    private List<List<Cela>> Celas = new ArrayList<List<Cela>>();
    private String tipuscela;
    private Integer numFiles;
    private Integer numColum;
    private Integer numcelestotal;
    private Integer numcelesocupades;
    private Integer numcelesbuides;
    private String adjacencia;
    private Cela cela;
    private Error e = new Error();
    private Integer vals;
    boolean b;


    private void mostrarfila(List<Cela> a){
        for(int i = 0; i < a.size(); ++i){
            System.out.print(a.get(i).getValor());
            if(i < a.size()-1) System.out.print(",");
        }

    }

    private boolean arriba(int val, int i, int j){
        if(consultarValidaCela(i-1,j)) {
            if(consultarValCela(i-1, j).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i-1, j));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean abajo(int val, int i, int j){
        if(consultarValidaCela(i+1,j)) {
            if(consultarValCela(i+1, j).equals("?")) return false;
            vals = Integer.parseInt(consultarValCela(i+1, j));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean izquierda(int val, int i, int j){
        if(consultarValidaCela(i,j-1)) {
            if (consultarValCela(i, j-1).equals("?")) return false;
            vals = Integer.parseInt(consultarValCela(i, j-1));
            if (vals == val-1 || vals == val-1) return true;
        }
        return false;
    }

    private boolean derecha(int val, int i, int j){
        if(consultarValidaCela(i,j+1)) {
            if(consultarValCela(i, j+1).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i, j+1));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean arriba_derecha(int val, int i, int j ){
        if(consultarValidaCela(i-1,j+1)){
            if(consultarValCela(i-1, j+1).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i-1, j+1));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean arriba_izquierda(int val,int i, int j){
        if(consultarValidaCela(i-1,j-1)){
            if(consultarValCela(i-1, j-1).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i-1, j-1));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean abajo_derecha(int val,int i, int j){
        if(consultarValidaCela(i+1,j+1)){
            if(consultarValCela(i+1, j+1).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i+1, j+1));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }

    private boolean abajo_izquierda(int val, int i, int j){
        if(consultarValidaCela(i+1,j-1)){
            if(consultarValCela(i+1, j-1).equals("?")) return false;
            vals =  Integer.parseInt(consultarValCela(i+1, j-1));
            if(vals == val-1 || vals == val+1) return true;
        }
        return false;
    }


    //constructora

    public Tauler(){}
    public Tauler(Integer numtauler) {//Obliguem a que sempre que es crei una instancia de Tauler, se li proporcioni un id
        this.id = numtauler;
    }


    public void CrearTauler(String ticela, String adj, String[][] matriu){
        this.tipuscela = ticela;
        this.adjacencia = adj;
        this.numFiles = matriu.length;
        this.numColum = matriu[0].length;
        boolean visible, valida;
        int numceltotal, numcelbuides, numcelesocu;
        numcelbuides = numceltotal = numcelesocu = 0;
        String valor;
        List<Cela> aux = new ArrayList<Cela>();
        //System.out.println("Estamos en tauler");
        //System.out.println(matriu.length);
        //System.out.println(matriu[0].length);
        for (int i = 0; i < matriu.length; ++i) {
            aux.clear();
            this.Celas.add(new ArrayList<Cela>() );
            for (int j = 0; j < matriu[0].length; ++j) {
                cela = new Cela();
                valor = matriu[i][j];
                if (valor.equals("#")) {
                    visible = false;
                    valida = false;
                    cela = new Cela(ticela, valor, visible, valida);
                    //aux.add(cela);
                } else if (valor.equals("?")) {
                    numceltotal++;
                    numcelbuides++;
                    visible = true;
                    valida = true;
                    cela = new Cela(ticela, valor, visible, valida);
                    //aux.add(cela);
                } else if (valor.equals("*")) {
                    numceltotal++;
                    visible = true;
                    valida = false;
                    cela = new Cela(ticela, valor, visible, valida);
                    //aux.add(cela);
                } else {
                    //error si no lee un numero, sobreentiendo que aqui siempre me llegara un numero.
                    //e(50)
                    numceltotal++;
                    numcelesocu++;
                    visible = true;
                    valida = true;
                    cela = new Cela(ticela, valor, visible, valida);
                    //aux.add(cela);
                }
                this.Celas.get(i).add(cela);
            }

        }
        this.numcelestotal = numceltotal;
        this.numcelesbuides = numcelbuides;
        this.numcelesocupades = numcelesocu;
    }




    //Consultora

    public Integer GetId() {
        return id;
    }

    @Test
    public Integer getNumFiles(){return numFiles;}

    @Test
    public Integer getNumColum(){return numColum;}

    @Test
    public Integer GetNumCelasTotal() {
        return numcelestotal;
    }

    @Test
    public Integer GetNumCelesOcupadas() {
        return numcelesocupades;
    }

    @Test
    public Integer GetNumCelesBuides() {
        return numcelesbuides;
    }

    @Test
    public String GetTiposAdj() {
        return adjacencia;
    }

    @Test
    public String getTipuscela() {
        return tipuscela;
    }

    @Test
    public List<List<Cela>> getCelas() {
        return Celas;
    }

    public Cela getcela(int x, int y){
        return Celas.get(x).get(y);
    }

    //consulturas cela
    @Test
    public String consultarValCela(int x, int y){
        cela = Celas.get(x).get(y);
        return cela.getValor();
    }

    @Test
    public boolean consultarValidaCela(int x, int y){
        cela = Celas.get(x).get(y);
        if (cela.isValida()) return true;
        else return false;
    }




    //Modificadoras
    @Test
    public void ModificaCeldaV(String x, int posi, int posj) {
        boolean b = false;
        if(x.equals("?")) {
            --this.numcelesocupades;
            ++this.numcelesbuides;
        }
        else {
            ++this.numcelesocupades;
            --this.numcelesbuides;
            //b = miraerror(x,posi,posj);
            b = true;
        }
        cela = Celas.get(posi).get(posj);
        cela.ModificarValor(x);
        //else System.out.println("No és possible posar un núnmero que no té números adjacentes al seu costat");

    }

    @Test
    public void SetAdjacencia(String  ad){
        this.adjacencia = ad;
    }

    @Test
    public void SetTipuscela(String ce){
        this.tipuscela = ce;
    }

    @Test
    public void MostrarTauler() {
        List<Cela> aux = new ArrayList<Cela>();
        for (int i = 0; i < Celas.size(); ++i) {
            aux = Celas.get(i);
            mostrarfila(aux);
            System.out.println();
        }
    }

}