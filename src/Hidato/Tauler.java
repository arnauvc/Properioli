package Hidato;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Tauler {
    /*private enum TipusCela{
        TRIANGLE, QUADRAT, HEXAGON
    }*/
    private Integer id;//Numero que identifica l'hidato inequivocament
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


    //constructora
    public Tauler(){}
    public Tauler(Integer numtauler) {//Obliguem a que sempre que es crei una instancia de Tauler, se li proporcioni un id
        this.id = numtauler;
    }

    public void CrearTauler(String ticela, String adj, String[][] matriu)//
    {
        this.tipuscela = ticela;
        this.adjacencia = adj;
        this.numFiles = matriu.length;
        this.numColum = matriu[0].length;
        boolean visible, valida;
        int numceltotal, numcelbuides, numcelesocu;
        numcelbuides = numceltotal = numcelesocu = 0;
        String valor;
        List<Cela> aux = new ArrayList<Cela>();
        for (int i = 0; matriu.length > i; ++i) {
            aux.clear();
            for (int j = 0; j < matriu[i].length; ++j) {
                valor = matriu[i][j];
                if (valor.equals("#")) {
                    visible = false;
                    valida = false;
                    cela = new Cela(ticela, valor, visible, valida);
                    aux.add(cela);
                } else if (valor.equals("?")) {
                    numceltotal++;
                    numcelbuides++;
                    visible = true;
                    valida = true;
                    cela = new Cela(ticela, valor, visible, valida);
                    aux.add(cela);
                } else if (valor.equals("*")) {
                    numceltotal++;
                    visible = true;
                    valida = false;
                    cela = new Cela(ticela, valor, visible, valida);
                    aux.add(cela);
                } else {
                    //error si no lee un numero, sobreentiendo que aqui siempre me llegara un numero.
                    //e(50)
                    numceltotal++;
                    numcelesocu++;
                    visible = true;
                    valida = true;
                    cela = new Cela(ticela, valor, visible, valida);
                    aux.add(cela);
                }
            }
            //mostrarfila(aux);
            this.Celas.add(aux);
            //System.out.println(Celas.size() + " " + aux.size());
        }
        this.numcelestotal = numceltotal;
        this.numcelesbuides = numcelbuides;
        this.numcelesocupades = numcelesocu;
    }

    private void mostrarfila(List<Cela> a){
        for(int i = 0; i < a.size(); ++i){
            System.out.print(a.get(i).getValor() +  ",");
        }
    }


    //Consultora
    public Integer GetId() {
        return id;
    }

    public Integer GetNumCelasTotal() {
        return numcelestotal;
    }

    public Integer GetNumCelesOcupadas() {
        return numcelesocupades;
    }

    public Integer GetNumCelesBuides() {
        return numcelesbuides;
    }

    public String GetTiposAdj() {
        return adjacencia;
    }

    public String getTipuscela() {
        return tipuscela;
    }

    public List<List<Cela>> getCelas() {
        return Celas;
    }

    public Integer getNumFiles(){return numFiles;}

    public Integer getNumColum(){return numColum;}

    public String consultarValCela(int x, int y){
        cela = Celas.get(x).get(y);
        return cela.getValor();
    }

    public boolean consultarValidaCela(int x, int y){
        cela = Celas.get(x).get(y);
        return cela.isValida();
    }



    //Modificadoras
    public void ModificaCeldaV(String x, int posi, int posj) {
        cela = Celas.get(posi).get(posj);
        cela.ModificarValor(x);

    }

    public void SetAdjacencia(String  ad){
        this.adjacencia = ad;
    }

    public void SetTipuscela(String ce){
        this.tipuscela = ce;
    }

    public void MostrarTauler() {//no funciona correctament
        List<Cela> aux = new ArrayList<Cela>();
        System.out.println(numFiles);
        System.out.println(Celas.size());
        for (int i = 0; i <= numFiles; ++i) {
            aux.clear();
            aux = Celas.get(i);
            System.out.println(aux.size());
            mostrarfila(aux);
        }
    }


}