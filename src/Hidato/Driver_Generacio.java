package Hidato;

import Hidato.Generacio;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Driver_Generacio {

    @Test
    public void HidatoHexagonalFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("H","C","F");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoHexagonalDificl()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("H","C","D");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoQuadratCostatsFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","C","F");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoQuadratCostatsDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","C","D");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoQuadratCostatsAnglesFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","CA","F");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoTriangularFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("T","C","F");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }

    @Test
    public void HidatoTriangularDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("T","C","D");
        Print(tauler, gen.GetNumFiles(), gen.GetNumColumnes());
    }



    public void Print(String[][] tauler, Integer nfiles, Integer ncolumnes){
        for (int i = 0; i < nfiles; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncolumnes; ++j){
                if (j > 0) System.out.printf(",%s", tauler[i][j]);
                else System.out.print(tauler[i][j]);
            }
        }
        System.out.println();
        System.out.println();
    }

}
