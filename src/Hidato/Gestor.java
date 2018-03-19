package Hidato;

import Hidato.Rellotge;
import Hidato.Error;
import Hidato.Ranking;
import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;

public class Gestor {

	private Error e;
	private Ranking ranking;
	private Rellotge rellotge;

    //Podem eliminar aquest usuari actiu potser
    private Usuari usuariactiu;//Conte l'objecte usuari del usuari actiu en el sistema
	
	public void IniciaJoc(){
		
		
	}
    public void CarregaPartida(){}
    public void ActulitzarRanking(){}

	public void Test(){
		//Aixo es un test de la classe rellotge. No forma part del main com a tal
        Rellotge r = new Rellotge();
        r.start();
        for(long i=0; i<100000000 ; i++) {
            Integer a = 0;//Codi Trivial
            Integer b = 3;//Codi Trivial
        }
        r.stop();
        System.out.println(r.GetTime());
        //Fi del test

        //Test d'usuari. Idem
        Usuari u = new Usuari();
        u.SetNom("Arnau");
        System.out.println(u.GetNom());
        //Fi test usuari

        //Test Ranking
        Ranking ra = new Ranking();
        ra.Inicialitzar();
        ra.Actualitzar(1,72.4);
        ra.Actualitzar(1, 63.5);
        ra.Actualitzar(1,95.7);
        ra.Actualitzar(1, 46.9);
        ra.Actualitzar(1,32.7);
        ra.Actualitzar(1, 56.9);
        System.out.println(ra.GetValue(1));
        //Per algun motiu que desconec, el punyetero ranking no surt ordenat. Ni en natural order, ni en reversed

        ra.Actualitzar(2, 3.14);
        System.out.println(ra.GetValue(2));
        //Fi test ranking

        //Test Error
        Error e = new Error();
        e.PrintError(1);
        e.PrintError(2);
        e.PrintError(5);
        //Fi test error

		//Test Partida; 
		/* 
		Partida par = new Partida();
		par.IniciaPartida();//Aquest metode falla degut, possiblement, a una falta de inicialitzacio
		for(long i=0; i<100000000 ; i++) {
            Integer a = 0;//Codi Trivial
            Integer b = 3;//Codi Trivial
        }
		par.AcabaPartida();
		System.out.println(par.GetTemps());
		
		If (par.Finalitzada()) {
		    u.AfegirTaulers(Tauler.Getid());
		}
		
		*/
		//Fi test
	}

}
