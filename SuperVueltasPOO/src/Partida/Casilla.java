package Partida;

import java.util.ArrayList;

public class Casilla {
    private int numero;
    private int fila;
    private int columna;
    private int efecto;
    private boolean revelada = false;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private Juego juego;

    // Constructor de la clase Casilla
    public Casilla(int fila, int columna, int efecto, Juego juego) {
        this.fila = fila;
        this.columna = columna;
        this.efecto = efecto;
        this.juego = juego;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isRevelada() {
        return revelada;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }

    // Método para establecer el número de la casilla
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Método para verificar si hay jugadores en la casilla
    public boolean hayJugador() {
        return !jugadores.isEmpty();
    }

    // Método para agregar un jugador a la casilla
    public void addJugador(Jugador jugador) {
        // Verificar si el jugador ya está en la casilla antes de agregarlo
        if (!jugadores.contains(jugador)) {
            jugadores.add(jugador);
        }
    }

    // Método para eliminar un jugador de la casilla
    public void deleteJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }
}
