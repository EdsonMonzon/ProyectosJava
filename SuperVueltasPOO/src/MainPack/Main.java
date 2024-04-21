package MainPack;
import Partida.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Menú de opciones para el usuario
        System.out.println("""
                Bienvenido a SuperVueltas, elije una opcion
                1.Jugar partida clasica
                2.Jugar una partida personalizada
                3.Cargar una partida
                4.Salir
                """);
        int opcio = Input.between(1, 4, "Introduce un numero que este entre las opciones");
        switch (opcio) {
            case 1 -> {
                // Iniciar partida clásica
                empezarPartidaClasica();
            }
            case 2 -> {
                // Iniciar partida personalizada
                empezarPartidaPersonalizada();
            }
            case 3 -> {
                // Cargar una partida guardada
                cargarPartida();
            }
            case 4 -> {
                // Salir del juego
                salir();
            }
        }
    }

    public static void empezarPartidaClasica() {
        // Solicitar el número de jugadores para la partida clásica
        System.out.println("Cuantos jugadores jugaran esta partida?");
        int numJugadores = Input.between(2, 4, "Deben haber entre 2 y 4 jugadores");
        // Crear una nueva partida con el número de jugadores y tamaño de tablero predeterminados
        Juego partida = new Juego(numJugadores, 5);
        // Crear jugadores y generar el tablero
        partida.crearJugadores();
        partida.generarTablero();
        // Mostrar el tablero inicial
        partida.mostrarTablero();
        // Comenzar la partida
        empezarPartida(partida);
    }

    public static void empezarPartidaPersonalizada() {
        // Solicitar el número de jugadores y el tamaño del tablero para la partida personalizada
        System.out.println("Cuantos jugadores jugaran esta partida?");
        int numJugadores = Input.integer("Introduce un numero entero");
        System.out.println("Que tamaño quieres que tenga el tablero?");
        int tamañotablero = Input.integer("Introduce un numero entero");
        // Crear una nueva partida con el número de jugadores y tamaño de tablero especificados
        Juego partida = new Juego(numJugadores, tamañotablero);
        // Crear jugadores y generar el tablero
        partida.crearJugadores();
        partida.generarTablero();
        // Mostrar el tablero inicial
        partida.mostrarTablero();
        // Comenzar la partida
        empezarPartida(partida);
    }

    public static void cargarPartida() {
        // Solicitar al usuario que elija una partida para cargar
        String archivo = elegirPartida();
        // Leer y cargar los datos de la partida seleccionada
        leerPartida(archivo);
    }

    public static String elegirPartida(){
        // Solicitar al usuario que elija una partida guardada
        System.out.println("Quieres cargar la partida 1, 2 o 3");
        int opcio = Input.between(1, 3, "Solo hay 3 partidas guardadas");
        String archivo = "";
        // Asignar el nombre de archivo correspondiente a la partida seleccionada
        switch (opcio) {
            case 1 -> {archivo = "partida1.txt";}
            case 2 -> {archivo = "partida2.txt";}
            case 3 -> {archivo = "partida3.txt";}
        }
        return archivo;
    }

    public static void leerPartida(String archivo){
        Scanner sc = null;
        File f = new File(archivo);

        try {
            sc = new Scanner(f);
            // Leer el tamaño del tablero y el número de jugadores
            int tamañoMapa = sc.nextInt();
            int numJugadores = sc.nextInt();
            int[] posicionesJugadores = new int[numJugadores];
            int[][] mapaCamino = new int[tamañoMapa][tamañoMapa];
            int[][] mapaValores = new int[tamañoMapa][tamañoMapa];
            ArrayList<Integer> casillasReveladas = new ArrayList<>();

            // Leer las posiciones iniciales de los jugadores
            for(int i = 0; i < numJugadores; i++) {
                posicionesJugadores[i] = sc.nextInt();
            }

            sc.nextLine();

                String linea = sc.nextLine(); // Lee la línea completa
                Scanner lineaScanner = new Scanner(linea); // Crea un nuevo scanner para procesar la línea
                while (lineaScanner.hasNextInt()) {
                    casillasReveladas.add(lineaScanner.nextInt()); // Agrega los enteros a la lista
                }
                lineaScanner.close(); // Cierra el scanner de la línea para liberar recursos

            // Leer el mapa de camino y los valores de las casillas
            for(int i = 0; i < tamañoMapa; i++) {
                for(int j = 0; j < tamañoMapa; j++) {
                    mapaCamino[i][j] = sc.nextInt();
                }
                sc.nextLine();
            }

            for(int i = 0; i < tamañoMapa; i++) {
                for(int j = 0; j < tamañoMapa; j++) {
                    mapaValores[i][j] = sc.nextInt();
                }
                sc.nextLine();
            }

            sc.close(); // Cerrar el Scanner después de leer los datos

            sc=new Scanner(System.in);
            // Crear una nueva partida con los datos cargados
            Juego partida = new Juego(numJugadores, tamañoMapa);

            // Crear jugadores con las posiciones iniciales cargadas
            for (int i = 0; i < partida.getNumeroJugadores(); i++) {
                System.out.println("Jugador " + (i + 1)+" elije tu ficha");
                char ficha = sc.next().charAt(0);
                partida.listaJugadores[i] = new Jugador(ficha, i, posicionesJugadores[i], partida);
            }

            // Construir el tablero de juego
            int buscador = 1;
            while(buscador <= ultimoCuadro(mapaCamino)) {
                for (int i = 0; i < tamañoMapa; i++) {
                    for (int j = 0; j < tamañoMapa; j++) {

                        if (mapaCamino[i][j] == buscador) {
                            partida.tablero[i][j] = new Casilla(i, j, mapaValores[i][j], partida);

                            for(Jugador juga:partida.listaJugadores){
                                if(juga.getNumero()==buscador){
                                    partida.tablero[i][j].addJugador(juga);
                                    partida.listaJugadores[juga.getNumeroJugador()].setCasilla(partida.tablero[i][j]);
                                }
                            }
                            for(int c:casillasReveladas){
                                if(partida.tablero[i][j].getNumero()==c){
                                    partida.tablero[i][j].setRevelada(true);
                                }
                            }
                            buscador++;
                        }else if(!(mapaCamino[i][j]>0 && mapaCamino[i][j]<=ultimoCuadro(mapaCamino))){
                            partida.tablero[i][j] = new Casilla(i, j, 0, partida);
                        }

                    }
                }
            }
            partida.asignarNumeroCasillas();

            sc=new Scanner(System.in);

            // Mostrar el tablero inicial
            partida.mostrarTablero();

            // Comenzar la partida
            empezarPartida(partida);

            sc.close();

        } catch (FileNotFoundException e) {
            // Manejar la excepción si el archivo no se encuentra
            throw new RuntimeException(e);
        }
    }

    public static int ultimoCuadro(int[][] mapaCamino) {
        // Encontrar el número más grande en el mapa de camino
        int numeroMayor = 0;
        for (int i = 0; i < mapaCamino.length; i++) {
            for (int j = 0; j < mapaCamino[i].length; j++) {
                if (mapaCamino[i][j] > numeroMayor) {
                    numeroMayor = mapaCamino[i][j];
                }
            }
        }
        return numeroMayor;
    }

    public static void empezarPartida(Juego partida) {
        // Iniciar el bucle del juego
        while (true) {
            for (Jugador juga : partida.listaJugadores) {
                // Verificar si el jugador puede jugar su turno
                if (juga.efectoCasilla != 1) {
                    // Realizar el turno del jugador y mostrar el tablero
                    juga.turnoJugador();
                    partida.mostrarTablero();
                } else {
                    // Notificar al jugador que ha perdido su turno
                    System.out.println("Este jugador ha perdido su turno");
                    juga.efectoCasilla = 0;
                }
            }
        }
    }

    public static void salir() {
        // Salir del juego
        System.exit(0);
    }
/**
    public static void imprimir(int[][] mapa) {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }*/
}
