package Hidato;

public class Partida {
    private enum Dificultat{
        EASY, MEDIUM, HARD
    }
    private Dificultat d;
    //Si els dos booleans seguents estan a fals, el temps servira pel ranking, sino no.
    private boolean ajuda;//Ha fet servir ajuda en algun moment de la partida
    private boolean guardat;//Ha guardat la partida en algun moment
    private boolean finalitzat;
    private float   temps;


}
