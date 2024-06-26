package AplicacionPack.Layout;

import AplicacionPack.BotonesImg;
import AplicacionPack.Pantallas.Pantalla;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Clase que representa un boton en la pantalla
public class Boton {

    private Pantalla pantalla;

    int TAMANIO_MAXIMO_ANCHO=1600;
    int TAMANIO_MAXIMO_ALTO=900;

    BufferedImage imgBoton;
    BufferedImage imgBotonNormal;
    BufferedImage imgBotonHover;
    BufferedImage imgBotonPress;

    private boolean botonPress = false;
    private boolean botonHover = false;

    private double botonAnchoProporcion;
    private double botonAltoProporcion;
    private double botonXProporcion;
    private double botonYProporcion;

    private double botonAncho;
    private double botonAlto;
    private double botonX;
    private double botonY;

    //Al crearlo es necesario pasarle la pantalla a la que pertenece
    // ,el id del que sacara las imagenes y las proporciones que tendra respecto a la ventana
    public Boton(Pantalla pantalla,int id, int botonAnchoProporcion, int botonAltoProporcion, int botonXProporcion, int botonYProporcion) {
        this.pantalla = pantalla;

        //Carga las imagenes
        try {
            // Cargar las imágenes desde archivos
            this.imgBotonNormal = ImageIO.read(new File(BotonesImg.getBotonesNormales(id)));
            this.imgBotonHover = ImageIO.read(new File(BotonesImg.getBotonesHover(id)));
            this.imgBotonPress = ImageIO.read(new File(BotonesImg.getBotonesPress(id)));// Nueva imagen

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Guarda las proporciones
        this.botonAnchoProporcion = botonAnchoProporcion;
        this.botonAltoProporcion = botonAltoProporcion;
        this.botonXProporcion = botonXProporcion;
        this.botonYProporcion = botonYProporcion;

    }

    public BufferedImage getImgBoton() {
        return imgBoton;
    }

    public void setBotonHover(boolean botonHover) {
        this.botonHover = botonHover;
    }

    public void setBotonPress(boolean botonPress) {
        this.botonPress = botonPress;
    }

    public void setBotonAncho(int botonAncho) {
        this.botonAncho = botonAncho;
    }

    public void setBotonAlto(int botonAlto) {
        this.botonAlto = botonAlto;
    }

    public void setBotonX(int botonX) {
        this.botonX = botonX;
    }

    public void setBotonY(int botonY) {
        this.botonY = botonY;
    }

    public boolean isBotonPress() {
        return botonPress;
    }

    public boolean isBotonHover() {
        return botonHover;
    }

    public BufferedImage getImgBotonNormal() {
        return imgBotonNormal;
    }

    public BufferedImage getImgBotonHover() {
        return imgBotonHover;
    }

    public BufferedImage getImgBotonPress() {
        return imgBotonPress;
    }

    public JPanel getPantalla() {
        return pantalla;
    }

    public int getBotonAncho() {
        return (int) this.botonAncho;
    }

    public int getBotonAlto() {
        return (int) this.botonAlto;
    }

    public int getBotonX() {
        return (int) this.botonX;
    }

    public int getBotonY() {
        return (int) this.botonY;
    }

    //Cambia el tamaño del boton de acuerdo a las proporciones
    public void escalaBoton() {

        botonAncho = pantalla.getWidth() * botonAnchoProporcion / TAMANIO_MAXIMO_ANCHO;
        botonAlto = pantalla.getHeight() * botonAltoProporcion / TAMANIO_MAXIMO_ALTO;
        botonX = (pantalla.getWidth() * botonXProporcion / TAMANIO_MAXIMO_ANCHO) - botonAncho / 2;
        botonY = (pantalla.getHeight() * botonYProporcion / TAMANIO_MAXIMO_ALTO) - botonAlto / 2;

    }

    //Comprueba si las coordenadas estan dentro del boton
    public void verificaSobreBoton(int x, int y) {
        botonHover = x >= botonX && x <= botonX + botonAncho &&
                y >= botonY && y <= botonY + botonAlto;
    }

    //Comprueba si las coordenadas estan dentro del boton
    public void verificaPressBoton(int x, int y) {
        if(x >= botonX && x <= botonX + botonAncho &&
                y >= botonY && y <= botonY + botonAlto) botonPress = true;
    }

    //Actualiza la imagen del boton de acuerdo a su estado
    public void cambiaImgBoton() {
        if (botonPress) {
            imgBoton =imgBotonPress;
        } else if (botonHover) {
            imgBoton =imgBotonHover;
        }else{
            imgBoton =imgBotonNormal;
        }
    }

    //Repinta el boton con la imagen, las coordenadas y las medidas anteriormente calculadas
    public void dibujar(Graphics2D g2d){
        g2d.drawImage(imgBoton, (int) botonX, (int) botonY, (int) botonAncho, (int) botonAlto,pantalla);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.botonX = x;
        this.botonY = y;
        this.botonAncho = width;
        this.botonAlto = height;
    }
}
