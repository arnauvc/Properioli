package Hidato;

import java.util.ArrayList;
import java.util.Random;

public class Hexagonal extends Cela{

    private ArrayList<Cela> veins_meus = new ArrayList<>();
    private Random ran = new Random();

    public Hexagonal(Integer i, Integer j, String adj){
        this.CoordI = i;
        this.CoordJ = j;
        this.Adjacencia = adj;
    }

    @Override
    public void Veins(String direccio) {

        this.Probabilitat = new Double[]{0.165, 0.165, 0.165, 0.165, 0.165, 0.165};
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ-1,Adjacencia));//TOP+LEFT 0
        veins_meus.add(new Hexagonal(CoordI, CoordJ-1,Adjacencia));//TOP 1
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ,Adjacencia));//LEFT 2
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ+1,Adjacencia));//BOTTOM+LEFT 3
        veins_meus.add(new Hexagonal(CoordI, CoordJ+1,Adjacencia));//BOTTOM 4
        veins_meus.add(new Hexagonal(CoordI+1, CoordJ,Adjacencia));//RIGHT 5
    }

    @Override
    public Cela NextCela() {
        Cela nextcela;
        Double dd = ran.nextDouble();
        if(dd < Probabilitat[0]){
            PosicioNextCela = 0;
            nextcela = veins_meus.get(0);//TOP+LEFT
        }
        else if (dd < Probabilitat[0] + Probabilitat[1]){
            PosicioNextCela = 1;
            nextcela = veins_meus.get(1);//TOP
        }
        else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2]){
            PosicioNextCela = 2;
            nextcela = veins_meus.get(2);//LEFT
        }
        else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2] + Probabilitat[3]){
            PosicioNextCela = 3;
            nextcela = veins_meus.get(3);//BOTTOM+LEFT
        }
        else if (dd < Probabilitat[0] + Probabilitat[1] + Probabilitat[2] + Probabilitat[3] + Probabilitat[4]){
            PosicioNextCela = 4;
            nextcela = veins_meus.get(4);//BOTTOM
        }
        else {
            PosicioNextCela = 5;
            nextcela = veins_meus.get(5);//RIGHT
        }
        return nextcela;
    }

/*
    @Override
    public ArrayList<Cela> Veins(String direccio) {
        veins_meus.clear();
        ArrayList<Integer> ordre = new ArrayList<>();
        ArrayList<Cela> veins_ordenats = new ArrayList<>();

        veins_meus.add(new Hexagonal(CoordI-1, CoordJ-1,Adjacencia));//TOP+LEFT 0
        veins_meus.add(new Hexagonal(CoordI, CoordJ-1,Adjacencia));//TOP 1
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ,Adjacencia));//LEFT 2
        veins_meus.add(new Hexagonal(CoordI-1, CoordJ+1,Adjacencia));//BOTTOM+LEFT 3
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
    */
}
