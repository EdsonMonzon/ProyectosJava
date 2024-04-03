import java.util.Random;
import java.util.Scanner;


public class Juego {

    private final Random random = new Random();
    int numeroJugadores = 4;
    int tamañoTablero = 5;
    int tamañoJuego = (tamañoTablero - 1) * 4;
    Casilla[][] tablero = new Casilla[5][5];
    Jugador[] listaJugadores = new Jugador[4];

    public Juego(int numeroJugadores, int tamañoTablero) {
        this.numeroJugadores = numeroJugadores;
        this.tamañoTablero = tamañoTablero;
    }

    public void generarTablero() {
        this.tablero = new Casilla[this.tamañoTablero][this.tamañoTablero];

        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {

                if (i == 0 && j == 0) {
                    this.tablero[i][j] = new Casilla(i, j, 1);

                } else if (i == 0 || i == tamañoTablero - 1 || j == 0 || j == tamañoTablero - 1) {
                    this.tablero[i][j] = new Casilla(i, j, random.nextInt(1, 10));

                } else {
                    this.tablero[i][j] = new Casilla(i, j, 0);
                }
            }
        }
        asignarNumeroCasillas();
    }

    public void asignarNumeroCasillas() {

        int fila=0;
        int columna=0;

        int pasosFaltantes = tamañoJuego;
        int contador=1;

        while (pasosFaltantes>0) {

            this.tablero[fila][columna].numero=contador;

            if (fila == 0 && columna != tamañoTablero - 1) {
                columna++;
                contador++;
                pasosFaltantes--;
            } else if (columna == tamañoTablero - 1 && fila != tamañoTablero - 1) {
                fila++;
                contador++;
                pasosFaltantes--;
            } else if (fila == tamañoTablero - 1 && columna != 0) {
                columna--;
                contador++;
                pasosFaltantes--;
            } else {
                fila--;
                contador++;
                pasosFaltantes--;
            }
        }
    }

    public void crearJugadores() {

        this.listaJugadores = new Jugador[this.numeroJugadores];

        for (int i = 0; i < this.numeroJugadores; i++) {
            char ficha = pedirFicha(i);
            this.listaJugadores[i] = new Jugador(ficha, i, tablero[0][0]);
        }
    }

    public char pedirFicha(int numeroJugador) {
        Scanner input = new Scanner(System.in);
        System.out.println("Jugador " + (numeroJugador + 1) + " introduce tu ficha");
        String cadena = input.next();
        return cadena.charAt(0);
    }

    public void mostrarTablero() {


        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {


                verificarFicha(i, j);

                if (tablero[i][j].hayFicha) {
                    for (int k = 0; k < numeroJugadores; k++) {
                        if (listaJugadores[k].casillaJugador.numero == tablero[i][j].numero) {
                            System.out.print("  " + listaJugadores[k].ficha + "  ");
                            break;
                        }
                    }
                } else if (!tablero[i][j].revelada) {

                    if (tablero[i][j].valor == 0) {
                        System.out.print("     ");
                    } else if (0 < tablero[i][j].valor && tablero[i][j].valor < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        System.out.print(" [?] ");
                    }
                } else {
                    if (tablero[i][j].valor > 0 && tablero[i][j].valor < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        switch (tablero[i][j].valor) {
                            case 4 -> System.out.print(" [/] ");
                            case 5 -> System.out.print(" [<] ");
                            case 6 -> System.out.print(" [>] ");
                            case 7 -> System.out.print(" [+] ");
                            case 8 -> System.out.print(" [-] ");
                            case 9 -> System.out.print(" [x] ");
                        }
                    }
                }
            }
            System.out.println("\n");
        }
        for (int i = 0; i < this.numeroJugadores; i++) {
            System.out.println("El jugador " + (i + 1) + " esta en la casilla " + listaJugadores[i].casillaJugador.numero);
        }
    }

    public void verificarFicha(int i, int j) {

        boolean hayJugador = false;

        for (int k = 0; k < this.numeroJugadores; k++) {
            if (tablero[i][j] == this.listaJugadores[k].casillaJugador) {
                hayJugador = true;
            }
        }

        if (hayJugador) {
            this.tablero[i][j].hayFicha = true;
        }else{
            this.tablero[i][j].hayFicha=false;
        }
    }

    public void turnoJugador(int indexJugador) {
        Scanner input = new Scanner(System.in);
        String s;

        System.out.println("Turno del jugador " + (indexJugador + 1));
        System.out.println("Escribe algo para tirar los dados");
        input.next();
        int movimiento = lanzarDados(indexJugador);
        moverJugador(indexJugador, movimiento);
        System.out.println("Tu movimiento se ejecuto correctamente");
        this.activarEfecto(indexJugador);
    }

    public void moverJugador(int indexJugador, int movimiento) {

        int nuevoNumeroCasilla = this.listaJugadores[indexJugador].casillaJugador.numero + movimiento;
        if (nuevoNumeroCasilla > this.tamañoJuego) {
            nuevoNumeroCasilla -= this.tamañoJuego;
        }

        for (int i = 0; i < this.tamañoTablero; i++) {
            for (int j = 0; j < this.tamañoTablero; j++) {
                if (this.tablero[i][j].numero == nuevoNumeroCasilla) {
                    tablero[this.listaJugadores[indexJugador].casillaJugador.filaPos][this.listaJugadores[indexJugador].casillaJugador.columnaPos].revelada=true;
                    this.listaJugadores[indexJugador].casillaJugador = this.tablero[i][j];
                }
            }
        }
    }

    public void moverJugadorAtras(int indexJugador, int movimiento) {

        int nuevoNumeroCasilla = this.listaJugadores[indexJugador].casillaJugador.numero - movimiento;
        if (nuevoNumeroCasilla < this.tamañoJuego) {
            nuevoNumeroCasilla += this.tamañoJuego;
        }

        for (int i = 0; i < this.tamañoTablero; i++) {
            for (int j = 0; j < this.tamañoTablero; j++) {
                if (this.tablero[i][j].numero == nuevoNumeroCasilla) {
                    tablero[this.listaJugadores[indexJugador].casillaJugador.filaPos][this.listaJugadores[indexJugador].casillaJugador.columnaPos].revelada=true;
                    this.listaJugadores[indexJugador].casillaJugador = this.tablero[i][j];
                }
            }
        }
        mostrarTablero();
    }

    public int lanzarDados(int indexJugador) {
        int dadoUno = random.nextInt(1, 7);
        int dadoDos = 0;
        int dado;

        if (this.listaJugadores[indexJugador].efectoCasilla != 3) {
            dadoDos = random.nextInt(1, 7);
        }

        int valor = 0;
        System.out.println("Dado #1 " + dadoUno);
        if (this.listaJugadores[indexJugador].efectoCasilla != 3) {
            System.out.println("Dado #2 " + dadoDos);
        }
        System.out.println("Elije cual dado usaras");
        if (this.listaJugadores[indexJugador].efectoCasilla == 2) {
            System.out.println("--Se multiplicara por 2--");
        }
        if (this.listaJugadores[indexJugador].efectoCasilla != 3) {
            dado = inputU.between(1, 2, "Solo hay 2 dados");
        } else {
            dado = inputU.entero("Solo hay 1 dado");
        }
        if (dado == 1) {
            valor = dadoUno;
        } else {
            valor = dadoDos;
        }
        return valor;

    }

    public void activarEfecto(int indexJugador) {

        switch (this.listaJugadores[indexJugador].casillaJugador.valor) {
            case 4 -> {
                System.out.println("--Solo lanzas un dado tu siguiente turno--");
                this.listaJugadores[indexJugador].efectoCasilla = 3;
            }
            case 5 -> {
                System.out.println("--Retrocede una casilla--");
                moverJugadorAtras(indexJugador, 1);
            }
            case 6 -> {
                System.out.println("--Avanza una casilla--");
                moverJugador(indexJugador, 1);
            }
            case 7 -> {
                mostrarTablero();
                System.out.println("--Juega otra vez--");
                turnoJugador(this.listaJugadores[indexJugador].numeroJugador);
            }
            case 8 -> {
                System.out.println("--Perdera tu siguiente turno--");
                this.listaJugadores[indexJugador].efectoCasilla = 1;
            }
            case 9 -> {
                System.out.println("--Tu siguiente turno tus dados valdran el doble--");
                this.listaJugadores[indexJugador].efectoCasilla = 2;
            }
        }
    }
}
