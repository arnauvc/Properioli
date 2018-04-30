package Hidato;

import java.util.ArrayList;
import java.util.Random;

public class Quadrada extends Cela{

    private ArrayList<Cela> veins_m = new ArrayList<>();
    private Random ran = new Random();

    public Quadrada(Integer i, Integer j,String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;
    }
    /*
    @Override
    public ArrayList<Cela> Veins(){
        if(Adjacencia.equals("C")) {
            veins_m.add(new Quadrada(CoordI, CoordJ + 1, Adjacencia));//TOP
            veins_m.add(new Quadrada(CoordI + 1, CoordJ, Adjacencia));//RIGHT
            veins_m.add(new Quadrada(CoordI, CoordJ - 1, Adjacencia));//BOTTOM
            veins_m.add(new Quadrada(CoordI - 1, CoordJ, Adjacencia));//LEFT
        }
        else {
            veins_m.add(new Quadrada(CoordI, CoordJ + 1, Adjacencia));//TOP 0
            veins_m.add(new Quadrada(CoordI + 1, CoordJ + 1, Adjacencia));//TOP+RIGHT 1
            veins_m.add(new Quadrada(CoordI + 1, CoordJ, Adjacencia));//RIGHT 2
            veins_m.add(new Quadrada(CoordI + 1, CoordJ - 1, Adjacencia));//BOTTOM+RIGHT 3
            veins_m.add(new Quadrada(CoordI, CoordJ - 1, Adjacencia));//BOTTOM 4
            veins_m.add(new Quadrada(CoordI - 1, CoordJ - 1, Adjacencia));//BOTTOM+LEFT 5
            veins_m.add(new Quadrada(CoordI - 1, CoordJ, Adjacencia));//LEFT 6
            veins_m.add(new Quadrada(CoordI - 1, CoordI + 1, Adjacencia));//TOP+LEFT 7
        }
        return veins_m;
    }
    */

    @Override
    public ArrayList<Cela> Veins(String direccio) {
        veins_m.clear();

        ArrayList<Integer> ordre = new ArrayList<>();
        ArrayList<Cela> veins_ordenats = new ArrayList<>();
        if(Adjacencia.equals("C")){
            veins_m.add(new Quadrada(CoordI,CoordJ+1,Adjacencia));//TOP
            veins_m.add(new Quadrada(CoordI+1,CoordJ,Adjacencia));//RIGHT
            veins_m.add(new Quadrada(CoordI,CoordJ-1,Adjacencia));//BOTTOM
            veins_m.add(new Quadrada(CoordI-1,CoordJ,Adjacencia));//LEFT

            if(direccio.equals("H")){
                if(ran.nextInt(4) < 2){ ordre.add(1);ordre.add(3);}
                else{ ordre.add(3);ordre.add(1); }
                if(ran.nextInt(4) < 2){ ordre.add(0);ordre.add(2); }
                else{ordre.add(2);ordre.add(0); }
            }
            else {
                if(ran.nextInt(4) < 2){ ordre.add(0);ordre.add(2);}
                else{ ordre.add(2);ordre.add(0); }
                if(ran.nextInt(4) < 2){ ordre.add(1);ordre.add(3); }
                else{ordre.add(3);ordre.add(1); }
            }
            for (Integer i: ordre) {
                veins_ordenats.add(veins_m.get(i));
            }

        }
        else{
            veins_m.add(new Quadrada(CoordI,CoordJ+1,Adjacencia));//TOP 0
            veins_m.add(new Quadrada(CoordI+1,CoordJ+1,Adjacencia));//TOP+RIGHT 1
            veins_m.add(new Quadrada(CoordI+1,CoordJ,Adjacencia));//RIGHT 2
            veins_m.add(new Quadrada(CoordI+1,CoordJ-1,Adjacencia));//BOTTOM+RIGHT 3
            veins_m.add(new Quadrada(CoordI,CoordJ-1,Adjacencia));//BOTTOM 4
            veins_m.add(new Quadrada(CoordI-1,CoordJ-1,Adjacencia));//BOTTOM+LEFT 5
            veins_m.add(new Quadrada(CoordI-1,CoordJ,Adjacencia));//LEFT 6
            veins_m.add(new Quadrada(CoordI-1,CoordI+1,Adjacencia));//TOP+LEFT 7

            if(direccio.equals("H")){
                if(ran.nextInt(4) < 2){
                    ordre.add(2);
                    ordre.add(6);
                    ordre.add(7);
                    ordre.add(3);
                    ordre.add(5);
                    ordre.add(1);
                }
                else{
                    ordre.add(6);
                    ordre.add(2);
                    ordre.add(7);
                    ordre.add(3);
                    ordre.add(5);
                    ordre.add(1);
                }
                if(ran.nextInt(4) < 2){ ordre.add(0);ordre.add(4); }
                else{ordre.add(4);ordre.add(0); }
            }
            else{
                if(ran.nextInt(4) < 2){
                    ordre.add(0);
                    ordre.add(4);
                    ordre.add(7);
                    ordre.add(3);
                    ordre.add(5);
                    ordre.add(1);
                }
                else{
                    ordre.add(4);
                    ordre.add(0);
                    ordre.add(1);
                    ordre.add(5);
                    ordre.add(3);
                    ordre.add(7);
                }
                if(ran.nextInt(4) < 2){ ordre.add(2);ordre.add(6); }
                else{ordre.add(6);ordre.add(2); }
            }
            for (Integer i: ordre) {
                veins_ordenats.add(veins_m.get(i));
            }
        }

        return veins_ordenats;
    }



}
