package AplicacionPack.Pantallas;

import AplicacionPack.BotonesImg;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Pantalla que se lanza al iniciar la aplicacion
//Posee 2 botones, Start y Salir
public class PantallaInicio extends Pantalla {

    private Ventana ventana;

    private Boton botonStart;
    private Boton botonSalir;


    public Boton getBotonStart() {
        return botonStart;
    }
    public Boton getBotonSalir() {
        return botonSalir;
    }

    public PantallaInicio(Ventana ventana) {

        super("PantallaInicio");
        this.ventana = ventana;
        this.botonStart = new Boton
                (this, 2,
                        150, 150,
                        800,  350);
        this.botonSalir = new Boton
                (this, 3,
                        150, 150,
                        800,  500);

        getBotones().add(this.botonSalir);
        getBotones().add(this.botonStart);

        try {
            setImagen(ImageIO.read(new File(BotonesImg.getPantallasImg(1))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
