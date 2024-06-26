package PartidaPack;

import java.util.Random;
import java.util.Scanner;

public class Jugador {

    public static final Random random = new Random();
    char ficha;
    int numeroJugador;
    public int efectoCasilla;
    int habilidad;
    int numero;
    Casilla casillaJugador;
    Juego juego;


    public Jugador(char ficha, int numeroJugador, int numero, Juego juego) {
        this.ficha = ficha;
        this.numeroJugador = numeroJugador;
        this.juego = juego;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public int getNumeroJugador() {
        return numeroJugador;
    }

    public void setCasilla(Casilla casilla) {
        this.casillaJugador = casilla;
    }

    public void turnoJugador() {
        Scanner input = new Scanner(System.in);
        String s;

        System.out.println("Turno del jugador " + (this.numeroJugador + 1));
        System.out.println("Escribe algo para tirar los dados");
        String lanzarDados=input.next();
        if(lanzarDados.equals("guardar")) {
            System.out.println("La partida se guardara");
            juego.guardarPartida();
        }
        int movimiento = lanzarDados(this.numeroJugador);
        moverJugador(movimiento);
        System.out.println("Tu movimiento se ejecuto correctamente");
    }

    public void moverJugador(int movimiento) {

        int nuevoNumeroCasilla = this.numero + movimiento;

        if (nuevoNumeroCasilla > juego.getTamañoCamino()) {
            nuevoNumeroCasilla -= juego.getTamañoCamino();
        }

        for (int i = 0; i < juego.getTamañoTablero(); i++) {
            for (int j = 0; j < juego.getTamañoTablero(); j++) {

                for(Jugador juga:juego.tablero[i][j].getJugadores()){
                    if(juga.numeroJugador==this.numeroJugador){
                        juego.tablero[i][j].deleteJugador(juga);
                        juego.tablero[i][j].setRevelada(true);
                        break;
                    }
                }

                if (juego.tablero[i][j].getNumero() == nuevoNumeroCasilla) {
                    this.numero=nuevoNumeroCasilla;
                    juego.tablero[i][j].getJugadores().add(this);
                    this.casillaJugador=juego.tablero[i][j];
                }
            }
        }
        activarEfecto();
    }

    public void moverJugadorAtras( int movimiento) {

        int nuevoNumeroCasilla = this.numero - movimiento;

        if (nuevoNumeroCasilla < 1) {
            nuevoNumeroCasilla += juego.getTamañoCamino();
        }

        for (int i = 0; i < juego.getTamañoTablero(); i++) {
            for (int j = 0; j < juego.getTamañoTablero(); j++) {

                for(Jugador juga:juego.tablero[i][j].getJugadores()){
                    if(juga.numeroJugador==this.numeroJugador){
                        juego.tablero[i][j].deleteJugador(juga);
                        juego.tablero[i][j].setRevelada(true);
                        break;
                    }
                }

                if (juego.tablero[i][j].getNumero() == nuevoNumeroCasilla) {
                    this.numero=nuevoNumeroCasilla;
                    juego.tablero[i][j].getJugadores().add(this);
                    this.casillaJugador=juego.tablero[i][j];
                }
            }
        }
        activarEfecto();
    }

    public int lanzarDados(int indexJugador) {
        int dadoUno = random.nextInt(1, 7);
        int dadoDos = 0;
        int dado;

        if (this.efectoCasilla != 3) {
            dadoDos = random.nextInt(1, 7);
        }

        int valor = 0;
        System.out.println("Dado #1 " + dadoUno);
        if (this.efectoCasilla != 3) {
            System.out.println("Dado #2 " + dadoDos);
        }
        System.out.println("Elije cual dado usaras");
        if (this.efectoCasilla == 2) {
            System.out.println("--Se multiplicara por 2--");
        }
        if (this.efectoCasilla != 3) {
            dado = Input.between(1, 2, "Solo hay 2 dados");
        } else {
            dado = Input.integer("Solo hay 1 dado");
        }
        if (dado == 1) {
            valor = dadoUno;
        } else {
            valor = dadoDos;
        }

        if(this.efectoCasilla==2){
            return valor*2;
        }
        return valor;

    }

    public void activarEfecto() {

        switch (this.casillaJugador.getEfecto()) {
            case 4 -> {
                System.out.println("--Solo lanzas un dado tu siguiente turno--");
                this.efectoCasilla = 3;
            }
            case 5 -> {
                System.out.println("--Retrocede una casilla--");
                moverJugadorAtras( 1);
            }
            case 6 -> {
                System.out.println("--Avanza una casilla--");
                moverJugador( 1);
            }
            case 7 -> {
                juego.mostrarTablero();
                System.out.println("--Juega otra vez--");
                this.turnoJugador();
            }
            case 8 -> {
                System.out.println("--Perdera tu siguiente turno--");
                this.efectoCasilla = 1;
            }
            case 9 -> {
                System.out.println("--Tu siguiente turno tus dados valdran el doble--");
                this.efectoCasilla = 2;
            }
        }
    }
}