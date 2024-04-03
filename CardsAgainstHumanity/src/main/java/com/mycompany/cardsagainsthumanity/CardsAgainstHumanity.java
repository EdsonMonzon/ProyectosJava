/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cardsagainsthumanity;

import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author dam1
 */
public class CardsAgainstHumanity {
    
    //Creamos las listas de cartas blancas y negras y el random
    public final static String cartasNegras[] = {"No hay cita sin ", "Para animar a los demas siempre ayuda ", "Estamos muy cerca de ", "Hace poco se descubrio "};
    public final static String cartasBlancas[] = {"un duende", "el poder de la amistad", "un billete falso del monopoly", "muchos peluches"};
    public final static Scanner input=new Scanner(System.in);
    public final static int ELEMENTOS_CARTAS_NEGRAS= cartasNegras.length;
    public final static int ELEMENTOS_CARTAS_BLANCAS= cartasBlancas.length;
    public final static int CARTAS_BLANCAS_A_MOSTRAR=4;
    
    public static void main(String[] args) {

        //Declaramos una variable para salir del juego
        boolean salir = false;

        //Entramos en el bucle principal , el menu
        while (!salir) {
            System.out.println("""
                               Elije una opcion
                               1. Genera una combinacion random
                               2. Jugar una partida
                               3. Salir""");

            //Guardamos la opcion elegida
            int opcio = input.nextInt();

            //Con un switch aplicamos la opcion elejida
            switch (opcio) {
                case 1 -> {
                    combinacionRandom();
                }
                case 2 -> {
                    System.out.println("Cuantos jugadores jugaran la partida?");
                    int numeroJugadores = input.nextInt();
                    jugarPartida(numeroJugadores);
                }
                case 3 -> {
                    System.out.println("Hasta la proxima");
                    salir = true;
                }
            }

        }
    }

    /**
     * Metodo que limpia la consola
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Metodo que genera una combinacion random entre una carta blanca y una
     * negra
     */
    public static void combinacionRandom() {

        Random random = new Random();

        //Generamos y guardamos 2 valores random
        int random1 = random.nextInt(ELEMENTOS_CARTAS_NEGRAS);
        int random2 = random.nextInt(ELEMENTOS_CARTAS_NEGRAS);

        //Cogemos 1 carta random de cada lista y las concatenamos
        System.out.println(cartasNegras[random1] + " " + cartasBlancas[random2]);
    }

