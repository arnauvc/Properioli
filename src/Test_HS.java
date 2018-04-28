import Hidato.Generacio;
import Hidato.HidatosSolucionats;
import Hidato.Resolucio;
import Hidato.Tauler;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Test_HS {

    Generacio g = new Generacio();
    HidatosSolucionats hs = new HidatosSolucionats();
    Tauler t =  new Tauler();
    Resolucio r = new Resolucio();
    String[][] tauler = null;
    ArrayList<Pair<Pair<Integer, Integer>, String>> s = new ArrayList<Pair<Pair<Integer, Integer>, String>>();
    ArrayList<Pair<Pair<Integer, Integer>, String>> solucio = new ArrayList<Pair<Pair<Integer, Integer>, String>>();

    public void Provar() {
        tauler = g.GenerarHidato("Q", "C", "NORMAL");
        solucio.add(new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(1, 1), String.valueOf(2)));
        solucio.add(new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(2, 3), String.valueOf(4)));
        solucio.add(new Pair<Pair<Integer, Integer>, String>(new Pair<Integer, Integer>(5, 5), String.valueOf(8)));
        t.CrearTauler("Q", "C", tauler);
        try {
            hs.GuardarHidato(1, t, solucio);
        } catch (Exception e) {
            System.out.println("NO GUARDA");
            e.printStackTrace();
        }
        try {
             s = hs.CarregarSolucio(1, 1);
        } catch (Exception e) {
            System.out.println("AIXO NO CARREGA");
        }
        for (int i = 0; i < s.size(); i++) {
            System.out.println("(" + s.get(i).getKey().getKey() + "," + s.get(i).getKey().getValue() + ")" + s.get(i).getValue());
        }
    }
}
