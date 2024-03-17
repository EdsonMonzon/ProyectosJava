import java.util.Random;

public class Main {
        private static final int FILAS = 30;
        private static final int COLUMNAS = 30;

        public static void main(String[] args) {
            int[][] tableroActual = generarTableroAleatorio(FILAS, COLUMNAS);
            imprimirTablero(tableroActual);

            int iteracion = 0;
            while (true) {
                int[][] nuevoTablero = siguienteGeneracion(tableroActual);
                imprimirTablero(nuevoTablero);

                if (esIgual(tableroActual, nuevoTablero)) {
                    System.out.println("El juego ha llegado a un estado estático.");
                    break;
                }

                if (iteracion >= 2 && esIgual(nuevoTablero, siguienteGeneracion(nuevoTablero))) {
                    System.out.println("El juego ha entrado en un bucle infinito.");
                    break;
                }

                tableroActual = nuevoTablero;
                iteracion++;
            }

            System.out.println("Iteraciones totales: " + iteracion);
        }

        private static int[][] generarTableroAleatorio(int filas, int columnas) {
            int[][] tablero = new int[filas][columnas];
            Random random = new Random();

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    tablero[i][j] = random.nextInt(2);
                }
            }

            return tablero;
        }

        private static void imprimirTablero(int[][] tablero) {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    System.out.print(tablero[i][j] == 1 ? "■" : "□");
                }
                System.out.println();
            }
            System.out.println();
        }

        private static int[][] siguienteGeneracion(int[][] tableroActual) {
            int filas = tableroActual.length;
            int columnas = tableroActual[0].length;
            int[][] nuevoTablero = new int[filas][columnas];

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    int vecinosVivos = contarVecinosVivos(tableroActual, i, j);
                    if (tableroActual[i][j] == 1) {
                        nuevoTablero[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                    } else {
                        nuevoTablero[i][j] = (vecinosVivos == 3) ? 1 : 0;
                    }
                }
            }

            return nuevoTablero;
        }

        private static int contarVecinosVivos(int[][] tablero, int fila, int columna) {
            int filas = tablero.length;
            int columnas = tablero[0].length;
            int count = 0;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nuevaFila = (fila + i + filas) % filas;
                    int nuevaColumna = (columna + j + columnas) % columnas;
                    count += tablero[nuevaFila][nuevaColumna];
                }
            }

            count -= tablero[fila][columna];
            return count;
        }

        private static boolean esIgual(int[][] tablero1, int[][] tablero2) {
            for (int i = 0; i < tablero1.length; i++) {
                for (int j = 0; j < tablero1[i].length; j++) {
                    if (tablero1[i][j] != tablero2[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
