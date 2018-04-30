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
        System.out.println("Para crear un tablero pulse: 1");
        System.out.println("Para mirar los get en tauler pulse: 2");
        System.out.println("Para mirar los set tauler pulse: 3");
        System.out.println("Para mirar los get en cela pulse: 4");
        System.out.println("Para consultar pulse: 5");
        System.out.println("Para modificaciones pulse: 6");
        System.out.println("Para selecionar un tablero: 7");
        System.out.println("Para mostar un tablero pulse :8");
        System.out.println("Para salir: 0");
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
                System.out.println("Escriba el tipo, ajd, numfiles, numcolumnas, y matriz");
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
                System.out.println("Para mirar el numero de filas pulse 1:");
                System.out.println("Para mirar el numero de columnas pulse 2:");
                System.out.println("Para miar el numero de celas totales pulse 3:");
                System.out.println("Para mirar el numero de celas ocupadas pulse 4:");
                System.out.println("Para mirar el numero de celas vacias pulse: 5");
                System.out.println("Para miarr el tipo de adjacencia: 6");
                System.out.println("Para mirar el tipo de cela: 7");
                System.out.println("Para copiar toda la matriz pulse: 8");
                System.out.println("Salir: 0");
                funcion2 = s.nextInt();
                while(funcion2 != 0){
                    if(funcion2 == 1) System.out.println("Numero de files: "+ t.getNumFiles());
                    else if(funcion2 == 2) System.out.println("Numero de columnas: " + t.getNumColum());
                    else if(funcion2 == 3) System.out.println("Numero de celas totales: " + t.GetNumCelasTotal());
                    else if(funcion2 == 4) System.out.println("Numero de  celas ocupadas: " + t.GetNumCelesOcupadas());
                    else if(funcion2 == 5) System.out.println("Numero de celas vacias: " + t.GetNumCelesBuides());
                    else if(funcion2 == 6) System.out.println("Tipo de adjacencia: " + t.GetTiposAdj());
                    else if(funcion2 == 7) System.out.println("Tipo cela: " + t.getTipuscela());
                    else if(funcion2 == 8) {
                        System.out.println("toda la matriz, no hay q mostar");
                        aux = t.getCelas();
                    }
                    funcion2 = s.nextInt();
                }
            }
            else if(funcio == 3){
                System.out.println("Para cambiar una cela pulse 1");
                System.out.println("Para cambiar el tipo de adjacencia pulse 2");
                System.out.println("para cambiar el tipo de cela pulse 3");
                System.out.println("Salir: 0");
                funcion2 = s.nextInt();
                while(funcion2 != 0){
                    if(funcion2 == 1){
                        String valor;
                        int posi, posj;
                        System.out.println("Escriba el valor, la poscion i, la poiscion j de la cela");
                        valor = s.next();
                        posi = s.nextInt();
                        posj = s.nextInt();
                        t.ModificaCeldaV(valor,posi,posj);
                        t.MostrarTauler();
                    }
                    else if(funcion2 == 2){
                        String tipoadj;
                        tipoadj = t.GetTiposAdj();
                        if(tipoadj.equals("CA")) System.out.println("Ahora mismo  la adyacencia es " + tipoadj + "(costats + angles) dime el nuevo tipo");
                        else System.out.println("Ahora mismo  la adyacencia es " + tipoadj + "(costats) dime el nuevo tipo");
                        tipoadj = s.next();
                        t.SetAdjacencia(tipoadj);
                    }
                    else if(funcion2 == 3){
                        String tipoc;
                        tipoc = t.getTipuscela();
                        if(tipoc.equals("T"))  System.out.println("Ahora mismo  la adyacencia es " + tipoc + "(Triangular) dime el nuevo tipo");
                        else if(tipoc.equals("Q")) System.out.println("Ahora mismo  la adyacencia es " + tipoc + "(Cuadrada) dime el nuevo tipo");
                        else System.out.println("Ahora mismo  la adyacencia es " + tipoc + "(Hexagonal) dime el nuevo tipo");
                        tipoc = s.next();
                        t.SetTipuscela(tipoc);
                    }
                    funcion2 = s.nextInt();

                }
            }
            else if(funcio == 4){
                System.out.println("Para consultar el valor de la cela 1");
                System.out.println("Para consultar si una cela es valida pulse uno pulse 2");
                System.out.println("Para consultar si una cela es visible pulse uno pulse 3");
                System.out.println("Salir 0");
                funcion2 = s.nextInt();
                System.out.println("Escrbie la posicion de la cela");
                int x, y;
                x = s.nextInt();
                y = s.nextInt();
                cela = new Cela();
                cela = t.getcela(x,y);
                while(funcion2 != 0){
                    if(funcion2 == 1){
                        if(cela.isValida()) System.out.println(cela.getValor());
                        else System.out.println("Esta cela no tiene valor porque no es valida");
                    }
                    else if(funcion2 == 2){
                        if(cela.isValida()) System.out.println("Es valida");
                        else System.out.println("No es valida");
                    }
                    else if(funcion2 == 3){
                        if(cela.isVisible()) System.out.println("Es visible");
                        else System.out.println("No es visible");
                    }
                }
            }

            else if(funcio == 8) t.MostrarTauler();
            System.out.println("Que desea seguir haciendo ahora?");
            mostraropcionesini();
            funcio = s.nextInt();

        }
    }
}
