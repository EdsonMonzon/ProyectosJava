package AplicacionPack.Pantallas;

import AplicacionPack.BotonesImg;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaPartidaCompetitiva extends Pantalla{

    private Ventana ventana;

    private Boton botonAtras;
    private Boton boton2Jugadores;
    private Boton boton3Jugadores;
    private Boton boton4Jugadores;



    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBoton2Jugadores() {
        return boton2Jugadores;
    }

    public Boton getBoton3Jugadores() {
        return boton3Jugadores;
    }

    public Boton getBoton4Jugadores() {
        return boton4Jugadores;
    }

    public PantallaPartidaCompetitiva(Ventana ventana) {

        super("PantallaPartidaCompetitiva");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75);
        this.boton2Jugadores = new Boton(this, 10, 150, 150, 800, 350);
        this.boton3Jugadores = new Boton(this, 11, 150, 150, 800, 500);
        this.boton4Jugadores = new Boton(this, 12, 150, 150, 800, 650);

        getBotones().add(botonAtras);
        getBotones().add(boton2Jugadores);
        getBotones().add(boton3Jugadores);
        getBotones().add(boton4Jugadores);

        try {
            setImagen(ImageIO.read(new File(BotonesImg.getPantallasImg(3))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
