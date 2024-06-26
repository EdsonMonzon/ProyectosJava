package AplicacionPack.Pantallas;

import AplicacionPack.BotonesImg;
import AplicacionPack.Layout.*;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Pantalla para elegir un modo de juego
//Posee 3 botones, Compe, Personalizada y cargar  partida ademas del boton atras
public class PantallaModoDeJuego extends Pantalla {

    private Ventana ventana;

    private Boton botonAtras;
    private Boton botonPartidaCompetitiva;
    private Boton botonCargarPartida;


    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBotonPartidaCompetitiva() {
        return botonPartidaCompetitiva;
    }

    public Boton getBotonCargarPartida() {
        return botonCargarPartida;
    }

    public PantallaModoDeJuego(Ventana ventana) {

        super("PantallaModoDeJuego");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75);
        this.botonPartidaCompetitiva = new Boton(this, 4, 150, 150, 800, 350);
        this.botonCargarPartida = new Boton(this, 6, 150, 150, 800, 500);

        getBotones().add(botonAtras);
        getBotones().add(botonPartidaCompetitiva);
        getBotones().add(botonCargarPartida);

        try {
            setImagen(ImageIO.read(new File(BotonesImg.getPantallasImg(2))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
