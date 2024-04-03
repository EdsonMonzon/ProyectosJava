import java.util.Random;
import java.util.Scanner;

public class Jugador {

    public static final Random random = new Random();
    char ficha;
    int numeroJugador;
    Casilla casillaJugador;
    int efectoCasilla;
    int habilidad;


    public Jugador(char ficha, int numeroJugador,Casilla casilla) {
        this.ficha = ficha;
        this.numeroJugador = numeroJugador;
        this.casillaJugador=casilla;
    }
    public void setCasilla(Casilla casilla){
        this.casillaJugador=casilla;
    }
}
