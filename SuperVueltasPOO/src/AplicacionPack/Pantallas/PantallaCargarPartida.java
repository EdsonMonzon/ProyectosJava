package AplicacionPack.Pantallas;

import AplicacionPack.BotonesImg;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Tipo de pantalla que se lanza cuando se quiere cargar una partida guardada
//Posee  3 botones y el boton atras
public class PantallaCargarPartida extends Pantalla{


    private Ventana ventana;

    private Boton botonAtras;
    private Boton botonCargar1;
    private Boton botonCargar2;
    private Boton botonCargar3;


    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBotonCargar1() {
        return botonCargar1;
    }

    public Boton getBotonCargar2() {
        return botonCargar2;
    }

    public Boton getBotonCargar3() {
        return botonCargar3;
    }

    public PantallaCargarPartida(Ventana ventana) {

        super("PantallaCargarPartida");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75);
        this.botonCargar1 = new Boton(this, 7, 150, 150, 800, 350);
        this.botonCargar2 = new Boton(this, 8, 150, 150, 800, 500);
        this.botonCargar3 = new Boton(this, 9, 150, 150, 800, 650);

        getBotones().add(botonAtras);
        getBotones().add(botonCargar1);
        getBotones().add(botonCargar2);
        getBotones().add(botonCargar3);

        try {
            setImagen(ImageIO.read(new File(BotonesImg.getPantallasImg(5))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
