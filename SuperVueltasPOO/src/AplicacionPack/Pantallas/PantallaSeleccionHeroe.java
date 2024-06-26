package AplicacionPack.Pantallas;

import AplicacionPack.BotonesImg;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Layout.Label;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaSeleccionHeroe extends Pantalla {

    final int NUMERO_HEROES = 4;
    double TAMAÑO_HEROE_ALTO = NUMERO_HEROES;
    double TAMAÑO_HEROE_ANCHO = NUMERO_HEROES;

    private Ventana ventana;
    int numeroJugadores;

    private Label label;
    private Boton botonAtras;
    private Boton heroePaladin;
    private Boton heroeArquero;
    private Boton heroeBanquero;
    private Boton heroeCorredor;

    /**
     * Al ser iniciado se le coloca el nombre de la pantalla que se pasa por parametro
     *
     * @param ventana
     */
    public PantallaSeleccionHeroe(Ventana ventana, int numeroJugadores) {
        super("PantallaSeleccionHeroe");
        this.ventana = ventana;
        this.numeroJugadores = numeroJugadores;

        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75);

        this.label = new Label(this,"Turno de jugador 1",150,150,800,150);
        this.heroePaladin = new Boton(this, 16, 330, 450, 330, 450);
        this.heroeArquero = new Boton(this, 15, 330, 450, 660, 450);
        this.heroeBanquero = new Boton(this, 14, 330, 450, 990, 450);
        this.heroeCorredor = new Boton(this, 13, 330, 450, 1320, 450);

        getBotones().add(heroePaladin);
        getBotones().add(heroeArquero);
        getBotones().add(heroeBanquero);
        getBotones().add(heroeCorredor);
        getLabels().add(label);

        getBotones().add(botonAtras);

        try {
            setImagen(ImageIO.read(new File(BotonesImg.getPantallasImg(6))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boton getBotonAtras() {
        return botonAtras;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public Boton getHeroePaladin() {
        return heroePaladin;
    }

    public Boton getHeroeArquero() {
        return heroeArquero;
    }

    public Boton getHeroeBanquero() {
        return heroeBanquero;
    }

    public Boton getHeroeCorredor() {
        return heroeCorredor;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
