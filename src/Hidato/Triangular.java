package Hidato;

import java.util.ArrayList;
import java.util.Random;

public class Triangular extends Cela{

    private ArrayList<Cela> veins_meus = new ArrayList<>();
    private Random ran = new Random();

    public Triangular(Integer i, Integer j, String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;

    }

    @Override
    public ArrayList<Cela> Veins(String direccio) {
        veins_meus.clear();
        ArrayList<Integer> ordre = new ArrayList<>();
        ArrayList<Cela> veins_ordenats = new ArrayList<>();

        if((CoordI%2 == 0 && CoordJ%2 == 0) || (CoordI%2 != 0 && CoordJ%2 != 0)){
            veins_meus.add(new Triangular(CoordI-1,CoordJ,Adjacencia));//LEFT 0
            veins_meus.add(new Triangular(CoordI,CoordJ-1,Adjacencia));//BOTTOM 1
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//RIGHT 2
        }
        else{
            veins_meus.add(new Triangular(CoordI-1,CoordJ,Adjacencia));//LEFT
            veins_meus.add(new Triangular(CoordI,CoordJ+1,Adjacencia));//TOP
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//RIGHT
        }


        if(direccio.equals("H")){
            if(ran.nextInt(4) < 2){ ordre.add(0);ordre.add(2);}
            else{ ordre.add(2);ordre.add(0); }
            ordre.add(1);
        }
        else {
            ordre.add(1);
            if(ran.nextInt(4) < 2){ ordre.add(2);ordre.add(0); }
            else{ordre.add(0);ordre.add(2); }
        }
        for (Integer i: ordre) {
            veins_ordenats.add(veins_meus.get(i));
        }

        return veins_ordenats;
    }
}
