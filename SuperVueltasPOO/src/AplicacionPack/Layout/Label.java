package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;

import java.awt.*;

public class Label {
    private Pantalla pantalla;

    int TAMANIO_MAXIMO_ANCHO=1600;
    int TAMANIO_MAXIMO_ALTO=900;

    String text;

    private double labelAnchoProporcion;
    private double labelAltoProporcion;
    private double labelXProporcion;
    private double labelYProporcion;

    private double labelAncho;
    private double labelAlto;
    private double labelX;
    private double labelY;

    public Label(Pantalla pantalla,String text, int labelAnchoProporcion, int labelAltoProporcion, int labelXProporcion, int labelYProporcion) {
        this.pantalla = pantalla;
        this.text = text;
        //Guarda las proporciones
        this.labelAnchoProporcion = labelAnchoProporcion;
        this.labelAltoProporcion = labelAltoProporcion;
        this.labelXProporcion = labelXProporcion;
        this.labelYProporcion = labelYProporcion;

    }

    public void setText(String text) {
        this.text = text;
    }

    //Cambia el tamaño del boton de acuerdo a las proporciones
    public void escalaBoton() {

        labelAncho = pantalla.getWidth() * labelAnchoProporcion / TAMANIO_MAXIMO_ANCHO;
        labelAlto = pantalla.getHeight() * labelAltoProporcion / TAMANIO_MAXIMO_ALTO;
        labelX = (pantalla.getWidth() * labelXProporcion / TAMANIO_MAXIMO_ANCHO) - labelAncho / 2;
        labelY = (pantalla.getHeight() * labelYProporcion / TAMANIO_MAXIMO_ALTO) - labelAlto / 2;

    }

    public void dibujar(Graphics2D g2d){
        g2d.drawString(text,(int)labelX,(int)labelY);
    }
}
