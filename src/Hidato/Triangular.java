package Hidato;

import java.util.ArrayList;

public class Triangular extends Cela{

    private ArrayList<Cela> veins_meus = new ArrayList<>();

    public Triangular(Integer i, Integer j, String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;
    }

    @Override
    public ArrayList<Cela> Veins(String direccio) {
        veins_meus.clear();
        if((CoordI%2 == 0 && CoordJ%2 == 0) || (CoordI%2 != 0 && CoordJ%2 != 0)){
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//LEFT
            veins_meus.add(new Triangular(CoordI,CoordJ-1,Adjacencia));//BOTTOM
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//RIGHT


        }
        else{
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//LEFT
            veins_meus.add(new Triangular(CoordI,CoordJ+1,Adjacencia));//TOP
            veins_meus.add(new Triangular(CoordI+1,CoordJ,Adjacencia));//RIGHT


        }
        return veins_meus;
    }
}
