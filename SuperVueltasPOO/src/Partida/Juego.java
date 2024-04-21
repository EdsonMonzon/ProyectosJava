package Partida;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private final Random random = new Random();
    private int numeroJugadores;
    private int tamañoTablero;
    private int tamañoCamino;
    public Casilla[][] tablero;
    public Jugador[] listaJugadores;

    // Constructor de la clase Juego
    public Juego(int numeroJugadores, int tamañoTablero) {
        this.numeroJugadores = numeroJugadores;
        this.tamañoTablero = tamañoTablero;
        tamañoCamino = (tamañoTablero - 1) * 4;
        tablero = new Casilla[this.tamañoTablero][this.tamañoTablero];
        listaJugadores = new Jugador[this.numeroJugadores];
    }

    public int getTamañoTablero() {
        return tamañoTablero;
    }

    public int getTamañoCamino() {
        return tamañoCamino;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    // Método para generar el tablero del juego
    public void generarTablero() {

        int ultimoValorGenerado=0;

        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {

                if (i == 0 && j == 0) {
                    tablero[i][j] = new Casilla(i, j, 1, this);
                    for (Jugador jugador : listaJugadores) {
                        tablero[i][j].addJugador(jugador);
                        jugador.setCasilla(tablero[i][j]);
                    }
                } else if (i == 0 || i == tamañoTablero - 1 || j == 0 || j == tamañoTablero - 1) {

                    int valorRandom = random.nextInt(1,10);
                    if(ultimoValorGenerado==6){
                        if(valorRandom==5){
                            valorRandom=3;
                        }
                    }
                    tablero[i][j] = new Casilla(i, j, valorRandom, this);
                    ultimoValorGenerado=valorRandom;
                } else {
                    tablero[i][j] = new Casilla(i, j, 0, this);
                }
            }
        }
        asignarNumeroCasillas();
    }

    // Método para asignar números a las casillas del tablero
    public void asignarNumeroCasillas() {
        int fila = 0;
        int columna = 0;
        int pasosFaltantes = tamañoCamino;
        int contador = 1;
        while (pasosFaltantes > 0) {
            tablero[fila][columna].setNumero(contador);
            if (fila == 0 && columna != tamañoTablero - 1) {
                columna++;
            } else if (columna == tamañoTablero - 1 && fila != tamañoTablero - 1) {
                fila++;
            } else if (fila == tamañoTablero - 1 && columna != 0) {
                columna--;
            } else {
                fila--;
            }
            contador++;
            pasosFaltantes--;
        }
    }

    // Método para crear jugadores y asignar fichas
    public void crearJugadores() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("Jugador " + (i + 1) + ", introduce tu ficha:");
            char ficha = input.next().charAt(0);
            listaJugadores[i] = new Jugador(ficha, i, 1, this);
        }
    }

    // Método para mostrar el tablero y la posición de los jugadores
    public void mostrarTablero() {
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                Casilla casilla = tablero[i][j];
                if (casilla.getEfecto() == 0) {
                    System.out.print("     ");
                } else if (casilla.hayJugador()) {
                    System.out.print("  " + casilla.getJugadores().get(0).ficha + "  ");
                } else if (casilla.getEfecto() > 0 && casilla.getEfecto() < 4) {
                    System.out.print(" [ ] ");
                } else if (!casilla.isRevelada()) {
                    System.out.print(" [?] ");
                } else {
                    switch (casilla.getEfecto()) {
                        case 4 -> System.out.print(" [/] ");
                        case 5 -> System.out.print(" [<] ");
                        case 6 -> System.out.print(" [>] ");
                        case 7 -> System.out.print(" [+] ");
                        case 8 -> System.out.print(" [-] ");
                        case 9 -> System.out.print(" [x] ");
                    }
                }
            }
            System.out.println();
        }
        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("El jugador " + (i + 1) + " está en la casilla " + listaJugadores[i].numero);
        }
    }

    public void guardarPartida() {

        String archivo=pedirRuta();

        try {
            FileWriter writer = new FileWriter(archivo);
            writer.write(tamañoTablero + " ");
            writer.write(numeroJugadores + " ");

            // Escribir las posiciones iniciales de los jugadores
            for (Jugador juga : listaJugadores) {
                writer.write(juga.casillaJugador.getNumero() + " ");
            }
            writer.write("\n");

            for (int i = 0; i < tamañoTablero; i++) {
                for (int j = 0; j < tamañoTablero; j++) {
                    if(tablero[i][j].isRevelada()){
                        writer.write(String.valueOf(tablero[i][j].getNumero())+" ");
                    }
                }
            }
            writer.write("\n");

            // Escribir el mapa de camino
            for (int i = 0; i < tamañoTablero; i++) {
                for (int j = 0; j < tamañoTablero; j++) {

                    writer.write(tablero[i][j].getNumero() + " ");
                }
                writer.write("\n");
            }
            // Escribir los valores de las casillas del tablero
            for (int i = 0; i < tamañoTablero; i++) {
                for (int j = 0; j < tamañoTablero; j++) {
                    writer.write(tablero[i][j].getEfecto() + " ");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Archivo de partida creado exitosamente: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de partida: " + e.getMessage());
        }
    }

    public String pedirRuta() {
        System.out.println("En que espacio quieres guardar tu partida? 1,2 o 3");

        String ruta="";
        int opcio=Input.between(1,3,"Elije un espacio valido");
        switch (opcio){
            case 1->ruta="partida1.txt";
            case 2->ruta="partida2.txt";
            case 3->ruta="partida3.txt";
        }

        return ruta;
    }
}