    //Metodo que contiene la partida
    public static void jugarPartida(int numeroJugadores) {

        /**
         * Declaramos random, partidaFinalizada que nos ayudara a
         * terminar la partida, creamo una lista de cartas elejidas donde se
         * alamcenaran las cartas que elija el usuario, una lista con los puntos
         * de jugadores y una variable con el jugador ganador y por ultimo una
         * lista donde se almacenan las opciones de eleccion de cada ronda
         */
        Random random = new Random();
        boolean partidaFinalizada = false;
        String cartasElegidas[] = new String[numeroJugadores];
        int puntosJugadores[] = new int[numeroJugadores];
        int jugadorGanador = -1;

        int indexJugadorSaltaTorn = 1;

        String cartaOpcions[] = new String[CARTAS_BLANCAS_A_MOSTRAR];


        //Preguntamos por cuantos puntos jugaremos la partida
        System.out.println("A cuantos puntos quieren jugar esta partida?");
        int puntosGanadores = input.nextInt();

        //Instrucciones
        System.out.println("""
        \nLa partida va a empezar
        Se mostrara una carta negra y todos los jugadores menos unos debera de colocar una carta blanca tratando de crear la mejor frase
        El jugador que no coloco carta sera el encargado de elejir la mejor combinacion\n """);

        //Entramos en el bucle principal de la partida
        while (!partidaFinalizada) {

            //Cogemos una carta negra random
            String cartaNegra = cartasNegras[random.nextInt(ELEMENTOS_CARTAS_NEGRAS)];

            //Por cada jugador...
            for (int i = 0; i < numeroJugadores; i++) {

                //Se generaran sus cartas blancas aun sin mostrarse
                for (int j = 0; j < CARTAS_BLANCAS_A_MOSTRAR; j++) {
                    cartaOpcions[i] = cartasBlancas[random.nextInt(ELEMENTOS_CARTAS_NEGRAS)];
                }
                
                //Se muestra un mensaje del jugadro al que no le toque jugar en ese momento
                if (i + 1 == indexJugadorSaltaTorn) {
                    System.out.println("Jugador " + indexJugadorSaltaTorn + " esta ronda no elijes carta blanca, te toca elejir al ganador\n");
                } else {
                    //Se mostrara la carta negra
                    System.out.println("La carta negra de esta partida es:\n");
                    System.out.println(cartaNegra + " ...");

                    //Se mostrara un mensaje para que solo el jugador que tenga el turno pueda ver sus cartas al presionar Enter
                    System.out.println("Turno de el jugador " + (i + 1) + " para elejir una carta blanca, presiona Enter para verlas");
                    input.nextLine();
                    input.nextLine();

                    //Se le muestran sus cartas blancas
                    for(int j=0;j<CARTAS_BLANCAS_A_MOSTRAR;j++){
                    System.out.println((i+1)+ ". + "+cartaOpcions[j]);
                    }
                    
                    //Guardamos la eleccion
                    int cartaOpcio = input.nextInt();

                    //Se guarda en el espacio de cada juador la carta que ha elejido
                    switch (cartaOpcio) {
                        case 1 ->
                            cartasElegidas[i] = cartaOpcions[0];
                        case 2 ->
                            cartasElegidas[i] = cartaOpcions[1];
                        case 3 ->
                            cartasElegidas[i] = cartaOpcions[2];
                        case 4 ->
                            cartasElegidas[i] = cartaOpcions[3];

                    }
                    //Limpiamos la pantalla para que el siguiente jugador no vea la eleccion
                    clearScreen();
                }
            }

            //El jugador que se saltara pasa al siguiente
            indexJugadorSaltaTorn++;

            //Una vez todos los jugadores hayan elejido su carta blanca mostramos las frases de los jugadores al jugador que elejira
            System.out.println("Las frases son: ");

            //Creamos 2 arrays, en uno se guardaran las frases de los jugadores en orden y en otro en desorden           
            ArrayList<String> combinacionesAleatorias = new ArrayList<>();
            ArrayList<String> combinacionesOrdenadas = new ArrayList<>();

            //Guardamos en ambos arrays todas las frases en orden de llegada
            for (int i = 1; i < numeroJugadores; i++) {
                String combinacion = cartaNegra + " " + cartasElegidas[i - 1];
                combinacionesAleatorias.add(combinacion);
                combinacionesOrdenadas.add(combinacion);
            }
            //Añadimos 2 nulos que representan las elecciones del jugador que no juega esa ronda
            combinacionesAleatorias.add("");
            combinacionesOrdenadas.add("");

            //Con el metodo shuffle combinamos los elementos de combinacionesAleatorias
            Collections.shuffle(combinacionesAleatorias);

            //Luego imprimimos los elementos de combinaciones aleatorias
            for (String combinacion : combinacionesAleatorias) {
                System.out.println(combinacion);
            }

            // El jugador elige la mejor combinación 
            System.out.println("Elige al ganador:");
            int mejorCombinacionIndex = input.nextInt();
            String mejorCombinacion = combinacionesAleatorias.get(mejorCombinacionIndex - 1);

            // Luego buscamos su eleccion dentro de la lista de combinaciones ordenadas para determinar a que jugador le pertenece
            jugadorGanador = combinacionesOrdenadas.indexOf(mejorCombinacion);

            //Y le agregamos 1 a su contador de puntos
            puntosJugadores[jugadorGanador] += 1;
            System.out.println("¡El jugador " + (jugadorGanador + 1) + " ha ganado esta ronda!");

            //Con un bucle for revisamos los valores almacenados en turno jugadores
            for (int i = 0; i < numeroJugadores; i++) {

                //Si alguno supera los puntos seleccionado para ganar acaba la partida y guarda al jugador ganador
                if (puntosJugadores[i] >= puntosGanadores) {
                    partidaFinalizada = true;
                    jugadorGanador = i;
                    break; // Salir del bucle porque ya encontraste un ganador
                }
            }

        }

        //Imprime al ganador
        System.out.println(
                "El ganador de la partida es el jugador " + jugadorGanador + 1);
    }

}
