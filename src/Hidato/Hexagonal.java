package Hidato;

import java.util.ArrayList;

public class Hexagonal extends Cela{

    private ArrayList<Cela> veins_meus = new ArrayList<>();

    public Hexagonal(Integer i, Integer j, String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;
    }


    @Override
    public ArrayList<Cela> Veins(String direccio) {
        veins_meus.clear();

        veins_meus.add(new Hexagonal(CoordI-1, CoordJ-1,Adjacencia));//TOP+LEFT
        veins_meus.add(new Hexagonal(CoordI, CoordJ-1,Adjacencia));//TOP
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ,Adjacencia));//LEFT
        veins_meus.add(new Hexagonal(CoordI+1, CoordJ-1,Adjacencia));//BOTTOM+LEFT
        veins_meus.add(new Hexagonal(CoordI, CoordJ+1,Adjacencia));//BOTTOM
        veins_meus.add(new Hexagonal(CoordI+1, CoordJ,Adjacencia));//RIGHT


        return veins_meus;
    }
}
