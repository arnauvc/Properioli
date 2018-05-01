package Hidato;

import Hidato.Generacio;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUnitari_Generacio {

    Resolucio res = new Resolucio();

    @Test
    public void HidatoHexagonalAmbInputIncorrecteDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("H","CA","D");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "H", "CA" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoHexagonalDificl()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("H","C","D");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "H", "C" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoHexagonalFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("H","C","F");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "H", "C" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoQuadratCostatsAnglesDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","CA","D");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "Q", "CA" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoQuadratCostatsAnglesFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","CA","F");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "Q", "CA" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoQuadratCostatsDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","C","D");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "Q", "C" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoQuadratCostatsFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("Q","C","F");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "Q", "C" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoTriangularDificil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("T","C","D");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "T", "C" );
        Print(solucio, tauler.length, tauler[0].length);
    }

    @Test
    public void HidatoTriangularFacil()  {
        Generacio gen = new Generacio();
        String[][] tauler;
        tauler = gen.GenerarHidato("T","C","F");
        Print(tauler, tauler.length, tauler[0].length);
        String[][] solucio;
        solucio = res.ResoltreHidato(tauler, "T", "C" );
        Print(solucio, solucio.length, solucio[0].length);
    }

    public void Print(String[][] matriu, Integer nfiles, Integer ncolumnes){
        for (int i = 0; i < nfiles; ++i){
            if (i > 0) System.out.println();
            for (int j = 0; j < ncolumnes; ++j){
                if (j > 0) System.out.printf(",%s", matriu[i][j]);
                else System.out.print(matriu[i][j]);
            }
        }
        System.out.println();
        System.out.println();
    }

}
