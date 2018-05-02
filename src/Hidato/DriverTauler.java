package Hidato;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverTauler {
    private Tauler t;
    private List<List<Cela>> aux = new ArrayList<List<Cela>>();
    private List<Tauler> tab = new ArrayList<Tauler>();
    private String[][] m;
    private Cela cela;


    private void mostraropcionesini(){
        System.out.println("Per crear un tauler premi: 1");
        System.out.println("Per mirar els get del tauler premi: 2");
        System.out.println("Per mirar els set del tauler premi: 3");
        System.out.println("Per mirar els get de la cel·la premi: 4");
        //System.out.println("Per consultar premi: 5");
        //System.out.println("Per modificacions premi: 6");
        System.out.println("Per seleccionar un tauler: 7");
        System.out.println("Per mostar un tauler premi :8");
        System.out.println("Per sortir premi: 0");
    }


    public DriverTauler(){}

    public void inicia(){
        int funcio;
        Scanner s = new Scanner(System.in);
        mostraropcionesini();
        funcio = s.nextInt();
        while(funcio != 0){
            int funcion2;
            if(funcio == 1){
                t = new Tauler(tab.size()+1);
                int nfiles,ncolum;
                String tipo, adj;
                System.out.println("Escrigui el tipus de cel·la, l'adjacència, el número de files, el número de columnes, i la matriu");
                tipo = s.next();
                adj = s.next();
                nfiles = s.nextInt();
                ncolum = s.nextInt();
                m = new String[nfiles][ncolum];
                for(int i = 0; i < nfiles;++i){
                    for(int j = 0; j < ncolum;++j){
                        m[i][j] = s.next();
                    }
                }
                t.CrearTauler(tipo,adj,m);
                tab.add(t);
            }
            else if(funcio == 2){
                //get tauler
                System.out.println("Per mirar el número de files premi 1:");
                System.out.println("Per mirar el número de columnes premi 2:");
                System.out.println("Per mirar el número de cel·les totals premi 3:");
                System.out.println("Per mirar el número de cel·les ocupades premi 4:");
                System.out.println("Per mirar el número de cel·les buides premi: 5");
                System.out.println("Per mirar el tipus d'adjacència premi: 6");
                System.out.println("Per mirar el tipus de cel·la premi: 7");
                System.out.println("Per copiar tota la matriu premi: 8");
                System.out.println("Per sortir premi: 0");
                funcion2 = s.nextInt();
                while(funcion2 != 0){
                    if(funcion2 == 1) System.out.println("Número de files: "+ t.getNumFiles());
                    else if(funcion2 == 2) System.out.println("Número de columnes: " + t.getNumColum());
                    else if(funcion2 == 3) System.out.println("Número de cel·les totals: " + t.GetNumCelasTotal());
                    else if(funcion2 == 4) System.out.println("Número de  cel·les ocupades: " + t.GetNumCelesOcupadas());
                    else if(funcion2 == 5) System.out.println("Número de cel·les buides: " + t.GetNumCelesBuides());
                    else if(funcion2 == 6) System.out.println("Tipus d'adjacència: " + t.GetTiposAdj());
                    else if(funcion2 == 7) System.out.println("Tipus cel·la: " + t.getTipuscela());
                    else if(funcion2 == 8) {
                        System.out.println("Còpia tota la matriu no hi ha res que mostrar");
                        aux = t.getCelas();
                    }
                    funcion2 = s.nextInt();
                }
            }
            else if(funcio == 3){
                System.out.println("Per canviar una cel·la premi 1");
                System.out.println("Per canviar el tipus d'adjacència premi 2");
                System.out.println("Per canviar el tipus de cel·la premi 3");
                System.out.println("Per sortir premi: 0");
                funcion2 = s.nextInt();
                while(funcion2 != 0){
                    if(funcion2 == 1){
                        String valor;
                        int posi, posj;
                        System.out.println("Escrigui el valor, la posició i, la posició j de la cel·la");
                        valor = s.next();
                        posi = s.nextInt();
                        posj = s.nextInt();
                        //5System.out.println(valor + posi + posj);
                        t.ModificaCeldaV(valor,posi,posj);
                        t.MostrarTauler();
                    }
                    else if(funcion2 == 2){
                        String tipoadj;
                        tipoadj = t.GetTiposAdj();
                        if(tipoadj.equals("CA")) System.out.println("Ara mateix l'adjacència és " + tipoadj + "(costats + angles) diga'm el nou tipus");
                        else System.out.println("Ara mateix l'adjacència és " + tipoadj + "(costats) diga'm el nou tipus");
                        tipoadj = s.next();
                        t.SetAdjacencia(tipoadj);
                    }
                    else if(funcion2 == 3){
                        String tipoc;
                        tipoc = t.getTipuscela();
                        if(tipoc.equals("T"))  System.out.println("Ara mateix l'adjacència és " + tipoc + "(Triangular) diga'm el nou tipus");
                        else if(tipoc.equals("Q")) System.out.println("Ara mateix l'adjacència és " + tipoc + "(Quadrada) diga'm el nou tipus");
                        else System.out.println("Ara mateix l'adjacència és " + tipoc + "(Hexagonal) diga'm el nou tipus");
                        tipoc = s.next();
                        t.SetTipuscela(tipoc);
                    }
                    funcion2 = s.nextInt();

                }
            }
            else if(funcio == 4){
                System.out.println("Per consultar el valor de la cel·la premi 1");
                System.out.println("Per consultar si una cel·la és vàlida premi 2");
                System.out.println("Per consultar si una cell·a és visible premi 3");
                System.out.println("Per sortir premi 0");
                funcion2 = s.nextInt();
                System.out.println("Escrigui la posició de la cel·la");
                int x, y;
                x = s.nextInt();
                y = s.nextInt();
                cela = new Cela();
                cela = t.getcela(x,y);
                while(funcion2 != 0){
                    if(funcion2 == 1){
                        if(cela.isValida()) System.out.println(cela.getValor());
                        else System.out.println("Aquesta cel·la no té valor perquè no és vàlida");
                    }
                    else if(funcion2 == 2){
                        if(cela.isValida()) System.out.println("Aquesta cel·la és vàlida");
                        else System.out.println("Aquesta cel·la no és vàlida");
                    }
                    else if(funcion2 == 3){
                        if(cela.isVisible()) System.out.println("Aquesta cel·la és visible");
                        else System.out.println("Aquesta cel·la no és visible");
                    }
                    funcion2 = s.nextInt();
                }
            }

            else if(funcio == 7){
                System.out.println("EL número màxim de taulers és: " + tab.size());
                if(tab.size() > 0) {
                    int i = s.nextInt();
                    t = tab.get(i);
                }
            }

            else if(funcio == 8) t.MostrarTauler();
            System.out.println("Què desitja continuar fent ara?");
            mostraropcionesini();
            funcio = s.nextInt();

        }
    }
}