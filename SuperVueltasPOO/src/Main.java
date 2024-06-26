import AplicacionPack.Layout.TextBox;
import AplicacionPack.Pantallas.*;
import AplicacionPack.Ventana;

import static MenuPack.Menu.presentaMenu;

//Ejecuta el juego
public class Main {


    public static void main(String[] args) {
        mostrarMenu();

        //presentaMenu();
    }

    //Inicia el juego
    public static void mostrarMenu() {

        //Crea un nuevo objeto de clase Ventana
        Ventana ventana = new Ventana();

        //Crea una pantalla y la agrega a ventana
        Pantalla pantalla = new PantallaInicio(ventana);

        //Muestra la pantalla
        ventana.setPantalla(pantalla);
        ventana.getContentPane().add(pantalla);
        ventana.setVisible(true);

        //Constantemente revisamos la pantalla que se encuentre en la ventana
        while (true) {

            pantalla = ventana.getPantalla();

            //Al presionar un boton en una pantalla cambia la pantalla
            if (pantalla.getTipoDePantalla().equals("PantallaInicio")) {
                if (((PantallaInicio) pantalla).getBotonStart().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
                } else if (((PantallaInicio) pantalla).getBotonSalir().isBotonPress()) {
                    descansar(50);
                    System.exit(0);
                }
            } else if (pantalla.getTipoDePantalla().equals("PantallaModoDeJuego")) {
                if (((PantallaModoDeJuego) pantalla).getBotonAtras().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaInicio(ventana));
                } else if (((PantallaModoDeJuego) pantalla).getBotonPartidaCompetitiva().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaPartidaCompetitiva(ventana));
                } else if (((PantallaModoDeJuego) pantalla).getBotonCargarPartida().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaCargarPartida(ventana));
                }
            } else if (pantalla.getTipoDePantalla().equals("PantallaPartidaCompetitiva")) {
                if (((PantallaPartidaCompetitiva) pantalla).getBotonAtras().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
                } else if (((PantallaPartidaCompetitiva) pantalla).getBoton2Jugadores().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana, 2));
                } else if (((PantallaPartidaCompetitiva) pantalla).getBoton3Jugadores().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana, 3));
                } else if (((PantallaPartidaCompetitiva) pantalla).getBoton4Jugadores().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana, 4));
                }
            } else if (pantalla.getTipoDePantalla().equals("PantallaCargarPartida")) {
                if (((PantallaCargarPartida) pantalla).getBotonAtras().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
                } else if (((PantallaCargarPartida) pantalla).getBotonCargar1().isBotonPress()) {
                    descansar(50);
                } else if (((PantallaCargarPartida) pantalla).getBotonCargar2().isBotonPress()) {
                    descansar(50);
                } else if (((PantallaCargarPartida) pantalla).getBotonCargar3().isBotonPress()) {
                    descansar(50);
                }
            } else if (pantalla.getTipoDePantalla().equals("PantallaSeleccionHeroe")) {
                if (((PantallaSeleccionHeroe) pantalla).getBotonAtras().isBotonPress()) {
                    descansar(50);
                    ventana.cambiarPantalla(new PantallaInicio(ventana));
                }
                seleccionHeroes(pantalla, ventana);
                System.out.println("Se termino la seleccion de heroes");
            }

            descansar(10);
        }
    }

    public static void seleccionHeroes(Pantalla pantalla, Ventana ventana) {

        boolean heroeYaSeleccionado = false;
        int heroesSeleccionados = 0;
        int[] heroesJugadores = new int[4];

        while (heroesSeleccionados < ((PantallaSeleccionHeroe) pantalla).getNumeroJugadores()) {
            if (((PantallaSeleccionHeroe) pantalla).getBotonAtras().isBotonPress()) {
                break;
            } else if (((PantallaSeleccionHeroe) pantalla).getHeroeArquero().isBotonPress()) {
                for (int i : heroesJugadores) {
                    if (i == 13) {
                        heroeYaSeleccionado = true;
                        break;
                    }
                }
                if (!heroeYaSeleccionado) {
                    heroesJugadores[heroesSeleccionados] = 13;
                    heroesSeleccionados++;
                } else {
                    ((PantallaSeleccionHeroe) pantalla).getLabel().setText("Este heroe ya fue seleccionado");
                }
                heroeYaSeleccionado = false;
            }
            if (((PantallaSeleccionHeroe) pantalla).getHeroeBanquero().isBotonPress()) {
                for (int i : heroesJugadores) {
                    if (i == 14) {
                        heroeYaSeleccionado = true;
                        break;
                    }
                }
                if (!heroeYaSeleccionado) {
                    heroesJugadores[heroesSeleccionados] = 14;
                    heroesSeleccionados++;
                } else {
                    ((PantallaSeleccionHeroe) pantalla).getLabel().setText("Este heroe ya fue seleccionado");
                }
                heroeYaSeleccionado = false;
            }
            if (((PantallaSeleccionHeroe) pantalla).getHeroeCorredor().isBotonPress()) {
                for (int i : heroesJugadores) {
                    if (i == 15) {
                        heroeYaSeleccionado = true;
                        break;
                    }
                }
                if (!heroeYaSeleccionado) {
                    heroesJugadores[heroesSeleccionados] = 15;
                    heroesSeleccionados++;
                } else {
                    ((PantallaSeleccionHeroe) pantalla).getLabel().setText("Este heroe ya fue seleccionado");
                }
                heroeYaSeleccionado = false;
            }
            if (((PantallaSeleccionHeroe) pantalla).getHeroePaladin().isBotonPress()) {
                for (int i : heroesJugadores) {
                    if (i == 16) {
                        heroeYaSeleccionado = true;
                        break;
                    }
                }
                if (!heroeYaSeleccionado) {
                    heroesJugadores[heroesSeleccionados] = 16;
                    heroesSeleccionados++;
                } else {
                    ((PantallaSeleccionHeroe) pantalla).getLabel().setText("Este heroe ya fue seleccionado");
                }
                heroeYaSeleccionado = false;
            }
            System.out.println("SeleccionandoHeroes");
            descansar(10);
        }
        System.out.println("Se elijieron todos los personajes");
    }

    //Metodo que descansa X segundo el hilo
    public static void descansar(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Toda la logica de las pantallas se basa en revisar si se presiono un boton especifico en una
     * pantalla especifica y en base a eso cambiar la pantalla que se muestra en la ventana
     */
}
