package Hidato;

public class Error {
    public void PrintError(Integer codierror){
        String missatge;
        switch (codierror){
            case 1:
                missatge = "Posicio invalida!";
                break;
            case 2:
                missatge = "No hi ha solucio, ha fallat Generacio";
                break;
            default:
                missatge = "Codi d'error no reconegut";
                break;
        }
        System.out.println(missatge);

    }

}
