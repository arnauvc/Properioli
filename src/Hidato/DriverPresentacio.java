package Hidato;
import java.util.Scanner;

public class DriverPresentacio {
    public void Inici() throws Exception{
        Scanner lector = new Scanner(System.in);
        String accio = "";
        System.out.println("Si vols executar el joc prem 1");
        System.out.println("Si vols executar el driver de Resolucio prem 2");
        System.out.println("Si vols executar el driver de Usuari prem 3");
        System.out.println("Si vols executar el driver de Partida prem 4");
        System.out.println("Si vols executar el driver de Tauler prem 5");
        System.out.println("Si vols executar el test de Generacio prem 6");
        System.out.println("Si vols executar el test de HidatosSolucionats prem 7");
        accio = lector.nextLine();
        switch (Integer.parseInt(accio)){
            case 1:
                CtrlPresGestor cpg = new CtrlPresGestor();
                cpg.Inicia();
                break;
            case 2:
                Driver_Resolucio dr = new Driver_Resolucio();
                dr.Prova();
                break;
            case 3:
                Driver_Usuari du = new Driver_Usuari();
                du.Prova();
                break;
            case 4:
                DriverPartida dp = new DriverPartida();
                dp.Test();
                break;
            case 5:
                DriverTauler dt = new DriverTauler();
                dt.inicia();
                break;
            case 6:
                TestUnitari_Generacio tg = new TestUnitari_Generacio();
                tg.SuiteTestosGeneracio();
                break;
            case 7:
                Test_HS test_hs = new Test_HS();
                test_hs.Provar();
                break;
        }
    }
}
