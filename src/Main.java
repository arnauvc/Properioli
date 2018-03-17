import Hidato.Rellotge;

public class Main {
    public static void main(String[] args) {
        Rellotge r = new Rellotge();
        r.start();
        for(long i=0; i<100000000 ; i++) {
            Integer a = 4;
        }


        r.stop();
        System.out.println(r.getElapsedTimeSecs());
    }
}
