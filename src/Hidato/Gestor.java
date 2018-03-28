package Hidato;

import Hidato.Rellotge;
import Hidato.Error;
import Hidato.Ranking;
import Hidato.Tauler;
import Hidato.Usuari;
import Hidato.Partida;
import java.util.Iterator;
import java.util.TreeSet;


public class Gestor {

	private Error e;
	private Ranking ranking;
	private Rellotge rellotge;

    //Podem eliminar aquest usuari actiu potser
    private Usuari usuariactiu;//Conte l'objecte usuari del usuari actiu en el sistema
    private Partida partidaactiva;
    private Tauler tauler;
	public void IniciaJoc(){

	}

    public void CarregaPartida(){

    }

    public void ActulitzarRanking(){

	    //ranking.Actualitzar(partidaactiva.GetIdHidato(), partidaactiva.GetTemps() );
    }

	public void Test(){

        tauler = new Tauler(1);
        System.out.print("El numero de id del tauler: ");

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
        ra.Actualitzar(1,2.4);
        ra.Actualitzar(1,5.6);
        ra.Actualitzar(1,7.4);
        ra.Actualitzar(1,1.4);
        ra.Actualitzar(1,3.4);

        /*
        TreeSet<Double> resultat = ra.GetValue(1);
        //Versio 1
        Iterator iterator;
        iterator = resultat.iterator();
        Integer count = 0;
        while (iterator.hasNext() & count <5) {
            System.out.println(iterator.next() + " ");
            count++;
        }
        */
        //Versio 2
        System.out.println(ra.GetValue(1));


        //ra.Actualitzar(2, 7);
        //System.out.println(ra.GetValue(2));
        //Fi test ranking

        //Test Error
        Error e = new Error();
        e.PrintError(1);
        e.PrintError(2);
        e.PrintError(5);
        //Fi test error

		//Test Partida;

		Partida par = new Partida();
		par.IniciaPartida();//Aquest metode falla degut, possiblement, a una falta de inicialitzacio


        par.TranscursPartida();

		par.AcabaPartida();
		
		/*If (par.Finalitzada()) {
		    u.AfegirTaulers(Tauler.Getid());
		}*/
		

		//Fi test
	}

}
