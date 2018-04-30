package Hidato;

import java.util.ArrayList;
import java.util.Random;

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
        Random ran = new Random();
        ArrayList<Integer> ordre = new ArrayList<>();
        ArrayList<Cela> veins_ordenats = new ArrayList<>();

        veins_meus.add(new Hexagonal(CoordI-1, CoordJ-1,Adjacencia));//TOP+LEFT 0
        veins_meus.add(new Hexagonal(CoordI, CoordJ-1,Adjacencia));//TOP 1
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ,Adjacencia));//LEFT 2
        veins_meus.add(new Hexagonal(CoordI+1, CoordJ-1,Adjacencia));//BOTTOM+LEFT 3
        veins_meus.add(new Hexagonal(CoordI, CoordJ+1,Adjacencia));//BOTTOM 4
        veins_meus.add(new Hexagonal(CoordI+1, CoordJ,Adjacencia));//RIGHT 5


        if(direccio.equals("H")){
            if(ran.nextInt(4) < 2){
                ordre.add(2);
                ordre.add(5);
                ordre.add(0);
                ordre.add(3);
                ordre.add(1);
                ordre.add(4);
            }
            else{
                ordre.add(5);
                ordre.add(2);
                ordre.add(0);
                ordre.add(3);
                ordre.add(4);
                ordre.add(1);
            }
        }
        else {
            if(ran.nextInt(4) < 2){
                ordre.add(1);
                ordre.add(4);
                ordre.add(0);
                ordre.add(3);
                ordre.add(5);
                ordre.add(2);
            }
            else{
                ordre.add(4);
                ordre.add(1);
                ordre.add(3);
                ordre.add(0);
                ordre.add(2);
                ordre.add(5);
            }
        }
        for (Integer i: ordre) {
            veins_ordenats.add(veins_meus.get(i));
        }

        return veins_ordenats;
    }
}
