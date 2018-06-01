package Hidato;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Quadrada extends Cela{

    private ArrayList<Cela> veins_m = new ArrayList<>();
    private Random ran = new Random();


    public Quadrada(Integer i, Integer j,String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;
    }

    @Override
    public void Veins(String direccio) {
        if(Adjacencia.equals("C")) {

            if(direccio.equals("H")){
                this.Probabilitat = new Double[]{0.166665, 0.33333, 0.166665, 0.33333};
            }
            else if(direccio.equals("V")){
                this.Probabilitat = new Double[]{0.33333, 0.166665, 0.33333, 0.166665};
            }
            else {this.Probabilitat = new Double[]{0.2475, 0.2475, 0.2475, 0.2475};}

            veins_m.add(new Quadrada(CoordI, CoordJ + 1, Adjacencia));//TOP
            veins_m.add(new Quadrada(CoordI + 1, CoordJ, Adjacencia));//RIGHT
            veins_m.add(new Quadrada(CoordI, CoordJ - 1, Adjacencia));//BOTTOM
            veins_m.add(new Quadrada(CoordI - 1, CoordJ, Adjacencia));//LEFT
        }
        else {
            if(direccio.equals("H")){
                this.Probabilitat = new Double[]{0.099, 0.099, 0.198, 0.099, 0.099, 0.099, 0.198, 0.099};
            }
            else if(direccio.equals("V")){
                this.Probabilitat = new Double[]{0.198, 0.099, 0.099, 0.099, 0.198, 0.099, 0.099, 0.099};
            }
            else {
                this.Probabilitat = new Double[]{0.12375, 0.12375, 0.12375, 0.12375, 0.12375, 0.12375, 0.12375, 0.12375};
            }

            veins_m.add(new Quadrada(CoordI, CoordJ - 1, Adjacencia));//TOP 0
            veins_m.add(new Quadrada(CoordI + 1, CoordJ - 1, Adjacencia));//TOP+RIGHT 1
            veins_m.add(new Quadrada(CoordI + 1, CoordJ, Adjacencia));//RIGHT 2
            veins_m.add(new Quadrada(CoordI + 1, CoordJ + 1, Adjacencia));//BOTTOM+RIGHT 3
            veins_m.add(new Quadrada(CoordI, CoordJ + 1, Adjacencia));//BOTTOM 4
            veins_m.add(new Quadrada(CoordI - 1, CoordJ + 1, Adjacencia));//BOTTOM+LEFT 5
            veins_m.add(new Quadrada(CoordI - 1, CoordJ, Adjacencia));//LEFT 6
            veins_m.add(new Quadrada(CoordI - 1, CoordJ - 1, Adjacencia));//TOP+LEFT 7
        }
    }

    @Override
    public Cela NextCela(){
        Cela nextcela;
        if(Adjacencia.equals("C")) {
            Double dd = ran.nextDouble();
            if(dd < Probabilitat[0]){
                nextcela = veins_m.get(0);//TOP
                PosicioNextCela = 0;
            }
            else if (dd < Probabilitat[0] + Probabilitat[1]){
                PosicioNextCela = 1;
                nextcela = veins_m.get(1);//RIGHT
            }
            else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2]){
                PosicioNextCela = 2;
                nextcela = veins_m.get(2);//BOTTOM
            }
            else {
                PosicioNextCela = 3;
                nextcela = veins_m.get(3);//LEFT
            }
        }
        else {

            Double dd = ran.nextDouble();
            if(dd < Probabilitat[0]){
                PosicioNextCela = 0;
                nextcela = veins_m.get(0);//TOP
            }
            else if (dd < Probabilitat[0] + Probabilitat[1]){
                PosicioNextCela = 1;
                nextcela = veins_m.get(1);//TOP+ RIGHT
            }
            else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2]){
                nextcela = veins_m.get(2);//RIGHT
                PosicioNextCela = 2;
            }
            else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2] + Probabilitat[3]){
                nextcela = veins_m.get(3);//BOTTOM+RIGHT
                PosicioNextCela = 3;
            }
            else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2]+ Probabilitat[3] + Probabilitat[4]){
                nextcela = veins_m.get(4);//BOTTOM
                PosicioNextCela = 4;
            }
            else if (dd < (Probabilitat[0] + Probabilitat[1] + Probabilitat[2] + Probabilitat[3] + Probabilitat[4]
                    + Probabilitat[5])){
                nextcela = veins_m.get(5);//BOTTOM+LEFT
                PosicioNextCela = 5;
            }
            else if (dd < (Probabilitat[0] + Probabilitat[1] + Probabilitat[2] + Probabilitat[3] + Probabilitat[4]
                    + Probabilitat[5] + Probabilitat[6])){
                nextcela = veins_m.get(6);//LEFT
                PosicioNextCela = 6;
            }
            else {
                nextcela = veins_m.get(7);//TOP+LEFT
                PosicioNextCela = 7;
            }
        }
        return nextcela;
    }

}
