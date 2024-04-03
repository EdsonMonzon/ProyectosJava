public class Main {
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

    public static void empezarPartidaClasica(){
        System.out.println("Cuantos jugadores jugaran esta partida?");
        int numJugadores=inputU.between(2,4,"Deben haber entre 2 y 4 jugadores");
        Juego partida= new Juego(numJugadores,5);
        partida.generarTablero();
        partida.crearJugadores();
        partida.mostrarTablero();


        while (true) {
            for (int i = 0; i < partida.numeroJugadores; i++) {

                if(partida.listaJugadores[i].efectoCasilla!=1){
                    partida.turnoJugador(i);
                    partida.mostrarTablero();
                }else{
                    System.out.println("Este jugador ha perdido su turno");
                    partida.listaJugadores[i].efectoCasilla=0;
                }
            }
        }

    }
}