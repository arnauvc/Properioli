package Hidato;

public class Error {
    public void PrintError(Integer codierror){
        String missatge;
        switch (codierror){
            case 1:
                missatge = "Posicio invalida!";
                break;
            case 2:
                missatge = "Missatge d'exemple del codi 2";
                break;
            default:
                missatge = "Codi d'error no reconegut";
                break;
        }
        System.out.println(missatge);

    }

}
