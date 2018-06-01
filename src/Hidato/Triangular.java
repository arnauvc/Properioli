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
    public void Veins(String direccio) {
        veins_meus.clear();
        if(direccio.equals("H")){
            this.Probabilitat = new Double[]{0.396, 0.198, 0.396};
        }
        else if(direccio.equals("V")){
            this.Probabilitat = new Double[]{0.165, 0.66, 0.165};
        }
        else {
            this.Probabilitat = new Double[]{0.33, 0.33, 0.33};
        }

        if ((CoordI % 2 == 0 && CoordJ % 2 == 0) || (CoordI % 2 != 0 && CoordJ % 2 != 0)) {
            veins_meus.add(new Triangular(CoordI - 1, CoordJ, Adjacencia));//LEFT 0
            veins_meus.add(new Triangular(CoordI, CoordJ + 1, Adjacencia));//BOTTOM 1
            veins_meus.add(new Triangular(CoordI + 1, CoordJ, Adjacencia));//RIGHT 2
        } else if ((CoordI % 2 != 0 && CoordJ % 2 == 0) || (CoordI % 2 == 0 && CoordJ % 2 != 0)) {
            veins_meus.add(new Triangular(CoordI - 1, CoordJ, Adjacencia));//LEFT
            veins_meus.add(new Triangular(CoordI, CoordJ - 1, Adjacencia));//TOP
            veins_meus.add(new Triangular(CoordI + 1, CoordJ, Adjacencia));//RIGHT
        }
    }

    @Override
    public Cela NextCela(){
        Cela nextcela;
        Double dd = ran.nextDouble();
        if(dd < Probabilitat[0]){
            nextcela = veins_meus.get(0);//LEFT
            PosicioNextCela = 0;
        }
        else if (dd < Probabilitat[0] + Probabilitat[1]){
            PosicioNextCela = 1;
            nextcela = veins_meus.get(1);//BOTTOM OR TOP
        }
        else {
            PosicioNextCela = 2;
            nextcela = veins_meus.get(2);//RIGHT
        }
        return nextcela;
    }

    /*
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
        else if ((CoordI%2 != 0 && CoordJ%2 == 0) || (CoordI%2 == 0 && CoordJ%2 != 0)){
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
    */
}
