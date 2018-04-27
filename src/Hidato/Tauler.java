package Hidato;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

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

    private void mostrarfila(List<Cela> a){
        for(int i = 0; i < a.size(); ++i){
            System.out.print(a.get(i).getValor());
            if(i < a.size()-1) System.out.print(",");
        }
    }

    private boolean miraerror(String x, int i, int j){
        int vali = Integer.parseInt(x);
        int vals;
        boolean b = true;
        if(this.tipuscela.equals("Q")){
            if(this.adjacencia.equals("C")){
                //controlo lo q ues la parte  de dentro del tablero
                if(i > 0 && j > 0 && i < numFiles-1 && j < numColum-1){
                    //abajo
                    if(consultarValidaCela(i+1,j)) {
                        b = true;
                        if(consultarValCela(i+1, j).equals("?")) b = false;
                        if(b){
                            vals = Integer.parseInt(consultarValCela(i+1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //arriba
                    if(consultarValidaCela(i-1,j)) {
                        b = true;
                        if(consultarValCela(i-1, j).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //derecha
                    if(consultarValidaCela(i,j+1)) {
                        b = true;
                        if(consultarValCela(i, j+1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j+1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //izquierda
                    if(consultarValidaCela(i,j-1)){
                        b = true;
                        if(consultarValCela(i, j-1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j-1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                }
                //contorlo la primera fila
                else if(i == 0){
                    System.out.println("Entro aqui2");
                    if(j == 0){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j > 0 &&  j < numColum-1){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j == numColum-1){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                }
                //controlo la ultima fila
                else if(i == numFiles-1){
                    System.out.println("Entro aqui3");
                    if(j == 0){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            b = true;
                            if(consultarValCela(i-1, j).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j > 0 && j < numColum-1){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            b = true;
                            if(consultarValCela(i-1, j).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j == numColum-1){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            b = true;
                            if(consultarValCela(i-1, j).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                }
                //controlo lo ques la primera columna
                else if(i > 0 && i < numFiles-1 && j == 0){
                    System.out.println("Entro aqui4");
                    //abajo
                    if(consultarValidaCela(i+1,j)) {
                        b = true;
                        if(consultarValCela(i+1, j).equals("?")) b = false;
                        if(b){
                            vals = Integer.parseInt(consultarValCela(i+1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //arriba
                    if(consultarValidaCela(i-1,j)) {
                        b = true;
                        if(consultarValCela(i-1, j).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //derecha
                    if(consultarValidaCela(i,j+1)) {
                        b = true;
                        if(consultarValCela(i, j+1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j+1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                }
                //controlo lo que es la ultima columna
                else if(i > 0 && i < numFiles-1 && j == numColum-1){
                    System.out.println("Entro aqui5");
                    //abajo
                    if(consultarValidaCela(i+1,j)) {
                        b = true;
                        if(consultarValCela(i+1, j).equals("?")) b = false;
                        if(b){
                            vals = Integer.parseInt(consultarValCela(i+1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //arriba
                    if(consultarValidaCela(i-1,j)) {
                        b = true;
                        if(consultarValCela(i-1, j).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //izquierda
                    if(consultarValidaCela(i,j-1)){
                        b = true;
                        if(consultarValCela(i, j-1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j-1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                }
            }
            else if(this.adjacencia.equals("CA")){
                //controlo lo q ues la parte  de dentro del tablero
                if(i > 0 && j > 0 && i < numFiles-1 && j < numColum-1){
                    //abajo
                    if(consultarValidaCela(i+1,j)) {
                        b = true;
                        if(consultarValCela(i+1, j).equals("?")) b = false;
                        if(b){
                            vals = Integer.parseInt(consultarValCela(i+1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //arriba
                    if(consultarValidaCela(i-1,j)) {
                        b = true;
                        if(consultarValCela(i-1, j).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //derecha
                    if(consultarValidaCela(i,j+1)) {
                        b = true;
                        if(consultarValCela(i, j+1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j+1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //izquierda
                    if(consultarValidaCela(i,j-1)){
                        b = true;
                        if(consultarValCela(i, j-1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i, j-1));
                            //System.out.println(vals);
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //diagonal abajo izquierda
                    if(consultarValidaCela(i+1,j-1)){
                        b = true;
                        if(consultarValCela(i+1, j-1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i+1, j-1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //diagonal abajo derecha
                    if(consultarValidaCela(i+1,j+1)){
                        b = true;
                        if(consultarValCela(i+1, j+1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i+1, j+1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //diagonale arriba izquierda
                    if(consultarValidaCela(i-1,j-1)){
                        b = true;
                        if(consultarValCela(i-1, j-1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j-1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    //diagonal arriba derecha
                    if(consultarValidaCela(i-1,j+1)){
                        b = true;
                        if(consultarValCela(i-1, j+1).equals("?")) b = false;
                        if(b){
                            vals =  Integer.parseInt(consultarValCela(i-1, j+1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                }
                //contorlo la primera fila
                else if(i == 0){
                    if(j == 0){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonal abajo derecha
                        if(consultarValidaCela(i+1,j+1)){
                            b = true;
                            if(consultarValCela(i+1, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i+1, j+1));
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j > 0 &&  j < numColum-1){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonal abajo izquierda
                        if(consultarValidaCela(i+1,j-1)){
                            b = true;
                            if(consultarValCela(i+1, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i+1, j-1));
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonal abajo derecha
                        if(consultarValidaCela(i+1,j+1)){
                            b = true;
                            if(consultarValCela(i+1, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i+1, j+1));
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j == numColum-1){
                        //abajo
                        if(consultarValidaCela(i+1,j)) {
                            b = true;
                            if(consultarValCela(i+1, j).equals("?")) b = false;
                            if(b){
                                vals = Integer.parseInt(consultarValCela(i+1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonal abajo izquierda
                        if(consultarValidaCela(i+1,j-1)){
                            b = true;
                            if(consultarValCela(i+1, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i+1, j-1));
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                }
                //controlo la ultima fila
                else if(i == numFiles-1){
                    if(j == 0){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            b = true;
                            if(consultarValCela(i-1, j).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonal arriba derecha
                        if(consultarValidaCela(i-1,j+1)){
                            b = true;
                            if(consultarValCela(i-1, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j+1));
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                    }
                    else if(j > 0 && j < numColum-1){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            b = true;
                            if(consultarValCela(i-1, j).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i-1, j));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //derecha
                        if(consultarValidaCela(i,j+1)) {
                            b = true;
                            if(consultarValCela(i, j+1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j+1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //izquierda
                        if(consultarValidaCela(i,j-1)){
                            b = true;
                            if(consultarValCela(i, j-1).equals("?")) b = false;
                            if(b){
                                vals =  Integer.parseInt(consultarValCela(i, j-1));
                                //System.out.println(vals);
                                if(vals == vali-1 || vals == vali+1) return true;
                            }
                        }
                        //diagonale arriba izquierda
                        else if(consultarValidaCela(i-1,j-1)){
                            vals =  Integer.parseInt(consultarValCela(i-1, j-1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                        //diagonal arriba derecha
                        else if(consultarValidaCela(i-1,j+1)){
                            vals =  Integer.parseInt(consultarValCela(i-1, j+1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                    else if(j == numColum-1){
                        //arriba
                        if(consultarValidaCela(i-1,j)) {
                            vals =  Integer.parseInt(consultarValCela(i-1, j));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                        //izquierda
                        else if(consultarValidaCela(i,j-1)){
                            vals =  Integer.parseInt(consultarValCela(i, j+1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                        //diagonale arriba izquierda
                        else if(consultarValidaCela(i-1,j-1)){
                            vals =  Integer.parseInt(consultarValCela(i-1, j-1));
                            if(vals == vali-1 || vals == vali+1) return true;
                        }
                    }
                }
                //controlo lo ques la primera columna
                else if(i > 0 && i < numFiles-1 && j == 0){
                    if(consultarValidaCela(i+1,j)) {
                        //abajo
                        vals = Integer.parseInt(consultarValCela(i+1, j));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //arriba
                    else if(consultarValidaCela(i-1,j)) {
                        vals =  Integer.parseInt(consultarValCela(i-1, j));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //derecha
                    else if(consultarValidaCela(i,j+1)) {
                        vals =  Integer.parseInt(consultarValCela(i, j+1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //diagonal abajo derecha
                    else if(consultarValidaCela(i+1,j+1)){
                        vals =  Integer.parseInt(consultarValCela(i+1, j+1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //diagonal arriba derecha
                    else if(consultarValidaCela(i-1,j+1)){
                        vals =  Integer.parseInt(consultarValCela(i-1, j+1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }

                }
                //controlo lo que es la ultima columna
                else if(i > 0 && i < numFiles-1 && j == numColum-1){
                    //arriba
                    if(consultarValidaCela(i+1,j)) {
                        vals = Integer.parseInt(consultarValCela(i+1, j));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //abajo
                    else if(consultarValidaCela(i-1,j)) {
                        vals =  Integer.parseInt(consultarValCela(i-1, j));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //izquierda
                    else if(consultarValidaCela(i,j-1)){
                        vals =  Integer.parseInt(consultarValCela(i, j+1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //diagonal abajo izquierda
                    else if(consultarValidaCela(i+1,j-1)){
                        vals =  Integer.parseInt(consultarValCela(i+1, j-1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                    //diagonale arriba izquierda
                    else if(consultarValidaCela(i-1,j-1)){
                        vals =  Integer.parseInt(consultarValCela(i-1, j-1));
                        if(vals == vali-1 || vals == vali+1) return true;
                    }
                }
            }
        }
        else if (this.tipuscela.equals("T")){






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

    public Integer getNumFiles(){return numFiles;}

    public Integer getNumColum(){return numColum;}

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

    //consulturas cela
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
        boolean b = false;
        if(x.equals("?")){
            --this.numcelesocupades;
            ++this.numcelesbuides;
            b = true;
        }
        else {
            ++this.numcelesocupades;
            --this.numcelesbuides;
            b = miraerror(x,posi,posj);
        }
        if(b){
            cela = Celas.get(posi).get(posj);
            cela.ModificarValor(x);
        }
        else System.out.println("No es posible poner un núnmero que no tiene núnmeros adjacentes a su lado");

    }

    public void SetAdjacencia(String  ad){
        this.adjacencia = ad;
    }

    public void SetTipuscela(String ce){
        this.tipuscela = ce;
    }

    public void MostrarTauler() {
        List<Cela> aux = new ArrayList<Cela>();
        //System.out.println(numFiles);
        //System.out.println(Celas.size());
        for (int i = 0; i < Celas.size(); ++i) {
            aux = Celas.get(i);
            mostrarfila(aux);
            System.out.println();
        }
    }

}
