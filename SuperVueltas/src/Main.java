import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    final static int maxJugadors = 4;
    final static int TABLEROTAMAÑO = 5;
    final static int CASILLASDELTABLERO = (TABLEROTAMAÑO - 1) * 4;
    static int[][] TABLERO = new int[TABLEROTAMAÑO][TABLEROTAMAÑO];

    static int[][] CASILLASREVELADAS = new int[CASILLASDELTABLERO][2];
    static int[][] PLAYERSCORDS = new int[maxJugadors][2];
    static int[] PLAYERSEFECTOS = new int[maxJugadors];
    static int[] PLAYERSCASILLAS = new int[maxJugadors];
    static char[] JUGADORESICONS = new char[maxJugadors];
    final static Scanner input = new Scanner(System.in);

    final static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("""
                Bienvenido a SuperVueltas, elije una opcion
                1.Jugar partida clasica
                2.Jugar una partida personalizada
                3.Cargar una partida
                4.Salir
                """);
        int opcio = inputU.between(1, 4, "Introduce un numero que este entre las opciones");
        switch (opcio) {
            case 1 -> {
                empezarPartidaClasica();
            }
            /**
             case 2 -> {
             personalizarPartida();
             }
             case 3 -> {
             cargarPartida();
             }
             case 4 -> {
             salir();
             }*/
        }
    }

    public static void empezarPartidaClasica() {
        boolean finPartida = false;

        System.out.println("Cuantos jugadores jugaran esta partida?");
        int numJugadores = inputU.between(2, 4, "Solo pueden haber de 2 a 4 jugadores");

        elijePersonaje(numJugadores);

        System.out.println("El tablero ha sido generado, tenemos casillas vacias [ ] y casillas especiales [X]");
        TABLERO = generarTablero(numJugadores);

        while (!finPartida) {
            for (int i = 0; i < numJugadores; i++) {
                if(PLAYERSEFECTOS[i]!=1){
                    turnoJugador(i, numJugadores);
                    mostrarTablero(TABLERO, numJugadores);
                }else{
                    System.out.println("Este jugador ha perdido su turno");
                    PLAYERSEFECTOS[i]=0;
                }
            }
        }
    }

    public static void turnoJugador(int jugador, int numJugadores) {
        System.out.println("Turno del jugador " + (jugador + 1));
        System.out.println("Escribe algo para tirar los dados");
        input.next();
        int movimiento = lanzarDados(jugador);
        contadorCasillas(jugador, movimiento);
        moverJugador(jugador, movimiento, numJugadores);
        System.out.println("Tu movimiento se ejecuto correctamente");
        efectoCasilla(jugador,numJugadores);
        System.out.println("Turno del siguiente jugador");
    }

    public static void efectoCasilla(int jugador, int numJugadores) {
        int casillaValor = TABLERO[PLAYERSCORDS[jugador][0]][PLAYERSCORDS[jugador][1]];
        switch (casillaValor) {
            case 4 -> {
                System.out.println("--Solo lanzas un dado tu siguiente turno--");
                PLAYERSEFECTOS[jugador]=3;
            }
            case 5 -> {
                System.out.println("--Retrocede una casilla--");
                moverJugadorAtras(jugador,1,numJugadores);}
            case 6 -> {
                System.out.println("--Avanza una casilla--");
                moverJugador(jugador,1,numJugadores);}
            case 7 -> {
                System.out.println("--Juega otra vez--");
                turnoJugador(jugador,numJugadores);
                mostrarTablero(TABLERO, numJugadores);
            }
            case 8 -> {
                System.out.println("--Perdera tu siguiente turno--");
                PLAYERSEFECTOS[jugador]=1;
            }
            case 9 -> {
                System.out.println("--Tu siguiente turno tus dados valdran el doble--");
                PLAYERSEFECTOS[jugador]=2;
            }
        }
    }

    public static void moverJugadorAtras(int player, int pasos,int numJugadores) {

        int[] playerCords = PLAYERSCORDS[player];
        int pasosFaltantes = pasos;

        int fila = playerCords[0];
        int columna = playerCords[1];

        while (pasosFaltantes > 0) {

            if (fila == 0 && columna != TABLEROTAMAÑO - 1) {
                columna--;
                pasosFaltantes--;
            } else if (columna == TABLEROTAMAÑO - 1 && fila != TABLEROTAMAÑO - 1) {
                fila--;
                pasosFaltantes--;
            } else if (fila == TABLEROTAMAÑO - 1 && columna != 0) {
                columna++;
                pasosFaltantes--;
            } else {
                fila++;
                pasosFaltantes--;
            }

        }
        PLAYERSCORDS[player] = new int[]{fila, columna};
        CASILLASREVELADAS[PLAYERSCASILLAS[player]] = new int[]{fila, columna};
        mostrarTablero(TABLERO,numJugadores);
    }
    public static void contadorCasillas(int jugador, int pasos) {
        PLAYERSCASILLAS[jugador] += pasos;
        if (PLAYERSCASILLAS[jugador] > (CASILLASDELTABLERO - 1)) {
            PLAYERSCASILLAS[jugador] -= CASILLASDELTABLERO;
        }
    }

    public static void moverJugador(int player, int pasos, int numJugadores) {

        int[] playerCords = PLAYERSCORDS[player];
        int pasosFaltantes = pasos;

        if(PLAYERSEFECTOS[player]==2){
            pasosFaltantes*=2;
        }

        int fila = playerCords[0];
        int columna = playerCords[1];

        while (pasosFaltantes > 0) {

            if (fila == 0 && columna != TABLEROTAMAÑO - 1) {
                columna++;
                pasosFaltantes--;
            } else if (columna == TABLEROTAMAÑO - 1 && fila != TABLEROTAMAÑO - 1) {
                fila++;
                pasosFaltantes--;
            } else if (fila == TABLEROTAMAÑO - 1 && columna != 0) {
                columna--;
                pasosFaltantes--;
            } else {
                fila--;
                pasosFaltantes--;
            }

        }
        PLAYERSCORDS[player] = new int[]{fila, columna};
        CASILLASREVELADAS[PLAYERSCASILLAS[player]] = new int[]{fila, columna};
        mostrarTablero(TABLERO,numJugadores);
    }

    public static int lanzarDados(int jugador) {
        int dadoUno = random.nextInt(1, 7);
        int dadoDos=0;
        int dado;

        if(PLAYERSEFECTOS[jugador]!=3){
            dadoDos = random.nextInt(1, 7);
        }

        int valor = 0;
        System.out.println("Dado #1 " + dadoUno);
        if(PLAYERSEFECTOS[jugador]!=3){
            System.out.println("Dado #2 " + dadoDos);
        }
        System.out.println("Elije cual dado usaras");
        if(PLAYERSEFECTOS[jugador]==2){
            System.out.println("--Se multiplicara por 2--");
        }
        if(PLAYERSEFECTOS[jugador]!=3){
            dado = inputU.between(1, 2, "Solo hay 2 dados");
        }else{
            dado=inputU.entero("Solo hay 1 dado");
        }
        if (dado == 1) {
            valor = dadoUno;
        } else {
            valor = dadoDos;
        }
        return valor;
    }

    public static void elijePersonaje(int numJug) {
        for (int i = 0; i < numJug; i++) {
            System.out.println("Jugador " + (i + 1) + " introduce una letra para que sea su ficha");
            JUGADORESICONS[i] = pedirChar();
        }
    }

    public static char pedirChar() {
        char ficha = (input.next()).charAt(0);
        if (!Character.isLetter(ficha)) {
            System.out.println("El simbolo introducido no es valido vuelve a intentarlo");
            pedirChar();
        }
        return ficha;

    }

    public static int[][] generarTablero(int numJugadores) {
        int[][] tablero = new int[TABLEROTAMAÑO][TABLEROTAMAÑO];

        for (int i = 0; i < TABLEROTAMAÑO; i++) {
            for (int j = 0; j < TABLEROTAMAÑO; j++) {
                if (i == 0 && j == 0) {
                    tablero[i][j] = 1;
                } else if (i == 0 || i == TABLEROTAMAÑO - 1 || j == 0 || j == TABLEROTAMAÑO - 1) {
                    tablero[i][j] = casillaRandom();
                } else {
                    tablero[i][j] = 0;
                }
            }
        }

        mostrarTablero(tablero, numJugadores);
        return tablero;
    }

    public static int casillaRandom() {

        return random.nextInt(1, 10);
    }

    public static void mostrarTablero(int[][] tablero, int numJugadores) {

        for (int i = 0; i < TABLEROTAMAÑO; i++) {
            for (int j = 0; j < TABLEROTAMAÑO; j++) {

                boolean hayPieza = false;
                boolean casillaRevelada = false;
                int jugadorEncontrado = 0;

                int casilla = tablero[i][j];

                for (int k = 0; k < numJugadores; k++) {
                    if (i == PLAYERSCORDS[k][0] && j == PLAYERSCORDS[k][1]) {

                        hayPieza = true;
                        jugadorEncontrado = k;

                    }
                }
                for (int k = 0; k < CASILLASDELTABLERO; k++) {
                    if (i == CASILLASREVELADAS[k][0] && j == CASILLASREVELADAS[k][1]) {

                        casillaRevelada = true;
                    }
                }

                if (hayPieza) {
                    System.out.print("  " + JUGADORESICONS[jugadorEncontrado] + "  ");
                } else if (!casillaRevelada) {

                    if (casilla == 0) {
                        System.out.print("     ");
                    } else if (0 < casilla && casilla < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        System.out.print(" [?] ");
                    }
                } else {
                    if (casilla > 0 && casilla < 4) {
                        System.out.print(" [ ] ");
                    } else {
                        switch (casilla) {
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
        for (int i = 0; i < maxJugadors; i++) {
            System.out.println("El jugador " + (i + 1) + " esta en la casilla " + PLAYERSCASILLAS[i]);
        }
    }
}