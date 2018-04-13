import Hidato.CtrlPresGestor;
import Hidato.HidatosSolucionats;


public class Main {
    public static void main(String[] args) throws Exception {
        String[][] s = new String[3][3];
        String[][] solucio = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j%2 == 0) s[i][j] = "#";
                else s[i][j] = "1";
                solucio[i][j] = "1";
            }
        }
        HidatosSolucionats hs = new HidatosSolucionats();
        hs.GuardarHidato(1,s,solucio);

        while(true) {
            CtrlPresGestor cpg = new CtrlPresGestor();
            cpg.Inicia();
        }
    }
}
